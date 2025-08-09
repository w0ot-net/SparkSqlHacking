package org.apache.arrow.memory;

public class AllocatorClosedException extends RuntimeException {
   public AllocatorClosedException(String message) {
      super(message);
   }
}
