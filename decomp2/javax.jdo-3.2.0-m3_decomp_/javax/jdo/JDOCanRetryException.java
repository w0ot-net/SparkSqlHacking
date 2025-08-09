package javax.jdo;

public class JDOCanRetryException extends JDOException {
   private static final long serialVersionUID = 3046202905626157173L;

   public JDOCanRetryException() {
   }

   public JDOCanRetryException(String msg) {
      super(msg);
   }

   public JDOCanRetryException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOCanRetryException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public JDOCanRetryException(String msg, Object failed) {
      super(msg, failed);
   }

   public JDOCanRetryException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public JDOCanRetryException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
