package trabalho.conclusao.curso.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trabalho.conclusao.curso.tcc.entities.Contratos;

public interface ContratosRepository extends JpaRepository<Contratos,Long> {



}
