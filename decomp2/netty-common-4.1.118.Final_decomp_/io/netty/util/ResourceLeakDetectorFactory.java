package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Constructor;

public abstract class ResourceLeakDetectorFactory {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(ResourceLeakDetectorFactory.class);
   private static volatile ResourceLeakDetectorFactory factoryInstance = new DefaultResourceLeakDetectorFactory();

   public static ResourceLeakDetectorFactory instance() {
      return factoryInstance;
   }

   public static void setResourceLeakDetectorFactory(ResourceLeakDetectorFactory factory) {
      factoryInstance = (ResourceLeakDetectorFactory)ObjectUtil.checkNotNull(factory, "factory");
   }

   public final ResourceLeakDetector newResourceLeakDetector(Class resource) {
      return this.newResourceLeakDetector(resource, ResourceLeakDetector.SAMPLING_INTERVAL);
   }

   /** @deprecated */
   @Deprecated
   public abstract ResourceLeakDetector newResourceLeakDetector(Class var1, int var2, long var3);

   public ResourceLeakDetector newResourceLeakDetector(Class resource, int samplingInterval) {
      ObjectUtil.checkPositive(samplingInterval, "samplingInterval");
      return this.newResourceLeakDetector(resource, samplingInterval, Long.MAX_VALUE);
   }

   private static final class DefaultResourceLeakDetectorFactory extends ResourceLeakDetectorFactory {
      private final Constructor obsoleteCustomClassConstructor;
      private final Constructor customClassConstructor;

      DefaultResourceLeakDetectorFactory() {
         String customLeakDetector;
         try {
            customLeakDetector = SystemPropertyUtil.get("io.netty.customResourceLeakDetector");
         } catch (Throwable cause) {
            ResourceLeakDetectorFactory.logger.error("Could not access System property: io.netty.customResourceLeakDetector", cause);
            customLeakDetector = null;
         }

         if (customLeakDetector == null) {
            this.obsoleteCustomClassConstructor = this.customClassConstructor = null;
         } else {
            this.obsoleteCustomClassConstructor = obsoleteCustomClassConstructor(customLeakDetector);
            this.customClassConstructor = customClassConstructor(customLeakDetector);
         }

      }

      private static Constructor obsoleteCustomClassConstructor(String customLeakDetector) {
         try {
            Class<?> detectorClass = Class.forName(customLeakDetector, true, PlatformDependent.getSystemClassLoader());
            if (ResourceLeakDetector.class.isAssignableFrom(detectorClass)) {
               return detectorClass.getConstructor(Class.class, Integer.TYPE, Long.TYPE);
            }

            ResourceLeakDetectorFactory.logger.error("Class {} does not inherit from ResourceLeakDetector.", (Object)customLeakDetector);
         } catch (Throwable t) {
            ResourceLeakDetectorFactory.logger.error("Could not load custom resource leak detector class provided: {}", customLeakDetector, t);
         }

         return null;
      }

      private static Constructor customClassConstructor(String customLeakDetector) {
         try {
            Class<?> detectorClass = Class.forName(customLeakDetector, true, PlatformDependent.getSystemClassLoader());
            if (ResourceLeakDetector.class.isAssignableFrom(detectorClass)) {
               return detectorClass.getConstructor(Class.class, Integer.TYPE);
            }

            ResourceLeakDetectorFactory.logger.error("Class {} does not inherit from ResourceLeakDetector.", (Object)customLeakDetector);
         } catch (Throwable t) {
            ResourceLeakDetectorFactory.logger.error("Could not load custom resource leak detector class provided: {}", customLeakDetector, t);
         }

         return null;
      }

      public ResourceLeakDetector newResourceLeakDetector(Class resource, int samplingInterval, long maxActive) {
         if (this.obsoleteCustomClassConstructor != null) {
            try {
               ResourceLeakDetector<T> leakDetector = (ResourceLeakDetector)this.obsoleteCustomClassConstructor.newInstance(resource, samplingInterval, maxActive);
               ResourceLeakDetectorFactory.logger.debug("Loaded custom ResourceLeakDetector: {}", (Object)this.obsoleteCustomClassConstructor.getDeclaringClass().getName());
               return leakDetector;
            } catch (Throwable t) {
               ResourceLeakDetectorFactory.logger.error("Could not load custom resource leak detector provided: {} with the given resource: {}", this.obsoleteCustomClassConstructor.getDeclaringClass().getName(), resource, t);
            }
         }

         ResourceLeakDetector<T> resourceLeakDetector = new ResourceLeakDetector(resource, samplingInterval, maxActive);
         ResourceLeakDetectorFactory.logger.debug("Loaded default ResourceLeakDetector: {}", (Object)resourceLeakDetector);
         return resourceLeakDetector;
      }

      public ResourceLeakDetector newResourceLeakDetector(Class resource, int samplingInterval) {
         if (this.customClassConstructor != null) {
            try {
               ResourceLeakDetector<T> leakDetector = (ResourceLeakDetector)this.customClassConstructor.newInstance(resource, samplingInterval);
               ResourceLeakDetectorFactory.logger.debug("Loaded custom ResourceLeakDetector: {}", (Object)this.customClassConstructor.getDeclaringClass().getName());
               return leakDetector;
            } catch (Throwable t) {
               ResourceLeakDetectorFactory.logger.error("Could not load custom resource leak detector provided: {} with the given resource: {}", this.customClassConstructor.getDeclaringClass().getName(), resource, t);
            }
         }

         ResourceLeakDetector<T> resourceLeakDetector = new ResourceLeakDetector(resource, samplingInterval);
         ResourceLeakDetectorFactory.logger.debug("Loaded default ResourceLeakDetector: {}", (Object)resourceLeakDetector);
         return resourceLeakDetector;
      }
   }
}
