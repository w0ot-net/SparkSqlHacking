package org.datanucleus.api.jdo.exceptions;

import org.datanucleus.util.Localiser;

public class TransactionNotReadableException extends TransactionNotActiveException {
   private static final long serialVersionUID = 2573953722247061663L;

   public TransactionNotReadableException() {
      super(Localiser.msg("015041"), (Object)null);
   }

   public TransactionNotReadableException(String message, Object failedObject) {
      super(message, failedObject);
   }
}
