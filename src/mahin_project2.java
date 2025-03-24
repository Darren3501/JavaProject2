import java.util.*;
import java.io.*;

//Darren Mahin
//N01611733
//March 15, 2025
public class mahin_project2 
{
	/**
	 * This program records fish catches, determines records for the heaviest fish,
	 * and allows users to save and load data from files.
	 */
	public static void main(String[] args) 
	{
		//Declarations
		Scanner in = new Scanner(System.in);
		ArrayList<Catch> newCatch = new ArrayList<>();
		String fileName = "";
		File file;
		
		//Prompt user for file name. If file doesn't exist, ask to start from scratch or retry file name.
		while(true)
		{
			System.out.println("Enter the name of the data file");
			fileName = in.nextLine();
			file = new File(fileName);
			
			if (file.exists()) //file found on system
			{
				//Load existing data
				readDataFromFile(fileName, newCatch);
	            break;
	        } 
			else //file not found
	        {
	            System.out.print("File not found. Start new file? (Y/N): ");
	            if (in.nextLine().equalsIgnoreCase("Y")) 
	            {
	                break;
	            }
	        }
		}
		
			//Main menu loop
			while(true)
			{
				try 
				{
					printMenu(); //prompt user with choices
					int answer = in.nextInt();
					switch(answer)
					{
						case 1: recordNewCatch(newCatch); //add catch to file
								break;
						case 2: determineFishRecords(newCatch); //find heaviest fish from each species
								break;
						case 3: printDataToScreen(newCatch); //print data from file
								break;
						case 4: saveAndQuit(newCatch); //save data to file and exit program
								return; //end loop
						
						default: System.out.println("Invalid input!");
					}
				}
				
				catch(Exception e)
				{
					System.out.println("Invalid Input! Enter a valid number.");
					in.nextLine(); //prevent infinite loop
				}
			}
	}
	
	/**
     * Displays the main menu options for the user.
     */
	public static void printMenu()
	{
		System.out.println("What would you like to do?\n"
							+ "1 = Record New Catch\n"
							+ "2 = Determine Fish Records\n"
							+ "3 = Print All Data to Screen\n"
							+ "4 = Save and Quit");
	}
	
