package org.jvnet.hk2.internal;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.ErrorInformation;
import org.glassfish.hk2.api.ErrorType;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.MultiException;

public class ErrorInformationImpl implements ErrorInformation {
   private final ErrorType errorType;
   private final Descriptor descriptor;
   private final Injectee injectee;
   private final MultiException exception;

   ErrorInformationImpl(ErrorType errorType, Descriptor descriptor, Injectee injectee, MultiException exception) {
      this.errorType = errorType;
      this.descriptor = descriptor;
      this.injectee = injectee;
      this.exception = exception;
   }

   public ErrorType getErrorType() {
      return this.errorType;
   }

   public Descriptor getDescriptor() {
      return this.descriptor;
   }

   public Injectee getInjectee() {
      return this.injectee;
   }

   public MultiException getAssociatedException() {
      return this.exception;
   }

   public String toString() {
      ErrorType var10000 = this.errorType;
      return "ErrorInformation(" + var10000 + "," + this.descriptor + "," + this.injectee + "," + this.exception + "," + System.identityHashCode(this) + ")";
   }
}
