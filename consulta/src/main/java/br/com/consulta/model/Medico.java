package br.com.consulta.model;

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
@Table(name = "medicos")
public class Medico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String nome;
	String sobrenome;
	String especializacao;
}
