package org.sparkproject.jetty.servlet.listener;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class ContainerInitializer {
   public static ServletContainerInitializerServletContextListener asContextListener(ServletContainerInitializer sci) {
      return new ServletContainerInitializerServletContextListener(sci);
   }

   public static class ServletContainerInitializerServletContextListener implements ServletContextListener {
      private final ServletContainerInitializer sci;
      private Set classNames;
      private Set classes = new HashSet();
      private Consumer afterStartupConsumer;

      public ServletContainerInitializerServletContextListener(ServletContainerInitializer sci) {
         this.sci = sci;
      }

      public ServletContainerInitializerServletContextListener addClasses(String... classNames) {
         if (this.classNames == null) {
            this.classNames = new HashSet();
         }

         this.classNames.addAll(Arrays.asList(classNames));
         return this;
      }

      public ServletContainerInitializerServletContextListener addClasses(Class... classes) {
         this.classes.addAll(Arrays.asList(classes));
         return this;
      }

      public ServletContainerInitializerServletContextListener afterStartup(Consumer consumer) {
         this.afterStartupConsumer = consumer;
         return this;
      }

      public void contextInitialized(ServletContextEvent sce) {
         ServletContext servletContext = sce.getServletContext();

         try {
            this.sci.onStartup(this.getClasses(), servletContext);
            if (this.afterStartupConsumer != null) {
               this.afterStartupConsumer.accept(servletContext);
            }

         } catch (RuntimeException rte) {
            throw rte;
         } catch (Throwable cause) {
            throw new RuntimeException(cause);
         }
      }

      public Set getClasses() {
         if (this.classNames != null && !this.classNames.isEmpty()) {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();

            for(String className : this.classNames) {
               try {
                  Class<?> clazz = cl.loadClass(className);
                  this.classes.add(clazz);
               } catch (ClassNotFoundException e) {
                  throw new RuntimeException("Unable to find class: " + className, e);
               }
            }
         }

         return this.classes;
      }

      public void contextDestroyed(ServletContextEvent sce) {
      }
   }
}
