package org.usfirst.frc.team1559.robot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This class is responsible for communicating to a server over UDP.
 */
public class UDPClient implements Runnable{

	private static final String HOST = "10.15.59.6";
	private static final int PORT = 26;
	
	Thread clientThread;
	boolean running;
	
	String data;
	
	public UDPClient() {
		clientThread = new Thread(this);
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			String rec = receive();
			if (rec != null) {
				data = rec;
			}
		}
	}
	
	public String get() {
		return data;
	}
	
	public String receive() {
		String modifiedSentence = null;
		try {

//			String sentence;
//			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			Socket clientSocket = new Socket(HOST, PORT);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

//			sentence = inFromUser.readLine();
			modifiedSentence = inFromServer.readLine();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modifiedSentence;
	}
}
