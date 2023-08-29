package trabalho.conclusao.curso.tcc.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.Usuario;
import trabalho.conclusao.curso.tcc.repositories.UsuarioRepository;
import trabalho.conclusao.curso.tcc.services.exceptions.DatabaseException;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Usuario insert(Usuario obj) {
        return repository.save(obj);
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

    public Usuario update(Long id, Usuario obj) {
        try {
            Usuario entity = repository.getReferenceById(id);
            updateData(entity, obj);    //oq vai ser dado upadte pelo metodo
            return repository.save(entity);
        }catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }


    private void updateData(Usuario entity, Usuario obj) {
        entity.setNome(obj.getNome());
        entity.setEmail(obj.getEmail());
        entity.setCelular(obj.getCelular());
        entity.setSenha(obj.getSenha());
        entity.setLink1(obj.getLink1());
        entity.setLink2(obj.getLink2());
        entity.setLink3(obj.getLink3());
    }


}
