package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class ConnectionFactoryNotFoundException extends NucleusUserException {
   private static final long serialVersionUID = -2474386281568460880L;

   public ConnectionFactoryNotFoundException(String name, Exception nested) {
      super(Localiser.msg("009002", name), (Throwable)nested);
   }
}
