package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import org.apache.spark.mllib.tree.configuration.FeatureType$;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class DecisionTreeModel$SaveLoadV1_0$SplitData implements Product, Serializable {
   private final int feature;
   private final double threshold;
   private final int featureType;
   private final Seq categories;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int feature() {
      return this.feature;
   }

   public double threshold() {
      return this.threshold;
   }

   public int featureType() {
      return this.featureType;
   }

   public Seq categories() {
      return this.categories;
   }

   public Split toSplit() {
      return new Split(this.feature(), this.threshold(), FeatureType$.MODULE$.apply(this.featureType()), this.categories().toList());
   }

   public DecisionTreeModel$SaveLoadV1_0$SplitData copy(final int feature, final double threshold, final int featureType, final Seq categories) {
      return new DecisionTreeModel$SaveLoadV1_0$SplitData(feature, threshold, featureType, categories);
   }

   public int copy$default$1() {
      return this.feature();
   }

   public double copy$default$2() {
      return this.threshold();
   }

   public int copy$default$3() {
      return this.featureType();
   }

   public Seq copy$default$4() {
      return this.categories();
   }

   public String productPrefix() {
      return "SplitData";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.feature());
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.threshold());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.featureType());
         }
         case 3 -> {
            return this.categories();
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
      return x$1 instanceof DecisionTreeModel$SaveLoadV1_0$SplitData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "feature";
         }
         case 1 -> {
            return "threshold";
         }
         case 2 -> {
            return "featureType";
         }
         case 3 -> {
            return "categories";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.feature());
      var1 = Statics.mix(var1, Statics.doubleHash(this.threshold()));
      var1 = Statics.mix(var1, this.featureType());
      var1 = Statics.mix(var1, Statics.anyHash(this.categories()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof DecisionTreeModel$SaveLoadV1_0$SplitData) {
               DecisionTreeModel$SaveLoadV1_0$SplitData var4 = (DecisionTreeModel$SaveLoadV1_0$SplitData)x$1;
               if (this.feature() == var4.feature() && this.threshold() == var4.threshold() && this.featureType() == var4.featureType()) {
                  label52: {
                     Seq var10000 = this.categories();
                     Seq var5 = var4.categories();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public DecisionTreeModel$SaveLoadV1_0$SplitData(final int feature, final double threshold, final int featureType, final Seq categories) {
      this.feature = feature;
      this.threshold = threshold;
      this.featureType = featureType;
      this.categories = categories;
      Product.$init$(this);
   }
}
