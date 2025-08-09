package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.util.FastMath;

public class BSPTree {
   private SubHyperplane cut;
   private BSPTree plus;
   private BSPTree minus;
   private BSPTree parent;
   private Object attribute;

   public BSPTree() {
      this.cut = null;
      this.plus = null;
      this.minus = null;
      this.parent = null;
      this.attribute = null;
   }

   public BSPTree(Object attribute) {
      this.cut = null;
      this.plus = null;
      this.minus = null;
      this.parent = null;
      this.attribute = attribute;
   }

   public BSPTree(SubHyperplane cut, BSPTree plus, BSPTree minus, Object attribute) {
      this.cut = cut;
      this.plus = plus;
      this.minus = minus;
      this.parent = null;
      this.attribute = attribute;
      plus.parent = this;
      minus.parent = this;
   }

   public boolean insertCut(Hyperplane hyperplane) {
      if (this.cut != null) {
         this.plus.parent = null;
         this.minus.parent = null;
      }

      SubHyperplane<S> chopped = this.fitToCell(hyperplane.wholeHyperplane());
      if (chopped != null && !chopped.isEmpty()) {
         this.cut = chopped;
         this.plus = new BSPTree();
         this.plus.parent = this;
         this.minus = new BSPTree();
         this.minus.parent = this;
         return true;
      } else {
         this.cut = null;
         this.plus = null;
         this.minus = null;
         return false;
      }
   }

   public BSPTree copySelf() {
      return this.cut == null ? new BSPTree(this.attribute) : new BSPTree(this.cut.copySelf(), this.plus.copySelf(), this.minus.copySelf(), this.attribute);
   }

   public SubHyperplane getCut() {
      return this.cut;
   }

   public BSPTree getPlus() {
      return this.plus;
   }

   public BSPTree getMinus() {
      return this.minus;
   }

   public BSPTree getParent() {
      return this.parent;
   }

   public void setAttribute(Object attribute) {
      this.attribute = attribute;
   }

   public Object getAttribute() {
      return this.attribute;
   }

   public void visit(BSPTreeVisitor visitor) {
      if (this.cut == null) {
         visitor.visitLeafNode(this);
      } else {
         switch (visitor.visitOrder(this)) {
            case PLUS_MINUS_SUB:
               this.plus.visit(visitor);
               this.minus.visit(visitor);
               visitor.visitInternalNode(this);
               break;
            case PLUS_SUB_MINUS:
               this.plus.visit(visitor);
               visitor.visitInternalNode(this);
               this.minus.visit(visitor);
               break;
            case MINUS_PLUS_SUB:
               this.minus.visit(visitor);
               this.plus.visit(visitor);
               visitor.visitInternalNode(this);
               break;
            case MINUS_SUB_PLUS:
               this.minus.visit(visitor);
               visitor.visitInternalNode(this);
               this.plus.visit(visitor);
               break;
            case SUB_PLUS_MINUS:
               visitor.visitInternalNode(this);
               this.plus.visit(visitor);
               this.minus.visit(visitor);
               break;
            case SUB_MINUS_PLUS:
               visitor.visitInternalNode(this);
               this.minus.visit(visitor);
               this.plus.visit(visitor);
               break;
            default:
               throw new MathInternalError();
         }
      }

   }

   private SubHyperplane fitToCell(SubHyperplane sub) {
      SubHyperplane<S> s = sub;

      for(BSPTree<S> tree = this; tree.parent != null && s != null; tree = tree.parent) {
         if (tree == tree.parent.plus) {
            s = s.split(tree.parent.cut.getHyperplane()).getPlus();
         } else {
            s = s.split(tree.parent.cut.getHyperplane()).getMinus();
         }
      }

      return s;
   }

   /** @deprecated */
   @Deprecated
   public BSPTree getCell(Vector point) {
      return this.getCell(point, 1.0E-10);
   }

   public BSPTree getCell(Point point, double tolerance) {
      if (this.cut == null) {
         return this;
      } else {
         double offset = this.cut.getHyperplane().getOffset(point);
         if (FastMath.abs(offset) < tolerance) {
            return this;
         } else {
            return offset <= (double)0.0F ? this.minus.getCell(point, tolerance) : this.plus.getCell(point, tolerance);
         }
      }
   }

