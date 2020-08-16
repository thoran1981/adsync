import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class baseballRecordWriter {
	private static String baseballFileName;
	private static String defaultFile = "C:\\Users\\Tim\\workspace\\baseballStats\\files\\baseballRecords.txt";
	private static BufferedWriter writer;
	
	/*************************************************************************
	 * No argument constructor
	 */
	baseballRecordWriter()
	{
		baseballFileName = new String(defaultFile);
		File outputFile = new File(baseballFileName);
		FileWriter outputFileWriter;
		
		try{ 
			if (!outputFile.exists()) {
			   outputFile.createNewFile();
			}
			outputFileWriter = new FileWriter(outputFile);
			writer = new BufferedWriter(new FileWriter(outputFile));
		} catch (IOException e)
		{
			System.err.println("Failed to open output file - " + baseballFileName);
		}
	}
	/**************************************************************
	 * Single argument constructor - user provided file name
	 * @param fileName
	 */
	baseballRecordWriter(String fileName)
	{
		baseballFileName = new String(fileName);
		File outputFile = new File(baseballFileName);
		FileWriter outputFileWriter;
		
		try{ 
			if (!outputFile.exists()) {
			   outputFile.createNewFile();
			}
			outputFileWriter = new FileWriter(outputFile);
			writer = new BufferedWriter(new FileWriter(outputFile));
		} catch (IOException e)
		{
			System.err.println("Failed to open output file - " + baseballFileName);
		}
	}
	
	/*************************************************************************
	 * Write single record to output file
	 * @param record
	 */
	public void write(baseballRecord record)
	{ 
		try {
			System.out.println("About to write - " + record.getLastName());
			writer.write(record.toString());
			writer.flush();
		} catch (IOException e)
		{
			System.out.println("Failed to write player - " + record.getLastName() + ", " + record.getFirstName());
		}
	}
	
}
