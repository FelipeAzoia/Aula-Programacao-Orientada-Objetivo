public class PayPalStrategy implements FormaPagamentoStrategy {
    @Override
    public String processarPagamento(double valor) {
        return "Pagamento de R$" + valor + " feito no PayPal com taxa de 3%";
    }
}