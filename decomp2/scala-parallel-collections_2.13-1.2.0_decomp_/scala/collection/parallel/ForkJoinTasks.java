package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;
import scala.Function0;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%aaB\t\u0013!\u0003\r\t!\u0007\u0005\u0006K\u0001!\tA\n\u0004\bU\u0001\u0001\n1!\u0001,\u0011\u0015)#\u0001\"\u0001'\u0011\u0015I%\u0001\"\u0001'\u0011\u0015Q%\u0001\"\u0001'\u0011\u0015Y%\u0001\"\u0001M\u0011\u0015\u0001\u0006A\"\u0005R\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u001d\u0011\u0007A1A\u0007\u0002yCQa\u0019\u0001\u0005\u0002\u0011DQ\u0001\u001d\u0001\u0005\u0002EDQ!\u001f\u0001\u0005\u0002i<QA \n\t\u0002}4a!\u0005\n\t\u0002\u0005\u0005\u0001bBA\u0002\u001d\u0011\u0005\u0011Q\u0001\u0005\n\u0003\u000fq\u0001R1A\u0005\u0002y\u0013QBR8sW*{\u0017N\u001c+bg.\u001c(BA\n\u0015\u0003!\u0001\u0018M]1mY\u0016d'BA\u000b\u0017\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002/\u0005)1oY1mC\u000e\u00011\u0003\u0002\u0001\u001b=\t\u0002\"a\u0007\u000f\u000e\u0003YI!!\b\f\u0003\r\u0005s\u0017PU3g!\ty\u0002%D\u0001\u0013\u0013\t\t#CA\u0003UCN\\7\u000f\u0005\u0002 G%\u0011AE\u0005\u0002\u0013\u0011\u00064\u0018N\\4G_J\\'j\\5o!>|G.\u0001\u0004%S:LG\u000f\n\u000b\u0002OA\u00111\u0004K\u0005\u0003SY\u0011A!\u00168ji\nqaI\u0013+Xe\u0006\u0004\b/\u001a3UCN\\Wc\u0001\u0017>\u000fN\u0019!!L\u001c\u0011\u00059*T\"A\u0018\u000b\u0005A\n\u0014AC2p]\u000e,(O]3oi*\u0011!gM\u0001\u0005kRLGNC\u00015\u0003\u0011Q\u0017M^1\n\u0005Yz#a\u0004*fGV\u00148/\u001b<f\u0003\u000e$\u0018n\u001c8\u0011\taJ4HR\u0007\u0002\u0001%\u0011!\b\t\u0002\f/J\f\u0007\u000f]3e)\u0006\u001c8\u000e\u0005\u0002={1\u0001A!\u0002 \u0003\u0005\u0004y$!\u0001*\u0012\u0005\u0001\u001b\u0005CA\u000eB\u0013\t\u0011eCA\u0004O_RD\u0017N\\4\u0011\u0005m!\u0015BA#\u0017\u0005\r\te.\u001f\t\u0003y\u001d#a\u0001\u0013\u0002\u0005\u0006\u0004y$A\u0001+q\u0003\u0015\u0019H/\u0019:u\u0003\u0011\u0019\u0018P\\2\u0002\u0013Q\u0014\u0018pQ1oG\u0016dG#A'\u0011\u0005mq\u0015BA(\u0017\u0005\u001d\u0011un\u001c7fC:\faB\\3x/J\f\u0007\u000f]3e)\u0006\u001c8.F\u0002S+^#\"a\u0015-\u0011\ta\u0012AK\u0016\t\u0003yU#QAP\u0004C\u0002}\u0002\"\u0001P,\u0005\u000b!;!\u0019A \t\u000be;\u0001\u0019\u0001.\u0002\u0003\t\u0004BaH.U-&\u0011AL\u0005\u0002\u0005)\u0006\u001c8.\u0001\u0007g_J\\'j\\5o!>|G.F\u0001`!\tq\u0003-\u0003\u0002b_\taai\u001c:l\u0015>Lg\u000eU8pY\u0006YQM\u001c<je>tW.\u001a8u\u0003\u001d)\u00070Z2vi\u0016,2!\u001a6p)\t17\u000eE\u0002\u001cO&L!\u0001\u001b\f\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004C\u0001\u001fk\t\u0015q$B1\u0001@\u0011\u0015a'\u00021\u0001n\u0003\u0011!\u0018m]6\u0011\t}Y\u0016N\u001c\t\u0003y=$Q\u0001\u0013\u0006C\u0002}\nA#\u001a=fGV$X-\u00118e/\u0006LGOU3tk2$Xc\u0001:uqR\u00111/\u001e\t\u0003yQ$QAP\u0006C\u0002}BQ\u0001\\\u0006A\u0002Y\u0004BaH.toB\u0011A\b\u001f\u0003\u0006\u0011.\u0011\raP\u0001\u0011a\u0006\u0014\u0018\r\u001c7fY&\u001cX\u000eT3wK2,\u0012a\u001f\t\u00037qL!! \f\u0003\u0007%sG/A\u0007G_J\\'j\\5o)\u0006\u001c8n\u001d\t\u0003?9\u0019\"A\u0004\u000e\u0002\rqJg.\u001b;?)\u0005y\u0018a\u00053fM\u0006,H\u000e\u001e$pe.Tu.\u001b8Q_>d\u0007"
)
public interface ForkJoinTasks extends Tasks, HavingForkJoinPool {
   static ForkJoinPool defaultForkJoinPool() {
      return ForkJoinTasks$.MODULE$.defaultForkJoinPool();
   }

