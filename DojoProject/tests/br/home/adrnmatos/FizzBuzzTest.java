package br.home.adrnmatos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FizzBuzzTest {
	
	FizzBuzz fizzBuzz;

	@BeforeEach
	void init() {
	
		fizzBuzz = new FizzBuzz();
	}

	
	@Test
	public void fizzBuzz_inputMultiple3_outputFizz() {
		
		int num = 12;
		
		String result = fizzBuzz.output(num);
		
		assertEquals("fizz", result);
	}

	
	@Test
	public void fizzBuzz_inputMultiple5_outputBuzz() {
		
		int num = 20;
		
		String result = fizzBuzz.output(num);
		
		assertEquals("buzz", result);
	}
	
	
	@Test
	public void fizzBuzz_inputMultiple3And5_outputFizzBuzz() {
		
		int num = 45;
		
		String result = fizzBuzz.output(num);
		
		assertEquals("fizzbuzz", result);
	}
	
	
	@Test
	public void fizzBuzz_inputNotMultiple3And5NorHas3or5InIt_outputSameInput() {
		
		int num = 14;
		
		String result = fizzBuzz.output(num);
		
		assertEquals("14", result);
	}
	
	
	@Test
	public void fizzBuzz_inputHas3InIt_outputFizz() {
		
		int num = 31;
		
		String result = fizzBuzz.output(num);
		
		assertEquals("fizz", result);
	}
	
	
	@Test
	public void fizzBuzz_inputHas5InIt_outputBuzz() {
		
		int num = 52;
		
		String result = fizzBuzz.output(num);
		
		assertEquals("buzz", result);
	}
	
	
	@Test
	public void fizzBuzz_inputHas3And5InIt_outputFizzBuzz() {
		
		int num = 538;
		
		String result = fizzBuzz.output(num);
		
		assertEquals("fizzbuzz", result);

	}
	
	
}