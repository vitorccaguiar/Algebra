package lucaslehmer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class LucasLehmer {
	
	public static ArrayList<BigInteger> createSequence(int size, BigInteger mersenne, int prints) {
		ArrayList<BigInteger> sequence = new ArrayList<BigInteger>();
		// Adiciona primeiro elemento da sequencia
		sequence.add(BigInteger.valueOf(4));
		
		if (prints == 1) System.out.println("\nCriando lista...\n");
		for (int count = 1; count < size; count++) {
			if (prints == 1) System.out.println("Calculando elemento " + (count+1) + "...");
			// Pega o ultimo elemento calculado da sequencia
			BigInteger element = sequence.get(count-1);
			// Eleva ele ao quadrado e subtrai 2
			element = element.modPow(BigInteger.valueOf(2), mersenne);
			element = element.subtract(BigInteger.valueOf(2));
			// Adiciona na sequencia
			sequence.add(element);
		}	
		return sequence;
	}
	
	public static void isMersennePrime( ArrayList<BigInteger> sequence, int index, BigInteger mersenne) {
		BigInteger element = sequence.get(index-1);
		if (element == BigInteger.valueOf(0)) {
			System.out.println("\nO número " + mersenne + " \né primo\n\n");
		}else {
			System.out.println("\nO número " + mersenne + " \nnão é primo\n\n");
		}
	}
	
	public static void lucasLehmerTest() {
		int n;
		Scanner scanner = new Scanner(System.in);
		BigInteger mersenne = BigInteger.valueOf(2);
				
		System.out.print("Digite um número: ");
		n = scanner.nextInt();
		// Calcula o numero de Mersenne
		mersenne = mersenne.pow(n);
		mersenne = mersenne.subtract(BigInteger.valueOf(1));
			
		System.out.println("M(" + n + ") é: " + mersenne);
		System.out.println("Verificando se " + mersenne + " é primo...");
			
		// Cria sequencia
		ArrayList<BigInteger> sequence = createSequence(n-1, mersenne, 1);
		// Verifica se o numero de Mersenne é primo
		isMersennePrime(sequence, n-1, mersenne);
	}
	
	public static void generateMersennePrimes() {
		int n, p = 3;
		ArrayList<BigInteger> sequence;
		BigInteger mersenne;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Quantidade de primos de mersenne: ");
		n = scanner.nextInt();
		int count = 0;
		while (count < n) {
			mersenne = BigInteger.valueOf(2);
			mersenne = mersenne.pow(p);
			mersenne = mersenne.subtract(BigInteger.valueOf(1));
			sequence = createSequence(p-1, mersenne, 0);
			BigInteger element = sequence.get(p-2);
			if (element == BigInteger.valueOf(0)) {
				System.out.println("\n" + (count+1) + ": M(" + p + ") = " + mersenne + " é primo");
				count++;
			}
			p++;
		}
	}
	
	public static void menu() {
		int opt = 1;
		
		while (opt >= 1 && opt <= 3) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n\tMenu");
			System.out.println("1- Teste de primalidade número de mersenne.");
			System.out.println("2- Gerar N primos de mersenne.");
			System.out.println("3- Sair");
			System.out.print("\nDigite a opção desejada: ");
			opt = scanner.nextInt();
			
			System.out.println("\n");
			if (opt == 1) lucasLehmerTest();
			else if (opt == 2) generateMersennePrimes();
			else System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		menu();
	}
}
