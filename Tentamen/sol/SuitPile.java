class SuitPile extends Pile {

    SuitPile (int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canTake(Card c) {
        if (isEmpty()) {
            return c.getRank() == 0;
        }
        else {
            Card topCard = top();
            return c.getSuit() == topCard.getSuit()
                && c.getRank() == 1 + topCard.getRank()
                || c == topCard;
        }
    }

    @Override
    public boolean canRelease(Card c) {
        return false;
    }

    @Override
    public void fillDeckPile(SolPanel panel) {}

    @Override
    public void showCard() {}

    @Override
    void addFanned(Card c)  {
        add(c);
    }

}
