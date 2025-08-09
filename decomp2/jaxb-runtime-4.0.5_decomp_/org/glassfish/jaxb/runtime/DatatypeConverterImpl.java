package org.glassfish.jaxb.runtime;

import jakarta.xml.bind.DatatypeConverterInterface;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;
import java.util.WeakHashMap;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.glassfish.jaxb.core.WhiteSpaceProcessor;

/** @deprecated */
@Deprecated
public final class DatatypeConverterImpl implements DatatypeConverterInterface {
   /** @deprecated */
   @Deprecated
   public static final DatatypeConverterInterface theInstance = new DatatypeConverterImpl();
   private static final byte[] decodeMap = initDecodeMap();
   private static final byte PADDING = 127;
   private static final char[] encodeMap = initEncodeMap();
   private static final Map DF_CACHE = Collections.synchronizedMap(new WeakHashMap());
   /** @deprecated */
   @Deprecated
   private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

   protected DatatypeConverterImpl() {
   }

   public static BigInteger _parseInteger(CharSequence s) {
      return new BigInteger(removeOptionalPlus(WhiteSpaceProcessor.trim(s)).toString());
   }

   public static String _printInteger(BigInteger val) {
      return val.toString();
   }

   public static int _parseInt(CharSequence s) {
      int len = s.length();
      int sign = 1;
      int r = 0;

      for(int i = 0; i < len; ++i) {
         char ch = s.charAt(i);
         if (!WhiteSpaceProcessor.isWhiteSpace(ch)) {
            if ('0' <= ch && ch <= '9') {
               r = r * 10 + (ch - 48);
            } else if (ch == '-') {
               sign = -1;
            } else if (ch != '+') {
               throw new NumberFormatException("Not a number: " + s);
            }
         }
      }

      return r * sign;
   }

   public static long _parseLong(CharSequence s) {
      return Long.parseLong(removeOptionalPlus(WhiteSpaceProcessor.trim(s)).toString());
   }

   public static short _parseShort(CharSequence s) {
      return (short)_parseInt(s);
   }

   public static String _printShort(short val) {
      return String.valueOf(val);
   }

   public static BigDecimal _parseDecimal(CharSequence content) {
      content = WhiteSpaceProcessor.trim(content);
      return content.length() <= 0 ? null : new BigDecimal(content.toString());
   }

   public static float _parseFloat(CharSequence _val) {
      String s = WhiteSpaceProcessor.trim(_val).toString();
      if (s.equals("NaN")) {
         return Float.NaN;
      } else if (s.equals("INF")) {
         return Float.POSITIVE_INFINITY;
      } else if (s.equals("-INF")) {
         return Float.NEGATIVE_INFINITY;
      } else if (s.length() != 0 && isDigitOrPeriodOrSign(s.charAt(0)) && isDigitOrPeriodOrSign(s.charAt(s.length() - 1))) {
         return Float.parseFloat(s);
      } else {
         throw new NumberFormatException();
      }
   }

   public static String _printFloat(float v) {
      if (Float.isNaN(v)) {
         return "NaN";
      } else if (v == Float.POSITIVE_INFINITY) {
         return "INF";
      } else {
         return v == Float.NEGATIVE_INFINITY ? "-INF" : String.valueOf(v);
      }
   }

   public static double _parseDouble(CharSequence _val) {
      String val = WhiteSpaceProcessor.trim(_val).toString();
      if (val.equals("NaN")) {
         return Double.NaN;
      } else if (val.equals("INF")) {
         return Double.POSITIVE_INFINITY;
      } else if (val.equals("-INF")) {
         return Double.NEGATIVE_INFINITY;
      } else if (val.length() != 0 && isDigitOrPeriodOrSign(val.charAt(0)) && isDigitOrPeriodOrSign(val.charAt(val.length() - 1))) {
         return Double.parseDouble(val);
      } else {
         throw new NumberFormatException(val);
      }
   }

