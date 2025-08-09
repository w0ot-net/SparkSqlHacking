package io.jsonwebtoken.impl.io;

import java.io.OutputStream;

class Base64OutputStream extends BaseNCodecOutputStream {
   Base64OutputStream(OutputStream outputStream) {
      this(outputStream, true, true);
   }

   Base64OutputStream(OutputStream outputStream, boolean doEncode, boolean urlSafe) {
      super(outputStream, new Base64Codec(0, BaseNCodec.CHUNK_SEPARATOR, urlSafe), doEncode);
   }

   Base64OutputStream(OutputStream outputStream, boolean doEncode, int lineLength, byte[] lineSeparator) {
      super(outputStream, new Base64Codec(lineLength, lineSeparator), doEncode);
   }

   Base64OutputStream(OutputStream outputStream, boolean doEncode, int lineLength, byte[] lineSeparator, CodecPolicy decodingPolicy) {
      super(outputStream, new Base64Codec(lineLength, lineSeparator, false, decodingPolicy), doEncode);
   }
}
