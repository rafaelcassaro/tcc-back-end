package trabalho.conclusao.curso.tcc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import trabalho.conclusao.curso.tcc.entities.Contratos;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.services.ContratosService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/contratos")
public class ContratosController {

    @Autowired
    private ContratosService service;

    @GetMapping
    public ResponseEntity<List<Contratos>> findAll(){
        List<Contratos> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Contratos> findById(@PathVariable Long id){
        Contratos obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Contratos> insert (@RequestBody Contratos obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);	//created(espera um obj uri) para voltar o voltar o padrao http certo
    }


}
