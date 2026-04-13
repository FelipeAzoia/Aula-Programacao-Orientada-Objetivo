import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static final int CAPACIDADE = 5;
    static Produto[] produtos = new Produto[CAPACIDADE];
    static int totalCadastrados = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;
        System.out.println("=== Sistema de Papelaria ===");
        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1: cadastrarProduto(); break;
                    case 2: exibirProdutos(); break;
                    case 3: calcularMedia(); break;
                    case 4: consultarPorPosicao(); break;
                    case 5: System.out.println("\nSistema encerrado. Até logo!"); break;
                    default: System.out.println("\n[AVISO] Opção inválida. Escolha entre 1 e 5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[ERRO] Opção inválida: digite apenas números inteiros.");
                scanner.nextLine(); 
            }

        } while (opcao != 5);
    }

    static void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Exibir produtos");
        System.out.println("3 - Calcular média dos preços");
        System.out.println("4 - Consultar produto por posição");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    static void cadastrarProduto() {
        if (totalCadastrados >= CAPACIDADE) {
            System.out.println("\n[AVISO] Limite de " + CAPACIDADE + " produtos atingido.");
            return;
        }

        System.out.println("\n-- Cadastro de Produto --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        double preco = 0;

        while (true) {
            System.out.print("Preço: R$ ");
            try {
                preco = scanner.nextDouble();
                scanner.nextLine();
                if (preco < 0) {
                    throw new IllegalArgumentException("Preço não pode ser negativo.");
                }
                break; 
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] Preço inválido: digite um número (ex: 9.90).");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERRO] " + e.getMessage());
            }
        }

        int codigo = 0;
        while (true) {
            System.out.print("Código (número inteiro): ");
            try {
                codigo = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] Código inválido: use apenas números inteiros.");
                scanner.nextLine();
            }
        }

        produtos[totalCadastrados] = new Produto(nome, preco, codigo);
        totalCadastrados++;
        System.out.println("[OK] Produto cadastrado na posição " + (totalCadastrados) + ".");
    }

    static void exibirProdutos() {
        System.out.println("\n-- Produtos Cadastrados --");
        for (int i = 0; i < CAPACIDADE; i++) {
            System.out.printf("Posição %d: ", i + 1);
            if (produtos[i] == null) {
                System.out.println("[vazio]");
            } else {
                System.out.println(produtos[i]);
            }
        }
    }

    static void calcularMedia() {
        System.out.println("\n-- Média dos Preços --");
        try {
            if (totalCadastrados == 0) {
                throw new ArithmeticException("Nenhum produto cadastrado (divisão por zero).");
            }
            double soma = 0;
            for (int i = 0; i < totalCadastrados; i++) {
                soma += produtos[i].getPreco();
            }
            double media = soma / totalCadastrados;
            System.out.printf("Média dos preços (%d produto(s)): R$ %.2f%n",
                    totalCadastrados, media);
        } catch (ArithmeticException e) {
            System.out.println("[ERRO] Impossível calcular a média: " + e.getMessage());
        }
    }

    static void consultarPorPosicao() {
        System.out.println("\n-- Consulta por Posição --");
        System.out.print("Digite a posição (1 a " + CAPACIDADE + "): ");

        try {
            int pos = scanner.nextInt();
            scanner.nextLine();
            int indice = pos - 1;
            Produto p = produtos[indice]; 

            if (p == null) {
                throw new NullPointerException("A posição " + pos + " está vazia.");
            }

            System.out.println("Produto encontrado: " + p);

        } catch (InputMismatchException e) {
            System.out.println("[ERRO] Posição inválida: digite um número inteiro.");
            scanner.nextLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[ERRO] Posição inexistente. Informe um valor entre 1 e " + CAPACIDADE + ".");
        } catch (NullPointerException e) {
            System.out.println("[AVISO] " + e.getMessage());
        }
    }
}