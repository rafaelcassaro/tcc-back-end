package trabalho.conclusao.curso.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.conclusao.curso.tcc.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
