package org.apache.spark.mllib.feature;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class ChiSqSelectorModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final int feature;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int feature() {
      return this.feature;
   }

   public ChiSqSelectorModel$SaveLoadV1_0$Data copy(final int feature) {
      return new ChiSqSelectorModel$SaveLoadV1_0$Data(feature);
   }

   public int copy$default$1() {
      return this.feature();
   }

   public String productPrefix() {
      return "Data";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.feature());
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
      return x$1 instanceof ChiSqSelectorModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "feature";
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
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof ChiSqSelectorModel$SaveLoadV1_0$Data) {
               ChiSqSelectorModel$SaveLoadV1_0$Data var4 = (ChiSqSelectorModel$SaveLoadV1_0$Data)x$1;
               if (this.feature() == var4.feature() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public ChiSqSelectorModel$SaveLoadV1_0$Data(final int feature) {
      this.feature = feature;
      Product.$init$(this);
   }
}
