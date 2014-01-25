

public class Group{
	private int id;
	private String title;
	private String subject;
	private String description;
	private String address;
	private String addresAddition;
	private String date; // e.g 2010-12-30 12:10
	private int duration; // in minute
	private int numOfPeople;
	private int numOfCheckin;
	private String school;
	private String creatorId;
	private String creatorName;

	public Group(){

	}

	public Group(int id, String title, String subject, String description, String address, String addresAddition, 
		String date, int duration, int numOfPeople, int numOfCheckin, String school, String creatorId,
		String creatorName){
		this.id = id;
		this.title = title;
		this.subject = subject;
		this.description = description;
		this.address = address;
		this.addresAddition = addresAddition;
		this.date = date;
		this.duration = duration;
		this.numOfPeople = numOfPeople;
		this.numOfCheckin = numOfCheckin;
		this.school = school;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
	}
	public int getId(){
		return id;
	}
	public String getTitle(){
		return title;
	}
	public String getSubject(){
		return subject;
	}
	public String getDescription(){
		return description;
	}
	public String getAddress(){
		return address;
	}
	public String getAddressAddition(){
		return addresAddition;
	}
	public String getSchool(){
		return school;
	}
	public String getDate(){
		return date;
	}
	public int getDuration(){
		return duration;
	}
	public int getNumOfPeople(){
		return numOfPeople;
	}
	public int getNumOfCheckin(){
		return numOfCheckin;
	}
	public String getCreatorId(){
		return creatorId;
	}
	public String getCreatorName(){
		return creatorName;
	}

	public void setId(int id){
		this.id = id;
	}
		
	public void setTitle(String title){
		this.title = title;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setAddressAddition(String addresAddition){
		this.addresAddition = addresAddition;
	}
	public void setSchool(String school){
		this.school = school;
	}
	public void setDate(String date){
		this.date = date;
	}
	public void setDuration(int duration){
		this.duration = duration;
	}
	public void setNumOfPeople(int numOfPeople){
		this.numOfPeople = numOfPeople;
	}
	public void setNumOfCheckin(int numOfCheckin){
		this.numOfCheckin = numOfCheckin;
	}
	public void setCreatorId(String creatorId){
		this.creatorId = creatorId;
	}
	public void setCreatorName(String creatorName){
		this.creatorName = creatorName;
	}
}