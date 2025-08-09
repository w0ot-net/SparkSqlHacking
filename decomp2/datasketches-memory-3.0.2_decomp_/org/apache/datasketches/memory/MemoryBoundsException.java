package org.apache.datasketches.memory;

public class MemoryBoundsException extends MemoryException {
   private static final long serialVersionUID = 1L;

   public MemoryBoundsException(String details) {
      super(details);
   }
}
