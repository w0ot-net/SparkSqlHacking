package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.util.Localiser;

public class PersistentSuperclassNotAllowedException extends ClassDefinitionException {
   private static final long serialVersionUID = -2749266341448447043L;

   public PersistentSuperclassNotAllowedException(String className) {
      super(Localiser.msg("020023", new Object[]{className}));
   }
}
