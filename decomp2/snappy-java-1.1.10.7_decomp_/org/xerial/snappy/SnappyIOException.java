package org.xerial.snappy;

import java.io.IOException;

public class SnappyIOException extends IOException {
   private final SnappyErrorCode errorCode;

   public SnappyIOException(SnappyErrorCode var1, String var2) {
      super(var2);
      this.errorCode = var1;
   }

   public String getMessage() {
      return String.format("[%s] %s", this.errorCode.name(), super.getMessage());
   }

   public SnappyErrorCode getErrorCode() {
      return this.errorCode;
   }
}
