import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConversorDeTextoTest {

    private ConversorDeTexto conversor;

    @BeforeEach
    void setup() {
        conversor = new ConversorDeTexto();
    }

    // -------------------------
    // capitalizarPalavras
    // -------------------------

    @Test
    void capitalizarPalavrasTest() {
        String entrada = "java é divertido";
        String resultado = conversor.capitalizarPalavras(entrada);
        assertEquals("Java É Divertido", resultado);
    }

    @Test
    void capitalizarPalavrasExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> conversor.capitalizarPalavras(null));
        assertThrows(IllegalArgumentException.class, () -> conversor.capitalizarPalavras("   "));
    }

    // -------------------------
    // inverterPalavras
    // -------------------------

    @Test
    void inverterPalavrasTest() {
        String entrada = "ola mundo bonito";
        String resultado = conversor.inverterPalavras(entrada);
        assertEquals("bonito mundo ola", resultado);
    }

    @Test
    void inverterPalavrasExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> conversor.inverterPalavras(null));
        assertThrows(IllegalArgumentException.class, () -> conversor.inverterPalavras("   "));
    }

    // -------------------------
    // contarVogais
    // -------------------------

    @Test
    void contarVogaisTest() {
        String entrada1 = "BanAna";
        String entrada2 = "rhythms";
        int qtd1 = conversor.contarVogais(entrada1); 
        int qtd2 = conversor.contarVogais(entrada2);
        assertAll(
            () -> assertEquals(3, qtd1),
            () -> assertEquals(0, qtd2),
            () -> assertThrows(IllegalArgumentException.class, () -> conversor.contarVogais(null))
        );
    }

    // -------------------------
    // ehPalindromo
    // -------------------------

    @Test
    void ehPalindromoTest() {
        String palindromo    = "arara";
        String naoPalindromo = "java";
        String comEspacos    = "socorram me subi no onibus em marrocos";
        assertAll(
            () -> assertTrue(conversor.ehPalindromo(palindromo)),
            () -> assertFalse(conversor.ehPalindromo(naoPalindromo)),
            () -> assertTrue(conversor.ehPalindromo(comEspacos))
        );
    }

    @Test
    void ehPalindromoEntradaInvalidaTest() {
        assertAll(
            () -> assertFalse(conversor.ehPalindromo(null)),
            () -> assertFalse(conversor.ehPalindromo("   "))
        );
    }

    // -------------------------
    // removerPalavrasCurtas
    // -------------------------

    @Test
    void removerPalavrasCurtasTest() {
        String entrada = "hoje e um lindo dia";
        String resultado = conversor.removerPalavrasCurtas(entrada, 4);
        assertEquals("hoje lindo", resultado);
    }

    @Test
    void removerPalavrasCurtasTodasRemovidasTest() {
        String entrada = "eu te vi";
        String resultado = conversor.removerPalavrasCurtas(entrada, 5);
        assertEquals("", resultado);
    }

    @Test
    void removerPalavrasCurtasExceptionTest() {
        assertThrows(IllegalArgumentException.class,
            () -> conversor.removerPalavrasCurtas(null, 3));
    }

    // -------------------------
    // substituirPalavra
    // -------------------------

    @Test
    void substituirPalavraTest() {
        String entrada = "java é legal";
        String resultado = conversor.substituirPalavra(entrada, "java", "python");
        assertEquals("python é legal", resultado);
    }

    @Test
    void substituirPalavraInexistenteTest() {
        String entrada = "java é legal";
        String resultado = conversor.substituirPalavra(entrada, "ruby", "python");
        assertEquals("java é legal", resultado);
    }

    @Test
    void substituirPalavraExceptionTest() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class,
                () -> conversor.substituirPalavra(null, "java", "python")),
            () -> assertThrows(IllegalArgumentException.class,
                () -> conversor.substituirPalavra("java é legal", null, "python")),
            () -> assertThrows(IllegalArgumentException.class,
                () -> conversor.substituirPalavra("java é legal", "java", null))
        );
    }

    // -------------------------
    // contarPalavras
    // -------------------------

    @Test
    void contarPalavrasTest() {
        String entrada = "Java é muito legal";
        int resultado = conversor.contarPalavras(entrada);
        assertEquals(4, resultado);
    }

    @Test
    void contarPalavrasEntradaInvalidaTest() {
        assertAll(
            () -> assertEquals(0, conversor.contarPalavras(null)),
            () -> assertEquals(0, conversor.contarPalavras("   "))
        );
    }

    // -------------------------
    // inverterLetrasPorPalavra
    // -------------------------

    @Test
    void inverterLetrasPorPalavraTest() {
        String entrada = "java legal";
        String resultado = conversor.inverterLetrasPorPalavra(entrada);
        assertEquals("avaj lagel", resultado);
    }

    @Test
    void inverterLetrasPorPalavraUmaPalavraTest() {
        String entrada = "palindromo";
        String resultado = conversor.inverterLetrasPorPalavra(entrada);
        assertEquals("omordnilap", resultado);
    }

    @Test
    void inverterLetrasPorPalavraExceptionTest() {
        assertThrows(IllegalArgumentException.class,
            () -> conversor.inverterLetrasPorPalavra(null));
        assertThrows(IllegalArgumentException.class,
            () -> conversor.inverterLetrasPorPalavra("   "));
    }
}