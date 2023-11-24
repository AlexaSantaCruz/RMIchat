/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.swing.JTextArea;

public class ChatClient {
    ChatService chatService;
    
    public void startClient(String username, JTextArea jTextArea){
        try {
            //String serverUrl = "rmi://192.168.84.107:9000/ChatService";
            String serverUrl = "rmi://localhost:9000/ChatService";

            
            chatService = (ChatService) Naming.lookup(serverUrl);

            ClientCallbackImpl clientCallback = new ClientCallbackImpl(username, jTextArea);
            chatService.registerClient(clientCallback, username);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String message, String username) throws RemoteException{
        chatService.broadcastMessage(username + ": " + message);

    }
    
    public void sendPrivate(String username, String privateuser, String message) throws RemoteException{
        chatService.sendDirectMessage(username, privateuser, message);
        

    }
    
}
    
//    public static void main(String[] args) {
//        try {
//            String username = "prueba3";
//           //String serverUrl = "rmi://192.168.84.107:9000/ChatService";
//            String serverUrl = "rmi://localhost:9000/ChatService";
//            
//            ChatService chatService = (ChatService) Naming.lookup(serverUrl);
//
//            ClientCallbackImpl clientCallback = new ClientCallbackImpl(username);
//            chatService.registerClient(clientCallback, username);
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Ingrese 'salir' para salir del chat.");
//
//            while (true) {
//                String message = scanner.nextLine();
//                if (message.equals("salir")) {
//                    chatService.unregisterClient(clientCallback);
//                    System.exit(0);
//                } else {
//                    if (message.startsWith("/privado")) {
//                        String[] parts = message.split(" ", 3);
//                        if (parts.length == 3) {
//                            chatService.sendDirectMessage(username, parts[1], parts[2]);
//                        } else {
//                            System.out.println("Formato incorrecto. Uso: /privado <usuario> <mensaje>");
//                        }
//                    } else {
//                        chatService.broadcastMessage(username + ": " + message);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