   public static Boolean _parseBoolean(CharSequence literal) {
      if (literal == null) {
         return null;
      } else {
         int i = 0;
         int len = literal.length();
         boolean value = false;
         if (len <= 0) {
            return null;
         } else {
            char ch;
            do {
               ch = literal.charAt(i++);
            } while(WhiteSpaceProcessor.isWhiteSpace(ch) && i < len);

            int strIndex = 0;
            switch (ch) {
               case '0':
                  value = false;
                  break;
               case '1':
                  value = true;
                  break;
               case 'f':
                  String strFalse = "alse";

                  do {
                     ch = literal.charAt(i++);
                  } while(strFalse.charAt(strIndex++) == ch && i < len && strIndex < 4);

                  if (strIndex != 4 || strFalse.charAt(strIndex - 1) != ch) {
                     return null;
                  }

                  value = false;
                  break;
               case 't':
                  String strTrue = "rue";

                  do {
                     ch = literal.charAt(i++);
                  } while(strTrue.charAt(strIndex++) == ch && i < len && strIndex < 3);

                  if (strIndex != 3 || strTrue.charAt(strIndex - 1) != ch) {
                     return null;
                  }

                  value = true;
            }

            if (i < len) {
               while(i < len && WhiteSpaceProcessor.isWhiteSpace(literal.charAt(i))) {
                  ++i;
               }
            }

            if (i == len) {
               return value;
            } else {
               return null;
            }
         }
      }
   }

   public static String _printBoolean(boolean val) {
      return val ? "true" : "false";
   }

   public static byte _parseByte(CharSequence literal) {
      return (byte)_parseInt(literal);
   }

   public static String _printByte(byte val) {
      return String.valueOf(val);
   }

   public static QName _parseQName(CharSequence text, NamespaceContext nsc) {
      int length = text.length();

      int start;
      for(start = 0; start < length && WhiteSpaceProcessor.isWhiteSpace(text.charAt(start)); ++start) {
      }

      int end;
      for(end = length; end > start && WhiteSpaceProcessor.isWhiteSpace(text.charAt(end - 1)); --end) {
      }

      if (end == start) {
         throw new IllegalArgumentException("input is empty");
      } else {
         int idx;
         for(idx = start + 1; idx < end && text.charAt(idx) != ':'; ++idx) {
         }

         String uri;
         String localPart;
         String prefix;
         if (idx == end) {
            uri = nsc.getNamespaceURI("");
            localPart = text.subSequence(start, end).toString();
            prefix = "";
         } else {
            prefix = text.subSequence(start, idx).toString();
            localPart = text.subSequence(idx + 1, end).toString();
            uri = nsc.getNamespaceURI(prefix);
            if (uri == null || uri.length() == 0) {
               throw new IllegalArgumentException("prefix " + prefix + " is not bound to a namespace");
            }
         }

         return new QName(uri, localPart, prefix);
      }
   }

   public static GregorianCalendar _parseDateTime(CharSequence s) {
      String val = WhiteSpaceProcessor.trim(s).toString();
      return getDatatypeFactory().newXMLGregorianCalendar(val).toGregorianCalendar();
   }

   public static String _printDateTime(Calendar val) {
      return DatatypeConverterImpl.CalendarFormatter.doFormat("%Y-%M-%DT%h:%m:%s%z", val);
   }

   public static String _printDate(Calendar val) {
      return DatatypeConverterImpl.CalendarFormatter.doFormat("%Y-%M-%D%z", val);
   }

   public static String _printInt(int val) {
      return String.valueOf(val);
   }

   public static String _printLong(long val) {
      return String.valueOf(val);
   }

   public static String _printDecimal(BigDecimal val) {
      return val.toPlainString();
   }

   public static String _printDouble(double v) {
      if (Double.isNaN(v)) {
         return "NaN";
      } else if (v == Double.POSITIVE_INFINITY) {
         return "INF";
      } else {
         return v == Double.NEGATIVE_INFINITY ? "-INF" : String.valueOf(v);
      }
   }

   public static String _printQName(QName val, NamespaceContext nsc) {
      String prefix = nsc.getPrefix(val.getNamespaceURI());
      String localPart = val.getLocalPart();
      String qname;
      if (prefix != null && prefix.length() != 0) {
         qname = prefix + ":" + localPart;
      } else {
         qname = localPart;
      }

      return qname;
   }

   private static byte[] initDecodeMap() {
      byte[] map = new byte[128];

      for(int i = 0; i < 128; ++i) {
         map[i] = -1;
      }

      for(int var2 = 65; var2 <= 90; ++var2) {
         map[var2] = (byte)(var2 - 65);
      }

      for(int var3 = 97; var3 <= 122; ++var3) {
         map[var3] = (byte)(var3 - 97 + 26);
      }

      for(int var4 = 48; var4 <= 57; ++var4) {
         map[var4] = (byte)(var4 - 48 + 52);
      }

      map[43] = 62;
      map[47] = 63;
      map[61] = 127;
      return map;
   }

