package br.home.adrnmatos;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

	final String zero = " _ " + "| |" + "|_|";

	final String um = "   " + "  |" + "  |";

	final String dois = " _ " + " _|" + "|_ ";

	final String tres = " _ " + " _|" + " _|";

	final String quatro = "   " + "|_|" + "  |";

	final String cinco = " _ " + "|_ " + " _|";

	final String seis = " _ " + "|_ " + "|_|";

	final String sete = " _ " + "  |" + "  |";

	final String oito = " _ " + "|_|" + "|_|";

	final String nove = " _ " + "|_|" + " _|";
	
	final String[] digits = {zero, um, dois, tres, quatro, cinco, seis, sete, oito, nove};
	
	private String input;

	private List<String> responses = new ArrayList<>();
		
	public String scan(String input) throws Exception {

		if (input.length() != 81)
			throw new Exception();
		
		this.input = input;

		StringBuffer accountNum = new StringBuffer();

		for (int i = 0; i < 9; i++) {			
			String extractedDigit = extractDigit(i);
			String identifiedDigit = identifyDigit(extractedDigit);
			accountNum.append(identifiedDigit);
		}

		checkForAccountNumbers(accountNum);
		return formatResponse();

	}

	
	private String extractDigit(int i) {

		StringBuffer digit = new StringBuffer();

		for (int j = 0; j < 3; j++) {
			for (int k = 0; k < 3; k++) {
				int idx = 27 * j + 3 * i + k;
				digit.append(input.charAt(idx));
			}
		}

		return digit.toString();
	}

	private String identifyDigit(String digit) {

		switch (digit) {
		case zero:
			return "0";
		case um:
			return "1";
		case dois:
			return "2";
		case tres:
			return "3";
		case quatro:
			return "4";
		case cinco:
			return "5";
		case seis:
			return "6";
		case sete:
			return "7";
		case oito:
			return "8";
		case nove:
			return "9";
		default:
			return "?";
		}
	}


	private void checkForAccountNumbers(StringBuffer buffer) throws Exception {

		if (calculateCheckSum(buffer.toString()) == 0)
			responses.add(buffer.toString());
		
		else
			if (buffer.indexOf("?") == -1)
				checkWhenInvalidChars(buffer);
			else 
				checkWhenNotInvalidChars(buffer);
	}
	

	private void checkWhenInvalidChars(StringBuffer account) {

		if(calculateCheckSum(account.toString()) == 0) {
			responses.add(account.toString());
			return;
		}
		
		int index = account.indexOf("?");
		
		if(index != -1) {

			for(String digit : digits) {
				if(distance(extractDigit(index), digit) == 1) {
					account.replace(index, index + 1, digit);
					checkWhenInvalidChars(account);
				}
			}
		}
	}

	
	private void checkWhenNotInvalidChars(StringBuffer account) {

		if(calculateCheckSum(account.toString()) == 0) {
			responses.add(account.toString());
			return;
		}

		for(int i = 0; i < 9; i++) {
			for(String digit : digits) {
				
				if(distance(extractDigit(i), digit) == 1 && extractDigit(i).compareTo(digit) != 0) {
					account.replace(i, i + 1, digit);
					if(calculateCheckSum(account.toString()) == 0) {
						responses.add(account.toString());
						return;
					}
				}
			}
		}
		
	}

	
	private int distance(String digit1, String digit2) {
	
		if(digit1.length() != digit2.length())
			return -1;
		
		int diferenceIndex = indexOfDifference(digit1, digit2);
		if(diferenceIndex != digit1.length()) {
			String subString1 = digit1.substring(diferenceIndex);
			String subString2 = digit2.substring(diferenceIndex);
			if(indexOfDifference(subString1, subString2) == -1)
				return 1;
		}
		
		return 0;
	}
	
	public int indexOfDifference(String str1, String str2) {
		if (str1 == str2) {
			return -1;
		}
		
		if (str1 == null || str2 == null) {
			return 0;
		}
		
		int i;
		for (i = 0; i < str1.length() && i < str2.length(); ++i) {
			if (str1.charAt(i) != str2.charAt(i)) {
				break;
			}
		}
		
		if (i < str2.length() || i < str1.length()) {
			return i;
		}
		
		return -1;
	}
	
	
	private int calculateCheckSum(String digits) {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			int numericValue = Character.getNumericValue(digits.charAt(i));
			if (numericValue == -1)
				return -1;
			sum += (9 - i) * numericValue;
		}

		return sum % 11;
	}


	private String formatResponse() {

		if (responses.size() == 1) {
			if (responses.get(0).contains("?"))
				return (responses.get(0) + " ILL");
			return responses.get(0);
		}

		else {

			StringBuffer answerStr = new StringBuffer(this.input + " AMB ['");

			for (String account : responses) {
				answerStr.append(account);
				answerStr.append("', '");
			}
			answerStr.delete(answerStr.length() - 3, answerStr.length());
			answerStr.append("]");
			return answerStr.toString();
		}

	}

}
