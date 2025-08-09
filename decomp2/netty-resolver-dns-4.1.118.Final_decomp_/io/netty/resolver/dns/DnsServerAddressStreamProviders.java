package io.netty.resolver.dns;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class DnsServerAddressStreamProviders {
   private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(DnsServerAddressStreamProviders.class);
   private static final Constructor STREAM_PROVIDER_CONSTRUCTOR;
   private static final String MACOS_PROVIDER_CLASS_NAME = "io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider";

   private DnsServerAddressStreamProviders() {
   }

   public static DnsServerAddressStreamProvider platformDefault() {
      if (STREAM_PROVIDER_CONSTRUCTOR != null) {
         try {
            return (DnsServerAddressStreamProvider)STREAM_PROVIDER_CONSTRUCTOR.newInstance();
         } catch (IllegalAccessException var1) {
         } catch (InstantiationException var2) {
         } catch (InvocationTargetException var3) {
         }
      }

      return unixDefault();
   }

   public static DnsServerAddressStreamProvider unixDefault() {
      return DnsServerAddressStreamProviders.DefaultProviderHolder.DEFAULT_DNS_SERVER_ADDRESS_STREAM_PROVIDER;
   }

   static {
      Constructor<? extends DnsServerAddressStreamProvider> constructor = null;
      if (PlatformDependent.isOsx()) {
         try {
            Object maybeProvider = AccessController.doPrivileged(new PrivilegedAction() {
               public Object run() {
                  try {
                     return Class.forName("io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider", true, DnsServerAddressStreamProviders.class.getClassLoader());
                  } catch (Throwable cause) {
                     return cause;
                  }
               }
            });
            if (!(maybeProvider instanceof Class)) {
               throw (Throwable)maybeProvider;
            }

            Class<? extends DnsServerAddressStreamProvider> providerClass = (Class)maybeProvider;
            constructor = providerClass.getConstructor();
            constructor.newInstance();
            LOGGER.debug("{}: available", "io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider");
         } catch (ClassNotFoundException var3) {
            LOGGER.warn("Can not find {} in the classpath, fallback to system defaults. This may result in incorrect DNS resolutions on MacOS. Check whether you have a dependency on 'io.netty:netty-resolver-dns-native-macos'", "io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider");
         } catch (Throwable cause) {
            if (LOGGER.isDebugEnabled()) {
               LOGGER.error("Unable to load {}, fallback to system defaults. This may result in incorrect DNS resolutions on MacOS. Check whether you have a dependency on 'io.netty:netty-resolver-dns-native-macos'", "io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider", cause);
            } else {
               LOGGER.error("Unable to load {}, fallback to system defaults. This may result in incorrect DNS resolutions on MacOS. Check whether you have a dependency on 'io.netty:netty-resolver-dns-native-macos'. Use DEBUG level to see the full stack: {}", "io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider", cause.getCause() != null ? cause.getCause().toString() : cause.toString());
            }

            constructor = null;
         }
      }

      STREAM_PROVIDER_CONSTRUCTOR = constructor;
   }

   private static final class DefaultProviderHolder {
      private static final long REFRESH_INTERVAL;
      static final DnsServerAddressStreamProvider DEFAULT_DNS_SERVER_ADDRESS_STREAM_PROVIDER;

      static {
         REFRESH_INTERVAL = TimeUnit.MINUTES.toNanos(5L);
         DEFAULT_DNS_SERVER_ADDRESS_STREAM_PROVIDER = new DnsServerAddressStreamProvider() {
            private volatile DnsServerAddressStreamProvider currentProvider = this.provider();
            private final AtomicLong lastRefresh = new AtomicLong(System.nanoTime());

            public DnsServerAddressStream nameServerAddressStream(String hostname) {
               long last = this.lastRefresh.get();
               DnsServerAddressStreamProvider current = this.currentProvider;
               if (System.nanoTime() - last > DnsServerAddressStreamProviders.DefaultProviderHolder.REFRESH_INTERVAL && this.lastRefresh.compareAndSet(last, System.nanoTime())) {
                  current = this.currentProvider = this.provider();
               }

               return current.nameServerAddressStream(hostname);
            }

            private DnsServerAddressStreamProvider provider() {
               return (DnsServerAddressStreamProvider)(PlatformDependent.isWindows() ? DefaultDnsServerAddressStreamProvider.INSTANCE : UnixResolverDnsServerAddressStreamProvider.parseSilently());
            }
         };
      }
   }
}
