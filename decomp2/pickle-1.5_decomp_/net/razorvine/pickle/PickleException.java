package net.razorvine.pickle;

public class PickleException extends RuntimeException {
   private static final long serialVersionUID = -5870448664938735316L;

   public PickleException(String message, Throwable cause) {
      super(message, cause);
   }

   public PickleException(String message) {
      super(message);
   }
}
