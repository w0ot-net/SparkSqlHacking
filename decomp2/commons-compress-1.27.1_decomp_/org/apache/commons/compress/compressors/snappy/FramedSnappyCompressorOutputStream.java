package org.apache.commons.compress.compressors.snappy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.codec.digest.PureJavaCrc32C;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.utils.ByteUtils;

public class FramedSnappyCompressorOutputStream extends CompressorOutputStream {
   private static final int MAX_COMPRESSED_BUFFER_SIZE = 65536;
   private final Parameters params;
   private final PureJavaCrc32C checksum;
   private final byte[] oneByte;
   private final byte[] buffer;
   private int currentIndex;
   private final ByteUtils.ByteConsumer consumer;

   static long mask(long x) {
      x = x >> 15 | x << 17;
      x += 2726488792L;
      x &= 4294967295L;
      return x;
   }

   public FramedSnappyCompressorOutputStream(OutputStream out) throws IOException {
      this(out, SnappyCompressorOutputStream.createParameterBuilder(32768).build());
   }

   public FramedSnappyCompressorOutputStream(OutputStream out, Parameters params) throws IOException {
      super(out);
      this.checksum = new PureJavaCrc32C();
      this.oneByte = new byte[1];
      this.buffer = new byte[65536];
      this.params = params;
      this.consumer = new ByteUtils.OutputStreamByteConsumer(out);
      out.write(FramedSnappyCompressorInputStream.SZ_SIGNATURE);
   }

   public void close() throws IOException {
      try {
         this.finish();
      } finally {
         this.out.close();
      }

   }

   public void finish() throws IOException {
      this.flushBuffer();
   }

   private void flushBuffer() throws IOException {
      if (this.currentIndex != 0) {
         this.out.write(0);
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         OutputStream o = new SnappyCompressorOutputStream(baos, (long)this.currentIndex, this.params);

         try {
            o.write(this.buffer, 0, this.currentIndex);
         } catch (Throwable var6) {
            try {
               o.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         o.close();
         byte[] b = baos.toByteArray();
         this.writeLittleEndian(3, (long)b.length + 4L);
         this.writeCrc();
         this.out.write(b);
         this.currentIndex = 0;
      }
   }

   public void write(byte[] data, int off, int len) throws IOException {
      int blockDataRemaining = this.buffer.length - this.currentIndex;

      while(len > 0) {
         int copyLen = Math.min(len, blockDataRemaining);
         System.arraycopy(data, off, this.buffer, this.currentIndex, copyLen);
         off += copyLen;
         blockDataRemaining -= copyLen;
         len -= copyLen;
         this.currentIndex += copyLen;
         if (blockDataRemaining == 0) {
            this.flushBuffer();
            blockDataRemaining = this.buffer.length;
         }
      }

   }

   public void write(int b) throws IOException {
      this.oneByte[0] = (byte)(b & 255);
      this.write(this.oneByte);
   }

   private void writeCrc() throws IOException {
      this.checksum.update(this.buffer, 0, this.currentIndex);
      this.writeLittleEndian(4, mask(this.checksum.getValue()));
      this.checksum.reset();
   }

   private void writeLittleEndian(int numBytes, long num) throws IOException {
      ByteUtils.toLittleEndian(this.consumer, num, numBytes);
   }
}
