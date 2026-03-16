public class VeiculoNovo extends Veiculo {

    private Integer garantiaAnos;

    public VeiculoNovo(String marca, String modelo, Integer ano, Double preco, Integer garantiaAnos) {
        super(marca, modelo, ano, preco);
        this.garantiaAnos = garantiaAnos;
    }

    public Integer getGarantiaAnos() {
        return garantiaAnos;
    }

    @Override
    public String getDescricao() {
        return "[NOVO] " + marca + " " + modelo
                + " | Ano: " + ano
                + " | Preço: R$ " + String.format("%.2f", preco)
                + " | Garantia: " + garantiaAnos + " ano(s)";
    }
}