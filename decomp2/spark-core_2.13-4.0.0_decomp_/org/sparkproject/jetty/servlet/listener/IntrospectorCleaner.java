package org.sparkproject.jetty.servlet.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.beans.Introspector;

public class IntrospectorCleaner implements ServletContextListener {
   public void contextInitialized(ServletContextEvent sce) {
   }

   public void contextDestroyed(ServletContextEvent sce) {
      Introspector.flushCaches();
   }
}
