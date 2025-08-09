package org.apache.spark.status.api.v1;

import jakarta.servlet.ServletContext;
import org.sparkproject.jetty.server.handler.ContextHandler;

public final class UIRootFromServletContext$ {
   public static final UIRootFromServletContext$ MODULE$ = new UIRootFromServletContext$();
   private static final String attribute;

   static {
      attribute = MODULE$.getClass().getCanonicalName();
   }

   private String attribute() {
      return attribute;
   }

   public void setUiRoot(final ContextHandler contextHandler, final UIRoot uiRoot) {
      contextHandler.setAttribute(this.attribute(), uiRoot);
   }

   public UIRoot getUiRoot(final ServletContext context) {
      return (UIRoot)context.getAttribute(this.attribute());
   }

   private UIRootFromServletContext$() {
   }
}
