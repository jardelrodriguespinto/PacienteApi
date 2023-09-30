package br.com.consulta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.consulta.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
	@Query(nativeQuery = true , value = "Select nome, sobrenome from medicos WHERE nome = :nome AND sobrenome = :sobrenome")
	boolean medicoExiste(@Param("nome") String nome , @Param("sobrenome") String sobrenome);
}
