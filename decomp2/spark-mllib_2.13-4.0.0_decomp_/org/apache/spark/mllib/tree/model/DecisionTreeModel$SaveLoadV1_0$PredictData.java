package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class DecisionTreeModel$SaveLoadV1_0$PredictData implements Product, Serializable {
   private final double predict;
   private final double prob;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double predict() {
      return this.predict;
   }

   public double prob() {
      return this.prob;
   }

   public Predict toPredict() {
      return new Predict(this.predict(), this.prob());
   }

   public DecisionTreeModel$SaveLoadV1_0$PredictData copy(final double predict, final double prob) {
      return new DecisionTreeModel$SaveLoadV1_0$PredictData(predict, prob);
   }

   public double copy$default$1() {
      return this.predict();
   }

   public double copy$default$2() {
      return this.prob();
   }

   public String productPrefix() {
      return "PredictData";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToDouble(this.predict());
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.prob());
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof DecisionTreeModel$SaveLoadV1_0$PredictData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "predict";
         }
         case 1 -> {
            return "prob";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.predict()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.prob()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof DecisionTreeModel$SaveLoadV1_0$PredictData) {
               DecisionTreeModel$SaveLoadV1_0$PredictData var4 = (DecisionTreeModel$SaveLoadV1_0$PredictData)x$1;
               if (this.predict() == var4.predict() && this.prob() == var4.prob() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public DecisionTreeModel$SaveLoadV1_0$PredictData(final double predict, final double prob) {
      this.predict = predict;
      this.prob = prob;
      Product.$init$(this);
   }
}
