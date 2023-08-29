package trabalho.conclusao.curso.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.Planos;
import trabalho.conclusao.curso.tcc.entities.Post;
import trabalho.conclusao.curso.tcc.repositories.PlanosRepository;
import trabalho.conclusao.curso.tcc.repositories.PostRepository;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PlanosService {

    @Autowired
    private PlanosRepository repository;

    public List<Planos> findAll(){
        return repository.findAll();
    }

    public Planos findById(Long id) {
        Optional<Planos> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
