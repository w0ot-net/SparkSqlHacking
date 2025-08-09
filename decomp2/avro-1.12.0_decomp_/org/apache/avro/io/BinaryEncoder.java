package org.apache.avro.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.apache.avro.util.Utf8;

public abstract class BinaryEncoder extends Encoder {
   public void writeNull() throws IOException {
   }

   public void writeString(Utf8 utf8) throws IOException {
      this.writeBytes(utf8.getBytes(), 0, utf8.getByteLength());
   }

   public void writeString(String string) throws IOException {
      if (string.isEmpty()) {
         this.writeZero();
      } else {
         byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
         this.writeInt(bytes.length);
         this.writeFixed(bytes, 0, bytes.length);
      }
   }

   public void writeBytes(ByteBuffer bytes) throws IOException {
      int len = bytes.limit() - bytes.position();
      if (0 == len) {
         this.writeZero();
      } else {
         this.writeInt(len);
         this.writeFixed(bytes);
      }

   }

   public void writeBytes(byte[] bytes, int start, int len) throws IOException {
      if (0 == len) {
         this.writeZero();
      } else {
         this.writeInt(len);
         this.writeFixed(bytes, start, len);
      }
   }

   public void writeEnum(int e) throws IOException {
      this.writeInt(e);
   }

   public void writeArrayStart() throws IOException {
   }

   public void setItemCount(long itemCount) throws IOException {
      if (itemCount > 0L) {
         this.writeLong(itemCount);
      }

   }

   public void startItem() throws IOException {
   }

   public void writeArrayEnd() throws IOException {
      this.writeZero();
   }

   public void writeMapStart() throws IOException {
   }

   public void writeMapEnd() throws IOException {
      this.writeZero();
   }

   public void writeIndex(int unionIndex) throws IOException {
      this.writeInt(unionIndex);
   }

   protected abstract void writeZero() throws IOException;

   public abstract int bytesBuffered();
}
