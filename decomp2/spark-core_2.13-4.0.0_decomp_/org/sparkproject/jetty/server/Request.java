package org.sparkproject.jetty.server;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestWrapper;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpUpgradeHandler;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.PushBuilder;
import jakarta.servlet.http.WebConnection;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.ComplianceViolation;
import org.sparkproject.jetty.http.HostPortHttpField;
import org.sparkproject.jetty.http.HttpCompliance;
import org.sparkproject.jetty.http.HttpCookie;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.UriCompliance;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.RuntimeIOException;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.session.Session;
import org.sparkproject.jetty.server.session.SessionHandler;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.AttributesMap;
import org.sparkproject.jetty.util.HostPort;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.MultiMap;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.UrlEncoded;

public class Request implements HttpServletRequest {
   public static final String __MULTIPART_CONFIG_ELEMENT = "org.sparkproject.jetty.multipartConfig";
   private static final Logger LOG = LoggerFactory.getLogger(Request.class);
   private static final Collection __defaultLocale = Collections.singleton(Locale.getDefault());
   private static final int INPUT_NONE = 0;
   private static final int INPUT_STREAM = 1;
   private static final int INPUT_READER = 2;
   private static final MultiMap NO_PARAMS = new MultiMap();
   private static final MultiMap BAD_PARAMS = new MultiMap();
   private final HttpChannel _channel;
   private final List _requestAttributeListeners = new ArrayList();
   private final HttpInput _input;
   private MetaData.Request _metaData;
   private HttpFields _httpFields;
   private HttpFields _trailers;
   private HttpURI _uri;
   private String _method;
   private String _pathInContext;
   private ServletPathMapping _servletPathMapping;
   private Object _asyncNotSupportedSource = null;
   private boolean _secure;
   private boolean _newContext;
   private boolean _cookiesExtracted = false;
   private boolean _handled = false;
   private boolean _contentParamsExtracted;
   private boolean _requestedSessionIdFromCookie = false;
   private Attributes _attributes;
   private Authentication _authentication;
   private String _contentType;
   private String _characterEncoding;
   private ContextHandler.Context _context;
   private ContextHandler.Context _errorContext;
   private Cookies _cookies;
   private DispatcherType _dispatcherType;
   private int _inputState = 0;
   private BufferedReader _reader;
   private String _readerEncoding;
   private MultiMap _queryParameters;
   private MultiMap _contentParameters;
   private MultiMap _parameters;
   private Charset _queryEncoding;
   private InetSocketAddress _remote;
   private String _requestedSessionId;
   private UserIdentity.Scope _scope;
   private HttpSession _session;
   private SessionHandler _sessionHandler;
   private long _timeStamp;
   private MultiParts _multiParts;
   private AsyncContextState _async;
   private List _sessions;
   private static final EnumSet NOT_PUSHED_HEADERS;

   private static boolean isNoParams(MultiMap inputParameters) {
      boolean isNoParams = inputParameters == NO_PARAMS;
      return isNoParams;
   }

   public static Request getBaseRequest(ServletRequest request) {
      if (request instanceof Request) {
         return (Request)request;
      } else {
         Object channel = request.getAttribute(HttpChannel.class.getName());
         if (channel instanceof HttpChannel) {
            return ((HttpChannel)channel).getRequest();
         } else {
            while(request instanceof ServletRequestWrapper) {
               request = ((ServletRequestWrapper)request).getRequest();
            }

            return request instanceof Request ? (Request)request : null;
         }
      }
   }

   public Request(HttpChannel channel, HttpInput input) {
      this._channel = channel;
      this._input = input;
   }

   public HttpFields getHttpFields() {
      return this._httpFields;
   }

   public void setHttpFields(HttpFields fields) {
      this._httpFields = fields.asImmutable();
   }

   public Map getTrailerFields() {
      HttpFields trailersFields = this.getTrailerHttpFields();
      if (trailersFields == null) {
         return Collections.emptyMap();
      } else {
         Map<String, String> trailers = new HashMap();

         for(HttpField field : trailersFields) {
            String key = field.getName().toLowerCase();
            String value = (String)trailers.get(key);
            trailers.put(key, value == null ? field.getValue() : value + "," + field.getValue());
         }

         return trailers;
      }
   }

   public void setTrailerHttpFields(HttpFields trailers) {
      this._trailers = trailers == null ? null : trailers.asImmutable();
   }

   public HttpFields getTrailerHttpFields() {
      return this._trailers;
   }

   public HttpInput getHttpInput() {
      return this._input;
   }

   public boolean isPush() {
      return Boolean.TRUE.equals(this.getAttribute("org.sparkproject.jetty.pushed"));
   }

   public boolean isPushSupported() {
      return !this.isPush() && this.getHttpChannel().getHttpTransport().isPushSupported();
   }

   public PushBuilder newPushBuilder() {
      if (!this.isPushSupported()) {
         return null;
      } else {
         HttpFields.Mutable fields = HttpFields.build(this.getHttpFields(), NOT_PUSHED_HEADERS);
         HttpField authField = this.getHttpFields().getField(HttpHeader.AUTHORIZATION);
         if (authField != null && this.getUserPrincipal() != null && authField.getValue().startsWith("Basic")) {
            fields.add(authField);
         }

         String id;
         try {
            HttpSession session = this.getSession();
            if (session != null) {
               session.getLastAccessedTime();
               id = session.getId();
            } else {
               id = this.getRequestedSessionId();
            }
         } catch (IllegalStateException var11) {
            id = this.getRequestedSessionId();
         }

         Map<String, String> cookies = new HashMap();
         Cookie[] existingCookies = this.getCookies();
         if (existingCookies != null) {
            for(Cookie c : this.getCookies()) {
               cookies.put(c.getName(), c.getValue());
            }
         }

         for(HttpField field : this.getResponse().getHttpFields()) {
            HttpHeader header = field.getHeader();
            if (header == HttpHeader.SET_COOKIE) {
               HttpCookie cookie = field instanceof HttpCookie.SetCookieHttpField ? ((HttpCookie.SetCookieHttpField)field).getHttpCookie() : new HttpCookie(field.getValue());
               if (cookie.getMaxAge() > 0L) {
                  cookies.put(cookie.getName(), cookie.getValue());
               } else {
                  cookies.remove(cookie.getName());
               }
            }
         }

         if (!cookies.isEmpty()) {
            StringBuilder buff = new StringBuilder();

            for(Map.Entry entry : cookies.entrySet()) {
               if (buff.length() > 0) {
                  buff.append("; ");
               }

               buff.append((String)entry.getKey()).append('=').append((String)entry.getValue());
            }

            fields.add(new HttpField(HttpHeader.COOKIE, buff.toString()));
         }

         PushBuilder builder = new PushBuilderImpl(this, fields, this.getMethod(), this.getQueryString(), id);
         builder.addHeader("referer", this.getRequestURL().toString());
         return builder;
      }
   }

   public void addEventListener(EventListener listener) {
      if (listener instanceof ServletRequestAttributeListener) {
         this._requestAttributeListeners.add((ServletRequestAttributeListener)listener);
      }

      if (listener instanceof AsyncListener) {
         throw new IllegalArgumentException(listener.getClass().toString());
      }
   }

   public void enterSession(HttpSession s) {
      if (s instanceof Session) {
         if (this._sessions == null) {
            this._sessions = new ArrayList();
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("Request {} entering session={}", this, s);
         }

         this._sessions.add((Session)s);
      }
   }

