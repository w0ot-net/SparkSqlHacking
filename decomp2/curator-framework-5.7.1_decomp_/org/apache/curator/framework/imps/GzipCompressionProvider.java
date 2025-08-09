package org.apache.curator.framework.imps;

import java.io.EOFException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.curator.framework.api.CompressionProvider;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;

public class GzipCompressionProvider implements CompressionProvider {
   private static final int MAX_SAFE_JAVA_BYTE_ARRAY_SIZE = 2147483519;
   private static final int GZIP_MAGIC = 35615;
   private static final byte OS_BIT;
   private static final byte[] GZIP_HEADER;
   private static final int FHCRC = 2;
   private static final int FEXTRA = 4;
   private static final int FNAME = 8;
   private static final int FCOMMENT = 16;
   private static final int GZIP_HEADER_SIZE;
   private static final int GZIP_TRAILER_SIZE = 8;
   private static final int MIN_COMPRESSED_DATA_SIZE = 2;
   private static final ConcurrentLinkedQueue DEFLATER_POOL;
   private static final ConcurrentLinkedQueue INFLATER_POOL;
   private static final byte[] COMPRESSED_EMPTY_BYTES;

   private static Deflater acquireDeflater() {
      Deflater deflater = (Deflater)DEFLATER_POOL.poll();
      if (deflater == null) {
         deflater = new Deflater(-1, true);
      }

      return deflater;
   }

   private static Inflater acquireInflater() {
      Inflater inflater = (Inflater)INFLATER_POOL.poll();
      if (inflater == null) {
         inflater = new Inflater(true);
      }

      return inflater;
   }

   public byte[] compress(String path, byte[] data) {
      return data.length == 0 ? (byte[])COMPRESSED_EMPTY_BYTES.clone() : doCompress(data);
   }

   @VisibleForTesting
   static byte[] doCompress(byte[] data) {
      byte[] result = Arrays.copyOf(GZIP_HEADER, conservativeGZippedSizeEstimate(data.length));
      Deflater deflater = acquireDeflater();

      try {
         deflater.setInput(data);
         deflater.finish();
         int offset = GZIP_HEADER_SIZE;

         while(true) {
            int available = result.length - 8 - offset;
            int numCompressedBytes = deflater.deflate(result, offset, available);
            offset += numCompressedBytes;
            if (deflater.finished()) {
               CRC32 crc = new CRC32();
               crc.update(data, 0, data.length);
               writeLittleEndianInt(result, offset, (int)crc.getValue());
               writeLittleEndianInt(result, offset + 4, data.length);
               numCompressedBytes = offset + 8;
               if (result.length != numCompressedBytes) {
                  result = Arrays.copyOf(result, numCompressedBytes);
               }

               byte[] var12 = result;
               return var12;
            }

            int newResultLength = result.length + result.length / 2;
            result = Arrays.copyOf(result, newResultLength);
         }
      } finally {
         deflater.reset();
         DEFLATER_POOL.add(deflater);
      }
   }

   private static int conservativeGZippedSizeEstimate(int dataSize) {
      int conservativeCompressedDataSizeEstimate;
      if (dataSize < 512) {
         conservativeCompressedDataSizeEstimate = Math.max(dataSize, 2);
      } else {
         conservativeCompressedDataSizeEstimate = Math.max(512, dataSize / 2);
      }

      return GZIP_HEADER_SIZE + conservativeCompressedDataSizeEstimate + 8;
   }

   private static void writeLittleEndianInt(byte[] b, int offset, int v) {
      b[offset] = (byte)v;
      b[offset + 1] = (byte)(v >> 8);
      b[offset + 2] = (byte)(v >> 16);
      b[offset + 3] = (byte)(v >> 24);
   }

