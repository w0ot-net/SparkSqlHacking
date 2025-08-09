package org.datanucleus.store.rdbms.exceptions;

import org.datanucleus.exceptions.NucleusUserException;

public class ColumnDefinitionException extends NucleusUserException {
   private static final long serialVersionUID = -6850676317990100517L;

   public ColumnDefinitionException() {
      this.setFatal();
   }

   public ColumnDefinitionException(String msg) {
      super(msg);
      this.setFatal();
   }
}
