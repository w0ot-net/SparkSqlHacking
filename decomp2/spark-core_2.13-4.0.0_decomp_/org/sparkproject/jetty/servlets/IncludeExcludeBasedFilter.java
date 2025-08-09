package org.sparkproject.jetty.servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.pathmap.PathSpecSet;
import org.sparkproject.jetty.util.IncludeExclude;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;

public abstract class IncludeExcludeBasedFilter implements Filter {
   private final IncludeExclude _mimeTypes = new IncludeExclude();
   private final IncludeExclude _httpMethods = new IncludeExclude();
   private final IncludeExclude _paths = new IncludeExclude(PathSpecSet.class);
   private static final Logger LOG = LoggerFactory.getLogger(IncludeExcludeBasedFilter.class);

   public void init(FilterConfig filterConfig) throws ServletException {
      String includedPaths = filterConfig.getInitParameter("includedPaths");
      String excludedPaths = filterConfig.getInitParameter("excludedPaths");
      String includedMimeTypes = filterConfig.getInitParameter("includedMimeTypes");
      String excludedMimeTypes = filterConfig.getInitParameter("excludedMimeTypes");
      String includedHttpMethods = filterConfig.getInitParameter("includedHttpMethods");
      String excludedHttpMethods = filterConfig.getInitParameter("excludedHttpMethods");
      if (includedPaths != null) {
         this._paths.include(StringUtil.csvSplit(includedPaths));
      }

      if (excludedPaths != null) {
         this._paths.exclude(StringUtil.csvSplit(excludedPaths));
      }

      if (includedMimeTypes != null) {
         this._mimeTypes.include(StringUtil.csvSplit(includedMimeTypes));
      }

      if (excludedMimeTypes != null) {
         this._mimeTypes.exclude(StringUtil.csvSplit(excludedMimeTypes));
      }

      if (includedHttpMethods != null) {
         this._httpMethods.include(StringUtil.csvSplit(includedHttpMethods));
      }

      if (excludedHttpMethods != null) {
         this._httpMethods.exclude(StringUtil.csvSplit(excludedHttpMethods));
      }

   }

   protected String guessMimeType(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
      String contentType = httpResponse.getContentType();
      LOG.debug("Content Type is: {}", contentType);
      String mimeType = "";
      if (contentType != null) {
         mimeType = MimeTypes.getContentTypeWithoutCharset(contentType);
         LOG.debug("Mime Type is: {}", mimeType);
      } else {
         String requestUrl = httpRequest.getPathInfo();
         mimeType = MimeTypes.getDefaultMimeByExtension(requestUrl);
         if (mimeType == null) {
            mimeType = "";
         }

         LOG.debug("Guessed mime type is {}", mimeType);
      }

      return mimeType;
   }

   protected boolean shouldFilter(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
      String httpMethod = httpRequest.getMethod();
      LOG.debug("HTTP method is: {}", httpMethod);
      if (!this._httpMethods.test(httpMethod)) {
         LOG.debug("should not apply filter because HTTP method does not match");
         return false;
      } else {
         String mimeType = this.guessMimeType(httpRequest, httpResponse);
         if (!this._mimeTypes.test(mimeType)) {
            LOG.debug("should not apply filter because mime type does not match");
            return false;
         } else {
            ServletContext context = httpRequest.getServletContext();
            String path = context == null ? httpRequest.getRequestURI() : URIUtil.addPaths(httpRequest.getServletPath(), httpRequest.getPathInfo());
            LOG.debug("Path is: {}", path);
            if (!this._paths.test(path)) {
               LOG.debug("should not apply filter because path does not match");
               return false;
            } else {
               return true;
            }
         }
      }
   }

   public void destroy() {
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("filter configuration:\n");
      sb.append("paths:\n").append(this._paths).append("\n");
      sb.append("mime types:\n").append(this._mimeTypes).append("\n");
      sb.append("http methods:\n").append(this._httpMethods);
      return sb.toString();
   }
}