   public byte[] decompress(String path, byte[] gzippedDataBytes) throws IOException {
      if (Arrays.equals(gzippedDataBytes, COMPRESSED_EMPTY_BYTES)) {
         return new byte[0];
      } else {
         ByteBuffer gzippedData = ByteBuffer.wrap(gzippedDataBytes);
         gzippedData.order(ByteOrder.LITTLE_ENDIAN);
         int headerSize = readGzipHeader(gzippedData);
         if (gzippedDataBytes.length < headerSize + 2 + 8) {
            throw new EOFException("Too short GZipped data");
         } else {
            int compressedDataSize = gzippedDataBytes.length - headerSize - 8;
            int initialResultLength = (int)Math.min((long)compressedDataSize * 3L, 2147483519L);
            byte[] result = new byte[initialResultLength];
            Inflater inflater = acquireInflater();

            try {
               inflater.setInput(gzippedDataBytes, headerSize, compressedDataSize);
               CRC32 crc = new CRC32();
               int offset = 0;

               while(true) {
                  int numDecompressedBytes;
                  try {
                     numDecompressedBytes = inflater.inflate(result, offset, result.length - offset);
                  } catch (DataFormatException e) {
                     String s = e.getMessage();
                     throw new ZipException(s != null ? s : "Invalid ZLIB data format");
                  }

                  crc.update(result, offset, numDecompressedBytes);
                  offset += numDecompressedBytes;
                  if (inflater.finished() || inflater.needsDictionary()) {
                     if (inflater.getRemaining() != 0) {
                        throw new ZipException("Expected just one GZip block, without garbage in the end");
                     }

                     numDecompressedBytes = gzippedData.getInt(gzippedDataBytes.length - 8);
                     int numUncompressedBytes = gzippedData.getInt(gzippedDataBytes.length - 4);
                     if (numDecompressedBytes != (int)crc.getValue() || numUncompressedBytes != offset) {
                        throw new ZipException("Corrupt GZIP trailer");
                     }

                     if (result.length != offset) {
                        result = Arrays.copyOf(result, offset);
                     }

                     byte[] var21 = result;
                     return var21;
                  }

                  if (numDecompressedBytes == 0 && inflater.needsInput()) {
                     throw new ZipException("Corrupt GZipped data");
                  }

                  if (result.length == 2147483519 && numDecompressedBytes == 0) {
                     throw new OutOfMemoryError("Unable to uncompress that much data into a single byte[] array");
                  }

                  int newResultLength = (int)Math.min((long)result.length + (long)(result.length / 2), 2147483519L);
                  if (result.length != newResultLength) {
                     result = Arrays.copyOf(result, newResultLength);
                  }
               }
            } finally {
               inflater.reset();
               INFLATER_POOL.add(inflater);
            }
         }
      }
   }

   private static int readGzipHeader(ByteBuffer gzippedData) throws IOException {
      try {
         return doReadHeader(gzippedData);
      } catch (BufferUnderflowException var2) {
         throw new EOFException();
      }
   }

   private static int doReadHeader(ByteBuffer gzippedData) throws IOException {
      if (gzippedData.getChar() != '謟') {
         throw new ZipException("Not in GZip format");
      } else if (gzippedData.get() != 8) {
         throw new ZipException("Unsupported compression method");
      } else {
         int flags = gzippedData.get();
         skip(gzippedData, 6);
         if ((flags & 4) != 0) {
            int extraBytes = gzippedData.getChar();
            skip(gzippedData, extraBytes);
         }

         if ((flags & 8) != 0) {
            skipZeroTerminatedString(gzippedData);
         }

         if ((flags & 16) != 0) {
            skipZeroTerminatedString(gzippedData);
         }

         if ((flags & 2) != 0) {
            CRC32 crc = new CRC32();
            crc.update(gzippedData.array(), 0, gzippedData.position());
            if (gzippedData.getChar() != (char)((int)crc.getValue())) {
               throw new ZipException("Corrupt GZIP header");
            }
         }

         return gzippedData.position();
      }
   }

   private static void skip(ByteBuffer gzippedData, int skipBytes) throws IOException {
      try {
         gzippedData.position(gzippedData.position() + skipBytes);
      } catch (IllegalArgumentException var3) {
         throw new EOFException();
      }
   }

   private static void skipZeroTerminatedString(ByteBuffer gzippedData) {
      while(gzippedData.get() != 0) {
      }

   }

   static {
      String version = System.getProperty("java.specification.version");
      if (version.contains(".")) {
         OS_BIT = 0;
      } else {
         OS_BIT = (byte)(Float.parseFloat(version) >= 16.0F ? -1 : 0);
      }

      GZIP_HEADER = new byte[]{31, -117, 8, 0, 0, 0, 0, 0, 0, OS_BIT};
      GZIP_HEADER_SIZE = GZIP_HEADER.length;
      DEFLATER_POOL = new ConcurrentLinkedQueue();
      INFLATER_POOL = new ConcurrentLinkedQueue();
      COMPRESSED_EMPTY_BYTES = new byte[]{31, -117, 8, 0, 0, 0, 0, 0, 0, OS_BIT, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   }
}
