package jhu.neptune.clueless.models.cardmodel;

import java.util.List;

public class CardDeck {
    private List<RoomCards>    room_cards;
    private List<SuspectCards> suspect_cards;
    private List<WeaponCards>  weapon_cards;

    public CardDeck(List<RoomCards> r, List<SuspectCards> s, List<WeaponCards> w) {
        this.room_cards    = r;
        this.suspect_cards = s;
        this.weapon_cards  = w;
    }

    public List<RoomCards> getRoomCards() {
        return room_cards;
    }
    public List<SuspectCards> getSuspectCards() {
        return suspect_cards;
    }
    public List<WeaponCards> getWeaponCards() { return weapon_cards; }
}
