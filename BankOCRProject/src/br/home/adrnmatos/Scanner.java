package br.home.adrnmatos;

public class Scanner {
	
	final String zero = " _ "+
				        "| |"+
			            "|_|";
	
	final String um = "   "+
	                  "  |"+
			          "  |";
	
	final String dois = " _ "+
	                    " _|"+
			            "|_ ";
	
	final String tres = " _ "+
	                    " _|"+
			            " _|";
	
	final String quatro = "   "+
	                      "|_|"+
		                  "  |";
	
	final String cinco = " _ "+
	                     "|_ "+
			             " _|";
	
	final String seis = " _ "+
	                    "|_ "+
			            "|_|";
	
	final String sete = " _ "+
	                    "  |"+
			            "  |";
	
	final String oito = " _ "+
	                    "|_|"+
			            "|_|";
	
	final String nove = " _ "+
	                    "|_|"+
			            " _|";



	public String scan(String input) {
		
		if(input.length() != 81)
			return "NaN";
		
		StringBuffer digits = new StringBuffer();

		for(int k=0; k<9; k++) {
			StringBuffer digit = new StringBuffer();

			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					int idx = 27*i+3*k+j;
					digit.append(input.charAt(idx));
				}			
			}
			

			switch (digit.toString()) {
			case zero:
				digits.append("0");
				break;
			case um:
				digits.append("1");
				break;
			case dois:
				digits.append("2");
				break;
			case tres:
				digits.append("3");
				break;
			case quatro:
				digits.append("4");
				break;
			case cinco:
				digits.append("5");
				break;
			case seis:
				digits.append("6");
				break;
			case sete:
				digits.append("7");
				break;
			case oito:
				digits.append("8");
				break;
			case nove:
				digits.append("9");
				break;
			default:
				digits.append("?");
			}
						
		}
		
		
		int checkSum = performCheckSum(digits);
		
		if(checkSum == 0) {
			return digits.toString();
		} else if(checkSum == -1){
			return "ILL";
		} else {
			return "ERR";
		}

	}
	
		
	public int performCheckSum(StringBuffer digits) {
		int sum = 0;
		for(int i=0; i<9; i++) {
			int numericValue = Character.getNumericValue(digits.charAt(i));
			if(numericValue == -1)
				return -1;
			sum += (9-i) * numericValue;
		}

		return sum % 11;
	}
	
	
	public String fixIllString(StringBuffer digits) {
		
		
		return "010000001";
	}
	
}
