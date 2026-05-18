import java.util.Scanner;          // Para ler entradas do usuário
import java.io.FileWriter;         // Para escrever em arquivo
import java.io.PrintWriter;        // Para facilitar a escrita formatada
import java.io.IOException;        // Para tratar erros de I/O

/* ===================== ENUM ===================== */
// Enum representa conjunto fechado de prioridades (tipo seguro)
enum NivelPrioridade {
    BAIXA, MEDIA, ALTA, CRITICA;

    // Método opcional: descrição mais amigável de cada nível
    public String getDescricao() {
        switch (this) {
            case BAIXA:   return "Baixa prioridade – atendimento padrão";
            case MEDIA:   return "Média prioridade – atenção moderada";
            case ALTA:    return "Alta prioridade – atendimento urgente";
            case CRITICA: return "Crítica – intervenção imediata";
            default:      return this.name();
        }
    }
}

/* ===================== INTERFACE ===================== */
// Contrato para serviços de atendimento
interface Atendimento {
    // Retorna string descrevendo a ação realizada para resolver o chamado
    String resolverChamado(Chamado chamado);
}

/* ===================== ENTIDADES (COMPOSIÇÃO) ===================== */
// Representa o cliente que abriu o chamado (nome e email)
class Cliente {
    private String nome;
    private String email;

    public Cliente(String nome, String email) {
        this.nome  = nome;
        this.email = email;
    }

    public String getNome()  { return nome; }
    public String getEmail() { return email; }
}

// Representa o chamado; possui um Cliente (composição)
class Chamado {
    private int            id;
    private String         descricao;
    private NivelPrioridade prioridade;
    private Cliente        cliente;   // COMPOSIÇÃO: Chamado "tem um" Cliente

    public Chamado(int id, String descricao, NivelPrioridade prioridade, Cliente cliente) {
        this.id         = id;
        this.descricao  = descricao;
        this.prioridade = prioridade;
        this.cliente    = cliente;
    }

    // Valida os dados do chamado; lança exceção se inválido
    public void validar() {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty())
            throw new IllegalArgumentException("E-mail do cliente não pode ser vazio.");
        if (descricao == null || descricao.trim().isEmpty())
            throw new IllegalArgumentException("Descrição do chamado não pode ser vazia.");
        if (prioridade == null)
            throw new IllegalArgumentException("Prioridade não pode ser nula.");
    }

    public int             getId()        { return id; }
    public String          getDescricao() { return descricao; }
    public NivelPrioridade getPrioridade(){ return prioridade; }
    public Cliente         getCliente()   { return cliente; }
}

/* ===================== IMPLEMENTAÇÕES DA INTERFACE ===================== */
// Suporte N1: resolve BAIXA e MEDIA
class SuporteBasico implements Atendimento {
    @Override
    public String resolverChamado(Chamado chamado) {
        return "[Suporte Básico] Chamado #" + chamado.getId()
             + " (" + chamado.getPrioridade() + ") resolvido: "
             + "Reinicialização do sistema / atualização de software.";
    }
}

// Suporte N2: resolve ALTA e CRITICA
class SuporteAvancado implements Atendimento {
    @Override
    public String resolverChamado(Chamado chamado) {
        return "[Suporte Avançado] Chamado #" + chamado.getId()
             + " (" + chamado.getPrioridade() + ") resolvido: "
             + "Troca de hardware / intervenção remota avançada.";
    }
}

/* ===================== CLASSE PRINCIPAL ===================== */
// Classe principal: orquestra o fluxo, interação e persistência
public class ServiceDeskEx {

    // Grava a linha do chamado resolvido no arquivo chamados.txt (modo acréscimo)
    private static void gravarChamado(String linha) {
        try (FileWriter fw = new FileWriter("chamados.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(linha);
            System.out.println("Chamado gravado em 'chamados.txt' com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gravar o chamado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exibe as prioridades disponíveis
        System.out.print("Prioridades: ");
        for (NivelPrioridade np : NivelPrioridade.values()) {
            System.out.print(np + " ");
        }
        System.out.println();

        try {
            // Leitura dos dados do usuário
            System.out.print("Nome do cliente: ");
            String nome = scanner.nextLine();

            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            System.out.print("Descrição do chamado: ");
            String descricao = scanner.nextLine();

            System.out.print("Prioridade (BAIXA, MEDIA, ALTA, CRITICA): ");
            String entradaPrioridade = scanner.nextLine().trim().toUpperCase();

            // Converte string para enum — lança IllegalArgumentException se inválido
            NivelPrioridade prioridade;
            try {
                prioridade = NivelPrioridade.valueOf(entradaPrioridade);
            } catch (IllegalArgumentException e) {
                System.out.println("Prioridade inválida: '" + entradaPrioridade + "'. "
                                 + "Use: BAIXA, MEDIA, ALTA ou CRITICA.");
                return;
            }

            // Cria as entidades
            Cliente cliente = new Cliente(nome, email);
            Chamado chamado = new Chamado(1, descricao, prioridade, cliente);

            // Valida o chamado (lança IllegalArgumentException se inválido)
            chamado.validar();

            // Escolhe o atendimento conforme a prioridade
            Atendimento atendimento;
            if (prioridade == NivelPrioridade.BAIXA || prioridade == NivelPrioridade.MEDIA) {
                atendimento = new SuporteBasico();
            } else {
                atendimento = new SuporteAvancado();
            }

            // Resolve o chamado
            String resolucao = atendimento.resolverChamado(chamado);

            // Monta a linha de registro
            String linha = "ID: " + chamado.getId()
                         + " | Cliente: " + chamado.getCliente().getNome()
                         + " | Email: "   + chamado.getCliente().getEmail()
                         + " | Prioridade: " + chamado.getPrioridade()
                         + " | Resolução: "  + resolucao;

            // Exibe no console
            System.out.println("\n" + linha);

            // Persiste no arquivo
            gravarChamado(linha);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}