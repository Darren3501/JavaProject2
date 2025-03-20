import java.util.*;
import java.io.*;
public class mahin_project2 
{

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		ArrayList<Catch> newCatch = new ArrayList<>();
		String fileName = "";
		File file;
		
		while(true)
		{
			System.out.println("Enter the name of the data file");
			fileName = in.nextLine();
			file = new File(fileName);
			
			if (file.exists()) 
			{
	            readDataFromFile(fileName, newCatch);
	            break;
	        } 
			else 
	        {
	            System.out.print("File not found. Start new file? (Y/N): ");
	            if (in.nextLine().equalsIgnoreCase("Y")) 
	            {
	                break;
	            }
	        }
		}
		while(true)
		{
			printMenu();
			int answer = in.nextInt();
			switch(answer)
			{
				case 1: recordNewCatch(newCatch);
						break;
				case 2: determineFishRecords(newCatch);
						break;
				case 3: printDataToScreen(newCatch);
						break;
				case 4: saveAndQuit(newCatch);
						return;
				
				default: System.out.println("Invalid input!");
			}
		}
		
	}
	
	public static void printMenu()
	{
		System.out.println("What would you like to do?\n"
							+ "1 = Record New Catch\n"
							+ "2 = Determine Fish Records\n"
							+ "3 = Print All Data to Screen\n"
							+ "4 = Save and Quit");
	}
	
	
	
	
	public static void recordNewCatch(ArrayList<Catch> c)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Which angler caught the fish? Enter their license.");
		
		String license = in.nextLine();
		
		System.out.println("What was the fish species?");
		String name = in.nextLine();
		
		System.out.println("What was the weight?");
		float weight = in.nextFloat();
		
		System.out.println("What was the length?");
		int length = in.nextInt();
		
		System.out.println("What was the year?");
		int year = in.nextInt();
		
		System.out.println("What was the month (number)?");
		int month = in.nextInt();
		
		System.out.println("What was the day (number)?");
		int day = in.nextInt();
		
		Catch newcatch = new Catch(license, name, weight, length, year, month, day);
		c.add(newcatch);
		
	}
	
	public static ArrayList<Catch> readDataFromFile(String fileName, ArrayList<Catch> c) 
	{
		File file = new File(fileName);
		
		
		try
		{
			Scanner readFile = new Scanner(file);
			String header = readFile.nextLine();
			
			while(readFile.hasNext())
			{
				String line = readFile.nextLine();
				String[] data = line.split(",");
				
				String license = data[0];
				String species = data[1];
				float weight = Float.valueOf(data[2]);
				int length = Integer.valueOf(data[3]);
				int year = Integer.valueOf(data[4]);
				int month = Integer.valueOf(data[5]);
				int day = Integer.valueOf(data[6]);
				Catch newCatch = new Catch(license, species, weight, length, year, month, day);
				c.add(newCatch);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error reading file");
		}
		
		return c;
	}
	
	public static void determineFishRecords(ArrayList<Catch> c)
	{
		ArrayList<String> checkedSpecies = new ArrayList<>();
		String species = "";
		Catch heaviestCatch = null;
		
		for(Catch catches : c)
		{
			species = catches.getName();
			
			if(checkedSpecies.contains(species)) 
			{
				continue;
			}
		
			heaviestCatch = catches;
			for(Catch fish : c)
			{
				if(fish.getName().equals(species) && fish.getWeight() > heaviestCatch.getWeight())
				{
					heaviestCatch = fish;
				}
			}
		
		
		
		 System.out.println("Biggest " + species + " had weight " + heaviestCatch.getWeight() +
	                " and length " + heaviestCatch.getLength() + " and was caught by " + heaviestCatch.getLicense() +
	                " on " + heaviestCatch.getCatchYear() + "/" + heaviestCatch.getCatchMonth() + "/" + heaviestCatch.getCatchDay());
		
		 checkedSpecies.add(species);
		
		}
		
	}
	
	public static void printDataToScreen(ArrayList<Catch> c)
	{
		for(Catch catches : c)
		{
			System.out.println(catches);
		}
	}
	
	public static void saveAndQuit(ArrayList<Catch> c)
	{
		String fileName = "";
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter name for file");
		fileName = in.nextLine();
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
		{
			writer.write("License,Species,Weight,Length,Year,Month,Day");
			writer.newLine();
			
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
	}
	
	
}