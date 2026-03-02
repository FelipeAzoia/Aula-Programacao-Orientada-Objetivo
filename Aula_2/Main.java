package Aula_2;
public class Main {

    public static void main(String[] args) {

        // Criando biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Criando livros
        Livro l1 = new Livro("Java POO", "Felipe Azoia", "1");
        Livro l2 = new Livro("Cálculo 2", "Lucas", "2");
        Livro l3 = new Livro("Estrutura de Dados C", "Kauã", "3");
        Livro l4 = new Livro("Arquitetura e Organização de Computadores", "Rafael", "4");
        
        // Adicionando livros à biblioteca
        biblioteca.adicionarLivro(l1);
        biblioteca.adicionarLivro(l2);
        biblioteca.adicionarLivro(l3);
        biblioteca.adicionarLivro(l4);

        // Criando usuário
        Usuario u1 = new Usuario("João", 1);
        biblioteca.adicionarUsuario(u1);

        // Listar livros
        biblioteca.listarLivros();

        System.out.println("\n--- Empréstimos ---");
        u1.emprestarLivro(l1);
        u1.emprestarLivro(l2);

        System.out.println("\n--- Livros do usuário ---");
        u1.listarLivros();

        System.out.println("\n--- Devolução ---");
        u1.devolverLivro(l1);

        System.out.println("\n--- Situação final ---");
        biblioteca.listarLivros();
    }
}