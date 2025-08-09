package org.datanucleus.exceptions;

public class NucleusUserException extends NucleusException {
   private static final long serialVersionUID = 3656872982168776107L;

   public NucleusUserException() {
   }

   public NucleusUserException(String msg) {
      super(msg);
   }

   public NucleusUserException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public NucleusUserException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public NucleusUserException(String msg, Object failed) {
      super(msg, failed);
   }

   public NucleusUserException(String msg, Throwable[] nested, Object failed) {
      super(msg, nested, failed);
   }

   public NucleusUserException(String msg, Throwable nested, Object failed) {
      super(msg, nested, failed);
   }
}
