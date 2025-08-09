package org.datanucleus.state;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Localiser;

public class IllegalStateTransitionException extends NucleusException {
   private static final long serialVersionUID = -1686259899799936448L;

   public IllegalStateTransitionException(LifeCycleState state, String transition, ObjectProvider op) {
      super(Localiser.msg("026027", transition, state, op));
      this.setFatal();
   }
}
