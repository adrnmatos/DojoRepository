package br.home.adrnmatos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScannerTest {

	Scanner scanner;
	
	@BeforeEach
	void init() {
	
		scanner = new Scanner();
	}
	
	@Disabled
	@Test
	public void scan_validInput_returnExpectedResult() {
		
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
															 "  | _|  ||_|  | _||_| _|  |")),
				
				() -> assertEquals("000000000", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
						                                     "| || || || || || || || || |" + 
						                                     "|_||_||_||_||_||_||_||_||_|")),
				
				() -> assertEquals("123456789", scanner.scan("    _  _     _  _  _  _  _ " + 
                                                             "  | _| _||_||_ |_   ||_||_|" + 
                                                             "  ||_  _|  | _||_|  ||_| _|")),
				
				() -> assertEquals("000000051", scanner.scan(" _  _  _  _  _  _  _  _    " + 
                                                             "| || || || || || || ||_   |" + 
                                                             "|_||_||_||_||_||_||_| _|  |"))
		);
		
	}
	
	@Test
	public void scan_invalidInput_returnFixedResult() {
		
		assertAll(
			() -> assertEquals("711111111", scanner.scan("                           " +
					                                     "  |  |  |  |  |  |  |  |  |" + 
					                                     "  |  |  |  |  |  |  |  |  |")),
			
			() -> assertEquals("777777177", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
					                                     "  |  |  |  |  |  |  |  |  |" + 
					                                     "  |  |  |  |  |  |  |  |  |")),
			
			() -> assertEquals("200800000", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
					                                     " _|| || || || || || || || |" + 
					                                     "|_ |_||_||_||_||_||_||_||_|")),
			
			() -> assertEquals("333393333", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
					                                     " _| _| _| _| _| _| _| _| _|" + 
					                                     " _| _| _| _| _| _| _| _| _|")),
			
			() -> assertEquals("888888888 AMB ['888886888', '888888880', '888888988']", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
					                                                                                 "|_||_||_||_||_||_||_||_||_|" + 
					                                                                                 "|_||_||_||_||_||_||_||_||_|")),
			
			() -> assertEquals("555555555 AMB ['555655555', '559555555']", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
					                                                                    "|_ |_ |_ |_ |_ |_ |_ |_ |_ " + 
					                                                                    " _| _| _| _| _| _| _| _| _|")),
			
			() -> assertEquals("666666666 AMB ['666566666', '686666666']", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
					                                                                    "|_ |_ |_ |_ |_ |_ |_ |_ |_ " + 
					                                                                    "|_||_||_||_||_||_||_||_||_|")),
			
			() -> assertEquals("999999999 AMB ['899999999', '993999999', '999959999']", scanner.scan(" _  _  _  _  _  _  _  _  _ " + 
					                                                                                 "|_||_||_||_||_||_||_||_||_|" + 
					                                                                                 " _| _| _| _| _| _| _| _| _|")),
			
			() -> assertEquals("490067715 AMB ['490067115', '490067719', '490867715']", scanner.scan("    _  _  _  _  _  _     _ " + 
					                                                                                 "|_||_|| || ||_   |  |  ||_ " + 
					                                                                                 "  | _||_||_||_|  |  |  | _|")),
			
			() -> assertEquals("123456789", scanner.scan("    _  _     _  _  _  _  _ " + 
					                                     " _| _| _||_||_ |_   ||_||_|" + 
					                                     "  ||_  _|  | _||_|  ||_| _|")),
			
			() -> assertEquals("000000051", scanner.scan(" _     _  _  _  _  _  _    " + 
					                                     "| || || || || || || ||_   |" + 
					                                     "|_||_||_||_||_||_||_| _|  |")),
			
			() -> assertEquals("490867715", scanner.scan("    _  _  _  _  _  _     _ " + 
					                                     "|_||_|| ||_||_   |  |  | _ " + 
					                                     "  | _||_||_||_|  |  |  | _|"))
				
		);
		
	}

	@Disabled
	@Test
	public void scan_ambiguousInput_returnIllResult() {
		
		assertAll(
			() -> assertEquals("49006771? ILL", scanner.scan("    _  _  _  _  _  _     _ " + 
					                                         "|_||_|| || ||_   |  |  | _ " + 
					                                         "  | _||_||_||_|  |  |  | _|")),
			
			() -> assertEquals("1234?678? ILL", scanner.scan("    _  _     _  _  _  _  _ " + 
					                                         "  | _| _||_| _ |_   ||_||_|" + 
					                                         "  ||_  _|  | _||_|  ||_| _ "))
		);
	}
	
	@Test
	public void scanDistance_equalStrings_returnZero() {
		
		String string1 = " _ " + "| |" + "|_|";
		String string2 = " _ " + "| |" + "|_|";
		
		int result = scanner.distance(string1, string2);
		
		assertEquals(0, result);
		
	}
	
	@Test
	public void scanDistance_1traceUnequalStrings_returnOne() {

		String string1 = " _ " + "| |" + "|_|";
		String string2 = " _ " + "|_|" + "|_|";
		
		int result = scanner.distance(string1, string2);
		
		assertEquals(1, result);

	}
	
	
	@Test
	public void scanDistance_moreThan1traceUnequalStrings_returnOne() {

		String string1 = " _ " + "  |" + "|_|";
		String string2 = " _|" + "| |" + "|_|";
		
		int result = scanner.distance(string1, string2);
		
		assertEquals(2, result);

	}
	
	 
	
}
