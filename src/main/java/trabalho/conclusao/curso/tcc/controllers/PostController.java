package trabalho.conclusao.curso.tcc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import trabalho.conclusao.curso.tcc.entities.*;
import trabalho.conclusao.curso.tcc.services.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    @Autowired
    private PostService service;
    @Autowired
    private UsuarioService userService;
    @Autowired
    private PostMoradiaService postMoradiaService;
    @Autowired
    private DetalhesMoradiaService detalhesMoradiaService;
    @Autowired
    private FotosService fotosService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "userPosts/{id}")
    public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Long id) {
        List<Post> obj = service.findPostByUserId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/cidade/{cidade}")
    public ResponseEntity<List<Post>> findAllPostBycidade(@PathVariable String cidade){
        List<Post> obj = service.findAllPostBycidade(cidade);
        return ResponseEntity.ok().body(obj);
    }



    /*@PostMapping
    public ResponseEntity<Post> insert (@RequestBody Post obj){
        obj = service.insert(obj);
       // System.out.println(obj.getUsuario().getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);    //created(espera um obj uri) para voltar o voltar o padrao http certo
    }*/

    @GetMapping(value = "/fotoperfil/{imageName:.+}", produces = "image/jpeg")
    public ResponseEntity<Resource> getUserProfilePicture(@PathVariable String imageName){
        try {
            // Caminho para o diretório onde as imagens estão armazenadas
            String imageDirectory = "src\\main\\resources\\static\\images\\image-perfil\\";

            // Construa o caminho completo para a imagem
            String imagePath = imageDirectory + imageName;

            // Crie um recurso a partir do caminho da imagem
            Resource resource = new UrlResource("file:" + imagePath);

            // Verifique se o arquivo existe
            if (resource.exists() && resource.isReadable()) {
                System.out.println("deu bao");
                return ResponseEntity.ok(resource);

            } else {
                System.out.println("deu ruim");
                return ResponseEntity.notFound().build();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/cadastro/{id}")
    public ResponseEntity<Fotos> insertCorreto (@RequestParam("file") MultipartFile imagem, @PathVariable Long id){
        Fotos fotos = new Fotos();
        try{
            if (imagem.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            String imagesPaste = "src\\main\\resources\\static\\images\\image-moradias\\";
            File dir = new File(imagesPaste);
            if (!dir.exists()){
                dir.mkdirs();
            }

            Date dataHora = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String nomeOriginal = imagem.getOriginalFilename();
            String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
            String dataString = formato.format(dataHora);

            String semBarra = dataString.replace("/","");
            String semDoisPontos = semBarra.replace(":","");
            String semPontos = semDoisPontos.replace(".","");

            File serverFile = new File(imagesPaste + id + semPontos + extensao);

            try (FileOutputStream stream = new FileOutputStream(serverFile)){
                stream.write(imagem.getBytes());

            }

            PostMoradia postMoradia = postMoradiaService.findById(id);
            fotos.setCaminhoImagem(serverFile.toString());
            fotos.setNomeFoto(serverFile.getName());
            fotos.setPostMoradia(postMoradia);



            //fotos.setCaminhoImagem(serverFile.get);
            System.out.println("==================================================================================================");
            System.out.println(serverFile.getCanonicalPath());
            // System.out.println(serverFile.getName());*
            //  System.out.println(serverFile.toPath());*
            System.out.println(serverFile.toURI());
            System.out.println(serverFile.toString());
            System.out.println(serverFile.getTotalSpace());


            //fotosService.insert(fotos);
            return ResponseEntity.ok().body(fotos);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(fotos);
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Post> insert( @RequestBody Post obj, @PathVariable Long id) {


        Usuario user = userService.findById(id);
        //Post post = obj;
        if (user != null) {
            obj.setUsuario(user);
        }

        if (obj.getPostMoradia() != null) {
            if (obj.getPostMoradia().getFotos().size() > 0) {
                obj.getPostMoradia().getFotos();
                for (int i = 0; i < obj.getPostMoradia().getFotos().size(); i++) {
                    obj.getPostMoradia().getFotos().get(i).setPostMoradia(obj.getPostMoradia());
                }
            }
        }




        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);    //created(espera um obj uri) para voltar o voltar o padrao http certo

        // return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PostMapping(value = "/denunciar/{id}")
    public ResponseEntity<Long> addDenuncia(@PathVariable Long id) {
        Post post = service.findById(id);
        int valor = post.getQntdDenuncia();
        valor++;

        post.setQntdDenuncia(valor);
        service.editDenuncia(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(id);    //created(espera um obj uri) para voltar o voltar o padrao http certo
        //return ResponseEntity.created(uri);
        //return ResponseEntity.accepted().body(id);
    }


    //EDITAR O POSTMORADIA TENHO Q FAZER O PRA EDITAR SO O POST COM POSTMORADIA NULL
    @PostMapping(value = "edit/{idUser}/{idMoradia}")
    public ResponseEntity<Post> updatePostMoradia(@RequestBody Post obj, @PathVariable Long idUser, @PathVariable Long idMoradia) {
        PostMoradia postMoradia = postMoradiaService.findById(idMoradia);
        DetalhesMoradia detalhesMoradia = detalhesMoradiaService.findById(obj.getPostMoradia().getDetalhes().getId());

        Usuario user = userService.findById(idUser);
        Fotos fotos = new Fotos();


        System.out.println("updatePostMoradia obj fotos: " + obj.getPostMoradia().getFotos().toString());
        System.out.println("updatePostMoradia postMoradia fotos: " + postMoradia.getFotos().toString());

        Post post = obj;
        post.getPostMoradia().setFotos(obj.getPostMoradia().getFotos());
        if (user != null && postMoradia != null && detalhesMoradia != null) {
            post.setUsuario(user);
            post.getPostMoradia().setId(idMoradia);
           // post = service.insert(obj);




            service.insert(obj);
          //  for(int i = 0; i< post.getPostMoradia().getFotos().size(); i++){
           //     fotos = post.getPostMoradia().getFotos().get(i);
           //     fotos.setPostMoradia(postMoradia);

           //     fotosService.insert(fotos);
          //  }
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).body(post);
        }
      //  service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(post);    //created(espera um obj uri) para voltar o voltar o padrao http certo
        // return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PostMapping(value = "edit/{idUser}")
    public ResponseEntity<Post> updatePost(@RequestBody Post obj, @PathVariable Long idUser) {

        Usuario user = userService.findById(idUser);
        Post post = obj;

        //post.setDataPost(null);
        //post.setDataPost(new Date());
        if (user != null) {
            post.setUsuario(user);
            post = service.insert(post);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).body(post);
        }


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(post);    //created(espera um obj uri) para voltar o voltar o padrao http certo
        // return new ResponseEntity<>(post, HttpStatus.CREATED);
    }


}
