package org.apache.thrift.transport.sasl;

public class SaslNegotiationFrameReader extends FrameReader {
   public SaslNegotiationFrameReader() {
      super(new SaslNegotiationHeaderReader());
   }
}
