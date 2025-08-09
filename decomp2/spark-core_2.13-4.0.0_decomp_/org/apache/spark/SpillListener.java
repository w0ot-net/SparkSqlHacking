package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerStageCompleted;
import org.apache.spark.scheduler.SparkListenerTaskEnd;
import scala.Option.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005=3A!\u0003\u0006\u0005#!)\u0001\u0004\u0001C\u00013!9A\u0004\u0001b\u0001\n\u0013i\u0002BB\u001b\u0001A\u0003%a\u0004C\u00047\u0001\t\u0007I\u0011B\u001c\t\rm\u0002\u0001\u0015!\u00039\u0011\u0015a\u0004\u0001\"\u0001>\u0011\u0015q\u0004\u0001\"\u0011@\u0011\u0015A\u0005\u0001\"\u0011J\u00055\u0019\u0006/\u001b7m\u0019&\u001cH/\u001a8fe*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0011\u0002CA\n\u0017\u001b\u0005!\"BA\u000b\u000b\u0003%\u00198\r[3ek2,'/\u0003\u0002\u0018)\ti1\u000b]1sW2K7\u000f^3oKJ\fa\u0001P5oSRtD#\u0001\u000e\u0011\u0005m\u0001Q\"\u0001\u0006\u0002)M$\u0018mZ3JIR{G+Y:l\u001b\u0016$(/[2t+\u0005q\u0002\u0003B\u0010'Q1j\u0011\u0001\t\u0006\u0003C\t\nq!\\;uC\ndWM\u0003\u0002$I\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0011\u0003\u000f!\u000b7\u000f['baB\u0011\u0011FK\u0007\u0002I%\u00111\u0006\n\u0002\u0004\u0013:$\bcA\u0010._%\u0011a\u0006\t\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000f\u0005\u00021g5\t\u0011G\u0003\u00023\u0015\u0005AQ\r_3dkR|'/\u0003\u00025c\tYA+Y:l\u001b\u0016$(/[2t\u0003U\u0019H/Y4f\u0013\u0012$v\u000eV1tW6+GO]5dg\u0002\nqb\u001d9jY2,Gm\u0015;bO\u0016LEm]\u000b\u0002qA\u0019q$\u000f\u0015\n\u0005i\u0002#a\u0002%bg\"\u001cV\r^\u0001\u0011gBLG\u000e\\3e'R\fw-Z%eg\u0002\n\u0001C\\;n'BLG\u000e\\3e'R\fw-Z:\u0016\u0003!\n\u0011b\u001c8UCN\\WI\u001c3\u0015\u0005\u0001\u001b\u0005CA\u0015B\u0013\t\u0011EE\u0001\u0003V]&$\b\"\u0002#\b\u0001\u0004)\u0015a\u0002;bg.,e\u000e\u001a\t\u0003'\u0019K!a\u0012\u000b\u0003)M\u0003\u0018M]6MSN$XM\\3s)\u0006\u001c8.\u00128e\u0003Aygn\u0015;bO\u0016\u001cu.\u001c9mKR,G\r\u0006\u0002A\u0015\")1\n\u0003a\u0001\u0019\u0006i1\u000f^1hK\u000e{W\u000e\u001d7fi\u0016\u0004\"aE'\n\u00059#\"aG*qCJ\\G*[:uK:,'o\u0015;bO\u0016\u001cu.\u001c9mKR,G\r"
)
public class SpillListener extends SparkListener {
   private final HashMap stageIdToTaskMetrics = new HashMap();
   private final HashSet spilledStageIds = new HashSet();

   private HashMap stageIdToTaskMetrics() {
      return this.stageIdToTaskMetrics;
   }

   private HashSet spilledStageIds() {
      return this.spilledStageIds;
   }

   public synchronized int numSpilledStages() {
      return this.spilledStageIds().size();
   }

   public void onTaskEnd(final SparkListenerTaskEnd taskEnd) {
      synchronized(this){}

      try {
         ArrayBuffer var10000 = (ArrayBuffer)((Growable)this.stageIdToTaskMetrics().getOrElseUpdate(BoxesRunTime.boxToInteger(taskEnd.stageId()), () -> new ArrayBuffer())).$plus$eq(taskEnd.taskMetrics());
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public void onStageCompleted(final SparkListenerStageCompleted stageComplete) {
      synchronized(this){}

      try {
         int stageId = stageComplete.stageInfo().stageId();
         Seq metrics = (Seq).MODULE$.option2Iterable(this.stageIdToTaskMetrics().remove(BoxesRunTime.boxToInteger(stageId))).toSeq().flatten(scala.Predef..MODULE$.$conforms());
         boolean spilled = BoxesRunTime.unboxToLong(((IterableOnceOps)metrics.map((x$9) -> BoxesRunTime.boxToLong($anonfun$onStageCompleted$1(x$9)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$)) > 0L;
         if (spilled) {
            this.spilledStageIds().$plus$eq(BoxesRunTime.boxToInteger(stageId));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } catch (Throwable var7) {
         throw var7;
      }

   }

   // $FF: synthetic method
   public static final long $anonfun$onStageCompleted$1(final TaskMetrics x$9) {
      return x$9.memoryBytesSpilled();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
