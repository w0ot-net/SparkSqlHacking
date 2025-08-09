package org.glassfish.jersey.innate;

import jakarta.ws.rs.core.Configuration;
import java.util.concurrent.ThreadFactory;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.innate.virtual.LoomishExecutors;

public final class VirtualThreadUtil {
   private static final boolean USE_VIRTUAL_THREADS_BY_DEFAULT = false;

   private VirtualThreadUtil() {
      throw new IllegalStateException();
   }

   public static LoomishExecutors withConfig(Configuration config) {
      return withConfig(config, false);
   }

   public static LoomishExecutors withConfig(Configuration config, boolean useVirtualByDefault) {
      ThreadFactory tfThreadFactory = null;
      boolean useVirtualThreads = useVirtualThreads(config, useVirtualByDefault);
      if (config != null) {
         Object threadFactory = config.getProperty(CommonProperties.THREAD_FACTORY);
         if (threadFactory != null && ThreadFactory.class.isInstance(threadFactory)) {
            tfThreadFactory = (ThreadFactory)threadFactory;
         }
      }

      return tfThreadFactory == null ? VirtualThreadSupport.allowVirtual(useVirtualThreads) : VirtualThreadSupport.allowVirtual(useVirtualThreads, tfThreadFactory);
   }

   private static boolean useVirtualThreads(Configuration config, boolean useByDefault) {
      boolean bUseVirtualThreads = useByDefault;
      if (config != null) {
         Object useVirtualThread = config.getProperty(CommonProperties.USE_VIRTUAL_THREADS);
         if (useVirtualThread != null && Boolean.class.isInstance(useVirtualThread)) {
            bUseVirtualThreads = (Boolean)useVirtualThread;
         }

         if (useVirtualThread != null && String.class.isInstance(useVirtualThread)) {
            bUseVirtualThreads = Boolean.parseBoolean(useVirtualThread.toString());
         }
      }

      return bUseVirtualThreads;
   }
}