   private void leaveSession(Session session) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Request {} leaving session {}", this, session);
      }

      ServletContext ctx = session.getServletContext();
      ContextHandler handler = ContextHandler.getContextHandler(ctx);
      if (handler == null) {
         session.getSessionHandler().complete(session);
      } else {
         handler.handle(this, () -> session.getSessionHandler().complete(session));
      }

   }

   private void commitSession(Session session) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Response {} committing for session {}", this, session);
      }

      ServletContext ctx = session.getServletContext();
      ContextHandler handler = ContextHandler.getContextHandler(ctx);
      if (handler == null) {
         session.getSessionHandler().commit(session);
      } else {
         handler.handle(this, () -> session.getSessionHandler().commit(session));
      }

   }

   private MultiMap getParameters() {
      if (!this._contentParamsExtracted) {
         this._contentParamsExtracted = true;
         if (this._contentParameters == null) {
            try {
               this.extractContentParameters();
            } catch (IllegalArgumentException | IllegalStateException e) {
               LOG.warn(((RuntimeException)e).toString());
               throw new BadMessageException("Unable to parse form content", e);
            }
         }
      }

      if (this._queryParameters == null) {
         this.extractQueryParameters();
      }

      if (!isNoParams(this._queryParameters) && this._queryParameters.size() != 0) {
         if (!isNoParams(this._contentParameters) && this._contentParameters.size() != 0) {
            if (this._parameters == null) {
               this._parameters = new MultiMap();
               this._parameters.addAllValues(this._queryParameters);
               this._parameters.addAllValues(this._contentParameters);
            }
         } else {
            this._parameters = this._queryParameters;
         }
      } else {
         this._parameters = this._contentParameters;
      }

      MultiMap<String> parameters = this._parameters;
      return parameters == null ? NO_PARAMS : parameters;
   }

   private void extractQueryParameters() {
      if (this._uri != null && !StringUtil.isEmpty(this._uri.getQuery())) {
         try {
            this._queryParameters = new MultiMap();
            UrlEncoded.decodeTo(this._uri.getQuery(), this._queryParameters, this._queryEncoding);
         } catch (IllegalArgumentException | IllegalStateException e) {
            this._queryParameters = BAD_PARAMS;
            throw new BadMessageException("Unable to parse URI query", e);
         }
      } else {
         this._queryParameters = NO_PARAMS;
      }

   }

   private boolean isContentEncodingSupported() {
      String contentEncoding = this.getHttpFields().get(HttpHeader.CONTENT_ENCODING);
      return contentEncoding == null ? true : HttpHeaderValue.IDENTITY.is(contentEncoding);
   }

   private void extractContentParameters() {
      String contentType = this.getContentType();
      if (contentType != null && !contentType.isEmpty()) {
         this._contentParameters = new MultiMap();
         int contentLength = this.getContentLength();
         if (contentLength != 0 && this._inputState == 0) {
            String baseType = HttpField.valueParameters(contentType, (Map)null);
            if (MimeTypes.Type.FORM_ENCODED.is(baseType) && this._channel.getHttpConfiguration().isFormEncodedMethod(this.getMethod())) {
               if (this._metaData != null && !this.isContentEncodingSupported()) {
                  throw new BadMessageException(415, "Unsupported Content-Encoding");
               }

               this.extractFormParameters(this._contentParameters);
            } else if (MimeTypes.Type.MULTIPART_FORM_DATA.is(baseType) && this.getAttribute("org.sparkproject.jetty.multipartConfig") != null && this._multiParts == null) {
               try {
                  if (this._metaData != null && !this.isContentEncodingSupported()) {
                     throw new BadMessageException(415, "Unsupported Content-Encoding");
                  }

                  this.getParts(this._contentParameters);
               } catch (IOException var6) {
                  String msg = "Unable to extract content parameters";
                  if (LOG.isDebugEnabled()) {
                     LOG.debug(msg, var6);
                  }

                  throw new RuntimeIOException(msg, var6);
               }
            }
         }
      } else {
         this._contentParameters = NO_PARAMS;
      }

   }

   public void extractFormParameters(MultiMap params) {
      try {
         int maxFormContentSize = 200000;
         int maxFormKeys = 1000;
         if (this._context != null) {
            ContextHandler contextHandler = this._context.getContextHandler();
            maxFormContentSize = contextHandler.getMaxFormContentSize();
            maxFormKeys = contextHandler.getMaxFormKeys();
         } else {
            maxFormContentSize = this.lookupServerAttribute("org.sparkproject.jetty.server.Request.maxFormContentSize", maxFormContentSize);
            maxFormKeys = this.lookupServerAttribute("org.sparkproject.jetty.server.Request.maxFormKeys", maxFormKeys);
         }

         int contentLength = this.getContentLength();
         if (maxFormContentSize >= 0 && contentLength > maxFormContentSize) {
            throw new IllegalStateException("Form is larger than max length " + maxFormContentSize);
         } else {
            InputStream in = this.getInputStream();
            if (this._input.isAsync()) {
               throw new IllegalStateException("Cannot extract parameters with async IO");
            } else {
               UrlEncoded.decodeTo(in, params, this.getCharacterEncoding(), maxFormContentSize, maxFormKeys);
            }
         }
      } catch (IOException var6) {
         String msg = "Unable to extract form parameters";
         if (LOG.isDebugEnabled()) {
            LOG.debug(msg, var6);
         }

         throw new RuntimeIOException(msg, var6);
      }
   }

   private int lookupServerAttribute(String key, int dftValue) {
      Object attribute = this._channel.getServer().getAttribute(key);
      if (attribute instanceof Number) {
         return ((Number)attribute).intValue();
      } else {
         return attribute instanceof String ? Integer.parseInt((String)attribute) : dftValue;
      }
   }

   public AsyncContext getAsyncContext() {
      HttpChannelState state = this.getHttpChannelState();
      if (this._async != null && state.isAsyncStarted()) {
         return this._async;
      } else {
         throw new IllegalStateException(state.getStatusString());
      }
   }

   public HttpChannelState getHttpChannelState() {
      return this._channel.getState();
   }

   public ComplianceViolation.Listener getComplianceViolationListener() {
      if (this._channel instanceof ComplianceViolation.Listener) {
         return (ComplianceViolation.Listener)this._channel;
      } else {
         ComplianceViolation.Listener listener = (ComplianceViolation.Listener)this._channel.getConnector().getBean(ComplianceViolation.Listener.class);
         if (listener == null) {
            listener = (ComplianceViolation.Listener)this._channel.getServer().getBean(ComplianceViolation.Listener.class);
         }

         return listener;
      }
   }

   public Object getAttribute(String name) {
      if (name.startsWith("org.sparkproject.jetty")) {
         if (Server.class.getName().equals(name)) {
            return this._channel.getServer();
         }

         if (HttpChannel.class.getName().equals(name)) {
            return this._channel;
         }

         if (HttpConnection.class.getName().equals(name) && this._channel.getHttpTransport() instanceof HttpConnection) {
            return this._channel.getHttpTransport();
         }
      }

      return this._attributes == null ? null : this._attributes.getAttribute(name);
   }

   public Enumeration getAttributeNames() {
      return this._attributes == null ? Collections.enumeration(Collections.emptyList()) : AttributesMap.getAttributeNamesCopy(this._attributes);
   }

   public Attributes getAttributes() {
      if (this._attributes == null) {
         this._attributes = new ServletAttributes();
      }

      return this._attributes;
   }

   public Authentication getAuthentication() {
      return this._authentication;
   }

   public String getAuthType() {
      if (this._authentication instanceof Authentication.Deferred) {
         this.setAuthentication(((Authentication.Deferred)this._authentication).authenticate(this));
      }

      return this._authentication instanceof Authentication.User ? ((Authentication.User)this._authentication).getAuthMethod() : null;
   }

   public String getCharacterEncoding() {
      if (this._characterEncoding == null) {
         if (this._context != null) {
            this._characterEncoding = this._context.getRequestCharacterEncoding();
         }

         if (this._characterEncoding == null) {
            String contentType = this.getContentType();
            if (contentType != null) {
               MimeTypes.Type mime = (MimeTypes.Type)MimeTypes.CACHE.get(contentType);
               String charset = mime != null && mime.getCharset() != null ? mime.getCharset().toString() : MimeTypes.getCharsetFromContentType(contentType);
               if (charset != null) {
                  this._characterEncoding = charset;
               }
            }
         }
      }

      return this._characterEncoding;
   }

   public HttpChannel getHttpChannel() {
      return this._channel;
   }

   public int getContentLength() {
      long contentLength = this.getContentLengthLong();
      return contentLength > 2147483647L ? -1 : (int)contentLength;
   }

   public long getContentLengthLong() {
      return this._httpFields == null ? -1L : this._httpFields.getLongField(HttpHeader.CONTENT_LENGTH);
   }

   public long getContentRead() {
      return this._input.getContentReceived();
   }

   public String getContentType() {
      if (this._contentType == null) {
         MetaData.Request metadata = this._metaData;
         this._contentType = metadata == null ? null : metadata.getFields().get(HttpHeader.CONTENT_TYPE);
      }

      return this._contentType;
   }

   public ContextHandler.Context getContext() {
      return this._context;
   }

   public ContextHandler.Context getErrorContext() {
      if (this.isAsyncStarted()) {
         ContextHandler handler = this._channel.getState().getContextHandler();
         if (handler != null) {
            return handler.getServletContext();
         }
      }

      return this._errorContext;
   }

   public String getContextPath() {
      ContextHandler.Context context;
      if (this._dispatcherType == DispatcherType.INCLUDE) {
         Dispatcher.IncludeAttributes include = (Dispatcher.IncludeAttributes)Attributes.unwrap(this._attributes, Dispatcher.IncludeAttributes.class);
         context = include == null ? this._context : include.getSourceContext();
      } else {
         context = this._context;
      }

      return context == null ? null : context.getContextHandler().getRequestContextPath();
   }

   public String getPathInContext() {
      return this._pathInContext;
   }

   public Cookie[] getCookies() {
      MetaData.Request metadata = this._metaData;
      if (metadata != null && !this._cookiesExtracted) {
         this._cookiesExtracted = true;

         for(HttpField field : metadata.getFields()) {
            if (field.getHeader() == HttpHeader.COOKIE) {
               if (this._cookies == null) {
                  this._cookies = new Cookies(this.getHttpChannel().getHttpConfiguration().getRequestCookieCompliance(), this.getComplianceViolationListener());
               }

               this._cookies.addCookieField(field.getValue());
            }
         }

         if (this._cookies != null && this._cookies.getCookies().length != 0) {
            return this._cookies.getCookies();
         } else {
            return null;
         }
      } else {
         return this._cookies != null && this._cookies.getCookies().length != 0 ? this._cookies.getCookies() : null;
      }
   }

   public long getDateHeader(String name) {
      HttpFields fields = this._httpFields;
      return fields == null ? -1L : fields.getDateField(name);
   }

   public DispatcherType getDispatcherType() {
      return this._dispatcherType;
   }

   public String getHeader(String name) {
      HttpFields fields = this._httpFields;
      return fields == null ? null : fields.get(name);
   }

   public Enumeration getHeaderNames() {
      HttpFields fields = this._httpFields;
      return fields == null ? Collections.emptyEnumeration() : fields.getFieldNames();
   }

   public Enumeration getHeaders(String name) {
      HttpFields fields = this._httpFields;
      if (fields == null) {
         return Collections.emptyEnumeration();
      } else {
         Enumeration<String> e = fields.getValues(name);
         return e == null ? Collections.enumeration(Collections.emptyList()) : e;
      }
   }

   public int getInputState() {
      return this._inputState;
   }

   public ServletInputStream getInputStream() throws IOException {
      if (this._inputState != 0 && this._inputState != 1) {
         throw new IllegalStateException("READER");
      } else {
         this._inputState = 1;
         if (this._channel.isExpecting100Continue()) {
            this._channel.continue100(this._input.available());
         }

         return this._input;
      }
   }

   public int getIntHeader(String name) {
      HttpFields fields = this._httpFields;
      return fields == null ? -1 : (int)fields.getLongField(name);
   }

   public Locale getLocale() {
      HttpFields fields = this._httpFields;
      if (fields == null) {
         return Locale.getDefault();
      } else {
         List<String> acceptable = fields.getQualityCSV(HttpHeader.ACCEPT_LANGUAGE);
         if (acceptable.isEmpty()) {
            return Locale.getDefault();
         } else {
            String language = (String)acceptable.get(0);
            language = HttpField.stripParameters(language);
            String country = "";
            int dash = language.indexOf(45);
            if (dash > -1) {
               country = language.substring(dash + 1).trim();
               language = language.substring(0, dash).trim();
            }

            return new Locale(language, country);
         }
      }
   }

   public Enumeration getLocales() {
      HttpFields fields = this._httpFields;
      if (fields == null) {
         return Collections.enumeration(__defaultLocale);
      } else {
         List<String> acceptable = fields.getQualityCSV(HttpHeader.ACCEPT_LANGUAGE);
         if (acceptable.isEmpty()) {
            return Collections.enumeration(__defaultLocale);
         } else {
            List<Locale> locales = (List)acceptable.stream().map((language) -> {
               language = HttpField.stripParameters(language);
               String country = "";
               int dash = language.indexOf(45);
               if (dash > -1) {
                  country = language.substring(dash + 1).trim();
                  language = language.substring(0, dash).trim();
               }

               return new Locale(language, country);
            }).collect(Collectors.toList());
            return Collections.enumeration(locales);
         }
      }
   }

   public String getLocalAddr() {
      if (this._channel != null) {
         InetSocketAddress local = this._channel.getLocalAddress();
         if (local == null) {
            return "";
         } else {
            InetAddress address = local.getAddress();
            String result = address == null ? local.getHostString() : address.getHostAddress();
            return this.formatAddrOrHost(result);
         }
      } else {
         return "";
      }
   }

   public String getLocalName() {
      if (this._channel != null) {
         String localName = this._channel.getLocalName();
         return this.formatAddrOrHost(localName);
      } else {
         return "";
      }
   }

   public int getLocalPort() {
      if (this._channel != null) {
         int localPort = this._channel.getLocalPort();
         if (localPort > 0) {
            return localPort;
         }
      }

      return 0;
   }

   public String getMethod() {
      return this._method;
   }

   public String getParameter(String name) {
      return (String)this.getParameters().getValue(name, 0);
   }

   public Map getParameterMap() {
      return Collections.unmodifiableMap(this.getParameters().toStringArrayMap());
   }

   public Enumeration getParameterNames() {
      return Collections.enumeration(this.getParameters().keySet());
   }

   public String[] getParameterValues(String name) {
      List<String> vals = this.getParameters().getValues(name);
      return vals == null ? null : (String[])vals.toArray(new String[0]);
   }

   public MultiMap getQueryParameters() {
      return this._queryParameters;
   }

   public void setQueryParameters(MultiMap queryParameters) {
      this._queryParameters = queryParameters;
   }

   public void setContentParameters(MultiMap contentParameters) {
      this._contentParameters = contentParameters;
   }

   public void resetParameters() {
      this._parameters = null;
   }

   public String getPathInfo() {
      ServletPathMapping mapping = this.findServletPathMapping();
      return mapping == null ? this._pathInContext : mapping.getPathInfo();
   }

   public String getPathTranslated() {
      String pathInfo = this.getPathInfo();
      return pathInfo != null && this._context != null ? this._context.getRealPath(pathInfo) : null;
   }

   public String getProtocol() {
      MetaData.Request metadata = this._metaData;
      if (metadata == null) {
         return null;
      } else {
         HttpVersion version = metadata.getHttpVersion();
         return version == null ? null : version.toString();
      }
   }

   public HttpVersion getHttpVersion() {
      MetaData.Request metadata = this._metaData;
      return metadata == null ? null : metadata.getHttpVersion();
   }

   public String getQueryEncoding() {
      return this._queryEncoding == null ? null : this._queryEncoding.name();
   }

   Charset getQueryCharset() {
      return this._queryEncoding;
   }

   public String getQueryString() {
      return this._uri == null ? null : this._uri.getQuery();
   }

   public BufferedReader getReader() throws IOException {
      if (this._inputState != 0 && this._inputState != 2) {
         throw new IllegalStateException("STREAMED");
      } else if (this._inputState == 2) {
         return this._reader;
      } else {
         String encoding = this.getCharacterEncoding();
         if (encoding == null) {
            encoding = "iso-8859-1";
         }

         if (this._reader != null && encoding.equalsIgnoreCase(this._readerEncoding)) {
            if (this._channel.isExpecting100Continue()) {
               this._channel.continue100(this._input.available());
            }
         } else {
            final ServletInputStream in = this.getInputStream();
            this._readerEncoding = encoding;
            this._reader = new BufferedReader(new InputStreamReader(in, encoding)) {
               public void close() throws IOException {
                  in.close();
               }
            };
         }

         this._inputState = 2;
         return this._reader;
      }
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public String getRealPath(String path) {
      return this._context == null ? null : this._context.getRealPath(path);
   }

   public InetSocketAddress getRemoteInetSocketAddress() {
      InetSocketAddress remote = this._remote;
      if (remote == null) {
         remote = this._channel.getRemoteAddress();
      }

      return remote;
   }

   public String getRemoteAddr() {
      InetSocketAddress remote = this._remote;
      if (remote == null) {
         remote = this._channel.getRemoteAddress();
      }

      if (remote == null) {
         return "";
      } else {
         InetAddress address = remote.getAddress();
         String result = address == null ? remote.getHostString() : address.getHostAddress();
         return this.formatAddrOrHost(result);
      }
   }

   public String getRemoteHost() {
      InetSocketAddress remote = this._remote;
      if (remote == null) {
         remote = this._channel.getRemoteAddress();
      }

      return remote == null ? "" : this.formatAddrOrHost(remote.getHostString());
   }

   public int getRemotePort() {
      InetSocketAddress remote = this._remote;
      if (remote == null) {
         remote = this._channel.getRemoteAddress();
      }

      return remote == null ? 0 : remote.getPort();
   }

   public String getRemoteUser() {
      Principal p = this.getUserPrincipal();
      return p == null ? null : p.getName();
   }

   public RequestDispatcher getRequestDispatcher(String path) {
      if (path != null && this._context != null) {
         if (!path.startsWith("/")) {
            String relTo = this._pathInContext;
            int slash = relTo.lastIndexOf("/");
            if (slash > 1) {
               relTo = relTo.substring(0, slash + 1);
            } else {
               relTo = "/";
            }

            path = URIUtil.addPaths(relTo, path);
         }

         return this._context.getRequestDispatcher(path);
      } else {
         return null;
      }
   }

   public String getRequestedSessionId() {
      return this._requestedSessionId;
   }

   public String getRequestURI() {
      return this._uri == null ? null : this._uri.getPath();
   }

   public StringBuffer getRequestURL() {
      StringBuffer url = new StringBuffer(128);
      URIUtil.appendSchemeHostPort(url, this.getScheme(), this.getServerName(), this.getServerPort());
      String path = this.getRequestURI();
      if (path != null) {
         url.append(path);
      }

      return url;
   }

   public Response getResponse() {
      return this._channel.getResponse();
   }

   public StringBuilder getRootURL() {
      StringBuilder url = new StringBuilder(128);
      URIUtil.appendSchemeHostPort(url, this.getScheme(), this.getServerName(), this.getServerPort());
      return url;
   }

   public String getScheme() {
      return this._uri == null ? "http" : this._uri.getScheme();
   }

   public String getServerName() {
      return this._uri != null && StringUtil.isNotBlank(this._uri.getAuthority()) ? this.formatAddrOrHost(this._uri.getHost()) : this.findServerName();
   }

   private String findServerName() {
      if (this._channel != null) {
         HostPort serverAuthority = this._channel.getServerAuthority();
         if (serverAuthority != null) {
            return this.formatAddrOrHost(serverAuthority.getHost());
         }
      }

      String name = this.getLocalName();
      return name != null ? this.formatAddrOrHost(name) : "";
   }

   public int getServerPort() {
      int port = -1;
      if (this._uri != null && StringUtil.isNotBlank(this._uri.getAuthority())) {
         port = this._uri.getPort();
      } else {
         port = this.findServerPort();
      }

      return port <= 0 ? HttpScheme.getDefaultPort(this.getScheme()) : port;
   }

   private int findServerPort() {
      if (this._channel != null) {
         HostPort serverAuthority = this._channel.getServerAuthority();
         if (serverAuthority != null) {
            return serverAuthority.getPort();
         }
      }

      return this.getLocalPort();
   }

   public ServletContext getServletContext() {
      return this._context;
   }

   public String getServletName() {
      return this._scope != null ? this._scope.getName() : null;
   }

   public String getServletPath() {
      ServletPathMapping mapping = this.findServletPathMapping();
      return mapping == null ? "" : mapping.getServletPath();
   }

   public ServletResponse getServletResponse() {
      return this._channel.getResponse();
   }

   public String changeSessionId() {
      HttpSession session = this.getSession(false);
      if (session == null) {
         throw new IllegalStateException("No session");
      } else {
         if (session instanceof Session) {
            Session s = (Session)session;
            s.renewId(this);
            if (this.getRemoteUser() != null) {
               s.setAttribute("org.sparkproject.jetty.security.sessionCreatedSecure", Boolean.TRUE);
            }

            if (s.isIdChanged() && this._sessionHandler.isUsingCookies()) {
               this._channel.getResponse().replaceCookie(this._sessionHandler.getSessionCookie(s, this.getContextPath(), this.isSecure()));
            }
         }

         return session.getId();
      }
   }

   public void onCompleted() {
      HttpChannel httpChannel = this.getHttpChannel();
      if (httpChannel != null) {
         RequestLog requestLog = httpChannel.getRequestLog();
         if (requestLog != null) {
            this._contentParamsExtracted = true;
            if (this._contentParameters == null) {
               this._contentParameters = NO_PARAMS;
            }

            MetaData.Response committedResponse = this.getResponse().getCommittedMetaData();
            if (committedResponse != null) {
               this.getResponse().setStatus(committedResponse.getStatus());
            }

            requestLog.log(this, this.getResponse());
         }
      }

      if (this._sessions != null) {
         for(Session s : this._sessions) {
            this.leaveSession(s);
         }
      }

      if (this._multiParts != null) {
         try {
            this._multiParts.close();
         } catch (Throwable e) {
            LOG.warn("Errors deleting multipart tmp files", e);
         }
      }

   }

   public void onResponseCommit() {
      if (this._sessions != null) {
         for(Session s : this._sessions) {
            this.commitSession(s);
         }
      }

   }

   public HttpSession getSession(SessionHandler sessionHandler) {
      if (this._sessions != null && this._sessions.size() != 0 && sessionHandler != null) {
         HttpSession session = null;

         for(HttpSession s : this._sessions) {
            Session ss = (Session)s;
            if (sessionHandler == ss.getSessionHandler()) {
               session = s;
               if (ss.isValid()) {
                  return s;
               }
            }
         }

         return session;
      } else {
         return null;
      }
   }

   public HttpSession getSession() {
      return this.getSession(true);
   }

   public HttpSession getSession(boolean create) {
      if (this._session != null) {
         if (this._sessionHandler == null || this._sessionHandler.isValid(this._session)) {
            return this._session;
         }

         this._session = null;
      }

      if (!create) {
         return null;
      } else if (this.getResponse().isCommitted()) {
         throw new IllegalStateException("Response is committed");
      } else if (this._sessionHandler == null) {
         throw new IllegalStateException("No SessionManager");
      } else {
         this._session = this._sessionHandler.newHttpSession(this);
         if (this._session == null) {
            throw new IllegalStateException("Create session failed");
         } else {
            HttpCookie cookie = this._sessionHandler.getSessionCookie(this._session, this.getContextPath(), this.isSecure());
            if (cookie != null) {
               this._channel.getResponse().replaceCookie(cookie);
            }

            return this._session;
         }
      }
   }

   public SessionHandler getSessionHandler() {
      return this._sessionHandler;
   }

   public long getTimeStamp() {
      return this._timeStamp;
   }

   public HttpURI getHttpURI() {
      return this._uri;
   }

   public void setHttpURI(HttpURI uri) {
      if (this._uri != null && !Objects.equals(this._uri.getQuery(), uri.getQuery()) && this._queryParameters != BAD_PARAMS) {
         this._parameters = this._queryParameters = null;
      }

      this._uri = uri.asImmutable();
   }

   public String getOriginalURI() {
      MetaData.Request metadata = this._metaData;
      if (metadata == null) {
         return null;
      } else {
         HttpURI uri = metadata.getURI();
         if (uri == null) {
            return null;
         } else {
            return uri.isAbsolute() && metadata.getHttpVersion() == HttpVersion.HTTP_2 ? uri.getPathQuery() : uri.toString();
         }
      }
   }

   public UserIdentity getUserIdentity() {
      if (this._authentication instanceof Authentication.Deferred) {
         this.setAuthentication(((Authentication.Deferred)this._authentication).authenticate(this));
      }

      return this._authentication instanceof Authentication.User ? ((Authentication.User)this._authentication).getUserIdentity() : null;
   }

   public UserIdentity getResolvedUserIdentity() {
      return this._authentication instanceof Authentication.User ? ((Authentication.User)this._authentication).getUserIdentity() : null;
   }

   public UserIdentity.Scope getUserIdentityScope() {
      return this._scope;
   }

   public Principal getUserPrincipal() {
      if (this._authentication instanceof Authentication.Deferred) {
         this.setAuthentication(((Authentication.Deferred)this._authentication).authenticate(this));
      }

      if (this._authentication instanceof Authentication.User) {
         UserIdentity user = ((Authentication.User)this._authentication).getUserIdentity();
         return user.getUserPrincipal();
      } else {
         return null;
      }
   }

   public boolean isHandled() {
      return this._handled;
   }

   public boolean isAsyncStarted() {
      return this.getHttpChannelState().isAsyncStarted();
   }

   public boolean isAsyncSupported() {
      return this._asyncNotSupportedSource == null;
   }

   public boolean isRequestedSessionIdFromCookie() {
      return this._requestedSessionId != null && this._requestedSessionIdFromCookie;
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public boolean isRequestedSessionIdFromUrl() {
      return this._requestedSessionId != null && !this._requestedSessionIdFromCookie;
   }

   public boolean isRequestedSessionIdFromURL() {
      return this._requestedSessionId != null && !this._requestedSessionIdFromCookie;
   }

   public boolean isRequestedSessionIdValid() {
      if (this._requestedSessionId == null) {
         return false;
      } else {
         HttpSession session = this.getSession(false);
         return session != null && this._sessionHandler.getSessionIdManager().getId(this._requestedSessionId).equals(this._sessionHandler.getId(session));
      }
   }

   public boolean isSecure() {
      return this._secure;
   }

   public void setSecure(boolean secure) {
      this._secure = secure;
   }

   public long getBeginNanoTime() {
      return this._metaData.getBeginNanoTime();
   }

   public boolean isUserInRole(String role) {
      if (this._authentication instanceof Authentication.Deferred) {
         this.setAuthentication(((Authentication.Deferred)this._authentication).authenticate(this));
      }

      return this._authentication instanceof Authentication.User ? ((Authentication.User)this._authentication).isUserInRole(this._scope, role) : false;
   }

   public void setMetaData(MetaData.Request request) {
      if (this._metaData == null && this._input != null && this._channel != null) {
         this._input.reopen();
         this._channel.getResponse().getHttpOutput().reopen();
      }

      this._metaData = request;
      this._method = request.getMethod();
      this._httpFields = request.getFields();
      HttpURI uri = request.getURI();
      UriCompliance compliance = null;
      if (uri.hasViolations()) {
         compliance = this._channel != null && this._channel.getHttpConfiguration() != null ? this._channel.getHttpConfiguration().getUriCompliance() : null;
         String badMessage = UriCompliance.checkUriCompliance(compliance, uri);
         if (badMessage != null) {
            throw new BadMessageException(badMessage);
         }
      }

      HttpField host = this.getHttpFields().getField(HttpHeader.HOST);
      if (uri.isAbsolute() && uri.hasAuthority() && uri.getPath() != null) {
         this._uri = uri;
         if (host instanceof HostPortHttpField && !((HostPortHttpField)host).getHostPort().toString().equals(uri.getAuthority())) {
            HttpChannel httpChannel = this.getHttpChannel();
            HttpConfiguration httpConfiguration = httpChannel.getHttpConfiguration();
            if (httpConfiguration != null) {
               HttpCompliance httpCompliance = httpConfiguration.getHttpCompliance();
               if (!httpCompliance.allows(HttpCompliance.Violation.MISMATCHED_AUTHORITY)) {
                  throw new BadMessageException(400, "Mismatched Authority");
               }

               if (httpChannel instanceof ComplianceViolation.Listener) {
                  ((ComplianceViolation.Listener)httpChannel).onComplianceViolation(httpCompliance, HttpCompliance.Violation.MISMATCHED_AUTHORITY, this._uri.toString());
               }
            }
         }
      } else {
         HttpURI.Mutable builder = HttpURI.build(uri);
         if (!uri.isAbsolute()) {
            builder.scheme(HttpScheme.HTTP.asString());
         }

         if (uri.getPath() == null) {
            builder.path("/");
         }

         if (!uri.hasAuthority()) {
            if (host instanceof HostPortHttpField) {
               HostPortHttpField authority = (HostPortHttpField)host;
               builder.host(authority.getHost()).port(authority.getPort());
            } else {
               builder.host(this.findServerName()).port(this.findServerPort());
            }
         }

         this._uri = builder.asImmutable();
      }

      this.setSecure(HttpScheme.HTTPS.is(this._uri.getScheme()));
      String encoded = this._uri.getPath();
      String path;
      if (encoded == null) {
         path = this._uri.isAbsolute() ? "/" : null;
      } else if (encoded.startsWith("/")) {
         path = encoded.length() == 1 ? "/" : this._uri.getDecodedPath();
      } else if (!"*".equals(encoded) && !HttpMethod.CONNECT.is(this.getMethod())) {
         path = null;
      } else {
         path = encoded;
      }

      if (path != null && !path.isEmpty()) {
         this._pathInContext = path;
      } else {
         this._pathInContext = encoded == null ? "" : encoded;
         throw new BadMessageException(400, "Bad URI");
      }
   }

   public MetaData.Request getMetaData() {
      return this._metaData;
   }

   public boolean hasMetaData() {
      return this._metaData != null;
   }

   protected void recycle() {
      if (this._context != null) {
         throw new IllegalStateException("Request in context!");
      } else {
         if (this._reader != null && this._inputState == 2) {
            try {
               for(int r = this._reader.read(); r != -1; r = this._reader.read()) {
               }
            } catch (Exception e) {
               LOG.trace("IGNORED", e);
               this._reader = null;
               this._readerEncoding = null;
            }
         }

         this.getHttpChannelState().recycle();
         this._requestAttributeListeners.clear();
         this._input.recycle();
         this._metaData = null;
         this._httpFields = null;
         this._trailers = null;
         this._uri = null;
         this._method = null;
         this._pathInContext = null;
         this._servletPathMapping = null;
         this._asyncNotSupportedSource = null;
         this._secure = false;
         this._newContext = false;
         this._cookiesExtracted = false;
         this._handled = false;
         this._contentParamsExtracted = false;
         this._requestedSessionIdFromCookie = false;
         this._attributes = Attributes.unwrap(this._attributes);
         if (this._attributes != null) {
            if (ServletAttributes.class.equals(this._attributes.getClass())) {
               this._attributes.clearAttributes();
            } else {
               this._attributes = null;
            }
         }

         this.setAuthentication(Authentication.NOT_CHECKED);
         this._contentType = null;
         this._characterEncoding = null;
         this._context = null;
         this._errorContext = null;
         if (this._cookies != null) {
            this._cookies.reset();
         }

         this._dispatcherType = null;
         this._inputState = 0;
         this._queryParameters = null;
         this._contentParameters = null;
         this._parameters = null;
         this._queryEncoding = null;
         this._remote = null;
         this._requestedSessionId = null;
         this._scope = null;
         this._session = null;
         this._sessionHandler = null;
         this._timeStamp = 0L;
         this._multiParts = null;
         if (this._async != null) {
            this._async.reset();
         }

         this._async = null;
         this._sessions = null;
      }
   }

   public void removeAttribute(String name) {
      Object oldValue = this._attributes == null ? null : this._attributes.getAttribute(name);
      if (this._attributes != null) {
         this._attributes.removeAttribute(name);
      }

      if (oldValue != null && !this._requestAttributeListeners.isEmpty()) {
         ServletRequestAttributeEvent event = new ServletRequestAttributeEvent(this._context, this, name, oldValue);

         for(ServletRequestAttributeListener listener : this._requestAttributeListeners) {
            listener.attributeRemoved(event);
         }
      }

   }

   public void removeEventListener(EventListener listener) {
      this._requestAttributeListeners.remove(listener);
   }

   public void setAsyncSupported(boolean supported, Object source) {
      this._asyncNotSupportedSource = supported ? null : (source == null ? "unknown" : source);
   }

   public void setAttribute(String name, Object value) {
      Object oldValue = this._attributes == null ? null : this._attributes.getAttribute(name);
      if ("org.sparkproject.jetty.server.Request.queryEncoding".equals(name)) {
         this.setQueryEncoding(value == null ? null : value.toString());
      } else if ("org.sparkproject.jetty.server.sendContent".equals(name)) {
         LOG.warn("Deprecated: org.eclipse.jetty.server.sendContent");
      }

      if (this._attributes == null) {
         this._attributes = new ServletAttributes();
      }

      this._attributes.setAttribute(name, value);
      if (!this._requestAttributeListeners.isEmpty()) {
         ServletRequestAttributeEvent event = new ServletRequestAttributeEvent(this._context, this, name, oldValue == null ? value : oldValue);

         for(ServletRequestAttributeListener l : this._requestAttributeListeners) {
            if (oldValue == null) {
               l.attributeAdded(event);
            } else if (value == null) {
               l.attributeRemoved(event);
            } else {
               l.attributeReplaced(event);
            }
         }
      }

   }

   public void setAttributes(Attributes attributes) {
      this._attributes = attributes;
   }

   public void setAsyncAttributes() {
      if (this.getAttribute("jakarta.servlet.async.request_uri") == null) {
         Attributes baseAttributes;
         if (this._attributes == null) {
            baseAttributes = this._attributes = new ServletAttributes();
         } else {
            baseAttributes = Attributes.unwrap(this._attributes);
         }

         String fwdRequestURI = (String)this.getAttribute("jakarta.servlet.forward.request_uri");
         if (fwdRequestURI == null) {
            if (baseAttributes instanceof ServletAttributes) {
               ((ServletAttributes)baseAttributes).setAsyncAttributes(this.getRequestURI(), this.getContextPath(), this.getPathInContext(), this.getServletPathMapping(), this.getQueryString());
            } else {
               this._attributes.setAttribute("jakarta.servlet.async.request_uri", this.getRequestURI());
               this._attributes.setAttribute("jakarta.servlet.async.context_path", this.getContextPath());
               this._attributes.setAttribute("jakarta.servlet.async.servlet_path", this.getServletPath());
               this._attributes.setAttribute("jakarta.servlet.async.path_info", this.getPathInfo());
               this._attributes.setAttribute("jakarta.servlet.async.query_string", this.getQueryString());
               this._attributes.setAttribute("jakarta.servlet.async.mapping", this.getHttpServletMapping());
            }
         } else if (baseAttributes instanceof ServletAttributes) {
            ((ServletAttributes)baseAttributes).setAsyncAttributes(fwdRequestURI, (String)this.getAttribute("jakarta.servlet.forward.context_path"), (String)this.getAttribute("jakarta.servlet.forward.path_info"), (ServletPathMapping)this.getAttribute("jakarta.servlet.forward.mapping"), (String)this.getAttribute("jakarta.servlet.forward.query_string"));
         } else {
            this._attributes.setAttribute("jakarta.servlet.async.request_uri", fwdRequestURI);
            this._attributes.setAttribute("jakarta.servlet.async.context_path", this.getAttribute("jakarta.servlet.forward.context_path"));
            this._attributes.setAttribute("jakarta.servlet.async.servlet_path", this.getAttribute("jakarta.servlet.forward.servlet_path"));
            this._attributes.setAttribute("jakarta.servlet.async.path_info", this.getAttribute("jakarta.servlet.forward.path_info"));
            this._attributes.setAttribute("jakarta.servlet.async.query_string", this.getAttribute("jakarta.servlet.forward.query_string"));
            this._attributes.setAttribute("jakarta.servlet.async.mapping", this.getAttribute("jakarta.servlet.forward.mapping"));
         }

      }
   }

   public void setAuthentication(Authentication authentication) {
      this._authentication = authentication;
   }

   public void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {
      if (this._inputState == 0) {
         this._characterEncoding = encoding;
         if (!StringUtil.isUTF8(encoding)) {
            try {
               Charset.forName(encoding);
            } catch (UnsupportedCharsetException e) {
               throw new UnsupportedEncodingException(e.getMessage());
            }
         }

      }
   }

   public void setCharacterEncodingUnchecked(String encoding) {
      this._characterEncoding = encoding;
   }

   public void setContentType(String contentType) {
      this._contentType = contentType;
   }

   public void setContext(ContextHandler.Context context, String pathInContext) {
      this._newContext = this._context != context;
      this._context = context;
      this._pathInContext = pathInContext;
      if (context != null) {
         this._errorContext = context;
      }

   }

   public boolean takeNewContext() {
      boolean nc = this._newContext;
      this._newContext = false;
      return nc;
   }

   public void setCookies(Cookie[] cookies) {
      if (this._cookies == null) {
         this._cookies = new Cookies(this.getHttpChannel().getHttpConfiguration().getRequestCookieCompliance(), this.getComplianceViolationListener());
      }

      this._cookies.setCookies(cookies);
   }

   public void setDispatcherType(DispatcherType type) {
      this._dispatcherType = type;
   }

   public void setHandled(boolean h) {
      this._handled = h;
   }

   public void setMethod(String method) {
      this._method = method;
   }

   public boolean isHead() {
      return HttpMethod.HEAD.is(this.getMethod());
   }

   public void setQueryEncoding(String queryEncoding) {
      this._queryEncoding = Charset.forName(queryEncoding);
   }

   public void setRemoteAddr(InetSocketAddress addr) {
      this._remote = addr;
   }

   public void setRequestedSessionId(String requestedSessionId) {
      this._requestedSessionId = requestedSessionId;
   }

   public void setRequestedSessionIdFromCookie(boolean requestedSessionIdCookie) {
      this._requestedSessionIdFromCookie = requestedSessionIdCookie;
   }

   public void setSession(HttpSession session) {
      this._session = session;
   }

   public void setSessionHandler(SessionHandler sessionHandler) {
      this._sessionHandler = sessionHandler;
   }

   public void setTimeStamp(long ts) {
      this._timeStamp = ts;
   }

   public void setUserIdentityScope(UserIdentity.Scope scope) {
      this._scope = scope;
   }

   public AsyncContext startAsync() throws IllegalStateException {
      if (this._asyncNotSupportedSource != null) {
         throw new IllegalStateException("!asyncSupported: " + String.valueOf(this._asyncNotSupportedSource));
      } else {
         return this.forceStartAsync();
      }
   }

   private AsyncContextState forceStartAsync() {
      HttpChannelState state = this.getHttpChannelState();
      if (this._async == null) {
         this._async = new AsyncContextState(state);
      }

      AsyncContextEvent event = new AsyncContextEvent(this._context, this._async, state, this, this, this.getResponse());
      state.startAsync(event);
      return this._async;
   }

   public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
      if (this._asyncNotSupportedSource != null) {
         throw new IllegalStateException("!asyncSupported: " + String.valueOf(this._asyncNotSupportedSource));
      } else {
         HttpChannelState state = this.getHttpChannelState();
         if (this._async == null) {
            this._async = new AsyncContextState(state);
         }

         AsyncContextEvent event = new AsyncContextEvent(this._context, this._async, state, this, servletRequest, servletResponse, this.getHttpURI());
         event.setDispatchContext(this.getServletContext());
         state.startAsync(event);
         return this._async;
      }
   }

   public static HttpServletRequest unwrap(ServletRequest servletRequest) {
      if (servletRequest instanceof HttpServletRequestWrapper) {
         return (HttpServletRequestWrapper)servletRequest;
      } else {
         return servletRequest instanceof ServletRequestWrapper ? unwrap(((ServletRequestWrapper)servletRequest).getRequest()) : (HttpServletRequest)servletRequest;
      }
   }

   public String toString() {
      return String.format("%s%s%s %s%s@%x", this.getClass().getSimpleName(), this._handled ? "[" : "(", this.getMethod(), this.getHttpURI(), this._handled ? "]" : ")", this.hashCode());
   }

   public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
      if (this.getUserPrincipal() != null && this.getRemoteUser() != null && this.getAuthType() != null) {
         return true;
      } else {
         if (this._authentication instanceof Authentication.Deferred) {
            this.setAuthentication(((Authentication.Deferred)this._authentication).authenticate(this, response));
         }

         if (this._authentication instanceof Authentication.Deferred) {
            response.sendError(401);
         }

         if (!(this._authentication instanceof Authentication.ResponseSent)) {
            return false;
         } else {
            throw new ServletException("Authentication failed");
         }
      }
   }

   public Part getPart(String name) throws IOException, ServletException {
      this.getParts();
      return this._multiParts.getPart(name);
   }

   public Collection getParts() throws IOException, ServletException {
      String contentType = this.getContentType();
      if (contentType != null && MimeTypes.Type.MULTIPART_FORM_DATA.is(HttpField.valueParameters(contentType, (Map)null))) {
         return this.getParts((MultiMap)null);
      } else {
         throw new ServletException("Unsupported Content-Type [" + contentType + "], expected [multipart/form-data]");
      }
   }

   private Collection getParts(MultiMap params) throws IOException {
      if (this._multiParts == null) {
         MultipartConfigElement config = (MultipartConfigElement)this.getAttribute("org.sparkproject.jetty.multipartConfig");
         if (config == null) {
            throw new IllegalStateException("No multipart config for servlet");
         }

         int maxFormContentSize = 200000;
         int maxFormKeys = 1000;
         if (this._context != null) {
            ContextHandler contextHandler = this._context.getContextHandler();
            maxFormContentSize = contextHandler.getMaxFormContentSize();
            maxFormKeys = contextHandler.getMaxFormKeys();
         } else {
            maxFormContentSize = this.lookupServerAttribute("org.sparkproject.jetty.server.Request.maxFormContentSize", maxFormContentSize);
            maxFormKeys = this.lookupServerAttribute("org.sparkproject.jetty.server.Request.maxFormKeys", maxFormKeys);
         }

         this._multiParts = this.newMultiParts(config, maxFormKeys);
         Collection<Part> parts = this._multiParts.getParts();
         String formCharset = null;
         Part charsetPart = this._multiParts.getPart("_charset_");
         if (charsetPart != null) {
            InputStream is = charsetPart.getInputStream();

            try {
               ByteArrayOutputStream os = new ByteArrayOutputStream();
               IO.copy((InputStream)is, (OutputStream)os);
               formCharset = os.toString(StandardCharsets.UTF_8);
            } catch (Throwable var20) {
               if (is != null) {
                  try {
                     is.close();
                  } catch (Throwable var19) {
                     var20.addSuppressed(var19);
                  }
               }

               throw var20;
            }

            if (is != null) {
               is.close();
            }
         }

         Charset defaultCharset;
         if (formCharset != null) {
            defaultCharset = Charset.forName(formCharset);
         } else if (this.getCharacterEncoding() != null) {
            defaultCharset = Charset.forName(this.getCharacterEncoding());
         } else {
            defaultCharset = StandardCharsets.UTF_8;
         }

         long formContentSize = 0L;
         ByteArrayOutputStream os = null;

         for(Part p : parts) {
            if (p.getSubmittedFileName() == null) {
               formContentSize = Math.addExact(formContentSize, p.getSize());
               if (maxFormContentSize >= 0 && formContentSize > (long)maxFormContentSize) {
                  throw new IllegalStateException("Form is larger than max length " + maxFormContentSize);
               }

               String charset = null;
               if (p.getContentType() != null) {
                  charset = MimeTypes.getCharsetFromContentType(p.getContentType());
               }

               InputStream is = p.getInputStream();

               try {
                  if (os == null) {
                     os = new ByteArrayOutputStream();
                  }

                  IO.copy((InputStream)is, (OutputStream)os);
                  String content = os.toString(charset == null ? defaultCharset : Charset.forName(charset));
                  if (this._contentParameters == null) {
                     this._contentParameters = params == null ? new MultiMap() : params;
                  }

                  this._contentParameters.add(p.getName(), content);
               } catch (Throwable var21) {
                  if (is != null) {
                     try {
                        is.close();
                     } catch (Throwable var18) {
                        var21.addSuppressed(var18);
                     }
                  }

                  throw var21;
               }

               if (is != null) {
                  is.close();
               }

               os.reset();
            }
         }
      }

      return this._multiParts.getParts();
   }

   private MultiParts newMultiParts(MultipartConfigElement config, int maxParts) throws IOException {
      MultiPartFormDataCompliance compliance = this.getHttpChannel().getHttpConfiguration().getMultipartFormDataCompliance();
      if (LOG.isDebugEnabled()) {
         LOG.debug("newMultiParts {} {}", compliance, this);
      }

      switch (compliance) {
         case RFC7578:
            return new MultiParts.MultiPartsHttpParser(this.getInputStream(), this.getContentType(), config, this._context != null ? (File)this._context.getAttribute("jakarta.servlet.context.tempdir") : null, this, maxParts);
         case LEGACY:
         default:
            return new MultiParts.MultiPartsUtilParser(this.getInputStream(), this.getContentType(), config, this._context != null ? (File)this._context.getAttribute("jakarta.servlet.context.tempdir") : null, this, maxParts);
      }
   }

   public void login(String username, String password) throws ServletException {
      if (this._authentication instanceof Authentication.LoginAuthentication) {
         Authentication auth = ((Authentication.LoginAuthentication)this._authentication).login(username, password, this);
         if (auth == null) {
            throw new Authentication.Failed("Authentication failed for username '" + username + "'");
         } else {
            this._authentication = auth;
         }
      } else {
         throw new Authentication.Failed("Authenticated failed for username '" + username + "'. Already authenticated as " + String.valueOf(this._authentication));
      }
   }

   public void logout() throws ServletException {
      if (this._authentication instanceof Authentication.LogoutAuthentication) {
         this._authentication = ((Authentication.LogoutAuthentication)this._authentication).logout(this);
      }

   }

   public void mergeQueryParameters(String oldQuery, String newQuery) {
      MultiMap<String> newQueryParams = null;
      if (newQuery != null) {
         newQueryParams = new MultiMap();
         UrlEncoded.decodeTo(newQuery, newQueryParams, UrlEncoded.ENCODING);
      }

      MultiMap<String> oldQueryParams = this._queryParameters;
      if (oldQueryParams == null && oldQuery != null) {
         oldQueryParams = new MultiMap();

         try {
            UrlEncoded.decodeTo(oldQuery, oldQueryParams, this.getQueryCharset());
         } catch (Throwable th) {
            this._queryParameters = BAD_PARAMS;
            throw new BadMessageException(400, "Bad query encoding", th);
         }
      }

      MultiMap<String> mergedQueryParams;
      if (newQueryParams != null && newQueryParams.size() != 0) {
         if (oldQueryParams != null && oldQueryParams.size() != 0) {
            mergedQueryParams = new MultiMap(newQueryParams);
            mergedQueryParams.addAllValues(oldQueryParams);
         } else {
            mergedQueryParams = newQueryParams;
         }
      } else {
         mergedQueryParams = oldQueryParams == null ? NO_PARAMS : oldQueryParams;
      }

      this.setQueryParameters(mergedQueryParams);
      this.resetParameters();
   }

   public HttpUpgradeHandler upgrade(Class handlerClass) throws IOException, ServletException {
      Response response = this._channel.getResponse();
      if (response.getStatus() != 101) {
         throw new IllegalStateException("Response status should be 101");
      } else if (response.getHeader("Upgrade") == null) {
         throw new IllegalStateException("Missing Upgrade header");
      } else if (!"Upgrade".equalsIgnoreCase(response.getHeader("Connection"))) {
         throw new IllegalStateException("Invalid Connection header");
      } else if (response.isCommitted()) {
         throw new IllegalStateException("Cannot upgrade committed response");
      } else if (this._metaData != null && this._metaData.getHttpVersion() == HttpVersion.HTTP_1_1) {
         final ServletOutputStream outputStream = response.getOutputStream();
         final ServletInputStream inputStream = this.getInputStream();
         HttpChannelOverHttp httpChannel11 = (HttpChannelOverHttp)this._channel;
         HttpConnection httpConnection = (HttpConnection)this._channel.getConnection();

         final T upgradeHandler;
         try {
            upgradeHandler = (T)((HttpUpgradeHandler)handlerClass.getDeclaredConstructor().newInstance());
         } catch (Exception e) {
            throw new ServletException("Unable to instantiate handler class", e);
         }

         httpChannel11.servletUpgrade();
         final AsyncContext asyncContext = this.forceStartAsync();
         outputStream.flush();
         httpConnection.getGenerator().servletUpgrade();
         httpConnection.addEventListener(new Connection.Listener() {
            public void onClosed(Connection connection) {
               try {
                  asyncContext.complete();
               } catch (Exception e) {
                  Request.LOG.warn("error during upgrade AsyncContext complete", e);
               }

               try {
                  upgradeHandler.destroy();
               } catch (Exception e) {
                  Request.LOG.warn("error during upgrade HttpUpgradeHandler destroy", e);
               }

            }

            public void onOpened(Connection connection) {
            }
         });
         upgradeHandler.init(new WebConnection() {
            public void close() throws Exception {
               try {
                  inputStream.close();
               } finally {
                  outputStream.close();
               }

            }

            public ServletInputStream getInputStream() {
               return inputStream;
            }

            public ServletOutputStream getOutputStream() {
               return outputStream;
            }
         });
         return upgradeHandler;
      } else {
         throw new IllegalStateException("Only requests over HTTP/1.1 can be upgraded");
      }
   }

   public void setServletPathMapping(ServletPathMapping servletPathMapping) {
      this._servletPathMapping = servletPathMapping;
   }

   public ServletPathMapping getServletPathMapping() {
      return this._servletPathMapping;
   }

   ServletPathMapping findServletPathMapping() {
      ServletPathMapping mapping;
      if (this._dispatcherType == DispatcherType.INCLUDE) {
         Dispatcher.IncludeAttributes include = (Dispatcher.IncludeAttributes)Attributes.unwrap(this._attributes, Dispatcher.IncludeAttributes.class);
         mapping = include == null ? this._servletPathMapping : include.getSourceMapping();
      } else {
         mapping = this._servletPathMapping;
      }

      return mapping;
   }

   public HttpServletMapping getHttpServletMapping() {
      if (this._dispatcherType == DispatcherType.ASYNC) {
         ServletPathMapping async = (ServletPathMapping)this.getAttribute("jakarta.servlet.async.mapping");
         if (async != null && "/DispatchServlet".equals(async.getServletPath())) {
            return async;
         }
      }

      return this.findServletPathMapping();
   }

   private String formatAddrOrHost(String name) {
      return this._channel == null ? HostPort.normalizeHost(name) : this._channel.formatAddrOrHost(name);
   }

   static {
      NOT_PUSHED_HEADERS = EnumSet.of(HttpHeader.IF_MATCH, HttpHeader.IF_RANGE, HttpHeader.IF_UNMODIFIED_SINCE, HttpHeader.RANGE, HttpHeader.EXPECT, HttpHeader.REFERER, HttpHeader.COOKIE, HttpHeader.AUTHORIZATION, HttpHeader.IF_NONE_MATCH, HttpHeader.IF_MODIFIED_SINCE);
   }
}
