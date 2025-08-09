package org.apache.avro.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class UtfTextUtils {
   private static final int TRANSFER_BUFFER_SIZE = 4096;
   private static final Charset UTF_32 = Charset.forName("UTF-32");
   private static final Charset UTF_32BE = Charset.forName("UTF-32BE");
   private static final Charset UTF_32LE = Charset.forName("UTF-32LE");

   public static String asString(byte[] bytes, Charset charset) {
      if (charset == null) {
         charset = detectUtfCharset(bytes);
      }

      return skipBOM(new String(bytes, charset));
   }

   public static String readAllBytes(InputStream input, Charset charset) throws IOException {
      if (charset == null) {
         input = ensureMarkSupport(input);
         input.mark(4);
         byte[] buffer = new byte[4];
         int bytesRead = fillBuffer(input, buffer);
         input.reset();
         charset = detectUtfCharset0(buffer, bytesRead);
         if (charset == null) {
            throw new IOException("Unsupported UCS-4 variant (neither UTF-32BE nor UTF32-LE)");
         }
      }

      Reader reader = new InputStreamReader(input, charset);
      return readAllChars(reader);
   }

   private static InputStream ensureMarkSupport(InputStream input) {
      return (InputStream)(input.markSupported() ? input : new BufferedInputStream(input));
   }

   private static int fillBuffer(InputStream in, byte[] buf) throws IOException {
      int remaining = buf.length;

      int offset;
      int bytesRead;
      for(offset = 0; remaining > 0; remaining -= bytesRead) {
         bytesRead = in.read(buf, offset, remaining);
         if (bytesRead == -1) {
            break;
         }

         offset += bytesRead;
      }

      return offset;
   }

   public static String readAllChars(Reader input) throws IOException {
      StringBuilder buffer = new StringBuilder();
      char[] charBuffer = new char[4096];

      int charsRead;
      while((charsRead = input.read(charBuffer, 0, 4096)) >= 0) {
         buffer.append(charBuffer, 0, charsRead);
      }

      return skipBOM(buffer);
   }

   private static String skipBOM(CharSequence buffer) {
      return buffer.charAt(0) == '\ufeff' ? buffer.subSequence(1, buffer.length()).toString() : buffer.toString();
   }

   public static Charset detectUtfCharset(byte[] firstFewBytes) {
      Charset detectedCharset = detectUtfCharset0(firstFewBytes, firstFewBytes.length);
      if (detectedCharset == null) {
         throw new IllegalArgumentException("Unsupported UCS-4 variant (neither UTF-32BE nor UTF32-LE)");
      } else {
         return detectedCharset;
      }
   }

   private static Charset detectUtfCharset0(byte[] firstFewBytes, int numBytes) {
      int quad = quad(firstFewBytes, numBytes);
      int word = quad >>> 16;
      if (numBytes <= 3 || quad != 65279 && quad != -131072) {
         if (numBytes <= 3 || quad != 65534 && quad != -16842752) {
            if (numBytes <= 1 || word != 65279 && word != 65534) {
               if (numBytes > 2 && quad >>> 8 == 15711167) {
                  return StandardCharsets.UTF_8;
               } else if (numBytes > 3 && (quad & -256) == 0) {
                  return UTF_32BE;
               } else if (numBytes > 3 && (quad & 16777215) == 0) {
                  return UTF_32LE;
               } else if ((numBytes <= 3 || (quad & -65281) != 0) && (quad & -16711681) != 0) {
                  if (numBytes > 1 && (word & '\uff00') == 0) {
                     return StandardCharsets.UTF_16BE;
                  } else {
                     return numBytes > 1 && (word & 255) == 0 ? StandardCharsets.UTF_16LE : StandardCharsets.UTF_8;
                  }
               } else {
                  return null;
               }
            } else {
               return StandardCharsets.UTF_16;
            }
         } else {
            return null;
         }
      } else {
         return UTF_32;
      }
   }

   private static int quad(byte[] bytes, int length) {
      int quad = -1;
      switch (length) {
         default:
            quad = quad & -256 | bytes[3] & 255;
         case 3:
            quad = quad & -65281 | (bytes[2] & 255) << 8;
         case 2:
            quad = quad & -16711681 | (bytes[1] & 255) << 16;
         case 1:
            quad = quad & 16777215 | (bytes[0] & 255) << 24;
         case 0:
            return quad;
      }
   }
}
