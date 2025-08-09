package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class CommitStateTransitionException extends NucleusException {
   private static final long serialVersionUID = 5977558567821991933L;

   public CommitStateTransitionException(Exception[] nested) {
      super(Localiser.msg("015037"), (Throwable[])nested);
   }
}
