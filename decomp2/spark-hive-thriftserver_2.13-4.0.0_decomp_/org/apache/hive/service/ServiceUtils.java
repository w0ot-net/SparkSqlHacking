package org.apache.hive.service;

import java.io.Closeable;
import java.io.IOException;
import org.apache.spark.internal.SparkLogger;

public class ServiceUtils {
   public static int indexOfDomainMatch(String userName) {
      if (userName == null) {
         return -1;
      } else {
         int idx = userName.indexOf(47);
         int idx2 = userName.indexOf(64);
         int endIdx = Math.min(idx, idx2);
         if (endIdx == -1) {
            endIdx = Math.max(idx, idx2);
         }

         return endIdx;
      }
   }

   public static void cleanup(SparkLogger log, Closeable... closeables) {
      for(Closeable c : closeables) {
         if (c != null) {
            try {
               c.close();
            } catch (IOException e) {
               if (log != null && log.isDebugEnabled()) {
                  log.debug("Exception in closing " + String.valueOf(c), e);
               }
            }
         }
      }

   }
}
