package org.apache.thrift.transport.sasl;

import java.nio.ByteBuffer;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.utils.StringUtils;

public class DataFrameWriter extends FrameWriter {
   public void withOnlyPayload(byte[] payload, int offset, int length) {
      if (!this.isComplete()) {
         throw new IllegalStateException("Previsous write is not yet complete, with " + this.frameBytes.remaining() + " bytes left.");
      } else {
         this.frameBytes = this.buildFrameWithPayload(payload, offset, length);
      }
   }

   protected ByteBuffer buildFrame(byte[] header, int headerOffset, int headerLength, byte[] payload, int payloadOffset, int payloadLength) {
      if (header != null && headerLength > 0) {
         throw new IllegalArgumentException("Extra header [" + StringUtils.bytesToHexString(header) + "] offset " + payloadOffset + " length " + payloadLength);
      } else {
         return this.buildFrameWithPayload(payload, payloadOffset, payloadLength);
      }
   }

   private ByteBuffer buildFrameWithPayload(byte[] payload, int offset, int length) {
      byte[] bytes = new byte[4 + length];
      EncodingUtils.encodeBigEndian(length, bytes, 0);
      System.arraycopy(payload, offset, bytes, 4, length);
      return ByteBuffer.wrap(bytes);
   }
}
