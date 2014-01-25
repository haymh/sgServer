import java.sql.*;
import java.util.*;


public class Database
{
  private Connection c;
  Statement stmt;
  public Database() throws ClassNotFoundException, SQLException{
    Class.forName("org.sqlite.JDBC");
    c = DriverManager.getConnection("jdbc:sqlite:studyGroup.db");
    stmt = c.createStatement();
  }

  public int register(User user)
  {
    String sql = "INSERT INTO users (id,password,firstName,lastName,phone,profile,school,major) VALUES( '"
      + user.getId() + "' , '" + user.getPassword() + "' , '" 
      + user.getFirstName() + "' , '" + user.getLastName() + "' , '" 
      + user.getPhone()  + "' , '" + user.getProfile()  + "' , '" 
      + user.getSchool()  + "' , '" + user.getMajor()+ "' )";
    try {
      return stmt.executeUpdate(sql);
    } catch ( Exception e ) {
      e.printStackTrace();
      return 0;
    }
  }

  public int createGroup(Group group){
    String sql = "INSERT INTO groups (title,subject,description,address,addressAddition,date,duration,peopleIn,checkIn,school,creatorId) VALUES( '"
      + group.getTitle() + "' , '" + group.getSubject() + "' , '" 
      + group.getDescription() + "' , '" + group.getAddress() + "' , '" 
      + group.getAddressAddition() + "' , '" + group.getDate() + "' , '" 
      + group.getDuration() + "' , '" + group.getNumOfPeople() + "' , '" 
      + group.getNumOfCheckin() + "' , '" + group.getSchool() + "' , '"
      + group.getCreatorId() + "' )";
    try {
      return stmt.executeUpdate(sql);
    } catch ( Exception e ) {
      e.printStackTrace();
      return 0;
    } finally{
      try{
        stmt.close();
      }catch(Exception e){
        e.printStackTrace();
      }
      
    }
  }

  public ArrayList<Group> searchGroups(String school, String[] keywords){
    ArrayList<Group> groups = new ArrayList<Group>();
    String sql = "SELECT *, firstName, lastName FROM groups, users where (subject like '%";
    if(keywords.length > 0){
      sql += keywords[0];
      sql += "%' ";
    }
    for(int i = 1; i < keywords.length; i++){
      sql += "OR subject like '%";
      sql += keywords[i];
      sql += "%' ";
    }
    if(keywords.length > 0){
      sql += "OR description like '%";
      sql += keywords[0];
      sql += "%' ";
    }
    for(int i = 1; i < keywords.length; i++){
      sql += "OR description like '%";
      sql += keywords[i];
      sql += "%') ";
    }
    sql += "AND groups.school = '" + school + "' ";
    sql += " AND users.id = creatorId";
    try{
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        Group g = new Group();
        g.setId(rs.getInt(1));
        g.setTitle(rs.getString("title"));
        g.setSubject(rs.getString("subject"));
        g.setDescription(rs.getString("description"));
        g.setAddress(rs.getString("address"));
        g.setAddressAddition(rs.getString("addressAddition"));
        g.setDate(rs.getString("date"));
        g.setDuration(rs.getInt("duration"));
        g.setNumOfPeople(rs.getInt("peopleIn"));
        g.setNumOfCheckin(rs.getInt("checkIn"));
        g.setSchool(rs.getString(10));
        g.setCreatorId(rs.getString("creatorId"));
        String name = rs.getString(12) + " " + rs.getString(13);
        g.setCreatorName(name);
        groups.add(g);
      }
      return groups;
    } catch ( Exception e ) {
      e.printStackTrace();
      return null;
    } finally{
      try{
        stmt.close();
      }catch(Exception e){
        e.printStackTrace();
      }
      
    }
  }

  public ArrayList<Group> searchGroupsTest(String school, String[] keywords){
    ArrayList<Group> groups = new ArrayList<Group>();
    String sql = "SELECT DISTINCT groups.id, title, subject, description, address, addressAddition,date, duration, peopleIn, checkIn, groups.school,creatorId,firstName,lastName FROM groups, users where ";
    sql += "groups.school = '" + school + "' ";
    sql += " AND users.id = creatorId ";
    
    if(keywords.length > 0){
      sql += "AND (subject like '%";
      sql += keywords[0];
      sql += "%' ";
    }
    for(int i = 1; i < keywords.length; i++){
      sql += "OR subject like '%";
      sql += keywords[i];
      sql += "%' ";
    }
    if(keywords.length > 0){
      sql += "OR description like '%";
      sql += keywords[0];
      sql += "%' ";
    }
    for(int i = 1; i < keywords.length; i++){
      sql += "OR description like '%";
      sql += keywords[i];
      sql += "%'";
    }
    sql += ")";
    
    
    try{
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        Group g = new Group();
        g.setId(rs.getInt(1));
        g.setTitle(rs.getString("title"));
        g.setSubject(rs.getString("subject"));
        g.setDescription(rs.getString("description"));
        g.setAddress(rs.getString("address"));
        g.setAddressAddition(rs.getString("addressAddition"));
        g.setDate(rs.getString("date"));
        g.setDuration(rs.getInt("duration"));
        g.setNumOfPeople(rs.getInt("peopleIn"));
        g.setNumOfCheckin(rs.getInt("checkIn"));
        g.setSchool(rs.getString(11));
        g.setCreatorId(rs.getString("creatorId"));
        String name = rs.getString("firstName") + " " + rs.getString("lastName");
        g.setCreatorName(name);
        groups.add(g);
      }
      return groups;
    } catch ( Exception e ) {
      e.printStackTrace();
      return null;
    } finally{
      try{
        stmt.close();
      }catch(Exception e){
        e.printStackTrace();
      }
      
    }
  }

  public int isIdExist(String id)
  {
    String sql = "SELECT count(*) FROM USERS WHERE id = '" + id + "'";
    try{
      int count = -1;
      ResultSet rs = stmt.executeQuery(sql);
      if(rs.next())
          count = rs.getInt(1);
      return count;
    } catch ( Exception e ) {
      e.printStackTrace();
      return -1;
    }
  }

  public String getNickName(String id)
  {
    String sql = "SELECT NAME FROM USERS WHERE ID = '" + id + "'";
    try{
      ResultSet rs = stmt.executeQuery(sql);
      if(rs.next())
        return rs.getString(1);
      return "";
    } catch ( Exception e ) {
      e.printStackTrace();
      return "";
    }
  }

  public boolean login(String id, String password)
  {
    String sql = "SELECT count(*) FROM users WHERE id = '" + id + "' AND password = '" + password + "'";
    try{
      ResultSet rs = stmt.executeQuery(sql);
      if(rs.next())
      {
        if(rs.getInt(1) >= 1)
          return true;
      }
      return false;
    } catch ( Exception e ) {
      e.printStackTrace();
      return false;
    }
  }

  public void close()
  {
    try{
      c.close();
      stmt.close();
    }catch(SQLException e){
      e.printStackTrace();
    }
    
  }
}