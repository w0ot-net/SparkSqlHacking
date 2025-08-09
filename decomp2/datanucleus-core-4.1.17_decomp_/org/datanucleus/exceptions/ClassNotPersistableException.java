package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class ClassNotPersistableException extends NucleusUserException {
   private static final long serialVersionUID = 3295175043550368870L;

   public ClassNotPersistableException(String className) {
      super(Localiser.msg("018000", className));
   }

   public ClassNotPersistableException(String className, Exception nested) {
      super(Localiser.msg("018000", className), (Throwable)nested);
   }
}
