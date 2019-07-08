package br.home.adrnmatos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScannerTest {

	Scanner scanner;
	
	@BeforeEach
	void init() {
	
		scanner = new Scanner();
	}
		
	
	@Test
	public void ScannerScan_validInput_returnsExpectedResult() {
		
		assertAll(
				() -> assertEquals("100000010", scanner.scan("    _  _  _  _  _  _     _ " + 
							                                 "  || || || || || || |  || |" + 
							                                 "  ||_||_||_||_||_||_|  ||_|")),
				
				() -> assertEquals("274853000", scanner.scan(" _  _     _  _  _  _  _  _ " + 
					       									 " _|  ||_||_||_  _|| || || |" + 
					       									 "|_   |  ||_| _| _||_||_||_|")),
				
				() -> assertEquals("438594711", scanner.scan("    _  _  _  _     _       " + 
							 								 "|_| _||_||_ |_||_|  |  |  |" + 
							 								 "  | _||_| _| _|  |  |  |  |")),
				
				() -> assertEquals("078261430", scanner.scan(" _  _  _  _  _        _  _ " + 
							 								 "| |  ||_| _||_   ||_| _|| |" + 
							 								 "|_|  ||_||_ |_|  |  | _||_|")),
				
				() -> assertEquals("194675831", scanner.scan("    _     _  _  _  _  _    " + 
															 "  ||_||_||_   ||_ |_| _|  |" + 
															 "  | _|  ||_|  | _||_| _|  |"))
		);		
		
	}
	
	

	public void Scanner_checkSumTest() {
		
		StringBuffer buffer = new StringBuffer("010000001");
		int checkSum = scanner.performCheckSum(buffer);
			
		System.out.println("checkSum returned " + checkSum);
		
		assertEquals(0, checkSum);
	}
	
	
	public void Scanner_fixIllTest() {
	
		StringBuffer buffer = new StringBuffer("0?0000001");
		
		String result = scanner.fixIllString(buffer);
		
		System.out.println("this test returns " + result);
	}

}
