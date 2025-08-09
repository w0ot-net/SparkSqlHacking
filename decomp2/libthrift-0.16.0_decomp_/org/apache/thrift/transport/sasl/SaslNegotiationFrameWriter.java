package org.apache.thrift.transport.sasl;

import java.nio.ByteBuffer;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.utils.StringUtils;

public class SaslNegotiationFrameWriter extends FrameWriter {
   public static final int HEADER_BYTES = 5;

   public void withOnlyPayload(byte[] payload, int offset, int length) {
      throw new UnsupportedOperationException("Status byte is expected for sasl frame header.");
   }

   protected ByteBuffer buildFrame(byte[] header, int headerOffset, int headerLength, byte[] payload, int payloadOffset, int payloadLength) {
      if (header != null && headerLength == 1) {
         byte[] bytes = new byte[5 + payloadLength];
         System.arraycopy(header, headerOffset, bytes, 0, 1);
         EncodingUtils.encodeBigEndian(payloadLength, bytes, 1);
         System.arraycopy(payload, payloadOffset, bytes, 5, payloadLength);
         return ByteBuffer.wrap(bytes);
      } else {
         throw new IllegalArgumentException("Header " + StringUtils.bytesToHexString(header) + " does not have expected length " + 1);
      }
   }
}