   private static int guessLength(String text) {
      int len = text.length();

      int j;
      for(j = len - 1; j >= 0; --j) {
         byte code = decodeMap[text.charAt(j)];
         if (code != 127) {
            if (code == -1) {
               return text.length() / 4 * 3;
            }
            break;
         }
      }

      ++j;
      int padSize = len - j;
      return padSize > 2 ? text.length() / 4 * 3 : text.length() / 4 * 3 - padSize;
   }

   public static byte[] _parseBase64Binary(String text) {
      int buflen = guessLength(text);
      byte[] out = new byte[buflen];
      int o = 0;
      int len = text.length();
      byte[] quadruplet = new byte[4];
      int q = 0;

      for(int i = 0; i < len; ++i) {
         char ch = text.charAt(i);
         byte v = decodeMap[ch];
         if (v != -1) {
            quadruplet[q++] = v;
         }

         if (q == 4) {
            out[o++] = (byte)(quadruplet[0] << 2 | quadruplet[1] >> 4);
            if (quadruplet[2] != 127) {
               out[o++] = (byte)(quadruplet[1] << 4 | quadruplet[2] >> 2);
            }

            if (quadruplet[3] != 127) {
               out[o++] = (byte)(quadruplet[2] << 6 | quadruplet[3]);
            }

            q = 0;
         }
      }

      if (buflen == o) {
         return out;
      } else {
         byte[] nb = new byte[o];
         System.arraycopy(out, 0, nb, 0, o);
         return nb;
      }
   }

   private static char[] initEncodeMap() {
      char[] map = new char[64];

      for(int i = 0; i < 26; ++i) {
         map[i] = (char)(65 + i);
      }

      for(int var2 = 26; var2 < 52; ++var2) {
         map[var2] = (char)(97 + (var2 - 26));
      }

      for(int var3 = 52; var3 < 62; ++var3) {
         map[var3] = (char)(48 + (var3 - 52));
      }

      map[62] = '+';
      map[63] = '/';
      return map;
   }

   public static char encode(int i) {
      return encodeMap[i & 63];
   }

   public static byte encodeByte(int i) {
      return (byte)encodeMap[i & 63];
   }

   public static String _printBase64Binary(byte[] input) {
      return _printBase64Binary(input, 0, input.length);
   }

   public static String _printBase64Binary(byte[] input, int offset, int len) {
      char[] buf = new char[(len + 2) / 3 * 4];
      int ptr = _printBase64Binary(input, offset, len, (char[])buf, 0);

      assert ptr == buf.length;

      return new String(buf);
   }

   public static int _printBase64Binary(byte[] input, int offset, int len, char[] buf, int ptr) {
      int remaining = len;

      int i;
      for(i = offset; remaining >= 3; i += 3) {
         buf[ptr++] = encode(input[i] >> 2);
         buf[ptr++] = encode((input[i] & 3) << 4 | input[i + 1] >> 4 & 15);
         buf[ptr++] = encode((input[i + 1] & 15) << 2 | input[i + 2] >> 6 & 3);
         buf[ptr++] = encode(input[i + 2] & 63);
         remaining -= 3;
      }

      if (remaining == 1) {
         buf[ptr++] = encode(input[i] >> 2);
         buf[ptr++] = encode((input[i] & 3) << 4);
         buf[ptr++] = '=';
         buf[ptr++] = '=';
      }

      if (remaining == 2) {
         buf[ptr++] = encode(input[i] >> 2);
         buf[ptr++] = encode((input[i] & 3) << 4 | input[i + 1] >> 4 & 15);
         buf[ptr++] = encode((input[i + 1] & 15) << 2);
         buf[ptr++] = '=';
      }

      return ptr;
   }

