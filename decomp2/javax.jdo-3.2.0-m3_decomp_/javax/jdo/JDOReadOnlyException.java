package javax.jdo;

public class JDOReadOnlyException extends JDOUserException {
   private static final long serialVersionUID = -6612305192571046806L;

   public JDOReadOnlyException() {
   }

   public JDOReadOnlyException(String msg) {
      super(msg);
   }

   public JDOReadOnlyException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOReadOnlyException(String msg, Throwable nested) {
      super(msg, nested);
   }
}
