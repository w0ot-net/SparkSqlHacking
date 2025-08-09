package org.sparkproject.jetty.http;

import java.nio.ByteBuffer;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.StringUtil;

public enum HttpHeader {
   CONNECTION("Connection"),
   CACHE_CONTROL("Cache-Control"),
   DATE("Date"),
   PRAGMA("Pragma"),
   PROXY_CONNECTION("Proxy-Connection"),
   TRAILER("Trailer"),
   TRANSFER_ENCODING("Transfer-Encoding"),
   UPGRADE("Upgrade"),
   VIA("Via"),
   WARNING("Warning"),
   NEGOTIATE("Negotiate"),
   ALLOW("Allow"),
   CONTENT_ENCODING("Content-Encoding"),
   CONTENT_LANGUAGE("Content-Language"),
   CONTENT_LENGTH("Content-Length"),
   CONTENT_LOCATION("Content-Location"),
   CONTENT_MD5("Content-MD5"),
   CONTENT_RANGE("Content-Range"),
   CONTENT_TYPE("Content-Type"),
   EXPIRES("Expires"),
   LAST_MODIFIED("Last-Modified"),
   ACCEPT("Accept"),
   ACCEPT_CHARSET("Accept-Charset"),
   ACCEPT_ENCODING("Accept-Encoding"),
   ACCEPT_LANGUAGE("Accept-Language"),
   AUTHORIZATION("Authorization"),
   EXPECT("Expect"),
   FORWARDED("Forwarded"),
   FROM("From"),
   HOST("Host"),
   IF_MATCH("If-Match"),
   IF_MODIFIED_SINCE("If-Modified-Since"),
   IF_NONE_MATCH("If-None-Match"),
   IF_RANGE("If-Range"),
   IF_UNMODIFIED_SINCE("If-Unmodified-Since"),
   KEEP_ALIVE("Keep-Alive"),
   MAX_FORWARDS("Max-Forwards"),
   PROXY_AUTHORIZATION("Proxy-Authorization"),
   RANGE("Range"),
   REQUEST_RANGE("Request-Range"),
   REFERER("Referer"),
   TE("TE"),
   USER_AGENT("User-Agent"),
   X_FORWARDED_FOR("X-Forwarded-For"),
   X_FORWARDED_PORT("X-Forwarded-Port"),
   X_FORWARDED_PROTO("X-Forwarded-Proto"),
   X_FORWARDED_SERVER("X-Forwarded-Server"),
   X_FORWARDED_HOST("X-Forwarded-Host"),
   ACCEPT_RANGES("Accept-Ranges"),
   AGE("Age"),
   ALT_SVC("Alt-Svc"),
   ETAG("ETag"),
   LINK("Link"),
   LOCATION("Location"),
   PROXY_AUTHENTICATE("Proxy-Authenticate"),
   RETRY_AFTER("Retry-After"),
   SERVER("Server"),
   SERVLET_ENGINE("Servlet-Engine"),
   VARY("Vary"),
   WWW_AUTHENTICATE("WWW-Authenticate"),
   ORIGIN("Origin"),
   SEC_WEBSOCKET_KEY("Sec-WebSocket-Key"),
   SEC_WEBSOCKET_VERSION("Sec-WebSocket-Version"),
   SEC_WEBSOCKET_EXTENSIONS("Sec-WebSocket-Extensions"),
   SEC_WEBSOCKET_SUBPROTOCOL("Sec-WebSocket-Protocol"),
   SEC_WEBSOCKET_ACCEPT("Sec-WebSocket-Accept"),
   COOKIE("Cookie"),
   SET_COOKIE("Set-Cookie"),
   SET_COOKIE2("Set-Cookie2"),
   MIME_VERSION("MIME-Version"),
   IDENTITY("identity"),
   X_POWERED_BY("X-Powered-By"),
   HTTP2_SETTINGS("HTTP2-Settings"),
   STRICT_TRANSPORT_SECURITY("Strict-Transport-Security"),
   C_METHOD(":method", true),
   C_SCHEME(":scheme", true),
   C_AUTHORITY(":authority", true),
   C_PATH(":path", true),
   C_STATUS(":status", true),
   C_PROTOCOL(":protocol");

   public static final Index CACHE = (new Index.Builder()).caseSensitive(false).withAll(values(), HttpHeader::toString).build();
   private final String _string;
   private final String _lowerCase;
   private final byte[] _bytes;
   private final byte[] _bytesColonSpace;
   private final ByteBuffer _buffer;
   private final boolean _pseudo;

   private HttpHeader(String s) {
      this(s, false);
   }

   private HttpHeader(String s, boolean pseudo) {
      this._string = s;
      this._lowerCase = StringUtil.asciiToLowerCase(s);
      this._bytes = StringUtil.getBytes(s);
      this._bytesColonSpace = StringUtil.getBytes(s + ": ");
      this._buffer = ByteBuffer.wrap(this._bytes);
      this._pseudo = pseudo;
   }

   public String lowerCaseName() {
      return this._lowerCase;
   }

   public ByteBuffer toBuffer() {
      return this._buffer.asReadOnlyBuffer();
   }

   public byte[] getBytes() {
      return this._bytes;
   }

   public byte[] getBytesColonSpace() {
      return this._bytesColonSpace;
   }

   public boolean is(String s) {
      return this._string.equalsIgnoreCase(s);
   }

   public boolean isPseudo() {
      return this._pseudo;
   }

   public String asString() {
      return this._string;
   }

   public String toString() {
      return this._string;
   }

   // $FF: synthetic method
   private static HttpHeader[] $values() {
      return new HttpHeader[]{CONNECTION, CACHE_CONTROL, DATE, PRAGMA, PROXY_CONNECTION, TRAILER, TRANSFER_ENCODING, UPGRADE, VIA, WARNING, NEGOTIATE, ALLOW, CONTENT_ENCODING, CONTENT_LANGUAGE, CONTENT_LENGTH, CONTENT_LOCATION, CONTENT_MD5, CONTENT_RANGE, CONTENT_TYPE, EXPIRES, LAST_MODIFIED, ACCEPT, ACCEPT_CHARSET, ACCEPT_ENCODING, ACCEPT_LANGUAGE, AUTHORIZATION, EXPECT, FORWARDED, FROM, HOST, IF_MATCH, IF_MODIFIED_SINCE, IF_NONE_MATCH, IF_RANGE, IF_UNMODIFIED_SINCE, KEEP_ALIVE, MAX_FORWARDS, PROXY_AUTHORIZATION, RANGE, REQUEST_RANGE, REFERER, TE, USER_AGENT, X_FORWARDED_FOR, X_FORWARDED_PORT, X_FORWARDED_PROTO, X_FORWARDED_SERVER, X_FORWARDED_HOST, ACCEPT_RANGES, AGE, ALT_SVC, ETAG, LINK, LOCATION, PROXY_AUTHENTICATE, RETRY_AFTER, SERVER, SERVLET_ENGINE, VARY, WWW_AUTHENTICATE, ORIGIN, SEC_WEBSOCKET_KEY, SEC_WEBSOCKET_VERSION, SEC_WEBSOCKET_EXTENSIONS, SEC_WEBSOCKET_SUBPROTOCOL, SEC_WEBSOCKET_ACCEPT, COOKIE, SET_COOKIE, SET_COOKIE2, MIME_VERSION, IDENTITY, X_POWERED_BY, HTTP2_SETTINGS, STRICT_TRANSPORT_SECURITY, C_METHOD, C_SCHEME, C_AUTHORITY, C_PATH, C_STATUS, C_PROTOCOL};
   }
}
