package org.sparkproject.jetty.server.handler.jmx;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.jmx.ObjectMBean;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.handler.AbstractHandler;
import org.sparkproject.jetty.server.handler.AbstractHandlerContainer;
import org.sparkproject.jetty.server.handler.ContextHandler;

public class AbstractHandlerMBean extends ObjectMBean {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractHandlerMBean.class);

   public AbstractHandlerMBean(Object managedObject) {
      super(managedObject);
   }

   public String getObjectContextBasis() {
      if (this._managed != null) {
         String basis = null;
         if (this._managed instanceof ContextHandler) {
            ContextHandler handler = (ContextHandler)this._managed;
            String context = this.getContextName(handler);
            if (context == null) {
               context = handler.getDisplayName();
            }

            if (context != null) {
               return context;
            }
         } else if (this._managed instanceof AbstractHandler) {
            AbstractHandler handler = (AbstractHandler)this._managed;
            Server server = handler.getServer();
            if (server != null) {
               ContextHandler context = (ContextHandler)AbstractHandlerContainer.findContainerOf(server, ContextHandler.class, handler);
               if (context != null) {
                  basis = this.getContextName(context);
               }
            }
         }

         if (basis != null) {
            return basis;
         }
      }

      return super.getObjectContextBasis();
   }

   protected String getContextName(ContextHandler context) {
      String name = null;
      if (context.getContextPath() != null && context.getContextPath().length() > 0) {
         int idx = context.getContextPath().lastIndexOf(47);
         String var10000;
         if (idx < 0) {
            var10000 = context.getContextPath();
         } else {
            var10000 = context.getContextPath();
            ++idx;
            var10000 = var10000.substring(idx);
         }

         name = var10000;
         if (name == null || name.length() == 0) {
            name = "ROOT";
         }
      }

      if (name == null && context.getBaseResource() != null) {
         try {
            if (context.getBaseResource().getFile() != null) {
               name = context.getBaseResource().getFile().getName();
            }
         } catch (IOException e) {
            LOG.trace("IGNORED", e);
            name = context.getBaseResource().getName();
         }
      }

      if (context.getVirtualHosts() != null && context.getVirtualHosts().length > 0) {
         name = "\"" + name + "@" + context.getVirtualHosts()[0] + "\"";
      }

      return name;
   }
}
