package org.datanucleus.store.valuegenerator;

import org.datanucleus.exceptions.NucleusException;

public class ValueGenerationException extends NucleusException {
   private static final long serialVersionUID = -3825260450088569187L;

   public ValueGenerationException(String message) {
      super(message);
   }

   public ValueGenerationException(String message, Throwable nested) {
      super(message, nested);
   }
}
