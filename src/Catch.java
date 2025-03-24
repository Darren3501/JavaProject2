/**
 * The Catch class represents a fish caught by an angler.
 * It stores details such as angler's license, fish species,
 * weight, length, and the date of the catch.
 */
public class Catch 
{
	private String license; //Angler's license number
	private String name; //Fish species name
	private float weight; //Weight of the fish in pounds
	private int length; //Length of the fish in inches
	private int catchDay; //Day the fish was caught
	private int catchMonth; //Month the fish was caught
	private int catchYear; //Year the fish was caught
	
	/**
     * Default constructor.
     */
	public Catch()
	{
		
	}
	/**
     * Constructor to initialize all attributes.
     * @param license Angler's license number
     * @param name Fish species name
     * @param weight Weight of the fish in pounds
     * @param length Length of the fish in inches
     * @param catchYear Year the fish was caught
     * @param catchMonth Month the fish was caught
     * @param catchDay Day the fish was caught
     */
	public Catch(String license, String name, float weight, int length, int catchYear, int catchMonth, int catchDay)
	{
		this.license = license;
		this.name = name;
		this.weight = weight;
		this.length = length;
		this.catchDay = catchDay;
		this.catchMonth = catchMonth;
		this.catchYear = catchYear;
				
	}
	
	//Getters and Setters
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCatchDay() {
		return catchDay;
	}
	
	public void setCatchDay(int catchDay) {
		this.catchDay = catchDay;
	}
	
	public int getCatchMonth() {
		return catchMonth;
	}
	
	public void setCatchMonth(int catchMonth) {
		this.catchMonth = catchMonth;
	}
	
	public int getCatchYear() {
		return catchYear;
	}
	 
	public void setCatchYear(int catchYear) {
		this.catchYear = catchYear;
	}
	 /**
     * Returns a string representation of the Catch object.
     * @return Formatted catch details
     */
	public String toString()
	{
		return "Angler " + license + " caught a " + name + ": " + weight + "lbs, " + length + "inches, on " + catchYear + "/" + catchMonth + "/" + catchDay;
	}
}
