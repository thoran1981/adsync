import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.*;

public class baseballRecordReader {
	private static String baseballFileName;
	private static String defaultFile = "C:\\Users\\Tim\\workspace\\baseballStats\\files\\baseballRecords.txt";
	//private static BufferedReader read;
	private static Scanner scan;
	private static String scanDelim = new String(",");
	
	/***********************************************************
	 * No argument constructor
	 */
	baseballRecordReader()
	{
		baseballFileName = new String(defaultFile);
		File inputFile = new File(baseballFileName);
		
		try {
			scan = new Scanner(inputFile);
		} catch (FileNotFoundException f)
		{
			System.err.println("Error.unable to load file - " + baseballFileName);
		}
		
		scan.useDelimiter(scanDelim);
		
		/*
		try{ 
			if (!inputFile.exists()) {
			   inputFile.createNewFile();
			}
									
			inputFileReader = new FileReader(inputFile);
			read = new BufferedReader(inputFileReader);
		} catch (FileNotFoundException e)
		{
			System.err.println("Initializatoin of record file - " + baseballFileName 
					+ " failed.");
		}
		catch (IOException i)
		{
			System.err.println("failed to open file - " + baseballFileName);
		}
		*/
	}
	
	/****************************************************************
	 * Single argument constructor. User provided file name
	 * @param inputFileName
	 */
	baseballRecordReader(String inputFileName)
	{
		baseballFileName = new String(inputFileName);
		File inputFile = new File(baseballFileName);
		
		try {
			scan = new Scanner(inputFile);
		} catch (FileNotFoundException f)
		{
			System.err.println("Error.unable to load file - " + baseballFileName);
		}
		
		scan.useDelimiter(scanDelim);
		
		/*
		try{ 
			if (!inputFile.exists()) {
			   inputFile.createNewFile();
			}
									
			inputFileReader = new FileReader(inputFile);
			read = new BufferedReader(inputFileReader);
		} catch (FileNotFoundException e)
		{
			System.err.println("Initializatoin of record file - " + baseballFileName 
					+ " failed.");
		}
		catch (IOException i)
		{
			System.err.println("failed to open file - " + baseballFileName);
		}
		*/
	}
	
	/*******************************************************
	 * set name of file to read records from
	 * @param fileName
	 */
	public void setFileName(String fileName)
	{
		baseballFileName = fileName;
	}
	
	/*****************************************************
	 * 
	 * @return baseballFileName
	 */
	public String getFileName()
	{
		return baseballFileName;
	}
	
	/******************************************************
	 * Queries next line in the input file
	 * 
	 * 8/15/2020 - TPH - ****BUG**** Can't get Scanner to work with Eclipse, 
	 * 	so currently BufferedReader is deleting records from file as it reads ******* 
	 * 
	 * 8/16/2020 - TPH - Updated code to replace BufferedReader with Scanner 
	 * 
	 * @return baseballRecord
	 */
	public baseballRecord readNextRecord()
	{	
		baseballRecord result = null;
		String nextRow = new String();
		String [] playerMetrics;
		
		try {
		if ((nextRow = scan.nextLine()) != null)
		{
			playerMetrics = nextRow.split(",");
			result = new baseballRecord(Integer.parseInt(playerMetrics[0]), playerMetrics[1], playerMetrics[2], 
					Integer.parseInt(playerMetrics[3]),Integer.parseInt(playerMetrics[4]), 
					Double.parseDouble(playerMetrics[5]));
		}
		} catch(NoSuchElementException nse)
		{
		}

		return result;
	}
	
	/************************************************************
	 * Print entire file one line at a time
	 */
	public void read()
	{
		baseballRecord nextRecord = new baseballRecord();
		
		// Reset Scanner
		File inputFile = new File(baseballFileName);
		
		try {
			scan = new Scanner(inputFile);
		} catch (FileNotFoundException f)
		{
			System.err.println("Error.unable to load file - " + baseballFileName);
		}
		
		scan.useDelimiter(scanDelim);
		
		//Print headers
		System.out.println("Num\tFName\t\tLName\t\tHits\tAB\tAvg");
		
		// While more records on file, print individually
		nextRecord = this.readNextRecord();
		while (nextRecord != null)
		{
			System.out.println(nextRecord.toString());
			nextRecord = this.readNextRecord();
		}
	} 
}
