package io.netty.handler.codec.http;

/** @deprecated */
@Deprecated
public final class ClientCookieEncoder {
   /** @deprecated */
   @Deprecated
   public static String encode(String name, String value) {
      return io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode(name, value);
   }

   /** @deprecated */
   @Deprecated
   public static String encode(Cookie cookie) {
      return io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode((io.netty.handler.codec.http.cookie.Cookie)cookie);
   }

   /** @deprecated */
   @Deprecated
   public static String encode(Cookie... cookies) {
      return io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode((io.netty.handler.codec.http.cookie.Cookie[])cookies);
   }

   /** @deprecated */
   @Deprecated
   public static String encode(Iterable cookies) {
      return io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode(cookies);
   }

   private ClientCookieEncoder() {
   }
}
