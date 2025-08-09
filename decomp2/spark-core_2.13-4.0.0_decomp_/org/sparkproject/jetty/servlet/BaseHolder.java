package org.sparkproject.jetty.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.UnavailableException;
import java.io.IOException;
import java.util.function.BiFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.Loader;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.thread.AutoLock;

public abstract class BaseHolder extends AbstractLifeCycle implements Dumpable {
   private static final Logger LOG = LoggerFactory.getLogger(BaseHolder.class);
   private final AutoLock _lock = new AutoLock();
   private final Source _source;
   private Class _class;
   private String _className;
   private Object _instance;
   private ServletHandler _servletHandler;

   protected BaseHolder(Source source) {
      this._source = source;
   }

   public Source getSource() {
      return this._source;
   }

   AutoLock lock() {
      return this._lock.lock();
   }

   boolean lockIsHeldByCurrentThread() {
      return this._lock.isHeldByCurrentThread();
   }

   public void initialize() throws Exception {
      if (!this.isStarted()) {
         throw new IllegalStateException("Not started: " + String.valueOf(this));
      }
   }

   public void doStart() throws Exception {
      if (this._class != null || this._className != null && !this._className.isEmpty()) {
         if (this._class == null) {
            try {
               this._class = Loader.loadClass(this._className);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Holding {} from {}", this._class, this._class.getClassLoader());
               }
            } catch (Exception e) {
               LOG.warn("Unable to load class {}", this._className, e);
               throw new UnavailableException("Class loading error for holder " + this.toString());
            }
         }

      } else {
         throw new UnavailableException("No class in holder " + this.toString());
      }
   }

   public void doStop() throws Exception {
      if (this._instance == null) {
         this._class = null;
      }

   }

   @ManagedAttribute(
      value = "Class Name",
      readonly = true
   )
   public String getClassName() {
      return this._className;
   }

   public Class getHeldClass() {
      return this._class;
   }

   public ServletHandler getServletHandler() {
      return this._servletHandler;
   }

   public void setServletHandler(ServletHandler servletHandler) {
      this._servletHandler = servletHandler;
   }

   public void setClassName(String className) {
      this._className = className;
      this._class = null;
   }

   public void setHeldClass(Class held) {
      this._class = held;
      if (held != null) {
         this._className = held.getName();
      }

   }

   protected void illegalStateIfContextStarted() {
      if (this._servletHandler != null) {
         ServletContext context = this._servletHandler.getServletContext();
         if (context instanceof ContextHandler.Context && ((ContextHandler.Context)context).getContextHandler().isStarted()) {
            throw new IllegalStateException("Started");
         }
      }

   }

   protected void setInstance(Object instance) {
      try (AutoLock l = this.lock()) {
         this._instance = instance;
         if (instance == null) {
            this.setHeldClass((Class)null);
         } else {
            this.setHeldClass(instance.getClass());
         }
      }

   }

   protected Object getInstance() {
      try (AutoLock l = this.lock()) {
         return this._instance;
      }
   }

   protected Object createInstance() throws Exception {
      try (AutoLock l = this.lock()) {
         ServletContext ctx = this.getServletContext();
         if (ctx == null) {
            return this.getHeldClass().getDeclaredConstructor().newInstance();
         } else if (ServletContextHandler.Context.class.isAssignableFrom(ctx.getClass())) {
            return ((ServletContextHandler.Context)ctx).createInstance(this);
         } else {
            return null;
         }
      }
   }

   public ServletContext getServletContext() {
      ServletContext scontext = null;
      if (this.getServletHandler() != null) {
         scontext = this.getServletHandler().getServletContext();
      }

      if (scontext != null) {
         return scontext;
      } else {
         ContextHandler.Context ctx = ContextHandler.getCurrentContext();
         if (ctx != null) {
            ContextHandler contextHandler = ctx.getContextHandler();
            if (contextHandler != null) {
               return contextHandler.getServletContext();
            }
         }

         return null;
      }
   }

   public boolean isInstance() {
      boolean var2;
      try (AutoLock l = this.lock()) {
         var2 = this._instance != null;
      }

      return var2;
   }

   protected Object wrap(Object component, Class wrapperFunctionType, BiFunction function) {
      T ret = component;
      ServletContextHandler contextHandler = this.getServletHandler().getServletContextHandler();
      if (contextHandler == null) {
         ContextHandler.Context context = ContextHandler.getCurrentContext();
         contextHandler = (ServletContextHandler)(context == null ? null : context.getContextHandler());
      }

      if (contextHandler != null) {
         for(Object wrapperFunction : contextHandler.getBeans(wrapperFunctionType)) {
            ret = (T)function.apply(wrapperFunction, ret);
         }
      }

      return ret;
   }

   protected Object unwrap(Object component) {
      T ret;
      for(ret = component; ret instanceof Wrapped; ret = (T)((Wrapped)ret).getWrapped()) {
      }

      return ret;
   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObject(out, this);
   }

   public String dump() {
      return Dumpable.dump(this);
   }

   interface Wrapped {
      Object getWrapped();
   }
}
