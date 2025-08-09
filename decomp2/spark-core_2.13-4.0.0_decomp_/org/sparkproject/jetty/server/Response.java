package org.sparkproject.jetty.server;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletResponseWrapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.IllegalSelectorException;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import org.sparkproject.jetty.http.CookieCompliance;
import org.sparkproject.jetty.http.DateGenerator;
import org.sparkproject.jetty.http.HttpContent;
import org.sparkproject.jetty.http.HttpCookie;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpGenerator;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.http.HttpStatus;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.io.RuntimeIOException;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.session.SessionHandler;
import org.sparkproject.jetty.util.AtomicBiInteger;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;

public class Response implements HttpServletResponse {
   private static final int __MIN_BUFFER_SIZE = 1;
   private static final HttpField __EXPIRES_01JAN1970;
   public static final int NO_CONTENT_LENGTH = -1;
   public static final int USE_KNOWN_CONTENT_LENGTH = -2;
   public static final String SET_INCLUDE_HEADER_PREFIX = "org.sparkproject.jetty.server.include.";
   private final HttpChannel _channel;
   private final HttpFields.Mutable _fields = HttpFields.build();
   private final AtomicBiInteger _errorSentAndIncludes = new AtomicBiInteger();
   private final HttpOutput _out;
   private int _status = 200;
   private String _reason;
   private Locale _locale;
   private MimeTypes.Type _mimeType;
   private String _characterEncoding;
   private EncodingFrom _encodingFrom;
   private String _contentType;
   private OutputType _outputType;
   private ResponseWriter _writer;
   private long _contentLength;
   private Supplier _trailers;
   private static final EnumSet __localeOverride;
   private static final EnumSet __explicitCharset;

   public Response(HttpChannel channel, HttpOutput out) {
      this._encodingFrom = Response.EncodingFrom.NOT_SET;
      this._outputType = Response.OutputType.NONE;
      this._contentLength = -1L;
      this._channel = channel;
      this._out = out;
   }

   public HttpChannel getHttpChannel() {
      return this._channel;
   }

   protected void recycle() {
      this._fields.clear();
      this._errorSentAndIncludes.set(0L);
      this._out.recycle();
      this._status = 200;
      this._reason = null;
      this._locale = null;
      this._mimeType = null;
      this._characterEncoding = null;
      this._encodingFrom = Response.EncodingFrom.NOT_SET;
      this._contentType = null;
      this._outputType = Response.OutputType.NONE;
      this._contentLength = -1L;
      this._trailers = null;
   }

   public HttpOutput getHttpOutput() {
      return this._out;
   }

   public void reopen() {
      this.setErrorSent(false);
      this._out.reopen();
   }

   public void errorClose() {
      this.setErrorSent(true);
      this._out.softClose();
   }

   private boolean isMutable() {
      return this._errorSentAndIncludes.get() == 0L;
   }

   private void setErrorSent(boolean errorSent) {
      this._errorSentAndIncludes.getAndSetHi(errorSent ? 1 : 0);
   }

   public boolean isIncluding() {
      return this._errorSentAndIncludes.getLo() > 0;
   }

   public void include() {
      this._errorSentAndIncludes.add(0, 1);
   }

   public void included() {
      this._errorSentAndIncludes.add(0, -1);
      if (this._outputType == Response.OutputType.WRITER) {
         this._writer.reopen();
      }

      this._out.reopen();
   }

   public void addCookie(HttpCookie cookie) {
      if (StringUtil.isBlank(cookie.getName())) {
         throw new IllegalArgumentException("Cookie.name cannot be blank/null");
      } else {
         this._fields.add((HttpField)(new HttpCookie.SetCookieHttpField(this.checkSameSite(cookie), this.getHttpChannel().getHttpConfiguration().getResponseCookieCompliance())));
         this._fields.put(__EXPIRES_01JAN1970);
      }
   }

