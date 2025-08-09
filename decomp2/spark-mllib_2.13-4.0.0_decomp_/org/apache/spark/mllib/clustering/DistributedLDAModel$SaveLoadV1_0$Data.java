package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class DistributedLDAModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final Vector globalTopicTotals;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Vector globalTopicTotals() {
      return this.globalTopicTotals;
   }

   public DistributedLDAModel$SaveLoadV1_0$Data copy(final Vector globalTopicTotals) {
      return new DistributedLDAModel$SaveLoadV1_0$Data(globalTopicTotals);
   }

   public Vector copy$default$1() {
      return this.globalTopicTotals();
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
            return this.globalTopicTotals();
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
      return x$1 instanceof DistributedLDAModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "globalTopicTotals";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof DistributedLDAModel$SaveLoadV1_0$Data) {
               label40: {
                  DistributedLDAModel$SaveLoadV1_0$Data var4 = (DistributedLDAModel$SaveLoadV1_0$Data)x$1;
                  Vector var10000 = this.globalTopicTotals();
                  Vector var5 = var4.globalTopicTotals();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
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

   public DistributedLDAModel$SaveLoadV1_0$Data(final Vector globalTopicTotals) {
      this.globalTopicTotals = globalTopicTotals;
      Product.$init$(this);
   }
}
