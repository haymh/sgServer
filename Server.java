import java.net.*;
import java.io.*;
import java.util.*;

public class Server {


	public Server(){
	}
	public static void main(String[] args) throws IOException{
		Server server = new Server();
		if(args.length != 1){
			System.err.println("Usage: Java RegisterServer <port number>");
			System.exit(1);
		}
		Database db = null;
		try
		{
			db = new Database();
		}catch(Exception e){
			e.printStackTrace();
			db.close();
			System.exit(-1);
		}
		int portNumber = Integer.parseInt(args[0]);
		boolean listening = true;

		try(ServerSocket serverSocket = new ServerSocket(portNumber)){
			while(listening){
				ServerThread thread = new ServerThread(serverSocket.accept(), db);
				thread.start();
			}
		}catch(IOException e){
				System.err.println("Could not listen on port " + portNumber);
				db.close();
				System.exit(-1);
		}
	}

}