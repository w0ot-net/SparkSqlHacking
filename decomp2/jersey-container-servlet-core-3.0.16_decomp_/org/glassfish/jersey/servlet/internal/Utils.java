package org.glassfish.jersey.servlet.internal;

import jakarta.servlet.ServletContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.glassfish.jersey.server.ResourceConfig;

public final class Utils {
   private static final String RESOURCE_CONFIG = "jersey.config.servlet.internal.resourceConfig";

   public static void store(ResourceConfig config, ServletContext context, String configName) {
      String attributeName = "jersey.config.servlet.internal.resourceConfig_" + configName;
      context.setAttribute(attributeName, config);
   }

   public static ResourceConfig retrieve(ServletContext context, String configName) {
      String attributeName = "jersey.config.servlet.internal.resourceConfig_" + configName;
      ResourceConfig config = (ResourceConfig)context.getAttribute(attributeName);
      context.removeAttribute(attributeName);
      return config;
   }

   public static Map getContextParams(ServletContext servletContext) {
      Map<String, Object> props = new HashMap();
      Enumeration names = servletContext.getAttributeNames();

      while(names.hasMoreElements()) {
         String name = (String)names.nextElement();
         props.put(name, servletContext.getAttribute(name));
      }

      return props;
   }

   private Utils() {
   }
}
