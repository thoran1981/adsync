import java.io.*;

public class baseballRecord {
	private int number;
	private String firstName;
	private String lastName;
	private int hits;
	private int atBats;
	private double battingAverage;
	int unInitializedNumber = -1;
	
	/**********************************************
	 * No param constructor
	 */
	baseballRecord()
	{
		number = unInitializedNumber;
		firstName = "";
		lastName = "";
		hits = 0;
		atBats = 0;
		setBA();
	}
	
	/************************************************
	 * Qualified constructor minus BA
	 * 
	 * @param inputNumber
	 * @param inputFirstName
	 * @param inputLastName
	 * @param inputHits
	 * @param inputAtBats
	 */
	baseballRecord(int inputNumber, String inputFirstName, 
							String inputLastName, int inputHits,
							int inputAtBats) 
	{
		number = inputNumber;
		firstName = inputFirstName;
		lastName = inputLastName;
		hits = inputHits;
		atBats = inputAtBats;
		// Calculate BA based on hits and at-bats
		setBA();
	}

	/************************************************
	 * Fully qualified constructor
	 * 
	 * @param inputNumber
	 * @param inputFirstName
	 * @param inputLastName
	 * @param inputHits
	 * @param inputAtBats
	 * @param inputBattingAverage
	 */
	baseballRecord(int inputNumber, String inputFirstName, 
							String inputLastName, int inputHits,
							int inputAtBats, double inputBattingAverage) 
	{
		number = inputNumber;
		firstName = inputFirstName;
		lastName = inputLastName;
		hits = inputHits;
		atBats = inputAtBats;
		// Even if BA is provided, calculate the value
		setBA();
	}
	
	/*************************************************
	 * Set First Name
	 * @param fName
	 */
	public void setFirstName(String fName)
	{
		firstName = fName;
	}
	
	/*************************************************
	 * Set Last Name
	 * @param lName
	 */
	public void setLastName(String lName)
	{
		lastName = lName;
	}
	
	/*************************************************
	 * Set number of hits
	 * @param ab
	 */
	public void setHits(int hit)
	{
		hits = hit;
		setBA();
	}
	
	/*************************************************
	 * Increment hits by inc
	 * @param inc
	 */
	public void incrementHits(int inc)
	{
		hits += inc;
		setBA();
	}
	
	/*************************************************
	 * Set number of at bats
	 * @param ab
	 */
	public void setAB(int ab)
	{
		atBats = ab;
		setBA();
	}
	
	/*************************************************
	 * Increment at bats by inc
	 * @param ab
	 */
	public void incrementAB(int inc)
	{
		atBats += inc;
		setBA();
	}
	
	/*************************************************
	 * Batting Average can not be explicitly set
	 * Calculated each time hits or AB are updated
	 */
	public void setBA()
	{
		// if hits and atBats are both 0, avoid division by 0 failure 
		if ((hits <= 0) && (atBats <= 0))
		{
			battingAverage = 0.0;
		}
		else
		{
			battingAverage = hits/atBats;
		}
		
		// Batting average can not be greater than 1.000
		// Set average to 1.0 and hits equal to at-bats
		if(battingAverage >= 1.0)
		{
			battingAverage = 1.0;
			hits = atBats;
		}
	}
	
	/*************************************************
	 * @returns number
	 */
	public int getNumber()
	{
		return this.number;
	}
	
	/*************************************************
	 * @returns firstName
	 */
	public String getFirstName()
	{
		return this.firstName;
	}
	
	/*************************************************
	 * @returns lastName
	 */
	public String getLastName()
	{
		return this.lastName;
	}
	
	/*************************************************
	 * @returns hits
	 */
	public int getHits()
	{
		return this.hits;
	}
	
	/*************************************************
	 * @returns atBats
	 */
	public int getAB()
	{
		return this.atBats;
	}
	
	/*************************************************
	 * @returns battingAverage
	 */
	public double getBA()
	{
		return this.battingAverage;
	}
	
	/*************************************************
	 * @returns player record as strin
	 */
	public String toString()
	{
		return String.valueOf(number) + '\t' + firstName + "\t\t" + lastName + "\t\t" + 
				String.valueOf(hits) + '\t'	+ String.valueOf(atBats) +
				'\t' + String.valueOf(battingAverage);
	}
}
