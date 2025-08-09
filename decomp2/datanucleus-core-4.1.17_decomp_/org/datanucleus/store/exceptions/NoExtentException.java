package org.datanucleus.store.exceptions;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class NoExtentException extends NucleusUserException {
   private static final long serialVersionUID = 3515714815763489073L;

   public NoExtentException(String className) {
      super(Localiser.msg("018007", className));
   }
}
