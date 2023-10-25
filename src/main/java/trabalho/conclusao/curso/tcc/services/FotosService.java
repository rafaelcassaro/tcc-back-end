package trabalho.conclusao.curso.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.Fotos;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.repositories.FotosRepository;
import trabalho.conclusao.curso.tcc.services.exceptions.DatabaseException;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class FotosService {

    @Autowired
    private FotosRepository repository;

    public Fotos insert(Fotos obj) {
        try{
            return repository.save(obj);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Fotos addImagem(Fotos obj) {
        try{
            return repository.save(obj);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }


    public List<Fotos> findAll(){
        return repository.findAll();
    }

    public Fotos findById(Long id) {
        Optional<Fotos> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }



    public void delete (Long id) {
        try {
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }



}
