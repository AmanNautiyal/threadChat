/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadchat;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aman Nautiyal
 */
public class ChatClient
{
 void runner() throws Exception
 {
  BufferedReader Br=new BufferedReader(new InputStreamReader(System.in));
  System.out.print("Enter Ip:");
  String ip=Br.readLine();
  System.out.print("Enter port:");
  int port=Integer.parseInt(Br.readLine());
  Socket s=new Socket(ip, port);
  System.out.print("Enter name:");
  String name=Br.readLine();
  BufferedReader reader=new BufferedReader(new InputStreamReader(s.getInputStream()));
  PrintWriter writer=new PrintWriter(s.getOutputStream(),true);
  writer.println(name);
  Listen listen=new Listen();
   System.out.println("Server:"+reader.readLine());
  while(true)
  {
      System.out.print(name+":");
      String clientSay=Br.readLine();
      writer.println(clientSay);
      if(clientSay.equalsIgnoreCase("quit"))
      {
        listen.stop();break;
      }
  }
 }
 private class Listen extends Thread
 {
     BufferedReader reader;
    public void Listen(BufferedReader read)
    {
     reader=read;
    }
     @Override
    public void run()
    {
      while(true) 
      {
          try {
              System.out.println(reader.readLine());
          } catch (IOException ex) {
              Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
 }
}
