package net.razorvine.pickle;

public class InvalidOpcodeException extends PickleException {
   private static final long serialVersionUID = -7691944009311968713L;

   public InvalidOpcodeException(String message, Throwable cause) {
      super(message, cause);
   }

   public InvalidOpcodeException(String message) {
      super(message);
   }
}
