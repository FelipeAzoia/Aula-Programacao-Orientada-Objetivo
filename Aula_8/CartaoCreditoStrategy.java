public class CartaoCreditoStrategy implements FormaPagamentoStrategy {
    @Override
    public String processarPagamento(double valor) {
        return "Pagamento de R$" + valor + " feito no cartão de crédito com taxa de 2,5%";
    }
}