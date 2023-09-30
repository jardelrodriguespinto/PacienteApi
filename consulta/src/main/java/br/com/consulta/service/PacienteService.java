package br.com.consulta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.consulta.model.Paciente;
import br.com.consulta.repository.PacienteRepository;

@Service
public class PacienteService {
	
	private PacienteRepository pacienteRepository;
	
	@Autowired
	protected PacienteService(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}
	
	public List<Paciente> getTodosPacientes() {
		return pacienteRepository.findAll();
	}
	
	public Paciente getPacienteById(Long id) {
		
		Optional<Paciente> paciente = pacienteRepository.findById(id);
		
		return paciente.orElse(null);
	}

	public Paciente getPacientePorNomeCompleto(String nome, String sobrenome) {
		
		Paciente paciente = pacienteRepository.getPacientePorNomeCompleto(nome, sobrenome);
		
		if (paciente != null)
			return paciente;
		
		return null;
	}
	
	public Paciente addNovoPaciente(Paciente novoPaciente) {
		
		boolean usuarioExiste = pacienteRepository.pacienteExiste(novoPaciente.getNome(), novoPaciente.getSobrenome());
		
		Paciente paciente = pacienteRepository.save(novoPaciente);
		
		if (! usuarioExiste)
			return null;
		
		return paciente;
	}
	
	public Paciente modificarPaciente(Paciente modificarPaciente, Long id) {
		
		Paciente paciente = pacienteRepository.findById(id).orElse(null);
		
		if (paciente != null) {
			paciente.setNome(modificarPaciente.getNome());
			paciente.setSobrenome(modificarPaciente.getSobrenome());
			paciente.setDataDeNascimento(modificarPaciente.getDataDeNascimento());
			paciente.setCidade(modificarPaciente.getCidade());
			paciente.setEstado(modificarPaciente.getEstado());
			paciente.setAltura(modificarPaciente.getAltura());
			paciente.setPeso(modificarPaciente.getPeso());
			paciente.setTeveAlta(modificarPaciente.isTeveAlta());
			paciente.setDataUltimaAlta(modificarPaciente.getDataUltimaAlta());
		}
		
		pacienteRepository.save(paciente);
		
		return modificarPaciente;
	}
	
	public String deletarPaciente(Long id) {
		
		Paciente paciente = pacienteRepository.findById(id).orElse(null);
		
		if (paciente != null) {
			pacienteRepository.deleteById(id);
			return "Paciente deletado com sucesso !";
		}

		return "Paciente não encontrado !";
	}
}
