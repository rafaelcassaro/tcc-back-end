package trabalho.conclusao.curso.tcc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho.conclusao.curso.tcc.entities.Post;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.services.PostService;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    @Autowired
    private PostService service;

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
}
