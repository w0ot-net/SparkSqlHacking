package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Vector;

public abstract class AbstractRegion implements Region {
   private BSPTree tree;
   private final double tolerance;
   private double size;
   private Point barycenter;

   protected AbstractRegion(double tolerance) {
      this.tree = new BSPTree(Boolean.TRUE);
      this.tolerance = tolerance;
   }

   protected AbstractRegion(BSPTree tree, double tolerance) {
      this.tree = tree;
      this.tolerance = tolerance;
   }

   protected AbstractRegion(Collection boundary, double tolerance) {
      // $FF: Couldn't be decompiled
   }

   public AbstractRegion(Hyperplane[] hyperplanes, double tolerance) {
      this.tolerance = tolerance;
      if (hyperplanes != null && hyperplanes.length != 0) {
         this.tree = hyperplanes[0].wholeSpace().getTree(false);
         BSPTree<S> node = this.tree;
         node.setAttribute(Boolean.TRUE);

         for(Hyperplane hyperplane : hyperplanes) {
            if (node.insertCut(hyperplane)) {
               node.setAttribute((Object)null);
               node.getPlus().setAttribute(Boolean.FALSE);
               node = node.getMinus();
               node.setAttribute(Boolean.TRUE);
            }
         }
      } else {
         this.tree = new BSPTree(Boolean.FALSE);
      }

   }

   public abstract AbstractRegion buildNew(BSPTree var1);

   public double getTolerance() {
      return this.tolerance;
   }

   private void insertCuts(BSPTree node, Collection boundary) {
      Iterator<SubHyperplane<S>> iterator = boundary.iterator();
      Hyperplane<S> inserted = null;

      while(inserted == null && iterator.hasNext()) {
         inserted = ((SubHyperplane)iterator.next()).getHyperplane();
         if (!node.insertCut(inserted.copySelf())) {
            inserted = null;
         }
      }

      if (iterator.hasNext()) {
         ArrayList<SubHyperplane<S>> plusList = new ArrayList();
         ArrayList<SubHyperplane<S>> minusList = new ArrayList();

         while(iterator.hasNext()) {
            SubHyperplane<S> other = (SubHyperplane)iterator.next();
            SubHyperplane.SplitSubHyperplane<S> split = other.split(inserted);
            switch (split.getSide()) {
               case PLUS:
                  plusList.add(other);
                  break;
               case MINUS:
                  minusList.add(other);
                  break;
               case BOTH:
                  plusList.add(split.getPlus());
                  minusList.add(split.getMinus());
            }
         }

         this.insertCuts(node.getPlus(), plusList);
         this.insertCuts(node.getMinus(), minusList);
      }
   }

   public AbstractRegion copySelf() {
      return this.buildNew(this.tree.copySelf());
   }

   public boolean isEmpty() {
      return this.isEmpty(this.tree);
   }

   public boolean isEmpty(BSPTree node) {
      if (node.getCut() == null) {
         return !(Boolean)node.getAttribute();
      } else {
         return this.isEmpty(node.getMinus()) && this.isEmpty(node.getPlus());
      }
   }

   public boolean isFull() {
      return this.isFull(this.tree);
   }

   public boolean isFull(BSPTree node) {
      if (node.getCut() == null) {
         return (Boolean)node.getAttribute();
      } else {
         return this.isFull(node.getMinus()) && this.isFull(node.getPlus());
      }
   }

   public boolean contains(Region region) {
      return (new RegionFactory()).difference(region, this).isEmpty();
   }

   public BoundaryProjection projectToBoundary(Point point) {
      BoundaryProjector<S, T> projector = new BoundaryProjector(point);
      this.getTree(true).visit(projector);
      return projector.getProjection();
   }

   public Region.Location checkPoint(Vector point) {
      return this.checkPoint((Point)point);
   }

   public Region.Location checkPoint(Point point) {
      return this.checkPoint(this.tree, point);
   }

   protected Region.Location checkPoint(BSPTree node, Vector point) {
      return this.checkPoint(node, (Point)point);
   }

   protected Region.Location checkPoint(BSPTree node, Point point) {
      BSPTree<S> cell = node.getCell(point, this.tolerance);
      if (cell.getCut() == null) {
         return (Boolean)cell.getAttribute() ? Region.Location.INSIDE : Region.Location.OUTSIDE;
      } else {
         Region.Location minusCode = this.checkPoint(cell.getMinus(), point);
         Region.Location plusCode = this.checkPoint(cell.getPlus(), point);
         return minusCode == plusCode ? minusCode : Region.Location.BOUNDARY;
      }
   }

