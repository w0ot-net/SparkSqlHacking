package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class ClassNotDetachableException extends NucleusUserException {
   private static final long serialVersionUID = 1689130917666545480L;

   public ClassNotDetachableException(String class_name) {
      super(Localiser.msg("018004", class_name));
   }

   public ClassNotDetachableException(String class_name, Exception nested) {
      super(Localiser.msg("018004", class_name), (Throwable)nested);
   }

   public ClassNotDetachableException(Throwable[] nested) {
      super(Localiser.msg("018005"), nested);
   }
}
