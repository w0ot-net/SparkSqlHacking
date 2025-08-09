package org.apache.avro.file;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class Codec {
   public abstract String getName();

   public abstract ByteBuffer compress(ByteBuffer uncompressedData) throws IOException;

   public abstract ByteBuffer decompress(ByteBuffer compressedData) throws IOException;

   public abstract boolean equals(Object other);

   public abstract int hashCode();

   public String toString() {
      return this.getName();
   }

   protected static int computeOffset(ByteBuffer data) {
      return data.arrayOffset() + data.position();
   }
}
