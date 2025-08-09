package org.apache.arrow.memory;

import java.lang.reflect.Field;
import org.apache.arrow.util.VisibleForTesting;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultAllocationManagerOption {
   public static final String ALLOCATION_MANAGER_TYPE_ENV_NAME = "ARROW_ALLOCATION_MANAGER_TYPE";
   public static final String ALLOCATION_MANAGER_TYPE_PROPERTY_NAME = "arrow.allocation.manager.type";
   static final Logger LOGGER = LoggerFactory.getLogger(DefaultAllocationManagerOption.class);
   private static AllocationManager.@Nullable Factory DEFAULT_ALLOCATION_MANAGER_FACTORY = null;

   @VisibleForTesting
   public static AllocationManagerType getDefaultAllocationManagerType() {
      AllocationManagerType ret = DefaultAllocationManagerOption.AllocationManagerType.Unknown;

      try {
         String envValue = System.getenv("ARROW_ALLOCATION_MANAGER_TYPE");
         ret = DefaultAllocationManagerOption.AllocationManagerType.valueOf(envValue);
      } catch (NullPointerException | IllegalArgumentException var3) {
      }

      try {
         String propValue = System.getProperty("arrow.allocation.manager.type");
         ret = DefaultAllocationManagerOption.AllocationManagerType.valueOf(propValue);
      } catch (NullPointerException | IllegalArgumentException var2) {
      }

      return ret;
   }

   static AllocationManager.Factory getDefaultAllocationManagerFactory() {
      if (DEFAULT_ALLOCATION_MANAGER_FACTORY != null) {
         return DEFAULT_ALLOCATION_MANAGER_FACTORY;
      } else {
         AllocationManagerType type = getDefaultAllocationManagerType();
         switch (type.ordinal()) {
            case 0:
               DEFAULT_ALLOCATION_MANAGER_FACTORY = getNettyFactory();
               break;
            case 1:
               DEFAULT_ALLOCATION_MANAGER_FACTORY = getUnsafeFactory();
               break;
            case 2:
               LOGGER.info("allocation manager type not specified, using netty as the default type");
               DEFAULT_ALLOCATION_MANAGER_FACTORY = getFactory(CheckAllocator.check());
               break;
            default:
               throw new IllegalStateException("Unknown allocation manager type: " + String.valueOf(type));
         }

         return DEFAULT_ALLOCATION_MANAGER_FACTORY;
      }
   }

   private static AllocationManager.Factory getFactory(String clazzName) {
      try {
         Field field = Class.forName(clazzName).getDeclaredField("FACTORY");
         field.setAccessible(true);
         return (AllocationManager.Factory)field.get((Object)null);
      } catch (Exception e) {
         throw new RuntimeException("Unable to instantiate Allocation Manager for " + clazzName, e);
      }
   }

   private static AllocationManager.Factory getUnsafeFactory() {
      try {
         return getFactory("org.apache.arrow.memory.unsafe.UnsafeAllocationManager");
      } catch (RuntimeException e) {
         throw new RuntimeException("Please add arrow-memory-unsafe to your classpath, No DefaultAllocationManager found to instantiate an UnsafeAllocationManager", e);
      }
   }

   private static AllocationManager.Factory getNettyFactory() {
      try {
         return getFactory("org.apache.arrow.memory.netty.NettyAllocationManager");
      } catch (RuntimeException e) {
         throw new RuntimeException("Please add arrow-memory-netty to your classpath, No DefaultAllocationManager found to instantiate an NettyAllocationManager", e);
      }
   }

   public static enum AllocationManagerType {
      Netty,
      Unsafe,
      Unknown;

      // $FF: synthetic method
      private static AllocationManagerType[] $values() {
         return new AllocationManagerType[]{Netty, Unsafe, Unknown};
      }
   }
}
