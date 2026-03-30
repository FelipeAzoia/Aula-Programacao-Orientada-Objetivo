import java.util.UUID;

public class PagamentoBoleto implements ProcessadorPagamento {

    @Override
    public boolean pagar(double valor) {
        if (valor <= 0) {
            System.out.println("Boleto recusado: valor deve ser maior que zero.");
            return false;
        }
        String linhaDigitavel = UUID.randomUUID().toString().toUpperCase().replace("-", "");
        System.out.println("Boleto gerado com sucesso!");
        System.out.println("Linha digitável: " + linhaDigitavel);
        System.out.printf("Valor: R$ %.2f%n", valor);
        System.out.println("Vencimento: em 3 dias úteis.");
        System.out.println("Pague em qualquer banco, lotérica ou pelo app do seu banco.");
        return true;
    }
}