package org.apache.thrift.transport.sasl;

public class SaslNegotiationHeaderReader extends FixedSizeHeaderReader {
   public static final int STATUS_BYTES = 1;
   public static final int PAYLOAD_LENGTH_BYTES = 4;
   private NegotiationStatus negotiationStatus;
   private int payloadSize;

   protected int headerSize() {
      return 5;
   }

   protected void onComplete() throws TSaslNegotiationException {
      this.negotiationStatus = NegotiationStatus.byValue(this.byteBuffer.get(0));
      this.payloadSize = this.byteBuffer.getInt(1);
      if (this.payloadSize < 0) {
         throw new TSaslNegotiationException(TSaslNegotiationException.ErrorType.PROTOCOL_ERROR, "Payload size is negative: " + this.payloadSize);
      }
   }

   public int payloadSize() {
      return this.payloadSize;
   }

   public NegotiationStatus getStatus() {
      return this.negotiationStatus;
   }
}
