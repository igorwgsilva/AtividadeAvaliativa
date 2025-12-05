/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.atividadeavaliativa;

/**
 *
 * @author igor2
 */

public class AtividadeAvaliativa {

    public static void main(String[] args) {
        
        DbUtils.createNewTable(); 
        
        IUsuarioRepository repository = new UsuarioJdbcRepository();
        
        UsuarioService service = new UsuarioService(repository);
  
        System.out.println("TESTE CADASTRO");
        
        Usuario admin = new Usuario();
        admin.setNome("Admin Principal");
        admin.setNomeDeUsuario("admin");
        admin.setSenha("senha123"); 
        
        try {
            Usuario adminSalvo = service.cadastrarNovoUsuario(admin);
            System.out.println("Cadastro 1 (Admin): OK!");
            System.out.printf("   ID: %d, Perfil: %s, Status: %s\n", adminSalvo.getId(), adminSalvo.getTipoPerfil(), adminSalvo.getStatus());
        } catch (IllegalArgumentException e) {
            System.err.println("Cadastro 1 (Admin): Nome de usuario ja existe.");
        }
        
        Usuario joao = new Usuario();
        joao.setNome("Joao Padrao");
        joao.setNomeDeUsuario("joao");
        joao.setSenha("joao123"); 
        
        try {
            Usuario joaoSalvo = service.cadastrarNovoUsuario(joao);
            System.out.println("Cadastro 2 (Padrao): OK!");
            System.out.printf("   ID: %d, Perfil: %s, Status: %s\n", joaoSalvo.getId(), joaoSalvo.getTipoPerfil(), joaoSalvo.getStatus());
        } catch (IllegalArgumentException e) {
            System.err.println("Cadastro 2 (Padrao): Nome de usuario ja existe.");
        }
        
        System.out.println("\nTESTE AUTORIZACAO");
        
        System.out.println("Tentativa de Login Joao (Pendente):");
        
        // SOLUÇÃO: BLOCO TRY-CATCH PARA CONTINUAR A EXECUÇÃO APÓS O ERRO ESPERADO
        try {
            Usuario joaoTentativa1 = service.autenticarUsuario("joao", "joao123");
            if (joaoTentativa1 != null) {
                 System.out.println("   Login SUCESSO INESPERADO (Status deveria ser PENDENTE).");
            }
        } catch (IllegalStateException e) {
            System.out.println("   Login falhou, MAS ESPERADO: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("   Erro durante a tentativa de login: " + e.getMessage());
        }
        
        
        try {
            Usuario joaoAutorizado = service.autorizarUsuario("joao");
            System.out.println("Autorizacao: OK! Usuário 'joao' agora esta " + joaoAutorizado.getStatus());
        } catch (Exception e) {
            System.err.println("Autorizacao: Erro: " + e.getMessage());
        }

        
        System.out.println("\nTESTE AUTENTICACAO");

        System.out.println("Tentativa de Login Admin:");
        Usuario adminLogado = service.autenticarUsuario("admin", "senha123");
        if (adminLogado != null) {
            System.out.println("   Login SUCESSO! Bem-vindo, " + adminLogado.getNome() + ".");
            System.out.println("   Perfil: " + adminLogado.getTipoPerfil());
        } else {
            System.err.println("   Login FALHOU para 'admin'.");
        }
        
        System.out.println("Tentativa de Login Joao (Autorizado):");
        Usuario joaoLogado = service.autenticarUsuario("joao", "joao123");
        if (joaoLogado != null) {
            System.out.println("   Login SUCESSO! Bem-vindo, " + joaoLogado.getNome() + ".");
            System.out.println("   Perfil: " + joaoLogado.getTipoPerfil());
        } else {
            System.err.println("   Login FALHOU para 'joao'.");
        }
    }
}
