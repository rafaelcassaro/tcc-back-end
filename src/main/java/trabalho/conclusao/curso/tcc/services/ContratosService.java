package trabalho.conclusao.curso.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.Contratos;
import trabalho.conclusao.curso.tcc.entities.Feedback;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.repositories.ContratosRepository;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ContratosService {

    @Autowired
    private ContratosRepository repository;

    public List<Contratos> findAll(){
        return repository.findAll();
    }

    public Contratos findById(Long id) {
        Optional<Contratos> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Contratos insert(Contratos obj) {
        return repository.save(obj);
    }
}
