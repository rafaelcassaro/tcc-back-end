package trabalho.conclusao.curso.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trabalho.conclusao.curso.tcc.entities.Post;
import trabalho.conclusao.curso.tcc.repositories.PostRepository;
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
        //List<Post> obj = repository.findPostByUserId(id);
        return repository.findAllPostByusuario_id(id);
        //return null;
    }

    public Post insert(Post obj){
        obj.setDataPost(new Date());
        return repository.save(obj);
    }
}
