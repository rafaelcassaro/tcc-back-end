package trabalho.conclusao.curso.tcc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import trabalho.conclusao.curso.tcc.entities.Feedback;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.services.FeedbackService;
import trabalho.conclusao.curso.tcc.services.PostService;
import trabalho.conclusao.curso.tcc.services.UsuarioService;

import java.net.URI;

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/{id}")
    public ResponseEntity<Feedback> insertFeedback(@RequestBody Feedback feedback, @PathVariable Long id){
        Usuario user = usuarioService.findById(id);
        feedback.setUsuario(user);

        feedbackService.save(feedback);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(feedback).toUri();
        return ResponseEntity.created(uri).body(feedback);
    }


}
