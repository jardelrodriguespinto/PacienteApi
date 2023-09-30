package br.com.consulta.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "admissoes")
public class Admissao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column(name = "paciente_id")
	Long pacienteId;
	@Column(name = "data_de_admissao")
	Date dataDeAdmissao;
	@Column(name = "data_da_alta")
	Date dataDaAlta;
	@Column(length = 600)
	String diagnostico;
	Long medicoId;
}