   public List getCloseCuts(Point point, double maxOffset) {
      List<BSPTree<S>> close = new ArrayList();
      this.recurseCloseCuts(point, maxOffset, close);
      return close;
   }

   private void recurseCloseCuts(Point point, double maxOffset, List close) {
      if (this.cut != null) {
         double offset = this.cut.getHyperplane().getOffset(point);
         if (offset < -maxOffset) {
            this.minus.recurseCloseCuts(point, maxOffset, close);
         } else if (offset > maxOffset) {
            this.plus.recurseCloseCuts(point, maxOffset, close);
         } else {
            close.add(this);
            this.minus.recurseCloseCuts(point, maxOffset, close);
            this.plus.recurseCloseCuts(point, maxOffset, close);
         }
      }

   }

   private void condense() {
      if (this.cut != null && this.plus.cut == null && this.minus.cut == null && (this.plus.attribute == null && this.minus.attribute == null || this.plus.attribute != null && this.plus.attribute.equals(this.minus.attribute))) {
         this.attribute = this.plus.attribute == null ? this.minus.attribute : this.plus.attribute;
         this.cut = null;
         this.plus = null;
         this.minus = null;
      }

   }

   public BSPTree merge(BSPTree tree, LeafMerger leafMerger) {
      return this.merge(tree, leafMerger, (BSPTree)null, false);
   }

   private BSPTree merge(BSPTree tree, LeafMerger leafMerger, BSPTree parentTree, boolean isPlusChild) {
      if (this.cut == null) {
         return leafMerger.merge(this, tree, parentTree, isPlusChild, true);
      } else if (tree.cut == null) {
         return leafMerger.merge(tree, this, parentTree, isPlusChild, false);
      } else {
         BSPTree<S> merged = tree.split(this.cut);
         if (parentTree != null) {
            merged.parent = parentTree;
            if (isPlusChild) {
               parentTree.plus = merged;
            } else {
               parentTree.minus = merged;
            }
         }

         this.plus.merge(merged.plus, leafMerger, merged, true);
         this.minus.merge(merged.minus, leafMerger, merged, false);
         merged.condense();
         if (merged.cut != null) {
            merged.cut = merged.fitToCell(merged.cut.getHyperplane().wholeHyperplane());
         }

         return merged;
      }
   }

