package trabalho.conclusao.curso.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.conclusao.curso.tcc.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
