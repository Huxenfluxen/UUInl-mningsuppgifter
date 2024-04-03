package napoleon;

import java.util.List;

public class CardPile {
    private List<Card> cards;
    void add(Card c) {
    cards.add(c);
    }
    public List<Card> getCards() {
    return cards;
    }
}
