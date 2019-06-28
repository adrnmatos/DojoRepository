package br.home.adrnmatos;

public class MainEntry {

	public static void main(String[] args) {
		Scanner scanner = new Scanner();

		StringBuffer buffer = new StringBuffer("010000001");
		
		System.out.println("checkSum returned " + scanner.performCheckSum(buffer));
	
	}

}
