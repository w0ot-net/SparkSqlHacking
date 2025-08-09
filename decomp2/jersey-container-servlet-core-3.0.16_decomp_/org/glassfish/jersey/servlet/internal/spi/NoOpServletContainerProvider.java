package org.glassfish.jersey.servlet.internal.spi;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.core.GenericType;
import java.lang.reflect.Type;
import java.util.Set;
import org.glassfish.jersey.server.ResourceConfig;

public class NoOpServletContainerProvider implements ExtendedServletContainerProvider {
   public final Type HTTP_SERVLET_REQUEST_TYPE = (new GenericType() {
   }).getType();
   public final Type HTTP_SERVLET_RESPONSE_TYPE = (new GenericType() {
   }).getType();

   public void preInit(ServletContext servletContext, Set classes) throws ServletException {
   }

   public void postInit(ServletContext servletContext, Set classes, Set servletNames) {
   }

   public void onRegister(ServletContext servletContext, Set servletNames) throws ServletException {
   }

   public void configure(ResourceConfig resourceConfig) throws ServletException {
   }

   public boolean bindsServletRequestResponse() {
      return false;
   }

   public RequestScopedInitializerProvider getRequestScopedInitializerProvider() {
      return null;
   }
}