	/**
     * Records a new fish catch by prompting the user for details.
     * @param c List of current catches.
     */
	public static void recordNewCatch(ArrayList<Catch> c)
	{
		//Declarations
		Scanner in = new Scanner(System.in);
		float weight = 0;
		int length = 0;
		int year = 0;
		int month = 0;
		int day = 0;
		
		//Get angler license
		System.out.println("Which angler caught the fish? Enter their license.");
		String license = in.nextLine(); 
			
		//Get fish name
		System.out.println("What was the fish species?");
		String name = in.nextLine(); 
			
		//Get fish weight
		while(true)
		{
			try
			{
				System.out.println("What was the weight?");
				weight = in.nextFloat(); 
				
				//Check if positive
				if(weight > 0)
				{
					break;
				}
				else
				{
					System.out.println("Weight must be positive.");
					in.nextLine();
				}
			}
			catch(Exception e)
			{
				System.out.println("Invalid input! Enter a valid number.");
				in.nextLine();
			}
		}
			
		//Get fish length
		while(true)
		{
			try
			{
				System.out.println("What was the length?");
				length = in.nextInt(); 
				
				//Check if positive
				if(length > 0)
				{
					break;
				}
				else
				{
					System.out.println("Length must be positive.");
					in.nextLine();
				}
			}
			catch(Exception e)
			{
				System.out.println("Invalid input! Enter a valid number.");
				in.nextLine();
			}
		}
			
		//Get catch year
		while(true)
		{
			try
			{
				System.out.println("What was the year?");
				year = in.nextInt(); 
				break;
			}
			catch(Exception e)
			{
				System.out.println("Invalid input! Enter a valid number.");
				in.nextLine();
			}
		}
			
		//Get catch month
		while(true)
		{
			try 
			{
				System.out.println("What was the month (number)?");
				month = in.nextInt(); 
				
				if(month <= 12 && month > 0)
				{
					break;
				}
				else
				{
					System.out.println("Please enter a number between 1 and 12.");
					in.nextLine();
				}
			}
			catch(Exception e)
			{
				System.out.println("Invalid input! Enter a valid number.");
				in.nextLine();
			}
		}
			
		//Get catch day
		while(true)
		{
			try
			{
				System.out.println("What was the day (number)?");
				day = in.nextInt(); 
				
				if(day <= 31 && day > 0)
				{
					break;
				}
				else
				{
					System.out.println("Please enter a number between 1 and 31.");
					in.nextLine();
				}
			}
			catch(Exception e)
			{
				System.out.println("Invalid input! Enter a valid number.");
				in.nextLine();
			}
		}
			//add data from user into object instance
			Catch newcatch = new Catch(license, name, weight, length, year, month, day); 
			
			//add data to catch list
			c.add(newcatch); 
	}
	 /**
     * Reads fish catch data from a file and loads it into the program.
     * @param fileName Name of the file to read from.
     * @param c List where the data will be stored.
     * @return The updated list of catches.
     */
	public static ArrayList<Catch> readDataFromFile(String fileName, ArrayList<Catch> c) 
	{
		File file = new File(fileName);
		
		try
		{
			Scanner readFile = new Scanner(file); //read data from file
			String header = readFile.nextLine(); //consume header line
			
			while(readFile.hasNext())
			{
				String line = readFile.nextLine(); //consume next line for processing 
				String[] data = line.split(","); //split each string 
				
				//Load data into respective variables
				String license = data[0]; 
				String species = data[1]; 
				float weight = Float.valueOf(data[2]); 
				int length = Integer.valueOf(data[3]);
				int year = Integer.valueOf(data[4]);
				int month = Integer.valueOf(data[5]);
				int day = Integer.valueOf(data[6]);
				
				//add data from file into object instance
				Catch newCatch = new Catch(license, species, weight, length, year, month, day); 
				
				//add data to catch list
				c.add(newCatch);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error reading file");
		}
		
		return c;
	}
	/**
     * Determines the heaviest fish of each species and displays the record.
     * If multiple fish have the same weight, the one caught first is selected.
     * @param c List of recorded catches.
     */
	public static void determineFishRecords(ArrayList<Catch> c)
	{
		//Declarations
		ArrayList<String> checkedSpecies = new ArrayList<>();
		String species = "";
		Catch heaviestCatch = null;
		
		for(Catch catches : c)
		{
			species = catches.getName(); //get species name 
			
			if(checkedSpecies.contains(species)) //if species already checked skip
			{
				continue;
			}
		
			heaviestCatch = catches; //assume first occurence of species is heaviest 
			
			for(Catch fish : c)
			{
				//if current fish has higher weight than previous heaviest, set heaviest to current fish's weight
				if(fish.getName().equals(species) && fish.getWeight() > heaviestCatch.getWeight())
				{
					heaviestCatch = fish; //update heaviest 
				}
				//If there is a tie in weights, keep the one that occurs first
				else if(fish.getWeight() == heaviestCatch.getWeight())
				{
					continue; //do nothing, keep first occurring catch
				}
			}
		
		 System.out.println("Biggest " + species + " had weight " + heaviestCatch.getWeight() +
	                " and length " + heaviestCatch.getLength() + " and was caught by " + heaviestCatch.getLicense() +
	                " on " + heaviestCatch.getCatchYear() + "/" + heaviestCatch.getCatchMonth() + "/" + heaviestCatch.getCatchDay());
		
		 checkedSpecies.add(species); //add species to checked list
		
		}
	}
	/**
     * Prints all recorded fish catch data to the screen.
     * @param c List of recorded catches.
     */
	public static void printDataToScreen(ArrayList<Catch> c)
	{
		for(Catch catches : c)
		{
			System.out.println(catches);
		}
	}
	/**
     * Saves the recorded catches to a file and exits the program.
     * @param c List of recorded catches.
     */
	public static void saveAndQuit(ArrayList<Catch> c)
	{
		String fileName = "";
		Scanner in = new Scanner(System.in);
		
		//Ask user for file name to save data to
		System.out.println("Enter name for file");
		fileName = in.nextLine();
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
		{
			writer.write("License,Species,Weight,Length,Year,Month,Day"); //write header to file
			writer.newLine();
			
			//Write data to file
			for(Catch fish : c)
			{
				writer.write(fish.getLicense() + "," +
							 fish.getName() + "," + 
							 fish.getWeight() + "," + 
							 fish.getLength() + "," +
							 fish.getCatchYear() + "," + 
							 fish.getCatchMonth() + "," +
							 fish.getCatchDay() + ",");
				writer.newLine();
				
			}
		}
		catch(IOException e)
		{
			System.out.println("Error saving data: ");
		}
		
		System.out.println("Data successfully written to file!");
	}
}