package e2.chapter3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Comparator;

public class Hand2 implements Iterable<Card> {
    public static void main(String[] args) {
        Hand2[] hands = new Hand2[2];

        Hand2 h2 = new Hand2();
        Hand2 h3 = new Hand2();

        int idx = 0;
        while (idx < 10) {
            Card c = new Card(Rank.ACE, Suit.CLUBS);
            h2.aCards[idx] = c;
            h3.aCards[idx] = c;
            idx += 1;
        }
        
        h3.aCards[9] = new Card(Rank.EIGHT, Suit.HEARTS);
        
        hands[0] = h3;
        hands[1] = h2;

        System.out.println(hands[0]);
        System.out.println(hands[1]);

        ByNumberComparator c = Hand2.ByNumberComparator.get(Rank.EIGHT);
        Arrays.sort(hands, c);

        System.out.println(hands[0]);
        System.out.println(hands[1]);
    }

	// Assume the cards are initialized somehow.
	// You don't have to provide the code to do this.
	private Card[] aCards = new Card[10];

    public Hand2() {}
	
	private int countByRank(Rank pRank) {
		int count = 0;
		for (Card card : aCards) {
			if (card.getRank().equals(pRank)) {
				count += 1;
			}
		}
		return count;
	}
	
	@Override
	public Iterator<Card> iterator() {
        return Arrays.asList(aCards).iterator();
	}
	
	public static class ByNumberComparator implements Comparator<Hand2> {
		private static final ByNumberComparator[] FACTORY = 
            new ByNumberComparator[Rank.values().length];
		
		private final Rank aRank;
		
		private ByNumberComparator(Rank pRank) {
			aRank = pRank;	
		}
		
        public static ByNumberComparator get(Rank pRank) {
            return FACTORY[pRank.ordinal()];
        }

		static {
			// create all needed comparators and put them in the factory
			for (Rank rank : Rank.values()) {
				FACTORY[rank.ordinal()] = new ByNumberComparator(rank);
			}
		}
		
		@Override
		public int compare(Hand2 first, Hand2 second) {
			int countFirst = first.countByRank(aRank);
			int countSecond = second.countByRank(aRank);
			return countFirst - countSecond;
		}
	}
}
