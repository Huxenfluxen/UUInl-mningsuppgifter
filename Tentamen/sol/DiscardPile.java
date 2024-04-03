public class DiscardPile extends Pile {

    DiscardPile (int x, int y) {
        super(x, y);
    }

    @Override
    public boolean canTake(Card c) {
        return false;
    }

    @Override
    public boolean canRelease(Card c) {
        return c == top();
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
