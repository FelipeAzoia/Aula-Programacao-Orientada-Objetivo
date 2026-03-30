import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<Item> itens = new ArrayList<>();

    public void adicionar(Item item) {
        itens.add(item);
    }

    public double total() {
        double soma = 0;
        for (Item item : itens) {
            soma += item.getPreco();
        }
        return soma;
    }

    public void exibirItens() {
        System.out.println("Carrinho:");
        for (Item item : itens) {
            System.out.printf("  - %s: R$ %.2f%n", item.getDescricao(), item.getPreco());
        }
        System.out.printf("Total: R$ %.2f%n", total());
    }

    // O Carrinho conhece APENAS a interface — nunca as classes concretas
    public void finalizarCompra(ProcessadorPagamento proc) {
        double valor = total();
        boolean aprovado = proc.pagar(valor);
        if (aprovado) {
            proc.emitirRecibo(valor);
        } else {
            System.out.println("Pagamento recusado.");
        }
    }
}