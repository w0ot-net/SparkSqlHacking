package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.HandlerContainer;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.util.ArrayUtil;
import org.sparkproject.jetty.util.MultiException;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject("Handler of multiple handlers")
public class HandlerCollection extends AbstractHandlerContainer {
   private final boolean _mutableWhenRunning;
   protected final AtomicReference _handlers;

   public HandlerCollection() {
      this(false);
   }

   public HandlerCollection(Handler... handlers) {
      this(false, handlers);
   }

   public HandlerCollection(boolean mutableWhenRunning, Handler... handlers) {
      this._handlers = new AtomicReference();
      this._mutableWhenRunning = mutableWhenRunning;
      if (handlers.length > 0) {
         this.setHandlers(handlers);
      }

   }

   @ManagedAttribute(
      value = "Wrapped handlers",
      readonly = true
   )
   public Handler[] getHandlers() {
      Handlers handlers = (Handlers)this._handlers.get();
      return handlers == null ? null : handlers._handlers;
   }

   public void setHandlers(Handler[] handlers) {
      if (!this._mutableWhenRunning && this.isStarted()) {
         throw new IllegalStateException(this.getState());
      } else {
         while(!this.updateHandlers((Handlers)this._handlers.get(), this.newHandlers(handlers))) {
         }

      }
   }

   protected Handlers newHandlers(Handler[] handlers) {
      return handlers != null && handlers.length != 0 ? new Handlers(handlers) : null;
   }

   protected boolean updateHandlers(Handlers old, Handlers handlers) {
      if (handlers != null) {
         for(Handler handler : handlers._handlers) {
            if (handler == this || handler instanceof HandlerContainer && Arrays.asList(((HandlerContainer)handler).getChildHandlers()).contains(this)) {
               throw new IllegalStateException("setHandler loop");
            }
         }

         for(Handler handler : handlers._handlers) {
            if (handler.getServer() != this.getServer()) {
               handler.setServer(this.getServer());
            }
         }
      }

      if (this._handlers.compareAndSet(old, handlers)) {
         Handler[] oldBeans = old == null ? null : old._handlers;
         Handler[] newBeans = handlers == null ? null : handlers._handlers;
         this.updateBeans(oldBeans, newBeans);
         return true;
      } else {
         return false;
      }
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (this.isStarted()) {
         Handlers handlers = (Handlers)this._handlers.get();
         if (handlers == null) {
            return;
         }

         MultiException mex = null;

         for(Handler handler : handlers._handlers) {
            try {
               handler.handle(target, baseRequest, request, response);
            } catch (RuntimeException | IOException e) {
               throw e;
            } catch (Exception e) {
               if (mex == null) {
                  mex = new MultiException();
               }

               mex.add(e);
            }
         }

         if (mex != null) {
            if (mex.size() == 1) {
               throw new ServletException(mex.getThrowable(0));
            }

            throw new ServletException(mex);
         }
      }

   }

   public void addHandler(Handler handler) {
      Handlers old;
      Handlers handlers;
      do {
         old = (Handlers)this._handlers.get();
         handlers = this.newHandlers((Handler[])ArrayUtil.addToArray(old == null ? null : (Handler[])ArrayUtil.removeFromArray(old._handlers, handler), handler, Handler.class));
      } while(!this.updateHandlers(old, handlers));

   }

   public void prependHandler(Handler handler) {
      Handlers old;
      Handlers handlers;
      do {
         old = (Handlers)this._handlers.get();
         handlers = this.newHandlers((Handler[])ArrayUtil.prependToArray(handler, old == null ? null : old._handlers, Handler.class));
      } while(!this.updateHandlers(old, handlers));

   }

   public void removeHandler(Handler handler) {
      while(true) {
         Handlers old = (Handlers)this._handlers.get();
         if (old != null && old._handlers.length != 0) {
            Handlers handlers = this.newHandlers((Handler[])ArrayUtil.removeFromArray(old._handlers, handler));
            if (!this.updateHandlers(old, handlers)) {
               continue;
            }
         }

         return;
      }
   }

   protected void expandChildren(List list, Class byClass) {
      Handler[] handlers = this.getHandlers();
      if (handlers != null) {
         for(Handler h : handlers) {
            this.expandHandler(h, list, byClass);
         }
      }

   }

   public void destroy() {
      if (!this.isStopped()) {
         throw new IllegalStateException("!STOPPED");
      } else {
         Handler[] children = this.getChildHandlers();
         this.setHandlers((Handler[])null);

         for(Handler child : children) {
            child.destroy();
         }

         super.destroy();
      }
   }

   protected static class Handlers {
      private final Handler[] _handlers;

      protected Handlers(Handler[] handlers) {
         this._handlers = handlers;
      }

      public Handler[] getHandlers() {
         return this._handlers;
      }
   }
}
