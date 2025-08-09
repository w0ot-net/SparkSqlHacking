package org.apache.datasketches.memory;

public class BufferPositionInvariantsException extends MemoryException {
   private static final long serialVersionUID = 1L;

   public BufferPositionInvariantsException(String details) {
      super(details);
   }
}
