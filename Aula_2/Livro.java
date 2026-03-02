package Aula_2;
public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void emprestar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("Livro \"" + titulo + "\" emprestado com sucesso.");
        } else {
            System.out.println("Livro \"" + titulo + "\" já está emprestado.");
        }
    }

    public void devolver() {
        disponivel = true;
        System.out.println("Livro \"" + titulo + "\" devolvido com sucesso.");
    }

    @Override   
    public String toString() {
        return titulo + " - " + autor + " (" +
                (disponivel ? "Disponível" : "Emprestado") + ")";
    }
}

