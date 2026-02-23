package Aula_1;
import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro (int): ");
        int numeroInt = scanner.nextInt();

        System.out.print("Digite um número decimal (float): ");
        float numeroFloat = scanner.nextFloat();

        System.out.print("Digite um número grande (long): ");
        long numeroLong = scanner.nextLong();

        System.out.print("Digite um caractere (char): ");
        char caractere = scanner.next().charAt(0);

        Integer intWrapper = Integer.valueOf(numeroInt);
        Float floatWrapper = Float.valueOf(numeroFloat);
        Long longWrapper = Long.valueOf(numeroLong);
        Character charWrapper = Character.valueOf(caractere);

        Integer resultadoInt = intWrapper + 10;
        Float resultadoFloat = floatWrapper * 2.5f;
        Long resultadoLong = longWrapper - 1000;

        boolean ehLetra = Character.isLetter(charWrapper);

        System.out.println("\nResultados:");
        System.out.println("Inteiro + 10: " + resultadoInt);
        System.out.println("Float * 2.5: " + resultadoFloat);
        System.out.println("Long - 1000: " + resultadoLong);
        System.out.println("O caractere é uma letra? " + ehLetra);

        scanner.close();
    }
}