/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.atividadeavaliativa;

/**
 *
 * @author igor2
 */

import java.util.ArrayList;
import java.util.List;

public class UsuarioJdbcRepository implements IUsuarioRepository {
    
    // Use uma lista ou mapa para armazenar os usuários em memória
    private final List<Usuario> usuarios = new ArrayList<>();
    
    @Override
    public boolean existeUsuarioCadastrado() {
        return !usuarios.isEmpty();
    }
    
    @Override
    public void salvar(Usuario usuario) {
        // Implemente a lógica de salvar ou atualizar
        usuarios.add(usuario);
    }
    
    @Override
    public Usuario buscarPorNomeDeUsuario(String nomeDeUsuario) {
        // Implemente a lógica de busca
        return usuarios.stream()
                       .filter(u -> u.getNomeDeUsuario().equals(nomeDeUsuario))
                       .findFirst()
                       .orElse(null); // Retorna null se não encontrar
    }
    
    @Override
    public List<Usuario> buscarTodos() {
        return new ArrayList<>(usuarios);
    }
}
