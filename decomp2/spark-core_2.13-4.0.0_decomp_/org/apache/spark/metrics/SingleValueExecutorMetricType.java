package org.apache.spark.metrics;

import org.apache.spark.memory.MemoryManager;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553q!\u0002\u0004\u0011\u0002\u0007\u0005r\u0002C\u0003\u001b\u0001\u0011\u00051\u0004\u0003\u0004 \u0001\u0011\u0005\u0003\u0002\t\u0005\u0007c\u0001!\t\u0005\u0003\u001a\t\r\u0005\u0003a\u0011\u0001\u0005C\u0005u\u0019\u0016N\\4mKZ\u000bG.^3Fq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2UsB,'BA\u0004\t\u0003\u001diW\r\u001e:jGNT!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\u0002\u0001'\r\u0001\u0001C\u0006\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]AR\"\u0001\u0004\n\u0005e1!AE#yK\u000e,Ho\u001c:NKR\u0014\u0018n\u0019+za\u0016\fa\u0001J5oSR$C#\u0001\u000f\u0011\u0005Ei\u0012B\u0001\u0010\u0013\u0005\u0011)f.\u001b;\u0002\u000b9\fW.Z:\u0016\u0003\u0005\u00022AI\u0014*\u001b\u0005\u0019#B\u0001\u0013&\u0003%IW.\\;uC\ndWM\u0003\u0002'%\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005!\u001a#aA*fcB\u0011!fL\u0007\u0002W)\u0011A&L\u0001\u0005Y\u0006twMC\u0001/\u0003\u0011Q\u0017M^1\n\u0005AZ#AB*ue&tw-A\bhKRlU\r\u001e:jGZ\u000bG.^3t)\t\u0019\u0014\bE\u0002\u0012iYJ!!\u000e\n\u0003\u000b\u0005\u0013(/Y=\u0011\u0005E9\u0014B\u0001\u001d\u0013\u0005\u0011auN\\4\t\u000bi\u001a\u0001\u0019A\u001e\u0002\u001b5,Wn\u001c:z\u001b\u0006t\u0017mZ3s!\tat(D\u0001>\u0015\tq\u0004\"\u0001\u0004nK6|'/_\u0005\u0003\u0001v\u0012Q\"T3n_JLX*\u00198bO\u0016\u0014\u0018AD4fi6+GO]5d-\u0006dW/\u001a\u000b\u0003m\rCQA\u000f\u0003A\u0002mJS\u0001A#H\u0013.S!A\u0012\u0004\u0002\u001b)3V\nS3ba6+Wn\u001c:z\u0015\tAe!\u0001\tK-6{eM\u001a%fCBlU-\\8ss&\u0011!J\u0002\u0002\u0018\u001b\n+\u0017M\\#yK\u000e,Ho\u001c:NKR\u0014\u0018n\u0019+za\u0016L!\u0001\u0014\u0004\u0003?5+Wn\u001c:z\u001b\u0006t\u0017mZ3s\u000bb,7-\u001e;pe6+GO]5d)f\u0004X\r"
)
public interface SingleValueExecutorMetricType extends ExecutorMetricType {
   // $FF: synthetic method
   static Seq names$(final SingleValueExecutorMetricType $this) {
      return $this.names();
   }

   default Seq names() {
      return new .colon.colon((String)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.getClass().getName()), "$").split("\\."))), scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   static long[] getMetricValues$(final SingleValueExecutorMetricType $this, final MemoryManager memoryManager) {
      return $this.getMetricValues(memoryManager);
   }

   default long[] getMetricValues(final MemoryManager memoryManager) {
      long[] metrics = new long[1];
      metrics[0] = this.getMetricValue(memoryManager);
      return metrics;
   }

   long getMetricValue(final MemoryManager memoryManager);

   static void $init$(final SingleValueExecutorMetricType $this) {
   }
}
