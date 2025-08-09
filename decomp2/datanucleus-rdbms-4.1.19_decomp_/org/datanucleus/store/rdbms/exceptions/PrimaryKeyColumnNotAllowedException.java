package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Localiser;

public class PrimaryKeyColumnNotAllowedException extends NucleusException {
   private static final long serialVersionUID = 4600461704079232724L;

   public PrimaryKeyColumnNotAllowedException(String viewName, String columnName) {
      super(Localiser.msg("020014", new Object[]{viewName, columnName}));
      this.setFatal();
   }
}
