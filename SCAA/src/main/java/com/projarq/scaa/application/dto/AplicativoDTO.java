package com.projarq.scaa.application.dto;


import com.projarq.scaa.domain.model.Assinatura;

import java.util.List;

public class AplicativoDTO {
    private Long codigo;
    private String nome;
    private Double custoMensal;
    private List<Assinatura> assinaturas;

    public AplicativoDTO(Long codigo, String nome, Double custoMensal, List<Assinatura> assinaturas) {
        this.codigo = codigo;
        this.nome = nome;
        this.custoMensal = custoMensal;
        this.assinaturas = assinaturas;
    }

    public Long getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public Double getCustoMensal() {
        return this.custoMensal;
    }
}