   public static void _printBase64Binary(byte[] input, int offset, int len, XMLStreamWriter output) throws XMLStreamException {
      int remaining = len;
      char[] buf = new char[4];

      int i;
      for(i = offset; remaining >= 3; i += 3) {
         buf[0] = encode(input[i] >> 2);
         buf[1] = encode((input[i] & 3) << 4 | input[i + 1] >> 4 & 15);
         buf[2] = encode((input[i + 1] & 15) << 2 | input[i + 2] >> 6 & 3);
         buf[3] = encode(input[i + 2] & 63);
         output.writeCharacters(buf, 0, 4);
         remaining -= 3;
      }

      if (remaining == 1) {
         buf[0] = encode(input[i] >> 2);
         buf[1] = encode((input[i] & 3) << 4);
         buf[2] = '=';
         buf[3] = '=';
         output.writeCharacters(buf, 0, 4);
      }

      if (remaining == 2) {
         buf[0] = encode(input[i] >> 2);
         buf[1] = encode((input[i] & 3) << 4 | input[i + 1] >> 4 & 15);
         buf[2] = encode((input[i + 1] & 15) << 2);
         buf[3] = '=';
         output.writeCharacters(buf, 0, 4);
      }

   }

   public static int _printBase64Binary(byte[] input, int offset, int len, byte[] out, int ptr) {
      byte[] buf = out;
      int remaining = len;

      int i;
      for(i = offset; remaining >= 3; i += 3) {
         buf[ptr++] = encodeByte(input[i] >> 2);
         buf[ptr++] = encodeByte((input[i] & 3) << 4 | input[i + 1] >> 4 & 15);
         buf[ptr++] = encodeByte((input[i + 1] & 15) << 2 | input[i + 2] >> 6 & 3);
         buf[ptr++] = encodeByte(input[i + 2] & 63);
         remaining -= 3;
      }

      if (remaining == 1) {
         buf[ptr++] = encodeByte(input[i] >> 2);
         buf[ptr++] = encodeByte((input[i] & 3) << 4);
         buf[ptr++] = 61;
         buf[ptr++] = 61;
      }

      if (remaining == 2) {
         buf[ptr++] = encodeByte(input[i] >> 2);
         buf[ptr++] = encodeByte((input[i] & 3) << 4 | input[i + 1] >> 4 & 15);
         buf[ptr++] = encodeByte((input[i + 1] & 15) << 2);
         buf[ptr++] = 61;
      }

      return ptr;
   }

   private static CharSequence removeOptionalPlus(CharSequence s) {
      int len = s.length();
      if (len > 1 && s.charAt(0) == '+') {
         s = s.subSequence(1, len);
         char ch = s.charAt(0);
         if ('0' <= ch && ch <= '9') {
            return s;
         } else if ('.' == ch) {
            return s;
         } else {
            throw new NumberFormatException();
         }
      } else {
         return s;
      }
   }

   private static boolean isDigitOrPeriodOrSign(char ch) {
      if ('0' <= ch && ch <= '9') {
         return true;
      } else {
         return ch == '+' || ch == '-' || ch == '.';
      }
   }