   private HttpCookie checkSameSite(HttpCookie cookie) {
      if (cookie != null && cookie.getSameSite() == null) {
         HttpCookie.SameSite contextDefault = HttpCookie.getSameSiteDefault(this._channel.getRequest().getContext());
         return contextDefault == null ? cookie : new HttpCookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(), cookie.getMaxAge(), cookie.isHttpOnly(), cookie.isSecure(), cookie.getComment(), cookie.getVersion(), contextDefault);
      } else {
         return cookie;
      }
   }

   public void addCookie(Cookie cookie) {
      if (this.isMutable()) {
         if (StringUtil.isBlank(cookie.getName())) {
            throw new IllegalArgumentException("Cookie.name cannot be blank/null");
         }

         String comment = cookie.getComment();
         boolean httpOnly = cookie.isHttpOnly() || HttpCookie.isHttpOnlyInComment(comment);
         boolean partitioned = HttpCookie.isPartitionedInComment(comment);
         HttpCookie.SameSite sameSite = HttpCookie.getSameSiteFromComment(comment);
         comment = HttpCookie.getCommentWithoutAttributes(comment);
         this.addCookie(new HttpCookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(), (long)cookie.getMaxAge(), httpOnly, cookie.getSecure(), comment, cookie.getVersion(), sameSite, partitioned));
      }

   }

   public void replaceCookie(HttpCookie cookie) {
      ListIterator<HttpField> i = this._fields.listIterator();

      CookieCompliance compliance;
      while(true) {
         HttpCookie oldCookie;
         while(true) {
            if (!i.hasNext()) {
               this.addCookie(cookie);
               return;
            }

            HttpField field = (HttpField)i.next();
            if (field.getHeader() == HttpHeader.SET_COOKIE) {
               compliance = this.getHttpChannel().getHttpConfiguration().getResponseCookieCompliance();
               if (field instanceof HttpCookie.SetCookieHttpField) {
                  oldCookie = ((HttpCookie.SetCookieHttpField)field).getHttpCookie();
               } else {
                  oldCookie = new HttpCookie(field.getValue());
               }

               if (cookie.getName().equals(oldCookie.getName())) {
                  if (cookie.getDomain() == null) {
                     if (oldCookie.getDomain() != null) {
                        continue;
                     }
                  } else if (!cookie.getDomain().equalsIgnoreCase(oldCookie.getDomain())) {
                     continue;
                  }
                  break;
               }
            }
         }

         if (cookie.getPath() == null) {
            if (oldCookie.getPath() != null) {
               continue;
            }
         } else if (!cookie.getPath().equals(oldCookie.getPath())) {
            continue;
         }
         break;
      }

      i.set(new HttpCookie.SetCookieHttpField(this.checkSameSite(cookie), compliance));
   }

   public boolean containsHeader(String name) {
      return this._fields.contains(name);
   }

   public String encodeURL(String url) {
      if (url == null) {
         return null;
      } else {
         Request request = this._channel.getRequest();
         SessionHandler sessionManager = request.getSessionHandler();
         if (sessionManager == null) {
            return url;
         } else {
            HttpURI uri = null;
            boolean hasScheme = URIUtil.hasScheme(url);
            if (sessionManager.isCheckingRemoteSessionIdEncoding() && hasScheme) {
               uri = HttpURI.from(url);
               String path = uri.getPath();
               path = path == null ? "" : path;
               int port = uri.getPort();
               if (port < 0) {
                  port = HttpScheme.getDefaultPort(uri.getScheme());
               }

               if (!request.getServerName().equalsIgnoreCase(uri.getHost())) {
                  return url;
               }

               if (request.getServerPort() != port) {
                  return url;
               }

               if (request.getContext() != null && !path.startsWith(request.getContextPath())) {
                  return url;
               }
            }

            String sessionURLPrefix = sessionManager.getSessionIdPathParameterNamePrefix();
            if (sessionURLPrefix == null) {
               return url;
            } else if ((!sessionManager.isUsingCookies() || !request.isRequestedSessionIdFromCookie()) && sessionManager.isUsingURLs()) {
               HttpSession session = request.getSession(false);
               if (session == null) {
                  return url;
               } else if (!sessionManager.isValid(session)) {
                  return url;
               } else {
                  String id = sessionManager.getExtendedId(session);
                  int prefix = url.indexOf(sessionURLPrefix);
                  if (prefix != -1) {
                     int suffix = url.indexOf("?", prefix);
                     if (suffix < 0) {
                        suffix = url.indexOf("#", prefix);
                     }

                     if (suffix <= prefix) {
                        String var18 = url.substring(0, prefix + sessionURLPrefix.length());
                        return var18 + id;
                     } else {
                        return url.substring(0, prefix + sessionURLPrefix.length()) + id + url.substring(suffix);
                     }
                  } else {
                     String nonNullPath = "";
                     if (hasScheme) {
                        if (uri == null) {
                           uri = HttpURI.from(url);
                        }

                        if (uri.getPath() == null) {
                           nonNullPath = "/";
                        }
                     }

                     int suffix = url.indexOf(63);
                     if (suffix < 0) {
                        suffix = url.indexOf(35);
                     }

                     return suffix < 0 ? url + nonNullPath + sessionURLPrefix + id : url.substring(0, suffix) + nonNullPath + sessionURLPrefix + id + url.substring(suffix);
                  }
               }
            } else {
               int prefix = url.indexOf(sessionURLPrefix);
               if (prefix != -1) {
                  int suffix = url.indexOf("?", prefix);
                  if (suffix < 0) {
                     suffix = url.indexOf("#", prefix);
                  }

                  if (suffix <= prefix) {
                     return url.substring(0, prefix);
                  } else {
                     String var10000 = url.substring(0, prefix);
                     return var10000 + url.substring(suffix);
                  }
               } else {
                  return url;
               }
            }
         }
      }
   }

   public String encodeRedirectURL(String url) {
      return this.encodeURL(url);
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public String encodeUrl(String url) {
      return this.encodeURL(url);
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public String encodeRedirectUrl(String url) {
      return this.encodeRedirectURL(url);
   }

   public void sendError(int sc) throws IOException {
      this.sendError(sc, (String)null);
   }

   public void sendError(int code, String message) throws IOException {
      if (!this.isIncluding()) {
         switch (code) {
            case -1:
               this._channel.abort(new IOException(message));
               break;
            case 102:
               this.sendProcessing();
               break;
            case 103:
               this.sendEarlyHint();
               break;
            default:
               this._channel.getState().sendError(code, message);
         }

      }
   }

   public void sendProcessing() throws IOException {
      if (this._channel.isExpecting102Processing() && !this.isCommitted()) {
         this._channel.sendResponse(HttpGenerator.PROGRESS_102_INFO, (ByteBuffer)null, true);
      }

   }

   public void sendEarlyHint() throws IOException {
      if (!this.isCommitted()) {
         this._channel.sendResponse(new MetaData.Response(this._channel.getRequest().getHttpVersion(), 103, this._channel.getResponse()._fields.asImmutable()), (ByteBuffer)null, true);
      }

   }

   public void sendRedirect(int code, String location) throws IOException {
      this.sendRedirect(code, location, false);
   }

   public void sendRedirect(String location, boolean consumeAll) throws IOException {
      this.sendRedirect(this.getHttpChannel().getRequest().getHttpVersion().getVersion() < HttpVersion.HTTP_1_1.getVersion() ? 302 : 303, location, consumeAll);
   }

   public void sendRedirect(int code, String location, boolean consumeAll) throws IOException {
      if (consumeAll) {
         this.getHttpChannel().ensureConsumeAllOrNotPersistent();
      }

      if (!HttpStatus.isRedirection(code)) {
         throw new IllegalArgumentException("Not a 3xx redirect code");
      } else if (this.isMutable()) {
         location = toRedirectURI(this._channel.getRequest(), location);
         this.resetBuffer();
         this.setHeader(HttpHeader.LOCATION, location);
         this.setStatus(code);
         this.closeOutput();
      }
   }

   public void sendRedirect(String location) throws IOException {
      this.sendRedirect(302, location);
   }

   public static String toRedirectURI(HttpServletRequest request, String location) {
      if (!URIUtil.hasScheme(location)) {
         if (!location.startsWith("/")) {
            String path = request.getRequestURI();
            String parent = path.endsWith("/") ? path : URIUtil.parentPath(path);
            location = URIUtil.addEncodedPaths(parent, location);
         }

         location = URIUtil.canonicalURI(location);
         if (location == null) {
            throw new IllegalStateException("redirect path cannot be above root");
         }

         Request baseRequest = Request.getBaseRequest(request);
         if (baseRequest == null || !baseRequest.getHttpChannel().getHttpConfiguration().isRelativeRedirectAllowed()) {
            StringBuilder url = new StringBuilder(128);
            URIUtil.appendSchemeHostPort(url, request.getScheme(), request.getServerName(), request.getServerPort());
            url.append(location);
            location = url.toString();
         }
      }

      return location;
   }

   public void setDateHeader(String name, long date) {
      if (this.isMutable()) {
         HttpHeader header = (HttpHeader)HttpHeader.CACHE.get(name);
         if (header == null) {
            this._fields.putDateField(name, date);
         } else {
            this._fields.putDateField(header, date);
         }
      }

   }

   public void addDateHeader(String name, long date) {
      if (this.isMutable()) {
         this._fields.addDateField(name, date);
      }

   }

   public void setHeader(HttpHeader name, String value) {
      if (this.isMutable()) {
         if (HttpHeader.CONTENT_TYPE == name) {
            this.setContentType(value);
         } else {
            this._fields.put(name, value);
            if (HttpHeader.CONTENT_LENGTH == name) {
               if (value == null) {
                  this._contentLength = -1L;
               } else {
                  this._contentLength = Long.parseLong(value);
               }
            }
         }
      }

   }

   public void setHeader(String name, String value) {
      long biInt = this._errorSentAndIncludes.get();
      if (biInt != 0L) {
         boolean errorSent = AtomicBiInteger.getHi(biInt) != 0;
         boolean including = AtomicBiInteger.getLo(biInt) > 0;
         if (errorSent || !including || !name.startsWith("org.sparkproject.jetty.server.include.")) {
            return;
         }

         name = name.substring("org.sparkproject.jetty.server.include.".length());
      }

      if (HttpHeader.CONTENT_TYPE.is(name)) {
         this.setContentType(value);
      } else {
         this._fields.put(name, value);
         if (HttpHeader.CONTENT_LENGTH.is(name)) {
            if (value == null) {
               this._contentLength = -1L;
            } else {
               this._contentLength = Long.parseLong(value);
            }
         }
      }

   }

   public Collection getHeaderNames() {
      return this._fields.getFieldNamesCollection();
   }

   public String getHeader(String name) {
      return this._fields.get(name);
   }

   public Collection getHeaders(String name) {
      Collection<String> i = this._fields.getValuesList(name);
      return (Collection)(i == null ? Collections.emptyList() : i);
   }

   public void addHeader(String name, String value) {
      long biInt = this._errorSentAndIncludes.get();
      if (biInt != 0L) {
         boolean errorSent = AtomicBiInteger.getHi(biInt) != 0;
         boolean including = AtomicBiInteger.getLo(biInt) > 0;
         if (errorSent || !including || !name.startsWith("org.sparkproject.jetty.server.include.")) {
            return;
         }

         name = name.substring("org.sparkproject.jetty.server.include.".length());
      }

      if (HttpHeader.CONTENT_TYPE.is(name)) {
         this.setContentType(value);
      } else if (HttpHeader.CONTENT_LENGTH.is(name)) {
         this.setHeader(name, value);
      } else {
         this._fields.add(name, value);
      }
   }

   public void setIntHeader(String name, int value) {
      if (this.isMutable()) {
         this._fields.putLongField(name, (long)value);
         if (HttpHeader.CONTENT_LENGTH.is(name)) {
            this._contentLength = (long)value;
         }
      }

   }

   public void addIntHeader(String name, int value) {
      if (this.isMutable()) {
         this._fields.add(name, Integer.toString(value));
         if (HttpHeader.CONTENT_LENGTH.is(name)) {
            this._contentLength = (long)value;
         }
      }

   }

   public void setStatus(int sc) {
      if (sc <= 0) {
         throw new IllegalArgumentException();
      } else {
         if (this.isMutable()) {
            if (this._status != sc) {
               this._reason = null;
            }

            this._status = sc;
         }

      }
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public void setStatus(int sc, String message) {
      this.setStatusWithReason(sc, (String)null);
   }

   public void setStatusWithReason(int sc, String message) {
      if (sc <= 0) {
         throw new IllegalArgumentException();
      } else {
         if (this.isMutable()) {
            this._status = sc;
            this._reason = message;
         }

      }
   }

   public String getCharacterEncoding() {
      return this.getCharacterEncoding(false);
   }

   private String getCharacterEncoding(boolean setContentType) {
      if (this._characterEncoding != null) {
         return this._characterEncoding;
      } else if (this._mimeType != null && this._mimeType.isCharsetAssumed()) {
         return this._mimeType.getCharsetString();
      } else {
         String encoding = MimeTypes.getCharsetAssumedFromContentType(this._contentType);
         if (encoding != null) {
            return encoding;
         } else {
            encoding = MimeTypes.getCharsetInferredFromContentType(this._contentType);
            if (encoding != null) {
               if (setContentType) {
                  this.setCharacterEncoding(encoding, Response.EncodingFrom.INFERRED);
               }

               return encoding;
            } else {
               ContextHandler.Context context = this._channel.getRequest().getContext();
               if (context != null) {
                  encoding = context.getResponseCharacterEncoding();
                  if (encoding != null) {
                     if (setContentType) {
                        this.setCharacterEncoding(encoding, Response.EncodingFrom.DEFAULT);
                     }

                     return encoding;
                  }
               }

               encoding = "iso-8859-1";
               if (setContentType) {
                  this.setCharacterEncoding(encoding, Response.EncodingFrom.DEFAULT);
               }

               return encoding;
            }
         }
      }
   }

   public String getContentType() {
      return this._contentType;
   }

   public ServletOutputStream getOutputStream() throws IOException {
      if (this._outputType == Response.OutputType.WRITER) {
         throw new IllegalStateException("WRITER");
      } else {
         this._outputType = Response.OutputType.STREAM;
         return this._out;
      }
   }

   public boolean isWriting() {
      return this._outputType == Response.OutputType.WRITER;
   }

   public boolean isStreaming() {
      return this._outputType == Response.OutputType.STREAM;
   }

   public boolean isWritingOrStreaming() {
      return this.isWriting() || this.isStreaming();
   }

   public PrintWriter getWriter() throws IOException {
      if (this._outputType == Response.OutputType.STREAM) {
         throw new IllegalStateException("STREAM");
      } else {
         if (this._outputType == Response.OutputType.NONE) {
            String encoding = this.getCharacterEncoding(true);
            Locale locale = this.getLocale();
            if (this._writer != null && this._writer.isFor(locale, encoding)) {
               this._writer.reopen();
            } else if ("iso-8859-1".equalsIgnoreCase(encoding)) {
               this._writer = new ResponseWriter(new Iso88591HttpWriter(this._out), locale, encoding);
            } else if ("utf-8".equalsIgnoreCase(encoding)) {
               this._writer = new ResponseWriter(new Utf8HttpWriter(this._out), locale, encoding);
            } else {
               this._writer = new ResponseWriter(new EncodingHttpWriter(this._out, encoding), locale, encoding);
            }

            this._outputType = Response.OutputType.WRITER;
         }

         return this._writer;
      }
   }

   public void setContentLength(int len) {
      if (!this.isCommitted() && this.isMutable()) {
         if (len > 0) {
            long written = this._out.getWritten();
            if (written > (long)len) {
               throw new IllegalArgumentException("setContentLength(" + len + ") when already written " + written);
            }

            this._contentLength = (long)len;
            this._fields.putLongField(HttpHeader.CONTENT_LENGTH, (long)len);
            if (this.isAllContentWritten(written)) {
               try {
                  this.closeOutput();
               } catch (IOException e) {
                  throw new RuntimeIOException(e);
               }
            }
         } else if (len == 0) {
            long written = this._out.getWritten();
            if (written > 0L) {
               throw new IllegalArgumentException("setContentLength(0) when already written " + written);
            }

            this._contentLength = (long)len;
            this._fields.put(HttpHeader.CONTENT_LENGTH, "0");
         } else {
            this._contentLength = (long)len;
            this._fields.remove(HttpHeader.CONTENT_LENGTH);
         }

      }
   }

   public long getContentLength() {
      return this._contentLength;
   }

   public boolean isAllContentWritten(long written) {
      return this._contentLength >= 0L && written >= this._contentLength;
   }

   public boolean isContentComplete(long written) {
      return this._contentLength < 0L || written >= this._contentLength;
   }

   public void closeOutput() throws IOException {
      if (this._outputType == Response.OutputType.WRITER) {
         this._writer.close();
      } else {
         this._out.close();
      }

   }

   /** @deprecated */
   @Deprecated
   public void completeOutput() throws IOException {
      this.closeOutput();
   }

   public void completeOutput(Callback callback) {
      if (this._outputType == Response.OutputType.WRITER) {
         this._writer.complete(callback);
      } else {
         this._out.complete(callback);
      }

   }

   public long getLongContentLength() {
      return this._contentLength;
   }

   public void setLongContentLength(long len) {
      if (!this.isCommitted() && this.isMutable()) {
         this._contentLength = len;
         this._fields.putLongField(HttpHeader.CONTENT_LENGTH.toString(), len);
      }
   }

   public void setContentLengthLong(long length) {
      this.setLongContentLength(length);
   }

   public void setCharacterEncoding(String encoding) {
      this.setCharacterEncoding(encoding, Response.EncodingFrom.SET_CHARACTER_ENCODING);
   }

   private void setCharacterEncoding(String encoding, EncodingFrom from) {
      if (this.isMutable() && !this.isWriting() && !this.isCommitted()) {
         if (encoding == null) {
            this._encodingFrom = Response.EncodingFrom.NOT_SET;
            if (this._characterEncoding != null) {
               this._characterEncoding = null;
               if (this._mimeType != null) {
                  this._mimeType = this._mimeType.getBaseType();
                  this._contentType = this._mimeType.asString();
                  this._fields.put(this._mimeType.getContentTypeField());
               } else if (this._contentType != null) {
                  this._contentType = MimeTypes.getContentTypeWithoutCharset(this._contentType);
                  this._fields.put(HttpHeader.CONTENT_TYPE, this._contentType);
               }
            }
         } else {
            this._encodingFrom = from;
            this._characterEncoding = HttpGenerator.__STRICT ? encoding : StringUtil.normalizeCharset(encoding);
            if (this._mimeType != null) {
               String var10001 = this._mimeType.getBaseType().asString();
               this._contentType = var10001 + ";charset=" + this._characterEncoding;
               this._mimeType = (MimeTypes.Type)MimeTypes.CACHE.get(this._contentType);
               if (this._mimeType != null && !HttpGenerator.__STRICT) {
                  this._fields.put(this._mimeType.getContentTypeField());
               } else {
                  this._fields.put(HttpHeader.CONTENT_TYPE, this._contentType);
               }
            } else if (this._contentType != null) {
               String var3 = MimeTypes.getContentTypeWithoutCharset(this._contentType);
               this._contentType = var3 + ";charset=" + this._characterEncoding;
               this._fields.put(HttpHeader.CONTENT_TYPE, this._contentType);
            }
         }

      }
   }

   public void setContentType(String contentType) {
      if (!this.isCommitted() && this.isMutable()) {
         if (contentType == null) {
            if (this.isWriting() && this._characterEncoding != null) {
               throw new IllegalSelectorException();
            }

            if (this._locale == null) {
               this._characterEncoding = null;
            }

            this._mimeType = null;
            this._contentType = null;
            this._fields.remove(HttpHeader.CONTENT_TYPE);
         } else {
            this._contentType = contentType;
            this._mimeType = (MimeTypes.Type)MimeTypes.CACHE.get(contentType);
            String charset = MimeTypes.getCharsetFromContentType(contentType);
            if (charset == null && this._mimeType != null && this._mimeType.isCharsetAssumed()) {
               charset = this._mimeType.getCharsetString();
            }

            if (charset == null) {
               switch (this._encodingFrom.ordinal()) {
                  case 0:
                     break;
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                  case 5:
                     this._contentType = contentType + ";charset=" + this._characterEncoding;
                     this._mimeType = (MimeTypes.Type)MimeTypes.CACHE.get(this._contentType);
                     break;
                  default:
                     throw new IllegalStateException(this._encodingFrom.toString());
               }
            } else if (this.isWriting() && !charset.equalsIgnoreCase(this._characterEncoding)) {
               this._contentType = MimeTypes.getContentTypeWithoutCharset(this._contentType);
               if (this._characterEncoding != null && (this._mimeType == null || !this._mimeType.isCharsetAssumed())) {
                  this._contentType = this._contentType + ";charset=" + this._characterEncoding;
               }

               this._mimeType = (MimeTypes.Type)MimeTypes.CACHE.get(this._contentType);
            } else {
               this._characterEncoding = charset;
               this._encodingFrom = Response.EncodingFrom.SET_CONTENT_TYPE;
            }

            if (!HttpGenerator.__STRICT && this._mimeType != null) {
               this._contentType = this._mimeType.asString();
               this._fields.put(this._mimeType.getContentTypeField());
            } else {
               this._fields.put(HttpHeader.CONTENT_TYPE, this._contentType);
            }
         }

      }
   }

   public void setBufferSize(int size) {
      if (this.isCommitted()) {
         throw new IllegalStateException("cannot set buffer size after response is in committed state");
      } else if (this.getContentCount() > 0L) {
         throw new IllegalStateException("cannot set buffer size after response has " + this.getContentCount() + " bytes already written");
      } else {
         if (size < 1) {
            size = 1;
         }

         this._out.setBufferSize(size);
      }
   }

   public int getBufferSize() {
      return this._out.getBufferSize();
   }

   public void flushBuffer() throws IOException {
      if (!this._out.isClosed()) {
         this._out.flush();
      }

   }

   public void reset() {
      this._status = 200;
      this._reason = null;
      this._out.resetBuffer();
      this._outputType = Response.OutputType.NONE;
      this._contentLength = -1L;
      this._contentType = null;
      this._mimeType = null;
      this._characterEncoding = null;
      this._encodingFrom = Response.EncodingFrom.NOT_SET;
      this._trailers = null;
      this._fields.clear();

      for(String value : this._channel.getRequest().getHttpFields().getCSV(HttpHeader.CONNECTION, false)) {
         HttpHeaderValue cb = (HttpHeaderValue)HttpHeaderValue.CACHE.get(value);
         if (cb != null) {
            switch (cb) {
               case CLOSE:
                  this._fields.put(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.toString());
                  break;
               case KEEP_ALIVE:
                  if (HttpVersion.HTTP_1_0.is(this._channel.getRequest().getProtocol())) {
                     this._fields.put(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE.toString());
                  }
                  break;
               case TE:
                  this._fields.put(HttpHeader.CONNECTION, HttpHeaderValue.TE.toString());
            }
         }
      }

      Request request = this.getHttpChannel().getRequest();
      HttpSession session = request.getSession(false);
      if (session != null && session.isNew()) {
         SessionHandler sh = request.getSessionHandler();
         if (sh != null) {
            HttpCookie c = sh.getSessionCookie(session, request.getContextPath(), request.isSecure());
            if (c != null) {
               this.addCookie(c);
            }
         }
      }

   }

   public void resetContent() {
      this._out.resetBuffer();
      this._outputType = Response.OutputType.NONE;
      this._contentLength = -1L;
      this._contentType = null;
      this._mimeType = null;
      this._characterEncoding = null;
      this._encodingFrom = Response.EncodingFrom.NOT_SET;
      Iterator<HttpField> i = this.getHttpFields().iterator();

      while(i.hasNext()) {
         HttpField field = (HttpField)i.next();
         if (field.getHeader() != null) {
            switch (field.getHeader()) {
               case CONTENT_TYPE:
               case CONTENT_LENGTH:
               case CONTENT_ENCODING:
               case CONTENT_LANGUAGE:
               case CONTENT_RANGE:
               case CONTENT_MD5:
               case CONTENT_LOCATION:
               case TRANSFER_ENCODING:
               case CACHE_CONTROL:
               case LAST_MODIFIED:
               case EXPIRES:
               case ETAG:
               case VARY:
                  i.remove();
            }
         }
      }

   }

   public void resetForForward() {
      this.resetBuffer();
      this._outputType = Response.OutputType.NONE;
   }

   public void resetBuffer() {
      this._out.resetBuffer();
      this._out.reopen();
   }

   public Supplier getTrailers() {
      return this._trailers;
   }

   public void setTrailers(Supplier trailers) {
      this._trailers = trailers;
   }

   public Supplier getTrailerFields() {
      return this._trailers instanceof HttpFieldsSupplier ? ((HttpFieldsSupplier)this._trailers).getSupplier() : null;
   }

   public void setTrailerFields(Supplier trailers) {
      if (this.isCommitted()) {
         throw new IllegalStateException("Committed");
      } else if (this.getHttpChannel().getRequest().getHttpVersion().ordinal() <= HttpVersion.HTTP_1_0.ordinal()) {
         throw new IllegalStateException("Trailers not supported");
      } else {
         this._trailers = new HttpFieldsSupplier(trailers);
      }
   }

   protected MetaData.Response newResponseMetaData() {
      return new MetaData.Response(this._channel.getRequest().getHttpVersion(), this.getStatus(), this.getReason(), this._fields, this.getLongContentLength(), this.getTrailers());
   }

   public MetaData.Response getCommittedMetaData() {
      MetaData.Response meta = this._channel.getCommittedMetaData();
      return meta == null ? this.newResponseMetaData() : meta;
   }

   public boolean isCommitted() {
      return this._channel.isSendError() ? true : this._channel.isCommitted();
   }

   public void setLocale(Locale locale) {
      if (!this.isCommitted() && this.isMutable()) {
         if (locale == null) {
            this._locale = null;
            this._fields.remove(HttpHeader.CONTENT_LANGUAGE);
            if (this._encodingFrom == Response.EncodingFrom.SET_LOCALE) {
               this.setCharacterEncoding((String)null, Response.EncodingFrom.NOT_SET);
            }
         } else {
            this._locale = locale;
            this._fields.put(HttpHeader.CONTENT_LANGUAGE, StringUtil.replace(locale.toString(), '_', '-'));
            if (this._outputType != Response.OutputType.NONE) {
               return;
            }

            ContextHandler.Context context = this._channel.getRequest().getContext();
            if (context == null) {
               return;
            }

            String charset = context.getContextHandler().getLocaleEncoding(locale);
            if (!StringUtil.isEmpty(charset) && __localeOverride.contains(this._encodingFrom)) {
               this.setCharacterEncoding(charset, Response.EncodingFrom.SET_LOCALE);
            }
         }

      }
   }

   public Locale getLocale() {
      return this._locale == null ? Locale.getDefault() : this._locale;
   }

   public int getStatus() {
      return this._status;
   }

   public String getReason() {
      return this._reason;
   }

   public HttpFields.Mutable getHttpFields() {
      return this._fields;
   }

   public long getContentCount() {
      return this._out.getWritten();
   }

   public String toString() {
      return String.format("%s %d %s%n%s", this._channel.getRequest().getHttpVersion(), this._status, this._reason == null ? "" : this._reason, this._fields);
   }

   public void putHeaders(HttpContent content, long contentLength, boolean etag) {
      HttpField lm = content.getLastModified();
      if (lm != null) {
         this._fields.put(lm);
      }

      if (contentLength == -2L) {
         this._fields.put(content.getContentLength());
         this._contentLength = content.getContentLengthValue();
      } else if (contentLength > -1L) {
         this._fields.putLongField(HttpHeader.CONTENT_LENGTH, contentLength);
         this._contentLength = contentLength;
      }

      HttpField ct = content.getContentType();
      if (ct != null) {
         if (!__explicitCharset.contains(this._encodingFrom)) {
            this._fields.put(ct);
            this._contentType = ct.getValue();
            this._characterEncoding = content.getCharacterEncoding();
            this._mimeType = content.getMimeType();
         } else {
            this.setContentType(content.getContentTypeValue());
         }
      }

      HttpField ce = content.getContentEncoding();
      if (ce != null) {
         this._fields.put(ce);
      }

      if (etag) {
         HttpField et = content.getETag();
         if (et != null) {
            this._fields.put(et);
         }
      }

   }

   public static void putHeaders(HttpServletResponse response, HttpContent content, long contentLength, boolean etag) {
      long lml = content.getResource().lastModified();
      if (lml >= 0L) {
         response.setDateHeader(HttpHeader.LAST_MODIFIED.asString(), lml);
      }

      if (contentLength == -2L) {
         contentLength = content.getContentLengthValue();
      }

      if (contentLength > -1L) {
         if (contentLength < 2147483647L) {
            response.setContentLength((int)contentLength);
         } else {
            response.setHeader(HttpHeader.CONTENT_LENGTH.asString(), Long.toString(contentLength));
         }
      }

      String ct = content.getContentTypeValue();
      if (ct != null && response.getContentType() == null) {
         response.setContentType(ct);
      }

      String ce = content.getContentEncodingValue();
      if (ce != null) {
         response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), ce);
      }

      if (etag) {
         String et = content.getETagValue();
         if (et != null) {
            response.setHeader(HttpHeader.ETAG.asString(), et);
         }
      }

   }

   public static HttpServletResponse unwrap(ServletResponse servletResponse) {
      if (servletResponse instanceof HttpServletResponseWrapper) {
         return (HttpServletResponseWrapper)servletResponse;
      } else {
         return servletResponse instanceof ServletResponseWrapper ? unwrap(((ServletResponseWrapper)servletResponse).getResponse()) : (HttpServletResponse)servletResponse;
      }
   }

   static {
      __EXPIRES_01JAN1970 = new PreEncodedHttpField(HttpHeader.EXPIRES, DateGenerator.__01Jan1970);
      __localeOverride = EnumSet.of(Response.EncodingFrom.NOT_SET, Response.EncodingFrom.DEFAULT, Response.EncodingFrom.INFERRED, Response.EncodingFrom.SET_LOCALE);
      __explicitCharset = EnumSet.of(Response.EncodingFrom.SET_LOCALE, Response.EncodingFrom.SET_CHARACTER_ENCODING, Response.EncodingFrom.SET_CONTENT_TYPE);
   }

   public static enum OutputType {
      NONE,
      STREAM,
      WRITER;

      // $FF: synthetic method
      private static OutputType[] $values() {
         return new OutputType[]{NONE, STREAM, WRITER};
      }
   }

   private static enum EncodingFrom {
      NOT_SET,
      DEFAULT,
      INFERRED,
      SET_LOCALE,
      SET_CONTENT_TYPE,
      SET_CHARACTER_ENCODING;

      // $FF: synthetic method
      private static EncodingFrom[] $values() {
         return new EncodingFrom[]{NOT_SET, DEFAULT, INFERRED, SET_LOCALE, SET_CONTENT_TYPE, SET_CHARACTER_ENCODING};
      }
   }

   private static class HttpFieldsSupplier implements Supplier {
      private final Supplier _supplier;

      public HttpFieldsSupplier(Supplier trailers) {
         this._supplier = trailers;
      }

      public HttpFields get() {
         Map<String, String> t = (Map)this._supplier.get();
         if (t == null) {
            return null;
         } else {
            HttpFields.Mutable fields = HttpFields.build();

            for(Map.Entry e : t.entrySet()) {
               fields.add((String)e.getKey(), (String)e.getValue());
            }

            return fields.asImmutable();
         }
      }

      public Supplier getSupplier() {
         return this._supplier;
      }
   }
}
