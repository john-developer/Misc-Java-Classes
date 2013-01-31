import java.io.File;

/**
 * FileScanner is a base class that scans a directory and provides
 * the user information abouts its contents.  This includes: the number of files,
 * the number of sub-directories, and the total size of all the files.
 * 
 * @author      John murphy
 */
public class FileScanner {

	/**
	 * Default constructor
	 */
	FileScanner(){
		numFiles = 0;
		numDirectories = 0;
		totalBytes = 0;
	}

	/**
	 * Returns the number of files found
	 * @return Integer : Number of files
	 */
	int getNumFiles() {return numFiles;}

	/**
	 * Returns the number of directories found
	 * @return Integer : Number of directories
	 */
	int getNumDirectories() {return numDirectories;}

	/**
	 * Returns the total amount of bytes
	 * @return Long : Total amount of bytes
	 */
	long getTotalBytes() {return totalBytes;}

	/**
	 * Returns the average amount of bytes per file
	 * @return Integer : The average bytes
	 */
	int getAvgBytes() {
		if (numFiles == 0)
			return 0;
		else
			return (int)(totalBytes / numFiles);
	}

	//Class properties
	private int numFiles;
	private int numDirectories;
	private long totalBytes;

	/**
	 * Performs scan of the specified file path
	 * @param Path String: File path to scan
	 */
	public void scan(String Path)
	{
		//Clear properties
		numFiles = 0;
		numDirectories = 0;
		totalBytes = 0;

		//Check if file exists
		File test = new File(Path);
		if (!test.exists()){
			log("File path doesn't exist: " + Path);
			return;
		}
		System.out.println("Scanning " + Path + "...");
		scanPath(Path);
		System.out.println("Scan completed succesfully!");
	}

	/**
	 * Does the actual scanning of the file path using recursion
	 * @param Path String: File path to scan
	 */
	private void scanPath(String Path)
	{
		File fD = new File(Path);
		for (File file : fD.listFiles()) {
			if (file.isFile()) {
				numFiles++;
				totalBytes += file.length();
			}
			if (file.isDirectory()) {
				numDirectories++;
				File[] listOfFiles = file.listFiles();
				if(listOfFiles!=null) 
					scanPath(file.getPath());
			}
		}
	}

	private void log(String aMessage){
		System.out.println(aMessage);
	} 
}
