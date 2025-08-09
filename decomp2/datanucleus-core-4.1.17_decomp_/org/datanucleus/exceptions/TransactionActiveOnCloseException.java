package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class TransactionActiveOnCloseException extends NucleusUserException {
   private static final long serialVersionUID = 8801501994814961125L;

   public TransactionActiveOnCloseException(Object failedObject) {
      super(Localiser.msg("015034"), failedObject);
   }
}
