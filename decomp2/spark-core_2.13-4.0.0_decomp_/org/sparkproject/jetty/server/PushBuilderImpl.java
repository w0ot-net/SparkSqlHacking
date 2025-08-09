package org.sparkproject.jetty.server;

import jakarta.servlet.http.PushBuilder;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;

public class PushBuilderImpl implements PushBuilder {
   private static final Logger LOG = LoggerFactory.getLogger(PushBuilderImpl.class);
   private static final HttpField JETTY_PUSH = new HttpField("x-http2-push", "PushBuilder");
   private static EnumSet UNSAFE_METHODS;
   private final Request _request;
   private final HttpFields.Mutable _fields;
   private String _method;
   private String _queryString;
   private String _sessionId;
   private String _path;
   private String _lastModified;

   public PushBuilderImpl(Request request, HttpFields fields, String method, String queryString, String sessionId) {
      this._request = request;
      this._fields = HttpFields.build(fields);
      this._method = method;
      this._queryString = queryString;
      this._sessionId = sessionId;
      this._fields.add(JETTY_PUSH);
      if (LOG.isDebugEnabled()) {
         LOG.debug("PushBuilder({} {}?{} s={})", new Object[]{this._method, this._request.getRequestURI(), this._queryString, this._sessionId});
      }

   }

   public String getMethod() {
      return this._method;
   }

   public PushBuilder method(String method) {
      Objects.requireNonNull(method);
      if (!StringUtil.isBlank(method) && !UNSAFE_METHODS.contains(HttpMethod.fromString(method))) {
         this._method = method;
         return this;
      } else {
         throw new IllegalArgumentException("Method not allowed for push: " + method);
      }
   }

   public String getQueryString() {
      return this._queryString;
   }

   public PushBuilder queryString(String queryString) {
      this._queryString = queryString;
      return this;
   }

   public String getSessionId() {
      return this._sessionId;
   }

   public PushBuilder sessionId(String sessionId) {
      this._sessionId = sessionId;
      return this;
   }

   public Set getHeaderNames() {
      return this._fields.getFieldNamesCollection();
   }

   public String getHeader(String name) {
      return this._fields.get(name);
   }

   public PushBuilder setHeader(String name, String value) {
      this._fields.put(name, value);
      return this;
   }

   public PushBuilder addHeader(String name, String value) {
      this._fields.add(name, value);
      return this;
   }

   public PushBuilder removeHeader(String name) {
      this._fields.remove(name);
      return this;
   }

   public String getPath() {
      return this._path;
   }

   public PushBuilder path(String path) {
      this._path = path;
      return this;
   }

   public void push() {
      if (this._path != null && this._path.length() != 0) {
         String path = this._path;
         String query = this._queryString;
         int q = path.indexOf(63);
         if (q >= 0) {
            query = query != null && query.length() > 0 ? path.substring(q + 1) + "&" + query : path.substring(q + 1);
            path = path.substring(0, q);
         }

         if (!path.startsWith("/")) {
            path = URIUtil.addPaths(this._request.getContextPath(), path);
         }

         String param = null;
         if (this._sessionId != null && this._request.isRequestedSessionIdFromURL()) {
            param = "jsessionid=" + this._sessionId;
         }

         HttpURI uri = HttpURI.build(this._request.getHttpURI(), path, param, query).normalize();
         MetaData.Request push = new MetaData.Request(this._method, uri, this._request.getHttpVersion(), this._fields);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Push {} {} inm={} ims={}", new Object[]{this._method, uri, this._fields.get(HttpHeader.IF_NONE_MATCH), this._fields.get(HttpHeader.IF_MODIFIED_SINCE)});
         }

         this._request.getHttpChannel().getHttpTransport().push(push);
         this._path = null;
         this._lastModified = null;
      } else {
         throw new IllegalStateException("Bad Path " + this._path);
      }
   }

   static {
      UNSAFE_METHODS = EnumSet.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.CONNECT, HttpMethod.OPTIONS, HttpMethod.TRACE);
   }
}
