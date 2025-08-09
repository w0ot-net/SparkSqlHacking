package org.apache.datasketches.memory;

public class MemoryException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public MemoryException(String message) {
      super(message);
   }
}
