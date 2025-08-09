package org.apache.logging.log4j.core.osgi;

import java.util.Dictionary;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.PluginRegistry;
import org.apache.logging.log4j.core.impl.Log4jProvider;
import org.apache.logging.log4j.core.impl.ThreadContextDataInjector;
import org.apache.logging.log4j.core.impl.ThreadContextDataProvider;
import org.apache.logging.log4j.core.util.ContextDataProvider;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ProviderActivator;
import org.osgi.annotation.bundle.Header;
import org.osgi.annotation.bundle.Headers;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.SynchronousBundleListener;
import org.osgi.framework.wiring.BundleWiring;

@Headers({@Header(
   name = "Bundle-Activator",
   value = "${@class}"
), @Header(
   name = "Bundle-ActivationPolicy",
   value = "lazy"
)})
public final class Activator extends ProviderActivator implements SynchronousBundleListener {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private final AtomicReference contextRef = new AtomicReference();
   private ServiceRegistration contextDataRegistration = null;

   public Activator() {
      super(new Log4jProvider());
   }

   public void start(final BundleContext context) throws Exception {
      super.start(context);
      ContextDataProvider threadContextProvider = new ThreadContextDataProvider();
      this.contextDataRegistration = context.registerService(ContextDataProvider.class, threadContextProvider, (Dictionary)null);
      loadContextProviders(context);
      if (PropertiesUtil.getProperties().getStringProperty("Log4jContextSelector") == null) {
         System.setProperty("Log4jContextSelector", BundleContextSelector.class.getName());
      }

      if (this.contextRef.compareAndSet((Object)null, context)) {
         context.addBundleListener(this);
         scanInstalledBundlesForPlugins(context);
      }

   }

   private static void scanInstalledBundlesForPlugins(final BundleContext context) {
      Bundle[] bundles = context.getBundles();

      for(Bundle bundle : bundles) {
         scanBundleForPlugins(bundle);
      }

   }

   private static void scanBundleForPlugins(final Bundle bundle) {
      long bundleId = bundle.getBundleId();
      if (bundle.getState() == 32 && bundleId != 0L) {
         LOGGER.trace("Scanning bundle [{}, id=%d] for plugins.", bundle.getSymbolicName(), bundleId);
         PluginRegistry.getInstance().loadFromBundle(bundleId, ((BundleWiring)bundle.adapt(BundleWiring.class)).getClassLoader());
      }

   }

   private static void loadContextProviders(final BundleContext bundleContext) {
      try {
         for(ServiceReference serviceReference : bundleContext.getServiceReferences(ContextDataProvider.class, (String)null)) {
            ContextDataProvider provider = (ContextDataProvider)bundleContext.getService(serviceReference);
            ThreadContextDataInjector.contextDataProviders.add(provider);
         }
      } catch (InvalidSyntaxException ex) {
         LOGGER.error("Error accessing context data provider", ex);
      }

   }

   private static void stopBundlePlugins(final Bundle bundle) {
      LOGGER.trace("Stopping bundle [{}] plugins.", bundle.getSymbolicName());
      PluginRegistry.getInstance().clearBundlePlugins(bundle.getBundleId());
   }

   public void stop(final BundleContext context) throws Exception {
      this.contextDataRegistration.unregister();
      this.contextRef.compareAndSet(context, (Object)null);
      LogManager.shutdown();
      super.stop(context);
   }

   public void bundleChanged(final BundleEvent event) {
      switch (event.getType()) {
         case 2:
            scanBundleForPlugins(event.getBundle());
            break;
         case 256:
            stopBundlePlugins(event.getBundle());
      }

   }
}
