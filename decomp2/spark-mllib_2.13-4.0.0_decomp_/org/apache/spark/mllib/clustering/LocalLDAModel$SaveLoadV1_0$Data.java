package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class LocalLDAModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final Vector topic;
   private final int index;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Vector topic() {
      return this.topic;
   }

   public int index() {
      return this.index;
   }

   public LocalLDAModel$SaveLoadV1_0$Data copy(final Vector topic, final int index) {
      return new LocalLDAModel$SaveLoadV1_0$Data(topic, index);
   }

   public Vector copy$default$1() {
      return this.topic();
   }

   public int copy$default$2() {
      return this.index();
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
            return this.topic();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.index());
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
      return x$1 instanceof LocalLDAModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "topic";
         }
         case 1 -> {
            return "index";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.topic()));
      var1 = Statics.mix(var1, this.index());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof LocalLDAModel$SaveLoadV1_0$Data) {
               LocalLDAModel$SaveLoadV1_0$Data var4 = (LocalLDAModel$SaveLoadV1_0$Data)x$1;
               if (this.index() == var4.index()) {
                  label44: {
                     Vector var10000 = this.topic();
                     Vector var5 = var4.topic();
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

   public LocalLDAModel$SaveLoadV1_0$Data(final Vector topic, final int index) {
      this.topic = topic;
      this.index = index;
      Product.$init$(this);
   }
}
