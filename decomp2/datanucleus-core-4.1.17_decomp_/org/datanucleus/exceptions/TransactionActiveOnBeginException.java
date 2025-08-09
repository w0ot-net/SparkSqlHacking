package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class TransactionActiveOnBeginException extends NucleusUserException {
   private static final long serialVersionUID = -7077528155288394047L;

   public TransactionActiveOnBeginException(Object failedObject) {
      super(Localiser.msg("015033"), failedObject);
   }
}
