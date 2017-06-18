package tegoods.model.exceptions;

/**
 *
 * @author igor
 */
public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ProductNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
