package org.apache.commons.compress.archivers.tar;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ParsingUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class TarUtils {
   private static final int BYTE_MASK = 255;
   static final ZipEncoding DEFAULT_ENCODING = ZipEncodingHelper.getZipEncoding(Charset.defaultCharset());
   static final ZipEncoding FALLBACK_ENCODING = new ZipEncoding() {
      public boolean canEncode(String name) {
         return true;
      }

      public String decode(byte[] buffer) {
         int length = buffer.length;
         StringBuilder result = new StringBuilder(length);

         for(byte b : buffer) {
            if (b == 0) {
               break;
            }

            result.append((char)(b & 255));
         }

         return result.toString();
      }

      public ByteBuffer encode(String name) {
         int length = name.length();
         byte[] buf = new byte[length];

         for(int i = 0; i < length; ++i) {
            buf[i] = (byte)name.charAt(i);
         }

         return ByteBuffer.wrap(buf);
      }
   };

   public static long computeCheckSum(byte[] buf) {
      long sum = 0L;

      for(byte element : buf) {
         sum += (long)(255 & element);
      }

      return sum;
   }

   private static String exceptionMessage(byte[] buffer, int offset, int length, int current, byte currentByte) {
      String string = new String(buffer, offset, length, Charset.defaultCharset());
      string = string.replace("\u0000", "{NUL}");
      return "Invalid byte " + currentByte + " at offset " + (current - offset) + " in '" + string + "' len=" + length;
   }

   private static void formatBigIntegerBinary(long value, byte[] buf, int offset, int length, boolean negative) {
      BigInteger val = BigInteger.valueOf(value);
      byte[] b = val.toByteArray();
      int len = b.length;
      if (len > length - 1) {
         throw new IllegalArgumentException("Value " + value + " is too large for " + length + " byte field.");
      } else {
         int off = offset + length - len;
         System.arraycopy(b, 0, buf, off, len);
         byte fill = (byte)(negative ? 255 : 0);

         for(int i = offset + 1; i < off; ++i) {
            buf[i] = fill;
         }

      }
   }

   public static int formatCheckSumOctalBytes(long value, byte[] buf, int offset, int length) {
      int idx = length - 2;
      formatUnsignedOctalString(value, buf, offset, idx);
      buf[offset + idx++] = 0;
      buf[offset + idx] = 32;
      return offset + length;
   }

   private static void formatLongBinary(long value, byte[] buf, int offset, int length, boolean negative) {
      // $FF: Couldn't be decompiled
   }

   public static int formatLongOctalBytes(long value, byte[] buf, int offset, int length) {
      int idx = length - 1;
      formatUnsignedOctalString(value, buf, offset, idx);
      buf[offset + idx] = 32;
      return offset + length;
   }

   public static int formatLongOctalOrBinaryBytes(long value, byte[] buf, int offset, int length) {
      long maxAsOctalChar = length == 8 ? 2097151L : 8589934591L;
      boolean negative = value < 0L;
      if (!negative && value <= maxAsOctalChar) {
         return formatLongOctalBytes(value, buf, offset, length);
      } else {
         if (length < 9) {
            formatLongBinary(value, buf, offset, length, negative);
         } else {
            formatBigIntegerBinary(value, buf, offset, length, negative);
         }

         buf[offset] = (byte)(negative ? 255 : 128);
         return offset + length;
      }
   }

   public static int formatNameBytes(String name, byte[] buf, int offset, int length) {
      try {
         return formatNameBytes(name, buf, offset, length, DEFAULT_ENCODING);
      } catch (IOException var7) {
         try {
            return formatNameBytes(name, buf, offset, length, FALLBACK_ENCODING);
         } catch (IOException ex2) {
            throw new UncheckedIOException(ex2);
         }
      }
   }

   public static int formatNameBytes(String name, byte[] buf, int offset, int length, ZipEncoding encoding) throws IOException {
      int len = name.length();

      ByteBuffer b;
      for(b = encoding.encode(name); b.limit() > length && len > 0; b = encoding.encode(name.substring(0, len))) {
         --len;
      }

      int limit = b.limit() - b.position();
      System.arraycopy(b.array(), b.arrayOffset(), buf, offset, limit);

      for(int i = limit; i < length; ++i) {
         buf[offset + i] = 0;
      }

      return offset + length;
   }

   public static int formatOctalBytes(long value, byte[] buf, int offset, int length) {
      int idx = length - 2;
      formatUnsignedOctalString(value, buf, offset, idx);
      buf[offset + idx++] = 32;
      buf[offset + idx] = 0;
      return offset + length;
   }

   public static void formatUnsignedOctalString(long value, byte[] buffer, int offset, int length) {
      int remaining = length - 1;
      if (value == 0L) {
         buffer[offset + remaining--] = 48;
      } else {
         long val;
         for(val = value; remaining >= 0 && val != 0L; --remaining) {
            buffer[offset + remaining] = (byte)(48 + (byte)((int)(val & 7L)));
            val >>>= 3;
         }

         if (val != 0L) {
            throw new IllegalArgumentException(value + "=" + Long.toOctalString(value) + " will not fit in octal number buffer of length " + length);
         }
      }

      while(remaining >= 0) {
         buffer[offset + remaining] = 48;
         --remaining;
      }

   }

   private static long parseBinaryBigInteger(byte[] buffer, int offset, int length, boolean negative) {
      byte[] remainder = new byte[length - 1];
      System.arraycopy(buffer, offset + 1, remainder, 0, length - 1);
      BigInteger val = new BigInteger(remainder);
      if (negative) {
         val = val.add(BigInteger.valueOf(-1L)).not();
      }

      if (val.bitLength() > 63) {
         throw new IllegalArgumentException("At offset " + offset + ", " + length + " byte binary number exceeds maximum signed long value");
      } else {
         return negative ? -val.longValue() : val.longValue();
      }
   }

   private static long parseBinaryLong(byte[] buffer, int offset, int length, boolean negative) {
      if (length >= 9) {
         throw new IllegalArgumentException("At offset " + offset + ", " + length + " byte binary number exceeds maximum signed long value");
      } else {
         long val = 0L;

         for(int i = 1; i < length; ++i) {
            val = (val << 8) + (long)(buffer[offset + i] & 255);
         }

         if (negative) {
            --val;
            val ^= (long)Math.pow((double)2.0F, (double)(length - 1) * (double)8.0F) - 1L;
         }

         return negative ? -val : val;
      }
   }

   public static boolean parseBoolean(byte[] buffer, int offset) {
      return buffer[offset] == 1;
   }

   protected static List parseFromPAX01SparseHeaders(String sparseMap) throws IOException {
      List<TarArchiveStructSparse> sparseHeaders = new ArrayList();
      String[] sparseHeaderStrings = sparseMap.split(",");
      if (sparseHeaderStrings.length % 2 == 1) {
         throw new IOException("Corrupted TAR archive. Bad format in GNU.sparse.map PAX Header");
      } else {
         for(int i = 0; i < sparseHeaderStrings.length; i += 2) {
            long sparseOffset = ParsingUtils.parseLongValue(sparseHeaderStrings[i]);
            if (sparseOffset < 0L) {
               throw new IOException("Corrupted TAR archive. Sparse struct offset contains negative value");
            }

            long sparseNumbytes = ParsingUtils.parseLongValue(sparseHeaderStrings[i + 1]);
            if (sparseNumbytes < 0L) {
               throw new IOException("Corrupted TAR archive. Sparse struct numbytes contains negative value");
            }

            sparseHeaders.add(new TarArchiveStructSparse(sparseOffset, sparseNumbytes));
         }

         return Collections.unmodifiableList(sparseHeaders);
      }
   }

   public static String parseName(byte[] buffer, int offset, int length) {
      try {
         return parseName(buffer, offset, length, DEFAULT_ENCODING);
      } catch (IOException var6) {
         try {
            return parseName(buffer, offset, length, FALLBACK_ENCODING);
         } catch (IOException ex2) {
            throw new UncheckedIOException(ex2);
         }
      }
   }

   public static String parseName(byte[] buffer, int offset, int length, ZipEncoding encoding) throws IOException {
      int len = 0;

      for(int i = offset; len < length && buffer[i] != 0; ++i) {
         ++len;
      }

      if (len > 0) {
         byte[] b = new byte[len];
         System.arraycopy(buffer, offset, b, 0, len);
         return encoding.decode(b);
      } else {
         return "";
      }
   }

   public static long parseOctal(byte[] buffer, int offset, int length) {
      long result = 0L;
      int end = offset + length;
      int start = offset;
      if (length < 2) {
         throw new IllegalArgumentException("Length " + length + " must be at least 2");
      } else if (buffer[offset] == 0) {
         return 0L;
      } else {
         while(start < end && buffer[start] == 32) {
            ++start;
         }

         for(byte trailer = buffer[end - 1]; start < end && (trailer == 0 || trailer == 32); trailer = buffer[end - 1]) {
            --end;
         }

         while(start < end) {
            byte currentByte = buffer[start];
            if (currentByte < 48 || currentByte > 55) {
               throw new IllegalArgumentException(exceptionMessage(buffer, offset, length, start, currentByte));
            }

            result = (result << 3) + (long)(currentByte - 48);
            ++start;
         }

         return result;
      }
   }

   public static long parseOctalOrBinary(byte[] buffer, int offset, int length) {
      if ((buffer[offset] & 128) == 0) {
         return parseOctal(buffer, offset, length);
      } else {
         boolean negative = buffer[offset] == -1;
         return length < 9 ? parseBinaryLong(buffer, offset, length, negative) : parseBinaryBigInteger(buffer, offset, length, negative);
      }
   }

   /** @deprecated */
   @Deprecated
   protected static List parsePAX01SparseHeaders(String sparseMap) {
      try {
         return parseFromPAX01SparseHeaders(sparseMap);
      } catch (IOException ex) {
         throw new UncheckedIOException(ex.getMessage(), ex);
      }
   }

   protected static List parsePAX1XSparseHeaders(InputStream inputStream, int recordSize) throws IOException {
      List<TarArchiveStructSparse> sparseHeaders = new ArrayList();
      long bytesRead = 0L;
      long[] readResult = readLineOfNumberForPax1X(inputStream);
      long sparseHeadersCount = readResult[0];
      if (sparseHeadersCount < 0L) {
         throw new IOException("Corrupted TAR archive. Negative value in sparse headers block");
      } else {
         bytesRead += readResult[1];

         while(sparseHeadersCount-- > 0L) {
            readResult = readLineOfNumberForPax1X(inputStream);
            long sparseOffset = readResult[0];
            if (sparseOffset < 0L) {
               throw new IOException("Corrupted TAR archive. Sparse header block offset contains negative value");
            }

            bytesRead += readResult[1];
            readResult = readLineOfNumberForPax1X(inputStream);
            long sparseNumbytes = readResult[0];
            if (sparseNumbytes < 0L) {
               throw new IOException("Corrupted TAR archive. Sparse header block numbytes contains negative value");
            }

            bytesRead += readResult[1];
            sparseHeaders.add(new TarArchiveStructSparse(sparseOffset, sparseNumbytes));
         }

         long bytesToSkip = (long)recordSize - bytesRead % (long)recordSize;
         IOUtils.skip(inputStream, bytesToSkip);
         return sparseHeaders;
      }
   }

   /** @deprecated */
   @Deprecated
   protected static Map parsePaxHeaders(InputStream inputStream, List sparseHeaders, Map globalPaxHeaders) throws IOException {
      return parsePaxHeaders(inputStream, sparseHeaders, globalPaxHeaders, -1L);
   }

   protected static Map parsePaxHeaders(InputStream inputStream, List sparseHeaders, Map globalPaxHeaders, long headerSize) throws IOException {
      Map<String, String> headers = new HashMap(globalPaxHeaders);
      Long offset = null;
      int totalRead = 0;

      int ch;
      label104:
      do {
         int len = 0;

         for(int read = 0; (ch = inputStream.read()) != -1; len += ch - 48) {
            ++read;
            ++totalRead;
            if (ch == 10) {
               break;
            }

            if (ch == 32) {
               ByteArrayOutputStream coll = new ByteArrayOutputStream();

               while((ch = inputStream.read()) != -1) {
                  ++read;
                  ++totalRead;
                  if (totalRead < 0 || headerSize >= 0L && (long)totalRead >= headerSize) {
                     continue label104;
                  }

                  if (ch == 61) {
                     String keyword = coll.toString(StandardCharsets.UTF_8);
                     int restLen = len - read;
                     if (restLen <= 1) {
                        headers.remove(keyword);
                     } else {
                        if (headerSize >= 0L && (long)restLen > headerSize - (long)totalRead) {
                           throw new IOException("Paxheader value size " + restLen + " exceeds size of header record");
                        }

                        byte[] rest = org.apache.commons.compress.utils.IOUtils.readRange(inputStream, restLen);
                        int got = rest.length;
                        if (got != restLen) {
                           throw new IOException("Failed to read Paxheader. Expected " + restLen + " bytes, read " + got);
                        }

                        totalRead += restLen;
                        if (rest[restLen - 1] != 10) {
                           throw new IOException("Failed to read Paxheader.Value should end with a newline");
                        }

                        String value = new String(rest, 0, restLen - 1, StandardCharsets.UTF_8);
                        headers.put(keyword, value);
                        if (keyword.equals("GNU.sparse.offset")) {
                           if (offset != null) {
                              sparseHeaders.add(new TarArchiveStructSparse(offset, 0L));
                           }

                           try {
                              offset = Long.valueOf(value);
                           } catch (NumberFormatException var19) {
                              throw new IOException("Failed to read Paxheader.GNU.sparse.offset contains a non-numeric value");
                           }

                           if (offset < 0L) {
                              throw new IOException("Failed to read Paxheader.GNU.sparse.offset contains negative value");
                           }
                        }

                        if (keyword.equals("GNU.sparse.numbytes")) {
                           if (offset == null) {
                              throw new IOException("Failed to read Paxheader.GNU.sparse.offset is expected before GNU.sparse.numbytes shows up.");
                           }

                           long numbytes = ParsingUtils.parseLongValue(value);
                           if (numbytes < 0L) {
                              throw new IOException("Failed to read Paxheader.GNU.sparse.numbytes contains negative value");
                           }

                           sparseHeaders.add(new TarArchiveStructSparse(offset, numbytes));
                           offset = null;
                        }
                     }
                     continue label104;
                  }

                  coll.write((byte)ch);
               }
               break;
            }

            if (ch < 48 || ch > 57) {
               throw new IOException("Failed to read Paxheader. Encountered a non-number while reading length");
            }

            len *= 10;
         }
      } while(ch != -1);

      if (offset != null) {
         sparseHeaders.add(new TarArchiveStructSparse(offset, 0L));
      }

      return headers;
   }

   public static TarArchiveStructSparse parseSparse(byte[] buffer, int offset) {
      long sparseOffset = parseOctalOrBinary(buffer, offset, 12);
      long sparseNumbytes = parseOctalOrBinary(buffer, offset + 12, 12);
      return new TarArchiveStructSparse(sparseOffset, sparseNumbytes);
   }

   private static long[] readLineOfNumberForPax1X(InputStream inputStream) throws IOException {
      long result = 0L;

      int number;
      long bytesRead;
      for(bytesRead = 0L; (number = inputStream.read()) != 10; result = result * 10L + (long)(number - 48)) {
         ++bytesRead;
         if (number == -1) {
            throw new IOException("Unexpected EOF when reading parse information of 1.X PAX format");
         }

         if (number < 48 || number > 57) {
            throw new IOException("Corrupted TAR archive. Non-numeric value in sparse headers block");
         }
      }

      ++bytesRead;
      return new long[]{result, bytesRead};
   }

   static List readSparseStructs(byte[] buffer, int offset, int entries) throws IOException {
      List<TarArchiveStructSparse> sparseHeaders = new ArrayList();

      for(int i = 0; i < entries; ++i) {
         try {
            TarArchiveStructSparse sparseHeader = parseSparse(buffer, offset + i * 24);
            if (sparseHeader.getOffset() < 0L) {
               throw new IOException("Corrupted TAR archive, sparse entry with negative offset");
            }

            if (sparseHeader.getNumbytes() < 0L) {
               throw new IOException("Corrupted TAR archive, sparse entry with negative numbytes");
            }

            sparseHeaders.add(sparseHeader);
         } catch (IllegalArgumentException ex) {
            throw new IOException("Corrupted TAR archive, sparse entry is invalid", ex);
         }
      }

      return Collections.unmodifiableList(sparseHeaders);
   }

   public static boolean verifyCheckSum(byte[] header) {
      long storedSum = parseOctal(header, 148, 8);
      long unsignedSum = 0L;
      long signedSum = 0L;

      for(int i = 0; i < header.length; ++i) {
         byte b = header[i];
         if (148 <= i && i < 156) {
            b = 32;
         }

         unsignedSum += (long)(255 & b);
         signedSum += (long)b;
      }

      return storedSum == unsignedSum || storedSum == signedSum;
   }

   private TarUtils() {
   }
}
