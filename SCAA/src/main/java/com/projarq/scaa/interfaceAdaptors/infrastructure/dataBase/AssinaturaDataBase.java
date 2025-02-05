package com.projarq.scaa.interfaceAdaptors.infrastructure.dataBase;


import com.projarq.scaa.domain.model.Aplicativo;
import com.projarq.scaa.domain.model.Assinatura;
import com.projarq.scaa.domain.model.Cliente;
import com.projarq.scaa.domain.model.Pagamento;
import com.projarq.scaa.domain.repository.IAssinaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

@Repository
public class AssinaturaDataBase implements IAssinaturaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AssinaturaDataBase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Assinatura create(Assinatura assinatura, Cliente cliente) {
        String sql = "INSERT INTO assinaturas (inicio_vigencia, fim_vigencia, aplicativo_codigo, cliente_codigo) VALUES (?, ?, ?, ?)";

        // Criar um KeyHolder para armazenar a chave gerada
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Executar a inserção e capturar a chave gerada
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"codigo"});
            ps.setDate(1, new java.sql.Date(assinatura.getInicioVigencia().getTime()));
            ps.setDate(2, new java.sql.Date(assinatura.getFimVigencia().getTime()));
            ps.setLong(3, assinatura.getAplicativo().getCodigo());
            ps.setLong(4, cliente.getCodigo());
            return ps;
        }, keyHolder);

        // Recuperar o código gerado da assinatura
        Long codigoAssinatura = keyHolder.getKey().longValue();

        // Definir o código da assinatura
        assinatura.setCodigo(codigoAssinatura);

        return assinatura;
    }

    @Override
    public List<Assinatura> getAll() {
        String sql = "SELECT * FROM assinaturas";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long codigo = rs.getLong("codigo");
            Date inicioVigencia = rs.getDate("inicio_vigencia");
            Date fimVigencia = rs.getDate("fim_vigencia");
            Long aplicativoId = rs.getLong("aplicativo_codigo");
            Long clienteId = rs.getLong("cliente_codigo");

            Cliente cliente = getClienteById(clienteId);
            Aplicativo aplicativo = getAplicativoById(aplicativoId);
            Assinatura assinatura = new Assinatura(codigo, inicioVigencia, fimVigencia, aplicativo, cliente);
            assinatura.setFimVigencia(fimVigencia);

            return assinatura;
        });
    }

    public List<Assinatura> getAssinaturaByClienteId(Long clienteId) {
        String sql = "SELECT * FROM assinaturas WHERE cliente_codigo = ?";
        return jdbcTemplate.query(sql, new Object[]{clienteId}, (rs, rowNum) -> {
            Long codigo = rs.getLong("codigo");
            Date inicioVigencia = rs.getDate("inicio_vigencia");
            Date fimVigencia = rs.getDate("fim_vigencia");
            Long aplicativoId = rs.getLong("aplicativo_codigo");

            Cliente cliente = getClienteById(clienteId);
            Aplicativo aplicativo = getAplicativoById(aplicativoId);
            Assinatura assinatura = new Assinatura(codigo, inicioVigencia, fimVigencia, aplicativo, cliente);
            assinatura.setFimVigencia(fimVigencia);

            return assinatura;
        });
    }

    @Override
    public Assinatura getById(Long codigo) {
        String sql = "SELECT * FROM assinaturas WHERE codigo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{codigo}, (rs, rowNum) -> {
            Date inicioVigencia = rs.getDate("inicio_vigencia");
            Date fimVigencia = rs.getDate("fim_vigencia");
            Long aplicativoId = rs.getLong("aplicativo_codigo");
            Long clienteId = rs.getLong("cliente_codigo");

            Cliente cliente = getClienteById(clienteId);
            Aplicativo aplicativo = getAplicativoById(aplicativoId);
            Assinatura assinatura = new Assinatura(codigo, inicioVigencia, fimVigencia, aplicativo, cliente);
            assinatura.setFimVigencia(fimVigencia);

            return assinatura;
        });
    }

    @Override
    public List<Assinatura> getByAplicativoId(Long aplicativoId) {
        String sql = "SELECT * FROM assinaturas WHERE aplicativo_codigo = ?";
        return jdbcTemplate.query(sql, new Object[]{aplicativoId}, (rs, rowNum) -> {
            Long codigo = rs.getLong("codigo");
            Date inicioVigencia = rs.getDate("inicio_vigencia");
            Date fimVigencia = rs.getDate("fim_vigencia");
            Long clienteId = rs.getLong("cliente_codigo");

            Cliente cliente = getClienteById(clienteId);
            Aplicativo aplicativo = getAplicativoById(aplicativoId);
            Assinatura assinatura = new Assinatura(codigo, inicioVigencia, fimVigencia, aplicativo, cliente);
            assinatura.setFimVigencia(fimVigencia);

            return assinatura;
        });
    }

    @Override
    public void update(Assinatura assinatura) {
        String sql = "UPDATE assinaturas SET inicio_vigencia = ?, fim_vigencia = ?, aplicativo_codigo = ?, cliente_codigo = ? WHERE codigo = ?";
        jdbcTemplate.update(sql, assinatura.getInicioVigencia(), assinatura.getFimVigencia(), assinatura.getAplicativo().getCodigo(), assinatura.getCliente().getCodigo(), assinatura.getCodigo());
    }

    @Override
    public void delete(Long codigo) {
        String sql = "DELETE FROM assinaturas WHERE codigo = ?";
        jdbcTemplate.update(sql, codigo);
    }

    private Aplicativo getAplicativoById(Long aplicativoId) {
        String sql = "SELECT * FROM aplicativos WHERE codigo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{aplicativoId}, (rs, rowNum) -> {
            Long codigo = rs.getLong("codigo");
            String nome = rs.getString("nome");
            double custo = rs.getDouble("custo_mensal");

            return new Aplicativo(codigo, nome, custo);
        });
    }

    private Cliente getClienteById(Long clienteId) {
        String sql = "SELECT * FROM clientes WHERE codigo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{clienteId}, (rs, rowNum) -> {
            String nome = rs.getString("nome");
            String email = rs.getString("email");

            return new Cliente(clienteId, nome, email);
        });
    }

    private List<Pagamento> getPagamentosByAssinaturaId(Long assinaturaId) {
        String sql = "SELECT * FROM pagamentos WHERE assinatura_codigo = ?";
        return jdbcTemplate.query(sql, new Object[]{assinaturaId}, (rs, rowNum) -> {
            Long codigo = rs.getLong("codigo");
            double valorPago = rs.getDouble("valor_pago");
            Date dataPagamento = rs.getDate("data_pagamento");
            String promocao = rs.getString("promocao");

            Assinatura assinatura = getAssinaturaById(assinaturaId);
            return new Pagamento(codigo, valorPago, dataPagamento, promocao, assinatura);
        });
    }

    public Assinatura getAssinaturaById(Long id) {
        String sql = "SELECT * FROM assinaturas WHERE codigo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Long codigo = rs.getLong("codigo");
            Date inicioVigencia = rs.getDate("inicio_vigencia");
            Date fimVigencia = rs.getDate("fim_vigencia");
            Long aplicativoId = rs.getLong("aplicativo_codigo");
            Long clienteId = rs.getLong("cliente_codigo");

            Cliente cliente = getClienteById(clienteId);
            Aplicativo aplicativo = getAplicativoById(aplicativoId);
            return new Assinatura(codigo, inicioVigencia, fimVigencia, aplicativo, cliente);
        });
    }
}
