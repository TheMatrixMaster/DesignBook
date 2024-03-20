package e2.chapter4;

import java.util.Comparator;

public class Game {
    private Comparator<Card> aComparator = NullComparator;

    public static final Comparator<Card> NullComparator = new Comparator<Card>() {
        public int compare(Card o1, Card o2) {
            return 0;
        }
    };

    public static <T extends Shufflable> void performShuffle(T pShufflableObj) {
        if (pShufflableObj != Shufflable.NULL_SHUFFLABLE) {
            pShufflableObj.shuffle();
        }
    }
}
