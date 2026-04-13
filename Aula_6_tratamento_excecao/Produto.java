public class Produto {
    private String nome;
    private double preco;
    private int codigo;

    public Produto(String nome, double preco, int codigo) {
        this.nome = nome;
        this.preco = preco;
        this.codigo = codigo;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getCodigo() { return codigo; }

    @Override
    public String toString() {
        return String.format("Código: %d | Nome: %s | Preço: R$ %.2f", codigo, nome, preco);
    }
}