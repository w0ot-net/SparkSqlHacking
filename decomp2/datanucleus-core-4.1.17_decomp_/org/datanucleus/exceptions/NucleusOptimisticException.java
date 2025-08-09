package org.datanucleus.exceptions;

public class NucleusOptimisticException extends NucleusException {
   private static final long serialVersionUID = 1796935497958670004L;

   public NucleusOptimisticException() {
   }

   public NucleusOptimisticException(String msg) {
      super(msg);
   }

   public NucleusOptimisticException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public NucleusOptimisticException(String msg, Object failed) {
      super(msg, failed);
   }
}
