package org.apache.thrift.transport.sasl;

public class DataFrameHeaderReader extends FixedSizeHeaderReader {
   public static final int PAYLOAD_LENGTH_BYTES = 4;
   private int payloadSize;

   protected int headerSize() {
      return 4;
   }

   protected void onComplete() throws TInvalidSaslFrameException {
      this.payloadSize = this.byteBuffer.getInt(0);
      if (this.payloadSize < 0) {
         throw new TInvalidSaslFrameException("Payload size is negative: " + this.payloadSize);
      }
   }

   public int payloadSize() {
      return this.payloadSize;
   }
}
