package org.datanucleus.exceptions;

import org.datanucleus.util.Localiser;

public class UnsupportedConnectionFactoryException extends NucleusUserException {
   private static final long serialVersionUID = 7626152395007903364L;

   public UnsupportedConnectionFactoryException(Object factory) {
      super(Localiser.msg("009001", factory));
   }
}
