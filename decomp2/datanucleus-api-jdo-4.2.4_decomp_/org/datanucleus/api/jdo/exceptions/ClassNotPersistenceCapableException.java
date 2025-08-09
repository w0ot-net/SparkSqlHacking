package org.datanucleus.api.jdo.exceptions;

import javax.jdo.JDOUserException;

public class ClassNotPersistenceCapableException extends JDOUserException {
   private static final long serialVersionUID = -32361409923511910L;

   public ClassNotPersistenceCapableException(String msg) {
      super(msg);
   }

   public ClassNotPersistenceCapableException(String msg, Exception nested) {
      super(msg, nested);
   }
}
