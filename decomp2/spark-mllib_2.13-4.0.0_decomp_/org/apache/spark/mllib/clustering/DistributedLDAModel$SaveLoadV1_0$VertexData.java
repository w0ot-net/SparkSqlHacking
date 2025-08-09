package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class DistributedLDAModel$SaveLoadV1_0$VertexData implements Product, Serializable {
   private final long id;
   private final Vector topicWeights;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long id() {
      return this.id;
   }

   public Vector topicWeights() {
      return this.topicWeights;
   }

   public DistributedLDAModel$SaveLoadV1_0$VertexData copy(final long id, final Vector topicWeights) {
      return new DistributedLDAModel$SaveLoadV1_0$VertexData(id, topicWeights);
   }

   public long copy$default$1() {
      return this.id();
   }

   public Vector copy$default$2() {
      return this.topicWeights();
   }

   public String productPrefix() {
      return "VertexData";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.id());
         }
         case 1 -> {
            return this.topicWeights();
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
      return x$1 instanceof DistributedLDAModel$SaveLoadV1_0$VertexData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "topicWeights";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.id()));
      var1 = Statics.mix(var1, Statics.anyHash(this.topicWeights()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof DistributedLDAModel$SaveLoadV1_0$VertexData) {
               DistributedLDAModel$SaveLoadV1_0$VertexData var4 = (DistributedLDAModel$SaveLoadV1_0$VertexData)x$1;
               if (this.id() == var4.id()) {
                  label44: {
                     Vector var10000 = this.topicWeights();
                     Vector var5 = var4.topicWeights();
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

   public DistributedLDAModel$SaveLoadV1_0$VertexData(final long id, final Vector topicWeights) {
      this.id = id;
      this.topicWeights = topicWeights;
      Product.$init$(this);
   }
}
