/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTextArea;

public class ChatServer extends UnicastRemoteObject implements ChatService {
    private Map<String, ClientCallback> clients;

    public ChatServer() throws RemoteException {
        clients = new HashMap<>();
    }

    @Override
    public void registerClient(ClientCallback client, String username) throws RemoteException {
        clients.put(username, client);
        broadcastMessage(username + " se ha unido al chat.");
    }

    private String getClientUsername(ClientCallback client) throws RemoteException {
    return client.getUsername();
}

    @Override
    public void unregisterClient(ClientCallback client) throws RemoteException {
        String username = getClientUsername(client);
        clients.remove(username);
    }

    @Override
    public void broadcastMessage(String message) throws RemoteException {
        for (ClientCallback client : clients.values()) {
            client.receiveMessage(message);
        }
    }

    @Override
    public void sendDirectMessage(String sender, String receiver, String message) throws RemoteException {
        ClientCallback client = clients.get(receiver);

        if (client != null) {
            client.receiveMessage("[Mensaje privado de " + sender + "]: " + message);
        }
    }
    
    public void connection(String Ip){
        try {
                ChatService chatService = new ChatServer();

                LocateRegistry.createRegistry(9000);

                //            String serverUrl = "rmi://192.168.84.107:9000/ChatService";

               // java.rmi.Naming.rebind("rmi://192.168.84.107:9000/ChatService", chatService);
                java.rmi.Naming.rebind("rmi://"+Ip+":9000/ChatService", chatService);
 
               System.out.println("Servidor de chat RMI listo.");
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    

    public static void main(String[] args) {
                try {
                ChatService chatService = new ChatServer();

                LocateRegistry.createRegistry(9000);

                //            String serverUrl = "rmi://192.168.84.107:9000/ChatService";

                java.rmi.Naming.rebind("rmi://localhost:9000/ChatService", chatService);
                System.out.println("Servidor de chat RMI listo.");
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
