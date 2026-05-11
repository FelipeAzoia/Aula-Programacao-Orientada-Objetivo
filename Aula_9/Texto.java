// Componente folha (leaf) — não tem filhos
public class Texto implements ComponenteGUI {

    private String conteudo;

    public Texto(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public void renderizar(String indentacao) {
        System.out.println(indentacao + "Texto: " + conteudo);
    }
}
