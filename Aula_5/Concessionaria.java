import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Concessionaria {
    private Map<String, Veiculo> veiculos;
    private ArrayList<Veiculo> vendas;
    public Concessionaria() {
        this.veiculos = new HashMap<>();
        this.vendas = new ArrayList<>();
    }

    private String gerarChave(Veiculo veiculo) {
        return (veiculo.marca + "-" + veiculo.modelo + "-" + veiculo.ano).toLowerCase();
    }

    public void cadastrarVeiculo(Veiculo veiculo) {
        String chave = gerarChave(veiculo);
        veiculos.put(chave, veiculo);
        System.out.println("Veículo cadastrado [" + chave + "]: " + veiculo.getDescricao());
    }

    public void registrarVenda(String chave) {
        Veiculo vendido = veiculos.remove(chave.toLowerCase());
        if (vendido != null) {
            vendas.add(vendido);
            System.out.println("Venda registrada: " + vendido.getDescricao());
        } else {
            System.out.println("Veículo não encontrado no estoque: " + chave);
        }
    }

    public void exibirEstoque() {
        System.out.println("\n----- ESTOQUE ATUAL (" + veiculos.size() + " veículo(s)) -----");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo em estoque.");
        } else {
            for (Map.Entry<String, Veiculo> entry : veiculos.entrySet()) {
                System.out.println("  Chave: " + entry.getKey());
                System.out.println("  " + entry.getValue()); 
                System.out.println();
            }
        }
        System.out.println("------------------------------------------\n");
    }

    public void exibirVendas() {
        System.out.println("\n----- VEÍCULOS VENDIDOS (" + vendas.size() + " venda(s)) -----");
        if (vendas.isEmpty()) {
            System.out.println("Nenhum veículo vendido ainda.");
        } else {
            for (Veiculo v : vendas) {
                System.out.println("  " + v); 
            }
        }
        System.out.println("------------------------------------------\n");
    }
}