package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

public class TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData implements Product, Serializable {
   private final int treeId;
   private final DecisionTreeModel$SaveLoadV1_0$NodeData node;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int treeId() {
      return this.treeId;
   }

   public DecisionTreeModel$SaveLoadV1_0$NodeData node() {
      return this.node;
   }

   public TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData copy(final int treeId, final DecisionTreeModel$SaveLoadV1_0$NodeData node) {
      return new TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData(treeId, node);
   }

   public int copy$default$1() {
      return this.treeId();
   }

   public DecisionTreeModel$SaveLoadV1_0$NodeData copy$default$2() {
      return this.node();
   }

   public String productPrefix() {
      return "EnsembleNodeData";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.treeId());
         }
         case 1 -> {
            return this.node();
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
      return x$1 instanceof TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "treeId";
         }
         case 1 -> {
            return "node";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.node()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData) {
               TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData var4 = (TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData)x$1;
               if (this.treeId() == var4.treeId()) {
                  label44: {
                     DecisionTreeModel$SaveLoadV1_0$NodeData var10000 = this.node();
                     DecisionTreeModel$SaveLoadV1_0$NodeData var5 = var4.node();
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

   public TreeEnsembleModel$SaveLoadV1_0$EnsembleNodeData(final int treeId, final DecisionTreeModel$SaveLoadV1_0$NodeData node) {
      this.treeId = treeId;
      this.node = node;
      Product.$init$(this);
   }
}
