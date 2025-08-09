package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.HandlerContainer;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject("Handler wrapping another Handler")
public class HandlerWrapper extends AbstractHandlerContainer {
   protected Handler _handler;

   @ManagedAttribute(
      value = "Wrapped Handler",
      readonly = true
   )
   public Handler getHandler() {
      return this._handler;
   }

   public Handler[] getHandlers() {
      return this._handler == null ? new Handler[0] : new Handler[]{this._handler};
   }

   public void setHandler(Handler handler) {
      if (this.isStarted()) {
         throw new IllegalStateException(this.getState());
      } else if (handler != this && (!(handler instanceof HandlerContainer) || !Arrays.asList(((HandlerContainer)handler).getChildHandlers()).contains(this))) {
         if (handler != null) {
            handler.setServer(this.getServer());
         }

         Handler old = this._handler;
         this._handler = handler;
         this.updateBean(old, this._handler, true);
      } else {
         throw new IllegalStateException("setHandler loop");
      }
   }

   public void insertHandler(HandlerWrapper wrapper) {
      if (wrapper == null) {
         throw new IllegalArgumentException();
      } else {
         HandlerWrapper tail;
         for(tail = wrapper; tail.getHandler() instanceof HandlerWrapper; tail = (HandlerWrapper)tail.getHandler()) {
         }

         if (tail.getHandler() != null) {
            throw new IllegalArgumentException("bad tail of inserted wrapper chain");
         } else {
            Handler next = this.getHandler();
            this.setHandler(wrapper);
            tail.setHandler(next);
         }
      }
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Handler handler = this._handler;
      if (handler != null) {
         handler.handle(target, baseRequest, request, response);
      }

   }

   protected void expandChildren(List list, Class byClass) {
      this.expandHandler(this._handler, list, byClass);
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
