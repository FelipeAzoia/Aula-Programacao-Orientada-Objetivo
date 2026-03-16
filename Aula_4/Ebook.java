public class Ebook extends MaterialBiblioteca {

    private String formatoArquivo;

    public Ebook(String titulo, String autor, int anoPublicacao, String formatoArquivo) {
        super(titulo, autor, anoPublicacao);
        this.formatoArquivo = formatoArquivo;
    }

    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    @Override
    public String getInformacoes() {
        return "[EBOOK] Título: " + getTitulo()
                + " | Autor: " + getAutor()
                + " | Ano: " + getAnoPublicacao()
                + " | Formato: " + formatoArquivo;
    }
}