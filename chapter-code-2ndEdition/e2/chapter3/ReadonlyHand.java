package e2.chapter3;
import java.lang.Iterable;

public interface ReadonlyHand extends Comparable<Hand>, Iterable<Card> {
    boolean isFull();
    boolean isEmpty();
    boolean contains(Card pCard);
    int size();
}
