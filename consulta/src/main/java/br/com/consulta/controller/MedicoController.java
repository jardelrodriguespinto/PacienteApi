package br.com.consulta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.consulta.model.Medico;
import br.com.consulta.service.MedicoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api/v1/medico")
@RequiredArgsConstructor
public class MedicoController {
	
	private MedicoService medicoService;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Medico>> getTodosPacientes() {
		try {
			List<Medico> pacientes = medicoService.getTodosMedicos();
			
			return new ResponseEntity<List<Medico>>(pacientes, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medico> getPacienteById(@PathVariable Long id) {
		try {
			Medico paciente = medicoService.getMedicoById(id);
			
			if (paciente == null)
				return new ResponseEntity<Medico>(paciente, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<Medico>(paciente, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<?> addNovoPaciente(@RequestBody Medico novoMedico) {
		try {
			Medico medico = medicoService.addNovoMedico(novoMedico);
			
			if (medico == null)
				return new ResponseEntity<>("Medico já existe !", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<>(medico, HttpStatus.CREATED);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarPaciente(@RequestBody Medico modificarMedico, @PathVariable Long id) {
		try {
			Medico medico = medicoService.modificarMedico(modificarMedico, id);
			
			if (medico == null)
				return new ResponseEntity<>("Medico não encontrado !", HttpStatus.BAD_REQUEST);

			return new ResponseEntity<>(medico, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarPaciente(@PathVariable Long id) {
		try {
			String msg = medicoService.deletarMedico(id);
			
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
