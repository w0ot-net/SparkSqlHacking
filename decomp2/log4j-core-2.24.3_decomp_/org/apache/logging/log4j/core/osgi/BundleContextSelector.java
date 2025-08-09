package org.apache.logging.log4j.core.osgi;

import java.lang.ref.WeakReference;
import java.net.URI;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.impl.ContextAnchor;
import org.apache.logging.log4j.core.selector.ClassLoaderContextSelector;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleReference;
import org.osgi.framework.FrameworkUtil;

public class BundleContextSelector extends ClassLoaderContextSelector {
   public void shutdown(final String fqcn, final ClassLoader loader, final boolean currentContext, final boolean allContexts) {
      LoggerContext ctx = null;
      Bundle bundle = null;
      if (currentContext) {
         ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
         ContextAnchor.THREAD_CONTEXT.remove();
      }

      if (ctx == null && loader instanceof BundleReference) {
         bundle = ((BundleReference)loader).getBundle();
         ctx = this.getLoggerContext(bundle);
         this.removeLoggerContext(ctx);
      }

      if (ctx == null) {
         Class<?> callerClass = StackLocatorUtil.getCallerClass(fqcn);
         if (callerClass != null) {
            bundle = FrameworkUtil.getBundle(callerClass);
            ctx = this.getLoggerContext(FrameworkUtil.getBundle(callerClass));
            this.removeLoggerContext(ctx);
         }
      }

      if (ctx == null) {
         ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
         ContextAnchor.THREAD_CONTEXT.remove();
      }

      if (ctx != null) {
         ctx.stop(50L, TimeUnit.MILLISECONDS);
      }

      if (bundle != null && allContexts) {
         Bundle[] bundles = bundle.getBundleContext().getBundles();

         for(Bundle bdl : bundles) {
            ctx = this.getLoggerContext(bdl);
            if (ctx != null) {
               ctx.stop(50L, TimeUnit.MILLISECONDS);
            }
         }
      }

   }

   private LoggerContext getLoggerContext(final Bundle bundle) {
      String name = ((Bundle)Objects.requireNonNull(bundle, "No Bundle provided")).getSymbolicName();
      AtomicReference<WeakReference<LoggerContext>> ref = (AtomicReference)CONTEXT_MAP.get(name);
      return ref != null && ref.get() != null ? (LoggerContext)((WeakReference)ref.get()).get() : null;
   }

   private void removeLoggerContext(final LoggerContext context) {
      CONTEXT_MAP.remove(context.getName());
   }

   public boolean hasContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
      if (currentContext && ContextAnchor.THREAD_CONTEXT.get() != null) {
         return ((LoggerContext)ContextAnchor.THREAD_CONTEXT.get()).isStarted();
      } else if (loader instanceof BundleReference) {
         return hasContext(((BundleReference)loader).getBundle());
      } else {
         Class<?> callerClass = StackLocatorUtil.getCallerClass(fqcn);
         if (callerClass != null) {
            return hasContext(FrameworkUtil.getBundle(callerClass));
         } else {
            return ContextAnchor.THREAD_CONTEXT.get() != null && ((LoggerContext)ContextAnchor.THREAD_CONTEXT.get()).isStarted();
         }
      }
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext, final URI configLocation) {
      if (currentContext) {
         LoggerContext ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
         return ctx != null ? ctx : this.getDefault();
      } else if (loader instanceof BundleReference) {
         return locateContext(((BundleReference)loader).getBundle(), configLocation);
      } else {
         Class<?> callerClass = StackLocatorUtil.getCallerClass(fqcn);
         if (callerClass != null) {
            return locateContext(FrameworkUtil.getBundle(callerClass), configLocation);
         } else {
            LoggerContext lc = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
            return lc == null ? this.getDefault() : lc;
         }
      }
   }

   private static boolean hasContext(final Bundle bundle) {
      String name = ((Bundle)Objects.requireNonNull(bundle, "No Bundle provided")).getSymbolicName();
      AtomicReference<WeakReference<LoggerContext>> ref = (AtomicReference)CONTEXT_MAP.get(name);
      return ref != null && ref.get() != null && ((WeakReference)ref.get()).get() != null && ((LoggerContext)((WeakReference)ref.get()).get()).isStarted();
   }

   private static LoggerContext locateContext(final Bundle bundle, final URI configLocation) {
      String name = ((Bundle)Objects.requireNonNull(bundle, "No Bundle provided")).getSymbolicName();
      AtomicReference<WeakReference<LoggerContext>> ref = (AtomicReference)CONTEXT_MAP.get(name);
      if (ref == null) {
         LoggerContext context = new LoggerContext(name, bundle, configLocation);
         CONTEXT_MAP.putIfAbsent(name, new AtomicReference(new WeakReference(context)));
         return (LoggerContext)((WeakReference)((AtomicReference)CONTEXT_MAP.get(name)).get()).get();
      } else {
         WeakReference<LoggerContext> r = (WeakReference)ref.get();
         LoggerContext ctx = (LoggerContext)r.get();
         if (ctx == null) {
            LoggerContext context = new LoggerContext(name, bundle, configLocation);
            ref.compareAndSet(r, new WeakReference(context));
            return (LoggerContext)((WeakReference)ref.get()).get();
         } else {
            URI oldConfigLocation = ctx.getConfigLocation();
            if (oldConfigLocation == null && configLocation != null) {
               LOGGER.debug("Setting bundle ({}) configuration to {}", name, configLocation);
               ctx.setConfigLocation(configLocation);
            } else if (oldConfigLocation != null && configLocation != null && !configLocation.equals(oldConfigLocation)) {
               LOGGER.warn("locateContext called with URI [{}], but existing LoggerContext has URI [{}]", configLocation, oldConfigLocation);
            }

            return ctx;
         }
      }
   }
}
