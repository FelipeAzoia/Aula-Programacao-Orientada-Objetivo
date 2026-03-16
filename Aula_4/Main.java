public class Main {

    public static void main(String[] args) {
        // Instancia a biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Criando materiais de tipos diferentes
        Livro livro1       = new Livro("Dom Casmurro", "Machado de Assis", 1899, 256);
        Livro livro2       = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, 1200);
        Revista revista1   = new Revista("National Geographic", "Vários Autores", 2023, 405);
        Ebook ebook1       = new Ebook("Clean Code", "Robert C. Martin", 2008, "PDF");
        Ebook ebook2       = new Ebook("Effective Java", "Joshua Bloch", 2018, "EPUB");
        Audiolivro audio1  = new Audiolivro("Sapiens", "Yuval Noah Harari", 2011, 870);

        // Adicionando materiais à biblioteca
        System.out.println("--- Adicionando materiais ---");
        biblioteca.adicionarMaterial(livro1);
        biblioteca.adicionarMaterial(livro2);
        biblioteca.adicionarMaterial(revista1);
        biblioteca.adicionarMaterial(ebook1);
        biblioteca.adicionarMaterial(ebook2);
        biblioteca.adicionarMaterial(audio1);

        // Listando todos os materiais
        biblioteca.listarTodosMateriais();

        // Buscando um material específico
        System.out.println("--- Buscando material específico ---");
        biblioteca.exibirInformacoesMaterial("Clean Code");
        biblioteca.exibirInformacoesMaterial("Livro Inexistente");

        // Removendo um material
        System.out.println("\n--- Removendo material ---");
        biblioteca.removerMaterial("National Geographic");

        // Listando após remoção
        biblioteca.listarTodosMateriais();
    }
}