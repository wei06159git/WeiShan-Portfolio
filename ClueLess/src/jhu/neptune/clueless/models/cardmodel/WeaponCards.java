package jhu.neptune.clueless.models.cardmodel;

import jhu.neptune.clueless.game.board.objects.Weapons;

public class WeaponCards {
    private final Weapons weapon;
    private final String image;

    public WeaponCards(Weapons weapon, String image) {
        this.weapon = weapon;
        this.image = image;
    }

    public Weapons getRoom() {
        return this.weapon;
    }
    public String getImageLocation() { return image; }
}
