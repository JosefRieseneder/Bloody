package at.fhooe.mhs.bloody.personalData;

import java.io.Serializable;
import java.util.Date;

public class PersonalData implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8483647724384199894L;
	/**
	 * User medical id = SozialversicherungsNr.
	 */
	private String id;
	/**
	 * Mail of doctor or a person that uses the blood pressuer data
	 */
	private String contactEmail;
	
	/**
	 * Date of birth
	 */
	private Date dateOfBirth;
	/**
	 * Weight in kilograms.
	 */
	private int weight;
	
	/**
	 * Height in cm
	 */
	private int height;
	
	/**
	 * Gender of person
	 */
	private Gender gender;
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

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
