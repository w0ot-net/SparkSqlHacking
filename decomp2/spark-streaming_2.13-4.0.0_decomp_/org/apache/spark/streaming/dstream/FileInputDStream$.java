package org.apache.spark.streaming.dstream;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.streaming.Duration;
import scala.Function1;
import scala.Option;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class FileInputDStream$ implements Serializable {
   public static final FileInputDStream$ MODULE$ = new FileInputDStream$();

   public Function1 $lessinit$greater$default$3() {
      return (path) -> BoxesRunTime.boxToBoolean($anonfun$$lessinit$greater$default$3$1(path));
   }

   public boolean $lessinit$greater$default$4() {
      return true;
   }

   public Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public boolean defaultFilter(final Path path) {
      return !path.getName().startsWith(".");
   }

   public int calculateNumBatchesToRemember(final Duration batchDuration, final Duration minRememberDurationS) {
      return (int)scala.math.package..MODULE$.ceil((double)minRememberDurationS.milliseconds() / (double)batchDuration.milliseconds());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FileInputDStream$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$lessinit$greater$default$3$1(final Path path) {
      return MODULE$.defaultFilter(path);
   }

   private FileInputDStream$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
