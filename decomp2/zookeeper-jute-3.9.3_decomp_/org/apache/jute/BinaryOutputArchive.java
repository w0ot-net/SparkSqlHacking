package org.apache.jute;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.TreeMap;

public class BinaryOutputArchive implements OutputArchive {
   private ByteBuffer bb = ByteBuffer.allocate(1024);
   private DataOutput out;
   private long dataSize;

   public static BinaryOutputArchive getArchive(OutputStream strm) {
      return new BinaryOutputArchive(new DataOutputStream(strm));
   }

   public BinaryOutputArchive(DataOutput out) {
      this.out = out;
   }

   public void writeByte(byte b, String tag) throws IOException {
      this.out.writeByte(b);
      ++this.dataSize;
   }

   public void writeBool(boolean b, String tag) throws IOException {
      this.out.writeBoolean(b);
      ++this.dataSize;
   }

   public void writeInt(int i, String tag) throws IOException {
      this.out.writeInt(i);
      this.dataSize += 4L;
   }

   public void writeLong(long l, String tag) throws IOException {
      this.out.writeLong(l);
      this.dataSize += 8L;
   }

   public void writeFloat(float f, String tag) throws IOException {
      this.out.writeFloat(f);
      this.dataSize += 4L;
   }

   public void writeDouble(double d, String tag) throws IOException {
      this.out.writeDouble(d);
      this.dataSize += 8L;
   }

   private ByteBuffer stringToByteBuffer(CharSequence s) {
      this.bb.clear();
      int len = s.length();

      for(int i = 0; i < len; ++i) {
         if (this.bb.remaining() < 3) {
            ByteBuffer n = ByteBuffer.allocate(this.bb.capacity() << 1);
            this.bb.flip();
            n.put(this.bb);
            this.bb = n;
         }

         char c = s.charAt(i);
         if (c < 128) {
            this.bb.put((byte)c);
         } else if (c < 2048) {
            this.bb.put((byte)(192 | c >> 6));
            this.bb.put((byte)(128 | c & 63));
         } else {
            this.bb.put((byte)(224 | c >> 12));
            this.bb.put((byte)(128 | c >> 6 & 63));
            this.bb.put((byte)(128 | c & 63));
         }
      }

      this.bb.flip();
      return this.bb;
   }

   public void writeString(String s, String tag) throws IOException {
      if (s == null) {
         this.writeInt(-1, "len");
      } else {
         ByteBuffer bb = this.stringToByteBuffer(s);
         int strLen = bb.remaining();
         this.writeInt(strLen, "len");
         this.out.write(bb.array(), bb.position(), bb.limit());
         this.dataSize += (long)strLen;
      }
   }

   public void writeBuffer(byte[] barr, String tag) throws IOException {
      if (barr == null) {
         this.writeInt(-1, "len");
      } else {
         int len = barr.length;
         this.writeInt(len, "len");
         this.out.write(barr);
         this.dataSize += (long)len;
      }
   }

   public void writeRecord(Record r, String tag) throws IOException {
      r.serialize(this, tag);
   }

   public void startRecord(Record r, String tag) throws IOException {
   }

   public void endRecord(Record r, String tag) throws IOException {
   }

   public void startVector(List v, String tag) throws IOException {
      if (v == null) {
         this.writeInt(-1, tag);
      } else {
         this.writeInt(v.size(), tag);
      }
   }

   public void endVector(List v, String tag) throws IOException {
   }

   public void startMap(TreeMap v, String tag) throws IOException {
      this.writeInt(v.size(), tag);
   }

   public void endMap(TreeMap v, String tag) throws IOException {
   }

   public long getDataSize() {
      return this.dataSize;
   }
}
