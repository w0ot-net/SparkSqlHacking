package javax.jdo;

public class JDOUnsupportedOptionException extends JDOUserException {
   private static final long serialVersionUID = -8357333650749157892L;

   public JDOUnsupportedOptionException() {
   }

   public JDOUnsupportedOptionException(String msg) {
      super(msg);
   }

   public JDOUnsupportedOptionException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOUnsupportedOptionException(String msg, Throwable nested) {
      super(msg, nested);
   }
}
