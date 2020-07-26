package jhu.neptune.clueless.game.board.objects;

/**
 * TODO: Write this out
 * This is the CaseFile, which will keep three random cards
 * selected from the CardDeck.
 * This will parse the cards.json file using Moshi, then keep three of the cards chosen at random,
 * then pass the rest of the cards to other objects that will need the card.
 */
public class CaseFile {

    private CaseFile instance;

    private CaseFile() {/* Singleton Class. Only one case file object per game*/}

    public CaseFile getInstance() {
        if (instance == null) {
            instance = new CaseFile();
        }
        return instance;
    }
}
