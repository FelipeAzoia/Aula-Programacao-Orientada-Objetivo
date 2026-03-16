public abstract class Veiculo {

    // Atributos protected: acessíveis pelas subclasses (herança)
    protected String marca;
    protected String modelo;
    protected Integer ano;
    protected Double preco;

    // Atributo estático: pertence à classe, não à instância (método estático)
    protected static Integer totalVeiculos = 0;

    // Construtor
    public Veiculo(String marca, String modelo, Integer ano, Double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        totalVeiculos++; // incrementa a cada novo objeto criado
    }

    // Método estático para consultar o total de veículos criados
    public static Integer getTotalVeiculos() {
        return totalVeiculos;
    }

    // Método abstrato: obriga subclasses a implementarem (polimorfismo)
    public abstract String getDescricao();

    // Sobrescrita de toString() para usar getDescricao() polimorficamente
    @Override
    public String toString() {
        return getDescricao();
    }
}