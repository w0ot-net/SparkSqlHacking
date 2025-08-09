package org.sparkproject.jetty.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.CompressedContentFormat;
import org.sparkproject.jetty.http.HttpContent;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.server.CachedContentFactory;
import org.sparkproject.jetty.server.ResourceContentFactory;
import org.sparkproject.jetty.server.ResourceService;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.ResourceHandler;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.resource.ResourceFactory;

public class DefaultServlet extends HttpServlet implements ResourceFactory, ResourceService.WelcomeFactory {
   public static final String CONTEXT_INIT = "org.sparkproject.jetty.servlet.Default.";
   private static final Logger LOG = LoggerFactory.getLogger(DefaultServlet.class);
   private static final long serialVersionUID = 4930458713846881193L;
   private final ResourceService _resourceService;
   private ServletContext _servletContext;
   private ContextHandler _contextHandler;
   private boolean _welcomeServlets;
   private boolean _welcomeExactServlets;
   private Resource _resourceBase;
   private CachedContentFactory _cache;
   private MimeTypes _mimeTypes;
   private String[] _welcomes;
   private Resource _stylesheet;
   private boolean _useFileMappedBuffer;
   private String _relativeResourceBase;
   private ServletHandler _servletHandler;

   public DefaultServlet(ResourceService resourceService) {
      this._welcomeServlets = false;
      this._welcomeExactServlets = false;
      this._useFileMappedBuffer = false;
      this._resourceService = resourceService;
   }

   public DefaultServlet() {
      this(new ResourceService());
   }

