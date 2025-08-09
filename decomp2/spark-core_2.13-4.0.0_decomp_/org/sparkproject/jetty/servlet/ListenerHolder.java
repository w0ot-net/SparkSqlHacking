package org.sparkproject.jetty.servlet;

import jakarta.servlet.ServletContext;
import java.util.EventListener;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.thread.AutoLock;

public class ListenerHolder extends BaseHolder {
   private EventListener _listener;

   public ListenerHolder() {
      this(Source.EMBEDDED);
   }

   public ListenerHolder(Source source) {
      super(source);
   }

   public ListenerHolder(Class listenerClass) {
      super(Source.EMBEDDED);
      this.setHeldClass(listenerClass);
   }

   public EventListener getListener() {
      return this._listener;
   }

   public void setListener(EventListener listener) {
      this.setInstance(listener);
   }

   public void doStart() throws Exception {
      super.doStart();
      if (!EventListener.class.isAssignableFrom(this.getHeldClass())) {
         String msg = String.valueOf(this.getHeldClass()) + " is not a java.util.EventListener";
         super.stop();
         throw new IllegalStateException(msg);
      } else {
         ContextHandler contextHandler = null;
         if (this.getServletHandler() != null) {
            contextHandler = this.getServletHandler().getServletContextHandler();
         }

         if (contextHandler == null && ContextHandler.getCurrentContext() != null) {
            contextHandler = ContextHandler.getCurrentContext().getContextHandler();
         }

         if (contextHandler == null) {
            throw new IllegalStateException("No Context");
         } else {
            this._listener = (EventListener)this.getInstance();
            if (this._listener == null) {
               this._listener = this.createInstance();
               this._listener = (EventListener)this.wrap(this._listener, WrapFunction.class, WrapFunction::wrapEventListener);
            }

            contextHandler.addEventListener(this._listener);
         }
      }
   }

   protected EventListener createInstance() throws Exception {
      try (AutoLock l = this.lock()) {
         EventListener listener = (EventListener)super.createInstance();
         if (listener == null) {
            ServletContext ctx = this.getServletContext();
            if (ctx != null) {
               listener = ctx.createListener(this.getHeldClass());
            }
         }

         return listener;
      }
   }

   public void doStop() throws Exception {
      super.doStop();
      if (this._listener != null) {
         try {
            ContextHandler contextHandler = ContextHandler.getCurrentContext().getContextHandler();
            if (contextHandler != null) {
               contextHandler.removeEventListener(this._listener);
            }

            this.getServletHandler().destroyListener((EventListener)this.unwrap(this._listener));
         } finally {
            this._listener = null;
         }
      }

   }

   public String toString() {
      return String.format("%s@%x{src=%s}", this.getClassName(), this.hashCode(), this.getSource());
   }

   public static class Wrapper implements EventListener, BaseHolder.Wrapped {
      final EventListener _listener;

      public Wrapper(EventListener listener) {
         this._listener = listener;
      }

      public EventListener getWrapped() {
         return this._listener;
      }

      public String toString() {
         return String.format("%s:%s", this.getClass().getSimpleName(), this._listener.toString());
      }
   }

   public interface WrapFunction {
      EventListener wrapEventListener(EventListener var1);
   }
}
