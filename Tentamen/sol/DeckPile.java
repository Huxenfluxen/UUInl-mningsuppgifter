public class DeckPile extends Pile {
    private Pile discard;

    DeckPile(int x, int y) { super(x, y); }
    
    public void setDiscard(Pile d) {
        discard = d;
    }

    public Pile getDiscard() {
        return discard;
    }
    
    @Override
    public void fillDeckPile(SolPanel panel)  {
        Pile d = getDiscard();
        if (isEmpty()) {
            while (!d.isEmpty()) {
                Card c = d.pop();
                c.flip();
                add(c);                    
                panel.putOnTop(c);
            }
        }
        else {
            Card c = pop();
            c.flip();
            d.add(c);
            panel.putOnTop(c);
        }
    }

    @Override
    public boolean canTake(Card c) {
        return false;
    }

    @Override
    public boolean canRelease(Card c) {
        return false;
    }

    @Override
    public void showCard() {}

    @Override
    void addFanned(Card c)  {
        add(c);
    }


}

