package jhu.neptune.clueless.networking.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

//TODO: We need to add the game objects to send over the network.
public class GameNetwork {

    static public final int port = 7778;
    // This registers objects that are going to be sent over the network.
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        //kryo.register(ChatNetwork.RegisterName.class);

    }
}
