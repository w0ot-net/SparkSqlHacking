package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusUserException;

public class ClassDefinitionException extends NucleusUserException {
   private static final long serialVersionUID = -611709032031993187L;

   public ClassDefinitionException() {
      this.setFatal();
   }

   public ClassDefinitionException(String msg) {
      super(msg);
      this.setFatal();
   }
}
