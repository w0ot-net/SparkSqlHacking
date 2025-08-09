package org.sparkproject.jetty.server.handler.gzip;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.CompressedContentFormat;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.http.pathmap.PathSpecSet;
import org.sparkproject.jetty.server.HttpOutput;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.handler.HandlerWrapper;
import org.sparkproject.jetty.util.AsciiLowerCaseSet;
import org.sparkproject.jetty.util.IncludeExclude;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.compression.CompressionPool;
import org.sparkproject.jetty.util.compression.DeflaterPool;
import org.sparkproject.jetty.util.compression.InflaterPool;

public class GzipHandler extends HandlerWrapper implements GzipFactory {
   public static final EnumSet ETAG_HEADERS;
   public static final String GZIP_HANDLER_ETAGS = "o.e.j.s.h.gzip.GzipHandler.etag";
   public static final String GZIP = "gzip";
   public static final String DEFLATE = "deflate";
   public static final int DEFAULT_MIN_GZIP_SIZE = 32;
   public static final int BREAK_EVEN_GZIP_SIZE = 23;
   private static final Logger LOG;
   private static final HttpField X_CE_GZIP;
   private static final Pattern COMMA_GZIP;
   private InflaterPool _inflaterPool;
   private DeflaterPool _deflaterPool;
   private int _minGzipSize = 32;
   private boolean _syncFlush = false;
   private int _inflateBufferSize = -1;
   private EnumSet _dispatchers;
   private final IncludeExclude _methods;
   private final IncludeExclude _paths;
   private final IncludeExclude _inflatePaths;
   private final IncludeExclude _mimeTypes;
   private HttpField _vary;

