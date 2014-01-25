

public class User{
	private String id;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String profile;
	private String school;
	private String major;
	public User(String id, String password, String firstName, String lastName, String phone, String profile, String school, String major){
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.profile = profile;
		this.school = school;
		this.major = major;
	}
	public String getId(){
		return id;
	}
	public String getPassword(){
		return password;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public String getPhone(){
		return phone;
	}
	public String getProfile(){
		return profile;
	}
	public String getSchool(){
		return school;
	}
	public String getMajor(){
		return major;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public void setProfile(String profile){
		this.profile = profile;
	}
	public void setSchool(String school){
		this.school = school;
	}
	public void setMajor(String major){
		this.major = major;
	}
}