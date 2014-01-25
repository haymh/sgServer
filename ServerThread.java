import java.net.*;
import java.io.*;
import java.util.*;

public class ServerThread extends Thread{
	final static int LOGIN_FAIL = 0;
	final static int LOGIN_SUCCESS = 1;
	final static int NO_DATA = -1;
	private Socket socket;
	private Database db;
	private PrintWriter out;
	public ServerThread(Socket socket, Database db){
		super("ServerThread");
		this.socket = socket;
		this.db = db;
		out = null;
	}

	public void run(){
		String id = null;
		String password = null;
		String school = null;
		try(
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					socket.getInputStream())))
		{
			out = new PrintWriter(socket.getOutputStream(), true);
			String inputLine = in.readLine();
			if(inputLine == null)
				return;
			String[] s = inputLine.split(" ");
			id = s[0];
			password = s[1];
			school = s[2];
			System.out.println("verifying " + id);
			//verify user id and password
			if(!db.login(id, password))
			{
				//fails verification then send code -1
				System.out.println("could not verify user: " + id + " with password " + password);
				send(LOGIN_FAIL);
				return ;
			}
			//login successfully
			send(LOGIN_SUCCESS);
			System.out.println(id + " is connected.");
			/*
			inputLine = in.readLine();
			if(inputLine == null){
				send(NO_DATA);
				return;
			}
			String keywords[] = inputLine.split(" ");
			for(int i = 0; i < keywords.length; i++)
				System.out.println(keywords[i] + " ");
			*/
			ArrayList<Group> groups = new ArrayList<Group>();
			groups.add(new Group(1, "title", "subject", "description", "address", "addresAddition", 
		"date", 120, 23, 0, "school", "creatorId", "creatorName"));
			groups.add(new Group(2, "title2", "subject2", "description2", "address2", "addresAddition2", 
		"date2", 132, 1, 1, "school2", "creatorId2", "creatorName2"));
			new XMLMaker(out).createGroupsXML(groups);
			//ArrayList<Group> groups = db.searchGroups(school,keywords);

				
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void send(int code)
	{
		out.println(code);
	}
}