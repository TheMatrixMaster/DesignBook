package e2.chapter4;

public interface Shufflable {
    public static final Shufflable NULL_SHUFFLABLE = new Shufflable() {
        @Override
        public void shuffle() {}    // do nothing
    };

    void shuffle();
}
