package org.apache.logging.log4j.core.selector;

import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.impl.ContextAnchor;
import org.apache.logging.log4j.spi.LoggerContextShutdownAware;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Lazy;
import org.apache.logging.log4j.util.StackLocatorUtil;

public class ClassLoaderContextSelector implements ContextSelector, LoggerContextShutdownAware {
   protected static final StatusLogger LOGGER = StatusLogger.getLogger();
   protected static final ConcurrentMap CONTEXT_MAP = new ConcurrentHashMap();
   private final Lazy defaultLoggerContext = Lazy.lazy(() -> this.createContext(this.defaultContextName(), (URI)null));

   public void shutdown(final String fqcn, final ClassLoader loader, final boolean currentContext, final boolean allContexts) {
      LoggerContext ctx = null;
      if (currentContext) {
         ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
      } else if (loader != null) {
         ctx = this.findContext(loader);
      } else {
         Class<?> clazz = StackLocatorUtil.getCallerClass(fqcn);
         if (clazz != null) {
            ctx = this.findContext(clazz.getClassLoader());
         }

         if (ctx == null) {
            ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
         }
      }

      if (ctx != null) {
         ctx.stop(50L, TimeUnit.MILLISECONDS);
      }

   }

   public void contextShutdown(final org.apache.logging.log4j.spi.LoggerContext loggerContext) {
      if (loggerContext instanceof LoggerContext) {
         this.removeContext((LoggerContext)loggerContext);
      }

   }

   public boolean hasContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
      LoggerContext ctx;
      if (currentContext) {
         ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
      } else if (loader != null) {
         ctx = this.findContext(loader);
      } else {
         Class<?> clazz = StackLocatorUtil.getCallerClass(fqcn);
         if (clazz != null) {
            ctx = this.findContext(clazz.getClassLoader());
         } else {
            ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
         }
      }

      return ctx != null && ctx.isStarted();
   }

   private LoggerContext findContext(final ClassLoader loaderOrNull) {
      ClassLoader loader = loaderOrNull != null ? loaderOrNull : ClassLoader.getSystemClassLoader();
      String name = this.toContextMapKey(loader);
      AtomicReference<WeakReference<LoggerContext>> ref = (AtomicReference)CONTEXT_MAP.get(name);
      if (ref != null) {
         WeakReference<LoggerContext> weakRef = (WeakReference)ref.get();
         return (LoggerContext)weakRef.get();
      } else {
         return null;
      }
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
      return this.getContext(fqcn, loader, currentContext, (URI)null);
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext, final URI configLocation) {
      return this.getContext(fqcn, loader, (Map.Entry)null, currentContext, configLocation);
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final Map.Entry entry, final boolean currentContext, final URI configLocation) {
      if (currentContext) {
         LoggerContext ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
         return ctx != null ? ctx : this.getDefault();
      } else if (loader != null) {
         return this.locateContext(loader, entry, configLocation);
      } else {
         Class<?> clazz = StackLocatorUtil.getCallerClass(fqcn);
         if (clazz != null) {
            return this.locateContext(clazz.getClassLoader(), entry, configLocation);
         } else {
            LoggerContext lc = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
            return lc != null ? lc : this.getDefault();
         }
      }
   }

   public void removeContext(final LoggerContext context) {
      for(Map.Entry entry : CONTEXT_MAP.entrySet()) {
         LoggerContext ctx = (LoggerContext)((WeakReference)((AtomicReference)entry.getValue()).get()).get();
         if (ctx == context) {
            CONTEXT_MAP.remove(entry.getKey());
         }
      }

   }

   public boolean isClassLoaderDependent() {
      return true;
   }

   public List getLoggerContexts() {
      List<LoggerContext> list = new ArrayList();

      for(AtomicReference ref : CONTEXT_MAP.values()) {
         LoggerContext ctx = (LoggerContext)((WeakReference)ref.get()).get();
         if (ctx != null) {
            list.add(ctx);
         }
      }

      return Collections.unmodifiableList(list);
   }

   private LoggerContext locateContext(final ClassLoader loaderOrNull, final Map.Entry entry, final URI configLocation) {
      ClassLoader loader = loaderOrNull != null ? loaderOrNull : ClassLoader.getSystemClassLoader();
      String name = this.toContextMapKey(loader);
      AtomicReference<WeakReference<LoggerContext>> ref = (AtomicReference)CONTEXT_MAP.get(name);
      if (ref == null) {
         if (configLocation == null) {
            for(ClassLoader parent = loader.getParent(); parent != null; parent = parent.getParent()) {
               ref = (AtomicReference)CONTEXT_MAP.get(this.toContextMapKey(parent));
               if (ref != null) {
                  WeakReference<LoggerContext> r = (WeakReference)ref.get();
                  LoggerContext ctx = (LoggerContext)r.get();
                  if (ctx != null) {
                     return ctx;
                  }
               }
            }
         }

         LoggerContext ctx = this.createContext(name, configLocation);
         if (entry != null) {
            ctx.putObject((String)entry.getKey(), entry.getValue());
         }

         LoggerContext newContext = (LoggerContext)((WeakReference)((AtomicReference)CONTEXT_MAP.computeIfAbsent(name, (k) -> new AtomicReference(new WeakReference(ctx)))).get()).get();
         if (newContext == ctx) {
            ctx.addShutdownListener(this);
         }

         return newContext;
      } else {
         WeakReference<LoggerContext> weakRef = (WeakReference)ref.get();
         LoggerContext ctx = (LoggerContext)weakRef.get();
         if (ctx != null) {
            if (entry != null && ctx.getObject((String)entry.getKey()) == null) {
               ctx.putObject((String)entry.getKey(), entry.getValue());
            }

            if (ctx.getConfigLocation() == null && configLocation != null) {
               LOGGER.debug("Setting configuration to {}", configLocation);
               ctx.setConfigLocation(configLocation);
            } else if (ctx.getConfigLocation() != null && configLocation != null && !ctx.getConfigLocation().equals(configLocation)) {
               LOGGER.warn("locateContext called with URI {}. Existing LoggerContext has URI {}", configLocation, ctx.getConfigLocation());
            }

            return ctx;
         } else {
            ctx = this.createContext(name, configLocation);
            if (entry != null) {
               ctx.putObject((String)entry.getKey(), entry.getValue());
            }

            ref.compareAndSet(weakRef, new WeakReference(ctx));
            return ctx;
         }
      }
   }

   protected LoggerContext createContext(final String name, final URI configLocation) {
      return new LoggerContext(name, (Object)null, configLocation);
   }

   protected String toContextMapKey(final ClassLoader loader) {
      return Integer.toHexString(System.identityHashCode(loader));
   }

   protected LoggerContext getDefault() {
      return (LoggerContext)this.defaultLoggerContext.get();
   }

   protected String defaultContextName() {
      return "Default";
   }
}
