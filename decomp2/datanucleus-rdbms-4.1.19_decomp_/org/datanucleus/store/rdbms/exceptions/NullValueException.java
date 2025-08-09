package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusDataStoreException;

public class NullValueException extends NucleusDataStoreException {
   private static final long serialVersionUID = -4852762927328278822L;

   public NullValueException() {
   }

   public NullValueException(String msg) {
      super(msg);
   }
}
