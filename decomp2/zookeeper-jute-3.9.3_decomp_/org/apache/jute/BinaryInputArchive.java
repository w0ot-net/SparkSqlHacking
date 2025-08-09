package org.apache.jute;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BinaryInputArchive implements InputArchive {
   public static final String UNREASONBLE_LENGTH = "Unreasonable length = ";
   public static final int maxBuffer = Integer.getInteger("jute.maxbuffer", 1048575);
   private static final int extraMaxBuffer;
   private final DataInput in;
   private final int totalBufferSize;

   public static BinaryInputArchive getArchive(InputStream stream) {
      return new BinaryInputArchive(new DataInputStream(stream));
   }

   public BinaryInputArchive(DataInput in) {
      this(in, maxBuffer, extraMaxBuffer);
   }

   public BinaryInputArchive(DataInput in, int maxBufferSize, int extraMaxBufferSize) {
      this.in = in;
      if ((long)maxBufferSize + (long)extraMaxBufferSize > 2147483647L) {
         this.totalBufferSize = Integer.MAX_VALUE;
      } else {
         this.totalBufferSize = maxBufferSize + extraMaxBufferSize;
      }

   }

   public byte readByte(String tag) throws IOException {
      return this.in.readByte();
   }

   public boolean readBool(String tag) throws IOException {
      return this.in.readBoolean();
   }

   public int readInt(String tag) throws IOException {
      return this.in.readInt();
   }

   public long readLong(String tag) throws IOException {
      return this.in.readLong();
   }

   public float readFloat(String tag) throws IOException {
      return this.in.readFloat();
   }

   public double readDouble(String tag) throws IOException {
      return this.in.readDouble();
   }

   public String readString(String tag) throws IOException {
      int len = this.in.readInt();
      if (len == -1) {
         return null;
      } else {
         this.checkLength(len);
         byte[] b = new byte[len];
         this.in.readFully(b);
         return new String(b, StandardCharsets.UTF_8);
      }
   }

   public byte[] readBuffer(String tag) throws IOException {
      int len = this.readInt(tag);
      if (len == -1) {
         return null;
      } else {
         this.checkLength(len);
         byte[] arr = new byte[len];
         this.in.readFully(arr);
         return arr;
      }
   }

   public void readRecord(Record r, String tag) throws IOException {
      r.deserialize(this, tag);
   }

   public void startRecord(String tag) throws IOException {
   }

   public void endRecord(String tag) throws IOException {
   }

   public Index startVector(String tag) throws IOException {
      int len = this.readInt(tag);
      return len == -1 ? null : new BinaryIndex(len);
   }

   public void endVector(String tag) throws IOException {
   }

   public Index startMap(String tag) throws IOException {
      return new BinaryIndex(this.readInt(tag));
   }

   public void endMap(String tag) throws IOException {
   }

   private void checkLength(int len) throws IOException {
      if (len < 0 || len > this.totalBufferSize) {
         throw new IOException("Unreasonable length = " + len);
      }
   }

   static {
      Integer configuredExtraMaxBuffer = Integer.getInteger("zookeeper.jute.maxbuffer.extrasize", maxBuffer);
      if (configuredExtraMaxBuffer < 1024) {
         extraMaxBuffer = 1024;
      } else {
         extraMaxBuffer = configuredExtraMaxBuffer;
      }

   }

   private static class BinaryIndex implements Index {
      private int n;

      BinaryIndex(int nelems) {
         this.n = nelems;
      }

      public boolean done() {
         return this.n <= 0;
      }

      public void incr() {
         --this.n;
      }
   }
}
