package trabalho.conclusao.curso.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.Post;
import trabalho.conclusao.curso.tcc.entities.PostMoradia;
import trabalho.conclusao.curso.tcc.repositories.PostMoradiaRepository;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
public class PostMoradiaService {

    @Autowired
    private PostMoradiaRepository rep;


    public PostMoradia findById(Long id){
        Optional<PostMoradia> obj = rep.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
