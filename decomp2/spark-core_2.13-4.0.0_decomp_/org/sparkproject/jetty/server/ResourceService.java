package org.sparkproject.jetty.server;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.InvalidPathException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.CompressedContentFormat;
import org.sparkproject.jetty.http.DateParser;
import org.sparkproject.jetty.http.HttpContent;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.http.QuotedCSV;
import org.sparkproject.jetty.http.QuotedQualityCSV;
import org.sparkproject.jetty.io.WriterOutputStream;
import org.sparkproject.jetty.server.resource.HttpContentRangeWriter;
import org.sparkproject.jetty.server.resource.InputStreamRangeWriter;
import org.sparkproject.jetty.server.resource.RangeWriter;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.MultiPartOutputStream;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.thread.Invocable;

public class ResourceService {
   private static final Logger LOG = LoggerFactory.getLogger(ResourceService.class);
   private static final PreEncodedHttpField ACCEPT_RANGES;
   private HttpContent.ContentFactory _contentFactory;
   private WelcomeFactory _welcomeFactory;
   private boolean _acceptRanges = true;
   private boolean _dirAllowed = true;
   private boolean _redirectWelcome = false;
   private CompressedContentFormat[] _precompressedFormats = new CompressedContentFormat[0];
   private String[] _preferredEncodingOrder = new String[0];
   private final Map _preferredEncodingOrderCache = new ConcurrentHashMap();
   private int _encodingCacheSize = 100;
   private boolean _pathInfoOnly = false;
   private boolean _etags = false;
   private HttpField _cacheControl;
   private List _gzipEquivalentFileExtensions;

   public HttpContent.ContentFactory getContentFactory() {
      return this._contentFactory;
   }

   public void setContentFactory(HttpContent.ContentFactory contentFactory) {
      this._contentFactory = contentFactory;
   }

   public WelcomeFactory getWelcomeFactory() {
      return this._welcomeFactory;
   }

   public void setWelcomeFactory(WelcomeFactory welcomeFactory) {
      this._welcomeFactory = welcomeFactory;
   }

   public boolean isAcceptRanges() {
      return this._acceptRanges;
   }

   public void setAcceptRanges(boolean acceptRanges) {
      this._acceptRanges = acceptRanges;
   }

   public boolean isDirAllowed() {
      return this._dirAllowed;
   }

   public void setDirAllowed(boolean dirAllowed) {
      this._dirAllowed = dirAllowed;
   }

   public boolean isRedirectWelcome() {
      return this._redirectWelcome;
   }

   public void setRedirectWelcome(boolean redirectWelcome) {
      this._redirectWelcome = redirectWelcome;
   }

   public CompressedContentFormat[] getPrecompressedFormats() {
      return this._precompressedFormats;
   }

   public void setPrecompressedFormats(CompressedContentFormat[] precompressedFormats) {
      this._precompressedFormats = precompressedFormats;
      this._preferredEncodingOrder = (String[])Arrays.stream(this._precompressedFormats).map((f) -> f.getEncoding()).toArray((x$0) -> new String[x$0]);
   }

   public void setEncodingCacheSize(int encodingCacheSize) {
      this._encodingCacheSize = encodingCacheSize;
   }

   public int getEncodingCacheSize() {
      return this._encodingCacheSize;
   }

   public boolean isPathInfoOnly() {
      return this._pathInfoOnly;
   }

   public void setPathInfoOnly(boolean pathInfoOnly) {
      this._pathInfoOnly = pathInfoOnly;
   }

   public boolean isEtags() {
      return this._etags;
   }

   public void setEtags(boolean etags) {
      this._etags = etags;
   }

   public HttpField getCacheControl() {
      return this._cacheControl;
   }

   public void setCacheControl(HttpField cacheControl) {
      if (cacheControl == null) {
         this._cacheControl = null;
      }

      if (cacheControl.getHeader() != HttpHeader.CACHE_CONTROL) {
         throw new IllegalArgumentException("!Cache-Control");
      } else {
         this._cacheControl = (HttpField)(cacheControl instanceof PreEncodedHttpField ? cacheControl : new PreEncodedHttpField(cacheControl.getHeader(), cacheControl.getValue()));
      }
   }

