package org.datanucleus.exceptions;

public class ClassNotResolvedException extends NucleusException {
   private static final long serialVersionUID = -397832231669819435L;

   public ClassNotResolvedException(String msg, Throwable nested) {
      super(msg, nested);
   }

   public ClassNotResolvedException(String msg) {
      super(msg);
   }
}
