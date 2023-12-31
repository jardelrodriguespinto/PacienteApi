package br.com.consulta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.consulta.model.Medico;
import br.com.consulta.repository.MedicoRepository;

@Service
public class MedicoService {
	
private MedicoRepository medicoRepository;
	
	@Autowired
	protected MedicoService(MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}
	
	public List<Medico> getTodosMedicos() {
		return medicoRepository.findAll();
	}
	
	public Medico getMedicoById(Long id) {
		
		Optional<Medico> medico = medicoRepository.findById(id);
		
		return medico.orElse(null);
	}
	
	public Medico addNovoMedico(Medico novoMedico) {
		
		boolean medicoExiste = medicoRepository.medicoExiste(novoMedico.getNome(), novoMedico.getSobrenome());
		
		Medico medico = medicoRepository.save(novoMedico);
		
		if (! medicoExiste)
			return null;
		
		return medico;
	}
	
	public Medico modificarMedico(Medico modificarMedico, Long id) {
		
		Medico medico = medicoRepository.findById(id).orElse(null);
		
		if (medico != null) {
			medico.setNome(modificarMedico.getNome());
			medico.setSobrenome(modificarMedico.getSobrenome());
			medico.setEspecializacao(modificarMedico.getEspecializacao());
		}
		
		medicoRepository.save(medico);
		
		return medico;
	}
	
	
	public String deletarMedico(Long id) {
		
		Medico medico = medicoRepository.findById(id).orElse(null);
		
		if (medico != null) {
			medicoRepository.deleteById(id);
			return "Médico deletado com sucesso !";
		}

		return "Médico não encontrado !";
	}
}
