package io.jsonwebtoken.impl.io;

import java.io.InputStream;

public class Base64InputStream extends BaseNCodecInputStream {
   public Base64InputStream(InputStream inputStream) {
      this(inputStream, false);
   }

   Base64InputStream(InputStream inputStream, boolean doEncode) {
      super(inputStream, new Base64Codec(0, BaseNCodec.CHUNK_SEPARATOR, false, CodecPolicy.STRICT), doEncode);
   }
}
