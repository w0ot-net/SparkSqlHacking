package org.apache.spark.streaming.api.java;

import java.util.List;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.Function3;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.dstream.DStream;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2aAA\u0002\u0002\u0002\u001dy\u0001\"B\u001b\u0001\t\u00031$aF!cgR\u0014\u0018m\u0019;KCZ\fGi\u0015;sK\u0006lG*[6f\u0015\t!Q!\u0001\u0003kCZ\f'B\u0001\u0004\b\u0003\r\t\u0007/\u001b\u0006\u0003\u0011%\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e,B\u0001E\u000f)YM\u0019\u0001!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g!\u0015A\u0012dG\u0014,\u001b\u0005\u0019\u0011B\u0001\u000e\u0004\u0005=Q\u0015M^1E'R\u0014X-Y7MS.,\u0007C\u0001\u000f\u001e\u0019\u0001!QA\b\u0001C\u0002\u0001\u0012\u0011\u0001V\u0002\u0001#\t\tC\u0005\u0005\u0002\u0013E%\u00111e\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011R%\u0003\u0002''\t\u0019\u0011I\\=\u0011\u0005qAC!B\u0015\u0001\u0005\u0004Q#\u0001\u0002+iSN\f\"!I\f\u0011\u0005qaC!B\u0017\u0001\u0005\u0004q#!\u0001*\u0012\u0005\u0005z\u0003\u0003\u0002\u001947-j\u0011!\r\u0006\u0003\tIR!AB\u0005\n\u0005Q\n$a\u0003&bm\u0006\u0014F\t\u0012'jW\u0016\fa\u0001P5oSRtD#A\u001c\u0011\u000ba\u00011dJ\u0016"
)
public abstract class AbstractJavaDStreamLike implements JavaDStreamLike {
   public JavaDStream scalaIntToJavaLong(final DStream in) {
      return JavaDStreamLike.scalaIntToJavaLong$(this, in);
   }

   public void print() {
      JavaDStreamLike.print$(this);
   }

   public void print(final int num) {
      JavaDStreamLike.print$(this, num);
   }

   public JavaDStream count() {
      return JavaDStreamLike.count$(this);
   }

   public JavaPairDStream countByValue() {
      return JavaDStreamLike.countByValue$(this);
   }

   public JavaPairDStream countByValue(final int numPartitions) {
      return JavaDStreamLike.countByValue$(this, numPartitions);
   }

   public JavaDStream countByWindow(final Duration windowDuration, final Duration slideDuration) {
      return JavaDStreamLike.countByWindow$(this, windowDuration, slideDuration);
   }

   public JavaPairDStream countByValueAndWindow(final Duration windowDuration, final Duration slideDuration) {
      return JavaDStreamLike.countByValueAndWindow$(this, windowDuration, slideDuration);
   }

   public JavaPairDStream countByValueAndWindow(final Duration windowDuration, final Duration slideDuration, final int numPartitions) {
      return JavaDStreamLike.countByValueAndWindow$(this, windowDuration, slideDuration, numPartitions);
   }

   public JavaDStream glom() {
      return JavaDStreamLike.glom$(this);
   }

   public StreamingContext context() {
      return JavaDStreamLike.context$(this);
   }

   public JavaDStream map(final Function f) {
      return JavaDStreamLike.map$(this, f);
   }

   public JavaPairDStream mapToPair(final PairFunction f) {
      return JavaDStreamLike.mapToPair$(this, f);
   }

   public JavaDStream flatMap(final FlatMapFunction f) {
      return JavaDStreamLike.flatMap$(this, f);
   }

   public JavaPairDStream flatMapToPair(final PairFlatMapFunction f) {
      return JavaDStreamLike.flatMapToPair$(this, f);
   }

   public JavaDStream mapPartitions(final FlatMapFunction f) {
      return JavaDStreamLike.mapPartitions$(this, f);
   }

   public JavaPairDStream mapPartitionsToPair(final PairFlatMapFunction f) {
      return JavaDStreamLike.mapPartitionsToPair$(this, f);
   }

   public JavaDStream reduce(final Function2 f) {
      return JavaDStreamLike.reduce$(this, f);
   }

   public JavaDStream reduceByWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return JavaDStreamLike.reduceByWindow$(this, reduceFunc, windowDuration, slideDuration);
   }

   public JavaDStream reduceByWindow(final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return JavaDStreamLike.reduceByWindow$(this, reduceFunc, invReduceFunc, windowDuration, slideDuration);
   }

   public List slice(final Time fromTime, final Time toTime) {
      return JavaDStreamLike.slice$(this, fromTime, toTime);
   }

   public void foreachRDD(final VoidFunction foreachFunc) {
      JavaDStreamLike.foreachRDD$(this, (VoidFunction)foreachFunc);
   }

   public void foreachRDD(final VoidFunction2 foreachFunc) {
      JavaDStreamLike.foreachRDD$(this, (VoidFunction2)foreachFunc);
   }

   public JavaDStream transform(final Function transformFunc) {
      return JavaDStreamLike.transform$(this, (Function)transformFunc);
   }

   public JavaDStream transform(final Function2 transformFunc) {
      return JavaDStreamLike.transform$(this, (Function2)transformFunc);
   }

   public JavaPairDStream transformToPair(final Function transformFunc) {
      return JavaDStreamLike.transformToPair$(this, (Function)transformFunc);
   }

   public JavaPairDStream transformToPair(final Function2 transformFunc) {
      return JavaDStreamLike.transformToPair$(this, (Function2)transformFunc);
   }

   public JavaDStream transformWith(final JavaDStream other, final Function3 transformFunc) {
      return JavaDStreamLike.transformWith$(this, (JavaDStream)other, transformFunc);
   }

   public JavaPairDStream transformWithToPair(final JavaDStream other, final Function3 transformFunc) {
      return JavaDStreamLike.transformWithToPair$(this, (JavaDStream)other, transformFunc);
   }

   public JavaDStream transformWith(final JavaPairDStream other, final Function3 transformFunc) {
      return JavaDStreamLike.transformWith$(this, (JavaPairDStream)other, transformFunc);
   }

   public JavaPairDStream transformWithToPair(final JavaPairDStream other, final Function3 transformFunc) {
      return JavaDStreamLike.transformWithToPair$(this, (JavaPairDStream)other, transformFunc);
   }

   public DStream checkpoint(final Duration interval) {
      return JavaDStreamLike.checkpoint$(this, interval);
   }

   public AbstractJavaDStreamLike() {
      JavaDStreamLike.$init$(this);
   }
}
