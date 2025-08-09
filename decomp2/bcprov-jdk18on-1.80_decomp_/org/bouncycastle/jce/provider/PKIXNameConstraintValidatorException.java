package org.bouncycastle.jce.provider;

public class PKIXNameConstraintValidatorException extends Exception {
   private Throwable cause;

   public PKIXNameConstraintValidatorException(String var1) {
      super(var1);
   }

   public PKIXNameConstraintValidatorException(String var1, Throwable var2) {
      super(var1);
      this.cause = var2;
   }

   public Throwable getCause() {
      return this.cause;
   }
}
