package org.glassfish.jersey.servlet;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import java.util.Enumeration;

public final class WebFilterConfig implements WebConfig {
   private final FilterConfig filterConfig;

   public WebFilterConfig(FilterConfig filterConfig) {
      this.filterConfig = filterConfig;
   }

   public WebConfig.ConfigType getConfigType() {
      return WebConfig.ConfigType.FilterConfig;
   }

   public ServletConfig getServletConfig() {
      return null;
   }

   public FilterConfig getFilterConfig() {
      return this.filterConfig;
   }

   public String getName() {
      return this.filterConfig.getFilterName();
   }

   public String getInitParameter(String name) {
      return this.filterConfig.getInitParameter(name);
   }

   public Enumeration getInitParameterNames() {
      return this.filterConfig.getInitParameterNames();
   }

   public ServletContext getServletContext() {
      return this.filterConfig.getServletContext();
   }
}
