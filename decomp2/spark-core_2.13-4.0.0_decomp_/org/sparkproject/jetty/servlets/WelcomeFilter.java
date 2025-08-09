package org.sparkproject.jetty.servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.sparkproject.jetty.util.URIUtil;

/** @deprecated */
@Deprecated
public class WelcomeFilter implements Filter {
   private String welcome;

   public void init(FilterConfig filterConfig) {
      this.welcome = filterConfig.getInitParameter("welcome");
      if (this.welcome == null) {
         this.welcome = "index.html";
      }

   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      String path = ((HttpServletRequest)request).getServletPath();
      if (this.welcome != null && path.endsWith("/")) {
         String uriInContext = URIUtil.encodePath(URIUtil.addPaths(path, this.welcome));
         request.getRequestDispatcher(uriInContext).forward(request, response);
      } else {
         chain.doFilter(request, response);
      }

   }

   public void destroy() {
   }
}
