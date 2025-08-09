package org.apache.datasketches.common;

public class SketchesReadOnlyException extends SketchesException {
   private static final long serialVersionUID = 1L;

   public SketchesReadOnlyException() {
      super("Write operation attempted on a read-only class.");
   }

   public SketchesReadOnlyException(String message) {
      super(message);
   }
}
