package javax.jdo;

public class JDOFatalDataStoreException extends JDOFatalException {
   private static final long serialVersionUID = 8953679366316248154L;

   public JDOFatalDataStoreException() {
   }

   public JDOFatalDataStoreException(String msg) {
      super(msg);
   }

   public JDOFatalDataStoreException(String msg, Object failed) {
      super(msg, failed);
   }

   public JDOFatalDataStoreException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOFatalDataStoreException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public JDOFatalDataStoreException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public JDOFatalDataStoreException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
