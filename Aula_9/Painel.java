import java.util.ArrayList;
import java.util.List;

// Componente composto (composite) — pode conter outros ComponenteGUI
public class Painel implements ComponenteGUI {

    private String nome;
    private List<ComponenteGUI> filhos = new ArrayList<>();

    public Painel(String nome) {
        this.nome = nome;
    }

    // Adiciona um componente filho ao painel
    public void adicionar(ComponenteGUI componente) {
        filhos.add(componente);
    }

    // Remove um componente filho do painel
    public void remover(ComponenteGUI componente) {
        filhos.remove(componente);
    }

    @Override
    public void renderizar(String indentacao) {
        System.out.println(indentacao + "Painel: " + nome);
        // Renderiza cada filho com indentação maior (recursão)
        for (ComponenteGUI filho : filhos) {
            filho.renderizar(indentacao + "  ");
        }
    }
}
