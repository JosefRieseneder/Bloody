package at.fhooe.mhs.bloody.personalData;

public class PersonalData {

	private String id;
	private String contactEmail;
	/**
	 * Weight in kilograms.
	 */
	private int weight;
	/**
	 * Age in years.
	 */
	private int age;
	/**
	 * Latitude and longitude of the location the user entered.
	 */
	private double locationLat, locationLon;
	/**
	 * String representation of the location the user entered.
	 */
	private String location;
	private Gender gender;

	public int getAge() {
		return age;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public double getLocationLat() {
		return locationLat;
	}

	public double getLocationLon() {
		return locationLon;
	}

	public int getWeight() {
		return weight;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}

	public void setLocationLon(double locationLon) {
		this.locationLon = locationLon;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
