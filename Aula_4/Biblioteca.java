import java.util.ArrayList;

public class Biblioteca {

    // Lista que armazena qualquer subtipo de MaterialBiblioteca
    private ArrayList<MaterialBiblioteca> materiais;

    public Biblioteca() {
        this.materiais = new ArrayList<>();
    }

    // Adiciona um material à biblioteca
    public void adicionarMaterial(MaterialBiblioteca material) {
        materiais.add(material);
        System.out.println("Material adicionado com sucesso: " + material.getTitulo());
    }

    public void removerMaterial(String titulo) {
        MaterialBiblioteca encontrado = null;

        for (MaterialBiblioteca m : materiais) {
            if (m.getTitulo().equalsIgnoreCase(titulo)) {
                encontrado = m;
                break;
            }
        }

        if (encontrado != null) {
            materiais.remove(encontrado);
            System.out.println("Material removido: " + titulo);
        } else {
            System.out.println("Material não encontrado: " + titulo);
        }
    }

    public void exibirInformacoesMaterial(String titulo) {
        for (MaterialBiblioteca m : materiais) {
            if (m.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println(m.getInformacoes());
                return;
            }
        }
        System.out.println("Material não encontrado: " + titulo);
    }

    public void listarTodosMateriais() {
        if (materiais.isEmpty()) {
            System.out.println("Nenhum material cadastrado na biblioteca.");
            return;
        }

        System.out.println("\n----- ACERVO DA BIBLIOTECA -----");
        for (MaterialBiblioteca m : materiais) {
            System.out.println(m.getInformacoes());
        }
        System.out.println("----------------------------------\n");
    }
}