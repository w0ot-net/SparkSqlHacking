package org.datanucleus.store.query;

import org.datanucleus.exceptions.NucleusException;

public class QueryTimeoutException extends NucleusException {
   private static final long serialVersionUID = -4856425363125315854L;

   public QueryTimeoutException() {
   }

   public QueryTimeoutException(String msg) {
      super(msg);
   }

   public QueryTimeoutException(String msg, Throwable e) {
      super(msg, e);
   }
}
