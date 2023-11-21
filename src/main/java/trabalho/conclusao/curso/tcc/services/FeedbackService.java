package trabalho.conclusao.curso.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.Feedback;
import trabalho.conclusao.curso.tcc.entities.Planos;
import trabalho.conclusao.curso.tcc.repositories.FeedbackRepository;
import trabalho.conclusao.curso.tcc.repositories.PlanosRepository;
import trabalho.conclusao.curso.tcc.services.exceptions.DatabaseException;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    public List<Feedback> findAll(){
        return repository.findAll();
    }

    public Feedback findById(Long id) {
        Optional<Feedback> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Feedback save(Feedback feedback){
        try{
            return repository.save(feedback);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

}
