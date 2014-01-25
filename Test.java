import java.util.*;
public class Test{
	public static void main(String[] args){
		
		try{
			Database db = new Database();
			//System.out.println("AAAAAAAAAAAAAAAAA");
			//db.register(new User("id", "password", "String firstName", "String lastName", "String phone", "String profile", "String school", "String major"));
			//db.createGroup(new Group(0, "title", "subject", "String description", "String address", "String addresAddition", "String date", 120, 32, 0, "String school","id","creator lastName"));
			//System.out.println("BBBBBBBBBBBBBBBBB");
			//db.close();
			ArrayList<Group> groups = db.searchGroupsTest("String school",new String[]{"str","sub"});
			for(int i = 0; i < groups.size(); i++){
				Group group = groups.get(i);
				System.out.println("Record " + i + " group ID" + "    " + "title" + "     " + "creator");
				System.out.println(group.getId() + "    " + group.getTitle() + "    " + group.getCreatorName());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		

	}
}