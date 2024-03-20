package e2.chapter3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

public class Hand implements EditableHand {
    
    private int aN;
    private List<Card> aCards;

    public Hand(int pN) {
        aN = pN;
        aCards = new ArrayList<>(pN);
    }

    public void add(Card pCard) {
        assert pCard != null;
        assert !isFull();
        aCards.add(pCard);
    }

    public void remove(Card pCard) {
        assert pCard != null;
        assert aCards.contains(pCard);
        aCards.remove(pCard);
    }

    public boolean contains(Card pCard) {
        assert pCard != null;
        return aCards.contains(pCard);
    }

    public boolean isEmpty() {
        return aCards.isEmpty();
    }

    public int size() {
        return aCards.size();
    }

    public boolean isFull() {
        return aCards.size() == aN;
    }

    private int countByRank(Rank pRank) {
        int count = 0;
        for (Card c : aCards) {
            if (c.getRank() == pRank) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public Iterator<Card> iterator() {
        return aCards.iterator();
    }

    @Override
    public int compareTo(Hand pHand) {
        assert pHand != null;
        return aCards.size() - pHand.size();
    }

    public static Comparator<Hand> createAscendingComparator() {
        return new Comparator<Hand>() {
            @Override
            public int compare(Hand o1, Hand o2) {
                return o1.size() - o2.size();
            }
        };
    }

    public static Comparator<Hand> createDescendingComparator() {
        return new Comparator<Hand>() {
            @Override
            public int compare(Hand o1, Hand o2) {
                return o2.size() - o1.size();
            }
        };
    }

    public static Comparator<Hand> createRankComparator(Rank pRank) {
        assert pRank != null;
        return new Comparator<Hand>() {
            @Override
            public int compare(Hand o1, Hand o2) {
                return o1.countByRank(pRank) - o2.countByRank(pRank);
            }
        };
    }

    public static void main(String[] args) {
        Hand hand = new Hand(10);
        int counter = 10;
        while (counter > 0) {
            Card c = new Card(Rank.ACE, Suit.CLUBS);
            hand.add(c);
            counter -= 1;
        }

        for (Card c : hand) {
            System.out.printf("%s, %s\n", c.getRank(), c.getSuit());
        }

        Hand hand2 = new Hand(9);
        hand2.add(new Card(Rank.EIGHT, Suit.HEARTS));

        ReadonlyHand rHand = new Hand(10);

        System.out.println(hand.compareTo(hand2));
        System.out.println(Hand.createAscendingComparator().compare(hand, hand2));
        System.out.println(Hand.createDescendingComparator().compare(hand, hand2));
        System.out.println(Hand.createRankComparator(Rank.ACE).compare(hand, hand2));
    }
}