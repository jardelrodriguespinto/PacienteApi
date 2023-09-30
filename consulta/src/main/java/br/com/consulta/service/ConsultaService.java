package br.com.consulta.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.consulta.model.Consulta;
import br.com.consulta.repository.ConsultaRepository;

@Service
public class ConsultaService {
	
private ConsultaRepository consultaRepository;
	
	@Autowired
	protected ConsultaService(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}
	
	public List<Consulta> getTodasConsultas() {
		return consultaRepository.findAll();
	}
	
	public Consulta getConsultaoById(Long id) {
		
		Optional<Consulta> consulta = consultaRepository.findById(id);
		
		return consulta.orElse(null);
	}
	
	public List<Consulta> getConsultasPorPeriodo(Date dataInicial, Date dataFinal) {
		
		/*Verificar pra se será necessário converter o formato da data*/
		
		List<Consulta> consultas = consultaRepository.buscaAdmissoesPorPeriodo(dataInicial, dataFinal);
		
		if (consultas.isEmpty())
			return null;
		
		return consultas;
	}
	
	public Consulta addNovaConsulta(Consulta novaConsulta) {
		
		Consulta consulta = consultaRepository.save(novaConsulta);
		
		return consulta;
	}
	
	public Consulta modificarConsulta(Consulta modificarConsulta, Long id) {
		
		Consulta consulta = consultaRepository.findById(id).orElse(null);
		
		if (consulta != null) {
			consulta.setMedicoId(modificarConsulta.getMedicoId());
			consulta.setPacienteId(modificarConsulta.getPacienteId());
			consulta.setDataDaAlta(modificarConsulta.getDataDaAlta());
			consulta.setDiagnostico(modificarConsulta.getDiagnostico());
			consulta.setDataDeAdmissao(modificarConsulta.getDataDeAdmissao());
		}
		
		consultaRepository.save(consulta);
		
		return consulta;
	}
	
	public String deletarConsulta(Long id) {
		
		Consulta consulta = consultaRepository.findById(id).orElse(null);
		
		if (consulta != null) {
			consultaRepository.deleteById(id);
			return "Consulta deletado com sucesso !";
		}
		
		return "Consulta não encontrado !";
		
	}
}
