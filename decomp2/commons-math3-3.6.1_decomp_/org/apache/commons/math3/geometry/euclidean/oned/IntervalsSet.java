package org.apache.commons.math3.geometry.euclidean.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.Precision;

public class IntervalsSet extends AbstractRegion implements Iterable {
   private static final double DEFAULT_TOLERANCE = 1.0E-10;

   public IntervalsSet(double tolerance) {
      super(tolerance);
   }

   public IntervalsSet(double lower, double upper, double tolerance) {
      super(buildTree(lower, upper, tolerance), tolerance);
   }

   public IntervalsSet(BSPTree tree, double tolerance) {
      super(tree, tolerance);
   }

   public IntervalsSet(Collection boundary, double tolerance) {
      super(boundary, tolerance);
   }

   /** @deprecated */
   @Deprecated
   public IntervalsSet() {
      this(1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public IntervalsSet(double lower, double upper) {
      this(lower, upper, 1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public IntervalsSet(BSPTree tree) {
      this(tree, 1.0E-10);
   }

   /** @deprecated */
   @Deprecated
   public IntervalsSet(Collection boundary) {
      this(boundary, 1.0E-10);
   }

   private static BSPTree buildTree(double lower, double upper, double tolerance) {
      if (Double.isInfinite(lower) && lower < (double)0.0F) {
         if (Double.isInfinite(upper) && upper > (double)0.0F) {
            return new BSPTree(Boolean.TRUE);
         } else {
            SubHyperplane<Euclidean1D> upperCut = (new OrientedPoint(new Vector1D(upper), true, tolerance)).wholeHyperplane();
            return new BSPTree(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object)null);
         }
      } else {
         SubHyperplane<Euclidean1D> lowerCut = (new OrientedPoint(new Vector1D(lower), false, tolerance)).wholeHyperplane();
         if (Double.isInfinite(upper) && upper > (double)0.0F) {
            return new BSPTree(lowerCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object)null);
         } else {
            SubHyperplane<Euclidean1D> upperCut = (new OrientedPoint(new Vector1D(upper), true, tolerance)).wholeHyperplane();
            return new BSPTree(lowerCut, new BSPTree(Boolean.FALSE), new BSPTree(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object)null), (Object)null);
         }
      }
   }

   public IntervalsSet buildNew(BSPTree tree) {
      return new IntervalsSet(tree, this.getTolerance());
   }

   protected void computeGeometricalProperties() {
      if (this.getTree(false).getCut() == null) {
         this.setBarycenter(Vector1D.NaN);
         this.setSize((Boolean)this.getTree(false).getAttribute() ? Double.POSITIVE_INFINITY : (double)0.0F);
      } else {
         double size = (double)0.0F;
         double sum = (double)0.0F;

         for(Interval interval : this.asList()) {
            size += interval.getSize();
            sum += interval.getSize() * interval.getBarycenter();
         }

         this.setSize(size);
         if (Double.isInfinite(size)) {
            this.setBarycenter(Vector1D.NaN);
         } else if (size >= Precision.SAFE_MIN) {
            this.setBarycenter(new Vector1D(sum / size));
         } else {
            this.setBarycenter(((OrientedPoint)this.getTree(false).getCut().getHyperplane()).getLocation());
         }
      }

   }

   public double getInf() {
      BSPTree<Euclidean1D> node = this.getTree(false);

      double inf;
      OrientedPoint op;
      for(inf = Double.POSITIVE_INFINITY; node.getCut() != null; node = op.isDirect() ? node.getMinus() : node.getPlus()) {
         op = (OrientedPoint)node.getCut().getHyperplane();
         inf = op.getLocation().getX();
      }

      return (Boolean)node.getAttribute() ? Double.NEGATIVE_INFINITY : inf;
   }

   public double getSup() {
      BSPTree<Euclidean1D> node = this.getTree(false);

      double sup;
      OrientedPoint op;
      for(sup = Double.NEGATIVE_INFINITY; node.getCut() != null; node = op.isDirect() ? node.getPlus() : node.getMinus()) {
         op = (OrientedPoint)node.getCut().getHyperplane();
         sup = op.getLocation().getX();
      }

      return (Boolean)node.getAttribute() ? Double.POSITIVE_INFINITY : sup;
   }

   public BoundaryProjection projectToBoundary(Point point) {
      double x = ((Vector1D)point).getX();
      double previous = Double.NEGATIVE_INFINITY;

      for(double[] a : this) {
         if (x < a[0]) {
            double previousOffset = x - previous;
            double currentOffset = a[0] - x;
            if (previousOffset < currentOffset) {
               return new BoundaryProjection(point, this.finiteOrNullPoint(previous), previousOffset);
            }

            return new BoundaryProjection(point, this.finiteOrNullPoint(a[0]), currentOffset);
         }

         if (x <= a[1]) {
            double offset0 = a[0] - x;
            double offset1 = x - a[1];
            if (offset0 < offset1) {
               return new BoundaryProjection(point, this.finiteOrNullPoint(a[1]), offset1);
            }

            return new BoundaryProjection(point, this.finiteOrNullPoint(a[0]), offset0);
         }

         previous = a[1];
      }

      return new BoundaryProjection(point, this.finiteOrNullPoint(previous), x - previous);
   }

   private Vector1D finiteOrNullPoint(double x) {
      return Double.isInfinite(x) ? null : new Vector1D(x);
   }

   public List asList() {
      List<Interval> list = new ArrayList();

      for(double[] a : this) {
         list.add(new Interval(a[0], a[1]));
      }

      return list;
   }

   private BSPTree getFirstLeaf(BSPTree root) {
      if (root.getCut() == null) {
         return root;
      } else {
         BSPTree<Euclidean1D> smallest = null;

         for(BSPTree<Euclidean1D> n = root; n != null; n = this.previousInternalNode(n)) {
            smallest = n;
         }

         return this.leafBefore(smallest);
      }
   }

   private BSPTree getFirstIntervalBoundary() {
      BSPTree<Euclidean1D> node = this.getTree(false);
      if (node.getCut() == null) {
         return null;
      } else {
         for(node = this.getFirstLeaf(node).getParent(); node != null && !this.isIntervalStart(node) && !this.isIntervalEnd(node); node = this.nextInternalNode(node)) {
         }

         return node;
      }
   }

   private boolean isIntervalStart(BSPTree node) {
      if ((Boolean)this.leafBefore(node).getAttribute()) {
         return false;
      } else {
         return (Boolean)this.leafAfter(node).getAttribute();
      }
   }

   private boolean isIntervalEnd(BSPTree node) {
      if (!(Boolean)this.leafBefore(node).getAttribute()) {
         return false;
      } else {
         return !(Boolean)this.leafAfter(node).getAttribute();
      }
   }

   private BSPTree nextInternalNode(BSPTree node) {
      if (this.childAfter(node).getCut() != null) {
         return this.leafAfter(node).getParent();
      } else {
         while(this.isAfterParent(node)) {
            node = node.getParent();
         }

         return node.getParent();
      }
   }

   private BSPTree previousInternalNode(BSPTree node) {
      if (this.childBefore(node).getCut() != null) {
         return this.leafBefore(node).getParent();
      } else {
         while(this.isBeforeParent(node)) {
            node = node.getParent();
         }

         return node.getParent();
      }
   }

   private BSPTree leafBefore(BSPTree node) {
      for(node = this.childBefore(node); node.getCut() != null; node = this.childAfter(node)) {
      }

      return node;
   }

   private BSPTree leafAfter(BSPTree node) {
      for(node = this.childAfter(node); node.getCut() != null; node = this.childBefore(node)) {
      }

      return node;
   }

   private boolean isBeforeParent(BSPTree node) {
      BSPTree<Euclidean1D> parent = node.getParent();
      if (parent == null) {
         return false;
      } else {
         return node == this.childBefore(parent);
      }
   }

   private boolean isAfterParent(BSPTree node) {
      BSPTree<Euclidean1D> parent = node.getParent();
      if (parent == null) {
         return false;
      } else {
         return node == this.childAfter(parent);
      }
   }

   private BSPTree childBefore(BSPTree node) {
      return this.isDirect(node) ? node.getMinus() : node.getPlus();
   }

   private BSPTree childAfter(BSPTree node) {
      return this.isDirect(node) ? node.getPlus() : node.getMinus();
   }

   private boolean isDirect(BSPTree node) {
      return ((OrientedPoint)node.getCut().getHyperplane()).isDirect();
   }

   private double getAngle(BSPTree node) {
      return ((OrientedPoint)node.getCut().getHyperplane()).getLocation().getX();
   }

   public Iterator iterator() {
      return new SubIntervalsIterator();
   }

   private class SubIntervalsIterator implements Iterator {
      private BSPTree current = IntervalsSet.this.getFirstIntervalBoundary();
      private double[] pending;

      SubIntervalsIterator() {
         if (this.current == null) {
            if ((Boolean)IntervalsSet.this.getFirstLeaf(IntervalsSet.this.getTree(false)).getAttribute()) {
               this.pending = new double[]{Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY};
            } else {
               this.pending = null;
            }
         } else if (IntervalsSet.this.isIntervalEnd(this.current)) {
            this.pending = new double[]{Double.NEGATIVE_INFINITY, IntervalsSet.this.getAngle(this.current)};
         } else {
            this.selectPending();
         }

      }

      private void selectPending() {
         BSPTree<Euclidean1D> start;
         for(start = this.current; start != null && !IntervalsSet.this.isIntervalStart(start); start = IntervalsSet.this.nextInternalNode(start)) {
         }

         if (start == null) {
            this.current = null;
            this.pending = null;
         } else {
            BSPTree<Euclidean1D> end;
            for(end = start; end != null && !IntervalsSet.this.isIntervalEnd(end); end = IntervalsSet.this.nextInternalNode(end)) {
            }

            if (end != null) {
               this.pending = new double[]{IntervalsSet.this.getAngle(start), IntervalsSet.this.getAngle(end)};
               this.current = end;
            } else {
               this.pending = new double[]{IntervalsSet.this.getAngle(start), Double.POSITIVE_INFINITY};
               this.current = null;
            }

         }
      }

      public boolean hasNext() {
         return this.pending != null;
      }

      public double[] next() {
         if (this.pending == null) {
            throw new NoSuchElementException();
         } else {
            double[] next = this.pending;
            this.selectPending();
            return next;
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
