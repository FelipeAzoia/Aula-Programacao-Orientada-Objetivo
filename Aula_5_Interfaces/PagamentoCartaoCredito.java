public class PagamentoCartaoCredito implements ProcessadorPagamento {

    private static final double LIMITE_SIMULADO = 5000.00;

    private String numero;
    private String nomeTitular;
    private String cvv;

    public PagamentoCartaoCredito(String numero, String nomeTitular, String cvv) {
        this.numero      = numero;
        this.nomeTitular = nomeTitular;
        this.cvv         = cvv;
    }

    @Override
    public boolean pagar(double valor) {
        if (numero == null || numero.isBlank()
                || nomeTitular == null || nomeTitular.isBlank()
                || cvv == null || cvv.isBlank()) {
            System.out.println("Cartão recusado: dados do cartão incompletos.");
            return false;
        }
        if (valor > LIMITE_SIMULADO) {
            System.out.printf("Cartão recusado: valor R$ %.2f excede o limite de R$ %.2f.%n",
                    valor, LIMITE_SIMULADO);
            return false;
        }
        System.out.println("Autorizando cartão de crédito...");
        System.out.printf("Titular: %s | Final: %s%n",
                nomeTitular, numero.substring(Math.max(0, numero.length() - 4)));
        System.out.println("Transação autorizada!");
        return true;
    }
}