public interface ProcessadorPagamento {

    boolean pagar(double valor);

    default void emitirRecibo(double valor) {
        System.out.println("Recibo: pagamento de R$ " + String.format("%.2f", valor) + " confirmado.");
    }
}