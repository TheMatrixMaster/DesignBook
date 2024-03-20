package e2.chapter4;

public class FancyCard extends Card {
    public enum Style {
        CLASSIC,
        ARTISTIC,
        Fun
    };

    private final Style aStyle;

    public FancyCard(Rank pRank, Suit pSuit, Style pStyle) {
        super(pRank, pSuit);
        aStyle = pStyle;
    }

    public FancyCard(boolean pIsWhiteJoker, Style pStyle) {
        super(pIsWhiteJoker);
        aStyle = pStyle;
    }

    public Style getStyle() {
        return aStyle;
    }

    public static void main(String[] args) {
        Card card1 = new Card(Rank.ACE, Suit.CLUBS);
        Card card2 = new FancyCard(Rank.ACE, Suit.CLUBS, FancyCard.Style.CLASSIC);
        Card card3 = new FancyCard(Rank.ACE, Suit.CLUBS, FancyCard.Style.ARTISTIC);

        System.out.println(card1.getClass() == card2.getClass());
        System.out.println(card2.getClass() == card3.getClass());
        System.out.println(card3.getClass() == card1.getClass());
        System.out.println();

        System.out.println(card1.equals(card2));
        System.out.println(card2.equals(card1));
        System.out.println(card2.equals(card3));
        System.out.println(card3.equals(card1));
    }
}
