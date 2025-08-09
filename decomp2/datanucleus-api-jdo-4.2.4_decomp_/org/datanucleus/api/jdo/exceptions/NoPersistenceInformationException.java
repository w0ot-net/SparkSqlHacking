package org.datanucleus.api.jdo.exceptions;

import javax.jdo.JDOUserException;

public class NoPersistenceInformationException extends JDOUserException {
   private static final long serialVersionUID = 8218822469557539549L;

   public NoPersistenceInformationException(String msg, Exception nested) {
      super(msg, nested);
   }
}
