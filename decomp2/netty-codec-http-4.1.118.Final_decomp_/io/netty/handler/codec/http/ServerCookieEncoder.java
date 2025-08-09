package io.netty.handler.codec.http;

import java.util.Collection;
import java.util.List;

/** @deprecated */
@Deprecated
public final class ServerCookieEncoder {
   /** @deprecated */
   @Deprecated
   public static String encode(String name, String value) {
      return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(name, value);
   }

   /** @deprecated */
   @Deprecated
   public static String encode(Cookie cookie) {
      return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode((io.netty.handler.codec.http.cookie.Cookie)cookie);
   }

   /** @deprecated */
   @Deprecated
   public static List encode(Cookie... cookies) {
      return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode((io.netty.handler.codec.http.cookie.Cookie[])cookies);
   }

   /** @deprecated */
   @Deprecated
   public static List encode(Collection cookies) {
      return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(cookies);
   }

   /** @deprecated */
   @Deprecated
   public static List encode(Iterable cookies) {
      return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(cookies);
   }

   private ServerCookieEncoder() {
   }
}
