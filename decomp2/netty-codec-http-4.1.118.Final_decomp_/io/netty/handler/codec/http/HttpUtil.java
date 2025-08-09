package io.netty.handler.codec.http;

import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class HttpUtil {
   private static final AsciiString CHARSET_EQUALS;
   private static final AsciiString SEMICOLON;
   private static final String COMMA_STRING;
   private static final long TOKEN_CHARS_HIGH;
   private static final long TOKEN_CHARS_LOW;

   private HttpUtil() {
   }

   public static boolean isOriginForm(URI uri) {
      return isOriginForm(uri.toString());
   }

   public static boolean isOriginForm(String uri) {
      return uri.startsWith("/");
   }

   public static boolean isAsteriskForm(URI uri) {
      return isAsteriskForm(uri.toString());
   }

   public static boolean isAsteriskForm(String uri) {
      return "*".equals(uri);
   }

   public static boolean isKeepAlive(HttpMessage message) {
      return !message.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE, true) && (message.protocolVersion().isKeepAliveDefault() || message.headers().containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE, true));
   }

   public static void setKeepAlive(HttpMessage message, boolean keepAlive) {
      setKeepAlive(message.headers(), message.protocolVersion(), keepAlive);
   }

   public static void setKeepAlive(HttpHeaders h, HttpVersion httpVersion, boolean keepAlive) {
      if (httpVersion.isKeepAliveDefault()) {
         if (keepAlive) {
            h.remove((CharSequence)HttpHeaderNames.CONNECTION);
         } else {
            h.set((CharSequence)HttpHeaderNames.CONNECTION, (Object)HttpHeaderValues.CLOSE);
         }
      } else if (keepAlive) {
         h.set((CharSequence)HttpHeaderNames.CONNECTION, (Object)HttpHeaderValues.KEEP_ALIVE);
      } else {
         h.remove((CharSequence)HttpHeaderNames.CONNECTION);
      }

   }

   public static long getContentLength(HttpMessage message) {
      String value = message.headers().get((CharSequence)HttpHeaderNames.CONTENT_LENGTH);
      if (value != null) {
         return Long.parseLong(value);
      } else {
         long webSocketContentLength = (long)getWebSocketContentLength(message);
         if (webSocketContentLength >= 0L) {
            return webSocketContentLength;
         } else {
            throw new NumberFormatException("header not found: " + HttpHeaderNames.CONTENT_LENGTH);
         }
      }
   }

   public static long getContentLength(HttpMessage message, long defaultValue) {
      String value = message.headers().get((CharSequence)HttpHeaderNames.CONTENT_LENGTH);
      if (value != null) {
         return Long.parseLong(value);
      } else {
         long webSocketContentLength = (long)getWebSocketContentLength(message);
         return webSocketContentLength >= 0L ? webSocketContentLength : defaultValue;
      }
   }

   public static int getContentLength(HttpMessage message, int defaultValue) {
      return (int)Math.min(2147483647L, getContentLength(message, (long)defaultValue));
   }

   static int getWebSocketContentLength(HttpMessage message) {
      HttpHeaders h = message.headers();
      if (message instanceof HttpRequest) {
         HttpRequest req = (HttpRequest)message;
         if (HttpMethod.GET.equals(req.method()) && h.contains((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_KEY1) && h.contains((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_KEY2)) {
            return 8;
         }
      } else if (message instanceof HttpResponse) {
         HttpResponse res = (HttpResponse)message;
         if (res.status().code() == 101 && h.contains((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_ORIGIN) && h.contains((CharSequence)HttpHeaderNames.SEC_WEBSOCKET_LOCATION)) {
            return 16;
         }
      }

      return -1;
   }

   public static void setContentLength(HttpMessage message, long length) {
      message.headers().set((CharSequence)HttpHeaderNames.CONTENT_LENGTH, (Object)length);
   }

   public static boolean isContentLengthSet(HttpMessage m) {
      return m.headers().contains((CharSequence)HttpHeaderNames.CONTENT_LENGTH);
   }

   public static boolean is100ContinueExpected(HttpMessage message) {
      return isExpectHeaderValid(message) && message.headers().contains((CharSequence)HttpHeaderNames.EXPECT, (CharSequence)HttpHeaderValues.CONTINUE, true);
   }

   static boolean isUnsupportedExpectation(HttpMessage message) {
      if (!isExpectHeaderValid(message)) {
         return false;
      } else {
         String expectValue = message.headers().get((CharSequence)HttpHeaderNames.EXPECT);
         return expectValue != null && !HttpHeaderValues.CONTINUE.toString().equalsIgnoreCase(expectValue);
      }
   }

   private static boolean isExpectHeaderValid(HttpMessage message) {
      return message instanceof HttpRequest && message.protocolVersion().compareTo(HttpVersion.HTTP_1_1) >= 0;
   }

   public static void set100ContinueExpected(HttpMessage message, boolean expected) {
      if (expected) {
         message.headers().set((CharSequence)HttpHeaderNames.EXPECT, (Object)HttpHeaderValues.CONTINUE);
      } else {
         message.headers().remove((CharSequence)HttpHeaderNames.EXPECT);
      }

   }

   public static boolean isTransferEncodingChunked(HttpMessage message) {
      return message.headers().containsValue(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED, true);
   }

   public static void setTransferEncodingChunked(HttpMessage m, boolean chunked) {
      if (chunked) {
         m.headers().set((CharSequence)HttpHeaderNames.TRANSFER_ENCODING, (Object)HttpHeaderValues.CHUNKED);
         m.headers().remove((CharSequence)HttpHeaderNames.CONTENT_LENGTH);
      } else {
         List<String> encodings = m.headers().getAll((CharSequence)HttpHeaderNames.TRANSFER_ENCODING);
         if (encodings.isEmpty()) {
            return;
         }

         List<CharSequence> values = new ArrayList(encodings);
         Iterator<CharSequence> valuesIt = values.iterator();

         while(valuesIt.hasNext()) {
            CharSequence value = (CharSequence)valuesIt.next();
            if (HttpHeaderValues.CHUNKED.contentEqualsIgnoreCase(value)) {
               valuesIt.remove();
            }
         }

         if (values.isEmpty()) {
            m.headers().remove((CharSequence)HttpHeaderNames.TRANSFER_ENCODING);
         } else {
            m.headers().set((CharSequence)HttpHeaderNames.TRANSFER_ENCODING, (Iterable)values);
         }
      }

   }

   public static Charset getCharset(HttpMessage message) {
      return getCharset(message, CharsetUtil.ISO_8859_1);
   }

   public static Charset getCharset(CharSequence contentTypeValue) {
      return contentTypeValue != null ? getCharset(contentTypeValue, CharsetUtil.ISO_8859_1) : CharsetUtil.ISO_8859_1;
   }

   public static Charset getCharset(HttpMessage message, Charset defaultCharset) {
      CharSequence contentTypeValue = message.headers().get((CharSequence)HttpHeaderNames.CONTENT_TYPE);
      return contentTypeValue != null ? getCharset(contentTypeValue, defaultCharset) : defaultCharset;
   }

   public static Charset getCharset(CharSequence contentTypeValue, Charset defaultCharset) {
      if (contentTypeValue != null) {
         CharSequence charsetRaw = getCharsetAsSequence(contentTypeValue);
         if (charsetRaw != null) {
            if (charsetRaw.length() > 2 && charsetRaw.charAt(0) == '"' && charsetRaw.charAt(charsetRaw.length() - 1) == '"') {
               charsetRaw = charsetRaw.subSequence(1, charsetRaw.length() - 1);
            }

            try {
               return Charset.forName(charsetRaw.toString());
            } catch (IllegalCharsetNameException var4) {
            } catch (UnsupportedCharsetException var5) {
            }
         }
      }

      return defaultCharset;
   }

   /** @deprecated */
   @Deprecated
   public static CharSequence getCharsetAsString(HttpMessage message) {
      return getCharsetAsSequence(message);
   }

   public static CharSequence getCharsetAsSequence(HttpMessage message) {
      CharSequence contentTypeValue = message.headers().get((CharSequence)HttpHeaderNames.CONTENT_TYPE);
      return contentTypeValue != null ? getCharsetAsSequence(contentTypeValue) : null;
   }

   public static CharSequence getCharsetAsSequence(CharSequence contentTypeValue) {
      ObjectUtil.checkNotNull(contentTypeValue, "contentTypeValue");
      int indexOfCharset = AsciiString.indexOfIgnoreCaseAscii(contentTypeValue, CHARSET_EQUALS, 0);
      if (indexOfCharset == -1) {
         return null;
      } else {
         int indexOfEncoding = indexOfCharset + CHARSET_EQUALS.length();
         if (indexOfEncoding < contentTypeValue.length()) {
            CharSequence charsetCandidate = contentTypeValue.subSequence(indexOfEncoding, contentTypeValue.length());
            int indexOfSemicolon = AsciiString.indexOfIgnoreCaseAscii(charsetCandidate, SEMICOLON, 0);
            return indexOfSemicolon == -1 ? charsetCandidate : charsetCandidate.subSequence(0, indexOfSemicolon);
         } else {
            return null;
         }
      }
   }

   public static CharSequence getMimeType(HttpMessage message) {
      CharSequence contentTypeValue = message.headers().get((CharSequence)HttpHeaderNames.CONTENT_TYPE);
      return contentTypeValue != null ? getMimeType(contentTypeValue) : null;
   }

   public static CharSequence getMimeType(CharSequence contentTypeValue) {
      ObjectUtil.checkNotNull(contentTypeValue, "contentTypeValue");
      int indexOfSemicolon = AsciiString.indexOfIgnoreCaseAscii(contentTypeValue, SEMICOLON, 0);
      if (indexOfSemicolon != -1) {
         return contentTypeValue.subSequence(0, indexOfSemicolon);
      } else {
         return contentTypeValue.length() > 0 ? contentTypeValue : null;
      }
   }

   public static String formatHostnameForHttp(InetSocketAddress addr) {
      String hostString = NetUtil.getHostname(addr);
      if (NetUtil.isValidIpV6Address(hostString)) {
         if (!addr.isUnresolved()) {
            hostString = NetUtil.toAddressString(addr.getAddress());
         } else if (hostString.charAt(0) == '[' && hostString.charAt(hostString.length() - 1) == ']') {
            return hostString;
         }

         return '[' + hostString + ']';
      } else {
         return hostString;
      }
   }

   public static long normalizeAndGetContentLength(List contentLengthFields, boolean isHttp10OrEarlier, boolean allowDuplicateContentLengths) {
      if (contentLengthFields.isEmpty()) {
         return -1L;
      } else {
         String firstField = ((CharSequence)contentLengthFields.get(0)).toString();
         boolean multipleContentLengths = contentLengthFields.size() > 1 || firstField.indexOf(44) >= 0;
         if (multipleContentLengths && !isHttp10OrEarlier) {
            if (!allowDuplicateContentLengths) {
               throw new IllegalArgumentException("Multiple Content-Length values found: " + contentLengthFields);
            }

            String firstValue = null;

            for(CharSequence field : contentLengthFields) {
               String[] tokens = field.toString().split(COMMA_STRING, -1);

               for(String token : tokens) {
                  String trimmed = token.trim();
                  if (firstValue == null) {
                     firstValue = trimmed;
                  } else if (!trimmed.equals(firstValue)) {
                     throw new IllegalArgumentException("Multiple Content-Length values found: " + contentLengthFields);
                  }
               }
            }

            firstField = firstValue;
         }

         if (!firstField.isEmpty() && Character.isDigit(firstField.charAt(0))) {
            try {
               long value = Long.parseLong(firstField);
               return ObjectUtil.checkPositiveOrZero(value, "Content-Length value");
            } catch (NumberFormatException e) {
               throw new IllegalArgumentException("Content-Length value is not a number: " + firstField, e);
            }
         } else {
            throw new IllegalArgumentException("Content-Length value is not a number: " + firstField);
         }
      }
   }

   static int validateToken(CharSequence token) {
      return token instanceof AsciiString ? validateAsciiStringToken((AsciiString)token) : validateCharSequenceToken(token);
   }

   private static int validateAsciiStringToken(AsciiString token) {
      byte[] array = token.array();
      int i = token.arrayOffset();

      for(int len = token.arrayOffset() + token.length(); i < len; ++i) {
         if (!HttpUtil.BitSet128.contains(array[i], TOKEN_CHARS_HIGH, TOKEN_CHARS_LOW)) {
            return i - token.arrayOffset();
         }
      }

      return -1;
   }

   private static int validateCharSequenceToken(CharSequence token) {
      int i = 0;

      for(int len = token.length(); i < len; ++i) {
         byte value = (byte)token.charAt(i);
         if (!HttpUtil.BitSet128.contains(value, TOKEN_CHARS_HIGH, TOKEN_CHARS_LOW)) {
            return i;
         }
      }

      return -1;
   }

   static {
      CHARSET_EQUALS = AsciiString.of(HttpHeaderValues.CHARSET + "=");
      SEMICOLON = AsciiString.cached(";");
      COMMA_STRING = String.valueOf(',');
      BitSet128 tokenChars = (new BitSet128()).range('0', '9').range('a', 'z').range('A', 'Z').bits('-', '.', '_', '~').bits('!', '#', '$', '%', '&', '\'', '*', '+', '^', '`', '|');
      TOKEN_CHARS_HIGH = tokenChars.high();
      TOKEN_CHARS_LOW = tokenChars.low();
   }

   private static final class BitSet128 {
      private long high;
      private long low;

      private BitSet128() {
      }

      BitSet128 range(char fromInc, char toInc) {
         for(int bit = fromInc; bit <= toInc; ++bit) {
            if (bit < 64) {
               this.low |= 1L << bit;
            } else {
               this.high |= 1L << bit - 64;
            }
         }

         return this;
      }

      BitSet128 bits(char... bits) {
         for(char bit : bits) {
            if (bit < '@') {
               this.low |= 1L << bit;
            } else {
               this.high |= 1L << bit - 64;
            }
         }

         return this;
      }

      long high() {
         return this.high;
      }

      long low() {
         return this.low;
      }

      static boolean contains(byte bit, long high, long low) {
         if (bit < 0) {
            return false;
         } else if (bit < 64) {
            return 0L != (low & 1L << bit);
         } else {
            return 0L != (high & 1L << bit - 64);
         }
      }
   }
}
