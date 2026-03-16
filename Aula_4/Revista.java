public class Revista extends MaterialBiblioteca {

    private int edicao;

    public Revista(String titulo, String autor, int anoPublicacao, int edicao) {
        super(titulo, autor, anoPublicacao);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }

    @Override
    public String getInformacoes() {
        return "[REVISTA] Título: " + getTitulo()
                + " | Autor: " + getAutor()
                + " | Ano: " + getAnoPublicacao()
                + " | Edição: " + edicao;
    }
}