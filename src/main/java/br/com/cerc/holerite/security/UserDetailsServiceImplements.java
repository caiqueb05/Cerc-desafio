package br.com.cerc.holerite.security;

import br.com.cerc.holerite.persistence.model.Usuario;
import br.com.cerc.holerite.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

    private @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> objetoOptional = repository.findByEmail(email);

        if (objetoOptional.isPresent()) {
            return new UserDetailsImplements(objetoOptional.get());
        } else {
            throw new UsernameNotFoundException(email + " Não existe!");
        }
    }
}
