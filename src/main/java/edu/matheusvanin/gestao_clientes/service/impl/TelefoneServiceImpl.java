package edu.matheusvanin.gestao_clientes.service.impl;

import edu.matheusvanin.gestao_clientes.domain.Telefone;
import edu.matheusvanin.gestao_clientes.dto.TelefoneDTO;
import edu.matheusvanin.gestao_clientes.mapper.GestaoClienteMapper;
import edu.matheusvanin.gestao_clientes.repository.TelefoneRepository;
import edu.matheusvanin.gestao_clientes.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static edu.matheusvanin.gestao_clientes.utils.Constantes.TELEFONE_INVALIDO;

@Service
@RequiredArgsConstructor
public class TelefoneServiceImpl implements TelefoneService {
    private final GestaoClienteMapper mapper;
    private final TelefoneRepository telefoneRepository;

    @Override
    public void insereTelefone(TelefoneDTO telefone, Integer clienteId) {
        telefone.setClienteId(clienteId);
        Telefone telefoneEntity = mapper.telefoneDtoToTelefone(telefone);
        telefoneRepository.save(telefoneEntity);
    }

    @Override
    public void insereListaTelefone(List<TelefoneDTO> telefoneDTOList, Integer clienteId) {
        telefoneDTOList.forEach(telefone -> telefone.setClienteId(clienteId));
        List<Telefone> telefoneList = mapper.telefoneDtoListToTelefoneList(telefoneDTOList);
        telefoneRepository.saveAll(telefoneList);
    }

    @Override
    public void validaTelefoneClienteOuException(TelefoneDTO telefoneDto, Integer clienteId) {
        List<Telefone> telefones = telefoneRepository.findByClienteId(clienteId);
        if (telefones.stream().noneMatch(telefone -> telefone.getNumero().equals(telefoneDto.getNumero()))) {
            throw new IllegalArgumentException(TELEFONE_INVALIDO);
        }
    }

    @Override
    public void validaTelefoneClienteOuException(List<TelefoneDTO> telefoneListDto, Integer clienteId) {
        List<Telefone> telefones = telefoneRepository.findByClienteId(clienteId);
        telefones.forEach(telefone -> {
            if (telefoneListDto.stream().noneMatch(telefoneDto -> telefoneDto.getUuid().equals(telefone.getUuid()))) {
                throw new IllegalArgumentException(TELEFONE_INVALIDO);
            }
        });
    }

    @Override
    public Telefone buscaTelefoneOuException(UUID uuid) {
        return telefoneRepository.findByUuid(uuid).orElseThrow(
                () -> new IllegalArgumentException(TELEFONE_INVALIDO));
    }

    @Override
    public Telefone atualizaTelefone(TelefoneDTO telefoneDTO) {
        Telefone telefone = buscaTelefoneOuException(telefoneDTO.getUuid());
        telefone.setDdd(telefoneDTO.getDdd());
        telefone.setNumero(telefoneDTO.getNumero());
        return telefoneRepository.save(telefone);
    }

    @Override
    public List<Telefone> atualizaTelefone(List<TelefoneDTO> telefones) {
        List<Telefone> telefoneList = new ArrayList<>();
        telefones.forEach(telefone -> telefoneList.add(atualizaTelefone(telefone)));
        return telefoneList;
    }

    @Override
    public void deletaTelefone(UUID uuid) {
        Telefone telefone = buscaTelefoneOuException(uuid);
        telefoneRepository.delete(telefone);
    }

    @Override
    public TelefoneDTO converteTelefoneEmDTO(Telefone telefone) {
        return mapper.telefoneToDto(telefone);
    }

}
