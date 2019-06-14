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
	public void Scanner_validInput_returnTrue() {
		
		assertAll(
				() -> assertEquals("070032000", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
					       									 "| |  || || | _| _|| || || |" + 
					       									 "|_|  ||_||_| _||_ |_||_||_|")),
				
				() -> assertEquals("070032008", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
							 								 "| |  || || | _| _|| || ||_|" + 
							 								 "|_|  ||_||_| _||_ |_||_||_|")),
				
				() -> assertEquals("170032400", scanner.scan("    _  _  _  _  _     _  _ " + 
							 								 "  |  || || | _| _||_|| || |" + 
							 								 "  |  ||_||_| _||_   ||_||_|")),
				
				() -> assertEquals("571432698", scanner.scan(" _  _        _  _     _  _ " + 
															 "|_   |  ||_| _| _||_ |_||_|" + 
															 " _|  |  |  | _||_ |_|  ||_|"))
		);		
		
	}

}
