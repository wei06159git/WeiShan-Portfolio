package jhu.neptune.clueless.models.cardmodel;

import jhu.neptune.clueless.game.board.objects.Characters;

public class SuspectCards {
    private final Characters suspect;
    private final String image;

    public SuspectCards(Characters suspect, String image) {
        this.suspect = suspect;
        this.image = image;
    }

    public Characters getRoom() { return suspect; }
    public String getImageLocation() { return image; }
}