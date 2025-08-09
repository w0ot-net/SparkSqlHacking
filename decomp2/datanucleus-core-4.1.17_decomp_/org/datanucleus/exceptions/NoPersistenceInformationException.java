package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class NoPersistenceInformationException extends NucleusUserException {
   private static final long serialVersionUID = -7436790264202971943L;

   public NoPersistenceInformationException(String className) {
      super(Localiser.msg("018001", className));
   }

   public NoPersistenceInformationException(String className, Exception nested) {
      super(Localiser.msg("018001", className), (Throwable)nested);
   }
}
