import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Item("Cafe",          8.50));
        carrinho.adicionar(new Item("Pao de queijo", 6.00));
        carrinho.adicionar(new Item("Suco",          9.90));
        carrinho.adicionar(new Item("Croissant",     7.50));

        carrinho.exibirItens();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecione o meio de pagamento [1-Pix, 2-Cartao, 3-Boleto]: ");
        int opcao = scanner.nextInt();
        System.out.println();

        ProcessadorPagamento processador;

        switch (opcao) {
            case 1:
                processador = new PagamentoPix("aluno@exemplo.com");
                break;
            case 2:
                processador = new PagamentoCartaoCredito("4111111111111234", "Joao da Silva", "321");
                break;
            case 3:
                processador = new PagamentoBoleto();
                break;
            default:
                System.out.println("Opcao invalida. Encerrando.");
                scanner.close();
                return;
        }

        carrinho.finalizarCompra(processador);
        scanner.close();
    }
}