   public void init() throws UnavailableException {
      this._servletContext = this.getServletContext();
      this._contextHandler = this.initContextHandler(this._servletContext);
      this._mimeTypes = this._contextHandler.getMimeTypes();
      this._welcomes = this._contextHandler.getWelcomeFiles();
      if (this._welcomes == null) {
         this._welcomes = new String[]{"index.html", "index.jsp"};
      }

      this._resourceService.setAcceptRanges(this.getInitBoolean("acceptRanges", this._resourceService.isAcceptRanges()));
      this._resourceService.setDirAllowed(this.getInitBoolean("dirAllowed", this._resourceService.isDirAllowed()));
      this._resourceService.setRedirectWelcome(this.getInitBoolean("redirectWelcome", this._resourceService.isRedirectWelcome()));
      this._resourceService.setPrecompressedFormats(this.parsePrecompressedFormats(this.getInitParameter("precompressed"), this.getInitBoolean("gzip"), this._resourceService.getPrecompressedFormats()));
      this._resourceService.setPathInfoOnly(this.getInitBoolean("pathInfoOnly", this._resourceService.isPathInfoOnly()));
      this._resourceService.setEtags(this.getInitBoolean("etags", this._resourceService.isEtags()));
      if ("exact".equals(this.getInitParameter("welcomeServlets"))) {
         this._welcomeExactServlets = true;
         this._welcomeServlets = false;
      } else {
         this._welcomeServlets = this.getInitBoolean("welcomeServlets", this._welcomeServlets);
      }

      this._useFileMappedBuffer = this.getInitBoolean("useFileMappedBuffer", this._useFileMappedBuffer);
      this._relativeResourceBase = this.getInitParameter("relativeResourceBase");
      String rb = this.getInitParameter("resourceBase");
      if (rb != null) {
         if (this._relativeResourceBase != null) {
            throw new UnavailableException("resourceBase & relativeResourceBase");
         }

         try {
            this._resourceBase = this._contextHandler.newResource(rb);
         } catch (Exception e) {
            LOG.warn("Unable to create resourceBase from {}", rb, e);
            throw new UnavailableException(e.toString());
         }
      }

      String css = this.getInitParameter("stylesheet");

      try {
         if (css != null) {
            this._stylesheet = Resource.newResource(css);
            if (!this._stylesheet.exists()) {
               LOG.warn("!{}", css);
               this._stylesheet = null;
            }
         }

         if (this._stylesheet == null) {
            this._stylesheet = ResourceHandler.getDefaultStylesheet();
         }
      } catch (Exception e) {
         if (LOG.isDebugEnabled()) {
            LOG.warn("Unable to use stylesheet: {}", css, e);
         } else {
            LOG.warn("Unable to use stylesheet: {} - {}", css, e.toString());
         }
      }

      int encodingHeaderCacheSize = this.getInitInt("encodingHeaderCacheSize", -1);
      if (encodingHeaderCacheSize >= 0) {
         this._resourceService.setEncodingCacheSize(encodingHeaderCacheSize);
      }

      String cc = this.getInitParameter("cacheControl");
      if (cc != null) {
         this._resourceService.setCacheControl(new PreEncodedHttpField(HttpHeader.CACHE_CONTROL, cc));
      }

      String resourceCache = this.getInitParameter("resourceCache");
      int maxCacheSize = this.getInitInt("maxCacheSize", -2);
      int maxCachedFileSize = this.getInitInt("maxCachedFileSize", -2);
      int maxCachedFiles = this.getInitInt("maxCachedFiles", -2);
      if (resourceCache != null) {
         if (maxCacheSize != -1 || maxCachedFileSize != -2 || maxCachedFiles != -2) {
            LOG.debug("ignoring resource cache configuration, using resourceCache attribute");
         }

         if (this._relativeResourceBase != null || this._resourceBase != null) {
            throw new UnavailableException("resourceCache specified with resource bases");
         }

         this._cache = (CachedContentFactory)this._servletContext.getAttribute(resourceCache);
      }

      try {
         if (this._cache == null && (maxCachedFiles != -2 || maxCacheSize != -2 || maxCachedFileSize != -2)) {
            this._cache = new CachedContentFactory((CachedContentFactory)null, this, this._mimeTypes, this._useFileMappedBuffer, this._resourceService.isEtags(), this._resourceService.getPrecompressedFormats());
            if (maxCacheSize >= 0) {
               this._cache.setMaxCacheSize(maxCacheSize);
            }

            if (maxCachedFileSize >= -1) {
               this._cache.setMaxCachedFileSize(maxCachedFileSize);
            }

            if (maxCachedFiles >= -1) {
               this._cache.setMaxCachedFiles(maxCachedFiles);
            }

            this._servletContext.setAttribute(resourceCache == null ? "resourceCache" : resourceCache, this._cache);
         }
      } catch (Exception e) {
         LOG.warn("Unable to setup CachedContentFactory", e);
         throw new UnavailableException(e.toString());
      }

      HttpContent.ContentFactory contentFactory = this._cache;
      if (contentFactory == null) {
         contentFactory = new ResourceContentFactory(this, this._mimeTypes, this._resourceService.getPrecompressedFormats());
         if (resourceCache != null) {
            this._servletContext.setAttribute(resourceCache, contentFactory);
         }
      }

      this._resourceService.setContentFactory(contentFactory);
      this._resourceService.setWelcomeFactory(this);
      List<String> gzipEquivalentFileExtensions = new ArrayList();
      String otherGzipExtensions = this.getInitParameter("otherGzipFileExtensions");
      if (otherGzipExtensions != null) {
         StringTokenizer tok = new StringTokenizer(otherGzipExtensions, ",", false);

         while(tok.hasMoreTokens()) {
            String s = tok.nextToken().trim();
            gzipEquivalentFileExtensions.add(s.charAt(0) == '.' ? s : "." + s);
         }
      } else {
         gzipEquivalentFileExtensions.add(".svgz");
      }

      this._resourceService.setGzipEquivalentFileExtensions(gzipEquivalentFileExtensions);
      this._servletHandler = (ServletHandler)this._contextHandler.getChildHandlerByClass(ServletHandler.class);
      if (LOG.isDebugEnabled()) {
         LOG.debug("resource base = {}", this._resourceBase);
      }

   }

   private CompressedContentFormat[] parsePrecompressedFormats(String precompressed, Boolean gzip, CompressedContentFormat[] dft) {
      if (precompressed == null && gzip == null) {
         return dft;
      } else {
         List<CompressedContentFormat> ret = new ArrayList();
         if (precompressed != null && precompressed.indexOf(61) > 0) {
            for(String pair : precompressed.split(",")) {
               String[] setting = pair.split("=");
               String encoding = setting[0].trim();
               String extension = setting[1].trim();
               ret.add(new CompressedContentFormat(encoding, extension));
               if (gzip == Boolean.TRUE && !ret.contains(CompressedContentFormat.GZIP)) {
                  ret.add(CompressedContentFormat.GZIP);
               }
            }
         } else if (precompressed != null) {
            if (Boolean.parseBoolean(precompressed)) {
               ret.add(CompressedContentFormat.BR);
               ret.add(CompressedContentFormat.GZIP);
            }
         } else if (gzip == Boolean.TRUE) {
            ret.add(CompressedContentFormat.GZIP);
         }

         return (CompressedContentFormat[])ret.toArray(new CompressedContentFormat[ret.size()]);
      }
   }

