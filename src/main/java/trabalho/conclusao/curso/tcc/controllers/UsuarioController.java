package trabalho.conclusao.curso.tcc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.services.UsuarioService;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "update/data/{id}")
    public ResponseEntity<Usuario> updateSemSenha(@PathVariable Long id, @RequestBody Usuario obj){
        //String encryptedPassword = new BCryptPasswordEncoder().encode(obj.getSenha());
        //obj.setSenha(encryptedPassword);
        obj = service.updateSemSenha(id, obj);

        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "update/password/{id}")
    public ResponseEntity<Usuario> updateSomenteSenha(@PathVariable Long id, @RequestBody Usuario user){
        //String encryptedPassword = new BCryptPasswordEncoder().encode(obj.getSenha());
        //obj.setSenha(encryptedPassword);
        //obj = service.updateSemSenha(id, obj);
        String senha = user.getSenha();
        service.updateSenha(id,senha);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")  //anotacao do spring boot para delecao
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "fotoperfil/{imageName:.+}", produces = "image/jpeg")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            // Caminho para o diretório onde as imagens estão armazenadas
            String imageDirectory = "src\\main\\resources\\static\\images\\image-perfil\\";

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


    @PostMapping("/register/fotoperfil/{id}")
    public ResponseEntity<Usuario> registrarFotoPerfil(@RequestParam("file") MultipartFile imagem, @PathVariable Long id){
        Usuario user = service.findById(id);
        try{

            if(service.findById(id) ==  null || user.getNomeFotoPerfil() != null || user.getCaminhoImagem() != null){
                return ResponseEntity.badRequest().body(user);
            }

            if (imagem.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            String imagesPaste = "src\\main\\resources\\static\\images\\image-perfil\\";
            File dir = new File(imagesPaste);
            if (!dir.exists()){
                dir.mkdirs();
            }


            Date dataHora = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
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
            user = service.findById(id);
            user.setNomeFotoPerfil(serverFile.getName());
            user.setCaminhoImagem(serverFile.toString());

            service.update(id,user);
            return ResponseEntity.ok().body(user);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(user);
        }
    }


    @PostMapping("/edit/fotoperfil/{id}")
    public ResponseEntity<Usuario> editarFotoPerfil(@RequestParam("file") MultipartFile imagem, @PathVariable Long id){
        Usuario user = service.findById(id);
        try{

            if (imagem.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            if(service.findById(id) !=  null || user.getNomeFotoPerfil() != null || user.getCaminhoImagem() != null){

                String imagesPaste = "src\\main\\resources\\static\\images\\image-perfil\\";
                File dir = new File(imagesPaste);
                if (!dir.exists()){
                    dir.mkdirs();
                }

                Date dataHora = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
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
                user = service.findById(id);
                user.setNomeFotoPerfil(serverFile.getName());
                user.setCaminhoImagem(serverFile.toString());

                service.update(id,user);
                return ResponseEntity.ok().body(user);



            }

            return ResponseEntity.badRequest().body(user);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(user);
        }
    }


}
