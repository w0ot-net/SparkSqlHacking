package org.tukaani.xz;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.EncoderUtil;

class BlockOutputStream extends FinishableOutputStream {
   private final OutputStream out;
   private final CountingOutputStream outCounted;
   private FinishableOutputStream filterChain;
   private final Check check;
   private final int headerSize;
   private final long compressedSizeLimit;
   private long uncompressedSize = 0L;
   private final byte[] tempBuf = new byte[1];

   public BlockOutputStream(OutputStream out, FilterEncoder[] filters, Check check, ArrayCache arrayCache) throws IOException {
      this.out = out;
      this.check = check;
      this.outCounted = new CountingOutputStream(out);
      this.filterChain = this.outCounted;

      for(int i = filters.length - 1; i >= 0; --i) {
         this.filterChain = filters[i].getOutputStream(this.filterChain, arrayCache);
      }

      ByteArrayOutputStream bufStream = new ByteArrayOutputStream();
      bufStream.write(0);
      bufStream.write(filters.length - 1);

      for(int i = 0; i < filters.length; ++i) {
         EncoderUtil.encodeVLI(bufStream, filters[i].getFilterID());
         byte[] filterProps = filters[i].getFilterProps();
         EncoderUtil.encodeVLI(bufStream, (long)filterProps.length);
         bufStream.write(filterProps);
      }

      while((bufStream.size() & 3) != 0) {
         bufStream.write(0);
      }

      byte[] buf = bufStream.toByteArray();
      this.headerSize = buf.length + 4;
      if (this.headerSize > 1024) {
         throw new UnsupportedOptionsException();
      } else {
         buf[0] = (byte)(buf.length / 4);
         out.write(buf);
         EncoderUtil.writeCRC32(out, buf);
         this.compressedSizeLimit = 9223372036854775804L - (long)this.headerSize - (long)check.getSize();
      }
   }

   public void write(int b) throws IOException {
      this.tempBuf[0] = (byte)b;
      this.write(this.tempBuf, 0, 1);
   }

   public void write(byte[] buf, int off, int len) throws IOException {
      this.filterChain.write(buf, off, len);
      this.check.update(buf, off, len);
      this.uncompressedSize += (long)len;
      this.validate();
   }

   public void flush() throws IOException {
      this.filterChain.flush();
      this.validate();
   }

   public void finish() throws IOException {
      this.filterChain.finish();
      this.validate();

      for(long i = this.outCounted.getSize(); (i & 3L) != 0L; ++i) {
         this.out.write(0);
      }

      this.out.write(this.check.finish());
   }

   private void validate() throws IOException {
      long compressedSize = this.outCounted.getSize();
      if (compressedSize < 0L || compressedSize > this.compressedSizeLimit || this.uncompressedSize < 0L) {
         throw new XZIOException("XZ Stream has grown too big");
      }
   }

   public long getUnpaddedSize() {
      return (long)this.headerSize + this.outCounted.getSize() + (long)this.check.getSize();
   }

   public long getUncompressedSize() {
      return this.uncompressedSize;
   }
}
