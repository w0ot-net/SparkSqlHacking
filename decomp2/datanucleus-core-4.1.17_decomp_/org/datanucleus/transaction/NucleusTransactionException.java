package org.datanucleus.transaction;

import org.datanucleus.exceptions.NucleusException;

public class NucleusTransactionException extends NucleusException {
   private static final long serialVersionUID = 2150861697402241048L;

   public NucleusTransactionException() {
   }

   public NucleusTransactionException(String message) {
      super(message);
   }

   public NucleusTransactionException(String message, Throwable exception) {
      super(message, exception);
   }

   public NucleusTransactionException(String message, Throwable[] exceptions) {
      super(message, exceptions);
   }
}
