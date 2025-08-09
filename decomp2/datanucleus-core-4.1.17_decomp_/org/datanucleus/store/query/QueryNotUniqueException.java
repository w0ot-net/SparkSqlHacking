package org.datanucleus.store.query;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class QueryNotUniqueException extends NucleusUserException {
   private static final long serialVersionUID = -7458437974414546277L;

   public QueryNotUniqueException() {
      super(Localiser.msg("021001"));
   }
}