   public List getGzipEquivalentFileExtensions() {
      return this._gzipEquivalentFileExtensions;
   }

   public void setGzipEquivalentFileExtensions(List gzipEquivalentFileExtensions) {
      this._gzipEquivalentFileExtensions = gzipEquivalentFileExtensions;
   }

   public boolean doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String servletPath = null;
      String pathInfo = null;
      Enumeration<String> reqRanges = null;
      boolean included = request.getAttribute("jakarta.servlet.include.request_uri") != null;
      if (included) {
         servletPath = this._pathInfoOnly ? "/" : (String)request.getAttribute("jakarta.servlet.include.servlet_path");
         pathInfo = (String)request.getAttribute("jakarta.servlet.include.path_info");
         if (servletPath == null) {
            servletPath = request.getServletPath();
            pathInfo = request.getPathInfo();
         }
      } else {
         servletPath = this._pathInfoOnly ? "/" : request.getServletPath();
         pathInfo = request.getPathInfo();
         reqRanges = request.getHeaders(HttpHeader.RANGE.asString());
         if (!this.hasDefinedRange(reqRanges)) {
            reqRanges = null;
         }
      }

      String pathInContext = URIUtil.addPaths(servletPath, pathInfo);
      boolean endsWithSlash = (pathInfo == null ? (this._pathInfoOnly ? "" : servletPath) : pathInfo).endsWith("/");
      boolean checkPrecompressedVariants = this._precompressedFormats.length > 0 && !endsWithSlash && !included && reqRanges == null;
      HttpContent content = null;
      boolean releaseContent = true;

