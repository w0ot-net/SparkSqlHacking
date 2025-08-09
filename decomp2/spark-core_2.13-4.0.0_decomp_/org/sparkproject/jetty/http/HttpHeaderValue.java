package org.sparkproject.jetty.http;

import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.function.Function;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.StringUtil;

public enum HttpHeaderValue {
   CLOSE("close"),
   CHUNKED("chunked"),
   GZIP("gzip"),
   IDENTITY("identity"),
   KEEP_ALIVE("keep-alive"),
   CONTINUE("100-continue"),
   PROCESSING("102-processing"),
   TE("TE"),
   BYTES("bytes"),
   NO_CACHE("no-cache"),
   UPGRADE("Upgrade");

   public static final Index CACHE = (new Index.Builder()).caseSensitive(false).withAll(values(), HttpHeaderValue::toString).build();
   private final String _string;
   private final ByteBuffer _buffer;
   private static final EnumSet __known = EnumSet.of(HttpHeader.CONNECTION, HttpHeader.TRANSFER_ENCODING, HttpHeader.CONTENT_ENCODING);

   private HttpHeaderValue(String s) {
      this._string = s;
      this._buffer = BufferUtil.toBuffer(s);
   }

   public ByteBuffer toBuffer() {
      return this._buffer.asReadOnlyBuffer();
   }

   public boolean is(String s) {
      return this._string.equalsIgnoreCase(s);
   }

   public String asString() {
      return this._string;
   }

   public String toString() {
      return this._string;
   }

   public static boolean hasKnownValues(HttpHeader header) {
      return header == null ? false : __known.contains(header);
   }

   public static boolean parseCsvIndex(String value, Function found) {
      return parseCsvIndex(value, found, (Function)null);
   }

   public static boolean parseCsvIndex(String value, Function found, Function unknown) {
      if (StringUtil.isBlank(value)) {
         return true;
      } else {
         int next = 0;

         label67:
         while(true) {
            if (next >= value.length()) {
               return true;
            }

            HttpHeaderValue token = (HttpHeaderValue)CACHE.getBest(value, next, value.length() - next);
            if (token != null) {
               label80: {
                  int i = next + token.toString().length();

                  while(i < value.length()) {
                     switch (value.charAt(i)) {
                        case ' ':
                           ++i;
                           break;
                        case ',':
                           if (!(Boolean)found.apply(token)) {
                              return false;
                           }

                           next = i + 1;
                           continue label67;
                        default:
                           break label80;
                     }
                  }

                  return (Boolean)found.apply(token);
               }
            }

            if (' ' != value.charAt(next)) {
               int comma = value.indexOf(44, next);
               if (comma != next) {
                  if (comma <= next) {
                     break;
                  }

                  if (unknown == null) {
                     next = comma + 1;
                  } else {
                     String v = value.substring(next, comma).trim();
                     if (!StringUtil.isBlank(v) && !(Boolean)unknown.apply(v)) {
                        break;
                     }

                     next = comma + 1;
                  }
               } else {
                  ++next;
               }
            } else {
               ++next;
            }
         }

         return false;
      }
   }

   // $FF: synthetic method
   private static HttpHeaderValue[] $values() {
      return new HttpHeaderValue[]{CLOSE, CHUNKED, GZIP, IDENTITY, KEEP_ALIVE, CONTINUE, PROCESSING, TE, BYTES, NO_CACHE, UPGRADE};
   }
}
