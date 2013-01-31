import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ReadTextFile {

	/**
	 * Constructor
	 * @param aFilePath String : Full file path of text file
	 */
	ReadTextFile(String aFilePath){
		filePath = sanitizeFilePath(aFilePath);
	}

	//Class fields
	private String filePath;
	private double total;
	private int numCount;
	private List<String> stringList = new ArrayList<String>();

	/**
	 * Returns the file path
	 * @return String: The file path 
	 * @throws IllegalArgumentException
	 */
	public String getFilePath() throws IllegalArgumentException {return filePath;}

	/**
	 * Sets the file path
	 * @param aFilePath String : File path to set
	 */
	public void setFilePath(String aFilePath){filePath = sanitizeFilePath(aFilePath);}


	/**
	 * Scans in all lines of the text file and outputs a summary (if desired)
	 * @param showOutput Boolean: True = show summary, False = don't show summary
	 * @throws IOException
	 */
	void read(boolean showOutput) throws IOException {

		total = 0;
		numCount = 0;
		stringList.clear();

		//Check if file exists
		File test = new File(filePath);
		if (!test.exists()){
			log("File doesn't exist: " + filePath);
			return;
		}

		log("Reading from file: " + filePath);
		StringBuilder text = new StringBuilder();
		String NL = System.getProperty("line.separator");
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		try {
			while (scanner.hasNextLine()){
				text.append(scanner.nextLine() + NL);
			}
		}
		finally{
			scanner.close();
		}
		//log("Text read in: " + text);
		output("" + text, showOutput);
	}

	/**
	 * Returns the sum of all the numbers in the text file
	 * @Return : Sum of all the numbers
	 */
	public double getTotal(){
		return total;
	}

	/**
	 * Returns the count of numbers in the text file
	 * @Return: Count of numbers
	 */
	public int getCountOfNumbers(){
		return numCount;
	}

	/**
	 * Returns the amount of strings in the text file
	 * @return Integer: Number of strings
	 */
	public int getCountOfStrings(){
		return stringList.size();
	}

	/**
	 * Determines if the specified string is in the text file
	 * @param src String to check
	 * @return True if found, false if not found
	 */
	public boolean contains(String src){
		return stringList.contains(src);
	}

	/**
	 * Outputs the specified string to the command line
	 * @param aMessage : String to display
	 */
	private void log(String aMessage){
		System.out.println(aMessage);
	} 

	/**
	 * Parses thru input file text and outputs summary
	 * @param text String representing contents of the input file
	 */
	private void output(String text, boolean showOutput){
		double sum = 0;
		double average = 0;
		double median = 0;
		double num;
		double numPercent;
		int numListSize = 0;
		int stringListSize = 0;

		List<Double> numList = new ArrayList<Double>();

		DecimalFormat df = new DecimalFormat("#.##");

		String lines[] = text.split(System.getProperty("line.separator"));
		for(String s: lines){
			s = s.trim();
			if (isNumeric(s)){
				num = Double.parseDouble(s);
				numList.add(num);	
			}
			else if (!("".equals(s))){
				stringList.add(s);
			}
		}

		if (!numList.isEmpty()){
			numListSize = numList.size();
			Collections.sort(numList);

			for (int i = 0; i <= numListSize - 1; i++){
				sum += numList.get(i);
				//Check for median
				if (i == numListSize / 2){
					median = numList.get(i);
				}
			}
			average = sum / numListSize;
			total = Double.valueOf(df.format(sum));
			numCount = numListSize;
		}

		if (!stringList.isEmpty()){
			stringListSize = stringList.size();
			Collections.sort(stringList,Collator.getInstance());
			Collections.reverse(stringList);
		}

		//Determine percentage of numbers
		if (numListSize > 0 && stringListSize == 0)
			numPercent = 100;
		else if (stringListSize > 0)
			numPercent = (double)numListSize / ((double)numListSize + (double)stringListSize) * 100;
		else
			numPercent = 0;

		if (showOutput){
			//Output summary
			System.out.println("File succesfully scanned: " + filePath);
			System.out.println("The sum is: " + df.format(sum));
			System.out.println("The average is: " + df.format(average));
			System.out.println("The median is: " + df.format(median));
			System.out.println("The percentage of numbers is: " + df.format(numPercent));

			//Output all distinct strings along with their frequency
			System.out.println("The following strings appear in the file, along with their count...");
			if (!stringList.isEmpty()){
				Set<String> unique = new LinkedHashSet<String>(stringList);
				for (String key : unique) {
					System.out.println(key + ": " + Collections.frequency(stringList, key));
				}
			}
			else
				System.out.println("(No strings found)");
		}
	} 

	/**
	 * Determines if the specified string is numeric
	 * @param str String: String to check
	 * @return True if string is numeric, false if not numeric
	 */
	private static boolean isNumeric(String str)
	{
		return str.matches("[-+]?\\d+(\\.\\d+)?");
	}

	private String sanitizeFilePath(String filePath){
		//Sanitize file path
		filePath = filePath.replace("\\", "//");
		filePath = filePath.replace("?", "");
		return filePath;
	}
}