package Aula_2;
import java.util.ArrayList;

public class Usuario {

    private String nome;
    private int id;
    private ArrayList<Livro> livrosEmprestados;

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.livrosEmprestados = new ArrayList<>();
    }

    public void emprestarLivro(Livro livro) {
        if (livro.isDisponivel()) {
            livro.emprestar();
            livrosEmprestados.add(livro);
        } else {
            System.out.println("Livro indisponível.");
        }
    }

    public void devolverLivro(Livro livro) {
        if (livrosEmprestados.contains(livro)) {
            livro.devolver();
            livrosEmprestados.remove(livro);
        } else {
            System.out.println("Este livro não pertence a este usuário.");
        }
    }

    public void listarLivros() {
        System.out.println("Livros emprestados por " + nome + ":");
        for (Livro livro : livrosEmprestados) {
            System.out.println("- " + livro.getTitulo());
        }
    }
}