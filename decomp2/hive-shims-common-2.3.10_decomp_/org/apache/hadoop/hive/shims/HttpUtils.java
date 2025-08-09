package org.apache.hadoop.hive.shims;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
   private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
   public static final String XSRF_CUSTOM_HEADER_PARAM = "custom-header";
   public static final String XSRF_CUSTOM_METHODS_TO_IGNORE_PARAM = "methods-to-ignore";
   private static final String XSRF_HEADER_DEFAULT = "X-XSRF-HEADER";
   private static final Set XSRF_METHODS_TO_IGNORE_DEFAULT = new HashSet(Arrays.asList("GET", "OPTIONS", "HEAD", "TRACE"));

   public static Filter getXSRFFilter() {
      String filterClass = "org.apache.hadoop.security.http.RestCsrfPreventionFilter";

      try {
         Class<? extends Filter> klass = Class.forName(filterClass);
         Filter f = (Filter)klass.newInstance();
         LOG.debug("Filter {} found, using as-is.", filterClass);
         return f;
      } catch (Exception e) {
         LOG.debug("Unable to use {}, got exception {}. Using internal shims impl of filter.", filterClass, e.getClass().getName());
         return constructXSRFFilter();
      }
   }

   private static Filter constructXSRFFilter() {
      return new Filter() {
         private String headerName = "X-XSRF-HEADER";
         private Set methodsToIgnore;

         {
            this.methodsToIgnore = HttpUtils.XSRF_METHODS_TO_IGNORE_DEFAULT;
         }

         public void init(FilterConfig filterConfig) throws ServletException {
            String customHeader = filterConfig.getInitParameter("custom-header");
            if (customHeader != null) {
               this.headerName = customHeader;
            }

            String customMethodsToIgnore = filterConfig.getInitParameter("methods-to-ignore");
            if (customMethodsToIgnore != null) {
               this.parseMethodsToIgnore(customMethodsToIgnore);
            }

         }

         void parseMethodsToIgnore(String mti) {
            String[] methods = mti.split(",");
            this.methodsToIgnore = new HashSet();

            for(int i = 0; i < methods.length; ++i) {
               this.methodsToIgnore.add(methods[i]);
            }

         }

         public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            if (HttpUtils.doXsrfFilter(request, response, this.methodsToIgnore, this.headerName)) {
               chain.doFilter(request, response);
            }

         }

         public void destroy() {
         }
      };
   }

   public static boolean doXsrfFilter(ServletRequest request, ServletResponse response, Set methodsToIgnore, String headerName) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      if (methodsToIgnore == null) {
         methodsToIgnore = XSRF_METHODS_TO_IGNORE_DEFAULT;
      }

      if (headerName == null) {
         headerName = "X-XSRF-HEADER";
      }

      if (!methodsToIgnore.contains(httpRequest.getMethod()) && httpRequest.getHeader(headerName) == null) {
         ((HttpServletResponse)response).sendError(400, "Missing Required Header for Vulnerability Protection");
         response.getWriter().println("XSRF filter denial, requests must contain header : " + headerName);
         return false;
      } else {
         return true;
      }
   }
}
