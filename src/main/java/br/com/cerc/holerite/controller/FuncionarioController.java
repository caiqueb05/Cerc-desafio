package br.com.cerc.holerite.controller;

import javax.validation.Valid;

import br.com.cerc.holerite.persistence.repository.FuncionarioRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cerc.holerite.persistence.dto.FuncionarioDTO;
import br.com.cerc.holerite.persistence.model.Funcionario;
import br.com.cerc.holerite.service.FuncionarioService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/funcionario")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class FuncionarioController {
	private final FuncionarioService funcionarioService;
	private final FuncionarioRepository funcionarioRepository;

	public FuncionarioController(FuncionarioService funcionarioService, FuncionarioRepository funcionarioRepository) {
		this.funcionarioService = funcionarioService;
		this.funcionarioRepository = funcionarioRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return new ResponseEntity<>(funcionarioService.findById(id), HttpStatus.OK);	
	}
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> getAll(){
		if(funcionarioRepository.findAll().isEmpty()) {
			return ResponseEntity.status(204).build();
		}
		else {
			return ResponseEntity.status(200).body(funcionarioRepository.findAll());
		}
	}
	
	/*@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid FuncionarioDTO dto) {
		System.out.println(dto);

		return new ResponseEntity<>(funcionarioService.save(dto), HttpStatus.CREATED);
	}*/

	@PostMapping
	public ResponseEntity<Funcionario> salvar(@Valid @RequestBody Funcionario novoFuncionario) {
		return ResponseEntity.status(201).body(funcionarioRepository.save(novoFuncionario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		funcionarioService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/*@PutMapping("/{id}")
	public ResponseEntity<?> replace(@PathVariable long id, @RequestBody @Valid FuncionarioDTO dto) {
		funcionarioService.replace(dto, id);
		return ResponseEntity.status(201).body(funcionarioService.save(dto));
	}*/

	@PutMapping("/{id_funcionario}")
	public ResponseEntity<Funcionario> atualizar(@PathVariable(value = "id_funcionario") Long idFuncionario, @Valid @RequestBody Funcionario novoUsuario) {
			return funcionarioService.atualizarUsuario(idFuncionario, novoUsuario).map(resposta -> ResponseEntity.status(201).body(resposta))
					.orElseThrow(() -> {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
								"Necessario que passe um idFuncionario valido para alterar!.");
					});
	}

	/*@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> atualizar(@Valid @RequestBody FuncionarioDTO dto, @PathVariable long id) {
		return ResponseEntity.status(201).body(funcionarioService.save(dto));
	}*/
}
