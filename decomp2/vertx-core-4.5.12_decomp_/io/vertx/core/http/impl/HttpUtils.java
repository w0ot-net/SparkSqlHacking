package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpClosedException;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.HostAndPort;
import io.vertx.core.net.impl.HostAndPortImpl;
import io.vertx.core.spi.tracing.TagExtractor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class HttpUtils {
   static final String H2_STREAM_BYTE_DISTRIBUTOR = "vertx.h2StreamByteDistributor";
   static final String UNIFORM_DISTRIBUTOR = "uniform";
   static final HttpClosedException CONNECTION_CLOSED_EXCEPTION = new HttpClosedException("Connection was closed");
   static final HttpClosedException STREAM_CLOSED_EXCEPTION = new HttpClosedException("Stream was closed");
   static final int SC_SWITCHING_PROTOCOLS = 101;
   static final int SC_BAD_GATEWAY = 502;
   static final TagExtractor SERVER_REQUEST_TAG_EXTRACTOR = new TagExtractor() {
      public int len(HttpServerRequest req) {
         return req.query() == null ? 4 : 5;
      }

      public String name(HttpServerRequest req, int index) {
         switch (index) {
            case 0:
               return "http.url";
            case 1:
               return "http.method";
            case 2:
               return "http.scheme";
            case 3:
               return "http.path";
            case 4:
               return "http.query";
            default:
               throw new IndexOutOfBoundsException("Invalid tag index " + index);
         }
      }

      public String value(HttpServerRequest req, int index) {
         switch (index) {
            case 0:
               return req.absoluteURI();
            case 1:
               return req.method().name();
            case 2:
               return req.scheme();
            case 3:
               return req.path();
            case 4:
               return req.query();
            default:
               throw new IndexOutOfBoundsException("Invalid tag index " + index);
         }
      }
   };
   static final TagExtractor SERVER_RESPONSE_TAG_EXTRACTOR = new TagExtractor() {
      public int len(HttpServerResponse resp) {
         return 1;
      }

      public String name(HttpServerResponse resp, int index) {
         if (index == 0) {
            return "http.status_code";
         } else {
            throw new IndexOutOfBoundsException("Invalid tag index " + index);
         }
      }

      public String value(HttpServerResponse resp, int index) {
         if (index == 0) {
            return "" + resp.getStatusCode();
         } else {
            throw new IndexOutOfBoundsException("Invalid tag index " + index);
         }
      }
   };
   static final TagExtractor CLIENT_HTTP_REQUEST_TAG_EXTRACTOR = new TagExtractor() {
      public int len(HttpRequestHead req) {
         return 2;
      }

      public String name(HttpRequestHead req, int index) {
         switch (index) {
            case 0:
               return "http.url";
            case 1:
               return "http.method";
            default:
               throw new IndexOutOfBoundsException("Invalid tag index " + index);
         }
      }

      public String value(HttpRequestHead req, int index) {
         switch (index) {
            case 0:
               return req.absoluteURI;
            case 1:
               return req.method.name();
            default:
               throw new IndexOutOfBoundsException("Invalid tag index " + index);
         }
      }
   };
   static final TagExtractor CLIENT_RESPONSE_TAG_EXTRACTOR = new TagExtractor() {
      public int len(HttpResponseHead resp) {
         return 1;
      }

      public String name(HttpResponseHead resp, int index) {
         if (index == 0) {
            return "http.status_code";
         } else {
            throw new IndexOutOfBoundsException("Invalid tag index " + index);
         }
      }

      public String value(HttpResponseHead resp, int index) {
         if (index == 0) {
            return "" + resp.statusCode;
         } else {
            throw new IndexOutOfBoundsException("Invalid tag index " + index);
         }
      }
   };
   static final StreamPriority DEFAULT_STREAM_PRIORITY = new StreamPriority() {
      public StreamPriority setWeight(short weight) {
         throw new UnsupportedOperationException("Unmodifiable stream priority");
      }

      public StreamPriority setDependency(int dependency) {
         throw new UnsupportedOperationException("Unmodifiable stream priority");
      }

      public StreamPriority setExclusive(boolean exclusive) {
         throw new UnsupportedOperationException("Unmodifiable stream priority");
      }
   };
   private static final String[] SMALL_POSITIVE_LONGS = new String[256];
   private static final AsciiString TIMEOUT_EQ = AsciiString.of("timeout=");
   private static final Consumer HEADER_VALUE_VALIDATOR = HttpUtils::validateHeaderValue;
   private static final int HIGHEST_INVALID_VALUE_CHAR_MASK = -32;
   private static final int NO_CR_LF_STATE = 0;
   private static final int CR_STATE = 1;
   private static final int LF_STATE = 2;
   private static final boolean[] VALID_H_NAME_ASCII_CHARS = new boolean[128];

   static boolean useH2UniformStreamByteDistributor() {
      return "uniform".equals(System.getProperty("vertx.h2StreamByteDistributor"));
   }

   private HttpUtils() {
   }

   private static int indexOfSlash(CharSequence str, int start) {
      for(int i = start; i < str.length(); ++i) {
         if (str.charAt(i) == '/') {
            return i;
         }
      }

      return -1;
   }

   private static boolean matches(CharSequence path, int start, String what) {
      return matches(path, start, what, false);
   }

   private static boolean matches(CharSequence path, int start, String what, boolean exact) {
      if (exact && path.length() - start != what.length()) {
         return false;
      } else if (path.length() - start >= what.length()) {
         for(int i = 0; i < what.length(); ++i) {
            if (path.charAt(start + i) != what.charAt(i)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static String positiveLongToString(long value) {
      if (value < 0L) {
         throw new IllegalArgumentException("contentLength must be >= 0");
      } else if (value >= (long)SMALL_POSITIVE_LONGS.length) {
         return Long.toString(value);
      } else {
         int index = (int)value;
         String str = SMALL_POSITIVE_LONGS[index];
         if (str == null) {
            str = Long.toString(value);
            SMALL_POSITIVE_LONGS[index] = str;
         }

         return str;
      }
   }

   public static String normalizePath(String pathname) {
      if (pathname == null) {
         return null;
      } else if (pathname.isEmpty()) {
         return "/";
      } else {
         int indexOfFirstPercent = pathname.indexOf(37);
         if (indexOfFirstPercent == -1 && pathname.indexOf(46) == -1 && pathname.indexOf("//") == -1) {
            return pathname.charAt(0) == '/' ? pathname : "/" + pathname;
         } else {
            return normalizePathSlow(pathname, indexOfFirstPercent);
         }
      }
   }

   private static String normalizePathSlow(String pathname, int indexOfFirstPercent) {
      StringBuilder ibuf;
      if (pathname.charAt(0) != '/') {
         ibuf = new StringBuilder(pathname.length() + 1);
         ibuf.append('/');
         if (indexOfFirstPercent != -1) {
            ++indexOfFirstPercent;
         }
      } else {
         ibuf = new StringBuilder(pathname.length());
      }

      ibuf.append(pathname);
      if (indexOfFirstPercent != -1) {
         decodeUnreservedChars(ibuf, indexOfFirstPercent);
      }

      return removeDots(ibuf);
   }

   private static void decodeUnreservedChars(StringBuilder path, int start) {
      for(; start < path.length(); ++start) {
         if (path.charAt(start) == '%') {
            decodeUnreserved(path, start);
         }
      }

   }

   private static void decodeUnreserved(StringBuilder path, int start) {
      if (start + 3 <= path.length()) {
         String escapeSequence = path.substring(start + 1, start + 3);

         int unescaped;
         try {
            unescaped = Integer.parseInt(escapeSequence, 16);
            if (unescaped < 0) {
               throw new IllegalArgumentException("Invalid escape sequence: %" + escapeSequence);
            }
         } catch (NumberFormatException var5) {
            throw new IllegalArgumentException("Invalid escape sequence: %" + escapeSequence);
         }

         if (unescaped >= 65 && unescaped <= 90 || unescaped >= 97 && unescaped <= 122 || unescaped >= 48 && unescaped <= 57 || unescaped == 45 || unescaped == 46 || unescaped == 95 || unescaped == 126) {
            path.setCharAt(start, (char)unescaped);
            path.delete(start + 1, start + 3);
         }

      } else {
         throw new IllegalArgumentException("Invalid position for escape character: " + start);
      }
   }

   public static String removeDots(CharSequence path) {
      if (path == null) {
         return null;
      } else {
         StringBuilder obuf = new StringBuilder(path.length());
         int i = 0;

         while(i < path.length()) {
            if (matches(path, i, "./")) {
               i += 2;
            } else if (matches(path, i, "../")) {
               i += 3;
            } else if (matches(path, i, "/./")) {
               i += 2;
            } else if (matches(path, i, "/.", true)) {
               path = "/";
               i = 0;
            } else if (matches(path, i, "/../")) {
               i += 3;
               int pos = obuf.lastIndexOf("/");
               if (pos != -1) {
                  obuf.delete(pos, obuf.length());
               }
            } else if (matches(path, i, "/..", true)) {
               path = "/";
               i = 0;
               int pos = obuf.lastIndexOf("/");
               if (pos != -1) {
                  obuf.delete(pos, obuf.length());
               }
            } else {
               if (matches(path, i, ".", true) || matches(path, i, "..", true)) {
                  break;
               }

               if (path.charAt(i) == '/') {
                  ++i;
                  if (obuf.length() == 0 || obuf.charAt(obuf.length() - 1) != '/') {
                     obuf.append('/');
                  }
               }

               int pos = indexOfSlash(path, i);
               if (pos == -1) {
                  obuf.append(path, i, path.length());
                  break;
               }

               obuf.append(path, i, pos);
               i = pos;
            }
         }

         return obuf.toString();
      }
   }

   public static URI resolveURIReference(String base, String ref) throws URISyntaxException {
      return resolveURIReference(URI.create(base), ref);
   }

   public static URI resolveURIReference(URI base, String ref) throws URISyntaxException {
      URI _ref = URI.create(ref);
      String scheme;
      String authority;
      String path;
      String query;
      if (_ref.getScheme() != null) {
         scheme = _ref.getScheme();
         authority = _ref.getAuthority();
         path = removeDots(_ref.getRawPath());
         query = _ref.getRawQuery();
      } else {
         if (_ref.getAuthority() != null) {
            authority = _ref.getAuthority();
            path = _ref.getRawPath();
            query = _ref.getRawQuery();
         } else {
            if (_ref.getRawPath().length() == 0) {
               path = base.getRawPath();
               if (_ref.getRawQuery() != null) {
                  query = _ref.getRawQuery();
               } else {
                  query = base.getRawQuery();
               }
            } else {
               if (_ref.getRawPath().startsWith("/")) {
                  path = removeDots(_ref.getRawPath());
               } else {
                  String basePath = base.getRawPath();
                  String mergedPath;
                  if (base.getAuthority() != null && basePath.length() == 0) {
                     mergedPath = "/" + _ref.getRawPath();
                  } else {
                     int index = basePath.lastIndexOf(47);
                     if (index > -1) {
                        mergedPath = basePath.substring(0, index + 1) + _ref.getRawPath();
                     } else {
                        mergedPath = _ref.getRawPath();
                     }
                  }

                  path = removeDots(mergedPath);
               }

               query = _ref.getRawQuery();
            }

            authority = base.getAuthority();
         }

         scheme = base.getScheme();
      }

      return new URI(scheme, authority, path, query, _ref.getFragment());
   }

   static String parsePath(String uri) {
      if (uri.length() == 0) {
         return "";
      } else {
         int i;
         if (uri.charAt(0) == '/') {
            i = 0;
         } else {
            i = uri.indexOf("://");
            if (i == -1) {
               i = 0;
            } else {
               i = uri.indexOf(47, i + 3);
               if (i == -1) {
                  return "/";
               }
            }
         }

         int queryStart = uri.indexOf(63, i);
         if (queryStart == -1) {
            queryStart = uri.length();
            if (i == 0) {
               return uri;
            }
         }

         return uri.substring(i, queryStart);
      }
   }

   static String parseQuery(String uri) {
      int i = uri.indexOf(63);
      return i == -1 ? null : uri.substring(i + 1);
   }

   static String absoluteURI(String serverOrigin, HttpServerRequest req) {
      String uri = req.uri();
      if ("*".equals(uri)) {
         return null;
      } else if (!uri.startsWith("https://") && !uri.startsWith("http://")) {
         boolean ssl = req.isSSL();
         HostAndPort authority = req.authority();
         String absoluteURI;
         if (authority != null) {
            StringBuilder sb = (new StringBuilder(req.scheme())).append("://").append(authority.host());
            if (authority.port() > 0 && (ssl && authority.port() != 443 || !ssl && authority.port() != 80)) {
               sb.append(':').append(authority.port());
            }

            sb.append(uri);
            absoluteURI = sb.toString();
         } else {
            absoluteURI = serverOrigin + uri;
         }

         return absoluteURI;
      } else {
         return uri;
      }
   }

   static MultiMap params(String uri, Charset charset, boolean semicolonIsNormalChar) {
      QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri, charset, true, 1024, semicolonIsNormalChar);
      Map<String, List<String>> prms = queryStringDecoder.parameters();
      MultiMap params = MultiMap.caseInsensitiveMultiMap();
      if (!prms.isEmpty()) {
         for(Map.Entry entry : prms.entrySet()) {
            params.add((String)entry.getKey(), (Iterable)entry.getValue());
         }
      }

      return params;
   }

   public static void fromVertxInitialSettings(boolean server, Http2Settings vertxSettings, io.netty.handler.codec.http2.Http2Settings nettySettings) {
      if (vertxSettings != null) {
         if (!server && !vertxSettings.isPushEnabled()) {
            nettySettings.pushEnabled(vertxSettings.isPushEnabled());
         }

         if (vertxSettings.getHeaderTableSize() != 4096L) {
            nettySettings.put('\u0001', vertxSettings.getHeaderTableSize());
         }

         if (vertxSettings.getInitialWindowSize() != 65535) {
            nettySettings.initialWindowSize(vertxSettings.getInitialWindowSize());
         }

         if (vertxSettings.getMaxConcurrentStreams() != 4294967295L) {
            nettySettings.maxConcurrentStreams(vertxSettings.getMaxConcurrentStreams());
         }

         if (vertxSettings.getMaxFrameSize() != 16384) {
            nettySettings.maxFrameSize(vertxSettings.getMaxFrameSize());
         }

         if (vertxSettings.getMaxHeaderListSize() != 8192L) {
            nettySettings.maxHeaderListSize(vertxSettings.getMaxHeaderListSize());
         }

         Map<Integer, Long> extraSettings = vertxSettings.getExtraSettings();
         if (extraSettings != null) {
            extraSettings.forEach((code, setting) -> nettySettings.put((char)code, setting));
         }
      }

   }

   public static io.netty.handler.codec.http2.Http2Settings fromVertxSettings(Http2Settings settings) {
      io.netty.handler.codec.http2.Http2Settings converted = new io.netty.handler.codec.http2.Http2Settings();
      converted.pushEnabled(settings.isPushEnabled());
      converted.maxFrameSize(settings.getMaxFrameSize());
      converted.initialWindowSize(settings.getInitialWindowSize());
      converted.headerTableSize(settings.getHeaderTableSize());
      converted.maxConcurrentStreams(settings.getMaxConcurrentStreams());
      converted.maxHeaderListSize(settings.getMaxHeaderListSize());
      if (settings.getExtraSettings() != null) {
         settings.getExtraSettings().forEach((key, value) -> converted.put((char)key, value));
      }

      return converted;
   }

   public static Http2Settings toVertxSettings(io.netty.handler.codec.http2.Http2Settings settings) {
      Http2Settings converted = new Http2Settings();
      Boolean pushEnabled = settings.pushEnabled();
      if (pushEnabled != null) {
         converted.setPushEnabled(pushEnabled);
      }

      Long maxConcurrentStreams = settings.maxConcurrentStreams();
      if (maxConcurrentStreams != null) {
         converted.setMaxConcurrentStreams(maxConcurrentStreams);
      }

      Long maxHeaderListSize = settings.maxHeaderListSize();
      if (maxHeaderListSize != null) {
         converted.setMaxHeaderListSize(maxHeaderListSize);
      }

      Integer maxFrameSize = settings.maxFrameSize();
      if (maxFrameSize != null) {
         converted.setMaxFrameSize(maxFrameSize);
      }

      Integer initialWindowSize = settings.initialWindowSize();
      if (initialWindowSize != null) {
         converted.setInitialWindowSize(initialWindowSize);
      }

      Long headerTableSize = settings.headerTableSize();
      if (headerTableSize != null) {
         converted.setHeaderTableSize(headerTableSize);
      }

      settings.forEach((key, value) -> {
         if (key > 6) {
            converted.set(key, value);
         }

      });
      return converted;
   }

   static io.netty.handler.codec.http2.Http2Settings decodeSettings(String base64Settings) {
      try {
         io.netty.handler.codec.http2.Http2Settings settings = new io.netty.handler.codec.http2.Http2Settings();
         Buffer buffer = Buffer.buffer(Base64.getUrlDecoder().decode(base64Settings));
         int pos = 0;
         int len = buffer.length();

         while(pos < len) {
            int i = buffer.getUnsignedShort(pos);
            pos += 2;
            long j = buffer.getUnsignedInt(pos);
            pos += 4;
            settings.put((char)i, j);
         }

         return settings;
      } catch (Exception var8) {
         return null;
      }
   }

   public static String encodeSettings(Http2Settings settings) {
      Buffer buffer = Buffer.buffer();
      fromVertxSettings(settings).forEach((c, l) -> {
         buffer.appendUnsignedShort(c);
         buffer.appendUnsignedInt(l);
      });
      return Base64.getUrlEncoder().encodeToString(buffer.getBytes());
   }

   public static ByteBuf generateWSCloseFrameByteBuf(short statusCode, String reason) {
      return reason != null ? Unpooled.copiedBuffer(new ByteBuf[]{Unpooled.copyShort(statusCode), Unpooled.copiedBuffer(reason, StandardCharsets.UTF_8)}) : Unpooled.copyShort(statusCode);
   }

   static void sendError(Channel ch, HttpResponseStatus status) {
      sendError(ch, status, status.reasonPhrase());
   }

   static void sendError(Channel ch, HttpResponseStatus status, CharSequence err) {
      FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
      if (status.code() == HttpResponseStatus.METHOD_NOT_ALLOWED.code()) {
         resp.headers().set(HttpHeaders.ALLOW, HttpHeaders.GET);
      }

      if (err != null) {
         resp.content().writeBytes(err.toString().getBytes(CharsetUtil.UTF_8));
         HttpUtil.setContentLength(resp, (long)err.length());
      } else {
         HttpUtil.setContentLength(resp, 0L);
      }

      ch.writeAndFlush(resp);
   }

   static String getWebSocketLocation(HttpServerRequest req, boolean ssl) throws Exception {
      String prefix;
      if (ssl) {
         prefix = "wss://";
      } else {
         prefix = "ws://";
      }

      URI uri = new URI(req.uri());
      String path = uri.getRawPath();
      String loc = prefix + req.headers().get((CharSequence)HttpHeaderNames.HOST) + path;
      String query = uri.getRawQuery();
      if (query != null) {
         loc = loc + "?" + query;
      }

      return loc;
   }

   public static CharSequence toLowerCase(CharSequence sequence) {
      return (CharSequence)(sequence instanceof AsciiString ? ((AsciiString)sequence).toLowerCase() : toCharSequenceLowerCase(sequence));
   }

   private static CharSequence toCharSequenceLowerCase(CharSequence sequence) {
      int len = sequence.length();
      StringBuilder buffer = null;

      for(int index = 0; index < len; ++index) {
         char c = sequence.charAt(index);
         if (c >= 'A' && c <= 'Z') {
            if (buffer == null) {
               if (sequence instanceof String) {
                  return toLowerCase(((String)sequence).toCharArray(), index, len);
               }

               buffer = new StringBuilder(sequence);
            }

            buffer.setCharAt(index, (char)(c + 32));
         }
      }

      if (buffer != null) {
         return buffer.toString();
      } else {
         return sequence;
      }
   }

   private static CharSequence toLowerCase(char[] chars, int firstUpperCase, int length) {
      assert chars[firstUpperCase] >= 'A' && chars[firstUpperCase] <= 'Z';

      for(int i = firstUpperCase; i < length; ++i) {
         char c = chars[i];
         if (c >= 'A' && c <= 'Z') {
            chars[i] = (char)(c + 32);
         }
      }

      return new String(chars, 0, length == chars.length ? chars.length : length);
   }

   static HttpVersion toNettyHttpVersion(io.vertx.core.http.HttpVersion version) {
      switch (version) {
         case HTTP_1_0:
            return HttpVersion.HTTP_1_0;
         case HTTP_1_1:
            return HttpVersion.HTTP_1_1;
         default:
            throw new IllegalArgumentException("Unsupported HTTP version: " + version);
      }
   }

   static HttpMethod toVertxMethod(String method) {
      return HttpMethod.valueOf(method);
   }

   public static int parseKeepAliveHeaderTimeout(CharSequence value) {
      int len = value.length();

      int next;
      for(int pos = 0; pos < len; pos = next) {
         int idx = AsciiString.indexOf(value, ',', pos);
         if (idx == -1) {
            next = len;
            idx = len;
         } else {
            next = idx + 1;
         }

         while(pos < idx && value.charAt(pos) == ' ') {
            ++pos;
         }

         int to;
         for(to = idx; to > pos && value.charAt(to - 1) == ' '; --to) {
         }

         if (AsciiString.regionMatches(value, true, pos, TIMEOUT_EQ, 0, TIMEOUT_EQ.length())) {
            pos += TIMEOUT_EQ.length();
            if (pos < to) {
               int ret;
               int ch;
               for(ret = 0; pos < to; ret = ret * 10 + (ch - 48)) {
                  ch = value.charAt(pos++);
                  if (ch < 48 || ch >= 57) {
                     ret = -1;
                     break;
                  }
               }

               if (ret > -1) {
                  return ret;
               }
            }
         }
      }

      return -1;
   }

   public static void validateHeader(CharSequence name, CharSequence value) {
      validateHeaderName(name);
      if (value != null) {
         validateHeaderValue(value);
      }

   }

   public static void validateHeader(CharSequence name, Iterable values) {
      validateHeaderName(name);
      values.forEach((value) -> {
         if (value != null) {
            HEADER_VALUE_VALIDATOR.accept(value);
         }

      });
   }

   public static void validateHeaderValue(CharSequence value) {
      if (value instanceof AsciiString) {
         validateAsciiHeaderValue((AsciiString)value);
      } else if (value instanceof String) {
         validateStringHeaderValue((String)value);
      } else {
         validateSequenceHeaderValue(value);
      }

   }

   private static void validateAsciiHeaderValue(AsciiString value) {
      int length = value.length();
      if (length != 0) {
         byte[] asciiChars = value.array();
         int off = value.arrayOffset();
         if (off == 0 && length == asciiChars.length) {
            for(int index = 0; index < asciiChars.length; ++index) {
               int latinChar = asciiChars[index] & 255;
               if (latinChar == 127) {
                  throw new IllegalArgumentException("a header value contains a prohibited character '127': " + value);
               }

               if (latinChar < 32 && latinChar != 9) {
                  validateSequenceHeaderValue(value, index - off);
                  break;
               }
            }
         } else {
            validateAsciiRangeHeaderValue(value, off, length, asciiChars);
         }

      }
   }

   private static void validateAsciiRangeHeaderValue(AsciiString value, int off, int length, byte[] asciiChars) {
      int end = off + length;

      for(int index = off; index < end; ++index) {
         int latinChar = asciiChars[index] & 255;
         if (latinChar == 127) {
            throw new IllegalArgumentException("a header value contains a prohibited character '127': " + value);
         }

         if (latinChar < 32 && latinChar != 9) {
            validateSequenceHeaderValue(value, index - off);
            break;
         }
      }

   }

   private static void validateStringHeaderValue(String value) {
      int length = value.length();
      if (length != 0) {
         for(int index = 0; index < length; ++index) {
            char latinChar = value.charAt(index);
            if (latinChar == 127) {
               throw new IllegalArgumentException("a header value contains a prohibited character '127': " + value);
            }

            if (latinChar < ' ' && latinChar != '\t') {
               validateSequenceHeaderValue(value, index);
               break;
            }
         }

      }
   }

   private static void validateSequenceHeaderValue(CharSequence value) {
      int length = value.length();
      if (length != 0) {
         for(int index = 0; index < length; ++index) {
            char latinChar = value.charAt(index);
            if (latinChar == 127) {
               throw new IllegalArgumentException("a header value contains a prohibited character '127': " + value);
            }

            if (latinChar < ' ' && latinChar != '\t') {
               validateSequenceHeaderValue(value, index);
               break;
            }
         }

      }
   }

   private static void validateSequenceHeaderValue(CharSequence seq, int index) {
      int state = validateValueChar(seq, 0, seq.charAt(index));

      for(int i = index + 1; i < seq.length(); ++i) {
         state = validateValueChar(seq, state, seq.charAt(i));
      }

      if (state != 0) {
         throw new IllegalArgumentException("a header value must not end with '\\r' or '\\n':" + seq);
      }
   }

   private static int validateValueChar(CharSequence seq, int state, char ch) {
      if (ch == 127) {
         throw new IllegalArgumentException("a header value contains a prohibited character '127': " + seq);
      } else {
         if ((ch & -32) == 0) {
            validateNonPrintableCtrlChar(seq, ch);
            if (state == 0) {
               switch (ch) {
                  case '\n':
                     return 2;
                  case '\r':
                     return 1;
                  default:
                     return 0;
               }
            }
         }

         return state != 0 ? validateCrLfChar(seq, state, ch) : 0;
      }
   }

   private static int validateCrLfChar(CharSequence seq, int state, char ch) {
      switch (state) {
         case 1:
            if (ch == '\n') {
               return 2;
            }

            throw new IllegalArgumentException("only '\\n' is allowed after '\\r': " + seq);
         case 2:
            switch (ch) {
               case '\t':
               case ' ':
                  return 0;
               default:
                  throw new IllegalArgumentException("only ' ' and '\\t' are allowed after '\\n': " + seq);
            }
         default:
            throw new AssertionError();
      }
   }

   private static void validateNonPrintableCtrlChar(CharSequence seq, int ch) {
      switch (ch) {
         case 9:
         case 10:
         case 13:
            return;
         case 11:
         case 12:
         default:
            throw new IllegalArgumentException("a header value contains a prohibited character '" + ch + "': " + seq);
      }
   }

   public static void validateHeaderName(CharSequence value) {
      if (value instanceof AsciiString) {
         validateAsciiHeaderName((AsciiString)value);
      } else if (value instanceof String) {
         validateStringHeaderName((String)value);
      } else {
         validateSequenceHeaderName(value);
      }

   }

   private static void validateAsciiHeaderName(AsciiString value) {
      int len = value.length();
      int off = value.arrayOffset();
      byte[] asciiChars = value.array();

      for(int i = 0; i < len; ++i) {
         byte c = asciiChars[off + i];
         if (c < 0) {
            throw new IllegalArgumentException("a header name cannot contain non-ASCII character: " + value);
         }

         if (!VALID_H_NAME_ASCII_CHARS[c & 127]) {
            throw new IllegalArgumentException("a header name cannot contain some prohibited characters, such as : " + value);
         }
      }

   }

   private static void validateStringHeaderName(String value) {
      for(int i = 0; i < value.length(); ++i) {
         char c = value.charAt(i);
         if (c > 127) {
            throw new IllegalArgumentException("a header name cannot contain non-ASCII character: " + value);
         }

         if (!VALID_H_NAME_ASCII_CHARS[c & 127]) {
            throw new IllegalArgumentException("a header name cannot contain some prohibited characters, such as : " + value);
         }
      }

   }

   private static void validateSequenceHeaderName(CharSequence value) {
      for(int i = 0; i < value.length(); ++i) {
         char c = value.charAt(i);
         if (c > 127) {
            throw new IllegalArgumentException("a header name cannot contain non-ASCII character: " + value);
         }

         if (!VALID_H_NAME_ASCII_CHARS[c & 127]) {
            throw new IllegalArgumentException("a header name cannot contain some prohibited characters, such as : " + value);
         }
      }

   }

   public static boolean isValidMultipartContentType(String contentType) {
      return HttpHeaderValues.MULTIPART_FORM_DATA.regionMatches(true, 0, contentType, 0, HttpHeaderValues.MULTIPART_FORM_DATA.length()) || HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.regionMatches(true, 0, contentType, 0, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.length());
   }

   public static boolean isValidMultipartMethod(io.netty.handler.codec.http.HttpMethod method) {
      return method.equals(io.netty.handler.codec.http.HttpMethod.POST) || method.equals(io.netty.handler.codec.http.HttpMethod.PUT) || method.equals(io.netty.handler.codec.http.HttpMethod.PATCH) || method.equals(io.netty.handler.codec.http.HttpMethod.DELETE);
   }

   static void resolveFile(VertxInternal vertx, String filename, long offset, long length, Handler resultHandler) {
      File file_ = vertx.resolveFile(filename);
      if (!file_.exists()) {
         resultHandler.handle(Future.failedFuture((Throwable)(new FileNotFoundException())));
      } else {
         try {
            RandomAccessFile raf = new RandomAccessFile(file_, "r");
            Throwable var9 = null;

            try {
               FileSystem fs = vertx.fileSystem();
               fs.open(filename, (new OpenOptions()).setCreate(false).setWrite(false)).transform((ar) -> {
                  if (ar.succeeded()) {
                     AsyncFile file = (AsyncFile)ar.result();
                     long contentLength = Math.min(length, file_.length() - offset);
                     if (contentLength < 0L) {
                        file.close();
                        return Future.failedFuture("offset : " + offset + " is larger than the requested file length : " + file_.length());
                     }

                     file.setReadPos(offset);
                     file.setReadLength(contentLength);
                  }

                  return (Future)ar;
               }).onComplete(resultHandler);
            } catch (Throwable var19) {
               var9 = var19;
               throw var19;
            } finally {
               if (raf != null) {
                  if (var9 != null) {
                     try {
                        raf.close();
                     } catch (Throwable var18) {
                        var9.addSuppressed(var18);
                     }
                  } else {
                     raf.close();
                  }
               }

            }
         } catch (IOException e) {
            resultHandler.handle(Future.failedFuture((Throwable)e));
         }

      }
   }

   static boolean isConnectOrUpgrade(HttpMethod method, MultiMap headers) {
      if (method == HttpMethod.CONNECT) {
         return true;
      } else {
         if (method == HttpMethod.GET) {
            for(String connection : headers.getAll(HttpHeaders.CONNECTION)) {
               if (AsciiString.containsIgnoreCase(connection, HttpHeaders.UPGRADE)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   static boolean isKeepAlive(HttpRequest request) {
      HttpVersion version = request.protocolVersion();
      return version == HttpVersion.HTTP_1_1 && !request.headers().contains(HttpHeaders.CONNECTION, HttpHeaders.CLOSE, true) || version == HttpVersion.HTTP_1_0 && request.headers().contains(HttpHeaders.CONNECTION, HttpHeaders.KEEP_ALIVE, true);
   }

   public static boolean isValidHostAuthority(String host) {
      int len = host.length();
      return HostAndPortImpl.parseHost(host, 0, len) == len;
   }

   public static boolean canUpgradeToWebSocket(HttpServerRequest req) {
      if (req.version() != io.vertx.core.http.HttpVersion.HTTP_1_1) {
         return false;
      } else if (req.method() != HttpMethod.GET) {
         return false;
      } else {
         MultiMap headers = req.headers();

         for(String connection : headers.getAll(HttpHeaders.CONNECTION)) {
            if (AsciiString.containsIgnoreCase(connection, HttpHeaders.UPGRADE)) {
               for(String upgrade : headers.getAll(HttpHeaders.UPGRADE)) {
                  if (AsciiString.containsIgnoreCase(upgrade, HttpHeaders.WEBSOCKET)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   public static HostAndPort socketAddressToHostAndPort(SocketAddress socketAddress) {
      if (socketAddress instanceof InetSocketAddress) {
         InetSocketAddress inetSocketAddress = (InetSocketAddress)socketAddress;
         return new HostAndPortImpl(inetSocketAddress.getHostString(), inetSocketAddress.getPort());
      } else {
         return null;
      }
   }

   static {
      Arrays.fill(VALID_H_NAME_ASCII_CHARS, true);
      VALID_H_NAME_ASCII_CHARS[32] = false;
      VALID_H_NAME_ASCII_CHARS[34] = false;
      VALID_H_NAME_ASCII_CHARS[40] = false;
      VALID_H_NAME_ASCII_CHARS[41] = false;
      VALID_H_NAME_ASCII_CHARS[44] = false;
      VALID_H_NAME_ASCII_CHARS[47] = false;
      VALID_H_NAME_ASCII_CHARS[58] = false;
      VALID_H_NAME_ASCII_CHARS[59] = false;
      VALID_H_NAME_ASCII_CHARS[60] = false;
      VALID_H_NAME_ASCII_CHARS[62] = false;
      VALID_H_NAME_ASCII_CHARS[61] = false;
      VALID_H_NAME_ASCII_CHARS[63] = false;
      VALID_H_NAME_ASCII_CHARS[64] = false;
      VALID_H_NAME_ASCII_CHARS[91] = false;
      VALID_H_NAME_ASCII_CHARS[93] = false;
      VALID_H_NAME_ASCII_CHARS[92] = false;
      VALID_H_NAME_ASCII_CHARS[123] = false;
      VALID_H_NAME_ASCII_CHARS[125] = false;
      VALID_H_NAME_ASCII_CHARS[127] = false;

      for(int i = 0; i < 32; ++i) {
         VALID_H_NAME_ASCII_CHARS[i] = false;
      }

   }
}
