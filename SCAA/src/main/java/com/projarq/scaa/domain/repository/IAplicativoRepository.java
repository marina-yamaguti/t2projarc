package com.projarq.scaa.domain.repository;


import com.projarq.scaa.domain.model.Aplicativo;

import java.util.List;

public interface IAplicativoRepository {
    Aplicativo create(Long codigo, String nome, Double custoMensal);
    List<Aplicativo> getAll();
    Aplicativo getById(Long codigo);
    void update(Aplicativo aplicativo);
    void delete(Long codigo);
}
