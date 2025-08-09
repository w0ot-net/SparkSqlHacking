package org.datanucleus.enhancer;

import org.datanucleus.exceptions.NucleusException;

public class NucleusEnhanceException extends NucleusException {
   private static final long serialVersionUID = -478183137030915917L;

   public NucleusEnhanceException(String msg) {
      super(msg);
   }

   public NucleusEnhanceException(String msg, Throwable[] nested) {
      super(msg, nested);
   }

   public NucleusEnhanceException(String msg, Throwable nested) {
      super(msg, nested);
   }
}
