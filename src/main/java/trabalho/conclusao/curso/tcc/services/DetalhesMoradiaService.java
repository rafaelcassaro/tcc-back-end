package trabalho.conclusao.curso.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.DetalhesMoradia;
import trabalho.conclusao.curso.tcc.repositories.DetalhesMoradiaRepository;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class DetalhesMoradiaService {
    @Autowired
    private DetalhesMoradiaRepository rep;

    public DetalhesMoradia findById(Long id){
        Optional<DetalhesMoradia> detalhesMoradia = rep.findById(id);
        return detalhesMoradia.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
