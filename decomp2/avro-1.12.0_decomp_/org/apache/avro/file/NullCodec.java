package org.apache.avro.file;

import java.io.IOException;
import java.nio.ByteBuffer;

final class NullCodec extends Codec {
   private static final NullCodec INSTANCE = new NullCodec();
   public static final CodecFactory OPTION = new Option();

   public String getName() {
      return "null";
   }

   public ByteBuffer compress(ByteBuffer buffer) throws IOException {
      return buffer;
   }

   public ByteBuffer decompress(ByteBuffer data) throws IOException {
      return data;
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else {
         return other != null && other.getClass() == this.getClass();
      }
   }

   public int hashCode() {
      return 2;
   }

   static class Option extends CodecFactory {
      protected Codec createInstance() {
         return NullCodec.INSTANCE;
      }
   }
}
