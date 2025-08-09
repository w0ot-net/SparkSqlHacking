package org.apache.datasketches.common;

public class SketchesException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public SketchesException(String message) {
      super(message);
   }

   public SketchesException(String message, Throwable cause) {
      super(message, cause);
   }
}
