import java.io.IOException;
import java.io.*;


public class baseballStats {
	
	private static String defaultFileName = new String ("baseballRecords.txt");
	
	/***************************************************************************
	 * Retrieve first name for specific player number
	 * 
	 * @param inputNumber
	 * @param inputReader
	 * @return
	 */
	public static baseballRecord getPlayerRecord(int inputNumber, baseballRecordReader inputReader) 
	{	
		baseballRecord searchResult = new baseballRecord();
		String resultFirstName = new String("*** Not Found ***");
		
		while ((searchResult = inputReader.readNextRecord()) != null) {
			if (searchResult.getNumber() == inputNumber)
			{
				resultFirstName = searchResult.getFirstName();
				break;
			}
		}
		
		if (searchResult == null)
		{
			searchResult = new baseballRecord();
		}
		
		return searchResult;
	}
	
	/*******************************************************
	 * Pull all records from database into an array
	 * @return
	 */
	public static baseballRecord [] readRecordsToArray()
	{
		baseballRecord [] records = new baseballRecord[100];
		baseballRecordReader reader = new baseballRecordReader();
		baseballRecord player = new baseballRecord();
		int i = 0;
		
		while ((player = reader.readNextRecord())!= null)
		{
			records[i] = new baseballRecord(player.getNumber(), player.getFirstName(),
											player.getLastName(), player.getHits(), player.getAB());
			i++;
		}
		
		return records;		
	}
	/************************************************************
	 * Writes contents of a baseballRecord array to the database
	 * @param records
	 */
	public static void writeRecordsFromArray(baseballRecord [] records)
	{
		baseballRecordWriter writer = new baseballRecordWriter();
		
		for (int i=0; i < records.length; i++)
		{
			writer.overWrite(records);
		}
	}
	
