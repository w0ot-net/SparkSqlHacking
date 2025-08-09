package org.apache.commons.math3.geometry.partitioning;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;

public class RegionFactory {
   private final NodesCleaner nodeCleaner = new NodesCleaner();

   public Region buildConvex(Hyperplane... hyperplanes) {
      if (hyperplanes != null && hyperplanes.length != 0) {
         Region<S> region = hyperplanes[0].wholeSpace();
         BSPTree<S> node = region.getTree(false);
         node.setAttribute(Boolean.TRUE);

         for(Hyperplane hyperplane : hyperplanes) {
            if (node.insertCut(hyperplane)) {
               node.setAttribute((Object)null);
               node.getPlus().setAttribute(Boolean.FALSE);
               node = node.getMinus();
               node.setAttribute(Boolean.TRUE);
            } else {
               SubHyperplane<S> s = hyperplane.wholeHyperplane();

               for(BSPTree<S> tree = node; tree.getParent() != null && s != null; tree = tree.getParent()) {
                  Hyperplane<S> other = tree.getParent().getCut().getHyperplane();
                  SubHyperplane.SplitSubHyperplane<S> split = s.split(other);
                  switch (split.getSide()) {
                     case HYPER:
                        if (!hyperplane.sameOrientationAs(other)) {
                           return this.getComplement(hyperplanes[0].wholeSpace());
                        }
                        break;
                     case PLUS:
                        throw new MathIllegalArgumentException(LocalizedFormats.NOT_CONVEX_HYPERPLANES, new Object[0]);
                     default:
                        s = split.getMinus();
                  }
               }
            }
         }

         return region;
      } else {
         return null;
      }
   }

