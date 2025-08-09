package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class DistributedLDAModel$SaveLoadV1_0$EdgeData implements Product, Serializable {
   private final long srcId;
   private final long dstId;
   private final double tokenCounts;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long srcId() {
      return this.srcId;
   }

   public long dstId() {
      return this.dstId;
   }

   public double tokenCounts() {
      return this.tokenCounts;
   }

   public DistributedLDAModel$SaveLoadV1_0$EdgeData copy(final long srcId, final long dstId, final double tokenCounts) {
      return new DistributedLDAModel$SaveLoadV1_0$EdgeData(srcId, dstId, tokenCounts);
   }

   public long copy$default$1() {
      return this.srcId();
   }

   public long copy$default$2() {
      return this.dstId();
   }

   public double copy$default$3() {
      return this.tokenCounts();
   }

   public String productPrefix() {
      return "EdgeData";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.srcId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.dstId());
         }
         case 2 -> {
            return BoxesRunTime.boxToDouble(this.tokenCounts());
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
      return x$1 instanceof DistributedLDAModel$SaveLoadV1_0$EdgeData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "srcId";
         }
         case 1 -> {
            return "dstId";
         }
         case 2 -> {
            return "tokenCounts";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.srcId()));
      var1 = Statics.mix(var1, Statics.longHash(this.dstId()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.tokenCounts()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof DistributedLDAModel$SaveLoadV1_0$EdgeData) {
               DistributedLDAModel$SaveLoadV1_0$EdgeData var4 = (DistributedLDAModel$SaveLoadV1_0$EdgeData)x$1;
               if (this.srcId() == var4.srcId() && this.dstId() == var4.dstId() && this.tokenCounts() == var4.tokenCounts() && var4.canEqual(this)) {
                  break label40;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public DistributedLDAModel$SaveLoadV1_0$EdgeData(final long srcId, final long dstId, final double tokenCounts) {
      this.srcId = srcId;
      this.dstId = dstId;
      this.tokenCounts = tokenCounts;
      Product.$init$(this);
   }
}
