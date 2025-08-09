package org.apache.spark;

import java.io.Serializable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.util.ThreadUtils$;
import scala.Predef.;
import scala.runtime.ModuleSerializationProxy;

@Experimental
public final class BarrierTaskContext$ implements Serializable {
   public static final BarrierTaskContext$ MODULE$ = new BarrierTaskContext$();
   private static final ScheduledThreadPoolExecutor org$apache$spark$BarrierTaskContext$$timer;

   static {
      ScheduledExecutorService executor = ThreadUtils$.MODULE$.newDaemonSingleThreadScheduledExecutor("Barrier task timer for barrier() calls.");
      .MODULE$.assert(executor instanceof ScheduledThreadPoolExecutor);
      org$apache$spark$BarrierTaskContext$$timer = (ScheduledThreadPoolExecutor)executor;
   }

   @Experimental
   public BarrierTaskContext get() {
      return (BarrierTaskContext)TaskContext$.MODULE$.get();
   }

   public ScheduledThreadPoolExecutor org$apache$spark$BarrierTaskContext$$timer() {
      return org$apache$spark$BarrierTaskContext$$timer;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BarrierTaskContext$.class);
   }

   private BarrierTaskContext$() {
   }
}
