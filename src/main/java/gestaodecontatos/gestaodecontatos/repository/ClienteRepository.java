package gestaodecontatos.gestaodecontatos.repository;

import gestaodecontatos.gestaodecontatos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE (:nome IS NOT NULL AND LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) OR (:cpf IS NOT NULL AND c.cpf LIKE CONCAT('%', :cpf, '%'))")
    List<Cliente> findByNomeOrCpf(@Param("nome") String nome, @Param("cpf") String cpf);

    boolean existsByCpf(String cpf);
}
