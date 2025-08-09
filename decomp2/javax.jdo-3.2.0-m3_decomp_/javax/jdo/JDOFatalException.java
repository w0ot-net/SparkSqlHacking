package javax.jdo;

public class JDOFatalException extends JDOException {
   private static final long serialVersionUID = -414371106009364006L;

   public JDOFatalException() {
   }

   public JDOFatalException(String msg) {
      super(msg);
   }

   public JDOFatalException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOFatalException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public JDOFatalException(String msg, Object failed) {
      super(msg, failed);
   }

   public JDOFatalException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public JDOFatalException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
