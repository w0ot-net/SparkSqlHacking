package org.datanucleus.store.query;

import org.datanucleus.exceptions.NucleusException;

public class QueryInterruptedException extends NucleusException {
   private static final long serialVersionUID = 4875874100202871443L;

   public QueryInterruptedException() {
   }

   public QueryInterruptedException(String msg) {
      super(msg);
   }

   public QueryInterruptedException(String msg, Throwable e) {
      super(msg, e);
   }
}
