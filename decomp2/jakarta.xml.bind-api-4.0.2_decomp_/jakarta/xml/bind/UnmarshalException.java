package jakarta.xml.bind;

public class UnmarshalException extends JAXBException {
   private static final long serialVersionUID = 6121932693435295453L;

   public UnmarshalException(String message) {
      this(message, (String)null, (Throwable)null);
   }

   public UnmarshalException(String message, String errorCode) {
      this(message, errorCode, (Throwable)null);
   }

   public UnmarshalException(Throwable exception) {
      this((String)null, (String)null, exception);
   }

   public UnmarshalException(String message, Throwable exception) {
      this(message, (String)null, exception);
   }

   public UnmarshalException(String message, String errorCode, Throwable exception) {
      super(message, errorCode, exception);
   }
}
