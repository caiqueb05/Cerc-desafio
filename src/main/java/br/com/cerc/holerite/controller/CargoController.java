package br.com.cerc.holerite.controller;

import br.com.cerc.holerite.persistence.model.Cargo;
import br.com.cerc.holerite.persistence.repository.CargoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cerc.holerite.service.CargoService;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cargo")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CargoController {
	private final CargoService cargoService;
	private final CargoRepository cargoRepository;

	public CargoController(CargoService cargoService, CargoRepository cargoRepository) {
		this.cargoService = cargoService;
		this.cargoRepository = cargoRepository;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return new ResponseEntity<>(cargoService.findById(id), HttpStatus.OK);
	}
	
	/*@GetMapping
	public ResponseEntity<?> listAll(Pageable page) {
		return new ResponseEntity<>(cargoService.listAll(page), HttpStatus.OK);
	}*/

	@GetMapping("/todos")
	public ResponseEntity<List<Cargo>> GetAll() {
		if (cargoRepository.findAll().isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(cargoRepository.findAll());
		}
	}

	@PostMapping()
	public ResponseEntity<Object> salvarCargo(@Valid @RequestBody Cargo cargo) {
		return cargoService.cadastrarCargo(cargo).map(resp -> ResponseEntity.status(201).body(resp)).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cargo já existe, cadestre outro!");
		});
	}
}
