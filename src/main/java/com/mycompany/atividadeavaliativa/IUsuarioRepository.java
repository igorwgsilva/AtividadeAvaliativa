/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author igor2
 */
package com.mycompany.atividadeavaliativa;

import java.util.List;

public interface IUsuarioRepository {
    
    boolean existeUsuarioCadastrado(); 
    
    void salvar(Usuario usuario); 
    Usuario buscarPorNomeDeUsuario(String nomeDeUsuario);
    List<Usuario> buscarTodos(); 
}
