package org.apache.spark.mllib.regression;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.NumericParser$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class LabeledPoint$ implements Serializable {
   public static final LabeledPoint$ MODULE$ = new LabeledPoint$();

   public LabeledPoint parse(final String s) {
      if (s.startsWith("(")) {
         Object var3 = NumericParser$.MODULE$.parse(s);
         if (var3 instanceof Seq) {
            Seq var4 = (Seq)var3;
            SeqOps var5 = .MODULE$.Seq().unapplySeq(var4);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var5) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var5)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var5), 2) == 0) {
               Object label = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var5), 0);
               Object numeric = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var5), 1);
               if (label instanceof Double) {
                  double var8 = BoxesRunTime.unboxToDouble(label);
                  if (numeric instanceof Object) {
                     return new LabeledPoint(var8, Vectors$.MODULE$.parseNumeric(numeric));
                  }
               }
            }
         }

         throw new SparkException("Cannot parse " + var3 + ".");
      } else {
         String[] parts = scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(s), ',');
         double label = Double.parseDouble(parts[0]);
         Vector features = Vectors$.MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(parts[1].trim()), ' ')), (x$1) -> BoxesRunTime.boxToDouble($anonfun$parse$1(x$1)), scala.reflect.ClassTag..MODULE$.Double()));
         return new LabeledPoint(label, features);
      }
   }

   public LabeledPoint fromML(final org.apache.spark.ml.feature.LabeledPoint point) {
      return new LabeledPoint(point.label(), Vectors$.MODULE$.fromML(point.features()));
   }

   public LabeledPoint apply(final double label, final Vector features) {
      return new LabeledPoint(label, features);
   }

   public Option unapply(final LabeledPoint x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.label()), x$0.features())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LabeledPoint$.class);
   }

   // $FF: synthetic method
   public static final double $anonfun$parse$1(final String x$1) {
      return Double.parseDouble(x$1);
   }

   private LabeledPoint$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
