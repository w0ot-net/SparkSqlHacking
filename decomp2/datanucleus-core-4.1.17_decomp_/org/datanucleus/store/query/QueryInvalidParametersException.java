package org.datanucleus.store.query;

import org.datanucleus.exceptions.NucleusUserException;

public class QueryInvalidParametersException extends NucleusUserException {
   private static final long serialVersionUID = -8859715327729874369L;

   public QueryInvalidParametersException(String msg) {
      super(msg);
   }
}
