package io.vertx.core.http;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http2.Http2Headers.PseudoHeaderName;
import io.netty.util.AsciiString;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.MultiMap;
import io.vertx.core.http.impl.headers.HeadersMultiMap;

@VertxGen
public interface HttpHeaders {
   /** @deprecated */
   @Deprecated
   String DISABLE_HTTP_HEADERS_VALIDATION_PROP_NAME = "vertx.disableHttpHeadersValidation";
   boolean DISABLE_HTTP_HEADERS_VALIDATION = Boolean.getBoolean("vertx.disableHttpHeadersValidation");
   @GenIgnore({"permitted-type"})
   CharSequence ACCEPT = HttpHeaderNames.ACCEPT;
   @GenIgnore({"permitted-type"})
   CharSequence ACCEPT_CHARSET = HttpHeaderNames.ACCEPT_CHARSET;
   @GenIgnore({"permitted-type"})
   CharSequence ACCEPT_ENCODING = HttpHeaderNames.ACCEPT_ENCODING;
   @GenIgnore({"permitted-type"})
   CharSequence ACCEPT_LANGUAGE = HttpHeaderNames.ACCEPT_LANGUAGE;
   @GenIgnore({"permitted-type"})
   CharSequence ACCEPT_RANGES = HttpHeaderNames.ACCEPT_RANGES;
   @GenIgnore({"permitted-type"})
   CharSequence ACCEPT_PATCH = HttpHeaderNames.ACCEPT_PATCH;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_ALLOW_CREDENTIALS = HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_ALLOW_HEADERS = HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_ALLOW_METHODS = HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_ALLOW_ORIGIN = HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK = HttpHeaderNames.ACCESS_CONTROL_ALLOW_PRIVATE_NETWORK;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_EXPOSE_HEADERS = HttpHeaderNames.ACCESS_CONTROL_EXPOSE_HEADERS;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_MAX_AGE = HttpHeaderNames.ACCESS_CONTROL_MAX_AGE;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_REQUEST_HEADERS = HttpHeaderNames.ACCESS_CONTROL_REQUEST_HEADERS;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_REQUEST_METHOD = HttpHeaderNames.ACCESS_CONTROL_REQUEST_METHOD;
   @GenIgnore({"permitted-type"})
   CharSequence ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK = HttpHeaderNames.ACCESS_CONTROL_REQUEST_PRIVATE_NETWORK;
   @GenIgnore({"permitted-type"})
   CharSequence AGE = HttpHeaderNames.AGE;
   @GenIgnore({"permitted-type"})
   CharSequence ALLOW = HttpHeaderNames.ALLOW;
   @GenIgnore({"permitted-type"})
   CharSequence AUTHORIZATION = HttpHeaderNames.AUTHORIZATION;
   @GenIgnore({"permitted-type"})
   CharSequence CACHE_CONTROL = HttpHeaderNames.CACHE_CONTROL;
   @GenIgnore({"permitted-type"})
   CharSequence CONNECTION = HttpHeaderNames.CONNECTION;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_BASE = HttpHeaderNames.CONTENT_BASE;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_DISPOSITION = HttpHeaderNames.CONTENT_DISPOSITION;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_ENCODING = HttpHeaderNames.CONTENT_ENCODING;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_LANGUAGE = HttpHeaderNames.CONTENT_LANGUAGE;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_LENGTH = HttpHeaderNames.CONTENT_LENGTH;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_LOCATION = HttpHeaderNames.CONTENT_LOCATION;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_TRANSFER_ENCODING = HttpHeaderNames.CONTENT_TRANSFER_ENCODING;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_MD5 = HttpHeaderNames.CONTENT_MD5;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_RANGE = HttpHeaderNames.CONTENT_RANGE;
   @GenIgnore({"permitted-type"})
   CharSequence CONTENT_TYPE = HttpHeaderNames.CONTENT_TYPE;
   @GenIgnore({"permitted-type"})
   CharSequence COOKIE = HttpHeaderNames.COOKIE;
   @GenIgnore({"permitted-type"})
   CharSequence DATE = HttpHeaderNames.DATE;
   @GenIgnore({"permitted-type"})
   CharSequence ETAG = HttpHeaderNames.ETAG;
   @GenIgnore({"permitted-type"})
   CharSequence EXPECT = HttpHeaderNames.EXPECT;
   @GenIgnore({"permitted-type"})
   CharSequence EXPIRES = HttpHeaderNames.EXPIRES;
   @GenIgnore({"permitted-type"})
   CharSequence FROM = HttpHeaderNames.FROM;
   @GenIgnore({"permitted-type"})
   CharSequence HOST = HttpHeaderNames.HOST;
   @GenIgnore({"permitted-type"})
   CharSequence IF_MATCH = HttpHeaderNames.IF_MATCH;
   @GenIgnore({"permitted-type"})
   CharSequence IF_MODIFIED_SINCE = HttpHeaderNames.IF_MODIFIED_SINCE;
   @GenIgnore({"permitted-type"})
   CharSequence IF_NONE_MATCH = HttpHeaderNames.IF_NONE_MATCH;
   @GenIgnore({"permitted-type"})
   CharSequence LAST_MODIFIED = HttpHeaderNames.LAST_MODIFIED;
   @GenIgnore({"permitted-type"})
   CharSequence LOCATION = HttpHeaderNames.LOCATION;
   @GenIgnore({"permitted-type"})
   CharSequence ORIGIN = HttpHeaderNames.ORIGIN;
   @GenIgnore({"permitted-type"})
   CharSequence PROXY_AUTHENTICATE = HttpHeaderNames.PROXY_AUTHENTICATE;
   @GenIgnore({"permitted-type"})
   CharSequence PROXY_AUTHORIZATION = HttpHeaderNames.PROXY_AUTHORIZATION;
   @GenIgnore({"permitted-type"})
   CharSequence REFERER = HttpHeaderNames.REFERER;
   @GenIgnore({"permitted-type"})
   CharSequence RETRY_AFTER = HttpHeaderNames.RETRY_AFTER;
   @GenIgnore({"permitted-type"})
   CharSequence SERVER = HttpHeaderNames.SERVER;
   @GenIgnore({"permitted-type"})
   CharSequence TRANSFER_ENCODING = HttpHeaderNames.TRANSFER_ENCODING;
   @GenIgnore({"permitted-type"})
   CharSequence USER_AGENT = HttpHeaderNames.USER_AGENT;
   @GenIgnore({"permitted-type"})
   CharSequence SET_COOKIE = HttpHeaderNames.SET_COOKIE;
   @GenIgnore({"permitted-type"})
   CharSequence APPLICATION_X_WWW_FORM_URLENCODED = HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED;
   @GenIgnore({"permitted-type"})
   CharSequence CHUNKED = HttpHeaderValues.CHUNKED;
   @GenIgnore({"permitted-type"})
   CharSequence CLOSE = HttpHeaderValues.CLOSE;
   @GenIgnore({"permitted-type"})
   CharSequence CONTINUE = HttpHeaderValues.CONTINUE;
   @GenIgnore({"permitted-type"})
   CharSequence IDENTITY = HttpHeaderValues.IDENTITY;
   @GenIgnore({"permitted-type"})
   CharSequence KEEP_ALIVE = HttpHeaderValues.KEEP_ALIVE;
   @GenIgnore({"permitted-type"})
   CharSequence UPGRADE = HttpHeaderValues.UPGRADE;
   @GenIgnore({"permitted-type"})
   CharSequence WEBSOCKET = HttpHeaderValues.WEBSOCKET;
   @GenIgnore({"permitted-type"})
   CharSequence DEFLATE_GZIP = createOptimized("deflate, gzip");
   @GenIgnore({"permitted-type"})
   CharSequence DEFLATE_GZIP_BR = createOptimized("deflate, gzip, br");
   @GenIgnore({"permitted-type"})
   CharSequence TEXT_HTML = createOptimized("text/html");
   @GenIgnore({"permitted-type"})
   CharSequence GET = createOptimized("GET");
   @GenIgnore({"permitted-type"})
   CharSequence VARY = createOptimized("vary");
   @GenIgnore({"permitted-type"})
   CharSequence PSEUDO_PATH = PseudoHeaderName.PATH.value();
   @GenIgnore({"permitted-type"})
   CharSequence PSEUDO_AUTHORITY = PseudoHeaderName.AUTHORITY.value();
   @GenIgnore({"permitted-type"})
   CharSequence PSEUDO_SCHEME = PseudoHeaderName.SCHEME.value();
   @GenIgnore({"permitted-type"})
   CharSequence PSEUDO_STATUS = PseudoHeaderName.STATUS.value();
   @GenIgnore({"permitted-type"})
   CharSequence PSEUDO_METHOD = PseudoHeaderName.METHOD.value();

   @GenIgnore({"permitted-type"})
   static CharSequence createOptimized(String value) {
      return new AsciiString(value);
   }

   static MultiMap headers() {
      return HeadersMultiMap.httpHeaders();
   }

   static MultiMap set(String name, String value) {
      return HeadersMultiMap.httpHeaders().set(name, value);
   }

   @GenIgnore({"permitted-type"})
   static MultiMap set(CharSequence name, CharSequence value) {
      return HeadersMultiMap.httpHeaders().set(name, value);
   }
}
