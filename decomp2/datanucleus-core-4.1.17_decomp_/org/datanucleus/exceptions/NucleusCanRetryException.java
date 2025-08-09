package org.datanucleus.exceptions;

public class NucleusCanRetryException extends NucleusException {
   private static final long serialVersionUID = 2772116140793944515L;

   public NucleusCanRetryException() {
   }

   public NucleusCanRetryException(String msg) {
      super(msg);
   }

   public NucleusCanRetryException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public NucleusCanRetryException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public NucleusCanRetryException(String msg, Object failed) {
      super(msg, failed);
   }

   public NucleusCanRetryException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public NucleusCanRetryException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
