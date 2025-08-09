package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import scala.Function0;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class WritableConverter$ implements Serializable {
   public static final WritableConverter$ MODULE$ = new WritableConverter$();
   private static final Function0 intWritableConverterFn = () -> MODULE$.simpleWritableConverter((x$73) -> BoxesRunTime.boxToInteger($anonfun$intWritableConverterFn$2(x$73)), .MODULE$.apply(IntWritable.class));
   private static final Function0 longWritableConverterFn = () -> MODULE$.simpleWritableConverter((x$74) -> BoxesRunTime.boxToLong($anonfun$longWritableConverterFn$2(x$74)), .MODULE$.apply(LongWritable.class));
   private static final Function0 doubleWritableConverterFn = () -> MODULE$.simpleWritableConverter((x$75) -> BoxesRunTime.boxToDouble($anonfun$doubleWritableConverterFn$2(x$75)), .MODULE$.apply(DoubleWritable.class));
   private static final Function0 floatWritableConverterFn = () -> MODULE$.simpleWritableConverter((x$76) -> BoxesRunTime.boxToFloat($anonfun$floatWritableConverterFn$2(x$76)), .MODULE$.apply(FloatWritable.class));
   private static final Function0 booleanWritableConverterFn = () -> MODULE$.simpleWritableConverter((x$77) -> BoxesRunTime.boxToBoolean($anonfun$booleanWritableConverterFn$2(x$77)), .MODULE$.apply(BooleanWritable.class));
   private static final Function0 bytesWritableConverterFn = () -> MODULE$.simpleWritableConverter((bw) -> Arrays.copyOfRange(bw.getBytes(), 0, bw.getLength()), .MODULE$.apply(BytesWritable.class));
   private static final Function0 stringWritableConverterFn = () -> MODULE$.simpleWritableConverter((x$78) -> x$78.toString(), .MODULE$.apply(Text.class));

   public WritableConverter simpleWritableConverter(final Function1 convert, final ClassTag evidence$18) {
      Class wClass = scala.reflect.package..MODULE$.classTag(evidence$18).runtimeClass();
      return new WritableConverter((x$72) -> wClass, (x) -> convert.apply(x));
   }

   public Function0 intWritableConverterFn() {
      return intWritableConverterFn;
   }

   public Function0 longWritableConverterFn() {
      return longWritableConverterFn;
   }

   public Function0 doubleWritableConverterFn() {
      return doubleWritableConverterFn;
   }

   public Function0 floatWritableConverterFn() {
      return floatWritableConverterFn;
   }

   public Function0 booleanWritableConverterFn() {
      return booleanWritableConverterFn;
   }

   public Function0 bytesWritableConverterFn() {
      return bytesWritableConverterFn;
   }

   public Function0 stringWritableConverterFn() {
      return stringWritableConverterFn;
   }

   public Function0 writableWritableConverterFn(final ClassTag evidence$19) {
      return () -> new WritableConverter((x$79) -> x$79.runtimeClass(), (x$80) -> x$80);
   }

   public WritableConverter intWritableConverter() {
      return this.simpleWritableConverter((x$81) -> BoxesRunTime.boxToInteger($anonfun$intWritableConverter$1(x$81)), .MODULE$.apply(IntWritable.class));
   }

   public WritableConverter longWritableConverter() {
      return this.simpleWritableConverter((x$82) -> BoxesRunTime.boxToLong($anonfun$longWritableConverter$1(x$82)), .MODULE$.apply(LongWritable.class));
   }

   public WritableConverter doubleWritableConverter() {
      return this.simpleWritableConverter((x$83) -> BoxesRunTime.boxToDouble($anonfun$doubleWritableConverter$1(x$83)), .MODULE$.apply(DoubleWritable.class));
   }

   public WritableConverter floatWritableConverter() {
      return this.simpleWritableConverter((x$84) -> BoxesRunTime.boxToFloat($anonfun$floatWritableConverter$1(x$84)), .MODULE$.apply(FloatWritable.class));
   }

   public WritableConverter booleanWritableConverter() {
      return this.simpleWritableConverter((x$85) -> BoxesRunTime.boxToBoolean($anonfun$booleanWritableConverter$1(x$85)), .MODULE$.apply(BooleanWritable.class));
   }

   public WritableConverter bytesWritableConverter() {
      return this.simpleWritableConverter((bw) -> Arrays.copyOfRange(bw.getBytes(), 0, bw.getLength()), .MODULE$.apply(BytesWritable.class));
   }

   public WritableConverter stringWritableConverter() {
      return this.simpleWritableConverter((x$86) -> x$86.toString(), .MODULE$.apply(Text.class));
   }

   public WritableConverter writableWritableConverter() {
      return new WritableConverter((x$87) -> x$87.runtimeClass(), (x$88) -> x$88);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WritableConverter$.class);
   }

   // $FF: synthetic method
   public static final int $anonfun$intWritableConverterFn$2(final IntWritable x$73) {
      return x$73.get();
   }

   // $FF: synthetic method
   public static final long $anonfun$longWritableConverterFn$2(final LongWritable x$74) {
      return x$74.get();
   }

   // $FF: synthetic method
   public static final double $anonfun$doubleWritableConverterFn$2(final DoubleWritable x$75) {
      return x$75.get();
   }

   // $FF: synthetic method
   public static final float $anonfun$floatWritableConverterFn$2(final FloatWritable x$76) {
      return x$76.get();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$booleanWritableConverterFn$2(final BooleanWritable x$77) {
      return x$77.get();
   }

   // $FF: synthetic method
   public static final int $anonfun$intWritableConverter$1(final IntWritable x$81) {
      return x$81.get();
   }

   // $FF: synthetic method
   public static final long $anonfun$longWritableConverter$1(final LongWritable x$82) {
      return x$82.get();
   }

   // $FF: synthetic method
   public static final double $anonfun$doubleWritableConverter$1(final DoubleWritable x$83) {
      return x$83.get();
   }

   // $FF: synthetic method
   public static final float $anonfun$floatWritableConverter$1(final FloatWritable x$84) {
      return x$84.get();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$booleanWritableConverter$1(final BooleanWritable x$85) {
      return x$85.get();
   }

   private WritableConverter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
