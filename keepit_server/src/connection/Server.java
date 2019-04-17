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
public class Server {
    public static void main(String dm[])
	{
		ServerSocket sskt =null;
		Socket skt = null;
		DataInputStream dis = null;
		InputStreamReader isr= null;
		BufferedReader br = null;
		DataOutputStream dos =null;
		try
		{
			sskt = new ServerSocket(12345);
			System.out.println("waiting for client request");
			skt = sskt.accept();
			System.out.println("Connected to client");
			dis = new DataInputStream(skt.getInputStream());
			isr = new InputStreamReader(System.in);
			br = new BufferedReader(isr);
			dos = new DataOutputStream(skt.getOutputStream());
			
			String msg =  " ";
			
			do
			{
			msg = dis.readUTF();
			System.out.println("Client : -"+msg);
			//String msg = " " ;
			//String msg = " ";
			msg =br.readLine();
			dos.writeUTF(msg);
			}while(!msg.equalsIgnoreCase("bye"));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally 
		{
			try
			{
				isr.close();
				br.close();
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
        } 
}
