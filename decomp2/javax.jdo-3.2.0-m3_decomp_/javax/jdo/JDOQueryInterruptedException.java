package javax.jdo;

public class JDOQueryInterruptedException extends JDOUserException {
   private static final long serialVersionUID = -5832046548440677226L;

   public JDOQueryInterruptedException() {
   }

   public JDOQueryInterruptedException(String msg) {
      super(msg);
   }
}
