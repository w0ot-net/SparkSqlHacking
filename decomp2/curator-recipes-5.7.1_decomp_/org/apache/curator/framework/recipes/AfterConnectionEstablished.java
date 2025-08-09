package org.apache.curator.framework.recipes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AfterConnectionEstablished {
   private static final Logger log = LoggerFactory.getLogger(AfterConnectionEstablished.class);

   public static Future execute(final CuratorFramework client, final Runnable runAfterConnection) throws Exception {
      final ExecutorService executor = ThreadUtils.newSingleThreadExecutor(ThreadUtils.getProcessName(runAfterConnection.getClass()));
      Runnable internalCall = new Runnable() {
         public void run() {
            try {
               client.blockUntilConnected();
               runAfterConnection.run();
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               AfterConnectionEstablished.log.error("An error occurred blocking until a connection is available", e);
            } finally {
               executor.shutdown();
            }

         }
      };
      return executor.submit(internalCall);
   }

   private AfterConnectionEstablished() {
   }
}