	/**************************************************************************
	 * 
	 * Main Method
	 */
	public static void main(String [] args)
	{
		baseballRecordReader defaultReader = new baseballRecordReader();
		baseballRecordWriter defaultWriter = new baseballRecordWriter();
		BufferedReader optionInput = new BufferedReader(new InputStreamReader(System.in));
		baseballRecord [] records = new baseballRecord[100];
		baseballRecord [] tempRecords = new baseballRecord[100]; 
		String options = new String ("1. Print Team\n2. Update First Name\n3. Update Last Name\n" +
										"4. Update Hits\n5. Update AB\n6. Add New Player\n" +
										"7. Delete Player\n0. Quit");
		int option=0;
		int playerNum=0;
		int hits = 0;
		int AB = 0;
		String FName;
		String LName;
		baseballRecord playerSearch;
		int unInitializedNumber = -1;
		String confirmChoice;
		int i,j;
		
		do {
			System.out.println("Choose One:");
			System.out.println(options);
			try {
				option = Integer.parseInt(optionInput.readLine());
			} catch (IOException io)
			{
				System.out.println("Could not parse input");
				break;
			}
			
			switch(option) {
			
				/***************************
				 * List Team Roster
				 */
				case 1: 	System.out.println("\n\n");
							defaultReader.read();
							System.out.println("\n\n");
							break;
							
				/***************************
				 * Update player last name
				 */
				case 2: 	System.out.println("\n\nEnter Player Number: ");
							try {
								playerNum = Integer.parseInt(optionInput.readLine());
							} catch (IOException e)
							{
								System.out.println("Unable to parse player number");
								break;
							}
							catch (NumberFormatException n)
							{
								System.out.println("Incorrectly formatted number");
								break;
							}
							System.out.println("\n\nEnter Player First Name: ");
							try {
								FName = optionInput.readLine();
							} catch (IOException e)
							{
								System.out.println("Unable to parse player name");
								break;
							}
							// Find pertinent player record and update first name
							records = readRecordsToArray();
							playerSearch = getPlayerRecord(playerNum, new baseballRecordReader());
							if(playerSearch.getNumber() == playerNum)
							{
								playerSearch.setFirstName(FName);
								defaultWriter.write(playerSearch);
							}
							// Update player in records
							for (i=0;i<records.length;i++)
							{
								if(records[i] == null)
								{
									break;
								}
								if(records[i].getNumber() == playerSearch.getNumber())
								{
									records[i] = playerSearch;
									break;
								}
							}
							writeRecordsFromArray(records);
							
							// Confirm if update succeeded
							if(records[i] == null)
							{
								System.out.println("Player Not Found");
							}
							else
							{
								System.out.println("Update Complete\n");
							}
							break;
							
				/***************************
				 * Update player last name
				 */
				case 3: 	System.out.println("\n\nEnter Player Number: ");
							try {
								playerNum = Integer.parseInt(optionInput.readLine());
							} catch (IOException e)
							{
								System.out.println("Unable to parse player number");
								break;
							}
							catch (NumberFormatException n)
							{
								System.out.println("Incorrectly formatted number");
								break;
							}
							System.out.println("\n\nEnter Player Last Name: ");
							try {
								LName = optionInput.readLine();
							} catch (IOException e)
							{
								System.out.println("Unable to parse player name");
								break;
							}
							// Find pertinent player record and update first name
							records = readRecordsToArray();
							playerSearch = getPlayerRecord(playerNum, new baseballRecordReader());
							if(playerSearch.getNumber() == playerNum)
							{
								playerSearch.setLastName(LName);
								defaultWriter.write(playerSearch);
							}
							// Update player in records
							for (i=0;i<records.length;i++)
							{
								if(records[i] == null)
								{
									break;
								}
								if(records[i].getNumber() == playerSearch.getNumber())
								{
									records[i] = playerSearch;
									break;
								}
							}
							writeRecordsFromArray(records);
							
							// Confirm if update succeeded
							if(records[i] == null)
							{
								System.out.println("Player Not Found");
							}
							else
							{
								System.out.println("Update Complete\n");
							}
							break;
							
				/************************************
				 * Increment Player Hits
				 */
				case 4:		System.out.println("\n\nEnter Player Number: ");
							try {
								playerNum = Integer.parseInt(optionInput.readLine());
							} catch (IOException e)
							{
								System.out.println("Unable to parse player number");
								break;
							}
							catch (NumberFormatException n)
							{
								System.out.println("Incorrectly formatted number");
								break;
							}
							System.out.println("\n\nEnter New Player Hits: ");
							try {
								hits = Integer.parseInt(optionInput.readLine());
							} catch (IOException e)
							{
								System.out.println("Unable to parse player hits");
								break;
							}
							// Find pertinent player record and update first name
							records = readRecordsToArray();
							playerSearch = getPlayerRecord(playerNum, new baseballRecordReader());
							if(playerSearch.getNumber() == playerNum)
							{
								playerSearch.incrementHits(hits);
								defaultWriter.write(playerSearch);
							}
							// Update player in records
							for (i=0;i<records.length;i++)
							{
								if(records[i] == null)
								{
									break;
								}
								if(records[i].getNumber() == playerSearch.getNumber())
								{
									records[i] = playerSearch;
									break;
								}
							}
							writeRecordsFromArray(records);
							
							// Confirm if update succeeded
							if(records[i] == null)
							{
								System.out.println("Player Not Found");
							}
							else
							{
								System.out.println("Update Complete\n");
							}
							break; 	
					
				/***************************************
				 * Increment Player At Bats
				 */
				case 5: 	System.out.println("\n\nEnter Player Number: ");
							try {
								playerNum = Integer.parseInt(optionInput.readLine());
							} catch (IOException e)
							{
								System.out.println("Unable to parse player number");
								break;
							}
							catch (NumberFormatException n)
							{
								System.out.println("Incorrectly formatted number");
								break;
							}
							System.out.println("\n\nEnter New Player At Bats: ");
							try {
								AB = Integer.parseInt(optionInput.readLine());
							} catch (IOException e)
							{
								System.out.println("Unable to parse player at bats");
								break;
							}
							// Find pertinent player record and update first name
							records = readRecordsToArray();
							playerSearch = getPlayerRecord(playerNum, new baseballRecordReader());
							if(playerSearch.getNumber() == playerNum)
							{
								playerSearch.incrementAB(AB);
								defaultWriter.write(playerSearch);
							}
							// Update player in records
							for (i=0;i<records.length;i++)
							{
								if(records[i] == null)
								{
									break;
								}
								if(records[i].getNumber() == playerSearch.getNumber())
								{
									records[i] = playerSearch;
									break;
								}
							}
							writeRecordsFromArray(records);
							
							// Confirm if update succeeded
							if(records[i] == null)
							{
								System.out.println("Player Not Found");
							}
							else
							{
								System.out.println("Update Complete\n");
							}
							break;
							
				/***************************************
				 * Add New Player To Roster			
				 */
				case 6:		System.out.println("\n\nEnter Player Number: ");
							try {
								playerNum = Integer.parseInt(optionInput.readLine());
							} catch (IOException e)
							{
								System.out.println("Unable to parse player number");
								break;
							}
							catch (NumberFormatException n)
							{
								System.out.println("Incorrectly formatted number");
								break;
							}
							
							playerSearch = getPlayerRecord(playerNum, new baseballRecordReader());
							if(playerSearch.getNumber() == playerNum)
							{
								System.out.println("\nNumber already taken by " 
										+ playerSearch.getFirstName() + " " + playerSearch.getLastName() + "\n\n");
								break;
							}
							else
							{
								//Populate first name
								System.out.println("\n\nEnter Player First Name: ");
								try {
									FName = optionInput.readLine();
								} catch (IOException e)
								{
									System.out.println("Unable to parse player name");
									break;
								}
								// Populate last name
								System.out.println("\n\nEnter Player Last Name: ");
								try {
									LName = optionInput.readLine();
								} catch (IOException e)
								{
									System.out.println("Unable to parse player name");
									break;
								}
								// Populate number of hits
								System.out.println("\n\nEnter Total Hits: ");
								try {
									hits = Integer.parseInt(optionInput.readLine());
								} catch (IOException e)
								{
									System.out.println("Unable to parse player hits");
									break;
								}
								//Populate at bats
								System.out.println("\n\nEnter Total At Bats: ");
								try {
									AB = Integer.parseInt(optionInput.readLine());
								} catch (IOException e)
								{
									System.out.println("Unable to parse player at bats");
									break;
								}
								// Generate new player record
								baseballRecord newPlayer = new baseballRecord(playerNum,FName,LName,hits,AB);
								// Add new player to file
								defaultWriter.write(newPlayer);
							}
							break;
				
				/*************************************
				 * Delete Player From Roster
				 */
				case 7:		System.out.println("\n\nEnter Player Number: ");
							try {
								playerNum = Integer.parseInt(optionInput.readLine());
							} catch (IOException e)
							{
								System.out.println("Unable to parse player number");
								break;
							}
							catch (NumberFormatException n)
							{
								System.out.println("Incorrectly formatted number");
								break;
							}
							
							playerSearch = getPlayerRecord(playerNum, new baseballRecordReader());
							if(playerSearch.getNumber() == -1)
							{
								System.out.println("\nPlayer does not exist\n");
								break;
							}
							else
							{
								System.out.println("\nPlease confirm deletion of " + playerSearch.getFirstName() +
										" " + playerSearch.getLastName() + " (y/n): ");
								do{
									try {
										confirmChoice = optionInput.readLine();
									} catch (IOException io)
									{
										System.out.println("Could not parse input");
										break;
									}
									if(confirmChoice.toLowerCase().equals("y") ||
											confirmChoice.toLowerCase().equals("yes"))
									{
										// Find pertinent player record and delete
										records = readRecordsToArray();
										i=0;
										j=0;
										tempRecords = new baseballRecord [100];
										while(records[i] != null)
										{
											// Move index up one to skip deleted record
											if(records[i].getNumber() == playerSearch.getNumber())
											{
												i++;
											}
											tempRecords[j] = records[i];				
											i++;
											j++;
										}
										
										// write records to file
										writeRecordsFromArray(tempRecords);
									}
									else if ((confirmChoice.toLowerCase().equals("n")) ||
												confirmChoice.toLowerCase().equals("no"))
									{
										break;
									}
									else
									{
										System.out.println("\nInvalid entry. Enter (y/n): "); 
									}
								} while (!(confirmChoice.toLowerCase().equals("y")) &&
										!(confirmChoice.toLowerCase().equals("yes")));
							}
							break;
				/*************************************
				 * Quit application
				 */
				case 0:		System.out.println("\n\nPLAY BALL!!\n\n");
							break;
				default: 	System.out.println("Invalid input - something went wrong\n\n");
							break;
			}
		} while (option != 0);
	
	}	
}