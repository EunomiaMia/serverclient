
package javaapplication140;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    
    @Override
    public void run(){
        
        try(
            ServerSocket serverSocket = new ServerSocket(1000);
                Socket socket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream()))
        {
            String line = br.readLine();
            String[] ops = line.split(" ");
            double sum = Double.valueOf(ops[0]) + Double.valueOf(ops[1]);
            String rez = sum + "\r\n";
            bos.write(rez.getBytes());
            
            }
                 catch(IOException e)
        {
            System.out.println(e.getMessage());  
    
    }}
    
    }
