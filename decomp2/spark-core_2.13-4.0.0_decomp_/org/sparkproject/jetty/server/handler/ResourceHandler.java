package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.CompressedContentFormat;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.PreEncodedHttpField;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.ResourceContentFactory;
import org.sparkproject.jetty.server.ResourceService;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.resource.ResourceFactory;

public class ResourceHandler extends HandlerWrapper implements ResourceFactory, ResourceService.WelcomeFactory {
   private static final Logger LOG = LoggerFactory.getLogger(ResourceHandler.class);
   Resource _baseResource;
   ContextHandler _context;
   Resource _defaultStylesheet;
   MimeTypes _mimeTypes;
   private final ResourceService _resourceService;
   Resource _stylesheet;
   String[] _welcomes;

   public ResourceHandler(ResourceService resourceService) {
      this._welcomes = new String[]{"index.html"};
      this._resourceService = resourceService;
   }

   public ResourceHandler() {
      this(new ResourceService() {
         protected void notFound(HttpServletRequest request, HttpServletResponse response) throws IOException {
         }
      });
      this._resourceService.setGzipEquivalentFileExtensions(new ArrayList(Arrays.asList(".svgz")));
   }

   public String getWelcomeFile(String pathInContext) throws IOException {
      if (this._welcomes == null) {
         return null;
      } else {
         for(int i = 0; i < this._welcomes.length; ++i) {
            String welcomeInContext = URIUtil.addPaths(pathInContext, this._welcomes[i]);
            Resource welcome = this.getResource(welcomeInContext);
            if (welcome.exists()) {
               return welcomeInContext;
            }
         }

         return null;
      }
   }

   public void doStart() throws Exception {
      ContextHandler.Context scontext = ContextHandler.getCurrentContext();
      this._context = scontext == null ? null : scontext.getContextHandler();
      if (this._mimeTypes == null) {
         this._mimeTypes = this._context == null ? new MimeTypes() : this._context.getMimeTypes();
      }

      this._resourceService.setContentFactory(new ResourceContentFactory(this, this._mimeTypes, this._resourceService.getPrecompressedFormats()));
      this._resourceService.setWelcomeFactory(this);
      super.doStart();
   }

   public Resource getBaseResource() {
      return this._baseResource == null ? null : this._baseResource;
   }

   public String getCacheControl() {
      return this._resourceService.getCacheControl().getValue();
   }

   public List getGzipEquivalentFileExtensions() {
      return this._resourceService.getGzipEquivalentFileExtensions();
   }

   public MimeTypes getMimeTypes() {
      return this._mimeTypes;
   }

   public Resource getResource(String path) throws IOException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} getResource({})", this._context == null ? this._baseResource : this._context, path);
      }

      if (StringUtil.isBlank(path)) {
         throw new IllegalArgumentException("Path is blank");
      } else if (!path.startsWith("/")) {
         throw new IllegalArgumentException("Path reference invalid: " + path);
      } else {
         Resource r = null;
         if (this._baseResource != null) {
            r = this._baseResource.addPath(path);
            if (r.isAlias() && (this._context == null || !this._context.checkAlias(path, r))) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Rejected alias resource={} alias={}", r, r.getAlias());
               }

               throw new IllegalStateException("Rejected alias reference: " + path);
            }
         } else if (this._context != null) {
            r = this._context.getResource(path);
         }

         if ((r == null || !r.exists()) && path.endsWith("/jetty-dir.css")) {
            r = this.getStylesheet();
         }

         if (r == null) {
            throw new FileNotFoundException("Resource: " + path);
         } else {
            return r;
         }
      }
   }

   public String getResourceBase() {
      return this._baseResource == null ? null : this._baseResource.toString();
   }

   public Resource getStylesheet() {
      if (this._stylesheet != null) {
         return this._stylesheet;
      } else {
         if (this._defaultStylesheet == null) {
            this._defaultStylesheet = getDefaultStylesheet();
         }

         return this._defaultStylesheet;
      }
   }

   public static Resource getDefaultStylesheet() {
      return Resource.newResource(ResourceHandler.class.getResource("/jetty-dir.css"));
   }

   public String[] getWelcomeFiles() {
      return this._welcomes;
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (!baseRequest.isHandled()) {
         if (!HttpMethod.GET.is(request.getMethod()) && !HttpMethod.HEAD.is(request.getMethod())) {
            super.handle(target, baseRequest, request, response);
         } else {
            if (this._resourceService.doGet(request, response)) {
               baseRequest.setHandled(true);
            } else {
               super.handle(target, baseRequest, request, response);
            }

         }
      }
   }

   public boolean isAcceptRanges() {
      return this._resourceService.isAcceptRanges();
   }

   public boolean isDirAllowed() {
      return this._resourceService.isDirAllowed();
   }

   public boolean isDirectoriesListed() {
      return this._resourceService.isDirAllowed();
   }

   public boolean isEtags() {
      return this._resourceService.isEtags();
   }

   public CompressedContentFormat[] getPrecompressedFormats() {
      return this._resourceService.getPrecompressedFormats();
   }

   public boolean isPathInfoOnly() {
      return this._resourceService.isPathInfoOnly();
   }

   public boolean isRedirectWelcome() {
      return this._resourceService.isRedirectWelcome();
   }

   public void setAcceptRanges(boolean acceptRanges) {
      this._resourceService.setAcceptRanges(acceptRanges);
   }

   public void setBaseResource(Resource base) {
      this._baseResource = base;
   }

   public void setCacheControl(String cacheControl) {
      this._resourceService.setCacheControl(new PreEncodedHttpField(HttpHeader.CACHE_CONTROL, cacheControl));
   }

   public void setDirAllowed(boolean dirAllowed) {
      this._resourceService.setDirAllowed(dirAllowed);
   }

   public void setDirectoriesListed(boolean directory) {
      this._resourceService.setDirAllowed(directory);
   }

   public void setEtags(boolean etags) {
      this._resourceService.setEtags(etags);
   }

   public void setGzipEquivalentFileExtensions(List gzipEquivalentFileExtensions) {
      this._resourceService.setGzipEquivalentFileExtensions(gzipEquivalentFileExtensions);
   }

   public void setPrecompressedFormats(CompressedContentFormat[] precompressedFormats) {
      this._resourceService.setPrecompressedFormats(precompressedFormats);
   }

   public void setMimeTypes(MimeTypes mimeTypes) {
      this._mimeTypes = mimeTypes;
   }

   public void setPathInfoOnly(boolean pathInfoOnly) {
      this._resourceService.setPathInfoOnly(pathInfoOnly);
   }

   public void setRedirectWelcome(boolean redirectWelcome) {
      this._resourceService.setRedirectWelcome(redirectWelcome);
   }

   public void setResourceBase(String resourceBase) {
      try {
         this.setBaseResource(Resource.newResource(resourceBase));
      } catch (Exception e) {
         LOG.warn("Invalid Base Resource reference: {}", resourceBase, e);
         throw new IllegalArgumentException(resourceBase);
      }
   }

   public void setStylesheet(String stylesheet) {
      try {
         this._stylesheet = Resource.newResource(stylesheet);
         if (!this._stylesheet.exists()) {
            LOG.warn("unable to find custom stylesheet: {}", stylesheet);
            this._stylesheet = null;
         }

      } catch (Exception e) {
         LOG.warn("Invalid StyleSheet reference: {}", stylesheet, e);
         throw new IllegalArgumentException(stylesheet);
      }
   }

   public void setWelcomeFiles(String[] welcomeFiles) {
      this._welcomes = welcomeFiles;
   }
}
