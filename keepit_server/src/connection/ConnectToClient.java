/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Hp
 */
public class ConnectToClient {
    public static boolean connection(String ip, int port) {
    Socket skt = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;

        try {
            skt = new Socket(ip, port); // local host 127.0.0.1   ip address + port // created if positive ack os recieved
            System.out.println("Connected to server");
            dos = new DataOutputStream(skt.getOutputStream());
            dis = new DataInputStream(skt.getInputStream());
            return true;

        } catch (Exception e) {
            System.out.println("Exception in Connection method of ConnectToServer class" + e);

        } finally {
            try {
                br.close();
                isr.close();
                skt.close();
                dos.close();
                dis.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return false;
    }
     public static String readData(int port )
    {
                ServerSocket sskt =null;
		Socket skt = null;
		DataInputStream dis = null;
		DataOutputStream dos =null;
                
                try
		{
			sskt = new ServerSocket(port);
			System.out.println("waiting for client request");
			skt = sskt.accept();
			System.out.println("Connected to client");
			dis = new DataInputStream(skt.getInputStream());
			String msg =  " ";
			msg = dis.readUTF();
                        System.out.println("Client = "+dis);
                return msg;        
                }
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally 
		{
			try
			{
				dis.close();
				dos.close();
				sskt.close();
				skt.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
                return null;
		
    }
}
