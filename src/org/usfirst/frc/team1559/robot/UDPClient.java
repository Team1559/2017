package org.usfirst.frc.team1559.robot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This class is responsible for communicating to a server over UDP.
 */
public class UDPClient {

	private static final String HOST = "10.15.59.6";
	private static final int PORT = 26;
	
	public String receive() {
		String modifiedSentence = "";
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
	// // TODO: UDPClient should probably be run in its own thread.
	//
	// Thread clientThread;
	// private static final int SOCKET_TIMEOUT = 1000; // in ms
	// private static final int RECEIVE_PACKET_SIZE = 8;
	// private String strAddress;
	// private int port;
	//
	// private InetAddress serverAddress;
	// private Socket socket;
	//
	// DatagramPacket receivePacket;
	// boolean running = false;
	//
	// public UDPClient(String host, int port) {
	// Thread clientThread = new Thread();
	// this.strAddress = host;
	// this.port = port;
	// receivePacket = new DatagramPacket(new byte[RECEIVE_PACKET_SIZE],
	// RECEIVE_PACKET_SIZE);
	// if (connect()) {
	//
	// } else {
	// System.err.println("Client failed to connect to " + host);
	// }
	// clientThread.start();
	// }
	//
	// public boolean connect() {
	// try {
	// serverAddress = InetAddress.getByName(strAddress);
	// } catch (UnknownHostException e) {
	// e.printStackTrace();
	// return false;
	// }
	//
	// try {
	// socket = new Socket();
	// socket.setSoTimeout(SOCKET_TIMEOUT);
	// send("connect!");
	// running = true;
	// while (running) {
	// receive();
	// send("rip");
	// System.out.println(Arrays.toString(getRawReceiveData()));
	// }
	// } catch (SocketException e) {
	// e.printStackTrace();
	// return false;
	// }
	//
	// return true;
	// }
	//
	// public void send(String string) {
	// send(string.getBytes());
	// }
	//
	// public void send(byte[] buffer) {
	// try {
	// DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
	// serverAddress, port);
	// socket.send(packet);
	// System.out.println("SENT " + buffer.length + " BYTES TO " + strAddress +
	// ":" + port);
	// } catch (IOException e) {
	// System.out.println("ERROR SENDING");
	//// e.printStackTrace();
	// }
	// }
	//
	// public void receive() {
	// try {
	// socket.receive(receivePacket);
	// } catch (SocketTimeoutException e) {
	// System.out.println("ERROR RECEIVING");
	//// System.err.println("Client timed out.");
	// } catch (IOException e) {
	// System.out.println("ERROR RECEIVING");
	//// e.printStackTrace();
	// }
	// }
	//
	// public DatagramPacket getReceivePacket() {
	// return receivePacket;
	// }
	//
	// public byte[] getRawReceiveData() {
	// return receivePacket.getData();
	// }
	//
	// @Override
	// public void run() {
	// connect();
	// }
}
