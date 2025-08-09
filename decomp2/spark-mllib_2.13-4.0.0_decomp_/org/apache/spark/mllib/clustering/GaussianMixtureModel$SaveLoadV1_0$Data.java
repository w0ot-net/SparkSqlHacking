package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class GaussianMixtureModel$SaveLoadV1_0$Data implements Product, Serializable {
   private final double weight;
   private final Vector mu;
   private final Matrix sigma;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double weight() {
      return this.weight;
   }

   public Vector mu() {
      return this.mu;
   }

   public Matrix sigma() {
      return this.sigma;
   }

   public GaussianMixtureModel$SaveLoadV1_0$Data copy(final double weight, final Vector mu, final Matrix sigma) {
      return new GaussianMixtureModel$SaveLoadV1_0$Data(weight, mu, sigma);
   }

   public double copy$default$1() {
      return this.weight();
   }

   public Vector copy$default$2() {
      return this.mu();
   }

   public Matrix copy$default$3() {
      return this.sigma();
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
            return BoxesRunTime.boxToDouble(this.weight());
         }
         case 1 -> {
            return this.mu();
         }
         case 2 -> {
            return this.sigma();
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
      return x$1 instanceof GaussianMixtureModel$SaveLoadV1_0$Data;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "weight";
         }
         case 1 -> {
            return "mu";
         }
         case 2 -> {
            return "sigma";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.weight()));
      var1 = Statics.mix(var1, Statics.anyHash(this.mu()));
      var1 = Statics.mix(var1, Statics.anyHash(this.sigma()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof GaussianMixtureModel$SaveLoadV1_0$Data) {
               GaussianMixtureModel$SaveLoadV1_0$Data var4 = (GaussianMixtureModel$SaveLoadV1_0$Data)x$1;
               if (this.weight() == var4.weight()) {
                  label52: {
                     Vector var10000 = this.mu();
                     Vector var5 = var4.mu();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Matrix var7 = this.sigma();
                     Matrix var6 = var4.sigma();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public GaussianMixtureModel$SaveLoadV1_0$Data(final double weight, final Vector mu, final Matrix sigma) {
      this.weight = weight;
      this.mu = mu;
      this.sigma = sigma;
      Product.$init$(this);
   }
}
