package org.glassfish.jersey.servlet;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import java.util.Enumeration;

public interface WebConfig {
   ConfigType getConfigType();

   ServletConfig getServletConfig();

   FilterConfig getFilterConfig();

   String getName();

   String getInitParameter(String var1);

   Enumeration getInitParameterNames();

   ServletContext getServletContext();

   public static enum ConfigType {
      ServletConfig,
      FilterConfig;
   }
}
