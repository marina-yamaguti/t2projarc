package com.projarq.scaa.domain.repository;


import com.projarq.scaa.domain.model.Assinatura;
import com.projarq.scaa.domain.model.Cliente;

import java.util.List;

public interface IAssinaturaRepository {
    Assinatura create(Assinatura assinatura, Cliente cliente);
    List<Assinatura> getAll();
    Assinatura getById(Long codigo);
    List<Assinatura> getByAplicativoId(Long aplicativoId);
    void update(Assinatura assinatura);
    void delete(Long codigo);
}