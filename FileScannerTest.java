import java.io.IOException;
import java.util.Scanner;


public class FileScannerTest {
	public static void main(String[] args) throws IOException {
		
		String testChoice;
		
		//Determine type of test to run
		System.out.print("Perform manual test 'm', automatic test 'a', or quit 'x'? ");
		testChoice = user_input.nextLine();
		if ("M".equals(testChoice.toUpperCase()))
			manualTest();
		else if ("A".equals(testChoice.toUpperCase()))
			automaticTest();
		else
			return;

	}
	
	  private static void manualTest() throws IOException{
			
			promptForDir();
			while (!("X".equals(directory.toUpperCase()))){
				test.scan(directory);
				System.out.println("The number of files is: " + test.getNumFiles());
				System.out.println("The number of directories is: " + test.getNumDirectories());
				System.out.println("The total number of bytes is: " + test.getTotalBytes());
				System.out.println("The average number of bytes is: " + test.getAvgBytes());
				promptForDir();
			}
	  }
	
	  private static void automaticTest() throws IOException{
		  	String directory;

		  	directory = "C:/Dirs/Dir1";
			test.scan(directory);
			System.out.println("Testing directory " + directory + " for assertion errors");
			assertGetNumFiles(5);
			assertGetNumDirectories(3);
			assertGetTotalBytes(26);
			assertGetAvgBytes(26/5);
			System.out.println("Completed assertion test for " + directory);
			
			directory = "C:/Dirs/Dir2";
			test.scan(directory);
			System.out.println("Testing directory " + directory + " for assertion errors");
			assertGetNumFiles(6);
			assertGetNumDirectories(5);
			assertGetTotalBytes(30);
			assertGetAvgBytes(30/6);
			System.out.println("Completed assertion test for " + directory);
			
			directory = "C:/Dirs/Dir3";
			test.scan(directory);
			System.out.println("Testing directory " + directory + " for assertion errors");
			assertGetNumFiles(0);
			assertGetNumDirectories(0);
			assertGetTotalBytes(0);
			assertGetAvgBytes(0);
			System.out.println("Completed assertion test for " + directory);
			
		  	directory = "C:/Dirs";
			test.scan(directory);
			System.out.println("Testing directory " + directory + " for assertion errors");
			assertGetNumFiles(11);
			assertGetNumDirectories(11);
			assertGetTotalBytes(56);
			assertGetAvgBytes(56/11);
			System.out.println("Completed assertion test for " + directory);
			
	  }
	  
	  private static void promptForDir(){
			System.out.print("Enter a directory to scan (ex 'c:/Dirs') or 'x' to quit: ");
			directory = user_input.nextLine();
	  }	  
	  
	  //Assertion functions
	  private static void assertGetNumFiles(int correctValue){
		  double returnedValue;
		  returnedValue = test.getNumFiles();
		  if (returnedValue != correctValue)
			  System.out.println("Assertion failed for getNumFiles() in the directory " + directory + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
		  else
			  System.out.println("Assertion succeeded for getNumFiles() in the directory " + directory + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
	  }
	  private static void assertGetNumDirectories(int correctValue){
		  int returnedValue;
		  returnedValue = test.getNumDirectories();
		  if (returnedValue != correctValue)
			  System.out.println("Assertion failed for getNumDirectories() in the directory " + directory + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
		  else
			  System.out.println("Assertion succeeded for getNumDirectories() in the directory " + directory + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
	  }
	  private static void assertGetTotalBytes(long correctValue){
		  long returnedValue;
		  returnedValue = test.getTotalBytes();
		  if (returnedValue != correctValue)
			  System.out.println("Assertion failed for getTotalBytes() in the directory " + directory + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
		  else
			  System.out.println("Assertion succeeded for getTotalBytes() in the directory " + directory + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
	  }
	  private static void assertGetAvgBytes(int correctValue){
		  int returnedValue;
		  returnedValue = test.getAvgBytes();
		  if (returnedValue != correctValue)
			  System.out.println("Assertion failed for getAvgBytes() in the directory " + directory + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
		  else
			  System.out.println("Assertion succeeded for getAvgBytes() in the directory " + directory + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
	  }
	  
	  private static FileScanner test = new FileScanner();
	  private static Scanner user_input = new Scanner( System.in );
	  private static String directory = "";
}
