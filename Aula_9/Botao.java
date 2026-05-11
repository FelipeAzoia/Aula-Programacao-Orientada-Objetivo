// Componente folha (leaf) — não tem filhos
public class Botao implements ComponenteGUI {

    private String label;

    public Botao(String label) {
        this.label = label;
    }

    @Override
    public void renderizar(String indentacao) {
        System.out.println(indentacao + "Botão: " + label);
    }
}
