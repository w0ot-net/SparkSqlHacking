package org.apache.datasketches.theta;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.datasketches.common.SuppressFBWarnings;

final class ConcurrentPropagationService {
   static int NUM_POOL_THREADS = 3;
   private static volatile ConcurrentPropagationService instance = null;
   private static ExecutorService[] propagationExecutorService = null;

   @SuppressFBWarnings(
      value = {"ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD"},
      justification = "Fix later"
   )
   private ConcurrentPropagationService() {
      propagationExecutorService = new ExecutorService[NUM_POOL_THREADS];
   }

   @SuppressFBWarnings(
      value = {"SSD_DO_NOT_USE_INSTANCE_LOCK_ON_SHARED_STATIC_DATA"},
      justification = "Fix later"
   )
   private static ConcurrentPropagationService getInstance() {
      if (instance == null) {
         synchronized(ConcurrentPropagationService.class) {
            if (instance == null) {
               instance = new ConcurrentPropagationService();
            }
         }
      }

      return instance;
   }

   public static ExecutorService getExecutorService(long id) {
      return getInstance().initExecutorService((int)id % NUM_POOL_THREADS);
   }

   public static ExecutorService resetExecutorService(long id) {
      getInstance();
      return propagationExecutorService[(int)id % NUM_POOL_THREADS] = null;
   }

   private ExecutorService initExecutorService(int i) {
      if (propagationExecutorService[i] == null) {
         propagationExecutorService[i] = Executors.newSingleThreadExecutor();
      }

      return propagationExecutorService[i];
   }
}
