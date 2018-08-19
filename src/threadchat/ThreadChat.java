/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadchat;
import java.io.*;
/**
 *
 * @author Aman Nautiyal
 */
public class ThreadChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        BufferedReader Br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Choice:\n1.Server\n2.Client");
        int i=Integer.parseInt(Br.readLine());
        switch(i)
        {
            case 1:
                 ChatServer ob=new ChatServer();
                 ob.process();break;
            case 2:
                 ChatClient ab=new ChatClient();
                 ab.runner();break;
            default:
                 System.out.println("Wrong Choice");
        }
        // TODO code application logic here
    }
    
}
