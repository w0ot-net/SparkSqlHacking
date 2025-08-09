package org.datanucleus.api.jdo.exceptions;

import javax.jdo.JDOUserException;
import org.datanucleus.util.Localiser;

public class ConnectionInUseException extends JDOUserException {
   private static final long serialVersionUID = -7180648507612667518L;

   public ConnectionInUseException() {
      super(Localiser.msg("009003"));
   }
}
