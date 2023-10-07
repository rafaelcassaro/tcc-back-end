package trabalho.conclusao.curso.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import trabalho.conclusao.curso.tcc.data.DetalhesUsuarioData;
import trabalho.conclusao.curso.tcc.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
    //public Optional<Usuario> findByEmail2(String email);


}
