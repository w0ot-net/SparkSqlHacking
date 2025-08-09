package scala.collection.parallel;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import scala.Function0;
import scala.concurrent.ExecutionContext;
import scala.concurrent.impl.ExecutionContextImpl;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3q\u0001C\u0005\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0003\u001f\u0001\u0011\u0005q\u0004C\u0004'\u0001\t\u0007i\u0011A\u0010\t\u000f\u001d\u0002!\u0019!C\u0005Q!)\u0011\u0006\u0001C\u0001U!)!\t\u0001C\u0001\u0007\")1\n\u0001C\u0001\u0019\n)R\t_3dkRLwN\\\"p]R,\u0007\u0010\u001e+bg.\u001c(B\u0001\u0006\f\u0003!\u0001\u0018M]1mY\u0016d'B\u0001\u0007\u000e\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u001d\u0005)1oY1mC\u000e\u00011c\u0001\u0001\u0012+A\u0011!cE\u0007\u0002\u001b%\u0011A#\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Y9R\"A\u0005\n\u0005aI!!\u0002+bg.\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001\u001c!\t\u0011B$\u0003\u0002\u001e\u001b\t!QK\\5u\u0003A)\u00070Z2vi&|gnQ8oi\u0016DH/F\u0001!!\t\tC%D\u0001#\u0015\t\u0019S\"\u0001\u0006d_:\u001cWO\u001d:f]RL!!\n\u0012\u0003!\u0015CXmY;uS>t7i\u001c8uKb$\u0018aC3om&\u0014xN\\7f]R\fa\u0001\u001a:jm\u0016\u0014X#A\u000b\u0002\u000f\u0015DXmY;uKV\u00191&\r!\u0015\u00051R\u0004c\u0001\n._%\u0011a&\u0004\u0002\n\rVt7\r^5p]B\u0002\"\u0001M\u0019\r\u0001\u0011)!'\u0002b\u0001g\t\t!+\u0005\u00025oA\u0011!#N\u0005\u0003m5\u0011qAT8uQ&tw\r\u0005\u0002\u0013q%\u0011\u0011(\u0004\u0002\u0004\u0003:L\b\"B\u001e\u0006\u0001\u0004a\u0014\u0001\u0002;bg.\u0004BAF\u001f0\u007f%\u0011a(\u0003\u0002\u0005)\u0006\u001c8\u000e\u0005\u00021\u0001\u0012)\u0011)\u0002b\u0001g\t\u0011A\u000b]\u0001\u0015Kb,7-\u001e;f\u0003:$w+Y5u%\u0016\u001cX\u000f\u001c;\u0016\u0007\u00113%\n\u0006\u0002F\u000fB\u0011\u0001G\u0012\u0003\u0006e\u0019\u0011\ra\r\u0005\u0006w\u0019\u0001\r\u0001\u0013\t\u0005-u*\u0015\n\u0005\u00021\u0015\u0012)\u0011I\u0002b\u0001g\u0005\u0001\u0002/\u0019:bY2,G.[:n\u0019\u00164X\r\\\u000b\u0002\u001bB\u0011!CT\u0005\u0003\u001f6\u00111!\u00138u\u0001"
)
public interface ExecutionContextTasks extends Tasks {
   void scala$collection$parallel$ExecutionContextTasks$_setter_$scala$collection$parallel$ExecutionContextTasks$$driver_$eq(final Tasks x$1);

   // $FF: synthetic method
   static ExecutionContext executionContext$(final ExecutionContextTasks $this) {
      return $this.executionContext();
   }

   default ExecutionContext executionContext() {
      return this.environment();
   }

   ExecutionContext environment();

   Tasks scala$collection$parallel$ExecutionContextTasks$$driver();

   // $FF: synthetic method
   static Function0 execute$(final ExecutionContextTasks $this, final Task task) {
      return $this.execute(task);
   }

   default Function0 execute(final Task task) {
      return this.scala$collection$parallel$ExecutionContextTasks$$driver().execute(task);
   }

   // $FF: synthetic method
   static Object executeAndWaitResult$(final ExecutionContextTasks $this, final Task task) {
      return $this.executeAndWaitResult(task);
   }

   default Object executeAndWaitResult(final Task task) {
      return this.scala$collection$parallel$ExecutionContextTasks$$driver().executeAndWaitResult(task);
   }

   // $FF: synthetic method
   static int parallelismLevel$(final ExecutionContextTasks $this) {
      return $this.parallelismLevel();
   }

   default int parallelismLevel() {
      return this.scala$collection$parallel$ExecutionContextTasks$$driver().parallelismLevel();
   }

   static void $init$(final ExecutionContextTasks $this) {
      ExecutionContext var3 = $this.executionContext();
      Object var10001;
      if (var3 instanceof ForkJoinPool) {
         ForkJoinPool var4 = (ForkJoinPool)var3;
         var10001 = new ForkJoinTaskSupport(var4);
      } else if (var3 instanceof ExecutionContextImpl) {
         ExecutionContextImpl var5 = (ExecutionContextImpl)var3;
         Executor var6 = var5.executor();
         if (var6 instanceof ForkJoinPool) {
            ForkJoinPool var7 = (ForkJoinPool)var6;
            var10001 = new ForkJoinTaskSupport(var7);
         } else {
            var10001 = new FutureTasks($this.environment());
         }
      } else {
         var10001 = new FutureTasks($this.environment());
      }

      $this.scala$collection$parallel$ExecutionContextTasks$_setter_$scala$collection$parallel$ExecutionContextTasks$$driver_$eq((Tasks)var10001);
   }
}
