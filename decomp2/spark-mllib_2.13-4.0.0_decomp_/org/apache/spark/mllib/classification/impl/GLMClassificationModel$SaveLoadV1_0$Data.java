package org.apache.spark.mllib.classification.impl;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class GLMClassificationModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final Vector weights;
   private final double intercept;
   private final Option threshold;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Vector weights() {
      return this.weights;
   }

   public double intercept() {
      return this.intercept;
   }

   public Option threshold() {
      return this.threshold;
   }

   public GLMClassificationModel$SaveLoadV1_0$Data copy(final Vector weights, final double intercept, final Option threshold) {
      return new GLMClassificationModel$SaveLoadV1_0$Data(weights, intercept, threshold);
   }

   public Vector copy$default$1() {
      return this.weights();
   }

   public double copy$default$2() {
      return this.intercept();
   }

   public Option copy$default$3() {
      return this.threshold();
   }

   public String productPrefix() {
      return "Data";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.weights();
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.intercept());
         }
         case 2 -> {
            return this.threshold();
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
      return x$1 instanceof GLMClassificationModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "weights";
         }
         case 1 -> {
            return "intercept";
         }
         case 2 -> {
            return "threshold";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.weights()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.intercept()));
      var1 = Statics.mix(var1, Statics.anyHash(this.threshold()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof GLMClassificationModel$SaveLoadV1_0$Data) {
               GLMClassificationModel$SaveLoadV1_0$Data var4 = (GLMClassificationModel$SaveLoadV1_0$Data)x$1;
               if (this.intercept() == var4.intercept()) {
                  label52: {
                     Vector var10000 = this.weights();
                     Vector var5 = var4.weights();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Option var7 = this.threshold();
                     Option var6 = var4.threshold();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public GLMClassificationModel$SaveLoadV1_0$Data(final Vector weights, final double intercept, final Option threshold) {
      this.weights = weights;
      this.intercept = intercept;
      this.threshold = threshold;
      Product.$init$(this);
   }
}
