package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class RollbackStateTransitionException extends NucleusException {
   private static final long serialVersionUID = -174876092935572477L;

   public RollbackStateTransitionException(Exception[] nested) {
      super(Localiser.msg("015031"), (Throwable[])nested);
      this.setFatal();
   }
}
