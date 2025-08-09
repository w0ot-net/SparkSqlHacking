package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.ConfigEntry;
import scala.Function0;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Try.;

@ScalaSignature(
   bytes = "\u0006\u0005E3Qa\u0003\u0007\u0001\u0019QAQa\u0007\u0001\u0005\u0002uAq\u0001\t\u0001C\u0002\u0013%\u0011\u0005\u0003\u0004-\u0001\u0001\u0006IA\t\u0005\b[\u0001\u0001\r\u0011\"\u0003/\u0011\u001d\u0011\u0004\u00011A\u0005\nMBa!\u000f\u0001!B\u0013y\u0003\"\u0002 \u0001\t\u0003y\u0004\"\u0002!\u0001\t\u0003y\u0004\"B!\u0001\t\u0003\u0011\u0005\"B'\u0001\t\u0003q%\u0001G*qCJ\\7\u000b[;uI><h\u000eS8pW6\u000bg.Y4fe*\u0011QBD\u0001\u0005kRLGN\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h'\t\u0001Q\u0003\u0005\u0002\u001735\tqCC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0013\tQrC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\ta\u0004\u0005\u0002 \u00015\tA\"A\u0003i_>\\7/F\u0001#!\r\u0019s%K\u0007\u0002I)\u0011Q\"\n\u0006\u0002M\u0005!!.\u0019<b\u0013\tACEA\u0007Qe&|'/\u001b;z#V,W/\u001a\t\u0003?)J!a\u000b\u0007\u0003#M\u0003\u0018M]6TQV$Hm\\<o\u0011>|7.\u0001\u0004i_>\\7\u000fI\u0001\rg\",H\u000f^5oO\u0012{wO\\\u000b\u0002_A\u0011a\u0003M\u0005\u0003c]\u0011qAQ8pY\u0016\fg.\u0001\ttQV$H/\u001b8h\t><hn\u0018\u0013fcR\u0011Ag\u000e\t\u0003-UJ!AN\f\u0003\tUs\u0017\u000e\u001e\u0005\bq\u0015\t\t\u00111\u00010\u0003\rAH%M\u0001\u000eg\",H\u000f^5oO\u0012{wO\u001c\u0011)\u0005\u0019Y\u0004C\u0001\f=\u0013\titC\u0001\u0005w_2\fG/\u001b7f\u0003\u001dIgn\u001d;bY2$\u0012\u0001N\u0001\u0007eVt\u0017\t\u001c7\u0002\u0007\u0005$G\rF\u0002\u0016\u0007\"CQ\u0001R\u0005A\u0002\u0015\u000b\u0001\u0002\u001d:j_JLG/\u001f\t\u0003-\u0019K!aR\f\u0003\u0007%sG\u000fC\u0003J\u0013\u0001\u0007!*\u0001\u0003i_>\\\u0007c\u0001\fLi%\u0011Aj\u0006\u0002\n\rVt7\r^5p]B\naA]3n_Z,GCA\u0018P\u0011\u0015\u0001&\u00021\u0001\u0016\u0003\r\u0011XM\u001a"
)
public class SparkShutdownHookManager {
   private final PriorityQueue hooks = new PriorityQueue();
   private volatile boolean shuttingDown = false;

   private PriorityQueue hooks() {
      return this.hooks;
   }

   private boolean shuttingDown() {
      return this.shuttingDown;
   }

   private void shuttingDown_$eq(final boolean x$1) {
      this.shuttingDown = x$1;
   }

   public void install() {
      Runnable hookTask = new Runnable() {
         // $FF: synthetic field
         private final SparkShutdownHookManager $outer;

         public void run() {
            this.$outer.runAll();
         }

         public {
            if (SparkShutdownHookManager.this == null) {
               throw null;
            } else {
               this.$outer = SparkShutdownHookManager.this;
            }
         }
      };
      int priority = 40;
      Option timeout = (Option)(new SparkConf()).get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SPARK_SHUTDOWN_TIMEOUT_MS());
      timeout.fold((JFunction0.mcV.sp)() -> org.apache.hadoop.util.ShutdownHookManager.get().addShutdownHook(hookTask, priority), (JFunction1.mcVJ.sp)(t) -> org.apache.hadoop.util.ShutdownHookManager.get().addShutdownHook(hookTask, priority, t, TimeUnit.MILLISECONDS));
   }

   public void runAll() {
      this.shuttingDown_$eq(true);
      ObjectRef nextHook = ObjectRef.create((Object)null);

      while(true) {
         synchronized(this.hooks()){}

         SparkShutdownHook var3;
         try {
            var3 = (SparkShutdownHook)this.hooks().poll();
         } catch (Throwable var5) {
            throw var5;
         }

         nextHook.elem = var3;
         if ((SparkShutdownHook)nextHook.elem == null) {
            return;
         }

         .MODULE$.apply((JFunction0.mcV.sp)() -> Utils$.MODULE$.logUncaughtExceptions((JFunction0.mcV.sp)() -> ((SparkShutdownHook)nextHook.elem).run()));
      }
   }

   public Object add(final int priority, final Function0 hook) {
      synchronized(this.hooks()){}

      SparkShutdownHook var4;
      try {
         if (this.shuttingDown()) {
            throw new IllegalStateException("Shutdown hooks cannot be modified during shutdown.");
         }

         SparkShutdownHook hookRef = new SparkShutdownHook(priority, hook);
         this.hooks().add(hookRef);
         var4 = hookRef;
      } catch (Throwable var7) {
         throw var7;
      }

      return var4;
   }

   public boolean remove(final Object ref) {
      synchronized(this.hooks()){}

      boolean var3;
      try {
         var3 = this.hooks().remove(ref);
      } catch (Throwable var5) {
         throw var5;
      }

      return var3;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
