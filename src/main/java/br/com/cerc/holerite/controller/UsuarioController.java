package br.com.cerc.holerite.controller;

import br.com.cerc.holerite.persistence.dto.CredenciaisDTO;
import br.com.cerc.holerite.persistence.dto.UsuarioLoginDTO;
import br.com.cerc.holerite.persistence.model.Usuario;
import br.com.cerc.holerite.persistence.repository.UsuarioRepository;
import br.com.cerc.holerite.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
/*@Api(tags = "Controlador de Usuario", description = "Utilitario de Usuarios")*/
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsuarioController {

    private @Autowired
    UsuarioRepository repositorio;
    private @Autowired
    UsuarioService servicos;

    @GetMapping()
    public ResponseEntity<List<Usuario>> pegarTodes() {
        List<Usuario> objetoLista = repositorio.findAll();

        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

    @GetMapping("/nome/{nome_usuario}")
    public ResponseEntity<List<Usuario>> buscarPorNomeI(@PathVariable(value = "nome_usuario") String nome) {
        List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<Usuario>> buscarPorNomeII(@RequestParam(defaultValue = "") String nome) {
        List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

        if (objetoLista.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(objetoLista);
        }
    }

    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
        return repositorio.findById(idUsuario).map(resp -> ResponseEntity.status(200).body(resp))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "ID inexistente, passe um ID valido para pesquisa!.");
                });

    }

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario novoUsuario) {
        return servicos.cadastrarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Email existente, cadastre outro email!.");
                });

    }

    @PutMapping("/credenciais")
    public ResponseEntity<CredenciaisDTO> credenciais(@Valid @RequestBody UsuarioLoginDTO usuarioParaAutenticar) {
        return servicos.pegarCredenciais(usuarioParaAutenticar);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario novoUsuario) {
        return servicos.atualizarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Necessario que passe um idUsuario valido para alterar!.");
                });

    }

    @DeleteMapping("/deletar/{id_usuario}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id_usuario") Long idUsuario) {
        return repositorio.findById(idUsuario).map(resp -> {
            repositorio.deleteById(idUsuario);
            return ResponseEntity.status(200).build();
        }).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ID inexistente, passe um ID valido para deletar!.");
        });
    }

}
