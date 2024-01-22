package e2.chapter3;

import java.util.Comparator;

public class UniversalComparator implements Comparator<Hand> {
    public enum Order { ASC, DESC };

    private Order aOrder = Order.ASC;

    public UniversalComparator() {}

    public UniversalComparator(Order pOrder) {
        assert pOrder != null;
        aOrder = pOrder;
    }

    public Order getOrder() {
        return aOrder;
    }

    public void setOrder(Order pOrder) {
        assert pOrder != null;
        aOrder = pOrder;
    }

    @Override
    public int compare(Hand o1, Hand o2) {
        if (aOrder == Order.ASC) {
            return Hand.createAscendingComparator().compare(o1, o2);
        } else {
            return Hand.createDescendingComparator().compare(o1, o2);
        }
    }

    public static void main(String[] args)
	{
		Hand hand1 = new Hand(3);
		Hand hand2 = new Hand(3);
		hand1.add(new Card(Rank.ACE, Suit.CLUBS));
        hand2.add(new Card(Rank.ACE, Suit.SPADES));
		UniversalComparator comparator = new UniversalComparator();
		System.out.println(comparator.compare(hand1, hand2));
		comparator.setOrder(Order.DESC);
		System.out.println(comparator.compare(hand1, hand2));
	}
}