   protected ContextHandler initContextHandler(ServletContext servletContext) {
      ContextHandler.Context scontext = ContextHandler.getCurrentContext();
      if (scontext == null) {
         if (servletContext instanceof ContextHandler.Context) {
            return ((ContextHandler.Context)servletContext).getContextHandler();
         } else {
            String var10002 = String.valueOf(servletContext);
            throw new IllegalArgumentException("The servletContext " + var10002 + " " + servletContext.getClass().getName() + " is not " + ContextHandler.Context.class.getName());
         }
      } else {
         return ContextHandler.getCurrentContext().getContextHandler();
      }
   }

   public String getInitParameter(String name) {
      String value = this.getServletContext().getInitParameter("org.sparkproject.jetty.servlet.Default." + name);
      if (value == null) {
         value = super.getInitParameter(name);
      }

      return value;
   }

   private Boolean getInitBoolean(String name) {
      String value = this.getInitParameter(name);
      return value != null && value.length() != 0 ? value.startsWith("t") || value.startsWith("T") || value.startsWith("y") || value.startsWith("Y") || value.startsWith("1") : null;
   }

   private boolean getInitBoolean(String name, boolean dft) {
      return (Boolean)Optional.ofNullable(this.getInitBoolean(name)).orElse(dft);
   }

   private int getInitInt(String name, int dft) {
      String value = this.getInitParameter(name);
      if (value == null) {
         value = this.getInitParameter(name);
      }

      return value != null && value.length() > 0 ? Integer.parseInt(value) : dft;
   }

   public Resource getResource(String pathInContext) {
      Resource r = null;
      if (this._relativeResourceBase != null) {
         pathInContext = URIUtil.addPaths(this._relativeResourceBase, pathInContext);
      }

      try {
         if (this._resourceBase != null) {
            r = this._resourceBase.addPath(pathInContext);
            if (!this._contextHandler.checkAlias(pathInContext, r)) {
               r = null;
            }
         } else {
            if (!(this._servletContext instanceof ContextHandler.Context)) {
               return null;
            }

            r = this._contextHandler.getResource(pathInContext);
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("Resource {}={}", pathInContext, r);
         }
      } catch (IOException e) {
         LOG.trace("IGNORED", e);
      }

      if ((r == null || !r.exists()) && pathInContext.endsWith("/jetty-dir.css")) {
         r = this._stylesheet;
      }

      return r;
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (!this._resourceService.doGet(request, response)) {
         response.sendError(404);
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }

   protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }

   protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.sendError(405);
   }

   protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setHeader("Allow", "GET,HEAD,POST,OPTIONS");
   }

   public void destroy() {
      if (this._cache != null) {
         this._cache.flushCache();
      }

      super.destroy();
   }

   public String getWelcomeFile(String pathInContext) {
      if (this._welcomes == null) {
         return null;
      } else {
         String welcomeServlet = null;

         for(String s : this._welcomes) {
            String welcomeInContext = URIUtil.addPaths(pathInContext, s);
            Resource welcome = this.getResource(welcomeInContext);
            if (welcome != null && welcome.exists()) {
               return welcomeInContext;
            }

            if ((this._welcomeServlets || this._welcomeExactServlets) && welcomeServlet == null) {
               ServletHandler.MappedServlet entry = this._servletHandler.getMappedServlet(welcomeInContext);
               if (entry != null && entry.getServletHolder().getServletInstance() != this && (this._welcomeServlets || this._welcomeExactServlets && entry.getPathSpec().getDeclaration().equals(welcomeInContext))) {
                  welcomeServlet = welcomeInContext;
               }
            }
         }

         return welcomeServlet;
      }
   }
}
