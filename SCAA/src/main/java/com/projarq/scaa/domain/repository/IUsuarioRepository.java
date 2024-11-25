package com.projarq.scaa.domain.repository;

import com.projarq.scaa.domain.model.Usuario;

public interface IUsuarioRepository {
    Usuario create(String nome, String email);
    Usuario get();
    void update(Usuario pagamento);
    void delete();
}
