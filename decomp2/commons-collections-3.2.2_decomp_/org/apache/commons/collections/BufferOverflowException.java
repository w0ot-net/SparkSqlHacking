package org.apache.commons.collections;

public class BufferOverflowException extends RuntimeException {
   private final Throwable throwable;

   public BufferOverflowException() {
      this.throwable = null;
   }

   public BufferOverflowException(String message) {
      this(message, (Throwable)null);
   }

   public BufferOverflowException(String message, Throwable exception) {
      super(message);
      this.throwable = exception;
   }

   public final Throwable getCause() {
      return this.throwable;
   }
}
