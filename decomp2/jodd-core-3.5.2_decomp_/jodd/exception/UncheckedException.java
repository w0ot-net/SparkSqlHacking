package jodd.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class UncheckedException extends RuntimeException {
   protected final Throwable cause;
   protected static final String CAUSE_DIV = "---[cause]------------------------------------------------------------------------";
   protected final boolean showCauseDetails;

   public UncheckedException(Throwable t) {
      super(t.getMessage());
      this.cause = t;
      this.showCauseDetails = true;
   }

   public UncheckedException(Throwable t, boolean showCauseDetails) {
      super(t.getMessage());
      this.cause = t;
      this.showCauseDetails = showCauseDetails;
   }

   public UncheckedException() {
      this.cause = null;
      this.showCauseDetails = false;
   }

   public UncheckedException(String message) {
      super(message);
      this.cause = null;
      this.showCauseDetails = false;
   }

   public UncheckedException(String message, Throwable t) {
      super(message, t);
      this.cause = t;
      this.showCauseDetails = true;
   }

   public UncheckedException(String message, Throwable t, boolean showCauseDetails) {
      super(message, t);
      this.cause = t;
      this.showCauseDetails = showCauseDetails;
   }

   public void printStackTrace() {
      this.printStackTrace(System.err);
   }

   public void printStackTrace(PrintStream ps) {
      synchronized(ps) {
         super.printStackTrace(ps);
         if (this.cause != null && this.showCauseDetails) {
            Throwable rootCause = ExceptionUtil.getRootCause(this.cause);
            ps.println("---[cause]------------------------------------------------------------------------");
            rootCause.printStackTrace(ps);
         }

      }
   }

   public void printStackTrace(PrintWriter pw) {
      synchronized(pw) {
         super.printStackTrace(pw);
         if (this.cause != null && this.showCauseDetails) {
            Throwable rootCause = ExceptionUtil.getRootCause(this.cause);
            pw.println("---[cause]------------------------------------------------------------------------");
            rootCause.printStackTrace(pw);
         }

      }
   }

   public String getMessage() {
      return ExceptionUtil.buildMessage(super.getMessage(), this.cause);
   }

   public static RuntimeException wrapChecked(Throwable t) {
      return (RuntimeException)(t instanceof RuntimeException ? (RuntimeException)t : new UncheckedException(t));
   }

   public static RuntimeException wrap(Throwable t) {
      return new UncheckedException(t);
   }

   public static RuntimeException wrap(Throwable t, String message) {
      return new UncheckedException(message, t);
   }

   public void rethrow() throws Throwable {
      if (this.cause != null) {
         throw this.cause;
      }
   }

   public Throwable getCause() {
      return this.cause;
   }
}
