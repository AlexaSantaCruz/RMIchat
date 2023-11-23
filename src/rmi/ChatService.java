/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    void registerClient(ClientCallback client, String username) throws RemoteException;
    void unregisterClient(ClientCallback client) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void sendDirectMessage(String sender, String receiver, String message) throws RemoteException;
}