   public BSPTree getTree(boolean includeBoundaryAttributes) {
      if (includeBoundaryAttributes && this.tree.getCut() != null && this.tree.getAttribute() == null) {
         this.tree.visit(new BoundaryBuilder());
      }

      return this.tree;
   }

   public double getBoundarySize() {
      BoundarySizeVisitor<S> visitor = new BoundarySizeVisitor();
      this.getTree(true).visit(visitor);
      return visitor.getSize();
   }

   public double getSize() {
      if (this.barycenter == null) {
         this.computeGeometricalProperties();
      }

      return this.size;
   }

   protected void setSize(double size) {
      this.size = size;
   }

   public Point getBarycenter() {
      if (this.barycenter == null) {
         this.computeGeometricalProperties();
      }

      return this.barycenter;
   }

   protected void setBarycenter(Vector barycenter) {
      this.setBarycenter((Point)barycenter);
   }

   protected void setBarycenter(Point barycenter) {
      this.barycenter = barycenter;
   }

   protected abstract void computeGeometricalProperties();

   /** @deprecated */
   @Deprecated
   public Side side(Hyperplane hyperplane) {
      InsideFinder<S> finder = new InsideFinder(this);
      finder.recurseSides(this.tree, hyperplane.wholeHyperplane());
      return finder.plusFound() ? (finder.minusFound() ? Side.BOTH : Side.PLUS) : (finder.minusFound() ? Side.MINUS : Side.HYPER);
   }

   public SubHyperplane intersection(SubHyperplane sub) {
      return this.recurseIntersection(this.tree, sub);
   }

   private SubHyperplane recurseIntersection(BSPTree node, SubHyperplane sub) {
      if (node.getCut() == null) {
         return (Boolean)node.getAttribute() ? sub.copySelf() : null;
      } else {
         Hyperplane<S> hyperplane = node.getCut().getHyperplane();
         SubHyperplane.SplitSubHyperplane<S> split = sub.split(hyperplane);
         if (split.getPlus() != null) {
            if (split.getMinus() != null) {
               SubHyperplane<S> plus = this.recurseIntersection(node.getPlus(), split.getPlus());
               SubHyperplane<S> minus = this.recurseIntersection(node.getMinus(), split.getMinus());
               if (plus == null) {
                  return minus;
               } else {
                  return minus == null ? plus : plus.reunite(minus);
               }
            } else {
               return this.recurseIntersection(node.getPlus(), sub);
            }
         } else {
            return split.getMinus() != null ? this.recurseIntersection(node.getMinus(), sub) : this.recurseIntersection(node.getPlus(), this.recurseIntersection(node.getMinus(), sub));
         }
      }
   }

   public AbstractRegion applyTransform(Transform transform) {
      Map<BSPTree<S>, BSPTree<S>> map = new HashMap();
      BSPTree<S> transformedTree = this.recurseTransform(this.getTree(false), transform, map);

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

      return this.buildNew(transformedTree);
   }

   private BSPTree recurseTransform(BSPTree node, Transform transform, Map map) {
      BSPTree<S> transformedNode;
      if (node.getCut() == null) {
         transformedNode = new BSPTree(node.getAttribute());
      } else {
         SubHyperplane<S> sub = node.getCut();
         SubHyperplane<S> tSub = ((AbstractSubHyperplane)sub).applyTransform(transform);
         BoundaryAttribute<S> attribute = (BoundaryAttribute)node.getAttribute();
         if (attribute != null) {
            SubHyperplane<S> tPO = attribute.getPlusOutside() == null ? null : ((AbstractSubHyperplane)attribute.getPlusOutside()).applyTransform(transform);
            SubHyperplane<S> tPI = attribute.getPlusInside() == null ? null : ((AbstractSubHyperplane)attribute.getPlusInside()).applyTransform(transform);
            attribute = new BoundaryAttribute(tPO, tPI, new NodesSet());
         }

         transformedNode = new BSPTree(tSub, this.recurseTransform(node.getPlus(), transform, map), this.recurseTransform(node.getMinus(), transform, map), attribute);
      }

      map.put(node, transformedNode);
      return transformedNode;
   }
}
