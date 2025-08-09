package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import scala.Function1;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class WritableFactory$ implements Serializable {
   public static final WritableFactory$ MODULE$ = new WritableFactory$();

   public WritableFactory simpleWritableFactory(final Function1 convert, final ClassTag evidence$20, final ClassTag evidence$21) {
      Class writableClass = ((ClassTag).MODULE$.implicitly(evidence$21)).runtimeClass();
      return new WritableFactory((x$89) -> writableClass, convert);
   }

   public WritableFactory intWritableFactory() {
      return this.simpleWritableFactory((x$90) -> $anonfun$intWritableFactory$1(BoxesRunTime.unboxToInt(x$90)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(IntWritable.class));
   }

   public WritableFactory longWritableFactory() {
      return this.simpleWritableFactory((x$91) -> $anonfun$longWritableFactory$1(BoxesRunTime.unboxToLong(x$91)), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(LongWritable.class));
   }

   public WritableFactory floatWritableFactory() {
      return this.simpleWritableFactory((x$92) -> $anonfun$floatWritableFactory$1(BoxesRunTime.unboxToFloat(x$92)), scala.reflect.ClassTag..MODULE$.Float(), scala.reflect.ClassTag..MODULE$.apply(FloatWritable.class));
   }

   public WritableFactory doubleWritableFactory() {
      return this.simpleWritableFactory((x$93) -> $anonfun$doubleWritableFactory$1(BoxesRunTime.unboxToDouble(x$93)), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(DoubleWritable.class));
   }

   public WritableFactory booleanWritableFactory() {
      return this.simpleWritableFactory((x$94) -> $anonfun$booleanWritableFactory$1(BoxesRunTime.unboxToBoolean(x$94)), scala.reflect.ClassTag..MODULE$.Boolean(), scala.reflect.ClassTag..MODULE$.apply(BooleanWritable.class));
   }

   public WritableFactory bytesWritableFactory() {
      return this.simpleWritableFactory((x$95) -> new BytesWritable(x$95), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)), scala.reflect.ClassTag..MODULE$.apply(BytesWritable.class));
   }

   public WritableFactory stringWritableFactory() {
      return this.simpleWritableFactory((x$96) -> new Text(x$96), scala.reflect.ClassTag..MODULE$.apply(String.class), scala.reflect.ClassTag..MODULE$.apply(Text.class));
   }

   public WritableFactory writableWritableFactory(final ClassTag evidence$22) {
      return this.simpleWritableFactory((w) -> w, evidence$22, evidence$22);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WritableFactory$.class);
   }

   // $FF: synthetic method
   public static final IntWritable $anonfun$intWritableFactory$1(final int x$90) {
      return new IntWritable(x$90);
   }

   // $FF: synthetic method
   public static final LongWritable $anonfun$longWritableFactory$1(final long x$91) {
      return new LongWritable(x$91);
   }

   // $FF: synthetic method
   public static final FloatWritable $anonfun$floatWritableFactory$1(final float x$92) {
      return new FloatWritable(x$92);
   }

   // $FF: synthetic method
   public static final DoubleWritable $anonfun$doubleWritableFactory$1(final double x$93) {
      return new DoubleWritable(x$93);
   }

   // $FF: synthetic method
   public static final BooleanWritable $anonfun$booleanWritableFactory$1(final boolean x$94) {
      return new BooleanWritable(x$94);
   }

   private WritableFactory$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
