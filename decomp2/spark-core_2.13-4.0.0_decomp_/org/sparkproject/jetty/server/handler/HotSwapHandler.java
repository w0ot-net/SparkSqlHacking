package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Server;

public class HotSwapHandler extends AbstractHandlerContainer {
   private volatile Handler _handler;

   public Handler getHandler() {
      return this._handler;
   }

   public Handler[] getHandlers() {
      Handler handler = this._handler;
      return handler == null ? new Handler[0] : new Handler[]{handler};
   }

   public void setHandler(Handler handler) {
      try {
         Server server = this.getServer();
         if (handler != this._handler) {
            Handler oldHandler = this._handler;
            if (handler != null) {
               handler.setServer(server);
               this.addBean(handler, true);
               if (oldHandler != null && oldHandler.isStarted()) {
                  handler.start();
               }
            }

            this._handler = handler;
            if (oldHandler != null) {
               this.removeBean(oldHandler);
            }

         }
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   protected void doStart() throws Exception {
      super.doStart();
   }

   protected void doStop() throws Exception {
      super.doStop();
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Handler handler = this._handler;
      if (handler != null && this.isStarted() && handler.isStarted()) {
         handler.handle(target, baseRequest, request, response);
      }

   }

   protected void expandChildren(List list, Class byClass) {
      Handler handler = this._handler;
      if (handler != null) {
         this.expandHandler(handler, list, byClass);
      }

   }

   public void destroy() {
      if (!this.isStopped()) {
         throw new IllegalStateException("!STOPPED");
      } else {
         Handler child = this.getHandler();
         if (child != null) {
            this.setHandler((Handler)null);
            child.destroy();
         }

         super.destroy();
      }
   }
}
