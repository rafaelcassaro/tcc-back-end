package trabalho.conclusao.curso.tcc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import trabalho.conclusao.curso.tcc.entities.DetalhesMoradia;
import trabalho.conclusao.curso.tcc.entities.Post;
import trabalho.conclusao.curso.tcc.entities.PostMoradia;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.services.DetalhesMoradiaService;
import trabalho.conclusao.curso.tcc.services.PostMoradiaService;
import trabalho.conclusao.curso.tcc.services.PostService;
import trabalho.conclusao.curso.tcc.services.UsuarioService;

import java.net.URI;
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


    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        List<Post> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id){
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "userPosts/{id}")
    public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Long id){
        List<Post> obj = service.findPostByUserId(id);
        return ResponseEntity.ok().body(obj);
    }

    /*@PostMapping
    public ResponseEntity<Post> insert (@RequestBody Post obj){
        obj = service.insert(obj);
       // System.out.println(obj.getUsuario().getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);    //created(espera um obj uri) para voltar o voltar o padrao http certo
    }*/

    @PostMapping(value = "/{id}")
    public ResponseEntity<Post> insert(@RequestBody Post obj, @PathVariable Long id){

        Usuario user = userService.findById(id);
        //Post post = obj;
        if(user != null){
            obj.setUsuario(user);
        }
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);    //created(espera um obj uri) para voltar o voltar o padrao http certo
       // return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PostMapping(value = "edit/{idUser}/{idMoradia}")
    public ResponseEntity<Post> update(@RequestBody Post obj, @PathVariable Long idUser,@PathVariable Long idMoradia){
        //obj.setDataPost(null);
        Usuario user = userService.findById(idUser);
        PostMoradia postMoradia = postMoradiaService.findById(idMoradia);

        DetalhesMoradia detalhesMoradia = detalhesMoradiaService.findById(obj.getPostMoradia().getDetalhes().getId());
        Post post = obj;
        //post.setDataPost(null);
        //post.setDataPost(new Date());
        if(user != null && postMoradia!=null && detalhesMoradia!=null){
            post.setUsuario(user);
            post.getPostMoradia().setId(idMoradia);
            post = service.insert(post);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).body(post);
        }


        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(post);    //created(espera um obj uri) para voltar o voltar o padrao http certo
        // return new ResponseEntity<>(post, HttpStatus.CREATED);
    }


}
