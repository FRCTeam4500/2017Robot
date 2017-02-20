package utilities;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.Semaphore;

public class VisionClient implements Runnable {
	
	private DatagramSocket socket;
	private Semaphore mutex;
	private double data;
	
	public VisionClient(short port) throws IOException {
		socket = new DatagramSocket(port);
		mutex = new Semaphore(1);
		data = 0;	}
	
	/**
	 * Grabs the data from the coprocessor using UDP protocol.
	 * Note that this MUST RUN in a separate thread since it uses a while loop.
	 */
	@Override
	public void run() {
		while(true) {
			byte[] recvData = new byte[1024];
			DatagramPacket packet = new DatagramPacket(recvData, recvData.length);
			try {
				socket.receive(packet);
				String dataS = new String(packet.getData());
				String[] parts = dataS.split(" ");
				
				mutex.acquire();
				data = Double.parseDouble(parts[0]);
				mutex.release();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Function for getting the current data from the coprocessor
	 * Returns the data retrieved from the coprocessor
	 */
	public double getData() {
		double data = 0.0;
		try {
			mutex.acquire();
			data = this.data;
			mutex.release();
		} catch(InterruptedException e) {
			System.out.print("Error");
		}
		return data;
	}

}