   public Region union(Region region1, Region region2) {
      BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new UnionMerger());
      tree.visit(this.nodeCleaner);
      return region1.buildNew(tree);
   }

   public Region intersection(Region region1, Region region2) {
      BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new IntersectionMerger());
      tree.visit(this.nodeCleaner);
      return region1.buildNew(tree);
   }

   public Region xor(Region region1, Region region2) {
      BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new XorMerger());
      tree.visit(this.nodeCleaner);
      return region1.buildNew(tree);
   }

   public Region difference(Region region1, Region region2) {
      BSPTree<S> tree = region1.getTree(false).merge(region2.getTree(false), new DifferenceMerger(region1, region2));
      tree.visit(this.nodeCleaner);
      return region1.buildNew(tree);
   }

   public Region getComplement(Region region) {
      return region.buildNew(this.recurseComplement(region.getTree(false)));
   }

   private BSPTree recurseComplement(BSPTree node) {
      Map<BSPTree<S>, BSPTree<S>> map = new HashMap();
      BSPTree<S> transformedTree = this.recurseComplement(node, map);

      for(Map.Entry entry : map.entrySet()) {
         if (((BSPTree)entry.getKey()).getCut() != null) {
            BoundaryAttribute<S> original = (BoundaryAttribute)((BSPTree)entry.getKey()).getAttribute();
            if (original != null) {
               BoundaryAttribute<S> transformed = (BoundaryAttribute)((BSPTree)entry.getValue()).getAttribute();

               for(BSPTree splitter : original.getSplitters()) {
                  transformed.getSplitters().add((BSPTree)map.get(splitter));
               }
            }
         }
      }

      return transformedTree;
   }

   private BSPTree recurseComplement(BSPTree node, Map map) {
      BSPTree<S> transformedNode;
      if (node.getCut() == null) {
         transformedNode = new BSPTree((Boolean)node.getAttribute() ? Boolean.FALSE : Boolean.TRUE);
      } else {
         BoundaryAttribute<S> attribute = (BoundaryAttribute)node.getAttribute();
         if (attribute != null) {
            SubHyperplane<S> plusOutside = attribute.getPlusInside() == null ? null : attribute.getPlusInside().copySelf();
            SubHyperplane<S> plusInside = attribute.getPlusOutside() == null ? null : attribute.getPlusOutside().copySelf();
            attribute = new BoundaryAttribute(plusOutside, plusInside, new NodesSet());
         }

         transformedNode = new BSPTree(node.getCut().copySelf(), this.recurseComplement(node.getPlus(), map), this.recurseComplement(node.getMinus(), map), attribute);
      }

      map.put(node, transformedNode);
      return transformedNode;
   }

   private class UnionMerger implements BSPTree.LeafMerger {
      private UnionMerger() {
      }

      public BSPTree merge(BSPTree leaf, BSPTree tree, BSPTree parentTree, boolean isPlusChild, boolean leafFromInstance) {
         if ((Boolean)leaf.getAttribute()) {
            leaf.insertInTree(parentTree, isPlusChild, RegionFactory.this.new VanishingToLeaf(true));
            return leaf;
         } else {
            tree.insertInTree(parentTree, isPlusChild, RegionFactory.this.new VanishingToLeaf(false));
            return tree;
         }
      }
   }

   private class IntersectionMerger implements BSPTree.LeafMerger {
      private IntersectionMerger() {
      }

      public BSPTree merge(BSPTree leaf, BSPTree tree, BSPTree parentTree, boolean isPlusChild, boolean leafFromInstance) {
         if ((Boolean)leaf.getAttribute()) {
            tree.insertInTree(parentTree, isPlusChild, RegionFactory.this.new VanishingToLeaf(true));
            return tree;
         } else {
            leaf.insertInTree(parentTree, isPlusChild, RegionFactory.this.new VanishingToLeaf(false));
            return leaf;
         }
      }
   }

   private class XorMerger implements BSPTree.LeafMerger {
      private XorMerger() {
      }

      public BSPTree merge(BSPTree leaf, BSPTree tree, BSPTree parentTree, boolean isPlusChild, boolean leafFromInstance) {
         BSPTree<S> t = tree;
         if ((Boolean)leaf.getAttribute()) {
            t = RegionFactory.this.recurseComplement(tree);
         }

         t.insertInTree(parentTree, isPlusChild, RegionFactory.this.new VanishingToLeaf(true));
         return t;
      }
   }

   private class DifferenceMerger implements BSPTree.LeafMerger, BSPTree.VanishingCutHandler {
      private final Region region1;
      private final Region region2;

      DifferenceMerger(Region region1, Region region2) {
         this.region1 = region1.copySelf();
         this.region2 = region2.copySelf();
      }

      public BSPTree merge(BSPTree leaf, BSPTree tree, BSPTree parentTree, boolean isPlusChild, boolean leafFromInstance) {
         if ((Boolean)leaf.getAttribute()) {
            BSPTree<S> argTree = RegionFactory.this.recurseComplement(leafFromInstance ? tree : leaf);
            argTree.insertInTree(parentTree, isPlusChild, this);
            return argTree;
         } else {
            BSPTree<S> instanceTree = leafFromInstance ? leaf : tree;
            instanceTree.insertInTree(parentTree, isPlusChild, this);
            return instanceTree;
         }
      }

      public BSPTree fixNode(BSPTree node) {
         BSPTree<S> cell = node.pruneAroundConvexCell(Boolean.TRUE, Boolean.FALSE, (Object)null);
         Region<S> r = this.region1.buildNew(cell);
         Point<S> p = r.getBarycenter();
         return new BSPTree(this.region1.checkPoint(p) == Region.Location.INSIDE && this.region2.checkPoint(p) == Region.Location.OUTSIDE);
      }
   }

   private class NodesCleaner implements BSPTreeVisitor {
      private NodesCleaner() {
      }

      public BSPTreeVisitor.Order visitOrder(BSPTree node) {
         return BSPTreeVisitor.Order.PLUS_SUB_MINUS;
      }

      public void visitInternalNode(BSPTree node) {
         node.setAttribute((Object)null);
      }

      public void visitLeafNode(BSPTree node) {
      }
   }

   private class VanishingToLeaf implements BSPTree.VanishingCutHandler {
      private final boolean inside;

      VanishingToLeaf(boolean inside) {
         this.inside = inside;
      }

      public BSPTree fixNode(BSPTree node) {
         return node.getPlus().getAttribute().equals(node.getMinus().getAttribute()) ? new BSPTree(node.getPlus().getAttribute()) : new BSPTree(this.inside);
      }
   }
}
