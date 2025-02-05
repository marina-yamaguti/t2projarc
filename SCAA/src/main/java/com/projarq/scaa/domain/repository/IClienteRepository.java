package com.projarq.scaa.domain.repository;

import com.projarq.scaa.domain.model.Cliente;

import java.util.List;

public interface IClienteRepository {
    Cliente create(Long codigo, String nome, String email);
    List<Cliente> getAll();
    Cliente getById(Long codigo);
    void update(Cliente cliente);
    void delete(Long codigo);
}