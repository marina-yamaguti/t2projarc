package com.projarq.scaa.domain.repository;

import com.projarq.scaa.domain.model.Pagamento;

import java.util.Date;

public interface IPagamentoRepository {
    Pagamento create(Double valorPago, Date dataPagamento, String promocao, Long idAssinatura);
}