package br.home.adrnmatos;

public class FizzBuzz {
	
	public void generateSequence() {
		
		for(int i = 1; i <= 100; i++) {
			System.out.println(output(i));
		}
	}
	
	public String output(int num) {
		
		if(num % 15 == 0)
			return "fizzbuzz";
				
		if(Integer.toString(num).contains("3"))
			if(Integer.toString(num).contains("5"))
				return "fizzbuzz";
				
		if(Integer.toString(num).contains("3"))
			if(num % 5 == 0)
				return "fizzbuzz";
			else
				return "fizz";
		
		if(Integer.toString(num).contains("5"))
			if(num % 3 == 0)
				return "fizzbuzz";
			else
				return "buzz";
		
		if(num % 3 == 0)
			return "fizz";
		
		if(num % 5 == 0)
			return "buzz";
		
		return Integer.toString(num);
		
	}

}
