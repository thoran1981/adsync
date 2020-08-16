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
	
	/**************************************************************************
	 * 
	 * Main Method
	 */
	public static void main(String [] args)
	{
		baseballRecordReader defaultReader = new baseballRecordReader();
		baseballRecordWriter defaultWriter = new baseballRecordWriter();
		BufferedReader optionInput = new BufferedReader(new InputStreamReader(System.in));
		String options = new String ("1. Print Team\n2. Update First Name\n3. Update Last Name\n" +
										"4. Update Hits\n5. Update AB\n0. Quit");
		int option=0;
		int playerNum=0;
		int hits = 0;
		int AB = 0;
		String FName;
		String LName;
		baseballRecord playerSearch;
		
		do {
			System.out.println("Choose One:");
			System.out.println(options);
			try {
				option = Integer.parseInt(optionInput.readLine());
			} catch (IOException i)
			{
				System.out.println("Could not parse input");
				break;
			}
			
			switch(option) {
				case 1: 	System.out.println("\n\n");
							defaultReader.read();
							System.out.println("\n\n");
							break;
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
							playerSearch = getPlayerRecord(playerNum, defaultReader);
							if(playerSearch.getNumber() == playerNum)
							{
								playerSearch.setFirstName(FName);
								defaultWriter.write(playerSearch);
							}
							System.out.println("Update Complete");
							break;
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
							playerSearch = getPlayerRecord(playerNum, defaultReader);
							if(playerSearch.getNumber() == playerNum)
							{
								playerSearch.setLastName(LName);
								defaultWriter.write(playerSearch);
							}
							System.out.println("Update Complete");
							break;
				case 4: 	System.out.println("\n\nEnter Player Number: ");
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
							playerSearch = getPlayerRecord(playerNum, defaultReader);
							if(playerSearch.getNumber() == playerNum)
							{
								playerSearch.incrementHits(hits);
								defaultWriter.write(playerSearch);
							}
							System.out.println("Update Complete");
							break;
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
							playerSearch = getPlayerRecord(playerNum, defaultReader);
							if(playerSearch.getNumber() == playerNum)
							{
								playerSearch.incrementAB(AB);
								defaultWriter.write(playerSearch);
							}
							System.out.println("Update Complete");
							break;
				case 0:		System.out.println("\n\nPLAY BALL!!\n\n");
							break;
				default: 	System.out.println("Invalid input - something went wrong\n\n");
							break;
			}
		} while (option != 0);
	
	}	
}