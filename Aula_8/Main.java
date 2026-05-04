public class Main {
    public static void main(String[] args) {
        LojaOnline loja1 = new LojaOnline(new CartaoCreditoStrategy());
        loja1.finalizarCompra(100.00);

        LojaOnline loja2 = new LojaOnline(new PayPalStrategy());
        loja2.finalizarCompra(100.00);

        LojaOnline loja3 = new LojaOnline(new TransferenciaBancariaStrategy());
        loja3.finalizarCompra(100.00);
    }
}