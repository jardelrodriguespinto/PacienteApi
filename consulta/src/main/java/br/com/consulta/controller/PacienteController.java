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

import br.com.consulta.model.Paciente;
import br.com.consulta.service.PacienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api/v1/paciente")
@RequiredArgsConstructor
public class PacienteController {
	
	private PacienteService pacienteService;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Paciente>> getTodosPacientes() {
		try {
			List<Paciente> pacientes = pacienteService.getTodosPacientes();
			
			return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
		try {
			Paciente paciente = pacienteService.getPacienteById(id);
			
			if (paciente == null)
				return new ResponseEntity<Paciente>(paciente, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/{nome}/{sobrenome}")
	public ResponseEntity<Paciente> getPacientePorNomeCompleto(@PathVariable("nome") String nome, @PathVariable("sobrenome") String sobrenome) {
		try {
			Paciente paciente = pacienteService.getPacientePorNomeCompleto(nome, sobrenome);
			
			if (paciente == null)
				return new ResponseEntity<Paciente>(paciente, HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<?> addNovoPaciente(@RequestBody Paciente novoPaciente) {
		try {
			Paciente paciente = pacienteService.addNovoPaciente(novoPaciente);
			
			if (paciente == null)
				return new ResponseEntity<>("Paciente já existe !", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<>(paciente, HttpStatus.CREATED);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarPaciente(@RequestBody Paciente modificarPaciente, @PathVariable Long id) {
		try {
			Paciente paciente = pacienteService.modificarPaciente(modificarPaciente, id);
			
			if (paciente == null)
				return new ResponseEntity<>("Paciente não encontrado !", HttpStatus.BAD_REQUEST);

			return new ResponseEntity<>(paciente, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarPaciente(@PathVariable Long id) {
		try {
			String msg = pacienteService.deletarPaciente(id);
			
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		catch(Exception exc) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
