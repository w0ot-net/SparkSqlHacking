package org.apache.commons.compress.compressors.gzip;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;

public class GzipCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
   private static final int FHCRC = 2;
   private static final int FEXTRA = 4;
   private static final int FNAME = 8;
   private static final int FCOMMENT = 16;
   private static final int FRESERVED = 224;
   private final BoundedInputStream countingStream;
   private final InputStream in;
   private final boolean decompressConcatenated;
   private final byte[] buf;
   private int bufUsed;
   private Inflater inf;
   private final CRC32 crc;
   private boolean endReached;
   private final byte[] oneByte;
   private final GzipParameters parameters;

   public static boolean matches(byte[] signature, int length) {
      return length >= 2 && signature[0] == 31 && signature[1] == -117;
   }

   private static byte[] readToNull(DataInput inData) throws IOException {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();

      byte[] var3;
      try {
         int b;
         while((b = inData.readUnsignedByte()) != 0) {
            bos.write(b);
         }

         var3 = bos.toByteArray();
      } catch (Throwable var5) {
         try {
            bos.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      bos.close();
      return var3;
   }

   public GzipCompressorInputStream(InputStream inputStream) throws IOException {
      this(inputStream, false);
   }

   public GzipCompressorInputStream(InputStream inputStream, boolean decompressConcatenated) throws IOException {
      this.buf = new byte[8192];
      this.inf = new Inflater(true);
      this.crc = new CRC32();
      this.oneByte = new byte[1];
      this.parameters = new GzipParameters();
      this.countingStream = ((BoundedInputStream.Builder)BoundedInputStream.builder().setInputStream(inputStream)).get();
      if (this.countingStream.markSupported()) {
         this.in = this.countingStream;
      } else {
         this.in = new BufferedInputStream(this.countingStream);
      }

      this.decompressConcatenated = decompressConcatenated;
      this.init(true);
   }

   public void close() throws IOException {
      if (this.inf != null) {
         this.inf.end();
         this.inf = null;
      }

      if (this.in != System.in) {
         this.in.close();
      }

   }

   public long getCompressedCount() {
      return this.countingStream.getCount();
   }

   public GzipParameters getMetaData() {
      return this.parameters;
   }

   private boolean init(boolean isFirstMember) throws IOException {
      if (!isFirstMember && !this.decompressConcatenated) {
         throw new IllegalStateException("Unexpected: isFirstMember and decompressConcatenated are both false!");
      } else {
         int magic0 = this.in.read();
         if (magic0 == -1 && !isFirstMember) {
            return false;
         } else if (magic0 == 31 && this.in.read() == 139) {
            DataInput inData = new DataInputStream(this.in);
            int method = inData.readUnsignedByte();
            if (method != 8) {
               throw new IOException("Unsupported compression method " + method + " in the .gz header");
            } else {
               int flg = inData.readUnsignedByte();
               if ((flg & 224) != 0) {
                  throw new IOException("Reserved flags are set in the .gz header");
               } else {
                  this.parameters.setModificationTime(ByteUtils.fromLittleEndian((DataInput)inData, 4) * 1000L);
                  switch (inData.readUnsignedByte()) {
                     case 2:
                        this.parameters.setCompressionLevel(9);
                        break;
                     case 4:
                        this.parameters.setCompressionLevel(1);
                  }

                  this.parameters.setOperatingSystem(inData.readUnsignedByte());
                  if ((flg & 4) != 0) {
                     int xlen = inData.readUnsignedByte();
                     xlen |= inData.readUnsignedByte() << 8;

                     while(xlen-- > 0) {
                        inData.readUnsignedByte();
                     }
                  }

                  if ((flg & 8) != 0) {
                     this.parameters.setFileName(new String(readToNull(inData), GzipUtils.GZIP_ENCODING));
                  }

                  if ((flg & 16) != 0) {
                     this.parameters.setComment(new String(readToNull(inData), GzipUtils.GZIP_ENCODING));
                  }

                  if ((flg & 2) != 0) {
                     inData.readShort();
                  }

                  this.inf.reset();
                  this.crc.reset();
                  return true;
               }
            }
         } else {
            throw new IOException(isFirstMember ? "Input is not in the .gz format" : "Garbage after a valid .gz stream");
         }
      }
   }

   public int read() throws IOException {
      return this.read(this.oneByte, 0, 1) == -1 ? -1 : this.oneByte[0] & 255;
   }

   public int read(byte[] b, int off, int len) throws IOException {
      if (len == 0) {
         return 0;
      } else if (this.endReached) {
         return -1;
      } else {
         int size = 0;

         while(len > 0) {
            if (this.inf.needsInput()) {
               this.in.mark(this.buf.length);
               this.bufUsed = this.in.read(this.buf);
               if (this.bufUsed == -1) {
                  throw new EOFException();
               }

               this.inf.setInput(this.buf, 0, this.bufUsed);
            }

            int ret;
            try {
               ret = this.inf.inflate(b, off, len);
            } catch (DataFormatException var12) {
               throw new IOException("Gzip-compressed data is corrupt");
            }

            this.crc.update(b, off, ret);
            off += ret;
            len -= ret;
            size += ret;
            this.count(ret);
            if (this.inf.finished()) {
               this.in.reset();
               int skipAmount = this.bufUsed - this.inf.getRemaining();
               if (IOUtils.skip(this.in, (long)skipAmount) != (long)skipAmount) {
                  throw new IOException();
               }

               this.bufUsed = 0;
               DataInput inData = new DataInputStream(this.in);
               long crcStored = ByteUtils.fromLittleEndian((DataInput)inData, 4);
               if (crcStored != this.crc.getValue()) {
                  throw new IOException("Gzip-compressed data is corrupt (CRC32 error)");
               }

               long isize = ByteUtils.fromLittleEndian((DataInput)inData, 4);
               if (isize != (this.inf.getBytesWritten() & 4294967295L)) {
                  throw new IOException("Gzip-compressed data is corrupt(uncompressed size mismatch)");
               }

               if (!this.decompressConcatenated || !this.init(false)) {
                  this.inf.end();
                  this.inf = null;
                  this.endReached = true;
                  return size == 0 ? -1 : size;
               }
            }
         }

         return size;
      }
   }
}