   public BSPTree split(SubHyperplane sub) {
      if (this.cut == null) {
         return new BSPTree(sub, this.copySelf(), new BSPTree(this.attribute), (Object)null);
      } else {
         Hyperplane<S> cHyperplane = this.cut.getHyperplane();
         Hyperplane<S> sHyperplane = sub.getHyperplane();
         SubHyperplane.SplitSubHyperplane<S> subParts = sub.split(cHyperplane);
         switch (subParts.getSide()) {
            case PLUS:
               BSPTree<S> split = this.plus.split(sub);
               if (this.cut.split(sHyperplane).getSide() == Side.PLUS) {
                  split.plus = new BSPTree(this.cut.copySelf(), split.plus, this.minus.copySelf(), this.attribute);
                  split.plus.condense();
                  split.plus.parent = split;
               } else {
                  split.minus = new BSPTree(this.cut.copySelf(), split.minus, this.minus.copySelf(), this.attribute);
                  split.minus.condense();
                  split.minus.parent = split;
               }

               return split;
            case MINUS:
               BSPTree<S> split = this.minus.split(sub);
               if (this.cut.split(sHyperplane).getSide() == Side.PLUS) {
                  split.plus = new BSPTree(this.cut.copySelf(), this.plus.copySelf(), split.plus, this.attribute);
                  split.plus.condense();
                  split.plus.parent = split;
               } else {
                  split.minus = new BSPTree(this.cut.copySelf(), this.plus.copySelf(), split.minus, this.attribute);
                  split.minus.condense();
                  split.minus.parent = split;
               }

               return split;
            case BOTH:
               SubHyperplane.SplitSubHyperplane<S> cutParts = this.cut.split(sHyperplane);
               BSPTree<S> split = new BSPTree(sub, this.plus.split(subParts.getPlus()), this.minus.split(subParts.getMinus()), (Object)null);
               split.plus.cut = cutParts.getPlus();
               split.minus.cut = cutParts.getMinus();
               BSPTree<S> tmp = split.plus.minus;
               split.plus.minus = split.minus.plus;
               split.plus.minus.parent = split.plus;
               split.minus.plus = tmp;
               split.minus.plus.parent = split.minus;
               split.plus.condense();
               split.minus.condense();
               return split;
            default:
               return cHyperplane.sameOrientationAs(sHyperplane) ? new BSPTree(sub, this.plus.copySelf(), this.minus.copySelf(), this.attribute) : new BSPTree(sub, this.minus.copySelf(), this.plus.copySelf(), this.attribute);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public void insertInTree(BSPTree parentTree, boolean isPlusChild) {
      // $FF: Couldn't be decompiled
   }

   public void insertInTree(BSPTree parentTree, boolean isPlusChild, VanishingCutHandler vanishingHandler) {
      this.parent = parentTree;
      if (parentTree != null) {
         if (isPlusChild) {
            parentTree.plus = this;
         } else {
            parentTree.minus = this;
         }
      }

      if (this.cut != null) {
         for(BSPTree<S> tree = this; tree.parent != null; tree = tree.parent) {
            Hyperplane<S> hyperplane = tree.parent.cut.getHyperplane();
            if (tree == tree.parent.plus) {
               this.cut = this.cut.split(hyperplane).getPlus();
               this.plus.chopOffMinus(hyperplane, vanishingHandler);
               this.minus.chopOffMinus(hyperplane, vanishingHandler);
            } else {
               this.cut = this.cut.split(hyperplane).getMinus();
               this.plus.chopOffPlus(hyperplane, vanishingHandler);
               this.minus.chopOffPlus(hyperplane, vanishingHandler);
            }

            if (this.cut == null) {
               BSPTree<S> fixed = vanishingHandler.fixNode(this);
               this.cut = fixed.cut;
               this.plus = fixed.plus;
               this.minus = fixed.minus;
               this.attribute = fixed.attribute;
               if (this.cut == null) {
                  break;
               }
            }
         }

         this.condense();
      }

   }

   public BSPTree pruneAroundConvexCell(Object cellAttribute, Object otherLeafsAttributes, Object internalAttributes) {
      BSPTree<S> tree = new BSPTree(cellAttribute);

      for(BSPTree<S> current = this; current.parent != null; current = current.parent) {
         SubHyperplane<S> parentCut = current.parent.cut.copySelf();
         BSPTree<S> sibling = new BSPTree(otherLeafsAttributes);
         if (current == current.parent.plus) {
            tree = new BSPTree(parentCut, tree, sibling, internalAttributes);
         } else {
            tree = new BSPTree(parentCut, sibling, tree, internalAttributes);
         }
      }

      return tree;
   }

   private void chopOffMinus(Hyperplane hyperplane, VanishingCutHandler vanishingHandler) {
      if (this.cut != null) {
         this.cut = this.cut.split(hyperplane).getPlus();
         this.plus.chopOffMinus(hyperplane, vanishingHandler);
         this.minus.chopOffMinus(hyperplane, vanishingHandler);
         if (this.cut == null) {
            BSPTree<S> fixed = vanishingHandler.fixNode(this);
            this.cut = fixed.cut;
            this.plus = fixed.plus;
            this.minus = fixed.minus;
            this.attribute = fixed.attribute;
         }
      }

   }

   private void chopOffPlus(Hyperplane hyperplane, VanishingCutHandler vanishingHandler) {
      if (this.cut != null) {
         this.cut = this.cut.split(hyperplane).getMinus();
         this.plus.chopOffPlus(hyperplane, vanishingHandler);
         this.minus.chopOffPlus(hyperplane, vanishingHandler);
         if (this.cut == null) {
            BSPTree<S> fixed = vanishingHandler.fixNode(this);
            this.cut = fixed.cut;
            this.plus = fixed.plus;
            this.minus = fixed.minus;
            this.attribute = fixed.attribute;
         }
      }

   }

   public interface LeafMerger {
      BSPTree merge(BSPTree var1, BSPTree var2, BSPTree var3, boolean var4, boolean var5);
   }

   public interface VanishingCutHandler {
      BSPTree fixNode(BSPTree var1);
   }
}
