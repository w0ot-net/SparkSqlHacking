package org.apache.spark.mllib.regression;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class IsotonicRegressionModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final double boundary;
   private final double prediction;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double boundary() {
      return this.boundary;
   }

   public double prediction() {
      return this.prediction;
   }

   public IsotonicRegressionModel$SaveLoadV1_0$Data copy(final double boundary, final double prediction) {
      return new IsotonicRegressionModel$SaveLoadV1_0$Data(boundary, prediction);
   }

   public double copy$default$1() {
      return this.boundary();
   }

   public double copy$default$2() {
      return this.prediction();
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
            return BoxesRunTime.boxToDouble(this.boundary());
         }
         case 1 -> {
            return BoxesRunTime.boxToDouble(this.prediction());
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
      return x$1 instanceof IsotonicRegressionModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "boundary";
         }
         case 1 -> {
            return "prediction";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.boundary()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.prediction()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof IsotonicRegressionModel$SaveLoadV1_0$Data) {
               IsotonicRegressionModel$SaveLoadV1_0$Data var4 = (IsotonicRegressionModel$SaveLoadV1_0$Data)x$1;
               if (this.boundary() == var4.boundary() && this.prediction() == var4.prediction() && var4.canEqual(this)) {
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

   public IsotonicRegressionModel$SaveLoadV1_0$Data(final double boundary, final double prediction) {
      this.boundary = boundary;
      this.prediction = prediction;
      Product.$init$(this);
   }
}
