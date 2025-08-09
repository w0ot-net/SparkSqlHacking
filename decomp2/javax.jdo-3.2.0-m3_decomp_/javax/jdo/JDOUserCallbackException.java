package javax.jdo;

public class JDOUserCallbackException extends JDOUserException {
   private static final long serialVersionUID = -3317062335034038699L;

   public JDOUserCallbackException() {
   }

   public JDOUserCallbackException(String msg) {
      super(msg);
   }

   public JDOUserCallbackException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOUserCallbackException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public JDOUserCallbackException(String msg, Object failed) {
      super(msg, failed);
   }

   public JDOUserCallbackException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public JDOUserCallbackException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
