package org.apache.spark.mllib.regression.impl;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class GLMRegressionModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final Vector weights;
   private final double intercept;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Vector weights() {
      return this.weights;
   }

   public double intercept() {
      return this.intercept;
   }

   public GLMRegressionModel$SaveLoadV1_0$Data copy(final Vector weights, final double intercept) {
      return new GLMRegressionModel$SaveLoadV1_0$Data(weights, intercept);
   }

   public Vector copy$default$1() {
      return this.weights();
   }

   public double copy$default$2() {
      return this.intercept();
   }

   public String productPrefix() {
      return "Data";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.weights();
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.intercept());
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
      return x$1 instanceof GLMRegressionModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "weights";
         }
         case 1 -> {
            return "intercept";
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
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof GLMRegressionModel$SaveLoadV1_0$Data) {
               GLMRegressionModel$SaveLoadV1_0$Data var4 = (GLMRegressionModel$SaveLoadV1_0$Data)x$1;
               if (this.intercept() == var4.intercept()) {
                  label44: {
                     Vector var10000 = this.weights();
                     Vector var5 = var4.weights();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
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

   public GLMRegressionModel$SaveLoadV1_0$Data(final Vector weights, final double intercept) {
      this.weights = weights;
      this.intercept = intercept;
      Product.$init$(this);
   }
}
