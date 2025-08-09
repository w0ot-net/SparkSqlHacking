package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import org.apache.arrow.flatbuf.BodyCompression;

public class ArrowBodyCompression implements FBSerializable {
   private final byte codec;
   private final byte method;

   public ArrowBodyCompression(byte codec, byte method) {
      this.codec = codec;
      this.method = method;
   }

   public int writeTo(FlatBufferBuilder builder) {
      return BodyCompression.createBodyCompression(builder, this.codec, this.method);
   }

   public byte getCodec() {
      return this.codec;
   }

   public byte getMethod() {
      return this.method;
   }

   public String toString() {
      return "ArrowBodyCompression [codec=" + this.codec + ", method=" + this.method + "]";
   }
}
