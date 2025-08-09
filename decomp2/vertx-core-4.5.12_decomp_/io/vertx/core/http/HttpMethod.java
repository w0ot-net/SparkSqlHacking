package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@DataObject
public class HttpMethod {
   public static final HttpMethod OPTIONS;
   public static final HttpMethod GET;
   public static final HttpMethod HEAD;
   public static final HttpMethod POST;
   public static final HttpMethod PUT;
   public static final HttpMethod DELETE;
   public static final HttpMethod TRACE;
   public static final HttpMethod CONNECT;
   public static final HttpMethod PATCH;
   public static final HttpMethod PROPFIND;
   public static final HttpMethod PROPPATCH;
   public static final HttpMethod MKCOL;
   public static final HttpMethod COPY;
   public static final HttpMethod MOVE;
   public static final HttpMethod LOCK;
   public static final HttpMethod UNLOCK;
   public static final HttpMethod MKCALENDAR;
   public static final HttpMethod VERSION_CONTROL;
   public static final HttpMethod REPORT;
   public static final HttpMethod CHECKOUT;
   public static final HttpMethod CHECKIN;
   public static final HttpMethod UNCHECKOUT;
   public static final HttpMethod MKWORKSPACE;
   public static final HttpMethod UPDATE;
   public static final HttpMethod LABEL;
   public static final HttpMethod MERGE;
   public static final HttpMethod BASELINE_CONTROL;
   public static final HttpMethod MKACTIVITY;
   public static final HttpMethod ORDERPATCH;
   public static final HttpMethod ACL;
   public static final HttpMethod SEARCH;
   private static final List ALL;
   private final io.netty.handler.codec.http.HttpMethod nettyMethod;

   public static List values() {
      return ALL;
   }

   public static HttpMethod fromNetty(io.netty.handler.codec.http.HttpMethod method) {
      if (method == io.netty.handler.codec.http.HttpMethod.GET) {
         return GET;
      } else {
         return method == io.netty.handler.codec.http.HttpMethod.POST ? POST : _fromNetty(method);
      }
   }

   private static HttpMethod _fromNetty(io.netty.handler.codec.http.HttpMethod sMethod) {
      switch (sMethod.name()) {
         case "OPTIONS":
            return OPTIONS;
         case "HEAD":
            return HEAD;
         case "PUT":
            return PUT;
         case "DELETE":
            return DELETE;
         case "TRACE":
            return TRACE;
         case "CONNECT":
            return CONNECT;
         case "PATCH":
            return PATCH;
         case "PROPFIND":
            return PROPFIND;
         case "PROPPATCH":
            return PROPPATCH;
         case "MKCOL":
            return MKCOL;
         case "COPY":
            return COPY;
         case "MOVE":
            return MOVE;
         case "LOCK":
            return LOCK;
         case "UNLOCK":
            return UNLOCK;
         case "MKCALENDAR":
            return MKCALENDAR;
         case "VERSION-CONTROL":
            return VERSION_CONTROL;
         case "REPORT":
            return REPORT;
         case "CHECKOUT":
            return CHECKOUT;
         case "CHECKIN":
            return CHECKIN;
         case "UNCHECKOUT":
            return UNCHECKOUT;
         case "MKWORKSPACE":
            return MKWORKSPACE;
         case "UPDATE":
            return UPDATE;
         case "LABEL":
            return LABEL;
         case "MERGE":
            return MERGE;
         case "BASELINE-CONTROL":
            return BASELINE_CONTROL;
         case "MKACTIVITY":
            return MKACTIVITY;
         case "ORDERPATCH":
            return ORDERPATCH;
         case "ACL":
            return ACL;
         case "SEARCH":
            return SEARCH;
         default:
            return new HttpMethod(sMethod);
      }
   }

