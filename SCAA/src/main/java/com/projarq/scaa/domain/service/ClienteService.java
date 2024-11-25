package com.projarq.scaa.domain.service;


import com.projarq.scaa.application.dto.ClienteDTO;
import com.projarq.scaa.domain.model.Cliente;
import com.projarq.scaa.domain.repository.IAplicativoRepository;
import com.projarq.scaa.domain.repository.IAssinaturaRepository;
import com.projarq.scaa.domain.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;
    private final IAssinaturaRepository assinaturaRepository;
    private final IAplicativoRepository aplicativoRepository;

    @Autowired
    public ClienteService(IClienteRepository clienteRepository, IAssinaturaRepository assinaturaRepository, IAplicativoRepository aplicativoRepository) {
        this.clienteRepository = clienteRepository;
        this.assinaturaRepository = assinaturaRepository;
        this.aplicativoRepository = aplicativoRepository;
    }

    // Cria um cliente
    public Cliente createCliente(Long codigo, String nome, String email) {
        return clienteRepository.create(codigo, nome, email);
    }

    // Recupera todos os clientes
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.getAll();
        return clientes.stream().map(this::toDTO).toList();
    }

    // Recupera um cliente por id
    public Cliente getById(Long codigo) {
        return clienteRepository.getById(codigo);
    }

    private ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getCodigo(), cliente.getNome(), cliente.getEmail(), cliente.getAssinaturas());
    }
}

