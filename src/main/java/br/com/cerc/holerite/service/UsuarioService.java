package br.com.cerc.holerite.service;

import br.com.cerc.holerite.persistence.dto.CredenciaisDTO;
import br.com.cerc.holerite.persistence.dto.UsuarioLoginDTO;
import br.com.cerc.holerite.persistence.model.Usuario;
import br.com.cerc.holerite.persistence.repository.UsuarioRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioService {

    private @Autowired
    UsuarioRepository repositorio;

    private static String encriptadorDeSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);

    }

    public Optional<Object> cadastrarUsuario(Usuario usuarioParaCadastrar) {
        return repositorio.findByEmail(usuarioParaCadastrar.getEmail()).map(usuarioExistente -> {
            return Optional.empty();
        }).orElseGet(() -> {
            usuarioParaCadastrar.setSenha(encriptadorDeSenha(usuarioParaCadastrar.getSenha()));
            return Optional.ofNullable(repositorio.save(usuarioParaCadastrar));
        });

    }

    public Optional<Usuario> atualizarUsuario(Usuario usuarioParaAtualizar) {
        return repositorio.findById(usuarioParaAtualizar.getIdUsuario()).map(resp -> {
            resp.setNome(usuarioParaAtualizar.getNome());
            resp.setSenha(encriptadorDeSenha(usuarioParaAtualizar.getSenha()));
            return Optional.ofNullable(repositorio.save(resp));
        }).orElseGet(() -> {
            return Optional.empty();
        });

    }

    private static String gerarToken(String email, String senha) {
        String estrutura = email + ":" + senha;
        byte[] estruturaBase64 = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(estruturaBase64);

    }

    public ResponseEntity<CredenciaisDTO> pegarCredenciais(UsuarioLoginDTO usuarioParaAutenticar) {
        return repositorio.findByEmail(usuarioParaAutenticar.getEmail()).map(resp -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(usuarioParaAutenticar.getSenha(), resp.getSenha())) {

                CredenciaisDTO objetoCredenciaisDTO = new CredenciaisDTO();

                objetoCredenciaisDTO.setToken(gerarToken(usuarioParaAutenticar.getEmail(), usuarioParaAutenticar.getSenha()));
                objetoCredenciaisDTO.setIdUsuario(resp.getIdUsuario());
                objetoCredenciaisDTO.setNome(resp.getNome());
                objetoCredenciaisDTO.setEmail(resp.getEmail());
                objetoCredenciaisDTO.setSenha(resp.getSenha());
                objetoCredenciaisDTO.setCargo(resp.getCargo());
                objetoCredenciaisDTO.setLinkFoto(resp.getLinkFoto());

                return ResponseEntity.status(201).body(objetoCredenciaisDTO); // Usuario Credenciado
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Incorreta!"); // Senha incorreta
            }
        }).orElseGet(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não existe!"); // Email não existe
        });

    }

}
