package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.mutable.HashMap;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005M3QAC\u0006\u0001\u0017MA\u0001B\u0007\u0001\u0003\u0006\u0004%\t\u0001\b\u0005\tQ\u0001\u0011\t\u0011)A\u0005;!)\u0011\u0006\u0001C\u0001U!9a\u0006\u0001b\u0001\n\u0003y\u0003BB!\u0001A\u0003%\u0001\u0007C\u0003C\u0001\u0011\u00051\tC\u0003L\u0001\u0011\u0005A\nC\u0003N\u0001\u0011\u0005a\nC\u0003R\u0001\u0011\u0005#KA\rFq\u0016\u001cW\u000f^8s\r\u0006LG.\u001e:fg&sG+Y:l'\u0016$(B\u0001\u0007\u000e\u0003%\u00198\r[3ek2,'O\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h'\t\u0001A\u0003\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VMZ\u0001\u0005]>$Wm\u0001\u0001\u0016\u0003u\u0001\"AH\u0013\u000f\u0005}\u0019\u0003C\u0001\u0011\u0017\u001b\u0005\t#B\u0001\u0012\u001c\u0003\u0019a$o\\8u}%\u0011AEF\u0001\u0007!J,G-\u001a4\n\u0005\u0019:#AB*ue&twM\u0003\u0002%-\u0005)an\u001c3fA\u00051A(\u001b8jiz\"\"aK\u0017\u0011\u00051\u0002Q\"A\u0006\t\u000bi\u0019\u0001\u0019A\u000f\u0002AQ\f7o\u001b+p\r\u0006LG.\u001e:f\u0007>,h\u000e^!oI\u001a\u000b\u0017\u000e\\;sKRKW.Z\u000b\u0002aA!\u0011G\u000e\u001d<\u001b\u0005\u0011$BA\u001a5\u0003\u001diW\u000f^1cY\u0016T!!\u000e\f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00028e\t9\u0001*Y:i\u001b\u0006\u0004\bCA\u000b:\u0013\tQdCA\u0002J]R\u0004B!\u0006\u001f9}%\u0011QH\u0006\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005Uy\u0014B\u0001!\u0017\u0005\u0011auN\\4\u0002CQ\f7o\u001b+p\r\u0006LG.\u001e:f\u0007>,h\u000e^!oI\u001a\u000b\u0017\u000e\\;sKRKW.\u001a\u0011\u0002#U\u0004H-\u0019;f/&$\bNR1jYV\u0014X\rF\u0002E\u000f&\u0003\"!F#\n\u0005\u00193\"\u0001B+oSRDQ\u0001\u0013\u0004A\u0002a\n\u0011\u0002^1tW&sG-\u001a=\t\u000b)3\u0001\u0019\u0001 \u0002\u0017\u0019\f\u0017\u000e\\;sKRKW.Z\u0001\u001b]VlWK\\5rk\u0016$\u0016m]6t/&$\bNR1jYV\u0014Xm]\u000b\u0002q\u0005\u0011r-\u001a;Ok6$\u0016m]6GC&dWO]3t)\tAt\nC\u0003Q\u0011\u0001\u0007\u0001(A\u0003j]\u0012,\u00070\u0001\u0005u_N#(/\u001b8h)\u0005i\u0002"
)
public class ExecutorFailuresInTaskSet {
   private final String node;
   private final HashMap taskToFailureCountAndFailureTime;

   public String node() {
      return this.node;
   }

   public HashMap taskToFailureCountAndFailureTime() {
      return this.taskToFailureCountAndFailureTime;
   }

   public void updateWithFailure(final int taskIndex, final long failureTime) {
      Tuple2 var6 = (Tuple2)this.taskToFailureCountAndFailureTime().getOrElse(BoxesRunTime.boxToInteger(taskIndex), () -> new Tuple2.mcIJ.sp(0, -1L));
      if (var6 != null) {
         int prevFailureCount = var6._1$mcI$sp();
         long prevFailureTime = var6._2$mcJ$sp();
         Tuple2.mcIJ.sp var5 = new Tuple2.mcIJ.sp(prevFailureCount, prevFailureTime);
         int prevFailureCount = ((Tuple2)var5)._1$mcI$sp();
         long prevFailureTime = ((Tuple2)var5)._2$mcJ$sp();
         long newFailureTime = .MODULE$.max(prevFailureTime, failureTime);
         this.taskToFailureCountAndFailureTime().update(BoxesRunTime.boxToInteger(taskIndex), new Tuple2.mcIJ.sp(prevFailureCount + 1, newFailureTime));
      } else {
         throw new MatchError(var6);
      }
   }

   public int numUniqueTasksWithFailures() {
      return this.taskToFailureCountAndFailureTime().size();
   }

   public int getNumTaskFailures(final int index) {
      return ((Tuple2)this.taskToFailureCountAndFailureTime().getOrElse(BoxesRunTime.boxToInteger(index), () -> new Tuple2.mcII.sp(0, 0)))._1$mcI$sp();
   }

   public String toString() {
      int var10000 = this.numUniqueTasksWithFailures();
      return "numUniqueTasksWithFailures = " + var10000 + "; tasksToFailureCount = " + this.taskToFailureCountAndFailureTime();
   }

   public ExecutorFailuresInTaskSet(final String node) {
      this.node = node;
      this.taskToFailureCountAndFailureTime = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
