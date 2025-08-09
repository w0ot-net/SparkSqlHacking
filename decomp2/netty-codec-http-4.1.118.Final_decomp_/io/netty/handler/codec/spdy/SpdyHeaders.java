package io.netty.handler.codec.spdy;

import io.netty.handler.codec.Headers;
import io.netty.util.AsciiString;
import java.util.Iterator;
import java.util.List;

public interface SpdyHeaders extends Headers {
   String getAsString(CharSequence var1);

   List getAllAsString(CharSequence var1);

   Iterator iteratorAsString();

   boolean contains(CharSequence var1, CharSequence var2, boolean var3);

   public static final class HttpNames {
      public static final AsciiString HOST = AsciiString.cached(":host");
      public static final AsciiString METHOD = AsciiString.cached(":method");
      public static final AsciiString PATH = AsciiString.cached(":path");
      public static final AsciiString SCHEME = AsciiString.cached(":scheme");
      public static final AsciiString STATUS = AsciiString.cached(":status");
      public static final AsciiString VERSION = AsciiString.cached(":version");

      private HttpNames() {
      }
   }
}
