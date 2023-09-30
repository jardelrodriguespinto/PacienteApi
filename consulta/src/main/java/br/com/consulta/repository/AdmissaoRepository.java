package br.com.consulta.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.consulta.model.Admissao;

@Repository
public interface AdmissaoRepository extends JpaRepository<Admissao, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM admissoes WHERE data_de_admissao BETWEEN :dataInicial AND :dataFinal ORDER BY id")
	List<Admissao> buscaAdmissoesPorPeriodo(@Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);
}
