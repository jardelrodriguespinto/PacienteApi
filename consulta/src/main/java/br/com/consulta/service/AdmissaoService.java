package br.com.consulta.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.consulta.model.Admissao;
import br.com.consulta.model.Medico;
import br.com.consulta.repository.AdmissaoRepository;

@Service
public class AdmissaoService {
	
private AdmissaoRepository admissaoRepository;
	
	@Autowired
	protected AdmissaoService(AdmissaoRepository admissaoRepository) {
		this.admissaoRepository = admissaoRepository;
	}
	
	
	public List<Admissao> getTodasAdmissoes() {
		return admissaoRepository.findAll();
	}
	
	public Admissao getAdmissaoById(Long id) {
		
		Optional<Admissao> medico = admissaoRepository.findById(id);
		
		return medico.orElse(null);
		
	}
	
	public List<Admissao> getAdmissoesPorPeriodo(Date dataInicial, Date dataFinal) {

		List<Admissao> admissoes = admissaoRepository.buscaAdmissoesPorPeriodo(dataInicial, dataFinal);
		
		if (admissoes.isEmpty())
			return null;
		
		return admissoes;
	}
	
	public Admissao addNovaAdmissao(Admissao novaAdmissao) {
		
		Admissao admissao = admissaoRepository.save(novaAdmissao);
		
		return admissao;
	}
	
	public Admissao modificarAdmissao(Medico modificarPaciente, Long id) {
		
		Optional<Admissao> admissao = admissaoRepository.findById(id);
		
		if (admissao.isPresent()) 
			return admissao.orElse(null);
		
		return null;
	}
	
	public void deletarAdmissao(Long id) {
		
		Admissao admissao = admissaoRepository.findById(id).orElse(null);
		
		if (admissao != null)
			admissaoRepository.deleteById(id);
	}
}
