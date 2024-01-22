package e2.chapter2;

public class Multideck {
    private Deck[] aDecks;

    public static void main(String[] args) {
        // test the code
        Multideck m1 = new Multideck(2);
        Multideck m2 = new Multideck(m1);
        
        assert m1.aDecks == m2.aDecks;
    }

    public Multideck(int pNumberOfDecks) {
        assert pNumberOfDecks > 0;
        aDecks = new Deck[pNumberOfDecks];

        for (int i=0; i<pNumberOfDecks; i++) {
            aDecks[i] = new Deck();    
        }
    }

    public Multideck(Multideck multideck) {
        // shallow copy constructor
        this.aDecks = multideck.aDecks;
    }

    public boolean isEmpty() {
        for (int i=0; i<aDecks.length; i++) {
            if (this.aDecks[i].isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public Card draw() {
        assert !isEmpty();
        for (Deck deck : aDecks) {
            if (deck.isEmpty()) {
                continue;
            } else {
                return deck.draw();
            }
        }
        assert false;
        return null;
    }


}
