package org.datanucleus.exceptions;

public class NucleusUnsupportedOptionException extends NucleusUserException {
   private static final long serialVersionUID = 320589235766151399L;

   public NucleusUnsupportedOptionException() {
   }

   public NucleusUnsupportedOptionException(String msg) {
      super(msg);
   }

   public NucleusUnsupportedOptionException(String msg, Throwable[] nested) {
      super(msg, nested);
   }
}
