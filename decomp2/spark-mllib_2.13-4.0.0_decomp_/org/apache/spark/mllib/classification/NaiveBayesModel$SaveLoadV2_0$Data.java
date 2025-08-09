package org.apache.spark.mllib.classification;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class NaiveBayesModel$SaveLoadV2_0$Data implements Product, Serializable {
   private final double[] labels;
   private final double[] pi;
   private final double[][] theta;
   private final String modelType;

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

   public String modelType() {
      return this.modelType;
   }

   public NaiveBayesModel$SaveLoadV2_0$Data copy(final double[] labels, final double[] pi, final double[][] theta, final String modelType) {
      return new NaiveBayesModel$SaveLoadV2_0$Data(labels, pi, theta, modelType);
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

   public String copy$default$4() {
      return this.modelType();
   }

   public String productPrefix() {
      return "Data";
   }

   public int productArity() {
      return 4;
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
         case 3 -> {
            return this.modelType();
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
      return x$1 instanceof NaiveBayesModel$SaveLoadV2_0$Data;
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
         case 3 -> {
            return "modelType";
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
         label59: {
            if (x$1 instanceof NaiveBayesModel$SaveLoadV2_0$Data) {
               NaiveBayesModel$SaveLoadV2_0$Data var4 = (NaiveBayesModel$SaveLoadV2_0$Data)x$1;
               if (this.labels() == var4.labels() && this.pi() == var4.pi() && this.theta() == var4.theta()) {
                  label52: {
                     String var10000 = this.modelType();
                     String var5 = var4.modelType();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
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

   public NaiveBayesModel$SaveLoadV2_0$Data(final double[] labels, final double[] pi, final double[][] theta, final String modelType) {
      this.labels = labels;
      this.pi = pi;
      this.theta = theta;
      this.modelType = modelType;
      Product.$init$(this);
   }
}
