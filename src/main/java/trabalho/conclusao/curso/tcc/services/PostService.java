package trabalho.conclusao.curso.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.Post;
import trabalho.conclusao.curso.tcc.repositories.PostRepository;
import trabalho.conclusao.curso.tcc.services.exceptions.DatabaseException;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Post findById(Long id) {
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Post> findPostByUserId(Long id) {
        return repository.findAllPostByusuario_id(id);
    }

    public List<Post> findAllPostBycidade(String cidade){
        return repository.findAllPostBycidade(cidade);
    }

    public Post insert(Post obj){
        obj.setDataPost(new Date());
        obj.setExcluido(false);
        return repository.save(obj);
    }

    public Post editDenuncia(Post obj){
        return repository.save(obj);
    }

    public Post delete (Post obj) {
        try {
            return repository.save(obj);
        }catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(obj);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
