package py4j;

public class Py4JAuthenticationException extends Py4JException {
   private static final long serialVersionUID = -8121049552991789743L;

   public Py4JAuthenticationException() {
   }

   public Py4JAuthenticationException(String message) {
      super(message);
   }

   public Py4JAuthenticationException(String message, Throwable cause) {
      super(message, cause);
   }

   public Py4JAuthenticationException(Throwable cause) {
   }
}
