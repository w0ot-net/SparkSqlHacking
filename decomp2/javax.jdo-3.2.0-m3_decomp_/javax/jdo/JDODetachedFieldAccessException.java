package javax.jdo;

public class JDODetachedFieldAccessException extends JDOUserException {
   private static final long serialVersionUID = 5035418119865472625L;

   public JDODetachedFieldAccessException() {
   }

   public JDODetachedFieldAccessException(String msg) {
      super(msg);
   }

   public JDODetachedFieldAccessException(String msg, Object failed) {
      super(msg, failed);
   }

   public JDODetachedFieldAccessException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDODetachedFieldAccessException(String msg, Throwable nested) {
      super(msg, nested);
   }
}
