package org.apache.arrow.vector.ipc;

public class InvalidArrowFileException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public InvalidArrowFileException(String message) {
      super(message);
   }
}
