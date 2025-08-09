package org.apache.spark.mllib.classification;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class NaiveBayesModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final double[] labels;
   private final double[] pi;
   private final double[][] theta;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double[] labels() {
      return this.labels;
   }

   public double[] pi() {
      return this.pi;
   }

   public double[][] theta() {
      return this.theta;
   }

   public NaiveBayesModel$SaveLoadV1_0$Data copy(final double[] labels, final double[] pi, final double[][] theta) {
      return new NaiveBayesModel$SaveLoadV1_0$Data(labels, pi, theta);
   }

   public double[] copy$default$1() {
      return this.labels();
   }

   public double[] copy$default$2() {
      return this.pi();
   }

   public double[][] copy$default$3() {
      return this.theta();
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
            return this.labels();
         }
         case 1 -> {
            return this.pi();
         }
         case 2 -> {
            return this.theta();
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
      return x$1 instanceof NaiveBayesModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "labels";
         }
         case 1 -> {
            return "pi";
         }
         case 2 -> {
            return "theta";
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
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof NaiveBayesModel$SaveLoadV1_0$Data) {
               NaiveBayesModel$SaveLoadV1_0$Data var4 = (NaiveBayesModel$SaveLoadV1_0$Data)x$1;
               if (this.labels() == var4.labels() && this.pi() == var4.pi() && this.theta() == var4.theta() && var4.canEqual(this)) {
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

   public NaiveBayesModel$SaveLoadV1_0$Data(final double[] labels, final double[] pi, final double[][] theta) {
      this.labels = labels;
      this.pi = pi;
      this.theta = theta;
      Product.$init$(this);
   }
}
