package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class DecisionTreeModel$SaveLoadV1_0$NodeData implements Product, Serializable {
   private final int treeId;
   private final int nodeId;
   private final DecisionTreeModel$SaveLoadV1_0$PredictData predict;
   private final double impurity;
   private final boolean isLeaf;
   private final Option split;
   private final Option leftNodeId;
   private final Option rightNodeId;
   private final Option infoGain;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int treeId() {
      return this.treeId;
   }

   public int nodeId() {
      return this.nodeId;
   }

   public DecisionTreeModel$SaveLoadV1_0$PredictData predict() {
      return this.predict;
   }

   public double impurity() {
      return this.impurity;
   }

   public boolean isLeaf() {
      return this.isLeaf;
   }

   public Option split() {
      return this.split;
   }

   public Option leftNodeId() {
      return this.leftNodeId;
   }

   public Option rightNodeId() {
      return this.rightNodeId;
   }

   public Option infoGain() {
      return this.infoGain;
   }

   public DecisionTreeModel$SaveLoadV1_0$NodeData copy(final int treeId, final int nodeId, final DecisionTreeModel$SaveLoadV1_0$PredictData predict, final double impurity, final boolean isLeaf, final Option split, final Option leftNodeId, final Option rightNodeId, final Option infoGain) {
      return new DecisionTreeModel$SaveLoadV1_0$NodeData(treeId, nodeId, predict, impurity, isLeaf, split, leftNodeId, rightNodeId, infoGain);
   }

   public int copy$default$1() {
      return this.treeId();
   }

   public int copy$default$2() {
      return this.nodeId();
   }

   public DecisionTreeModel$SaveLoadV1_0$PredictData copy$default$3() {
      return this.predict();
   }

   public double copy$default$4() {
      return this.impurity();
   }

   public boolean copy$default$5() {
      return this.isLeaf();
   }

   public Option copy$default$6() {
      return this.split();
   }

   public Option copy$default$7() {
      return this.leftNodeId();
   }

   public Option copy$default$8() {
      return this.rightNodeId();
   }

   public Option copy$default$9() {
      return this.infoGain();
   }

   public String productPrefix() {
      return "NodeData";
   }

   public int productArity() {
      return 9;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.treeId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.nodeId());
         }
         case 2 -> {
            return this.predict();
         }
         case 3 -> {
            return BoxesRunTime.boxToDouble(this.impurity());
         }
         case 4 -> {
            return BoxesRunTime.boxToBoolean(this.isLeaf());
         }
         case 5 -> {
            return this.split();
         }
         case 6 -> {
            return this.leftNodeId();
         }
         case 7 -> {
            return this.rightNodeId();
         }
         case 8 -> {
            return this.infoGain();
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
      return x$1 instanceof DecisionTreeModel$SaveLoadV1_0$NodeData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "treeId";
         }
         case 1 -> {
            return "nodeId";
         }
         case 2 -> {
            return "predict";
         }
         case 3 -> {
            return "impurity";
         }
         case 4 -> {
            return "isLeaf";
         }
         case 5 -> {
            return "split";
         }
         case 6 -> {
            return "leftNodeId";
         }
         case 7 -> {
            return "rightNodeId";
         }
         case 8 -> {
            return "infoGain";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.treeId());
      var1 = Statics.mix(var1, this.nodeId());
      var1 = Statics.mix(var1, Statics.anyHash(this.predict()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.impurity()));
      var1 = Statics.mix(var1, this.isLeaf() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.split()));
      var1 = Statics.mix(var1, Statics.anyHash(this.leftNodeId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.rightNodeId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.infoGain()));
      return Statics.finalizeHash(var1, 9);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label95: {
            if (x$1 instanceof DecisionTreeModel$SaveLoadV1_0$NodeData) {
               DecisionTreeModel$SaveLoadV1_0$NodeData var4 = (DecisionTreeModel$SaveLoadV1_0$NodeData)x$1;
               if (this.treeId() == var4.treeId() && this.nodeId() == var4.nodeId() && this.impurity() == var4.impurity() && this.isLeaf() == var4.isLeaf()) {
                  label88: {
                     DecisionTreeModel$SaveLoadV1_0$PredictData var10000 = this.predict();
                     DecisionTreeModel$SaveLoadV1_0$PredictData var5 = var4.predict();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label88;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label88;
                     }

                     Option var10 = this.split();
                     Option var6 = var4.split();
                     if (var10 == null) {
                        if (var6 != null) {
                           break label88;
                        }
                     } else if (!var10.equals(var6)) {
                        break label88;
                     }

                     var10 = this.leftNodeId();
                     Option var7 = var4.leftNodeId();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label88;
                        }
                     } else if (!var10.equals(var7)) {
                        break label88;
                     }

                     var10 = this.rightNodeId();
                     Option var8 = var4.rightNodeId();
                     if (var10 == null) {
                        if (var8 != null) {
                           break label88;
                        }
                     } else if (!var10.equals(var8)) {
                        break label88;
                     }

                     var10 = this.infoGain();
                     Option var9 = var4.infoGain();
                     if (var10 == null) {
                        if (var9 != null) {
                           break label88;
                        }
                     } else if (!var10.equals(var9)) {
                        break label88;
                     }

                     if (var4.canEqual(this)) {
                        break label95;
                     }
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public DecisionTreeModel$SaveLoadV1_0$NodeData(final int treeId, final int nodeId, final DecisionTreeModel$SaveLoadV1_0$PredictData predict, final double impurity, final boolean isLeaf, final Option split, final Option leftNodeId, final Option rightNodeId, final Option infoGain) {
      this.treeId = treeId;
      this.nodeId = nodeId;
      this.predict = predict;
      this.impurity = impurity;
      this.isLeaf = isLeaf;
      this.split = split;
      this.leftNodeId = leftNodeId;
      this.rightNodeId = rightNodeId;
      this.infoGain = infoGain;
      Product.$init$(this);
   }
}
