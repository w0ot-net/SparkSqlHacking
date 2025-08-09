package org.datanucleus.store.rdbms.exceptions;

public class MappedDatastoreException extends Exception {
   private static final long serialVersionUID = 4268561046702550087L;

   public MappedDatastoreException(String s, Throwable throwable) {
      super(s, throwable);
   }
}
