import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
<<<<<<< HEAD
=======
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.*;
import java.text.*;

>>>>>>> Jen

public class Server extends JFrame{
	private JTextField enterField; //imputs message from user
	private JTextArea displayArea;
	private ObjectOutputStream output; //output stream to client
	private ObjectInputStream input;
	private ServerSocket server; //server socket
	private Socket connection; //connection to client
	private int counter = 1; //number of connections
<<<<<<< HEAD
=======
	PrintWriter textLogs;
	DateFormat date;
	Date dNow;
	SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss ");
>>>>>>> Jen

	//Setting up simple GUI
	public Server(){
		super("Server");

		enterField = new JTextField();
		enterField.setEditable(false);
		enterField.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					sendData(event.getActionCommand());
					enterField.setText("");
				}
			}
		);

		add(enterField, BorderLayout.SOUTH);
		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);

		setSize(400,200);
		setVisible(true);
	}

	//set up and run server
	public void runServer(){
		try{
			server = new ServerSocket(1900, 100); //create ServerSocket

			while(true){
				try{
<<<<<<< HEAD
=======
					textLogs = new PrintWriter(new BufferedWriter(new FileWriter("messageLogs.txt", true)));
				}catch(IOException e){
					System.out.println("Couldn't open file");
				}
				try{
>>>>>>> Jen
					waitForConnection();
					getStreams();
					processConnection();
				}
<<<<<<< HEAD
				catch(EOFException eofException){ displayMessage("\nServer terminated connection"); }
				
=======
				catch(EOFException eofException){
					displayMessage("\nServer terminated connection");
				}
>>>>>>> Jen
				finally{
					closeConnection();
					++counter;
				}
			}
		}catch(IOException ioException){ ioException.printStackTrace(); }
	}

	private void waitForConnection() throws IOException{
		displayMessage("Waiting for connection\n");
		connection = server.accept();
		displayMessage("Connection "+counter+" recieved from: "+connection.getInetAddress().getHostName());
	}

	//streams to end and recieve data
	private void getStreams() throws IOException{
		output =  new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());

		displayMessage("\nGot I/O streams\n");
	}

	//Process connection
	private void processConnection() throws IOException{
<<<<<<< HEAD
		String message = "Connection Sucessful";
=======
		String message = "Connection Successful";
>>>>>>> Jen
		sendData(message);
		setTextFieldEditable(true);
		//Check message is in string format
		do{
			try{
				message = (String) input.readObject();
				displayMessage("\n"+message);
<<<<<<< HEAD
=======
				dNow = new Date( );
				textLogs.println(ft.format(dNow) + "| "+message);
>>>>>>> Jen
			}catch(ClassNotFoundException classNotFoundException){
				displayMessage("\n Unknown message format. Please use basic String text\n");
			}
		}while(!message.equals("CLIENT>>> TERMINATE"));
<<<<<<< HEAD
=======
		message = "";
>>>>>>> Jen
	}

	//close streams and sockets.
	private void closeConnection(){
		displayMessage("\nTerminating connection\n");
		setTextFieldEditable(false);

		try{
			output.close();
			input.close();
			connection.close();
<<<<<<< HEAD
=======
			textLogs.close();
>>>>>>> Jen
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	//Sending a message to the client.
	private void sendData(String message){
		try{
			output.writeObject("SERVER>>> "+message);
<<<<<<< HEAD
=======
			dNow = new Date( );
			textLogs.println(ft.format(dNow) + "| SERVER>>> "+message);
>>>>>>> Jen
			output.flush();
			displayMessage("\nSERVER>>> "+message);
		}catch(IOException ioException){
			displayArea.append("\nError writing object");
		}
	}

	private void displayMessage(final String messageToDisplay){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){ //updates the display area
					displayArea.append(messageToDisplay);
				}
			}
		);
	}

	//manipulates enterfield.
	public void setTextFieldEditable(final boolean editable){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					enterField.setEditable(editable);
				}
			}
		);
	}
}
<<<<<<< HEAD





































=======
>>>>>>> Jen
