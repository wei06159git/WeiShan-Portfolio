package jhu.neptune.clueless.networking.servers;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import jhu.neptune.clueless.networking.common.ChatNetwork;

import java.io.IOException;
import java.util.ArrayList;

public class ClueLessChatServer implements Disposable {

    public Server server;
    /**
     * Contains a list of active connections and their respective client objects
     */
    public ClueLessChatServer () throws IOException {
        Log.set(Log.LEVEL_DEBUG);

        server = new Server() {
            protected Connection newConnection () {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new ChatConnection();
            }
        };

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        ChatNetwork.register(server);

        server.addListener(new Listener() {
            public void received (Connection c, Object object) {
                // We know all connections for this server are actually ChatConnections.
                ChatConnection connection = (ChatConnection)c;

                if (object instanceof ChatNetwork.RegisterName) {
                    // Ignore the object if a client has already registered a name. This is
                    // impossible with our client, but a hacker could send messages at any time.
                    if (connection.name != null) return;
                    // Ignore the object if the name is invalid.
                    String name = ((ChatNetwork.RegisterName)object).name;
                    if (name == null) return;
                    name = name.trim();
                    if (name.length() == 0) return;
                    // Store the name on the connection.
                    connection.name = name;
                    // Send a "connected" message to everyone except the new client.
                    ChatNetwork.ChatMessage chatMessage = new ChatNetwork.ChatMessage();
                    chatMessage.text = name + " connected.";
                    chatMessage.timestamp = System.currentTimeMillis();
                    server.sendToAllExceptTCP(connection.getID(), chatMessage);
                    // Send everyone a new list of connection names.
                    updateNames();
                    return;
                }

                if (object instanceof ChatNetwork.ChatMessage) {
                    // Ignore the object if a client tries to chat before registering a name.
                    if (connection.name == null) return;
                    ChatNetwork.ChatMessage chatMessage = (ChatNetwork.ChatMessage)object;
                    // Ignore the object if the chat message is invalid.
                    String message = chatMessage.text;
                    if (message == null) return;
                    message = message.trim();
                    if (message.length() == 0) return;
                    // Prepend the connection's name and send to everyone.
                    chatMessage.text = connection.name + ": " + message;
                    System.out.println("SERVER: Sending following Message: " + chatMessage.text);
                    server.sendToAllTCP(chatMessage);
                    return;
                }
            }

            public void disconnected (Connection c) {
                ChatConnection connection = (ChatConnection)c;
                if (connection.name != null) {
                    // Announce to everyone that someone (with a registered name) has left.
                    ChatNetwork.ChatMessage chatMessage = new ChatNetwork.ChatMessage();
                    chatMessage.text = connection.name + " disconnected.";
                    chatMessage.timestamp = System.currentTimeMillis();
                    server.sendToAllTCP(chatMessage);
                    updateNames();
                }
            }
        });
        server.bind(ChatNetwork.port);
        server.start();
    }

    void updateNames () {
        // Collect the names for each connection.
        Connection[] connections = server.getConnections();
        ArrayList names = new ArrayList(connections.length);
        for (int i = connections.length - 1; i >= 0; i--) {
            ChatConnection connection = (ChatConnection)connections[i];
            names.add(connection.name);
        }
        // Send the names to everyone.
        ChatNetwork.UpdateNames updateNames = new ChatNetwork.UpdateNames();
        updateNames.names = (String[])names.toArray(new String[names.size()]);
        server.sendToAllTCP(updateNames);
    }

    @Override
    public void dispose() {
        try {
            server.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // This holds per connection state.
    static class ChatConnection extends Connection {
        public String name;
    }
}