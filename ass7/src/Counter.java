/**
 * Eitan Kerzhner
 * 205697139
 * kerzhne
 */

/**
 * The type Counter.
 */
public class Counter {
    // might be problem - didn't use constructor
    private int counter;

    /**
     * Instantiates a new Counter.
     *
     * @param c the c
     */
    public Counter(int c) {
        this.counter = c;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.counter;
    }
}
