import java.io.*;
import java.net.*;

public class TestClient{
	public TestClient(String hostName, int portNumber)
	{
		try(
			Socket socket = new Socket(hostName, portNumber);
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
			){
			BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
			String id = "";
			String password = "";
			String school = "";
			System.out.println("Enter your user id:");
			id = stdIn.readLine();
			System.out.println("Enter your password:");
			password = stdIn.readLine();
			System.out.println("Enter your school:");
			school = stdIn.readLine();
			String send = id + " " + password + " " + school;
			out.println(send);
			int code = Integer.parseInt(in.readLine());
			if(code == -1)
			{
				System.out.println("login failed");
				return ;
			}
			if(code == 1)
				System.out.println("login successfully");
			String line = in.readLine();
			System.out.println(line);
			line = in.readLine();
			System.out.println(line);
		}catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
	}
	public static void main(String args[]){
		if(args.length != 2){
			System.err.println("Usage: java ChatClient <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		new TestClient(hostName, portNumber);
		
	}
}