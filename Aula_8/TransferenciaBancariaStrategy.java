public class TransferenciaBancariaStrategy implements FormaPagamentoStrategy {
    @Override
    public String processarPagamento(double valor) {
        return "Pagamento de R$" + valor + " feito por transferência bancária sem taxas";
    }
}