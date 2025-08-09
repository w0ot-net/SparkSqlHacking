package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class ViewDefinitionException extends NucleusUserException {
   private static final long serialVersionUID = -3074298411767413664L;

   public ViewDefinitionException(String className, String viewDef) {
      super(Localiser.msg("020017", new Object[]{className, viewDef}));
      this.setFatal();
   }
}
