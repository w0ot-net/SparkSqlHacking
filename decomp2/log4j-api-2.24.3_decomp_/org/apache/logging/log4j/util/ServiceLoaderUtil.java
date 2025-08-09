package org.apache.logging.log4j.util;

import aQute.bnd.annotation.baseline.BaselineIgnore;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.logging.log4j.Logger;

@InternalApi
@BaselineIgnore("2.24.0")
public final class ServiceLoaderUtil {
   private static final int MAX_BROKEN_SERVICES = 8;

   private ServiceLoaderUtil() {
   }

   public static Stream safeStream(final Class serviceType, final ServiceLoader serviceLoader, final Logger logger) {
      Objects.requireNonNull(serviceLoader, "serviceLoader");
      Collection<Class<?>> classes = new HashSet();
      Stream<S> services = StreamSupport.stream(new ServiceLoaderSpliterator(serviceType, serviceLoader, logger), false);
      Class<?> callerClass = StackLocatorUtil.getCallerClass(2);
      Stream<S> allServices = OsgiServiceLocator.isAvailable() && callerClass != null ? Stream.concat(services, OsgiServiceLocator.loadServices(serviceType, callerClass, logger)) : services;
      return allServices.filter((service) -> classes.add(service.getClass()));
   }

   private static final class ServiceLoaderSpliterator extends Spliterators.AbstractSpliterator {
      private final String serviceName;
      private final Iterator serviceIterator;
      private final Logger logger;

      private ServiceLoaderSpliterator(final Class serviceType, final Iterable serviceLoader, final Logger logger) {
         super(Long.MAX_VALUE, 1296);
         this.serviceName = serviceType.getName();
         this.serviceIterator = serviceLoader.iterator();
         this.logger = logger;
      }

      public boolean tryAdvance(final Consumer action) {
         int i = 8;

         while(i-- > 0) {
            try {
               if (this.serviceIterator.hasNext()) {
                  action.accept(this.serviceIterator.next());
                  return true;
               }
            } catch (LinkageError | ServiceConfigurationError e) {
               this.logger.warn((String)"Unable to load implementation for service {}", (Object)this.serviceName, (Object)e);
            } catch (Exception e) {
               this.logger.warn((String)"Unexpected exception  while loading implementation for service {}", (Object)this.serviceName, (Object)e);
               throw e;
            }
         }

         return false;
      }
   }
}
