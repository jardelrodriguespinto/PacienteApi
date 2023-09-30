package br.com.consulta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.consulta.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	@Query(nativeQuery = true , value = "SELECT nome, sobrenome FROM pacientes WHERE nome = :nome AND sobrenome = :sobrenome")
	boolean pacienteExiste(@Param("nome") String nome , @Param("sobrenome") String sobrenome);
	
	@Query(nativeQuery = true , value = "SELECT * FROM pacientes WHERE nome = :nome AND sobrenome = :sobrenome")
	Paciente getPacientePorNomeCompleto(@Param("nome") String nome , @Param("sobrenome") String sobrenome);
}
