package trabalho.conclusao.curso.tcc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trabalho.conclusao.curso.tcc.entities.Fotos;
import trabalho.conclusao.curso.tcc.entities.PostMoradia;
import trabalho.conclusao.curso.tcc.services.FotosService;
import trabalho.conclusao.curso.tcc.services.PostMoradiaService;
import trabalho.conclusao.curso.tcc.services.PostService;

import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private FotosService fotosService;
    @Autowired
    private PostMoradiaService postMoradiaService;
    @Autowired
    private PostService postService;

   /* @PostMapping("/cadastro")
    public String insert (@RequestParam("file") MultipartFile imagem){

        try{
            if (imagem.isEmpty()){
                return "Arquvio vazio ou nulo";
            }

            String imagesPaste = "C:\\Users\\rafae\\eclipse-workspace\\tcc- not\\tcc\\src\\main\\resources\\static\\images\\image-moradias\\";
            File dir = new File(imagesPaste);
            if (!dir.exists()){
                dir.mkdirs();
            }

            File serverFile = new File(imagesPaste + imagem.getOriginalFilename());

            try (FileOutputStream stream = new FileOutputStream(serverFile)){
                stream.write(imagem.getBytes());

            }
            return "Arquivo enviado";

        }catch (Exception e){
            return "Erro ao enviar o arquivo: " + e.getMessage();
        }







       /* Fotos obj = new Fotos();
        if(UploadImage.makeUploadImagem(imagem)){
            obj.setCaminhoImagem(imagem.getOriginalFilename());
        }

        obj = service.addImagem(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);    //created(espera um obj uri) para voltar o voltar o padrao http certo
    }*/



    @GetMapping(value = "/{imageName:.+}", produces = "image/jpeg")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            // Caminho para o diretório onde as imagens estão armazenadas
            String imageDirectory = "src\\main\\resources\\static\\images\\image-moradias\\";

            // Construa o caminho completo para a imagem
            String imagePath = imageDirectory + imageName;

            // Crie um recurso a partir do caminho da imagem
            Resource resource = new UrlResource("file:" + imagePath);

            // Verifique se o arquivo existe
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok(resource);
            } else {
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

            File serverFile = new File(imagesPaste + imagem.getOriginalFilename());

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


            fotosService.insert(fotos);
            return ResponseEntity.ok().body(fotos);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(fotos);
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Fotos> insertSemPostMoradia (@RequestParam("file") MultipartFile imagem){
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

            File serverFile = new File(imagesPaste + imagem.getOriginalFilename());

            try (FileOutputStream stream = new FileOutputStream(serverFile)){
                stream.write(imagem.getBytes());

            }
            //PostMoradia postMoradia = postMoradiaService.findById(id);

            fotos.setCaminhoImagem(serverFile.toString());
            fotos.setNomeFoto(serverFile.getName());
           // fotos.setPostMoradia(postMoradia);



            //fotos.setCaminhoImagem(serverFile.get);
            System.out.println("==================================================================================================");
            System.out.println(serverFile.getCanonicalPath());
            // System.out.println(serverFile.getName());*
            //  System.out.println(serverFile.toPath());*
            System.out.println(serverFile.toURI());
            System.out.println(serverFile.toString());
            System.out.println(serverFile.getTotalSpace());


            fotosService.insert(fotos);
            return ResponseEntity.ok().body(fotos);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(fotos);
        }
    }


   /* @GetMapping
    public ResponseEntity<List<Fotos>> findAll(){
        List<Fotos> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Fotos> findById(@PathVariable Long id){
        Fotos obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }*/


    @DeleteMapping(value = "/{id}")  //anotacao do spring boot para delecao
    public ResponseEntity<Void> delete(@PathVariable Long id){
        fotosService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
