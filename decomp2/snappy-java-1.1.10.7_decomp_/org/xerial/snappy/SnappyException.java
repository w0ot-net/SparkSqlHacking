package org.xerial.snappy;

/** @deprecated */
@Deprecated
public class SnappyException extends Exception {
   private static final long serialVersionUID = 1L;
   public final SnappyErrorCode errorCode;

   public SnappyException(int var1) {
      this(SnappyErrorCode.getErrorCode(var1));
   }

   public SnappyException(SnappyErrorCode var1) {
      this.errorCode = var1;
   }

   public SnappyException(SnappyErrorCode var1, Exception var2) {
      super(var2);
      this.errorCode = var1;
   }

   public SnappyException(SnappyErrorCode var1, String var2) {
      super(var2);
      this.errorCode = var1;
   }

   public SnappyErrorCode getErrorCode() {
      return this.errorCode;
   }

   public static void throwException(int var0) throws SnappyException {
      throw new SnappyException(var0);
   }

   public String getMessage() {
      return String.format("[%s] %s", this.errorCode.name(), super.getMessage());
   }
}
