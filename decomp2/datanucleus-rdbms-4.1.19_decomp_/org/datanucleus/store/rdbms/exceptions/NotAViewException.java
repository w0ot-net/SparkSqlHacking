package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.store.exceptions.DatastoreValidationException;
import org.datanucleus.util.Localiser;

public class NotAViewException extends DatastoreValidationException {
   private static final long serialVersionUID = -3924285872907278607L;

   public NotAViewException(String viewName, String type) {
      super(Localiser.msg("020013", new Object[]{viewName, type}));
   }
}
