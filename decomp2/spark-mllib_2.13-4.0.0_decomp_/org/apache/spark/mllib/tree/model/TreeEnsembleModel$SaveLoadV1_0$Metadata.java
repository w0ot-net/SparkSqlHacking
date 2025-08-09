package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class TreeEnsembleModel$SaveLoadV1_0$Metadata implements Product, Serializable {
   private final String algo;
   private final String treeAlgo;
   private final String combiningStrategy;
   private final double[] treeWeights;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String algo() {
      return this.algo;
   }

   public String treeAlgo() {
      return this.treeAlgo;
   }

   public String combiningStrategy() {
      return this.combiningStrategy;
   }

   public double[] treeWeights() {
      return this.treeWeights;
   }

   public TreeEnsembleModel$SaveLoadV1_0$Metadata copy(final String algo, final String treeAlgo, final String combiningStrategy, final double[] treeWeights) {
      return new TreeEnsembleModel$SaveLoadV1_0$Metadata(algo, treeAlgo, combiningStrategy, treeWeights);
   }

   public String copy$default$1() {
      return this.algo();
   }

   public String copy$default$2() {
      return this.treeAlgo();
   }

   public String copy$default$3() {
      return this.combiningStrategy();
   }

   public double[] copy$default$4() {
      return this.treeWeights();
   }

   public String productPrefix() {
      return "Metadata";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.algo();
         }
         case 1 -> {
            return this.treeAlgo();
         }
         case 2 -> {
            return this.combiningStrategy();
         }
         case 3 -> {
            return this.treeWeights();
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
      return x$1 instanceof TreeEnsembleModel$SaveLoadV1_0$Metadata;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "algo";
         }
         case 1 -> {
            return "treeAlgo";
         }
         case 2 -> {
            return "combiningStrategy";
         }
         case 3 -> {
            return "treeWeights";
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
      boolean var10;
      if (this != x$1) {
         label65: {
            if (x$1 instanceof TreeEnsembleModel$SaveLoadV1_0$Metadata) {
               label58: {
                  TreeEnsembleModel$SaveLoadV1_0$Metadata var4 = (TreeEnsembleModel$SaveLoadV1_0$Metadata)x$1;
                  String var10000 = this.algo();
                  String var5 = var4.algo();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label58;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label58;
                  }

                  var10000 = this.treeAlgo();
                  String var6 = var4.treeAlgo();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label58;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label58;
                  }

                  var10000 = this.combiningStrategy();
                  String var7 = var4.combiningStrategy();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label58;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label58;
                  }

                  if (this.treeWeights() == var4.treeWeights() && var4.canEqual(this)) {
                     break label65;
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public TreeEnsembleModel$SaveLoadV1_0$Metadata(final String algo, final String treeAlgo, final String combiningStrategy, final double[] treeWeights) {
      this.algo = algo;
      this.treeAlgo = treeAlgo;
      this.combiningStrategy = combiningStrategy;
      this.treeWeights = treeWeights;
      Product.$init$(this);
   }
}
