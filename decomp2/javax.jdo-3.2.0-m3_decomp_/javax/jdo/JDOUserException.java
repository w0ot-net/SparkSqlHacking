package javax.jdo;

public class JDOUserException extends JDOCanRetryException {
   private static final long serialVersionUID = 2552001511762583039L;

   public JDOUserException() {
   }

   public JDOUserException(String msg) {
      super(msg);
   }

   public JDOUserException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOUserException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public JDOUserException(String msg, Object failed) {
      super(msg, failed);
   }

   public JDOUserException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public JDOUserException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
