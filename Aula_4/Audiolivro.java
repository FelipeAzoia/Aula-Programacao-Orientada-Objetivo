public class Audiolivro extends MaterialBiblioteca {

    private int duracaoMinutos;

    public Audiolivro(String titulo, String autor, int anoPublicacao, int duracaoMinutos) {
        super(titulo, autor, anoPublicacao);
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    @Override
    public String getInformacoes() {
        return "[AUDIOLIVRO] Título: " + getTitulo()
                + " | Autor: " + getAutor()
                + " | Ano: " + getAnoPublicacao()
                + " | Duração: " + duracaoMinutos + " min";
    }
}