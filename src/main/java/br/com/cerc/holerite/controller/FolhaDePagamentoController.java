package br.com.cerc.holerite.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cerc.holerite.persistence.dto.FolhaDePagamentoDTO;
import br.com.cerc.holerite.service.FolhaDePagamentoService;

@RestController
@RequestMapping("/api/v1/folha")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class FolhaDePagamentoController {

	/*@Autowired
	private FolhaDePagamentoDTORepository repositorio;*/

	private final FolhaDePagamentoService folhaDePagamentoService;

	public FolhaDePagamentoController(FolhaDePagamentoService folhaDePagamentoService) {
		this.folhaDePagamentoService = folhaDePagamentoService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return new ResponseEntity<>(folhaDePagamentoService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping("/todas")
	public ResponseEntity<?> listAll(Pageable pageable) {
		return new ResponseEntity<>(folhaDePagamentoService.listAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> listAllByFunc(@RequestParam(value="funcId", defaultValue="") String id) {
		return new ResponseEntity<>(folhaDePagamentoService.listAllByFunc(Long.parseLong(id)), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid FolhaDePagamentoDTO dto){
		return new ResponseEntity<>(folhaDePagamentoService.save(dto), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		folhaDePagamentoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> replace(@RequestBody @Valid FolhaDePagamentoDTO dto, @PathVariable long id) {
		folhaDePagamentoService.replace(dto, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*@PutMapping("/novo/{id}")
	public ResponseEntity<FolhaDePagamentoDTO> put(@RequestBody @Valid FolhaDePagamentoDTO folhaAtualizada) {
		return  ResponseEntity.status(201).body(repositorio.save(folhaAtualizada));
	}*/
}
