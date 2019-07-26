package br.home.adrnmatos;


public class MainEntry {

	public static void main(String[] args) {

		Scanner scanner = new Scanner();
		
		String string1 = " _|" + "| |" + "|_|";
		String string2 = " _ " + "| |" + "|_|";
		
		int result = scanner.distance(string1, string2);
		
		System.out.println(result);
		
		
	}

}
