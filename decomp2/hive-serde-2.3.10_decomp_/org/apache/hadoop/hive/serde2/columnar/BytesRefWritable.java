package org.apache.hadoop.hive.serde2.columnar;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableFactories;
import org.apache.hadoop.io.WritableFactory;

public class BytesRefWritable implements Writable, Comparable {
   private static final byte[] EMPTY_BYTES = new byte[0];
   public static BytesRefWritable ZeroBytesRefWritable = new BytesRefWritable();
   int start;
   int length;
   byte[] bytes;
   LazyDecompressionCallback lazyDecompressObj;

   public BytesRefWritable() {
      this(EMPTY_BYTES);
   }

   public BytesRefWritable(int length) {
      this.start = 0;
      this.length = 0;
      this.bytes = null;

      assert length > 0;

      this.length = length;
      this.bytes = new byte[this.length];
      this.start = 0;
   }

   public BytesRefWritable(byte[] bytes) {
      this.start = 0;
      this.length = 0;
      this.bytes = null;
      this.bytes = bytes;
      this.length = bytes.length;
      this.start = 0;
   }

   public BytesRefWritable(byte[] data, int offset, int len) {
      this.start = 0;
      this.length = 0;
      this.bytes = null;
      this.bytes = data;
      this.start = offset;
      this.length = len;
   }

   public BytesRefWritable(LazyDecompressionCallback lazyDecompressData, int offset, int len) {
      this.start = 0;
      this.length = 0;
      this.bytes = null;
      this.lazyDecompressObj = lazyDecompressData;
      this.start = offset;
      this.length = len;
   }

   private void lazyDecompress() throws IOException {
      if (this.bytes == null && this.lazyDecompressObj != null) {
         this.bytes = this.lazyDecompressObj.decompress();
      }

   }

   public byte[] getBytesCopy() throws IOException {
      this.lazyDecompress();
      byte[] bb = new byte[this.length];
      System.arraycopy(this.bytes, this.start, bb, 0, this.length);
      return bb;
   }

   public byte[] getData() throws IOException {
      this.lazyDecompress();
      return this.bytes;
   }

   public void set(byte[] newData, int offset, int len) {
      this.bytes = newData;
      this.start = offset;
      this.length = len;
      this.lazyDecompressObj = null;
   }

   public void set(LazyDecompressionCallback newData, int offset, int len) {
      this.bytes = null;
      this.start = offset;
      this.length = len;
      this.lazyDecompressObj = newData;
   }

   public void writeDataTo(DataOutput out) throws IOException {
      this.lazyDecompress();
      out.write(this.bytes, this.start, this.length);
   }

   public void readFields(DataInput in) throws IOException {
      int len = in.readInt();
      if (len > this.bytes.length) {
         this.bytes = new byte[len];
      }

      this.start = 0;
      this.length = len;
      in.readFully(this.bytes, this.start, this.length);
   }

   public void write(DataOutput out) throws IOException {
      this.lazyDecompress();
      out.writeInt(this.length);
      out.write(this.bytes, this.start, this.length);
   }

   public int hashCode() {
      return super.hashCode();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder(3 * this.length);

      for(int idx = this.start; idx < this.length; ++idx) {
         if (idx != 0) {
            sb.append(' ');
         }

         String num = Integer.toHexString(255 & this.bytes[idx]);
         if (num.length() < 2) {
            sb.append('0');
         }

         sb.append(num);
      }

      return sb.toString();
   }

   public int compareTo(BytesRefWritable other) {
      if (other == null) {
         throw new IllegalArgumentException("Argument can not be null.");
      } else if (this == other) {
         return 0;
      } else {
         try {
            return WritableComparator.compareBytes(this.getData(), this.start, this.getLength(), other.getData(), other.start, other.getLength());
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
   }

   public boolean equals(Object right_obj) {
      if (right_obj != null && right_obj instanceof BytesRefWritable) {
         return this.compareTo((BytesRefWritable)right_obj) == 0;
      } else {
         return false;
      }
   }

   public int getLength() {
      return this.length;
   }

   public int getStart() {
      return this.start;
   }

   static {
      WritableFactories.setFactory(BytesRefWritable.class, new WritableFactory() {
         public Writable newInstance() {
            return new BytesRefWritable();
         }
      });
   }
}
