package trabalho.conclusao.curso.tcc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho.conclusao.curso.tcc.entities.Contratos;
import trabalho.conclusao.curso.tcc.entities.Planos;
import trabalho.conclusao.curso.tcc.services.PlanosService;

import java.util.List;

@RestController
@RequestMapping(value = "/planos")
public class PlanosController {

    @Autowired
    private PlanosService service;

    @GetMapping
    public ResponseEntity<List<Planos>> findAll(){
        List<Planos> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
