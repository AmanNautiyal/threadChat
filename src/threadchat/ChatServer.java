/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadchat;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aman Nautiyal
 */
public class ChatServer {

    Inet4Address serverIp;
    ServerSocket s;
    BufferedReader Br;
    ArrayList<Socket> clientList;

    public ChatServer() throws IOException {
        Br = new BufferedReader(new InputStreamReader(System.in));
        initialise();
    }

    private void initialise() throws IOException {
        System.out.print("Enter the port:");
        int port = Integer.parseInt(Br.readLine());
        s = new ServerSocket(port);
    }

    public void process() throws Exception {
        while (true) {
            Socket client = s.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            clientManager cm = new clientManager(client, reader.readLine());
            cm.start();
        }
    }
    private void sayAll(String s,Socket client)
    {
        PrintWriter writer;
       for(Socket output:clientList)
       {
           if(output==client)
               continue;
            try {
                writer=new PrintWriter(output.getOutputStream());
                   writer.println(s);
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       }
    }
    class clientManager extends Thread {

        Socket client;
        String name;

        public clientManager(Socket in, String n) {
            client = in;
            name = n;
            clientList.add(in);
        }

        @Override
        public void run() {
            PrintWriter writer;
            BufferedReader reader;
            try {
                writer = new PrintWriter(client.getOutputStream(), true);
                writer.println("Connected to Server.Your IP is " + client.getInetAddress().getHostAddress());
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while (true) {
                    System.out.print(name+":");
                    String clientSay = reader.readLine();
                    System.out.println(clientSay);
                    if (clientSay.equalsIgnoreCase("quit")) 
                    {
                        this.stop();break;
                    }
                     sayAll(name+":"+clientSay,client);
                }
            } catch (IOException ex) {
                System.out.println(name + " disconnected abruptly");
            }
            clientList.remove(client);
            System.out.println(name +"Disconnected");
        }
    }
}
