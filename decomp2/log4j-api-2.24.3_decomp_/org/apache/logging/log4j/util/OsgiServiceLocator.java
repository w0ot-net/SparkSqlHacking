package org.apache.logging.log4j.util;

import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleRevision;

public class OsgiServiceLocator {
   private static final Logger LOGGER = StatusLogger.getLogger();
   private static final boolean OSGI_AVAILABLE = checkOsgiAvailable();

   private static boolean checkOsgiAvailable() {
      try {
         Class<?> clazz = Class.forName("org.osgi.framework.FrameworkUtil");
         return clazz.getMethod("getBundle", Class.class).invoke((Object)null, OsgiServiceLocator.class) != null;
      } catch (NoSuchMethodException | LinkageError | ClassNotFoundException var1) {
         return false;
      } catch (Throwable error) {
         LOGGER.error("Unknown error checking OSGI environment.", error);
         return false;
      }
   }

   public static boolean isAvailable() {
      return OSGI_AVAILABLE;
   }

   public static Stream loadServices(final Class serviceType, final MethodHandles.Lookup lookup) {
      return loadServices(serviceType, lookup, true);
   }

   public static Stream loadServices(final Class serviceType, final MethodHandles.Lookup lookup, final boolean verbose) {
      Class<?> lookupClass = ((MethodHandles.Lookup)Objects.requireNonNull(lookup, "lookup")).lookupClass();
      return loadServices(serviceType, lookupClass, StatusLogger.getLogger());
   }

   static Stream loadServices(final Class serviceType, final Class callerClass, final Logger logger) {
      Bundle bundle = FrameworkUtil.getBundle(callerClass);
      if (bundle != null && !isFragment(bundle)) {
         BundleContext ctx = bundle.getBundleContext();
         if (ctx == null) {
            Supplier[] var10002 = new Supplier[3];
            Objects.requireNonNull(serviceType);
            var10002[0] = serviceType::getName;
            Objects.requireNonNull(bundle);
            var10002[1] = bundle::getSymbolicName;
            var10002[2] = () -> {
               switch (bundle.getState()) {
                  case 1:
                     return "UNINSTALLED";
                  case 2:
                     return "INSTALLED";
                  case 4:
                     return "RESOLVED";
                  case 8:
                     return "STARTING";
                  case 16:
                     return "STOPPING";
                  case 32:
                     return "ACTIVE";
                  default:
                     return "UNKNOWN";
               }
            };
            logger.warn("Unable to load OSGi services for service {}: bundle {} (state {}) does not have a valid BundleContext", var10002);
         } else {
            try {
               Stream var10000 = ctx.getServiceReferences(serviceType, (String)null).stream();
               Objects.requireNonNull(ctx);
               return var10000.map(ctx::getService);
            } catch (Exception e) {
               logger.error((String)"Unable to load OSGI services for service {}", (Object)serviceType, (Object)e);
            }
         }
      }

      return Stream.empty();
   }

   private static boolean isFragment(final Bundle bundle) {
      try {
         return (((BundleRevision)bundle.adapt(BundleRevision.class)).getTypes() & 1) != 0;
      } catch (SecurityException var2) {
         return false;
      }
   }
}
