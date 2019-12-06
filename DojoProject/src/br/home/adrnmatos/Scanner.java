package br.home.adrnmatos;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

	private final String zero = " _ " + "| |" + "|_|";

	private final String um = "   " + "  |" + "  |";

	private final String dois = " _ " + " _|" + "|_ ";

	private final String tres = " _ " + " _|" + " _|";

	private final String quatro = "   " + "|_|" + "  |";

	private final String cinco = " _ " + "|_ " + " _|";

	private final String seis = " _ " + "|_ " + "|_|";

	private final String sete = " _ " + "  |" + "  |";

	private final String oito = " _ " + "|_|" + "|_|";

	private final String nove = " _ " + "|_|" + " _|";
	
	private final String[] digits = {zero, um, dois, tres, quatro, cinco, seis, sete, oito, nove};
	
	private String input;
	private List<String> responses = new ArrayList<>();
		
	public String scan(String input) throws Exception {

		if (input.length() != 81)
			throw new Exception();
		
		this.input = input;

		StringBuffer account = new StringBuffer();

		for (int i = 0; i < 9; i++) {			
			String extractedDigit = extractDigit(i);
			String identifiedDigit = identifyDigit(extractedDigit);
			account.append(identifiedDigit);
		}
		
		checkForAccountNumbers(account.toString());
		
		String responseString = formatResponse(account.toString());
		responses.clear();
		
		return responseString;
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


	private void checkForAccountNumbers(String account) throws Exception {

		if (calculateCheckSum(account) == 0)
			responses.add(account.toString());
		
		else
			if (account.indexOf("?") != -1)
				checkWhenInvalidChars(account);
			else 
				checkWhenNotInvalidChars(account);
	}
	

	private void checkWhenInvalidChars(String account) {

		if(calculateCheckSum(account) == 0) {
			responses.add(account);
			return;
		}
		
		int index = account.indexOf("?");
		
		if(index != -1) {

			for(int i = 0; i < 10; i++) {
				if(distance(extractDigit(index), digits[i]) == 1) {
					StringBuffer possibleAccount = new StringBuffer(account);
					possibleAccount.replace(index, index + 1, Integer.toString(i));
					checkWhenInvalidChars(possibleAccount.toString());
				}
			}
		}
	}

	
	private void checkWhenNotInvalidChars(String account) {

		for(int i = 0; i < 9; i++) {

			for(int j = 0; j < 10; j++) {
				
				if(distance(extractDigit(i), digits[j]) == 1) {
					StringBuffer possibleAccount = new StringBuffer(account);
					
					possibleAccount.replace(i, i + 1, Integer.toString(j));
					if(calculateCheckSum(possibleAccount.toString()) == 0) {
						responses.add(possibleAccount.toString());
					}
				}
			}
		}
		
	}

	
	private int distance(String digit1, String digit2) {
	
		if(digit1.length() != digit2.length())
			return -1;
		
		int diffIndex = indexOfDifference(digit1, digit2);
		if(diffIndex == -1)
			return -1;
		
		if(diffIndex == 100)
			return 0;

		else {
			String subString1 = digit1.substring(diffIndex+1);
			String subString2 = digit2.substring(diffIndex+1);
			if(indexOfDifference(subString1, subString2) == 100)
				return 1;
		}
		
		return 2;
	}
	
	private int indexOfDifference(String str1, String str2) {
		if (str1.equals(str2)) {
			return 100;
		}
		
		if (str1 == null || str2 == null) {
			return -1;
		}
		
		for (int i = 0; i < str1.length(); ++i) {
			if (str1.charAt(i) != str2.charAt(i)) {
				return i;
			}
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


	private String formatResponse(String account) {

		if(responses.size() == 0)
			return (account + " ILL");
		
		if(responses.size() == 1)
			return responses.get(0);

		else {

			StringBuffer answerStr = new StringBuffer(account + " AMB ['");

			for (String response : responses) {
				answerStr.append(response);
				answerStr.append("', '");
			}
			
			answerStr.delete(answerStr.length() - 3, answerStr.length());
			answerStr.append("]");
			return answerStr.toString();
		}

	}

}
