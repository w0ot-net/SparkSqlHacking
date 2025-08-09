package org.apache.thrift.transport.sasl;

import org.apache.thrift.transport.TTransportException;

public class TSaslNegotiationException extends TTransportException {
   private final ErrorType error;

   public TSaslNegotiationException(ErrorType error, String summary) {
      super(summary);
      this.error = error;
   }

   public TSaslNegotiationException(ErrorType error, String summary, Throwable cause) {
      super(summary, cause);
      this.error = error;
   }

   public ErrorType getErrorType() {
      return this.error;
   }

   public String getSummary() {
      return this.error.name() + ": " + this.getMessage();
   }

   public String getDetails() {
      return this.getCause() == null ? this.getSummary() : this.getSummary() + "\nReason: " + this.getCause().getMessage();
   }

   public static enum ErrorType {
      INTERNAL_ERROR(NegotiationStatus.ERROR),
      PROTOCOL_ERROR(NegotiationStatus.ERROR),
      MECHANISME_MISMATCH(NegotiationStatus.BAD),
      AUTHENTICATION_FAILURE(NegotiationStatus.BAD);

      public final NegotiationStatus code;

      private ErrorType(NegotiationStatus code) {
         this.code = code;
      }
   }
}
