package org.sparkproject.jetty.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringUtil {
   public static final String ALL_INTERFACES = "0.0.0.0";
   public static final String CRLF = "\r\n";
   public static final String DEFAULT_DELIMS = ",;";
   public static final String __ISO_8859_1 = "iso-8859-1";
   public static final String __UTF8 = "utf-8";
   public static final String __UTF16 = "utf-16";
   private static final Index CHARSETS = (new Index.Builder()).caseSensitive(false).with("utf-8", "utf-8").with("utf8", "utf-8").with("utf-16", "utf-16").with("utf16", "utf-16").with("iso-8859-1", "iso-8859-1").with("iso_8859_1", "iso-8859-1").build();
   private static final char[] LOWERCASES = new char[]{'\u0000', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '\u007f'};
   private static final char[] UPPERCASES = new char[]{'\u0000', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', '\u007f'};

   public static String normalizeCharset(String s) {
      String n = (String)CHARSETS.get(s);
      return n == null ? s : n;
   }

   public static String normalizeCharset(String s, int offset, int length) {
      String n = (String)CHARSETS.get(s, offset, length);
      return n == null ? s.substring(offset, offset + length) : n;
   }

   public static char asciiToLowerCase(char c) {
      return c < 128 ? LOWERCASES[c] : c;
   }

   public static byte asciiToLowerCase(byte c) {
      return c > 0 ? (byte)LOWERCASES[c] : c;
   }

   public static char asciiToUpperCase(char c) {
      return c < 128 ? UPPERCASES[c] : c;
   }

   public static byte asciiToUpperCase(byte c) {
      return c > 0 ? (byte)UPPERCASES[c] : c;
   }

   public static String asciiToLowerCase(String s) {
      if (s == null) {
         return null;
      } else {
         char[] c = null;
         int i = s.length();

         while(i-- > 0) {
            char c1 = s.charAt(i);
            if (c1 <= 127) {
               char c2 = LOWERCASES[c1];
               if (c1 != c2) {
                  c = s.toCharArray();
                  c[i] = c2;
                  break;
               }
            }
         }

         while(i-- > 0) {
            if (c[i] <= 127) {
               c[i] = LOWERCASES[c[i]];
            }
         }

         return c == null ? s : new String(c);
      }
   }

   public static String asciiToUpperCase(String s) {
      if (s == null) {
         return null;
      } else {
         char[] c = null;
         int i = s.length();

         while(i-- > 0) {
            char c1 = s.charAt(i);
            if (c1 <= 127) {
               char c2 = UPPERCASES[c1];
               if (c1 != c2) {
                  c = s.toCharArray();
                  c[i] = c2;
                  break;
               }
            }
         }

         while(i-- > 0) {
            if (c[i] <= 127) {
               c[i] = UPPERCASES[c[i]];
            }
         }

         return c == null ? s : new String(c);
      }
   }

   public static String sanitizeFileSystemName(String str) {
      if (str == null) {
         return null;
      } else {
         char[] chars = str.toCharArray();
         int len = chars.length;

         for(int i = 0; i < len; ++i) {
            char c = chars[i];
            if (c <= 31 || c >= 127 || c == '|' || c == '>' || c == '<' || c == '/' || c == '&' || c == '\\' || c == '.' || c == ':' || c == '=' || c == '"' || c == ',' || c == '*' || c == '?' || c == '!' || c == ' ') {
               chars[i] = '_';
            }
         }

         return String.valueOf(chars);
      }
   }

   public static boolean startsWithIgnoreCase(String s, String w) {
      if (w == null) {
         return true;
      } else if (s != null && s.length() >= w.length()) {
         for(int i = 0; i < w.length(); ++i) {
            char c1 = s.charAt(i);
            char c2 = w.charAt(i);
            if (c1 != c2) {
               if (c1 <= 127) {
                  c1 = LOWERCASES[c1];
               }

               if (c2 <= 127) {
                  c2 = LOWERCASES[c2];
               }

               if (c1 != c2) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean endsWithIgnoreCase(String s, String w) {
      if (w == null) {
         return true;
      } else if (s == null) {
         return false;
      } else {
         int sl = s.length();
         int wl = w.length();
         if (sl < wl) {
            return false;
         } else {
            int i = wl;

            while(i-- > 0) {
               --sl;
               char c1 = s.charAt(sl);
               char c2 = w.charAt(i);
               if (c1 != c2) {
                  if (c1 <= 127) {
                     c1 = LOWERCASES[c1];
                  }

                  if (c2 <= 127) {
                     c2 = LOWERCASES[c2];
                  }

                  if (c1 != c2) {
                     return false;
                  }
               }
            }

            return true;
         }
      }
   }

   public static int indexFrom(String s, String chars) {
      for(int i = 0; i < s.length(); ++i) {
         if (chars.indexOf(s.charAt(i)) >= 0) {
            return i;
         }
      }

      return -1;
   }

   public static String replace(String str, char find, char with) {
      if (str == null) {
         return null;
      } else if (find == with) {
         return str;
      } else {
         int c = 0;
         int idx = str.indexOf(find, c);
         if (idx == -1) {
            return str;
         } else {
            char[] chars = str.toCharArray();
            int len = chars.length;

            for(int i = idx; i < len; ++i) {
               if (chars[i] == find) {
                  chars[i] = with;
               }
            }

            return String.valueOf(chars);
         }
      }
   }

   public static String replace(String s, String sub, String with) {
      if (s == null) {
         return null;
      } else {
         int c = 0;
         int i = s.indexOf(sub, c);
         if (i == -1) {
            return s;
         } else {
            StringBuilder buf = new StringBuilder(s.length() + with.length());

            do {
               buf.append(s, c, i);
               buf.append(with);
               c = i + sub.length();
            } while((i = s.indexOf(sub, c)) != -1);

            if (c < s.length()) {
               buf.append(s.substring(c));
            }

            return buf.toString();
         }
      }
   }

   public static String replaceFirst(String original, String target, String replacement) {
      int idx = original.indexOf(target);
      if (idx == -1) {
         return original;
      } else {
         int offset = 0;
         int originalLen = original.length();
         StringBuilder buf = new StringBuilder(originalLen + replacement.length());
         buf.append(original, offset, idx);
         offset += idx + target.length();
         buf.append(replacement);
         buf.append(original, offset, originalLen);
         return buf.toString();
      }
   }

   public static void append(StringBuilder buf, String s, int offset, int length) {
      synchronized(buf) {
         int end = offset + length;

         for(int i = offset; i < end && i < s.length(); ++i) {
            buf.append(s.charAt(i));
         }

      }
   }

   public static void append(StringBuilder buf, byte b, int base) {
      int bi = 255 & b;
      int c = 48 + bi / base % base;
      if (c > 57) {
         c = 97 + (c - 48 - 10);
      }

      buf.append((char)c);
      c = 48 + bi % base;
      if (c > 57) {
         c = 97 + (c - 48 - 10);
      }

      buf.append((char)c);
   }

   public static void append2digits(StringBuffer buf, int i) {
      if (i < 100) {
         buf.append((char)(i / 10 + 48));
         buf.append((char)(i % 10 + 48));
      }

   }

   public static void append2digits(StringBuilder buf, int i) {
      if (i < 100) {
         buf.append((char)(i / 10 + 48));
         buf.append((char)(i % 10 + 48));
      }

   }

   public static String stringFrom(String s, int n) {
      StringBuilder stringBuilder = new StringBuilder(s.length() * n);

      for(int i = 0; i < n; ++i) {
         stringBuilder.append(s);
      }

      return stringBuilder.toString();
   }

   public static String nonNull(String s) {
      return s == null ? "" : s;
   }

   public static boolean equals(String s, char[] buf, int offset, int length) {
      if (s.length() != length) {
         return false;
      } else {
         for(int i = 0; i < length; ++i) {
            if (buf[offset + i] != s.charAt(i)) {
               return false;
            }
         }

         return true;
      }
   }

   public static String toUTF8String(byte[] b, int offset, int length) {
      return new String(b, offset, length, StandardCharsets.UTF_8);
   }

   /** @deprecated */
   @Deprecated(
      since = "10",
      forRemoval = true
   )
   public static String toString(byte[] b, int offset, int length, String charset) {
      try {
         return new String(b, offset, length, charset);
      } catch (UnsupportedEncodingException e) {
         throw new IllegalArgumentException(e);
      }
   }

   public static int indexOfControlChars(String str) {
      if (str == null) {
         return -1;
      } else {
         int len = str.length();

         for(int i = 0; i < len; ++i) {
            if (Character.isISOControl(str.codePointAt(i))) {
               return i;
            }
         }

         return -1;
      }
   }

   public static boolean isBlank(String str) {
      if (str == null) {
         return true;
      } else {
         int len = str.length();

         for(int i = 0; i < len; ++i) {
            if (!Character.isWhitespace(str.codePointAt(i))) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean isEmpty(String str) {
      return str == null || str.isEmpty();
   }

   public static int getLength(String s) {
      return s == null ? 0 : s.length();
   }

   public static boolean isNotBlank(String str) {
      if (str == null) {
         return false;
      } else {
         int len = str.length();

         for(int i = 0; i < len; ++i) {
            if (!Character.isWhitespace(str.codePointAt(i))) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean isUTF8(String charset) {
      return "utf-8".equalsIgnoreCase(charset) || "utf-8".equalsIgnoreCase(normalizeCharset(charset));
   }

   public static boolean isHex(String str, int offset, int length) {
      if (offset + length > str.length()) {
         return false;
      } else {
         for(int i = offset; i < offset + length; ++i) {
            char c = str.charAt(i);
            if ((c < 'a' || c > 'f') && (c < 'A' || c > 'F') && (c < '0' || c > '9')) {
               return false;
            }
         }

         return true;
      }
   }

   public static byte[] fromHexString(String s) {
      if (s.length() % 2 != 0) {
         throw new IllegalArgumentException(s);
      } else {
         byte[] array = new byte[s.length() / 2];

         for(int i = 0; i < array.length; ++i) {
            int b = Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
            array[i] = (byte)(255 & b);
         }

         return array;
      }
   }

   public static String toHexString(byte b) {
      return toHexString(new byte[]{b}, 0, 1);
   }

   public static String toHexString(byte[] b) {
      return toHexString((byte[])Objects.requireNonNull(b, "ByteBuffer cannot be null"), 0, b.length);
   }

   public static String toHexString(byte[] b, int offset, int length) {
      StringBuilder buf = new StringBuilder();

      for(int i = offset; i < offset + length; ++i) {
         int bi = 255 & b[i];
         int c = 48 + bi / 16 % 16;
         if (c > 57) {
            c = 65 + (c - 48 - 10);
         }

         buf.append((char)c);
         c = 48 + bi % 16;
         if (c > 57) {
            c = 97 + (c - 48 - 10);
         }

         buf.append((char)c);
      }

      return buf.toString();
   }

   public static String printable(String name) {
      if (name == null) {
         return null;
      } else {
         StringBuilder buf = new StringBuilder(name.length());

         for(int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            if (!Character.isISOControl(c)) {
               buf.append(c);
            }
         }

         return buf.toString();
      }
   }

   public static String printable(byte[] b) {
      StringBuilder buf = new StringBuilder();

      for(int i = 0; i < b.length; ++i) {
         char c = (char)b[i];
         if (!Character.isWhitespace(c) && (c <= ' ' || c >= 127)) {
            buf.append("0x");
            TypeUtil.toHex((byte)b[i], buf);
         } else {
            buf.append(c);
         }
      }

      return buf.toString();
   }

   public static byte[] getBytes(String s) {
      return s.getBytes(StandardCharsets.ISO_8859_1);
   }

   public static byte[] getBytes(String s, String charset) {
      try {
         return s.getBytes(charset);
      } catch (Exception var3) {
         return s.getBytes();
      }
   }

   public static byte[] getUtf8Bytes(String s) {
      return s.getBytes(StandardCharsets.UTF_8);
   }

   public static int toInt(String string, int from) {
      int val = 0;
      boolean started = false;
      boolean minus = false;

      for(int i = from; i < string.length(); ++i) {
         char b = string.charAt(i);
         if (b <= ' ') {
            if (started) {
               break;
            }
         } else if (b >= '0' && b <= '9') {
            val = val * 10 + (b - 48);
            started = true;
         } else {
            if (b != '-' || started) {
               break;
            }

            minus = true;
         }
      }

      if (started) {
         return minus ? -val : val;
      } else {
         throw new NumberFormatException(string);
      }
   }

   public static long toLong(String string) {
      long val = 0L;
      boolean started = false;
      boolean minus = false;

      for(int i = 0; i < string.length(); ++i) {
         char b = string.charAt(i);
         if (b <= ' ') {
            if (started) {
               break;
            }
         } else if (b >= '0' && b <= '9') {
            val = val * 10L + (long)(b - 48);
            started = true;
         } else {
            if (b != '-' || started) {
               break;
            }

            minus = true;
         }
      }

      if (started) {
         return minus ? -val : val;
      } else {
         throw new NumberFormatException(string);
      }
   }

   public static String truncate(String str, int maxSize) {
      if (str == null) {
         return null;
      } else {
         return str.length() <= maxSize ? str : str.substring(0, maxSize);
      }
   }

   public static String[] arrayFromString(String s) {
      if (s == null) {
         return new String[0];
      } else if (s.startsWith("[") && s.endsWith("]")) {
         return s.length() == 2 ? new String[0] : csvSplit(s, 1, s.length() - 2);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static String[] csvSplit(String s) {
      return s == null ? null : csvSplit(s, 0, s.length());
   }

   public static String[] csvSplit(String s, int off, int len) {
      if (s == null) {
         return null;
      } else if (off >= 0 && len >= 0 && off <= s.length()) {
         List<String> list = new ArrayList();
         csvSplit(list, s, off, len);
         return (String[])list.toArray(new String[list.size()]);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static List csvSplit(List list, String s, int off, int len) {
      if (list == null) {
         list = new ArrayList();
      }

      CsvSplitState state = StringUtil.CsvSplitState.PRE_DATA;
      StringBuilder out = new StringBuilder();
      int last = -1;

      while(len > 0) {
         char ch = s.charAt(off++);
         --len;
         switch (state.ordinal()) {
            case 0:
               if (!Character.isWhitespace(ch)) {
                  if ('"' == ch) {
                     state = StringUtil.CsvSplitState.QUOTE;
                  } else if (',' == ch) {
                     list.add("");
                  } else {
                     state = StringUtil.CsvSplitState.DATA;
                     out.append(ch);
                  }
               }
               break;
            case 1:
               if ('\\' == ch) {
                  state = StringUtil.CsvSplitState.SLOSH;
               } else if ('"' == ch) {
                  list.add(out.toString());
                  out.setLength(0);
                  state = StringUtil.CsvSplitState.POST_DATA;
               } else {
                  out.append(ch);
               }
               break;
            case 2:
               out.append(ch);
               state = StringUtil.CsvSplitState.QUOTE;
               break;
            case 3:
               if (Character.isWhitespace(ch)) {
                  last = out.length();
                  out.append(ch);
                  state = StringUtil.CsvSplitState.WHITE;
               } else if (',' == ch) {
                  list.add(out.toString());
                  out.setLength(0);
                  state = StringUtil.CsvSplitState.PRE_DATA;
               } else {
                  out.append(ch);
               }
               break;
            case 4:
               if (Character.isWhitespace(ch)) {
                  out.append(ch);
               } else if (',' == ch) {
                  out.setLength(last);
                  list.add(out.toString());
                  out.setLength(0);
                  state = StringUtil.CsvSplitState.PRE_DATA;
               } else {
                  state = StringUtil.CsvSplitState.DATA;
                  out.append(ch);
                  last = -1;
               }
               break;
            case 5:
               if (',' == ch) {
                  state = StringUtil.CsvSplitState.PRE_DATA;
               }
               break;
            default:
               throw new IllegalStateException(state.toString());
         }
      }

      switch (state.ordinal()) {
         case 0:
         case 5:
            break;
         case 1:
         case 2:
         case 3:
            list.add(out.toString());
            break;
         case 4:
            out.setLength(last);
            list.add(out.toString());
            break;
         default:
            throw new IllegalStateException(state.toString());
      }

      return list;
   }

   public static String sanitizeXmlString(String html) {
      if (html == null) {
         return null;
      } else {
         int i;
         label54:
         for(i = 0; i < html.length(); ++i) {
            char c = html.charAt(i);
            switch (c) {
               case '"':
               case '&':
               case '\'':
               case '<':
               case '>':
                  break label54;
               default:
                  if (Character.isISOControl(c) && !Character.isWhitespace(c)) {
                     break label54;
                  }
            }
         }

         if (i == html.length()) {
            return html;
         } else {
            StringBuilder out = new StringBuilder(html.length() * 4 / 3);
            out.append(html, 0, i);

            for(; i < html.length(); ++i) {
               char c = html.charAt(i);
               switch (c) {
                  case '"':
                     out.append("&quot;");
                     break;
                  case '&':
                     out.append("&amp;");
                     break;
                  case '\'':
                     out.append("&apos;");
                     break;
                  case '<':
                     out.append("&lt;");
                     break;
                  case '>':
                     out.append("&gt;");
                     break;
                  default:
                     if (Character.isISOControl(c) && !Character.isWhitespace(c)) {
                        out.append('?');
                     } else {
                        out.append(c);
                     }
               }
            }

            return out.toString();
         }
      }
   }

   public static String strip(String str, String find) {
      return replace(str, find, "");
   }

   public static String valueOf(Object object) {
      return object == null ? null : String.valueOf(object);
   }

   static enum CsvSplitState {
      PRE_DATA,
      QUOTE,
      SLOSH,
      DATA,
      WHITE,
      POST_DATA;

      // $FF: synthetic method
      private static CsvSplitState[] $values() {
         return new CsvSplitState[]{PRE_DATA, QUOTE, SLOSH, DATA, WHITE, POST_DATA};
      }
   }
}
