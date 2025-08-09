package net.razorvine.pickle;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public abstract class PickleUtils {
   public static String readline(InputStream input) throws IOException {
      return readline(input, false);
   }

   public static String readline(InputStream input, boolean includeLF) throws IOException {
      StringBuilder sb = new StringBuilder();

      int c;
      do {
         c = input.read();
         if (c == -1) {
            if (sb.length() == 0) {
               throw new IOException("premature end of file");
            }
            break;
         }

         if (c != 10 || includeLF) {
            sb.append((char)c);
         }
      } while(c != 10);

      return sb.toString();
   }

   public static short readbyte(InputStream input) throws IOException {
      int b = input.read();
      return (short)b;
   }

   public static byte[] readbytes(InputStream input, int n) throws IOException {
      byte[] buffer = new byte[n];
      readbytes_into(input, buffer, 0, n);
      return buffer;
   }

   public static byte[] readbytes(InputStream input, long n) throws IOException {
      if (n > 2147483647L) {
         throw new PickleException("pickle too large, can't read more than maxint");
      } else {
         return readbytes(input, (int)n);
      }
   }

   public static void readbytes_into(InputStream input, byte[] buffer, int offset, int length) throws IOException {
      while(length > 0) {
         int read = input.read(buffer, offset, length);
         if (read == -1) {
            throw new IOException("expected more bytes in input stream");
         }

         offset += read;
         length -= read;
      }

   }

   public static int bytes_to_integer(byte[] bytes) {
      return bytes_to_integer(bytes, 0, bytes.length);
   }

   public static int bytes_to_integer(byte[] bytes, int offset, int size) {
      if (size == 2) {
         int i = bytes[1 + offset] & 255;
         i <<= 8;
         i |= bytes[0 + offset] & 255;
         return i;
      } else if (size == 4) {
         int i = bytes[3 + offset];
         i <<= 8;
         i |= bytes[2 + offset] & 255;
         i <<= 8;
         i |= bytes[1 + offset] & 255;
         i <<= 8;
         i |= bytes[0 + offset] & 255;
         return i;
      } else {
         throw new PickleException("invalid amount of bytes to convert to int: " + size);
      }
   }

   public static long bytes_to_long(byte[] bytes, int offset) {
      if (bytes.length - offset < 8) {
         throw new PickleException("too few bytes to convert to long");
      } else {
         long i = (long)(bytes[7 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[6 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[5 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[4 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[3 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[2 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[1 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[offset] & 255);
         return i;
      }
   }

   public static long bytes_to_uint(byte[] bytes, int offset) {
      if (bytes.length - offset < 4) {
         throw new PickleException("too few bytes to convert to long");
      } else {
         long i = (long)(bytes[3 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[2 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[1 + offset] & 255);
         i <<= 8;
         i |= (long)(bytes[0 + offset] & 255);
         return i;
      }
   }

   public static byte[] integer_to_bytes(int i) {
      byte[] b = new byte[4];
      b[0] = (byte)(i & 255);
      i >>= 8;
      b[1] = (byte)(i & 255);
      i >>= 8;
      b[2] = (byte)(i & 255);
      i >>= 8;
      b[3] = (byte)(i & 255);
      return b;
   }

   public static byte[] double_to_bytes(double d) {
      long bits = Double.doubleToRawLongBits(d);
      byte[] b = new byte[8];
      b[7] = (byte)((int)(bits & 255L));
      bits >>= 8;
      b[6] = (byte)((int)(bits & 255L));
      bits >>= 8;
      b[5] = (byte)((int)(bits & 255L));
      bits >>= 8;
      b[4] = (byte)((int)(bits & 255L));
      bits >>= 8;
      b[3] = (byte)((int)(bits & 255L));
      bits >>= 8;
      b[2] = (byte)((int)(bits & 255L));
      bits >>= 8;
      b[1] = (byte)((int)(bits & 255L));
      bits >>= 8;
      b[0] = (byte)((int)(bits & 255L));
      return b;
   }

   public static double bytes_to_double(byte[] bytes, int offset) {
      try {
         long result = (long)(bytes[0 + offset] & 255);
         result <<= 8;
         result |= (long)(bytes[1 + offset] & 255);
         result <<= 8;
         result |= (long)(bytes[2 + offset] & 255);
         result <<= 8;
         result |= (long)(bytes[3 + offset] & 255);
         result <<= 8;
         result |= (long)(bytes[4 + offset] & 255);
         result <<= 8;
         result |= (long)(bytes[5 + offset] & 255);
         result <<= 8;
         result |= (long)(bytes[6 + offset] & 255);
         result <<= 8;
         result |= (long)(bytes[7 + offset] & 255);
         return Double.longBitsToDouble(result);
      } catch (IndexOutOfBoundsException var4) {
         throw new PickleException("decoding double: too few bytes");
      }
   }

   public static float bytes_to_float(byte[] bytes, int offset) {
      try {
         int result = bytes[0 + offset] & 255;
         result <<= 8;
         result |= bytes[1 + offset] & 255;
         result <<= 8;
         result |= bytes[2 + offset] & 255;
         result <<= 8;
         result |= bytes[3 + offset] & 255;
         return Float.intBitsToFloat(result);
      } catch (IndexOutOfBoundsException var3) {
         throw new PickleException("decoding float: too few bytes");
      }
   }

   public static Number decode_long(byte[] data) {
      if (data.length == 0) {
         return 0L;
      } else {
         byte[] data2 = new byte[data.length];

         for(int i = 0; i < data.length; ++i) {
            data2[data.length - i - 1] = data[i];
         }

         BigInteger bigint = new BigInteger(data2);
         return optimizeBigint(bigint);
      }
   }

   public static byte[] encode_long(BigInteger big) {
      byte[] data = big.toByteArray();
      byte[] data2 = new byte[data.length];

      for(int i = 0; i < data.length; ++i) {
         data2[data.length - i - 1] = data[i];
      }

      return data2;
   }

   public static Number optimizeBigint(BigInteger bigint) {
      BigInteger MAXLONG = BigInteger.valueOf(Long.MAX_VALUE);
      BigInteger MINLONG = BigInteger.valueOf(Long.MIN_VALUE);
      switch (bigint.signum()) {
         case -1:
            if (bigint.compareTo(MINLONG) >= 0) {
               return bigint.longValue();
            }
            break;
         case 0:
            return 0L;
         case 1:
            if (bigint.compareTo(MAXLONG) <= 0) {
               return bigint.longValue();
            }
      }

      return bigint;
   }

   public static String rawStringFromBytes(byte[] data) {
      StringBuilder str = new StringBuilder(data.length);

      for(byte b : data) {
         str.append((char)(b & 255));
      }

      return str.toString();
   }

   public static byte[] str2bytes(String str) throws IOException {
      byte[] b = new byte[str.length()];

      for(int i = 0; i < str.length(); ++i) {
         char c = str.charAt(i);
         if (c > 255) {
            throw new UnsupportedEncodingException("string contained a char > 255, cannot convert to bytes");
         }

         b[i] = (byte)c;
      }

      return b;
   }

   public static String decode_escaped(String str) {
      if (str.indexOf(92) == -1) {
         return str;
      } else {
         StringBuilder sb = new StringBuilder(str.length());

         for(int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == '\\') {
               ++i;
               char c2 = str.charAt(i);
               switch (c2) {
                  case '\'':
                     sb.append('\'');
                     break;
                  case '\\':
                     sb.append(c);
                     break;
                  case 'n':
                     sb.append('\n');
                     break;
                  case 'r':
                     sb.append('\r');
                     break;
                  case 't':
                     sb.append('\t');
                     break;
                  case 'x':
                     ++i;
                     char h1 = str.charAt(i);
                     ++i;
                     char h2 = str.charAt(i);
                     c2 = (char)Integer.parseInt("" + h1 + h2, 16);
                     sb.append(c2);
                     break;
                  default:
                     if (str.length() > 80) {
                        str = str.substring(0, 80);
                     }

                     throw new PickleException("invalid escape sequence char '" + c2 + "' in string \"" + str + " [...]\" (possibly truncated)");
               }
            } else {
               sb.append(str.charAt(i));
            }
         }

         return sb.toString();
      }
   }

   public static String decode_unicode_escaped(String str) {
      if (str.indexOf(92) == -1) {
         return str;
      } else {
         StringBuilder sb = new StringBuilder(str.length());

         for(int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == '\\') {
               ++i;
               char c2 = str.charAt(i);
               switch (c2) {
                  case 'U':
                     ++i;
                     char h1 = str.charAt(i);
                     ++i;
                     char h2 = str.charAt(i);
                     ++i;
                     char h3 = str.charAt(i);
                     ++i;
                     char h4 = str.charAt(i);
                     ++i;
                     char h5 = str.charAt(i);
                     ++i;
                     char h6 = str.charAt(i);
                     ++i;
                     char h7 = str.charAt(i);
                     ++i;
                     char h8 = str.charAt(i);
                     String encoded = "" + h1 + h2 + h3 + h4 + h5 + h6 + h7 + h8;
                     String s = new String(Character.toChars(Integer.parseInt(encoded, 16)));
                     sb.append(s);
                     break;
                  case '\\':
                     sb.append(c);
                     break;
                  case 'n':
                     sb.append('\n');
                     break;
                  case 'r':
                     sb.append('\r');
                     break;
                  case 't':
                     sb.append('\t');
                     break;
                  case 'u':
                     ++i;
                     char h1 = str.charAt(i);
                     ++i;
                     char h2 = str.charAt(i);
                     ++i;
                     char h3 = str.charAt(i);
                     ++i;
                     char h4 = str.charAt(i);
                     c2 = (char)Integer.parseInt("" + h1 + h2 + h3 + h4, 16);
                     sb.append(c2);
                     break;
                  default:
                     if (str.length() > 80) {
                        str = str.substring(0, 80);
                     }

                     throw new PickleException("invalid escape sequence char '" + c2 + "' in string \"" + str + " [...]\" (possibly truncated)");
               }
            } else {
               sb.append(str.charAt(i));
            }
         }

         return sb.toString();
      }
   }
}
