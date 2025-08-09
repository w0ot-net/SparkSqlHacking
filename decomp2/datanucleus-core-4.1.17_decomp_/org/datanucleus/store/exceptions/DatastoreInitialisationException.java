package org.datanucleus.store.exceptions;

import org.datanucleus.exceptions.NucleusUserException;

public class DatastoreInitialisationException extends NucleusUserException {
   private static final long serialVersionUID = 3704576773794574913L;

   public DatastoreInitialisationException(String msg) {
      super(msg);
      this.setFatal();
   }

   public DatastoreInitialisationException(String msg, Throwable ex) {
      super(msg, ex);
      this.setFatal();
   }
}
