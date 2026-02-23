package Aula_1;
import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro: ");
        int numeroInt = scanner.nextInt();

        System.out.print("Digite um número decimal (double): ");
        double numeroDouble = scanner.nextDouble();

        System.out.print("Digite um número grande (long): ");
        long numeroLong = scanner.nextLong();

        System.out.print("Digite um valor booleano (true/false): ");
        boolean valorBoolean = scanner.nextBoolean();

        Integer intWrapper = Integer.valueOf(numeroInt);
        Double doubleWrapper = Double.valueOf(numeroDouble);
        Long longWrapper = Long.valueOf(numeroLong);
        Boolean booleanWrapper = Boolean.valueOf(valorBoolean);

        Integer resultadoInt = intWrapper * 2;
        Double resultadoDouble = doubleWrapper + 5.5;
        Long resultadoLong = longWrapper / 2;
        Boolean resultadoBoolean = !booleanWrapper;

        System.out.println("\nResultados:");
        System.out.println("Inteiro multiplicado por 2: " + resultadoInt);
        System.out.println("Double + 5.5: " + resultadoDouble);
        System.out.println("Long dividido por 2: " + resultadoLong);
        System.out.println("Boolean invertido: " + resultadoBoolean);

        scanner.close();
    }
}
