package org.apache.thrift.transport.sasl;

public class TInvalidSaslFrameException extends TSaslNegotiationException {
   public TInvalidSaslFrameException(String message) {
      super(TSaslNegotiationException.ErrorType.PROTOCOL_ERROR, message);
   }
}
