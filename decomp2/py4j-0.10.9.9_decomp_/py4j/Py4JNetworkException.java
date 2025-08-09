package py4j;

public class Py4JNetworkException extends Py4JException {
   private static final long serialVersionUID = -3338931855286981212L;
   private final ErrorTime when;

   public Py4JNetworkException() {
      this((String)null, (Throwable)null, Py4JNetworkException.ErrorTime.OTHER);
   }

   public Py4JNetworkException(ErrorTime when) {
      this((String)null, (Throwable)null, when);
   }

   public Py4JNetworkException(String message) {
      this(message, (Throwable)null, Py4JNetworkException.ErrorTime.OTHER);
   }

   public Py4JNetworkException(String message, ErrorTime when) {
      this(message, (Throwable)null, when);
   }

   public Py4JNetworkException(String message, Throwable cause) {
      this(message, cause, Py4JNetworkException.ErrorTime.OTHER);
   }

   public Py4JNetworkException(String message, Throwable cause, ErrorTime when) {
      super(message, cause);
      this.when = when;
   }

   public Py4JNetworkException(Throwable cause) {
      this((String)null, cause, Py4JNetworkException.ErrorTime.OTHER);
   }

   public ErrorTime getWhen() {
      return this.when;
   }

   public static enum ErrorTime {
      ERROR_ON_SEND,
      ERROR_ON_RECEIVE,
      OTHER;
   }
}
