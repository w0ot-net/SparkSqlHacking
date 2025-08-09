package py4j;

public class Py4JJavaException extends Py4JException {
   private static final long serialVersionUID = -8121049552991788743L;

   public Py4JJavaException() {
   }

   public Py4JJavaException(String message) {
      super(message);
   }

   public Py4JJavaException(String message, Throwable cause) {
      super(message, cause);
   }

   public Py4JJavaException(Throwable cause) {
      super(cause);
   }
}
