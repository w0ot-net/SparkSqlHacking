package org.datanucleus.store.query;

import org.datanucleus.exceptions.NucleusException;

public class NoQueryResultsException extends NucleusException {
   private static final long serialVersionUID = -5046488758382548159L;

   public NoQueryResultsException(String msg) {
      super(msg);
   }
}
