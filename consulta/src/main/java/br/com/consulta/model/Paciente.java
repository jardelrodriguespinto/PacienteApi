package br.com.consulta.model;


import java.util.Date;

import br.com.consulta.Utils.Estado;
import br.com.consulta.Utils.Genero;
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
@Table(name = "pacientes")
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paciente_id")
	Long id;
	String nome;
	String sobrenome;
	Genero genero;
	Date dataDeNascimento;
	String cidade;
	Estado estado;
	double altura;
	double peso;
	boolean teveAlta;
	@Column(name = "data_ultima_alta")
	Date dataUltimaAlta;
}
