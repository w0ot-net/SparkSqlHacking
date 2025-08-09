package javax.jdo;

public class JDOEnhanceException extends JDOException {
   private static final long serialVersionUID = 7953336394264555958L;

   public JDOEnhanceException() {
   }

   public JDOEnhanceException(String msg) {
      super(msg);
   }

   public JDOEnhanceException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public JDOEnhanceException(String msg, Throwable nested) {
      super(msg, nested);
   }
}
