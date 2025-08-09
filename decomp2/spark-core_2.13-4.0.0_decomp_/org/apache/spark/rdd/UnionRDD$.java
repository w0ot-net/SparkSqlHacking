package org.apache.spark.rdd;

import java.io.Serializable;
import org.apache.spark.util.ThreadUtils$;
import scala.collection.parallel.ForkJoinTaskSupport;
import scala.runtime.ModuleSerializationProxy;

public final class UnionRDD$ implements Serializable {
   public static final UnionRDD$ MODULE$ = new UnionRDD$();
   private static ForkJoinTaskSupport partitionEvalTaskSupport;
   private static volatile boolean bitmap$0;

   private ForkJoinTaskSupport partitionEvalTaskSupport$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            partitionEvalTaskSupport = new ForkJoinTaskSupport(ThreadUtils$.MODULE$.newForkJoinPool("partition-eval-task-support", 8));
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return partitionEvalTaskSupport;
   }

   public ForkJoinTaskSupport partitionEvalTaskSupport() {
      return !bitmap$0 ? this.partitionEvalTaskSupport$lzycompute() : partitionEvalTaskSupport;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnionRDD$.class);
   }

   private UnionRDD$() {
   }
}
