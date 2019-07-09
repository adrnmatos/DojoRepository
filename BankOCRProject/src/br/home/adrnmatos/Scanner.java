package br.home.adrnmatos;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
	
	final String zero =   " _ "+
				  	      "| |"+
				          "|_|";
				
	final String um =     "   "+
						  "  |"+
	                      "  |";
				
	final String dois =   " _ "+
	       	              " _|"+
				          "|_ ";
				
	final String tres =   " _ "+
				          " _|"+
				          " _|";
				
	final String quatro = "   "+
				          "|_|"+
				          "  |";
				
	final String cinco =  " _ "+
				          "|_ "+
				          " _|";
				
	final String seis =   " _ "+
				          "|_ "+
				          "|_|";
				
	final String sete =   " _ "+
				          "  |"+
				          "  |";
				
	final String oito =   " _ "+
				          "|_|"+
				          "|_|";
				
	final String nove =   " _ "+
				          "|_|"+
				          " _|";
	
	
	private List<String> responses = new ArrayList<>();
	
	
	public String scan(String input) throws Exception {
		
		if(input.length() != 81)
			throw new Exception();
				
		StringBuffer digits = new StringBuffer();
		
		for(int k=0; k<9; k++) {
			StringBuffer singleDigit = new StringBuffer();

			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					int idx = 27*i+3*k+j;
					singleDigit.append(input.charAt(idx));
				}			
			}
			
			String recognizedDigit = recognizeDigit(singleDigit.toString());
			
			digits.append(recognizedDigit);
						
		}
		
		return fixBuffer(digits.toString());

	}
	
	
	private String recognizeDigit(String singleDigit) {

		switch (singleDigit) {
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
	
		
	private int calculateCheckSum(String digits) {
		int sum = 0;
		for(int i=0; i<9; i++) {
			int numericValue = Character.getNumericValue(digits.charAt(i));
			if(numericValue == -1)
				return -1;
			sum += (9-i) * numericValue;
		}

		return sum % 11;
	}
	
	
	private String fixBuffer(String buffer) {
		
		if(calculateCheckSum(buffer) == 0) {
			responses.add(buffer);
			String response = formatResponse();
			responses.clear();
			return response;
		} else
			return "";
	}
	
	
	private String findSubstitute(String buffer, int idx) {
		
		
		
		return "";
	}
	
	
	public int measureDistance(String digit1, String digit2) throws Exception {
		
		if(digit1.length() != digit2.length())
			throw new Exception();
		
		if(digit1.length() != 9)
			throw new Exception();
		
		int distance = 0;
		for(int i = 0; i < 9 ; i++) {
			
			if(digit1.charAt(i) != digit2.charAt(i))
				distance++;
		}
		
		return distance;
	}
	
	
	private String formatResponse() {
		
		if(responses.size() == 1) {
			
			return responses.get(0);
		}

		return null;
	}
	
}
