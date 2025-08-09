package org.apache.spark.shuffle;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.package$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class ShuffleBlockPusher$ {
   public static final ShuffleBlockPusher$ MODULE$;
   private static final ExecutorService org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL;

   static {
      SparkConf conf;
      boolean var4;
      Utils$ var10000;
      label22: {
         label21: {
            MODULE$ = new ShuffleBlockPusher$();
            conf = SparkEnv$.MODULE$.get().conf();
            var10000 = Utils$.MODULE$;
            String var10002 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            String var1 = SparkEnv$.MODULE$.get().executorId();
            if (var10002 == null) {
               if (var1 == null) {
                  break label21;
               }
            } else if (var10002.equals(var1)) {
               break label21;
            }

            var4 = false;
            break label22;
         }

         var4 = true;
      }

      ThreadPoolExecutor var3;
      if (var10000.isPushBasedShuffleEnabled(conf, var4, Utils$.MODULE$.isPushBasedShuffleEnabled$default$3())) {
         int numThreads = BoxesRunTime.unboxToInt(((Option)conf.get((ConfigEntry)package$.MODULE$.SHUFFLE_NUM_PUSH_THREADS())).getOrElse((JFunction0.mcI.sp)() -> conf.getInt("spark.executor.cores", 1)));
         var3 = ThreadUtils$.MODULE$.newDaemonFixedThreadPool(numThreads, "shuffle-block-push-thread");
      } else {
         var3 = null;
      }

      org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL = var3;
   }

   public ExecutorService org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL() {
      return org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL;
   }

   public void stop() {
      if (this.org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL() != null) {
         this.org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL().shutdown();
      }
   }

   private ShuffleBlockPusher$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
