public class VeiculoUsado extends Veiculo {

    private Integer quilometragem;
    private boolean unicoDono;

    public VeiculoUsado(String marca, String modelo, Integer ano, Double preco,
                        Integer quilometragem, boolean unicoDono) {
        super(marca, modelo, ano, preco);
        this.quilometragem = quilometragem;
        this.unicoDono = unicoDono;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public boolean isUnicoDono() {
        return unicoDono;
    }

    @Override
    public String getDescricao() {
        return "[USADO] " + marca + " " + modelo
                + " | Ano: " + ano
                + " | Preço: R$ " + String.format("%.2f", preco)
                + " | KM: " + quilometragem
                + " | Único dono: " + (unicoDono ? "Sim" : "Não");
    }
}