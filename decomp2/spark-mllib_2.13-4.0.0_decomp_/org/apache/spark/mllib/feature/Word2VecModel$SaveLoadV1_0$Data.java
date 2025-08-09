package org.apache.spark.mllib.feature;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class Word2VecModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final String word;
   private final float[] vector;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String word() {
      return this.word;
   }

   public float[] vector() {
      return this.vector;
   }

   public Word2VecModel$SaveLoadV1_0$Data copy(final String word, final float[] vector) {
      return new Word2VecModel$SaveLoadV1_0$Data(word, vector);
   }

   public String copy$default$1() {
      return this.word();
   }

   public float[] copy$default$2() {
      return this.vector();
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
            return this.word();
         }
         case 1 -> {
            return this.vector();
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
      return x$1 instanceof Word2VecModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "word";
         }
         case 1 -> {
            return "vector";
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
         label49: {
            if (x$1 instanceof Word2VecModel$SaveLoadV1_0$Data) {
               label42: {
                  Word2VecModel$SaveLoadV1_0$Data var4 = (Word2VecModel$SaveLoadV1_0$Data)x$1;
                  String var10000 = this.word();
                  String var5 = var4.word();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label42;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label42;
                  }

                  if (this.vector() == var4.vector() && var4.canEqual(this)) {
                     break label49;
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

   public Word2VecModel$SaveLoadV1_0$Data(final String word, final float[] vector) {
      this.word = word;
      this.vector = vector;
      Product.$init$(this);
   }
}
