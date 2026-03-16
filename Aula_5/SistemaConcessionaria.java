public class SistemaConcessionaria {

    public static void main(String[] args) {

        Concessionaria concessionaria = new Concessionaria();

        VeiculoNovo v1 = new VeiculoNovo("Toyota", "Corolla", 2024, 145000.00, 3);
        VeiculoNovo v2 = new VeiculoNovo("Honda",  "Civic",   2024, 138000.00, 3);
        VeiculoUsado v3 = new VeiculoUsado("Ford", "Ka", 2019, 42000.00, 75000, true);
        VeiculoUsado v4 = new VeiculoUsado("Volkswagen", "Gol", 2018, 38000.00, 102000, false);

        System.out.println("--- Cadastrando veículos ---");
        concessionaria.cadastrarVeiculo(v1);
        concessionaria.cadastrarVeiculo(v2);
        concessionaria.cadastrarVeiculo(v3);
        concessionaria.cadastrarVeiculo(v4);

        System.out.println("Total criados: " + Veiculo.getTotalVeiculos());

        concessionaria.exibirEstoque();

        System.out.println("--- Registrando venda ---");
        concessionaria.registrarVenda("toyota-corolla-2024");
        concessionaria.registrarVenda("ford-ka-2019");

        concessionaria.exibirEstoque();
        concessionaria.exibirVendas();
    }
}
