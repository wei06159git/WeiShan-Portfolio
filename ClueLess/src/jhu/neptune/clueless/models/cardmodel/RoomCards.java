package jhu.neptune.clueless.models.cardmodel;

import jhu.neptune.clueless.game.board.objects.Rooms;

public class RoomCards {
    private final Rooms room;
    private final String image;

    public RoomCards(Rooms room, String image) {
        this.room = room;
        this.image = image;
    }

    public Rooms getRoom() {
        return this.room;
    }
    public String getImageLocation() { return image; }
}
