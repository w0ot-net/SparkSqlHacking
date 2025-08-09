package org.datanucleus.store.types;

import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.util.Localiser;

public class IncompatibleFieldTypeException extends NucleusUserException {
   private static final long serialVersionUID = 6864005515921540632L;

   public IncompatibleFieldTypeException(String classAndFieldName, String requiredTypeName, String requestedTypeName) {
      super(Localiser.msg("023000", classAndFieldName, requiredTypeName, requestedTypeName));
   }
}
