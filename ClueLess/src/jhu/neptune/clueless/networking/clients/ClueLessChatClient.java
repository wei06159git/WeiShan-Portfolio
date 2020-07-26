package jhu.neptune.clueless.networking.clients;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import com.sun.tools.javac.Main;
import jhu.neptune.clueless.networking.common.ChatNetwork;
import jhu.neptune.clueless.screens.api.ClueLessCommon;
import jhu.neptune.clueless.screens.gamescreens.MainGameScreen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClueLessChatClient implements Disposable {

    public Client client;
    private String name;
    private String host;
    private static ArrayList<ChatNetwork.ChatMessage> messages = new ArrayList<>();

    public ClueLessChatClient () {
        Log.set(Log.LEVEL_DEBUG);
        client = new Client();
        client.start();
        host = ClueLessCommon.hostname;
        name = ClueLessCommon.player_name;

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        ChatNetwork.register(client);

        client.addListener(new Listener() {
            public void connected (Connection connection) {
                ChatNetwork.RegisterName registerName = new ChatNetwork.RegisterName();
                registerName.name = name;
                client.sendTCP(registerName);
            }

            public void received (Connection connection, Object object) {
                if (object instanceof ChatNetwork.UpdateNames) {
                    ChatNetwork.UpdateNames updateNames = (ChatNetwork.UpdateNames)object;
                    MainGameScreen.addUsersToList(updateNames.names);
                   // chatWindow.setNames(updateNames.names);
                    return;
                }

                if (object instanceof ChatNetwork.ChatMessage) {
                    ChatNetwork.ChatMessage chatMessage = (ChatNetwork.ChatMessage)object;
                    System.out.println("CLIENT: Received following message: " + chatMessage.text);
                    MainGameScreen.onChatMessageReceived(chatMessage.text); //TODO: Make this thread safe or redesign
                }
            }

            public void disconnected (Connection connection) {
                EventQueue.invokeLater(() -> {
                    // Closing the frame calls the close listener which will stop the client's update thread.
                    //chatWindow.dispose();
                });
            }
        });

        new Thread("Connect") {
            public void run () {
                try {
                    client.connect(60000, host, ChatNetwork.port);
                    // Server communication after connection can go here, or in Listener#connected().
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }

    @Override
    public void dispose() {
        if (client != null) {
            try {
                client.dispose();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
