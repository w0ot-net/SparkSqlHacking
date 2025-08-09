package javax.jdo;

public class JDOFatalUserException extends JDOFatalException {
   private static final long serialVersionUID = -7451285498620809419L;

   public JDOFatalUserException() {
   }

   public JDOFatalUserException(String msg) {
      super(msg);
   }

   public JDOFatalUserException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOFatalUserException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public JDOFatalUserException(String msg, Object failed) {
      super(msg, failed);
   }

   public JDOFatalUserException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public JDOFatalUserException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
