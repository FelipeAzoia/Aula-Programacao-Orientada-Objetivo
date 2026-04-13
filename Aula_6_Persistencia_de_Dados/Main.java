import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_FILE  = "loja.db";
    static final String LOG_FILE = "loja.log";
    static GerenciarBD dao     = new GerenciarBD(DB_FILE);
    static Scanner     scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Sistema de Produtos - Papelaria ===");
        try {
            dao.criarTabelaSeNaoExistir();
            FileTools.appendLog(LOG_FILE, "SUCESSO", "Tabela verificada/criada.");
            System.out.println("[OK] Banco de dados pronto.");
        } catch (SQLException ex) {
            String msg = ex.getMessage() != null ? ex.getMessage() : "";
            if (msg.contains("already exists") || msg.contains("Table") || ex.getErrorCode() == 42101) {
                FileTools.appendLog(LOG_FILE, "INFO", "Tabela ja existia, seguindo normalmente.");
                System.out.println("[OK] Tabela ja existe. Continuando...");
            } else {
                FileTools.appendLog(LOG_FILE, "ERRO", "Falha ao criar/verificar tabela.", ex);
                System.err.println("[ERRO CRITICO] Nao foi possivel preparar o banco. Encerrando.");
                return;
            }
        }

        int opcao = -1;
        do {
            exibirMenu();
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1: adicionarProduto();   break;
                    case 2: listarTodos();         break;
                    case 3: listarEstoqueBaixo();  break;
                    case 4: atualizarEstoque();    break;
                    case 0: System.out.println("\nSistema encerrado. Ate logo!"); break;
                    default: System.out.println("\n[AVISO] Opcao invalida. Escolha entre 0 e 4.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\n[ERRO] Opcao invalida: digite apenas numeros inteiros.");
                scanner.nextLine();
                opcao = -1;
            }

        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1 - Adicionar produto");
        System.out.println("2 - Listar todos os produtos");
        System.out.println("3 - Listar produtos com estoque baixo");
        System.out.println("4 - Atualizar estoque de um produto");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    static void adicionarProduto() {
        System.out.println("\n-- Adicionar Produto --");

        String nome = "";
        while (nome.isEmpty()) {
            System.out.print("Nome do produto: ");
            nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("[ERRO] Nome nao pode ser vazio.");
            }
        }

        double preco = -1;
        while (preco < 0) {
            System.out.print("Preco (ex: 9.90): R$ ");
            try {
                preco = scanner.nextDouble();
                scanner.nextLine();
                if (preco < 0) {
                    System.out.println("[ERRO] Preco nao pode ser negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] Preco invalido: use numeros (ex: 9.90).");
                scanner.nextLine();
            }
        }

        int quantidade = -1;
        while (quantidade < 0) {
            System.out.print("Quantidade em estoque: ");
            try {
                quantidade = scanner.nextInt();
                scanner.nextLine();
                if (quantidade < 0) {
                    System.out.println("[ERRO] Quantidade nao pode ser negativa.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] Quantidade invalida: use numeros inteiros.");
                scanner.nextLine();
            }
        }

        try {
            long idGerado = dao.inserirProduto(nome, preco, quantidade);
            String logMsg = "Produto inserido: id=" + idGerado + ", nome=" + nome
                          + ", preco=" + String.format("%.2f", preco)
                          + ", qtd=" + quantidade;
            FileTools.appendLog(LOG_FILE, "SUCESSO", logMsg);
            System.out.printf("[OK] Produto \"%s\" cadastrado com ID %d.%n", nome, idGerado);
        } catch (SQLException ex) {
            FileTools.appendLog(LOG_FILE, "ERRO", "Falha ao inserir produto: " + nome, ex);
            System.out.println("[ERRO] Nao foi possivel cadastrar o produto: " + ex.getMessage());
        }
    }

    static void listarTodos() {
        System.out.println("\n-- Todos os Produtos --");
        try {
            List<GerenciarBD.ProdutoDTO> lista = dao.listarTodos();

            if (lista.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
                FileTools.appendLog(LOG_FILE, "INFO", "Listagem solicitada: nenhum produto encontrado.");
                return;
            }

            System.out.printf("%-5s %-25s %10s %10s%n", "ID", "Nome", "Preco", "Estoque");
            System.out.println("-".repeat(55));

            for (GerenciarBD.ProdutoDTO p : lista) {
                System.out.printf("%-5d %-25s R$ %7.2f %10d%n",
                        p.id, p.nome, p.preco, p.quantidade);
            }

            FileTools.appendLog(LOG_FILE, "SUCESSO",
                    "Listagem realizada: " + lista.size() + " produto(s) exibido(s).");

        } catch (SQLException ex) {
            FileTools.appendLog(LOG_FILE, "ERRO", "Falha ao listar produtos.", ex);
            System.out.println("[ERRO] Nao foi possivel listar os produtos: " + ex.getMessage());
        }
    }

    static void listarEstoqueBaixo() {
        System.out.println("\n-- Produtos com Estoque Baixo --");

        int limite = -1;
        while (limite < 0) {
            System.out.print("Mostrar produtos com estoque ate: ");
            try {
                limite = scanner.nextInt();
                scanner.nextLine();
                if (limite < 0) {
                    System.out.println("[ERRO] Limite nao pode ser negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] Valor invalido: use numeros inteiros.");
                scanner.nextLine();
            }
        }

        try {
            List<GerenciarBD.ProdutoDTO> lista = dao.listarPorEstoqueAte(limite);

            if (lista.isEmpty()) {
                System.out.println("Nenhum produto com estoque ate " + limite + " unidades.");
                FileTools.appendLog(LOG_FILE, "INFO",
                        "Filtro estoque ate " + limite + ": nenhum resultado.");
                return;
            }

            System.out.printf("%-5s %-25s %10s %10s%n", "ID", "Nome", "Preco", "Estoque");
            System.out.println("-".repeat(55));

            for (GerenciarBD.ProdutoDTO p : lista) {
                System.out.printf("%-5d %-25s R$ %7.2f %10d%n",
                        p.id, p.nome, p.preco, p.quantidade);
            }

            FileTools.appendLog(LOG_FILE, "SUCESSO",
                    "Filtro estoque ate " + limite + ": " + lista.size() + " produto(s).");

        } catch (SQLException ex) {
            FileTools.appendLog(LOG_FILE, "ERRO",
                    "Falha ao filtrar por estoque (limite=" + limite + ").", ex);
            System.out.println("[ERRO] Nao foi possivel filtrar produtos: " + ex.getMessage());
        }
    }

    static void atualizarEstoque() {
        System.out.println("\n-- Atualizar Estoque --");

        int id = -1;
        while (id <= 0) {
            System.out.print("ID do produto: ");
            try {
                id = scanner.nextInt();
                scanner.nextLine();
                if (id <= 0) {
                    System.out.println("[ERRO] ID deve ser maior que zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] ID invalido: use numeros inteiros.");
                scanner.nextLine();
            }
        }

        int novaQtd = -1;
        while (novaQtd < 0) {
            System.out.print("Nova quantidade em estoque: ");
            try {
                novaQtd = scanner.nextInt();
                scanner.nextLine();
                if (novaQtd < 0) {
                    System.out.println("[ERRO] Quantidade nao pode ser negativa.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] Quantidade invalida: use numeros inteiros.");
                scanner.nextLine();
            }
        }

        try {
            int linhasAfetadas = dao.atualizarQuantidade(id, novaQtd);

            if (linhasAfetadas == 0) {
                System.out.println("[AVISO] Nenhum produto encontrado com ID " + id + ".");
                FileTools.appendLog(LOG_FILE, "AVISO",
                        "Atualizacao falhou: ID " + id + " nao encontrado.");
            } else {
                System.out.printf("[OK] Estoque do produto ID %d atualizado para %d unidades.%n",
                        id, novaQtd);
                FileTools.appendLog(LOG_FILE, "SUCESSO",
                        "Estoque atualizado: id=" + id + ", novaQtd=" + novaQtd);
            }

        } catch (SQLException ex) {
            FileTools.appendLog(LOG_FILE, "ERRO",
                    "Falha ao atualizar estoque: id=" + id, ex);
            System.out.println("[ERRO] Nao foi possivel atualizar o estoque: " + ex.getMessage());
        }
    }
}