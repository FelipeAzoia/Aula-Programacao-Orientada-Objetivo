public class Livro extends MaterialBiblioteca {
    private int numeroPaginas;

    // Construtor chama o construtor da superclasse com super()
    public Livro(String titulo, String autor, int anoPublicacao, int numeroPaginas) {
        super(titulo, autor, anoPublicacao);
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String getInformacoes() {
        return "[LIVRO] Título: " + getTitulo()
                + " | Autor: " + getAutor()
                + " | Ano: " + getAnoPublicacao()
                + " | Páginas: " + numeroPaginas;
    }
}