      boolean q;
      try {
         content = this._contentFactory.getContent(pathInContext, response.getBufferSize());
         if (LOG.isDebugEnabled()) {
            LOG.debug("content={}", content);
         }

         if (content != null && content.getResource().exists()) {
            if (content.getResource().isDirectory()) {
               this.sendWelcome(content, pathInContext, endsWithSlash, included, request, response);
               q = true;
               return q;
            }

            if (!included && endsWithSlash && pathInContext.length() > 1) {
               String q = request.getQueryString();
               pathInContext = pathInContext.substring(0, pathInContext.length() - 1);
               if (q != null && q.length() != 0) {
                  pathInContext = pathInContext + "?" + q;
               }

               response.sendRedirect(response.encodeRedirectURL(URIUtil.addPaths(request.getContextPath(), pathInContext)));
               boolean var31 = true;
               return var31;
            }

            if (!included && !this.passConditionalHeaders(request, response, content)) {
               q = true;
               return q;
            }

            Map<CompressedContentFormat, ? extends HttpContent> precompressedContents = checkPrecompressedVariants ? content.getPrecompressedContents() : null;
            if (precompressedContents != null && precompressedContents.size() > 0) {
               response.addHeader(HttpHeader.VARY.asString(), HttpHeader.ACCEPT_ENCODING.asString());
               List<String> preferredEncodings = this.getPreferredEncodingOrder(request);
               CompressedContentFormat precompressedContentEncoding = this.getBestPrecompressedContent(preferredEncodings, precompressedContents.keySet());
               if (precompressedContentEncoding != null) {
                  HttpContent precompressedContent = (HttpContent)precompressedContents.get(precompressedContentEncoding);
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("precompressed={}", precompressedContent);
                  }

                  content = precompressedContent;
                  response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), precompressedContentEncoding.getEncoding());
               }
            }

            if (this.isGzippedContent(pathInContext)) {
               response.setHeader(HttpHeader.CONTENT_ENCODING.asString(), "gzip");
            }

            releaseContent = this.sendData(request, response, included, content, reqRanges);
            return true;
         }

         if (included) {
            throw new FileNotFoundException("!" + pathInContext);
         }

         this.notFound(request, response);
         q = response.isCommitted();
      } catch (InvalidPathException e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("InvalidPathException for pathInContext: {}", pathInContext, e);
         }

         if (included) {
            throw new FileNotFoundException("!" + pathInContext);
         }

         this.notFound(request, response);
         boolean preferredEncodings = response.isCommitted();
         return preferredEncodings;
      } catch (IllegalArgumentException e) {
         LOG.warn("Failed to serve resource: {}", pathInContext, e);
         if (!response.isCommitted()) {
            response.sendError(500, e.getMessage());
         }

         return true;
      } finally {
         if (releaseContent && content != null) {
            content.release();
         }

      }

      return q;
   }

   private List getPreferredEncodingOrder(HttpServletRequest request) {
      Enumeration<String> headers = request.getHeaders(HttpHeader.ACCEPT_ENCODING.asString());
      if (!headers.hasMoreElements()) {
         return Collections.emptyList();
      } else {
         String key = (String)headers.nextElement();
         if (headers.hasMoreElements()) {
            StringBuilder sb = new StringBuilder(key.length() * 2);
            sb.append(key);

            do {
               sb.append(',').append((String)headers.nextElement());
            } while(headers.hasMoreElements());

            key = sb.toString();
         }

         List<String> values = (List)this._preferredEncodingOrderCache.get(key);
         if (values == null) {
            QuotedQualityCSV encodingQualityCSV = new QuotedQualityCSV(this._preferredEncodingOrder);
            encodingQualityCSV.addValue(key);
            values = encodingQualityCSV.getValues();
            if (this._preferredEncodingOrderCache.size() > this._encodingCacheSize) {
               this._preferredEncodingOrderCache.clear();
            }

            this._preferredEncodingOrderCache.put(key, values);
         }

         return values;
      }
   }

   private CompressedContentFormat getBestPrecompressedContent(List preferredEncodings, Collection availableFormats) {
      if (availableFormats.isEmpty()) {
         return null;
      } else {
         for(String encoding : preferredEncodings) {
            for(CompressedContentFormat format : availableFormats) {
               if (format.getEncoding().equals(encoding)) {
                  return format;
               }
            }

            if ("*".equals(encoding)) {
               return (CompressedContentFormat)availableFormats.iterator().next();
            }

            if (HttpHeaderValue.IDENTITY.asString().equals(encoding)) {
               return null;
            }
         }

         return null;
      }
   }

   protected void sendWelcome(HttpContent content, String pathInContext, boolean endsWithSlash, boolean included, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (!endsWithSlash) {
         StringBuilder buf = new StringBuilder(request.getRequestURI());
         int param = buf.lastIndexOf(";");
         if (param >= 0 && buf.lastIndexOf("/", param) <= 0) {
            buf.insert(param, '/');
         } else {
            buf.append('/');
         }

         String q = request.getQueryString();
         if (q != null && q.length() != 0) {
            buf.append('?');
            buf.append(q);
         }

         response.setContentLength(0);
         response.sendRedirect(response.encodeRedirectURL(buf.toString()));
      } else {
         String welcome = this._welcomeFactory == null ? null : this._welcomeFactory.getWelcomeFile(pathInContext);
         if (welcome != null) {
            String servletPath = included ? (String)request.getAttribute("jakarta.servlet.include.servlet_path") : request.getServletPath();
            if (this._pathInfoOnly) {
               welcome = URIUtil.addPaths(servletPath, welcome);
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("welcome={}", welcome);
            }

            ServletContext context = request.getServletContext();
            if (!this._redirectWelcome && context != null) {
               RequestDispatcher dispatcher = context.getRequestDispatcher(URIUtil.encodePath(welcome));
               if (dispatcher != null) {
                  if (included) {
                     dispatcher.include(request, response);
                  } else {
                     request.setAttribute("org.sparkproject.jetty.server.welcome", welcome);
                     dispatcher.forward(request, response);
                  }
               }

            } else {
               response.setContentLength(0);
               String uri = URIUtil.encodePath(URIUtil.addPaths(request.getContextPath(), welcome));
               String q = request.getQueryString();
               if (q != null && !q.isEmpty()) {
                  uri = uri + "?" + q;
               }

               response.sendRedirect(response.encodeRedirectURL(uri));
            }
         } else {
            if (included || this.passConditionalHeaders(request, response, content)) {
               this.sendDirectory(request, response, content.getResource(), pathInContext);
            }

         }
      }
   }

   protected boolean isGzippedContent(String path) {
      if (path != null && this._gzipEquivalentFileExtensions != null) {
         for(String suffix : this._gzipEquivalentFileExtensions) {
            if (path.endsWith(suffix)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean hasDefinedRange(Enumeration reqRanges) {
      return reqRanges != null && reqRanges.hasMoreElements();
   }

   protected void notFound(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.sendError(404);
   }

   protected void sendStatus(HttpServletResponse response, int status, Supplier etag) throws IOException {
      response.setStatus(status);
      if (this._etags && etag != null) {
         response.setHeader(HttpHeader.ETAG.asString(), (String)etag.get());
      }

      response.flushBuffer();
   }

   protected boolean passConditionalHeaders(HttpServletRequest request, HttpServletResponse response, HttpContent content) throws IOException {
      try {
         String ifm = null;
         String ifnm = null;
         String ifms = null;
         String ifums = null;
         if (request instanceof Request) {
            for(HttpField field : ((Request)request).getHttpFields()) {
               if (field.getHeader() != null) {
                  switch (field.getHeader()) {
                     case IF_MATCH:
                        ifm = field.getValue();
                        break;
                     case IF_NONE_MATCH:
                        ifnm = field.getValue();
                        break;
                     case IF_MODIFIED_SINCE:
                        ifms = field.getValue();
                        break;
                     case IF_UNMODIFIED_SINCE:
                        ifums = field.getValue();
                  }
               }
            }
         } else {
            ifm = request.getHeader(HttpHeader.IF_MATCH.asString());
            ifnm = request.getHeader(HttpHeader.IF_NONE_MATCH.asString());
            ifms = request.getHeader(HttpHeader.IF_MODIFIED_SINCE.asString());
            ifums = request.getHeader(HttpHeader.IF_UNMODIFIED_SINCE.asString());
         }

         if (this._etags) {
            String etag = content.getETagValue();
            if (ifm != null) {
               boolean match = false;
               if (etag != null) {
                  for(String etagWithSuffix : new QuotedCSV(true, new String[]{ifm})) {
                     if (CompressedContentFormat.tagEquals(etag, etagWithSuffix)) {
                        match = true;
                        break;
                     }
                  }
               }

               if (!match) {
                  this.sendStatus(response, 412, (Supplier)null);
                  return false;
               }
            }

            if (ifnm != null && etag != null) {
               if (CompressedContentFormat.tagEquals(etag, ifnm) && ifnm.indexOf(44) < 0) {
                  Objects.requireNonNull(ifnm);
                  this.sendStatus(response, 304, ifnm::toString);
                  return false;
               }

               for(String tag : new QuotedCSV(true, new String[]{ifnm})) {
                  if (CompressedContentFormat.tagEquals(etag, tag)) {
                     Objects.requireNonNull(tag);
                     this.sendStatus(response, 304, tag::toString);
                     return false;
                  }
               }

               return true;
            }
         }

         if (ifms != null && ifnm == null) {
            String mdlm = content.getLastModifiedValue();
            if (ifms.equals(mdlm)) {
               Objects.requireNonNull(content);
               this.sendStatus(response, 304, content::getETagValue);
               return false;
            }

            long ifmsl = DateParser.parseDate(ifms);
            if (ifmsl != -1L) {
               long lm = content.getResource().lastModified();
               if (lm != -1L && lm / 1000L <= ifmsl / 1000L) {
                  Objects.requireNonNull(content);
                  this.sendStatus(response, 304, content::getETagValue);
                  return false;
               }
            }
         }

         if (ifums != null && ifm == null) {
            long ifumsl = DateParser.parseDate(ifums);
            if (ifumsl != -1L) {
               long lm = content.getResource().lastModified();
               if (lm != -1L && lm / 1000L > ifumsl / 1000L) {
                  response.sendError(412);
                  return false;
               }
            }
         }

         return true;
      } catch (IllegalArgumentException var13) {
         if (!response.isCommitted()) {
            response.sendError(400, var13.getMessage());
         }

         throw var13;
      }
   }

   protected void sendDirectory(HttpServletRequest request, HttpServletResponse response, Resource resource, String pathInContext) throws IOException {
      if (!this._dirAllowed) {
         response.sendError(403);
      } else {
         byte[] data = null;
         String base = URIUtil.addEncodedPaths(request.getRequestURI(), "/");
         String dir = resource.getListHTML(base, pathInContext.length() > 1, request.getQueryString());
         if (dir == null) {
            response.sendError(403, "No directory");
         } else {
            data = dir.getBytes(StandardCharsets.UTF_8);
            response.setContentType("text/html;charset=utf-8");
            response.setContentLength(data.length);
            response.getOutputStream().write(data);
         }
      }
   }

   protected boolean sendData(HttpServletRequest request, HttpServletResponse response, boolean include, final HttpContent content, Enumeration reqRanges) throws IOException {
      long content_length = content.getContentLengthValue();

      OutputStream out;
      boolean written;
      try {
         out = response.getOutputStream();
         written = out instanceof HttpOutput ? ((HttpOutput)out).isWritten() : true;
      } catch (IllegalStateException var25) {
         out = new WriterOutputStream(response.getWriter());
         written = true;
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug(String.format("sendData content=%s out=%s async=%b", content, out, request.isAsyncSupported()));
      }

      if (reqRanges != null && reqRanges.hasMoreElements() && content_length >= 0L) {
         List<InclusiveByteRange> ranges = InclusiveByteRange.satisfiableRanges(reqRanges, content_length);
         if (ranges == null || ranges.size() == 0) {
            this.putHeaders(response, content, -2L);
            response.setHeader(HttpHeader.CONTENT_RANGE.asString(), InclusiveByteRange.to416HeaderRangeString(content_length));
            this.sendStatus(response, 416, (Supplier)null);
            return true;
         }

         if (ranges.size() == 1) {
            InclusiveByteRange singleSatisfiableRange = (InclusiveByteRange)ranges.iterator().next();
            long singleLength = singleSatisfiableRange.getSize();
            this.putHeaders(response, content, singleLength);
            response.setStatus(206);
            if (!response.containsHeader(HttpHeader.DATE.asString())) {
               response.addDateHeader(HttpHeader.DATE.asString(), System.currentTimeMillis());
            }

            response.setHeader(HttpHeader.CONTENT_RANGE.asString(), singleSatisfiableRange.toHeaderRangeString(content_length));
            writeContent(content, out, singleSatisfiableRange.getFirst(), singleLength);
            return true;
         }

         this.putHeaders(response, content, -1L);
         String mimetype = content.getContentTypeValue();
         if (mimetype == null) {
            LOG.warn("Unknown mimetype for {}", request.getRequestURI());
         }

         response.setStatus(206);
         if (!response.containsHeader(HttpHeader.DATE.asString())) {
            response.addDateHeader(HttpHeader.DATE.asString(), System.currentTimeMillis());
         }

         String ctp;
         if (request.getHeader(HttpHeader.REQUEST_RANGE.asString()) != null) {
            ctp = "multipart/x-byteranges; boundary=";
         } else {
            ctp = "multipart/byteranges; boundary=";
         }

         MultiPartOutputStream multi = new MultiPartOutputStream(out);
         response.setContentType(ctp + multi.getBoundary());
         int length = 0;
         String[] header = new String[ranges.size()];
         int i = 0;
         int CRLF = "\r\n".length();
         int DASHDASH = "--".length();
         int BOUNDARY = multi.getBoundary().length();
         int FIELD_SEP = ": ".length();

         for(InclusiveByteRange ibr : ranges) {
            header[i] = ibr.toHeaderRangeString(content_length);
            if (i > 0) {
               length += CRLF;
            }

            length += DASHDASH + BOUNDARY + CRLF;
            if (mimetype != null) {
               length += HttpHeader.CONTENT_TYPE.asString().length() + FIELD_SEP + mimetype.length() + CRLF;
            }

            length += HttpHeader.CONTENT_RANGE.asString().length() + FIELD_SEP + header[i].length() + CRLF;
            length += CRLF;
            length = (int)((long)length + ibr.getSize());
            ++i;
         }

         length += CRLF + DASHDASH + BOUNDARY + DASHDASH + CRLF;
         response.setContentLength(length);
         RangeWriter rangeWriter = HttpContentRangeWriter.newRangeWriter(content);

         try {
            i = 0;

            for(InclusiveByteRange ibr : ranges) {
               String[] var10002 = new String[1];
               String var10005 = String.valueOf(HttpHeader.CONTENT_RANGE);
               var10002[0] = var10005 + ": " + header[i];
               multi.startPart(mimetype, var10002);
               rangeWriter.writeTo(multi, ibr.getFirst(), ibr.getSize());
               ++i;
            }
         } catch (Throwable var26) {
            if (rangeWriter != null) {
               try {
                  rangeWriter.close();
               } catch (Throwable var24) {
                  var26.addSuppressed(var24);
               }
            }

            throw var26;
         }

         if (rangeWriter != null) {
            rangeWriter.close();
         }

         multi.close();
      } else if (include) {
         writeContent(content, out, 0L, content_length);
      } else if (written) {
         this.putHeaders(response, content, -1L);
         writeContent(content, out, 0L, content_length);
      } else {
         this.putHeaders(response, content, -2L);
         if (request.isAsyncSupported()) {
            final AsyncContext context = request.startAsync();
            context.setTimeout(0L);
            ((HttpOutput)out).sendContent(content, new Callback() {
               public void succeeded() {
                  context.complete();
                  content.release();
               }

               public void failed(Throwable x) {
                  String msg = "Failed to send content";
                  if (x instanceof IOException) {
                     ResourceService.LOG.debug(msg, x);
                  } else {
                     ResourceService.LOG.warn(msg, x);
                  }

                  context.complete();
                  content.release();
               }

               public Invocable.InvocationType getInvocationType() {
                  return Invocable.InvocationType.NON_BLOCKING;
               }

               public String toString() {
                  return String.format("ResourceService@%x$CB", ResourceService.this.hashCode());
               }
            });
            return false;
         }

         ((HttpOutput)out).sendContent(content);
      }

      return true;
   }

   private static void writeContent(HttpContent content, OutputStream out, long start, long contentLength) throws IOException {
      if (start == 0L && content.getResource().length() == contentLength) {
         ByteBuffer buffer = content.getIndirectBuffer();
         if (buffer != null) {
            BufferUtil.writeTo(buffer, out);
         } else {
            InputStream input = content.getResource().getInputStream();

            try {
               IO.copy(input, out);
            } catch (Throwable var13) {
               if (input != null) {
                  try {
                     input.close();
                  } catch (Throwable var11) {
                     var13.addSuppressed(var11);
                  }
               }

               throw var13;
            }

            if (input != null) {
               input.close();
            }

         }
      } else {
         InputStreamRangeWriter rangeWriter = new InputStreamRangeWriter(() -> content.getInputStream());

         try {
            rangeWriter.writeTo(out, start, contentLength);
         } catch (Throwable var12) {
            try {
               rangeWriter.close();
            } catch (Throwable var10) {
               var12.addSuppressed(var10);
            }

            throw var12;
         }

         rangeWriter.close();
      }
   }

   protected void putHeaders(HttpServletResponse response, HttpContent content, long contentLength) {
      if (response instanceof Response) {
         Response r = (Response)response;
         r.putHeaders(content, contentLength, this._etags);
         HttpFields.Mutable fields = r.getHttpFields();
         if (this._acceptRanges && !fields.contains(HttpHeader.ACCEPT_RANGES)) {
            fields.add((HttpField)ACCEPT_RANGES);
         }

         if (this._cacheControl != null && !fields.contains(HttpHeader.CACHE_CONTROL)) {
            fields.add(this._cacheControl);
         }
      } else {
         Response.putHeaders(response, content, contentLength, this._etags);
         if (this._acceptRanges && !response.containsHeader(HttpHeader.ACCEPT_RANGES.asString())) {
            response.setHeader(ACCEPT_RANGES.getName(), ACCEPT_RANGES.getValue());
         }

         if (this._cacheControl != null && !response.containsHeader(HttpHeader.CACHE_CONTROL.asString())) {
            response.setHeader(this._cacheControl.getName(), this._cacheControl.getValue());
         }
      }

   }

   static {
      ACCEPT_RANGES = new PreEncodedHttpField(HttpHeader.ACCEPT_RANGES, "bytes");
   }

   public interface WelcomeFactory {
      String getWelcomeFile(String var1) throws IOException;
   }
}
