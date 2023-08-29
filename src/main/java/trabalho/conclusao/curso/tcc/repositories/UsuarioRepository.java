package trabalho.conclusao.curso.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trabalho.conclusao.curso.tcc.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
