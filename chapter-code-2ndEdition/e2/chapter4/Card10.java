package e2.chapter4;

public class Card10
{
    public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (Rank r : Rank.values()) {
			for (Suit s : Suit.values()) {
				Card9.get(r, s);
			}
		}
		long end = System.currentTimeMillis();
        
		System.out.println(start);
        System.out.println(end);
	}

    private static final Card10[][] CARDS = new Card10[Suit.values().length][Rank.values().length];
    
    private final Suit aSuit;
    private final Rank aRank;

    private Card10(Suit pSuit, Rank pRank) {
        aSuit = pSuit;
        aRank = pRank;
    }

    public static Card10 get(Suit pSuit, Rank pRank) {
        assert pSuit != null && pRank != null;

        Card10 card = CARDS[pSuit.ordinal()][pRank.ordinal()];
        if (card == null) {
            card = new Card10(pSuit, pRank);
            CARDS[pSuit.ordinal()][pRank.ordinal()] = card;
        }

        return card;
    }

    public Suit getSuit() {
        return aSuit;
    }

    public Rank getRank() {
        return aRank;
    }

    @Override
	public String toString()
	{
		return String.format("%s of %s", aRank, aSuit);
	}
}
