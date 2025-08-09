package org.sparkproject.jetty.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlEncoded {
   static final Logger LOG = LoggerFactory.getLogger(UrlEncoded.class);
   public static final Charset ENCODING;

   private UrlEncoded() {
   }

   public static String encode(MultiMap map, Charset charset, boolean equalsForNullValue) {
      if (charset == null) {
         charset = ENCODING;
      }

      StringBuilder result = new StringBuilder(128);
      boolean delim = false;

      for(Map.Entry entry : map.entrySet()) {
         String key = (String)entry.getKey();
         List<String> list = (List)entry.getValue();
         int s = list.size();
         if (delim) {
            result.append('&');
         }

         if (s == 0) {
            result.append(encodeString(key, charset));
            if (equalsForNullValue) {
               result.append('=');
            }
         } else {
            for(int i = 0; i < s; ++i) {
               if (i > 0) {
                  result.append('&');
               }

               String val = (String)list.get(i);
               result.append(encodeString(key, charset));
               if (val != null) {
                  if (val.length() > 0) {
                     result.append('=');
                     result.append(encodeString(val, charset));
                  } else if (equalsForNullValue) {
                     result.append('=');
                  }
               } else if (equalsForNullValue) {
                  result.append('=');
               }
            }
         }

         delim = true;
      }

      return result.toString();
   }

   /** @deprecated */
   @Deprecated(
      since = "10",
      forRemoval = true
   )
   public static void decodeTo(String content, MultiMap map, String charset) {
      decodeTo(content, map, charset == null ? null : Charset.forName(charset));
   }

   public static void decodeTo(String content, MultiMap map, Charset charset) {
      decodeTo(content, map, charset, -1);
   }

   public static void decodeTo(String content, MultiMap map, Charset charset, int maxKeys) {
      if (charset == null) {
         charset = ENCODING;
      }

      if (StandardCharsets.UTF_8.equals(charset)) {
         decodeUtf8To(content, 0, content.length(), map);
      } else {
         String key = null;
         int mark = -1;
         boolean encoded = false;

         for(int i = 0; i < content.length(); ++i) {
            char c = content.charAt(i);
            switch (c) {
               case '%':
               case '+':
                  encoded = true;
                  break;
               case '&':
                  int l = i - mark - 1;
                  String value = l == 0 ? "" : (encoded ? decodeString(content, mark + 1, l, charset) : content.substring(mark + 1, i));
                  mark = i;
                  encoded = false;
                  if (key != null) {
                     map.add(key, value);
                  } else if (value != null && value.length() > 0) {
                     map.add(value, "");
                  }

                  checkMaxKeys(map, maxKeys);
                  key = null;
                  String value = null;
                  break;
               case '=':
                  if (key == null) {
                     key = encoded ? decodeString(content, mark + 1, i - mark - 1, charset) : content.substring(mark + 1, i);
                     mark = i;
                     encoded = false;
                  }
            }
         }

         if (key != null) {
            int l = content.length() - mark - 1;
            String value = l == 0 ? "" : (encoded ? decodeString(content, mark + 1, l, charset) : content.substring(mark + 1));
            map.add(key, value);
            checkMaxKeys(map, maxKeys);
         } else if (mark < content.length()) {
            key = encoded ? decodeString(content, mark + 1, content.length() - mark - 1, charset) : content.substring(mark + 1);
            if (key != null && key.length() > 0) {
               map.add(key, "");
               checkMaxKeys(map, maxKeys);
            }
         }

      }
   }

   public static void decodeUtf8To(String query, MultiMap map) {
      decodeUtf8To(query, 0, query.length(), map);
   }

   public static void decodeUtf8To(String query, int offset, int length, MultiMap map) {
      Utf8StringBuilder buffer = new Utf8StringBuilder();
      String key = null;
      int end = offset + length;

      for(int i = offset; i < end; ++i) {
         char c = query.charAt(i);
         switch (c) {
            case '%':
               if (i + 2 >= end) {
                  throw new Utf8Appendable.NotUtf8Exception("Incomplete % encoding");
               }

               ++i;
               char hi = query.charAt(i);
               ++i;
               char lo = query.charAt(i);
               buffer.append(decodeHexByte(hi, lo));
               break;
            case '&':
               String value = buffer.toReplacedString();
               buffer.reset();
               if (key != null) {
                  map.add(key, value);
               } else if (value != null && value.length() > 0) {
                  map.add(value, "");
               }

               key = null;
               break;
            case '+':
               buffer.append((byte)32);
               break;
            case '=':
               if (key != null) {
                  buffer.append(c);
               } else {
                  key = buffer.toReplacedString();
                  buffer.reset();
               }
               break;
            default:
               buffer.append(c);
         }
      }

      if (key != null) {
         String value = buffer.toReplacedString();
         buffer.reset();
         map.add(key, value);
      } else if (buffer.length() > 0) {
         map.add(buffer.toReplacedString(), "");
      }

   }

   public static void decode88591To(InputStream in, MultiMap map, int maxLength, int maxKeys) throws IOException {
      StringBuilder buffer = new StringBuilder();
      String key = null;
      int totalLength = 0;

      int b;
      while((b = in.read()) >= 0) {
         switch ((char)b) {
            case '%':
               int code0 = in.read();
               int code1 = in.read();
               buffer.append(decodeHexChar(code0, code1));
               break;
            case '&':
               String value = buffer.length() == 0 ? "" : buffer.toString();
               buffer.setLength(0);
               if (key != null) {
                  map.add(key, value);
               } else if (value.length() > 0) {
                  map.add(value, "");
               }

               key = null;
               checkMaxKeys(map, maxKeys);
               break;
            case '+':
               buffer.append(' ');
               break;
            case '=':
               if (key != null) {
                  buffer.append((char)b);
               } else {
                  key = buffer.toString();
                  buffer.setLength(0);
               }
               break;
            default:
               buffer.append((char)b);
         }

         ++totalLength;
         checkMaxLength(totalLength, maxLength);
      }

      if (key != null) {
         String value = buffer.length() == 0 ? "" : buffer.toString();
         buffer.setLength(0);
         map.add(key, value);
      } else if (buffer.length() > 0) {
         map.add(buffer.toString(), "");
      }

      checkMaxKeys(map, maxKeys);
   }

   public static void decodeUtf8To(InputStream in, MultiMap map, int maxLength, int maxKeys) throws IOException {
      Utf8StringBuilder buffer = new Utf8StringBuilder();
      String key = null;
      int totalLength = 0;

      int b;
      while((b = in.read()) >= 0) {
         switch ((char)b) {
            case '%':
               char code0 = (char)in.read();
               char code1 = (char)in.read();
               buffer.append(decodeHexByte(code0, code1));
               break;
            case '&':
               String value = buffer.toReplacedString();
               buffer.reset();
               if (key != null) {
                  map.add(key, value);
               } else if (value != null && value.length() > 0) {
                  map.add(value, "");
               }

               key = null;
               checkMaxKeys(map, maxKeys);
               break;
            case '+':
               buffer.append((byte)32);
               break;
            case '=':
               if (key != null) {
                  buffer.append((byte)b);
               } else {
                  key = buffer.toReplacedString();
                  buffer.reset();
               }
               break;
            default:
               buffer.append((byte)b);
         }

         ++totalLength;
         checkMaxLength(totalLength, maxLength);
      }

      if (key != null) {
         String value = buffer.toReplacedString();
         buffer.reset();
         map.add(key, value);
      } else if (buffer.length() > 0) {
         map.add(buffer.toReplacedString(), "");
      }

      checkMaxKeys(map, maxKeys);
   }

   public static void decodeUtf16To(InputStream in, MultiMap map, int maxLength, int maxKeys) throws IOException {
      InputStreamReader input = new InputStreamReader(in, StandardCharsets.UTF_16);
      StringWriter buf = new StringWriter(8192);
      IO.copy((Reader)input, (Writer)buf, (long)maxLength);
      decodeTo(buf.getBuffer().toString(), map, StandardCharsets.UTF_16, maxKeys);
   }

   /** @deprecated */
   @Deprecated(
      since = "10",
      forRemoval = true
   )
   public static void decodeTo(InputStream in, MultiMap map, String charset, int maxLength, int maxKeys) throws IOException {
      if (charset == null) {
         if (ENCODING.equals(StandardCharsets.UTF_8)) {
            decodeUtf8To(in, map, maxLength, maxKeys);
         } else {
            decodeTo(in, map, ENCODING, maxLength, maxKeys);
         }
      } else if ("utf-8".equalsIgnoreCase(charset)) {
         decodeUtf8To(in, map, maxLength, maxKeys);
      } else if ("iso-8859-1".equalsIgnoreCase(charset)) {
         decode88591To(in, map, maxLength, maxKeys);
      } else if ("utf-16".equalsIgnoreCase(charset)) {
         decodeUtf16To(in, map, maxLength, maxKeys);
      } else {
         decodeTo(in, map, Charset.forName(charset), maxLength, maxKeys);
      }

   }

   public static void decodeTo(InputStream in, MultiMap map, Charset charset, int maxLength, int maxKeys) throws IOException {
      if (charset == null) {
         charset = ENCODING;
      }

      if (StandardCharsets.UTF_8.equals(charset)) {
         decodeUtf8To(in, map, maxLength, maxKeys);
      } else if (StandardCharsets.ISO_8859_1.equals(charset)) {
         decode88591To(in, map, maxLength, maxKeys);
      } else if (StandardCharsets.UTF_16.equals(charset)) {
         decodeUtf16To(in, map, maxLength, maxKeys);
      } else {
         String key = null;
         int totalLength = 0;
         ByteArrayOutputStream2 output = new ByteArrayOutputStream2();

         try {
            int c;
            while((c = in.read()) > 0) {
               switch ((char)c) {
                  case '%':
                     int code0 = in.read();
                     int code1 = in.read();
                     output.write(decodeHexChar(code0, code1));
                     break;
                  case '&':
                     int size = output.size();
                     String value = size == 0 ? "" : output.toString(charset);
                     output.setCount(0);
                     if (key != null) {
                        map.add(key, value);
                     } else if (value != null && value.length() > 0) {
                        map.add(value, "");
                     }

                     key = null;
                     checkMaxKeys(map, maxKeys);
                     break;
                  case '+':
                     output.write(32);
                     break;
                  case '=':
                     if (key != null) {
                        output.write(c);
                     } else {
                        int size = output.size();
                        key = size == 0 ? "" : output.toString(charset);
                        output.setCount(0);
                     }
                     break;
                  default:
                     output.write(c);
               }

               ++totalLength;
               checkMaxLength(totalLength, maxLength);
            }

            int size = output.size();
            if (key != null) {
               String value = size == 0 ? "" : output.toString(charset);
               output.setCount(0);
               map.add(key, value);
            } else if (size > 0) {
               map.add(output.toString(charset), "");
            }

            checkMaxKeys(map, maxKeys);
         } catch (Throwable var14) {
            try {
               output.close();
            } catch (Throwable var13) {
               var14.addSuppressed(var13);
            }

            throw var14;
         }

         output.close();
      }
   }

   private static void checkMaxKeys(MultiMap map, int maxKeys) {
      int size = map.size();
      if (maxKeys >= 0 && size > maxKeys) {
         throw new IllegalStateException(String.format("Form with too many keys [%d > %d]", size, maxKeys));
      }
   }

   private static void checkMaxLength(int length, int maxLength) {
      if (maxLength >= 0 && length > maxLength) {
         throw new IllegalStateException("Form is larger than max length " + maxLength);
      }
   }

   public static String decodeString(String encoded) {
      return decodeString(encoded, 0, encoded.length(), ENCODING);
   }

   public static String decodeString(String encoded, int offset, int length, Charset charset) {
      if (charset != null && !StandardCharsets.UTF_8.equals(charset)) {
         StringBuffer buffer = null;

         for(int i = 0; i < length; ++i) {
            char c = encoded.charAt(offset + i);
            if (c > 255) {
               if (buffer == null) {
                  buffer = new StringBuffer(length);
                  buffer.append(encoded, offset, offset + i + 1);
               } else {
                  buffer.append(c);
               }
            } else if (c == '+') {
               if (buffer == null) {
                  buffer = new StringBuffer(length);
                  buffer.append(encoded, offset, offset + i);
               }

               buffer.append(' ');
            } else if (c != '%') {
               if (buffer != null) {
                  buffer.append(c);
               }
            } else {
               if (buffer == null) {
                  buffer = new StringBuffer(length);
                  buffer.append(encoded, offset, offset + i);
               }

               byte[] ba = new byte[length];

               int n;
               for(n = 0; c <= 255; c = encoded.charAt(offset + i)) {
                  if (c == '%') {
                     if (i + 2 < length) {
                        int o = offset + i + 1;
                        i += 3;
                        ba[n] = (byte)TypeUtil.parseInt((String)encoded, o, 2, 16);
                        ++n;
                     } else {
                        ba[n++] = 63;
                        i = length;
                     }
                  } else if (c == '+') {
                     ba[n++] = 32;
                     ++i;
                  } else {
                     ba[n++] = (byte)c;
                     ++i;
                  }

                  if (i >= length) {
                     break;
                  }
               }

               --i;
               buffer.append(new String(ba, 0, n, charset));
            }
         }

         if (buffer == null) {
            if (offset == 0 && encoded.length() == length) {
               return encoded;
            } else {
               return encoded.substring(offset, offset + length);
            }
         } else {
            return buffer.toString();
         }
      } else {
         Utf8StringBuffer buffer = null;

         for(int i = 0; i < length; ++i) {
            char c = encoded.charAt(offset + i);
            if (c > 255) {
               if (buffer == null) {
                  buffer = new Utf8StringBuffer(length);
                  buffer.getStringBuffer().append(encoded, offset, offset + i + 1);
               } else {
                  buffer.getStringBuffer().append(c);
               }
            } else if (c == '+') {
               if (buffer == null) {
                  buffer = new Utf8StringBuffer(length);
                  buffer.getStringBuffer().append(encoded, offset, offset + i);
               }

               buffer.getStringBuffer().append(' ');
            } else if (c == '%') {
               if (buffer == null) {
                  buffer = new Utf8StringBuffer(length);
                  buffer.getStringBuffer().append(encoded, offset, offset + i);
               }

               if (i + 2 < length) {
                  int o = offset + i + 1;
                  i += 2;
                  byte b = (byte)TypeUtil.parseInt((String)encoded, o, 2, 16);
                  buffer.append(b);
               } else {
                  buffer.getStringBuffer().append('�');
                  i = length;
               }
            } else if (buffer != null) {
               buffer.getStringBuffer().append(c);
            }
         }

         if (buffer == null) {
            if (offset == 0 && encoded.length() == length) {
               return encoded;
            } else {
               return encoded.substring(offset, offset + length);
            }
         } else {
            return buffer.toReplacedString();
         }
      }
   }

   private static char decodeHexChar(int hi, int lo) {
      try {
         return (char)((TypeUtil.convertHexDigit(hi) << 4) + TypeUtil.convertHexDigit(lo));
      } catch (NumberFormatException var3) {
         throw new IllegalArgumentException("Not valid encoding '%" + (char)hi + (char)lo + "'");
      }
   }

   private static byte decodeHexByte(char hi, char lo) {
      try {
         return (byte)((TypeUtil.convertHexDigit(hi) << 4) + TypeUtil.convertHexDigit(lo));
      } catch (NumberFormatException var3) {
         throw new IllegalArgumentException("Not valid encoding '%" + hi + lo + "'");
      }
   }

   public static String encodeString(String string) {
      return encodeString(string, ENCODING);
   }

   public static String encodeString(String string, Charset charset) {
      if (charset == null) {
         charset = ENCODING;
      }

      byte[] bytes = string.getBytes(charset);
      byte[] encoded = new byte[bytes.length * 3];
      int n = 0;
      boolean noEncode = true;

      for(byte b : bytes) {
         if (b == 32) {
            noEncode = false;
            encoded[n++] = 43;
         } else if ((b < 97 || b > 122) && (b < 65 || b > 90) && (b < 48 || b > 57) && b != 45 && b != 46 && b != 95 && b != 126) {
            noEncode = false;
            encoded[n++] = 37;
            byte nibble = (byte)((b & 240) >> 4);
            if (nibble >= 10) {
               encoded[n++] = (byte)(65 + nibble - 10);
            } else {
               encoded[n++] = (byte)(48 + nibble);
            }

            nibble = (byte)(b & 15);
            if (nibble >= 10) {
               encoded[n++] = (byte)(65 + nibble - 10);
            } else {
               encoded[n++] = (byte)(48 + nibble);
            }
         } else {
            encoded[n++] = b;
         }
      }

      if (noEncode) {
         return string;
      } else {
         return new String(encoded, 0, n, charset);
      }
   }

   static {
      String charset = null;

      Charset encoding;
      try {
         charset = System.getProperty("org.sparkproject.jetty.util.UrlEncoding.charset");
         if (charset == null) {
            charset = StandardCharsets.UTF_8.toString();
            encoding = StandardCharsets.UTF_8;
         } else {
            encoding = Charset.forName(charset);
         }
      } catch (Exception e) {
         LOG.warn("Unable to set default UrlEncoding charset: {}", charset, e);
         encoding = StandardCharsets.UTF_8;
      }

      ENCODING = encoding;
   }
}
