package javolution.context;

public class ConcurrentException extends RuntimeException {
   private Throwable _cause;
   private static final long serialVersionUID = 1L;

   ConcurrentException(Throwable cause) {
      this._cause = cause;
   }

   public Throwable getCause() {
      return this._cause;
   }
}
