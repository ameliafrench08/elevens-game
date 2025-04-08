import java.util.ArrayList;

public class Deck {
    static ArrayList<Card> staticDeck;

    public ArrayList<Card> returnStatic(ArrayList<Card> deck){
        staticDeck = new ArrayList<>();
        staticDeck.addAll(deck);
        return staticDeck;
    }
}
