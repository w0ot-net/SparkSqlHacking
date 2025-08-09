package org.apache.spark.network.shuffle;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.network.server.BlockPushNonFatalFailure;
import org.sparkproject.guava.base.Throwables;

@Evolving
public interface ErrorHandler {
   ErrorHandler NOOP_ERROR_HANDLER = (t) -> true;

   boolean shouldRetryError(Throwable var1);

   default boolean shouldLogError(Throwable t) {
      return true;
   }

   public static class BlockPushErrorHandler implements ErrorHandler {
      public static final String IOEXCEPTIONS_EXCEEDED_THRESHOLD_PREFIX = "IOExceptions exceeded the threshold";
      public static final String STALE_SHUFFLE_FINALIZE_SUFFIX = "stale shuffle finalize request as shuffle blocks of a higher shuffleMergeId for the shuffle is already being pushed";

      public boolean shouldRetryError(Throwable t) {
         if (!(t.getCause() instanceof ConnectException) && !(t.getCause() instanceof FileNotFoundException)) {
            boolean var10000;
            if (t instanceof BlockPushNonFatalFailure) {
               BlockPushNonFatalFailure blockPushNonFatalFailure = (BlockPushNonFatalFailure)t;
               if (BlockPushNonFatalFailure.shouldNotRetryErrorCode(blockPushNonFatalFailure.getReturnCode())) {
                  var10000 = true;
                  return !var10000;
               }
            }

            var10000 = false;
            return !var10000;
         } else {
            return false;
         }
      }

      public boolean shouldLogError(Throwable t) {
         return !(t instanceof BlockPushNonFatalFailure);
      }
   }

   public static class BlockFetchErrorHandler implements ErrorHandler {
      public static final String STALE_SHUFFLE_BLOCK_FETCH = "stale shuffle block fetch request as shuffle blocks of a higher shuffleMergeId for the shuffle is available";

      public boolean shouldRetryError(Throwable t) {
         return !Throwables.getStackTraceAsString(t).contains("stale shuffle block fetch request as shuffle blocks of a higher shuffleMergeId for the shuffle is available");
      }

      public boolean shouldLogError(Throwable t) {
         return !Throwables.getStackTraceAsString(t).contains("stale shuffle block fetch request as shuffle blocks of a higher shuffleMergeId for the shuffle is available");
      }
   }
}
