package org.apache.spark.executor;

import org.apache.spark.memory.SparkOutOfMemoryError;
import org.apache.spark.util.SparkUncaughtExceptionHandler;
import org.apache.spark.util.SparkUncaughtExceptionHandler$;
import org.apache.spark.util.Utils$;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;

public final class Executor$ {
   public static final Executor$ MODULE$ = new Executor$();
   private static final ThreadLocal taskDeserializationProps = new ThreadLocal();
   private static ExecutorSource executorSourceLocalModeOnly = null;

   public Seq $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public boolean $lessinit$greater$default$5() {
      return false;
   }

   public Thread.UncaughtExceptionHandler $lessinit$greater$default$6() {
      return new SparkUncaughtExceptionHandler(SparkUncaughtExceptionHandler$.MODULE$.$lessinit$greater$default$1());
   }

   public ThreadLocal taskDeserializationProps() {
      return taskDeserializationProps;
   }

   public ExecutorSource executorSourceLocalModeOnly() {
      return executorSourceLocalModeOnly;
   }

   public void executorSourceLocalModeOnly_$eq(final ExecutorSource x$1) {
      executorSourceLocalModeOnly = x$1;
   }

   public boolean isFatalError(final Throwable t, final int depthToCheck) {
      while(depthToCheck > 0) {
         if (t instanceof SparkOutOfMemoryError) {
            return false;
         }

         if (Utils$.MODULE$.isFatalError(t)) {
            return true;
         }

         if (t.getCause() == null) {
            return false;
         }

         Throwable var10000 = t.getCause();
         --depthToCheck;
         t = var10000;
      }

      return false;
   }

   private Executor$() {
   }
}