   FJTWrappedTask newWrappedTask(final Task b);

   // $FF: synthetic method
   static ForkJoinPool forkJoinPool$(final ForkJoinTasks $this) {
      return $this.forkJoinPool();
   }

   default ForkJoinPool forkJoinPool() {
      return this.environment();
   }

   ForkJoinPool environment();

   // $FF: synthetic method
   static Function0 execute$(final ForkJoinTasks $this, final Task task) {
      return $this.execute(task);
   }

   default Function0 execute(final Task task) {
      FJTWrappedTask fjtask = this.newWrappedTask(task);
      Thread var4 = Thread.currentThread();
      if (var4 instanceof ForkJoinWorkerThread) {
         ForkJoinWorkerThread var5 = (ForkJoinWorkerThread)var4;
         if (var5.getPool() == this.forkJoinPool()) {
            ((ForkJoinTask)fjtask).fork();
            return () -> {
               fjtask.sync();
               fjtask.body().forwardThrowable();
               return fjtask.body().result();
            };
         }
      }

      this.forkJoinPool().execute((ForkJoinTask)fjtask);
      BoxedUnit var10000 = BoxedUnit.UNIT;
      return () -> {
         fjtask.sync();
         fjtask.body().forwardThrowable();
         return fjtask.body().result();
      };
   }

   // $FF: synthetic method
   static Object executeAndWaitResult$(final ForkJoinTasks $this, final Task task) {
      return $this.executeAndWaitResult(task);
   }

   default Object executeAndWaitResult(final Task task) {
      FJTWrappedTask fjtask;
      label14: {
         fjtask = this.newWrappedTask(task);
         Thread var4 = Thread.currentThread();
         if (var4 instanceof ForkJoinWorkerThread) {
            ForkJoinWorkerThread var5 = (ForkJoinWorkerThread)var4;
            if (var5.getPool() == this.forkJoinPool()) {
               ((ForkJoinTask)fjtask).fork();
               break label14;
            }
         }

         this.forkJoinPool().execute((ForkJoinTask)fjtask);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      fjtask.sync();
      fjtask.body().forwardThrowable();
      return fjtask.body().result();
   }

   // $FF: synthetic method
   static int parallelismLevel$(final ForkJoinTasks $this) {
      return $this.parallelismLevel();
   }

   default int parallelismLevel() {
      return this.forkJoinPool().getParallelism();
   }

   static void $init$(final ForkJoinTasks $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface FJTWrappedTask extends Tasks.WrappedTask {
      // $FF: synthetic method
      static void start$(final FJTWrappedTask $this) {
         $this.start();
      }

      default void start() {
         ((ForkJoinTask)this).fork();
      }

      // $FF: synthetic method
      static void sync$(final FJTWrappedTask $this) {
         $this.sync();
      }

      default void sync() {
         ((ForkJoinTask)this).join();
      }

      // $FF: synthetic method
      static boolean tryCancel$(final FJTWrappedTask $this) {
         return $this.tryCancel();
      }

      default boolean tryCancel() {
         return ((ForkJoinTask)this).tryUnfork();
      }

      // $FF: synthetic method
      ForkJoinTasks scala$collection$parallel$ForkJoinTasks$FJTWrappedTask$$$outer();

      static void $init$(final FJTWrappedTask $this) {
      }
   }
}
