package org.apache.logging.log4j.util;

import aQute.bnd.annotation.spi.ServiceConsumer;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.internal.SimpleProvider;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.status.StatusLogger;

@InternalApi
@ServiceConsumer(
   value = Provider.class,
   resolution = "optional",
   cardinality = "multiple"
)
public final class ProviderUtil {
   static final String PROVIDER_RESOURCE = "META-INF/log4j-provider.properties";
   static final Collection PROVIDERS = new HashSet();
   static final Lock STARTUP_LOCK = new ReentrantLock();
   private static final String[] COMPATIBLE_API_VERSIONS = new String[]{"2.6.0"};
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static volatile Provider PROVIDER;

   private ProviderUtil() {
   }

   static void addProvider(final Provider provider) {
      if (validVersion(provider.getVersions())) {
         PROVIDERS.add(provider);
         LOGGER.debug((String)"Loaded provider:\n{}", (Object)provider);
      } else {
         LOGGER.warn((String)"Ignoring provider for incompatible version {}:\n{}", (Object)provider.getVersions(), (Object)provider);
      }

   }

   @SuppressFBWarnings(
      value = {"URLCONNECTION_SSRF_FD"},
      justification = "Uses a fixed URL that ends in 'META-INF/log4j-provider.properties'."
   )
   static void loadProvider(final URL url, final ClassLoader cl) {
      try {
         Properties props = PropertiesUtil.loadClose(url.openStream(), url);
         addProvider(new Provider(props, url, cl));
      } catch (IOException e) {
         LOGGER.error((String)"Unable to open {}", (Object)url, (Object)e);
      }

   }

   /** @deprecated */
   @Deprecated
   static void loadProviders(final Enumeration urls, final ClassLoader cl) {
      if (urls != null) {
         while(urls.hasMoreElements()) {
            loadProvider((URL)urls.nextElement(), cl);
         }
      }

   }

   public static Provider getProvider() {
      lazyInit();
      return PROVIDER;
   }

   public static Iterable getProviders() {
      lazyInit();
      return PROVIDERS;
   }

   public static boolean hasProviders() {
      lazyInit();
      return !PROVIDERS.isEmpty();
   }

   static void lazyInit() {
      if (PROVIDER == null) {
         try {
            STARTUP_LOCK.lockInterruptibly();

            try {
               if (PROVIDER == null) {
                  ServiceLoaderUtil.safeStream(Provider.class, ServiceLoader.load(Provider.class, ProviderUtil.class.getClassLoader()), LOGGER).filter((provider) -> validVersion(provider.getVersions())).forEach(ProviderUtil::addProvider);

                  for(LoaderUtil.UrlResource resource : LoaderUtil.findUrlResources("META-INF/log4j-provider.properties", false)) {
                     loadProvider(resource.getUrl(), resource.getClassLoader());
                  }

                  PROVIDER = selectProvider(PropertiesUtil.getProperties(), PROVIDERS, LOGGER);
               }
            } finally {
               STARTUP_LOCK.unlock();
            }
         } catch (InterruptedException e) {
            LOGGER.fatal((String)"Interrupted before Log4j Providers could be loaded.", (Throwable)e);
            Thread.currentThread().interrupt();
         }
      }

   }

   static Provider selectProvider(final PropertiesUtil properties, final Collection providers, final Logger statusLogger) {
      Provider selected = null;
      String providerClass = properties.getStringProperty("log4j.provider");
      if (providerClass != null) {
         if (SimpleProvider.class.getName().equals(providerClass)) {
            selected = new SimpleProvider();
         } else {
            try {
               selected = (Provider)LoaderUtil.newInstanceOf(providerClass);
            } catch (Exception e) {
               statusLogger.error((String)"Unable to create provider {}.\nFalling back to default selection process.", (Object)PROVIDER, (Object)e);
            }
         }
      }

      String factoryClassName = properties.getStringProperty("log4j2.loggerContextFactory");
      if (factoryClassName != null) {
         if (selected != null) {
            statusLogger.warn((String)"Ignoring {} system property, since {} was set.", (Object)"log4j2.loggerContextFactory", (Object)"log4j.provider");
         } else {
            statusLogger.warn((String)"Usage of the {} property is deprecated. Use the {} property instead.", (Object)"log4j2.loggerContextFactory", (Object)"log4j.provider");

            for(Provider provider : providers) {
               if (factoryClassName.equals(provider.getClassName())) {
                  selected = provider;
                  break;
               }
            }
         }

         if (selected == null) {
            statusLogger.warn((String)"No provider found using {} as logger context factory. The factory will be instantiated directly.", (Object)factoryClassName);

            try {
               Class<?> clazz = LoaderUtil.loadClass(factoryClassName);
               if (LoggerContextFactory.class.isAssignableFrom(clazz)) {
                  selected = new Provider((Integer)null, "", clazz.asSubclass(LoggerContextFactory.class));
               } else {
                  statusLogger.error((String)"Class {} specified in the {} system property does not extend {}", (Object)factoryClassName, "log4j2.loggerContextFactory", LoggerContextFactory.class.getName());
               }
            } catch (Exception e) {
               statusLogger.error((String)"Unable to create class {} specified in the {} system property", (Object)factoryClassName, "log4j2.loggerContextFactory", e);
            }
         }
      }

      if (selected == null) {
         Comparator<Provider> comparator = Comparator.comparing(Provider::getPriority);
         switch (providers.size()) {
            case 0:
               statusLogger.error("Log4j API could not find a logging provider.");
            case 1:
               break;
            default:
               statusLogger.warn((String)providers.stream().sorted(comparator).map(Provider::toString).collect(Collectors.joining("\n", "Log4j API found multiple logging providers:\n", "")));
         }

         selected = (Provider)providers.stream().max(comparator).orElseGet(SimpleProvider::new);
      }

      statusLogger.info((String)"Using provider:\n{}", (Object)selected);
      return selected;
   }

   public static ClassLoader findClassLoader() {
      return LoaderUtil.getThreadContextClassLoader();
   }

   private static boolean validVersion(final String version) {
      for(String v : COMPATIBLE_API_VERSIONS) {
         if (version.startsWith(v)) {
            return true;
         }
      }

      return false;
   }
}
