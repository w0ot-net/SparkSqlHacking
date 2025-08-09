package org.sparkproject.jetty.server.handler;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

@ManagedObject("Jetty Handler")
public abstract class AbstractHandler extends ContainerLifeCycle implements Handler {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractHandler.class);
   private Server _server;

   public abstract void handle(String var1, Request var2, HttpServletRequest var3, HttpServletResponse var4) throws IOException, ServletException;

   /** @deprecated */
   @Deprecated
   protected void doError(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Object o = request.getAttribute("jakarta.servlet.error.status_code");
      int code = o instanceof Integer ? (Integer)o : (o != null ? Integer.parseInt(o.toString()) : 500);
      response.setStatus(code);
      baseRequest.setHandled(true);
   }

   protected void doStart() throws Exception {
      if (LOG.isDebugEnabled()) {
         LOG.debug("starting {}", this);
      }

      if (this._server == null) {
         LOG.warn("No Server set for {}", this);
      }

      super.doStart();
   }

   protected void doStop() throws Exception {
      if (LOG.isDebugEnabled()) {
         LOG.debug("stopping {}", this);
      }

      super.doStop();
   }

   public void setServer(Server server) {
      if (this._server != server) {
         if (this.isStarted()) {
            throw new IllegalStateException(this.getState());
         } else {
            this._server = server;
         }
      }
   }

   public Server getServer() {
      return this._server;
   }

   public void destroy() {
      if (!this.isStopped()) {
         throw new IllegalStateException("!STOPPED");
      } else {
         super.destroy();
      }
   }

   /** @deprecated */
   @Deprecated
   public abstract static class ErrorDispatchHandler extends AbstractHandler {
      public final void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         if (baseRequest.getDispatcherType() == DispatcherType.ERROR) {
            this.doError(target, baseRequest, request, response);
         } else {
            this.doNonErrorHandle(target, baseRequest, request, response);
         }

      }

      /** @deprecated */
      @Deprecated
      protected abstract void doNonErrorHandle(String var1, Request var2, HttpServletRequest var3, HttpServletResponse var4) throws IOException, ServletException;
   }
}
