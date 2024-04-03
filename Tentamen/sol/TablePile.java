class TablePile extends Pile {

    TablePile (int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canTake(Card c) {
        if (isEmpty()) {
            return c.getRank() == 12;
        }
        else {
            Card topCard = top();
            return ((c.getSuit() != topCard.getSuit())
                    && (c.getRank() == topCard.getRank() - 1))
                || topCard == c;
        }
    }

    @Override
    public boolean canRelease(Card c) {
        return c == top();
    }

    @Override
    public void fillDeckPile(SolPanel panel) {}

    @Override
    public void showCard() {
        if (!isEmpty()) {
            top().turnUp();
        }
    }

}
