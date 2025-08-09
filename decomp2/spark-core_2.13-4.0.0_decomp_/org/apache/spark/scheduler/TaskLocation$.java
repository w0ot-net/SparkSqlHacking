package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import scala.Array;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.StringOps.;

public final class TaskLocation$ {
   public static final TaskLocation$ MODULE$ = new TaskLocation$();
   private static final String inMemoryLocationTag = "hdfs_cache_";
   private static final String executorLocationTag = "executor_";

   public String inMemoryLocationTag() {
      return inMemoryLocationTag;
   }

   public String executorLocationTag() {
      return executorLocationTag;
   }

   public TaskLocation apply(final String host, final String executorId) {
      return new ExecutorCacheTaskLocation(host, executorId);
   }

   public TaskLocation apply(final String str) {
      String hstr = .MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(str), this.inMemoryLocationTag());
      if (hstr.equals(str)) {
         if (str.startsWith(this.executorLocationTag())) {
            String hostAndExecutorId = .MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(str), this.executorLocationTag());
            String[] splits = hostAndExecutorId.split("_", 2);
            scala.Predef..MODULE$.require(splits.length == 2, () -> "Illegal executor location format: " + str);
            if (splits != null) {
               Object var8 = scala.Array..MODULE$.unapplySeq(splits);
               if (!scala.Array.UnapplySeqWrapper..MODULE$.isEmpty$extension(var8) && new Array.UnapplySeqWrapper(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var8)) != null && scala.Array.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var8), 2) == 0) {
                  String host = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var8), 0);
                  String executorId = (String)scala.Array.UnapplySeqWrapper..MODULE$.apply$extension(scala.Array.UnapplySeqWrapper..MODULE$.get$extension(var8), 1);
                  Tuple2 var6 = new Tuple2(host, executorId);
                  String host = (String)var6._1();
                  String executorId = (String)var6._2();
                  return new ExecutorCacheTaskLocation(host, executorId);
               }
            }

            throw new MatchError(splits);
         } else {
            return new HostTaskLocation(str);
         }
      } else {
         return new HDFSCacheTaskLocation(hstr);
      }
   }

   private TaskLocation$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
