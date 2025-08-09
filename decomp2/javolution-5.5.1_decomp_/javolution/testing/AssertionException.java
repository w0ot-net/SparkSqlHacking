package javolution.testing;

public class AssertionException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public AssertionException() {
   }

   public AssertionException(String message) {
      super(message);
   }
}