   public static HttpMethod valueOf(String value) {
      Objects.requireNonNull(value, "value");
      switch (value) {
         case "OPTIONS":
            return OPTIONS;
         case "GET":
            return GET;
         case "HEAD":
            return HEAD;
         case "POST":
            return POST;
         case "PUT":
            return PUT;
         case "DELETE":
            return DELETE;
         case "TRACE":
            return TRACE;
         case "CONNECT":
            return CONNECT;
         case "PATCH":
            return PATCH;
         case "PROPFIND":
            return PROPFIND;
         case "PROPPATCH":
            return PROPPATCH;
         case "MKCOL":
            return MKCOL;
         case "COPY":
            return COPY;
         case "MOVE":
            return MOVE;
         case "LOCK":
            return LOCK;
         case "UNLOCK":
            return UNLOCK;
         case "MKCALENDAR":
            return MKCALENDAR;
         case "VERSION-CONTROL":
            return VERSION_CONTROL;
         case "REPORT":
            return REPORT;
         case "CHECKOUT":
            return CHECKOUT;
         case "CHECKIN":
            return CHECKIN;
         case "UNCHECKOUT":
            return UNCHECKOUT;
         case "MKWORKSPACE":
            return MKWORKSPACE;
         case "UPDATE":
            return UPDATE;
         case "LABEL":
            return LABEL;
         case "MERGE":
            return MERGE;
         case "BASELINE-CONTROL":
            return BASELINE_CONTROL;
         case "MKACTIVITY":
            return MKACTIVITY;
         case "ORDERPATCH":
            return ORDERPATCH;
         case "ACL":
            return ACL;
         case "SEARCH":
            return SEARCH;
         default:
            return new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf(value));
      }
   }

   public JsonObject toJson() {
      throw new UnsupportedOperationException();
   }

   public HttpMethod(String name) {
      Objects.requireNonNull(name, "HTTP method name");
      this.nettyMethod = io.netty.handler.codec.http.HttpMethod.valueOf(name);
   }

   private HttpMethod(io.netty.handler.codec.http.HttpMethod nettyMethod) {
      Objects.requireNonNull(nettyMethod, "HTTP method");
      this.nettyMethod = nettyMethod;
   }

   public String name() {
      return this.nettyMethod.name();
   }

   public String toString() {
      return this.nettyMethod.toString();
   }

   public int hashCode() {
      return this.nettyMethod.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj instanceof HttpMethod) {
         HttpMethod that = (HttpMethod)obj;
         return Objects.equals(this.name(), that.name());
      } else {
         return false;
      }
   }

   public io.netty.handler.codec.http.HttpMethod toNetty() {
      return this.nettyMethod;
   }

   static {
      OPTIONS = new HttpMethod(io.netty.handler.codec.http.HttpMethod.OPTIONS);
      GET = new HttpMethod(io.netty.handler.codec.http.HttpMethod.GET);
      HEAD = new HttpMethod(io.netty.handler.codec.http.HttpMethod.HEAD);
      POST = new HttpMethod(io.netty.handler.codec.http.HttpMethod.POST);
      PUT = new HttpMethod(io.netty.handler.codec.http.HttpMethod.PUT);
      DELETE = new HttpMethod(io.netty.handler.codec.http.HttpMethod.DELETE);
      TRACE = new HttpMethod(io.netty.handler.codec.http.HttpMethod.TRACE);
      CONNECT = new HttpMethod(io.netty.handler.codec.http.HttpMethod.CONNECT);
      PATCH = new HttpMethod(io.netty.handler.codec.http.HttpMethod.PATCH);
      PROPFIND = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("PROPFIND"));
      PROPPATCH = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("PROPPATCH"));
      MKCOL = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("MKCOL"));
      COPY = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("COPY"));
      MOVE = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("MOVE"));
      LOCK = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("LOCK"));
      UNLOCK = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("UNLOCK"));
      MKCALENDAR = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("MKCALENDAR"));
      VERSION_CONTROL = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("VERSION-CONTROL"));
      REPORT = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("REPORT"));
      CHECKOUT = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("CHECKOUT"));
      CHECKIN = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("CHECKIN"));
      UNCHECKOUT = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("UNCHECKOUT"));
      MKWORKSPACE = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("MKWORKSPACE"));
      UPDATE = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("UPDATE"));
      LABEL = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("LABEL"));
      MERGE = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("MERGE"));
      BASELINE_CONTROL = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("BASELINE-CONTROL"));
      MKACTIVITY = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("MKACTIVITY"));
      ORDERPATCH = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("ORDERPATCH"));
      ACL = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("ACL"));
      SEARCH = new HttpMethod(io.netty.handler.codec.http.HttpMethod.valueOf("SEARCH"));
      ALL = Collections.unmodifiableList(Arrays.asList(OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT, PATCH, PROPFIND, PROPPATCH, MKCOL, COPY, MOVE, LOCK, UNLOCK, MKCALENDAR, VERSION_CONTROL, REPORT, CHECKIN, CHECKOUT, UNCHECKOUT, MKWORKSPACE, UPDATE, LABEL, MERGE, BASELINE_CONTROL, MKACTIVITY, ORDERPATCH, ACL, SEARCH));
   }
}
