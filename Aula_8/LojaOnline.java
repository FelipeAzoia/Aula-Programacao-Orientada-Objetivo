public class LojaOnline {
    private FormaPagamentoStrategy formaPagamento;

    public LojaOnline(FormaPagamentoStrategy formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void finalizarCompra(double valor) {
        String confirmacao = formaPagamento.processarPagamento(valor);
        System.out.println(confirmacao);
    }
}