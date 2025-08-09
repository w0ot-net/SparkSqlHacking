package org.glassfish.jersey.servlet.internal.spi;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.util.Set;
import org.glassfish.jersey.server.ResourceConfig;

public interface ServletContainerProvider {
   void preInit(ServletContext var1, Set var2) throws ServletException;

   void postInit(ServletContext var1, Set var2, Set var3) throws ServletException;

   void onRegister(ServletContext var1, Set var2) throws ServletException;

   void configure(ResourceConfig var1) throws ServletException;
}
