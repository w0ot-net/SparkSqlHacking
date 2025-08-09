package io.netty.bootstrap;

import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

abstract class ChannelInitializerExtensions {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(ChannelInitializerExtensions.class);
   private static volatile ChannelInitializerExtensions implementation;

   private ChannelInitializerExtensions() {
   }

   static ChannelInitializerExtensions getExtensions() {
      ChannelInitializerExtensions impl = implementation;
      if (impl == null) {
         synchronized(ChannelInitializerExtensions.class) {
            impl = implementation;
            if (impl != null) {
               return impl;
            }

            String extensionProp = SystemPropertyUtil.get("io.netty.bootstrap.extensions");
            logger.debug("-Dio.netty.bootstrap.extensions: {}", extensionProp);
            if ("serviceload".equalsIgnoreCase(extensionProp)) {
               impl = new ServiceLoadingExtensions(true);
            } else if ("log".equalsIgnoreCase(extensionProp)) {
               impl = new ServiceLoadingExtensions(false);
            } else {
               impl = new EmptyExtensions();
            }

            implementation = impl;
         }
      }

      return impl;
   }

   abstract Collection extensions(ClassLoader var1);

   private static final class EmptyExtensions extends ChannelInitializerExtensions {
      private EmptyExtensions() {
      }

      Collection extensions(ClassLoader cl) {
         return Collections.emptyList();
      }
   }

   private static final class ServiceLoadingExtensions extends ChannelInitializerExtensions {
      private final boolean loadAndCache;
      private WeakReference classLoader;
      private Collection extensions;

      ServiceLoadingExtensions(boolean loadAndCache) {
         this.loadAndCache = loadAndCache;
      }

      synchronized Collection extensions(ClassLoader cl) {
         ClassLoader configured = this.classLoader == null ? null : (ClassLoader)this.classLoader.get();
         if (configured == null || configured != cl) {
            Collection<ChannelInitializerExtension> loaded = serviceLoadExtensions(this.loadAndCache, cl);
            this.classLoader = new WeakReference(cl);
            this.extensions = (Collection)(this.loadAndCache ? loaded : Collections.emptyList());
         }

         return this.extensions;
      }

      private static Collection serviceLoadExtensions(boolean load, ClassLoader cl) {
         List<ChannelInitializerExtension> extensions = new ArrayList();

         for(ChannelInitializerExtension extension : ServiceLoader.load(ChannelInitializerExtension.class, cl)) {
            extensions.add(extension);
         }

         if (!extensions.isEmpty()) {
            Collections.sort(extensions, new Comparator() {
               public int compare(ChannelInitializerExtension a, ChannelInitializerExtension b) {
                  return Double.compare(a.priority(), b.priority());
               }
            });
            ChannelInitializerExtensions.logger.info("ServiceLoader {}(s) {}: {}", new Object[]{ChannelInitializerExtension.class.getSimpleName(), load ? "registered" : "detected", extensions});
            return Collections.unmodifiableList(extensions);
         } else {
            ChannelInitializerExtensions.logger.debug("ServiceLoader {}(s) {}: []", ChannelInitializerExtension.class.getSimpleName(), load ? "registered" : "detected");
            return Collections.emptyList();
         }
      }
   }
}
