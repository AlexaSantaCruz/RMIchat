/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {
    private final String username;
    private final JTextArea jTextArea;

    public ClientCallbackImpl(String username,  JTextArea jTextArea) throws RemoteException {
        this.username = username;
        this.jTextArea = jTextArea;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        SwingUtilities.invokeLater(() -> {
            if (jTextArea != null) {
                jTextArea.append(message + "\n");
            } else {
                System.out.println("JTextArea not set for user: " + username);
            }
        });
    }


    @Override
    public String getUsername() throws RemoteException {
        return username;
    }
}

