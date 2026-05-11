public class Main {
    public static void main(String[] args) {
 
        // --- Monta o Painel Interno ---
        Painel painelInterno = new Painel("Painel Interno");
        painelInterno.adicionar(new Botao("Cancelar"));
        painelInterno.adicionar(new Texto("Mensagem interna"));
 
        // --- Monta o Painel Principal ---
        Painel painelPrincipal = new Painel("Painel Principal");
        painelPrincipal.adicionar(new Botao("Salvar"));
        painelPrincipal.adicionar(new Texto("Bem-vindo!"));
        painelPrincipal.adicionar(painelInterno); // Composite: painel dentro de painel
 
        // --- Dispara a renderização a partir da raiz ---
        painelPrincipal.renderizar("");
    }
}