   public static DatatypeFactory getDatatypeFactory() {
      ClassLoader tccl = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            return Thread.currentThread().getContextClassLoader();
         }
      });
      DatatypeFactory df = (DatatypeFactory)DF_CACHE.get(tccl);
      if (df == null) {
         synchronized(DatatypeConverterImpl.class) {
            df = (DatatypeFactory)DF_CACHE.get(tccl);
            if (df == null) {
               try {
                  df = DatatypeFactory.newInstance();
               } catch (DatatypeConfigurationException e) {
                  throw new Error(Messages.FAILED_TO_INITIALE_DATATYPE_FACTORY.format(), e);
               }

               DF_CACHE.put(tccl, df);
            }
         }
      }

      return df;
   }

   /** @deprecated */
   @Deprecated
   public String parseString(String lexicalXSDString) {
      return lexicalXSDString;
   }

   /** @deprecated */
   @Deprecated
   public BigInteger parseInteger(String lexicalXSDInteger) {
      return _parseInteger(lexicalXSDInteger);
   }

   /** @deprecated */
   @Deprecated
   public String printInteger(BigInteger val) {
      return _printInteger(val);
   }

   /** @deprecated */
   @Deprecated
   public int parseInt(String s) {
      return _parseInt(s);
   }

   /** @deprecated */
   @Deprecated
   public long parseLong(String lexicalXSLong) {
      return _parseLong(lexicalXSLong);
   }

   /** @deprecated */
   @Deprecated
   public short parseShort(String lexicalXSDShort) {
      return _parseShort(lexicalXSDShort);
   }

   /** @deprecated */
   @Deprecated
   public String printShort(short val) {
      return _printShort(val);
   }

   /** @deprecated */
   @Deprecated
   public BigDecimal parseDecimal(String content) {
      return _parseDecimal(content);
   }

   /** @deprecated */
   @Deprecated
   public float parseFloat(String lexicalXSDFloat) {
      return _parseFloat(lexicalXSDFloat);
   }

   /** @deprecated */
   @Deprecated
   public String printFloat(float v) {
      return _printFloat(v);
   }

   /** @deprecated */
   @Deprecated
   public double parseDouble(String lexicalXSDDouble) {
      return _parseDouble(lexicalXSDDouble);
   }

   /** @deprecated */
   @Deprecated
   public boolean parseBoolean(String lexicalXSDBoolean) {
      Boolean b = _parseBoolean(lexicalXSDBoolean);
      return b != null && b;
   }

   /** @deprecated */
   @Deprecated
   public String printBoolean(boolean val) {
      return val ? "true" : "false";
   }

   /** @deprecated */
   @Deprecated
   public byte parseByte(String lexicalXSDByte) {
      return _parseByte(lexicalXSDByte);
   }

   /** @deprecated */
   @Deprecated
   public String printByte(byte val) {
      return _printByte(val);
   }

   /** @deprecated */
   @Deprecated
   public QName parseQName(String lexicalXSDQName, NamespaceContext nsc) {
      return _parseQName(lexicalXSDQName, nsc);
   }

   /** @deprecated */
   @Deprecated
   public Calendar parseDateTime(String lexicalXSDDateTime) {
      return _parseDateTime(lexicalXSDDateTime);
   }

   /** @deprecated */
   @Deprecated
   public String printDateTime(Calendar val) {
      return _printDateTime(val);
   }

   /** @deprecated */
   @Deprecated
   public byte[] parseBase64Binary(String lexicalXSDBase64Binary) {
      return _parseBase64Binary(lexicalXSDBase64Binary);
   }

   /** @deprecated */
   @Deprecated
   public byte[] parseHexBinary(String s) {
      int len = s.length();
      if (len % 2 != 0) {
         throw new IllegalArgumentException("hexBinary needs to be even-length: " + s);
      } else {
         byte[] out = new byte[len / 2];

         for(int i = 0; i < len; i += 2) {
            int h = hexToBin(s.charAt(i));
            int l = hexToBin(s.charAt(i + 1));
            if (h == -1 || l == -1) {
               throw new IllegalArgumentException("contains illegal character for hexBinary: " + s);
            }

            out[i / 2] = (byte)(h * 16 + l);
         }

         return out;
      }
   }

   /** @deprecated */
   @Deprecated
   private static int hexToBin(char ch) {
      if ('0' <= ch && ch <= '9') {
         return ch - 48;
      } else if ('A' <= ch && ch <= 'F') {
         return ch - 65 + 10;
      } else {
         return 'a' <= ch && ch <= 'f' ? ch - 97 + 10 : -1;
      }
   }

   /** @deprecated */
   @Deprecated
   public String printHexBinary(byte[] data) {
      StringBuilder r = new StringBuilder(data.length * 2);

      for(byte b : data) {
         r.append(hexCode[b >> 4 & 15]);
         r.append(hexCode[b & 15]);
      }

      return r.toString();
   }

   /** @deprecated */
   @Deprecated
   public long parseUnsignedInt(String lexicalXSDUnsignedInt) {
      return _parseLong(lexicalXSDUnsignedInt);
   }

   /** @deprecated */
   @Deprecated
   public String printUnsignedInt(long val) {
      return _printLong(val);
   }

   /** @deprecated */
   @Deprecated
   public int parseUnsignedShort(String lexicalXSDUnsignedShort) {
      return _parseInt(lexicalXSDUnsignedShort);
   }

   /** @deprecated */
   @Deprecated
   public Calendar parseTime(String lexicalXSDTime) {
      return getDatatypeFactory().newXMLGregorianCalendar(lexicalXSDTime).toGregorianCalendar();
   }

   /** @deprecated */
   @Deprecated
   public String printTime(Calendar val) {
      return DatatypeConverterImpl.CalendarFormatter.doFormat("%h:%m:%s%z", val);
   }

   /** @deprecated */
   @Deprecated
   public Calendar parseDate(String lexicalXSDDate) {
      return getDatatypeFactory().newXMLGregorianCalendar(lexicalXSDDate).toGregorianCalendar();
   }

   /** @deprecated */
   @Deprecated
   public String printDate(Calendar val) {
      return _printDate(val);
   }

   /** @deprecated */
   @Deprecated
   public String parseAnySimpleType(String lexicalXSDAnySimpleType) {
      return lexicalXSDAnySimpleType;
   }

   /** @deprecated */
   @Deprecated
   public String printString(String val) {
      return val;
   }

   /** @deprecated */
   @Deprecated
   public String printInt(int val) {
      return _printInt(val);
   }

   /** @deprecated */
   @Deprecated
   public String printLong(long val) {
      return _printLong(val);
   }

   /** @deprecated */
   @Deprecated
   public String printDecimal(BigDecimal val) {
      return _printDecimal(val);
   }

   /** @deprecated */
   @Deprecated
   public String printDouble(double v) {
      return _printDouble(v);
   }

   /** @deprecated */
   @Deprecated
   public String printQName(QName val, NamespaceContext nsc) {
      return _printQName(val, nsc);
   }

   /** @deprecated */
   @Deprecated
   public String printBase64Binary(byte[] val) {
      return _printBase64Binary(val);
   }

   /** @deprecated */
   @Deprecated
   public String printUnsignedShort(int val) {
      return String.valueOf(val);
   }

   /** @deprecated */
   @Deprecated
   public String printAnySimpleType(String val) {
      return val;
   }

   private static final class CalendarFormatter {
      public static String doFormat(String format, Calendar cal) throws IllegalArgumentException {
         int fidx = 0;
         int flen = format.length();
         StringBuilder buf = new StringBuilder();

         while(fidx < flen) {
            char fch = format.charAt(fidx++);
            if (fch != '%') {
               buf.append(fch);
            } else {
               switch (format.charAt(fidx++)) {
                  case 'D':
                     formatDays(cal, buf);
                     break;
                  case 'M':
                     formatMonth(cal, buf);
                     break;
                  case 'Y':
                     formatYear(cal, buf);
                     break;
                  case 'h':
                     formatHours(cal, buf);
                     break;
                  case 'm':
                     formatMinutes(cal, buf);
                     break;
                  case 's':
                     formatSeconds(cal, buf);
                     break;
                  case 'z':
                     formatTimeZone(cal, buf);
                     break;
                  default:
                     throw new InternalError();
               }
            }
         }

         return buf.toString();
      }

      private static void formatYear(Calendar cal, StringBuilder buf) {
         int year = cal.get(1);
         String s;
         if (year <= 0) {
            s = Integer.toString(1 - year);
         } else {
            s = Integer.toString(year);
         }

         while(s.length() < 4) {
            s = "0" + s;
         }

         if (year <= 0) {
            s = "-" + s;
         }

         buf.append(s);
      }

      private static void formatMonth(Calendar cal, StringBuilder buf) {
         formatTwoDigits(cal.get(2) + 1, buf);
      }

      private static void formatDays(Calendar cal, StringBuilder buf) {
         formatTwoDigits(cal.get(5), buf);
      }

      private static void formatHours(Calendar cal, StringBuilder buf) {
         formatTwoDigits(cal.get(11), buf);
      }

      private static void formatMinutes(Calendar cal, StringBuilder buf) {
         formatTwoDigits(cal.get(12), buf);
      }

      private static void formatSeconds(Calendar cal, StringBuilder buf) {
         formatTwoDigits(cal.get(13), buf);
         if (cal.isSet(14)) {
            int n = cal.get(14);
            if (n != 0) {
               StringBuilder ms = new StringBuilder(Integer.toString(n));

               while(ms.length() < 3) {
                  ms.insert(0, '0');
               }

               buf.append('.');
               buf.append(ms);
            }
         }

      }

      private static void formatTimeZone(Calendar cal, StringBuilder buf) {
         TimeZone tz = cal.getTimeZone();
         if (tz != null) {
            int offset = tz.getOffset(cal.getTime().getTime());
            if (offset == 0) {
               buf.append('Z');
            } else {
               if (offset >= 0) {
                  buf.append('+');
               } else {
                  buf.append('-');
                  offset *= -1;
               }

               offset /= 60000;
               formatTwoDigits(offset / 60, buf);
               buf.append(':');
               formatTwoDigits(offset % 60, buf);
            }
         }
      }

      private static void formatTwoDigits(int n, StringBuilder buf) {
         if (n < 10) {
            buf.append('0');
         }

         buf.append(n);
      }
   }
}
