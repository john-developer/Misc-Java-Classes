import java.io.IOException;
import java.util.Scanner;

public class ReadTextFileTest {
	public static void main(String... aArgs) throws IOException {

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

		ReadTextFile test = new ReadTextFile("");
		promptForFile();
		while (!("X".equals(filePath.toUpperCase()))){
			test.setFilePath(filePath);
			test.read(true);
			System.out.println("The sum is: " + test.getTotal());
			System.out.println("The total amount of numbers is: " + test.getCountOfNumbers());
			promptForSearchString();
			while (!("I".equals(searchString.toUpperCase())) && !(("X".equals(searchString.toUpperCase())))){
				System.out.println(searchString + " is found in " + filePath + " ?: " + test.contains(searchString));
				promptForSearchString();
			}
			if (("X".equals(searchString.toUpperCase())))
				break;
			promptForFile();
		} 
	}

	private static void automaticTest() throws IOException{

		test.setFilePath("c:/TextFiles/default.txt");
		test.read(false);
		System.out.println("Testing file " + test.getFilePath() + " for assertion errors");
		assertGetTotal(16.2);
		assertGetCountOfNumbers(3);
		assertGetCountOfStrings(4);
		assertContains(true, "foo");
		assertContains(false, "Gibberish");
		System.out.println("Completed assertion test for " + test.getFilePath());

		test.setFilePath("c:/TextFiles/Empty.txt");
		test.read(false);
		System.out.println("Testing file " + test.getFilePath() + " for assertion errors");
		assertGetTotal(0);
		assertGetCountOfNumbers(0);
		assertGetCountOfStrings(0);
		assertContains(false, "foo");
		assertContains(false, "Gibberish");
		System.out.println("Completed assertion test for " + test.getFilePath());

		test.setFilePath("c:/TextFiles/HugeList.txt");
		test.read(false);
		System.out.println("Testing file " + test.getFilePath() + " for assertion errors");
		assertGetTotal(917.60);
		assertGetCountOfNumbers(164);
		assertGetCountOfStrings(193);
		assertContains(false, "foo");
		assertContains(false, "Gibberish");
		System.out.println("Completed assertion test for " + test.getFilePath());

		test.setFilePath("c:/TextFiles/OnlyNums.txt");
		test.read(false);
		System.out.println("Testing file " + test.getFilePath() + " for assertion errors");
		assertGetTotal(7952.98);
		assertGetCountOfNumbers(10);
		assertGetCountOfStrings(0);
		assertContains(false, "foo");
		assertContains(false, "Gibberish");
		System.out.println("Completed assertion test for " + test.getFilePath());

		test.setFilePath("c:/TextFiles/OnlyStrings.txt");
		test.read(false);
		System.out.println("Testing file " + test.getFilePath() + " for assertion errors");
		assertGetTotal(0);
		assertGetCountOfNumbers(0);
		assertGetCountOfStrings(6);
		assertContains(true, "foo");
		assertContains(false, "Gibberish");
		System.out.println("Completed assertion test for " + test.getFilePath());
	}

	private static void promptForFile(){
		System.out.print("Enter .txt file path to read (ex 'c:/textfile.txt') or 'x' to quit: ");
		filePath = user_input.nextLine();
	}
	private static void promptForSearchString(){
		System.out.print("Enter a search string or 'i' to ignore 'x' to quit: ");
		searchString = user_input.nextLine();
	}


	//Assertion functions
	private static void assertGetTotal(double correctValue){
		double returnedValue;
		returnedValue = test.getTotal();
		if (returnedValue != correctValue)
			System.out.println("Assertion failed for getTotal() in the file " + test.getFilePath() + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
		else
			System.out.println("Assertion succeeded for getTotal() in the file " + test.getFilePath() + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
	}
	private static void assertGetCountOfNumbers(int correctValue){
		int returnedValue;
		returnedValue = test.getCountOfNumbers();
		if (returnedValue != correctValue)
			System.out.println("Assertion failed for getCountOfNumbers() in the file " + test.getFilePath() + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
		else
			System.out.println("Assertion succeeded for getCountOfNumbers() in the file " + test.getFilePath() + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
	}
	private static void assertGetCountOfStrings(int correctValue){
		int returnedValue;
		returnedValue = test.getCountOfStrings();
		if (returnedValue != correctValue)
			System.out.println("Assertion failed for getCountOfStrings() in the file " + test.getFilePath() + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
		else
			System.out.println("Assertion succeeded for getCountOfStrings() in the file " + test.getFilePath() + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);

	}
	private static void assertContains(boolean correctValue, String src){
		boolean returnedValue;
		returnedValue = test.contains(src);
		if (returnedValue != correctValue)
			System.out.println("Assertion failed for getContains("+src+") in the file " + test.getFilePath() + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);
		else
			System.out.println("Assertion succeeded for getContains("+src+") in the file " + test.getFilePath() + ".  Correct value: " + correctValue + ", Returned value: " + returnedValue);

	}

	private static ReadTextFile test = new ReadTextFile("");
	private static Scanner user_input = new Scanner( System.in );
	private static String filePath = "";
	private static String searchString = "";
}
