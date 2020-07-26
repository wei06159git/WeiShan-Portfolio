package jhu.neptune.clueless.util;


import jhu.neptune.clueless.networking.common.ChatNetwork;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Quickly created this util class to fix the sorting. (the client was showing
 * messages in different orders.) You should write some unit tests to verify if
 * it works 100% if you're seriously going to use it.
 *
 * @author Leejjon
 */
public class HashMapToOrderedArray {

    public static Object[] getOrderedObjectArray(Map<Long, ChatNetwork.ChatMessage> chatMessages) {
        HashMap<Long, ChatNetwork.ChatMessage> chatMessagesToCopy = new HashMap<>();
        chatMessagesToCopy.putAll(chatMessages);
        Object[] orderedArray = new Object[chatMessagesToCopy.size()];

        for (int i = 0; i < chatMessages.size(); i++) {
            Long lowest = getLowestKey(chatMessagesToCopy.values());
            orderedArray[i] = chatMessagesToCopy.get(lowest);
            chatMessagesToCopy.remove(lowest);
        }

        return orderedArray;
    }
    public static Long getLowestKey(Collection<ChatNetwork.ChatMessage> chatMessages) {
        Long lowestTimestamp = Long.MAX_VALUE;
        Long lowestKey = Long.MAX_VALUE;
        for (ChatNetwork.ChatMessage message : chatMessages) {
            if (message.timestamp < lowestTimestamp) {
                lowestTimestamp = message.timestamp;
                lowestKey = message.timestamp;
            }
        }
        return lowestKey;
    }
}