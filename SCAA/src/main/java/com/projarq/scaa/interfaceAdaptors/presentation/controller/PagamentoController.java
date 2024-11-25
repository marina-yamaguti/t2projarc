package com.projarq.scaa.interfaceAdaptors.presentation.controller;

import com.projarq.scaa.application.dto.PagamentoRequestDTO;
import com.projarq.scaa.application.dto.PagamentoResponseDTO;
import com.projarq.scaa.domain.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // Cria um pagamento
    @PostMapping("/registrarpagamento")
    public ResponseEntity<PagamentoResponseDTO> createPagamento(@RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        try {
            // Monta a data a partir de dia, mês e ano
            Calendar calendar = Calendar.getInstance();
            calendar.set(pagamentoRequestDTO.getAno(), pagamentoRequestDTO.getMes() - 1, pagamentoRequestDTO.getDia()); // Mês começa em 0 em Calendar

            Date dataPagamento = calendar.getTime();

            // Chama o serviço de criação do pagamento
            PagamentoResponseDTO responseDTO = pagamentoService.create(
                    pagamentoRequestDTO.getCodass(),
                    pagamentoRequestDTO.getValorPago(),
                    dataPagamento,
                    pagamentoRequestDTO.getPromocao()
            );

            return ResponseEntity.ok(responseDTO);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}