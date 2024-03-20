package e2.chapter3;

public interface EditableHand extends ReadonlyHand {
    void add(Card pCard);
    void remove(Card pCard);
}
