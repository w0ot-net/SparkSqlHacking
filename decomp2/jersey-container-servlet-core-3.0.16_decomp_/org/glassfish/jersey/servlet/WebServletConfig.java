package org.glassfish.jersey.servlet;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import java.util.Enumeration;

public final class WebServletConfig implements WebConfig {
   private final ServletContainer servlet;

   public WebServletConfig(ServletContainer servlet) {
      this.servlet = servlet;
   }

   public WebConfig.ConfigType getConfigType() {
      return WebConfig.ConfigType.ServletConfig;
   }

   public ServletConfig getServletConfig() {
      return this.servlet.getServletConfig();
   }

   public FilterConfig getFilterConfig() {
      return null;
   }

   public String getName() {
      return this.servlet.getServletName();
   }

   public String getInitParameter(String name) {
      return this.servlet.getInitParameter(name);
   }

   public Enumeration getInitParameterNames() {
      return this.servlet.getInitParameterNames();
   }

   public ServletContext getServletContext() {
      return this.servlet.getServletContext();
   }
}