   public GzipHandler() {
      this._dispatchers = EnumSet.of(DispatcherType.REQUEST);
      this._methods = new IncludeExclude();
      this._paths = new IncludeExclude(PathSpecSet.class);
      this._inflatePaths = new IncludeExclude(PathSpecSet.class);
      this._mimeTypes = new IncludeExclude(AsciiLowerCaseSet.class);
      this._vary = GzipHttpOutputInterceptor.VARY_ACCEPT_ENCODING;
      this._methods.include(HttpMethod.GET.asString());
      this._methods.include(HttpMethod.POST.asString());

      for(String type : MimeTypes.getKnownMimeTypes()) {
         if ("image/svg+xml".equals(type)) {
            this._paths.exclude("*.svgz");
         } else if (type.startsWith("image/") || type.startsWith("audio/") || type.startsWith("video/")) {
            this._mimeTypes.exclude(type);
         }
      }

      this._mimeTypes.exclude("application/compress");
      this._mimeTypes.exclude("application/zip");
      this._mimeTypes.exclude("application/gzip");
      this._mimeTypes.exclude("application/bzip2");
      this._mimeTypes.exclude("application/brotli");
      this._mimeTypes.exclude("application/x-xz");
      this._mimeTypes.exclude("application/x-rar-compressed");
      this._mimeTypes.exclude("text/event-stream");
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} mime types {}", this, this._mimeTypes);
      }

   }

   protected void doStart() throws Exception {
      Server server = this.getServer();
      if (this._inflaterPool == null) {
         this._inflaterPool = InflaterPool.ensurePool(server);
         this.addBean(this._inflaterPool);
      }

      if (this._deflaterPool == null) {
         this._deflaterPool = DeflaterPool.ensurePool(server);
         this.addBean(this._deflaterPool);
      }

      super.doStart();
   }

   protected void doStop() throws Exception {
      super.doStop();
      this.removeBean(this._inflaterPool);
      this._inflaterPool = null;
      this.removeBean(this._deflaterPool);
      this._deflaterPool = null;
   }

   public HttpField getVary() {
      return this._vary;
   }

   public void setVary(HttpField vary) {
      if (this.isRunning()) {
         throw new IllegalStateException(this.getState());
      } else {
         if (vary != null && !(vary instanceof PreEncodedHttpField)) {
            this._vary = new PreEncodedHttpField(vary.getHeader(), vary.getName(), vary.getValue());
         } else {
            this._vary = vary;
         }

      }
   }

   public void addExcludedMethods(String... methods) {
      for(String m : methods) {
         this._methods.exclude(m);
      }

   }

   public EnumSet getDispatcherTypes() {
      return this._dispatchers;
   }

   public void setDispatcherTypes(EnumSet dispatchers) {
      this._dispatchers = dispatchers;
   }

   public void setDispatcherTypes(DispatcherType... dispatchers) {
      this._dispatchers = EnumSet.copyOf(Arrays.asList(dispatchers));
   }

   public void addExcludedMimeTypes(String... types) {
      for(String t : types) {
         this._mimeTypes.exclude(StringUtil.csvSplit(t));
      }

   }

   public void addExcludedPaths(String... pathspecs) {
      for(String p : pathspecs) {
         this._paths.exclude(StringUtil.csvSplit(p));
      }

   }

   public void addExcludedInflationPaths(String... pathspecs) {
      for(String p : pathspecs) {
         this._inflatePaths.exclude(StringUtil.csvSplit(p));
      }

   }

   public void addIncludedMethods(String... methods) {
      for(String m : methods) {
         this._methods.include(m);
      }

   }

   public boolean isSyncFlush() {
      return this._syncFlush;
   }

   public void setSyncFlush(boolean syncFlush) {
      this._syncFlush = syncFlush;
   }

   public void addIncludedMimeTypes(String... types) {
      for(String t : types) {
         this._mimeTypes.include(StringUtil.csvSplit(t));
      }

   }

   public void addIncludedPaths(String... pathspecs) {
      for(String p : pathspecs) {
         this._paths.include(StringUtil.csvSplit(p));
      }

   }

   public void addIncludedInflationPaths(String... pathspecs) {
      for(String p : pathspecs) {
         this._inflatePaths.include(StringUtil.csvSplit(p));
      }

   }

   public CompressionPool.Entry getDeflaterEntry(Request request, long contentLength) {
      if (contentLength >= 0L && contentLength < (long)this._minGzipSize) {
         LOG.debug("{} excluded minGzipSize {}", this, request);
         return null;
      } else if (!request.getHttpFields().contains(HttpHeader.ACCEPT_ENCODING, "gzip")) {
         LOG.debug("{} excluded not gzip accept {}", this, request);
         return null;
      } else {
         return this._deflaterPool.acquire();
      }
   }

   public String[] getExcludedMethods() {
      Set<String> excluded = this._methods.getExcluded();
      return (String[])excluded.toArray(new String[0]);
   }

   public String[] getExcludedMimeTypes() {
      Set<String> excluded = this._mimeTypes.getExcluded();
      return (String[])excluded.toArray(new String[0]);
   }

   public String[] getExcludedPaths() {
      Set<String> excluded = this._paths.getExcluded();
      return (String[])excluded.toArray(new String[0]);
   }

   public String[] getExcludedInflationPaths() {
      Set<String> excluded = this._inflatePaths.getExcluded();
      return (String[])excluded.toArray(new String[0]);
   }

   public String[] getIncludedMethods() {
      Set<String> includes = this._methods.getIncluded();
      return (String[])includes.toArray(new String[0]);
   }

   public String[] getIncludedMimeTypes() {
      Set<String> includes = this._mimeTypes.getIncluded();
      return (String[])includes.toArray(new String[0]);
   }

   public String[] getIncludedPaths() {
      Set<String> includes = this._paths.getIncluded();
      return (String[])includes.toArray(new String[0]);
   }

   public String[] getIncludedInflationPaths() {
      Set<String> includes = this._inflatePaths.getIncluded();
      return (String[])includes.toArray(new String[0]);
   }

   public int getMinGzipSize() {
      return this._minGzipSize;
   }

   protected HttpField getVaryField() {
      return this._vary;
   }

   public int getInflateBufferSize() {
      return this._inflateBufferSize;
   }

   public void setInflateBufferSize(int size) {
      this._inflateBufferSize = size;
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (baseRequest.isHandled()) {
         super.handle(target, baseRequest, request, response);
      } else {
         ServletContext context = baseRequest.getServletContext();
         String path = baseRequest.getPathInContext();
         LOG.debug("{} handle {} in {}", new Object[]{this, baseRequest, context});
         if (!this._dispatchers.contains(baseRequest.getDispatcherType())) {
            LOG.debug("{} excluded by dispatcherType {}", this, baseRequest.getDispatcherType());
            super.handle(target, baseRequest, request, response);
         } else {
            HttpFields httpFields = baseRequest.getHttpFields();
            boolean inflated = this._inflateBufferSize > 0 && httpFields.contains(HttpHeader.CONTENT_ENCODING, "gzip") && this.isPathInflatable(path);
            if (inflated) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("{} inflate {}", this, request);
               }

               GzipHttpInputInterceptor gzipHttpInputInterceptor = new GzipHttpInputInterceptor(this._inflaterPool, baseRequest.getHttpChannel().getByteBufferPool(), this._inflateBufferSize, baseRequest.getHttpChannel().isUseInputDirectByteBuffers());
               baseRequest.getHttpInput().addInterceptor(gzipHttpInputInterceptor);
            }

            if (response.isCommitted()) {
               super.handle(target, baseRequest, request, response);
            } else {
               HttpOutput out = baseRequest.getResponse().getHttpOutput();
               HttpOutput.Interceptor interceptor = out.getInterceptor();

               boolean alreadyGzipped;
               for(alreadyGzipped = false; interceptor != null; interceptor = interceptor.getNextInterceptor()) {
                  if (interceptor instanceof GzipHttpOutputInterceptor) {
                     alreadyGzipped = true;
                     break;
                  }
               }

               if (inflated || httpFields.contains(ETAG_HEADERS)) {
                  HttpFields.Mutable newFields = HttpFields.build(httpFields.size() + 1);

                  for(HttpField field : httpFields) {
                     if (field.getHeader() == null) {
                        newFields.add(field);
                     } else {
                        switch (field.getHeader()) {
                           case IF_MATCH:
                           case IF_NONE_MATCH:
                              String etags = field.getValue();
                              String etagsNoSuffix = CompressedContentFormat.GZIP.stripSuffixes(etags);
                              if (etagsNoSuffix.equals(etags)) {
                                 newFields.add(field);
                              } else {
                                 newFields.add(new HttpField(field.getHeader(), etagsNoSuffix));
                                 baseRequest.setAttribute("o.e.j.s.h.gzip.GzipHandler.etag", etags);
                              }
                              break;
                           case CONTENT_LENGTH:
                              newFields.add(inflated ? new HttpField("X-Content-Length", field.getValue()) : field);
                              break;
                           case CONTENT_ENCODING:
                              if (inflated) {
                                 if (field.getValue().equalsIgnoreCase("gzip")) {
                                    newFields.add(X_CE_GZIP);
                                 } else if (COMMA_GZIP.matcher(field.getValue()).matches()) {
                                    String v = field.getValue();
                                    v = v.substring(0, v.lastIndexOf(44));
                                    newFields.add(X_CE_GZIP);
                                    newFields.add(new HttpField(HttpHeader.CONTENT_ENCODING, v));
                                 }
                              } else {
                                 newFields.add(field);
                              }
                              break;
                           default:
                              newFields.add(field);
                        }
                     }
                  }

                  baseRequest.setHttpFields(newFields);
               }

               if (alreadyGzipped) {
                  LOG.debug("{} already intercepting {}", this, request);
                  super.handle(target, baseRequest, request, response);
               } else if (!this._methods.test(baseRequest.getMethod())) {
                  LOG.debug("{} excluded by method {}", this, request);
                  super.handle(target, baseRequest, request, response);
               } else if (!this.isPathGzipable(path)) {
                  LOG.debug("{} excluded by path {}", this, request);
                  super.handle(target, baseRequest, request, response);
               } else {
                  String mimeType = context == null ? MimeTypes.getDefaultMimeByExtension(path) : context.getMimeType(path);
                  if (mimeType != null) {
                     mimeType = HttpField.valueParameters(mimeType, (Map)null);
                     if (!this.isMimeTypeGzipable(mimeType)) {
                        LOG.debug("{} excluded by path suffix mime type {}", this, request);
                        super.handle(target, baseRequest, request, response);
                        return;
                     }
                  }

                  HttpOutput.Interceptor origInterceptor = out.getInterceptor();

                  try {
                     out.setInterceptor(new GzipHttpOutputInterceptor(this, this.getVaryField(), baseRequest.getHttpChannel(), origInterceptor, this.isSyncFlush()));
                     super.handle(target, baseRequest, request, response);
                  } finally {
                     if (!baseRequest.isHandled() && !baseRequest.isAsyncStarted()) {
                        out.setInterceptor(origInterceptor);
                     }

                  }

               }
            }
         }
      }
   }

   public boolean isMimeTypeGzipable(String mimetype) {
      return this._mimeTypes.test(mimetype);
   }

   protected boolean isPathGzipable(String requestURI) {
      return requestURI == null ? true : this._paths.test(requestURI);
   }

   protected boolean isPathInflatable(String requestURI) {
      return requestURI == null ? true : this._inflatePaths.test(requestURI);
   }

   public void setExcludedMethods(String... methods) {
      this._methods.getExcluded().clear();
      this._methods.exclude(methods);
   }

   public void setExcludedMimeTypes(String... types) {
      this._mimeTypes.getExcluded().clear();
      this._mimeTypes.exclude(types);
   }

   public void setExcludedMimeTypesList(String csvTypes) {
      this.setExcludedMimeTypes(StringUtil.csvSplit(csvTypes));
   }

   public void setExcludedPaths(String... pathspecs) {
      this._paths.getExcluded().clear();
      this._paths.exclude(pathspecs);
   }

   public void setExcludedInflatePaths(String... pathspecs) {
      this._inflatePaths.getExcluded().clear();
      this._inflatePaths.exclude(pathspecs);
   }

   public void setDispatcherTypes(String... dispatchers) {
      this._dispatchers = EnumSet.copyOf((Collection)Stream.of(dispatchers).flatMap((s) -> Stream.of(StringUtil.csvSplit(s))).map(DispatcherType::valueOf).collect(Collectors.toSet()));
   }

   public void setIncludedMethods(String... methods) {
      this._methods.getIncluded().clear();
      this._methods.include(methods);
   }

   public void setIncludedMimeTypes(String... types) {
      this._mimeTypes.getIncluded().clear();
      this._mimeTypes.include(types);
   }

   public void setIncludedMimeTypesList(String csvTypes) {
      this.setIncludedMimeTypes(StringUtil.csvSplit(csvTypes));
   }

   public void setIncludedPaths(String... pathspecs) {
      this._paths.getIncluded().clear();
      this._paths.include(pathspecs);
   }

   public void setIncludedInflatePaths(String... pathspecs) {
      this._inflatePaths.getIncluded().clear();
      this._inflatePaths.include(pathspecs);
   }

   public void setMinGzipSize(int minGzipSize) {
      if (minGzipSize < 23) {
         LOG.warn("minGzipSize of {} is inefficient for short content, break even is size {}", minGzipSize, 23);
      }

      this._minGzipSize = Math.max(0, minGzipSize);
   }

   public void setIncludedMethodList(String csvMethods) {
      this.setIncludedMethods(StringUtil.csvSplit(csvMethods));
   }

   public String getIncludedMethodList() {
      return String.join(",", this.getIncludedMethods());
   }

   public void setExcludedMethodList(String csvMethods) {
      this.setExcludedMethods(StringUtil.csvSplit(csvMethods));
   }

   public String getExcludedMethodList() {
      return String.join(",", this.getExcludedMethods());
   }

   public DeflaterPool getDeflaterPool() {
      return this._deflaterPool;
   }

   public InflaterPool getInflaterPool() {
      return this._inflaterPool;
   }

   public void setDeflaterPool(DeflaterPool deflaterPool) {
      if (this.isStarted()) {
         throw new IllegalStateException(this.getState());
      } else {
         this.updateBean(this._deflaterPool, deflaterPool);
         this._deflaterPool = deflaterPool;
      }
   }

   public void setInflaterPool(InflaterPool inflaterPool) {
      if (this.isStarted()) {
         throw new IllegalStateException(this.getState());
      } else {
         this.updateBean(this._inflaterPool, inflaterPool);
         this._inflaterPool = inflaterPool;
      }
   }

   /** @deprecated */
   @Deprecated
   public int getDeflaterPoolCapacity() {
      return this._deflaterPool == null ? 1024 : this._deflaterPool.getCapacity();
   }

   /** @deprecated */
   @Deprecated
   public void setDeflaterPoolCapacity(int capacity) {
      if (this._deflaterPool != null) {
         this._deflaterPool.setCapacity(capacity);
      }

   }

   /** @deprecated */
   @Deprecated
   public int getInflaterPoolCapacity() {
      return this._inflaterPool == null ? 1024 : this._inflaterPool.getCapacity();
   }

   /** @deprecated */
   @Deprecated
   public void setInflaterPoolCapacity(int capacity) {
      if (this._inflaterPool != null) {
         this._inflaterPool.setCapacity(capacity);
      }

   }

   public String toString() {
      return String.format("%s@%x{%s,min=%s,inflate=%s}", this.getClass().getSimpleName(), this.hashCode(), this.getState(), this._minGzipSize, this._inflateBufferSize);
   }

   static {
      ETAG_HEADERS = EnumSet.of(HttpHeader.IF_MATCH, HttpHeader.IF_NONE_MATCH);
      LOG = LoggerFactory.getLogger(GzipHandler.class);
      X_CE_GZIP = new PreEncodedHttpField("X-Content-Encoding", "gzip");
      COMMA_GZIP = Pattern.compile(".*, *gzip");
   }
}
