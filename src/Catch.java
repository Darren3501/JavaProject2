
public class Catch 
{
	private String license;
	private String name;
	private float weight;
	private int length;
	private int catchDay;
	private int catchMonth;
	private int catchYear;
	
	public Catch()
	{
		
	}
	
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
	
	public String toString()
	{
		return "Angler " + license + " caught a " + name + ": " + weight + "lbs, " + length + "inches, on " + catchYear + "/" + catchMonth + "/" + catchDay;
	}
}
