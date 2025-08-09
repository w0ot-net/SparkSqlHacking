package org.apache.spark.status.api.v1;

import org.glassfish.jersey.servlet.ServletContainer;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import org.sparkproject.jetty.servlet.ServletHolder;

public final class ApiRootResource$ {
   public static final ApiRootResource$ MODULE$ = new ApiRootResource$();

   public ServletContextHandler getServletHandler(final UIRoot uiRoot) {
      ServletContextHandler jerseyContext = new ServletContextHandler(0);
      jerseyContext.setContextPath("/api");
      ServletHolder holder = new ServletHolder(ServletContainer.class);
      holder.setInitParameter("jersey.config.server.provider.packages", "org.apache.spark.status.api.v1");
      UIRootFromServletContext$.MODULE$.setUiRoot(jerseyContext, uiRoot);
      jerseyContext.addServlet(holder, "/*");
      return jerseyContext;
   }

   private ApiRootResource$() {
   }
}
