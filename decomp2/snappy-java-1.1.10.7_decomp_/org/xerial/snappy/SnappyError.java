package org.xerial.snappy;

public class SnappyError extends Error {
   private static final long serialVersionUID = 1L;
   public final SnappyErrorCode errorCode;

   public SnappyError(SnappyErrorCode var1) {
      this.errorCode = var1;
   }

   public SnappyError(SnappyErrorCode var1, Error var2) {
      super(var2);
      this.errorCode = var1;
   }

   public SnappyError(SnappyErrorCode var1, String var2) {
      super(var2);
      this.errorCode = var1;
   }

   public String getMessage() {
      return String.format("[%s] %s", this.errorCode.name(), super.getMessage());
   }
}
