package org.apache.spark.rdd;

import java.io.Serializable;
import org.apache.spark.util.ThreadUtils$;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.ExecutionContext.;
import scala.runtime.ModuleSerializationProxy;

public final class AsyncRDDActions$ implements Serializable {
   public static final AsyncRDDActions$ MODULE$ = new AsyncRDDActions$();
   private static final ExecutionContextExecutorService futureExecutionContext;

   static {
      futureExecutionContext = .MODULE$.fromExecutorService(ThreadUtils$.MODULE$.newDaemonCachedThreadPool("AsyncRDDActions-future", 128, ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3()));
   }

   public ExecutionContextExecutorService futureExecutionContext() {
      return futureExecutionContext;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AsyncRDDActions$.class);
   }

   private AsyncRDDActions$() {
   }
}
