package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class TransactionNotReadableException extends TransactionNotActiveException {
   private static final long serialVersionUID = -2296343182895752406L;

   public TransactionNotReadableException() {
      super(Localiser.msg("015041"), (Object)null);
   }

   public TransactionNotReadableException(String message, Object failedObject) {
      super(message, failedObject);
   }
}
