import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.*;

public class baseballRecordReader {
	private static String baseballFileName;
	private static String defaultFile = "C:\\Users\\Tim\\workspace\\baseballStats\\files\\baseballRecords.txt";
	private static BufferedReader read;
	
	/***********************************************************
	 * No argument constructor
	 */
	baseballRecordReader()
	{
		baseballFileName = new String(defaultFile);
		String baseballFileNameTemp = new String(defaultFile + ".temp");
		File inputFile = new File(baseballFileName);
		File inputFileTemp = new File(baseballFileNameTemp);
		FileReader inputFileReader;
		
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
	}
	
	/****************************************************************
	 * Single argument constructor. User provided file name
	 * @param inputFileName
	 */
	baseballRecordReader(String inputFileName)
	{
		baseballFileName = new String(inputFileName);
		File inputFile = new File(baseballFileName);
		FileReader inputFileReader;
		
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
	 * ****BUG**** Can't get Scanner to work with Eclipse, 
	 * 	so currently BufferedReader is deleting records from file as it reads ******* 
	 * 
	 * @return baseballRecord
	 */
	public baseballRecord readNextRecord()
	{	
		baseballRecord result = null;
		String nextRow = new String();
		String [] playerMetrics;
		
		try{
			if ((nextRow = read.readLine()) != null)
			{
				playerMetrics = nextRow.split(",");
				result = new baseballRecord(Integer.parseInt(playerMetrics[0]), playerMetrics[1], playerMetrics[2], 
						Integer.parseInt(playerMetrics[3]),Integer.parseInt(playerMetrics[4]), 
						Double.parseDouble(playerMetrics[5]));
			}
		} catch (IOException e)
		{
			System.err.println("Failed to read next record from file");
		}
		
			return result;
	}
	/************************************************************
	 * Print entire file one line at a time
	 */
	public void read()
	{
		baseballRecord nextRecord = new baseballRecord();

		//Print headers
		System.out.println("Num\tFName\tLName\tHits\tAB\tAvg");
		nextRecord = this.readNextRecord();
		while (nextRecord != null)
		{
			System.out.println(nextRecord.toString());
			nextRecord = this.readNextRecord();
		}
	} 
}
