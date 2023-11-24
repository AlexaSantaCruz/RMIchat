/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatService {
    public List<ClientCallback> clients;

    public ChatServer() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void registerClient(ClientCallback client, String username) throws RemoteException {
        clients.add(client);
        broadcastMessage(username + " se ha unido al chat.");
        for(int i=0;i<clients.size();i++){
            System.out.println(clients.get(i));
        }
    }

    @Override
    public void unregisterClient(ClientCallback client) throws RemoteException {
        clients.remove(client);
    }

    @Override
    public void broadcastMessage(String message) throws RemoteException {
        for (ClientCallback client : clients) {
            client.receiveMessage(message);
        }
    }

    @Override
    public void sendDirectMessage(String sender, String receiver, String message) throws RemoteException {
        // Buscar al receptor en la lista de clientes y enviarle el mensaje directo
        for (ClientCallback client : clients) {
            if (client.toString().equals(receiver)) {
                client.receiveMessage("[Mensaje privado de " + sender + "]: " + message);
                break;
            }
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
