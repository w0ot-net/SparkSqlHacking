package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.WritableFactory;
import scala.Function1;
import scala.Tuple2;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Null;

public final class RDD$ implements Serializable {
   public static final RDD$ MODULE$ = new RDD$();
   private static final String CHECKPOINT_ALL_MARKED_ANCESTORS = "spark.checkpoint.checkpointAllMarkedAncestors";

   public String CHECKPOINT_ALL_MARKED_ANCESTORS() {
      return CHECKPOINT_ALL_MARKED_ANCESTORS;
   }

   public PairRDDFunctions rddToPairRDDFunctions(final RDD rdd, final ClassTag kt, final ClassTag vt, final Ordering ord) {
      return new PairRDDFunctions(rdd, kt, vt, ord);
   }

   public Null rddToPairRDDFunctions$default$4(final RDD rdd) {
      return null;
   }

   public AsyncRDDActions rddToAsyncRDDActions(final RDD rdd, final ClassTag evidence$38) {
      return new AsyncRDDActions(rdd, evidence$38);
   }

   public SequenceFileRDDFunctions rddToSequenceFileRDDFunctions(final RDD rdd, final ClassTag kt, final ClassTag vt, final WritableFactory keyWritableFactory, final WritableFactory valueWritableFactory) {
      Function1 keyConverter = keyWritableFactory.convert();
      Function1 valueConverter = valueWritableFactory.convert();
      return new SequenceFileRDDFunctions(rdd, (Class)keyWritableFactory.writableClass().apply(kt), (Class)valueWritableFactory.writableClass().apply(vt), keyConverter, kt, valueConverter, vt);
   }

   public OrderedRDDFunctions rddToOrderedRDDFunctions(final RDD rdd, final Ordering evidence$39, final ClassTag evidence$40, final ClassTag evidence$41) {
      return new OrderedRDDFunctions(rdd, evidence$39, evidence$40, evidence$41, .MODULE$.apply(Tuple2.class));
   }

   public DoubleRDDFunctions doubleRDDToDoubleRDDFunctions(final RDD rdd) {
      return new DoubleRDDFunctions(rdd);
   }

   public DoubleRDDFunctions numericRDDToDoubleRDDFunctions(final RDD rdd, final Numeric num) {
      return new DoubleRDDFunctions(rdd.map((x) -> BoxesRunTime.boxToDouble($anonfun$numericRDDToDoubleRDDFunctions$1(num, x)), .MODULE$.Double()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RDD$.class);
   }

   // $FF: synthetic method
   public static final double $anonfun$numericRDDToDoubleRDDFunctions$1(final Numeric num$5, final Object x) {
      return num$5.toDouble(x);
   }

   private RDD$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
