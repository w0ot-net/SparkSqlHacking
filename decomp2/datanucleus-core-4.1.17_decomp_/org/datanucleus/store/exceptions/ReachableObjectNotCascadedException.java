package org.datanucleus.store.exceptions;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class ReachableObjectNotCascadedException extends NucleusUserException {
   private static final long serialVersionUID = -6261382506454022719L;

   public ReachableObjectNotCascadedException(String fieldName, Object pc) {
      super(Localiser.msg("018008", fieldName, pc));
   }
}
