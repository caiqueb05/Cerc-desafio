package br.com.cerc.holerite.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cerc.holerite.persistence.model.Cargo;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{

    public Optional<Cargo> findByNome(String nome);

    Optional<Cargo> findByIdCargo(Long id);
}
