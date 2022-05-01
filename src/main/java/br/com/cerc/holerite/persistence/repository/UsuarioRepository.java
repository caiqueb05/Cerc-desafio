package br.com.cerc.holerite.persistence.repository;

import br.com.cerc.holerite.persistence.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     *
     * @param email
     * @return
     */
    public Optional<Usuario> findByEmail(String email);

    public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

}

