package jhu.neptune.clueless.util;


import java.util.Set;

public class LowestIdFinder {
    public static Long getLowestId(Set<Long> chatMessages) {
        Long lowestId = Long.MAX_VALUE;

        for (Long id : chatMessages) {
            if (id < lowestId) {
                lowestId = id;
            }
        }
        return lowestId;
    }
}