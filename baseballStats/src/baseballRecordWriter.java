import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class baseballRecordWriter {
	private static String baseballFileName;
	private static String defaultFile = "C:\\Users\\Tim\\workspace\\baseballStats\\files\\baseballRecords.txt";
	private static BufferedWriter writer;
	private static BufferedWriter overwriteWriter;
	private int unInitializedNumber = -1;
	
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
			outputFileWriter = new FileWriter(outputFile,true);
			writer = new BufferedWriter(outputFileWriter);
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
			outputFileWriter = new FileWriter(outputFile,true);
			writer = new BufferedWriter(outputFileWriter);
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
		String delimitedPlayer = new String(String.valueOf(record.getNumber())+ ',' + record.getFirstName() + ',' +
									record.getLastName() + ',' + String.valueOf(record.getHits()) + ',' +
									String.valueOf(record.getAB()) + ',' + String.valueOf(record.getBA()));
		
		try {
			writer.write("\n" + delimitedPlayer);
			writer.flush();
		} catch (IOException e)
		{
			System.out.println("Failed to write player - " + record.getLastName() + ", " + record.getFirstName());
		}
	}
	
	/***************************************************************
	 * Full overwrite of archive file
	 * @param records
	 */
	public void overWrite(baseballRecord [] records)
	{
		String delimitedPlayer;
		File overwriteFile = new File(baseballFileName);
		FileWriter overwriteFileWriter;
		int i;
		
		// Declare new file writer to overwrite contents of the existing file
		try {
			overwriteFileWriter = new FileWriter(overwriteFile);
			overwriteWriter = new BufferedWriter(overwriteFileWriter);
		} catch (IOException io)
		{
		}
		
		
		for(i=0;i<records.length;i++)
		{
			if(records[i] == null)
			{
				break;
			}
			else if(records[i+1] == null)
			{
				delimitedPlayer = new String(String.valueOf(records[i].getNumber())+ ',' + records[i].getFirstName() + ',' +
						records[i].getLastName() + ',' + String.valueOf(records[i].getHits()) + ',' +
						String.valueOf(records[i].getAB()) + ',' + String.valueOf(records[i].getBA()));
				try{
					overwriteWriter.write(delimitedPlayer);
					overwriteWriter.flush();
				} catch(IOException io)
				{
					System.out.println("Unable to print player - " + records[i].getFirstName() + " " + records[i].getLastName());
				}
			}
			else
			{
				delimitedPlayer = new String(String.valueOf(records[i].getNumber())+ ',' + records[i].getFirstName() + ',' +
						records[i].getLastName() + ',' + String.valueOf(records[i].getHits()) + ',' +
						String.valueOf(records[i].getAB()) + ',' + String.valueOf(records[i].getBA()));
				try{
					overwriteWriter.write(delimitedPlayer + "\n");
					overwriteWriter.flush();
				} catch(IOException io)
				{
					System.out.println("Unable to print player - " + records[i].getFirstName() + " " + records[i].getLastName());
				}
			}
		}
	}
	
}
