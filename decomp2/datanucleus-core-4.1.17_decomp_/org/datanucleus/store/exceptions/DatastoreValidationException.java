package org.datanucleus.store.exceptions;

import org.datanucleus.exceptions.NucleusDataStoreException;

public class DatastoreValidationException extends NucleusDataStoreException {
   private static final long serialVersionUID = 5531946566608396432L;

   public DatastoreValidationException(String msg) {
      super(msg);
   }

   public DatastoreValidationException(String msg, Exception nested) {
      super(msg, (Throwable)nested);
   }
}
