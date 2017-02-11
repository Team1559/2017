package org.usfirst.frc.team1559.robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * This class is responsible for communicating to a server over UDP. For the
 * 2017 season, this class connects to the Jetson TX1.
 */
public class UDPClient {

	// TODO: UDPClient should probably be run in its own thread.

	private static final int SOCKET_TIMEOUT = 3000; // in ms
	private static final int RECEIVE_PACKET_SIZE = 8;
	private String strAddress;
	private int port;

	private InetAddress serverAddress;
	private DatagramSocket socket;

	DatagramPacket receivePacket;

	public UDPClient(String host, int port) {
		this.strAddress = host;
		this.port = port;
		receivePacket = new DatagramPacket(new byte[RECEIVE_PACKET_SIZE], RECEIVE_PACKET_SIZE);
		if (connect()) {

		} else {
			System.err.println("Client failed to connect to ");
		}
	}

	public boolean connect() {
		try {
			serverAddress = InetAddress.getByName(strAddress);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}

		try {
			socket = new DatagramSocket();
			socket.setSoTimeout(SOCKET_TIMEOUT);
			// TODO: Send a connection package here to ensure connectivity. If
			// no response is received, fall back to no Jetson mode.
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void send(String string) {
		send(string.getBytes());
	}

	public void send(byte[] buffer) {
		try {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, port);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}

	/**
	 * Receive a packet from the server. Packet can be accessed with
	 * <blockquote><code>client.getReceivePacket()</code></blockquote>dank
	 */
	public void receive() {
		try {
			socket.receive(receivePacket);
		} catch (SocketTimeoutException e) {
			System.err.println("Client timed out.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DatagramPacket getReceivePacket() {
		return receivePacket;
	}

	public byte[] getRawReceiveData() {
		return receivePacket.getData();
	}
}
