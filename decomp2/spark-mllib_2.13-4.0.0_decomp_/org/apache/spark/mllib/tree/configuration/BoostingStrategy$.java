package org.apache.spark.mllib.tree.configuration;

import java.io.Serializable;
import org.apache.spark.mllib.tree.loss.LogLoss$;
import org.apache.spark.mllib.tree.loss.Loss;
import org.apache.spark.mllib.tree.loss.SquaredError$;
import scala.Enumeration;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BoostingStrategy$ implements Serializable {
   public static final BoostingStrategy$ MODULE$ = new BoostingStrategy$();

   public int $lessinit$greater$default$3() {
      return 100;
   }

   public double $lessinit$greater$default$4() {
      return 0.1;
   }

   public double $lessinit$greater$default$5() {
      return 0.001;
   }

   public BoostingStrategy defaultParams(final String algo) {
      return this.defaultParams(Algo$.MODULE$.fromString(algo));
   }

   public BoostingStrategy defaultParams(final Enumeration.Value algo) {
      Strategy treeStrategy;
      label35: {
         treeStrategy = Strategy$.MODULE$.defaultStrategy(algo);
         treeStrategy.maxDepth_$eq(3);
         Enumeration.Value var10000 = Algo$.MODULE$.Classification();
         if (var10000 == null) {
            if (algo == null) {
               break label35;
            }
         } else if (var10000.equals(algo)) {
            break label35;
         }

         var10000 = Algo$.MODULE$.Regression();
         if (var10000 == null) {
            if (algo == null) {
               return new BoostingStrategy(treeStrategy, SquaredError$.MODULE$, this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5());
            }
         } else if (var10000.equals(algo)) {
            return new BoostingStrategy(treeStrategy, SquaredError$.MODULE$, this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5());
         }

         throw new IllegalArgumentException(algo + " is not supported by boosting.");
      }

      treeStrategy.numClasses_$eq(2);
      return new BoostingStrategy(treeStrategy, LogLoss$.MODULE$, this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5());
   }

   public BoostingStrategy apply(final Strategy treeStrategy, final Loss loss, final int numIterations, final double learningRate, final double validationTol) {
      return new BoostingStrategy(treeStrategy, loss, numIterations, learningRate, validationTol);
   }

   public int apply$default$3() {
      return 100;
   }

   public double apply$default$4() {
      return 0.1;
   }

   public double apply$default$5() {
      return 0.001;
   }

   public Option unapply(final BoostingStrategy x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.treeStrategy(), x$0.loss(), BoxesRunTime.boxToInteger(x$0.numIterations()), BoxesRunTime.boxToDouble(x$0.learningRate()), BoxesRunTime.boxToDouble(x$0.validationTol()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BoostingStrategy$.class);
   }

   private BoostingStrategy$() {
   }
}
