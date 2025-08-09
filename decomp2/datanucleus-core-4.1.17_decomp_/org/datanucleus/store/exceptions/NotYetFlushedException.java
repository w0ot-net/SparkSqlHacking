package org.datanucleus.store.exceptions;

import org.datanucleus.exceptions.NucleusException;

public class NotYetFlushedException extends NucleusException {
   private static final long serialVersionUID = 6053032947592880580L;
   private final Object pc;

   public NotYetFlushedException(Object pc) {
      super("not yet flushed");
      this.pc = pc;
   }

   public Object getPersistable() {
      return this.pc;
   }
}
