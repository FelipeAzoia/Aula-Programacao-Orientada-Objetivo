public class PagamentoPix implements ProcessadorPagamento {

    private String chavePix;

    public PagamentoPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public boolean pagar(double valor) {
        if (valor <= 0 || chavePix == null || chavePix.isBlank()) {
            System.out.println("PIX recusado: chave inválida ou valor zerado.");
            return false;
        }
        System.out.println("Processando PIX para chave: " + chavePix + " ...");
        System.out.println("Pagamento aprovado!");
        return true;
    }
}