package br.com.cerc.holerite.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cerc.holerite.persistence.dto.FuncionarioDTO;
import br.com.cerc.holerite.persistence.model.Cargo;
import br.com.cerc.holerite.persistence.model.Funcionario;
import br.com.cerc.holerite.persistence.repository.CargoRepository;
import br.com.cerc.holerite.persistence.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    /*private final FuncionarioRepository funcionarioRepository;*/
    /*private final CargoRepository cargoRepository;*/

    @Autowired FuncionarioRepository funcionarioRepository;
    @Autowired CargoRepository cargoRepository;



    /*public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
    }*/


    public Funcionario findById(long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK));
    }

    public Page<Funcionario> listAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    /*public Funcionario save(FuncionarioDTO dto) {
        System.out.println(dto);
        Funcionario funcionarioDB = funcionarioRepository.findByCpf(dto.getCpf());
        Optional<Cargo> cargo = cargoRepository.findById(dto.getIdCargo());
        if (funcionarioDB != null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Funcionario funcionario = new Funcionario(dto.getNome(), dto.getCpf(), cargo.get(), dto.getEmail());
        return funcionarioRepository.save(funcionario);*/

        /*Funcionario funcionario = new Funcionario(dto.getNome(), dto.getCpf(), cargo.get(), dto.getEmail());
        System.out.println(funcionario);
        return funcionarioRepository.save(funcionario);*/
    /*}*/



    public void delete(long id) {
        findById(id);
        funcionarioRepository.deleteById(id);
    }

    public Optional<Funcionario> atualizarUsuario(Long idFuncionario, Funcionario funcionarioParaAtualizar) {
        return funcionarioRepository.findById(idFuncionario).map(resp -> {
            Funcionario funcionarioDB = funcionarioRepository.findByCpf(funcionarioParaAtualizar.getCpf());
            Optional<Cargo> cargo = cargoRepository.findByIdCargo(funcionarioParaAtualizar.getCargo().getIdCargo());

            if (funcionarioDB != null /*|| !cargo.isPresent()*/) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            resp.setNome(funcionarioParaAtualizar.getNome());
            resp.setCpf(funcionarioParaAtualizar.getCpf());
            resp.setCargo(cargo.get());
            return Optional.ofNullable(funcionarioRepository.save(resp));
        }).orElseGet(() -> {
            return Optional.empty();
        });

    }


}
