/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import java.rmi.Naming;
import java.util.Scanner;

public class ChatClient {
    
    
    public void startClient(){
        try {
            String username = "prueba3";
            //String serverUrl = "rmi://192.168.84.107:9000/ChatService";
            String serverUrl = "rmi://localhost:9000/ChatService";

            
            ChatService chatService = (ChatService) Naming.lookup(serverUrl);

            ClientCallbackImpl clientCallback = new ClientCallbackImpl(username);
            chatService.registerClient(clientCallback, username);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese 'salir' para salir del chat.");

            while (true) {
                String message = scanner.nextLine();
                if (message.equals("salir")) {
                    chatService.unregisterClient(clientCallback);
                    System.exit(0);
                } else {
                    if (message.startsWith("/privado")) {
                        String[] parts = message.split(" ", 3);
                        if (parts.length == 3) {
                            chatService.sendDirectMessage(username, parts[1], parts[2]);
                        } else {
                            System.out.println("Formato incorrecto. Uso: /privado <usuario> <mensaje>");
                        }
                    } else {
                        chatService.broadcastMessage(username + ": " + message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            String username = "prueba3";
           //String serverUrl = "rmi://192.168.84.107:9000/ChatService";
            String serverUrl = "rmi://localhost:9000/ChatService";
            
            ChatService chatService = (ChatService) Naming.lookup(serverUrl);

            ClientCallbackImpl clientCallback = new ClientCallbackImpl(username);
            chatService.registerClient(clientCallback, username);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese 'salir' para salir del chat.");

            while (true) {
                String message = scanner.nextLine();
                if (message.equals("salir")) {
                    chatService.unregisterClient(clientCallback);
                    System.exit(0);
                } else {
                    if (message.startsWith("/privado")) {
                        String[] parts = message.split(" ", 3);
                        if (parts.length == 3) {
                            chatService.sendDirectMessage(username, parts[1], parts[2]);
                        } else {
                            System.out.println("Formato incorrecto. Uso: /privado <usuario> <mensaje>");
                        }
                    } else {
                        chatService.broadcastMessage(username + ": " + message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
