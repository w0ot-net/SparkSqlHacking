package org.apache.datasketches.memory;

public class ReadOnlyException extends MemoryException {
   private static final long serialVersionUID = 1L;

   public ReadOnlyException(String message) {
      super(message);
   }
}
