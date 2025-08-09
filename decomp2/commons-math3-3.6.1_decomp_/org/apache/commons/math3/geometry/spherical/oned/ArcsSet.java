package org.apache.commons.math3.geometry.spherical.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class ArcsSet extends AbstractRegion implements Iterable {
   public ArcsSet(double tolerance) {
      super(tolerance);
   }

   public ArcsSet(double lower, double upper, double tolerance) throws NumberIsTooLargeException {
      super(buildTree(lower, upper, tolerance), tolerance);
   }

   public ArcsSet(BSPTree tree, double tolerance) throws InconsistentStateAt2PiWrapping {
      super(tree, tolerance);
      this.check2PiConsistency();
   }

   public ArcsSet(Collection boundary, double tolerance) throws InconsistentStateAt2PiWrapping {
      super(boundary, tolerance);
      this.check2PiConsistency();
   }

   private static BSPTree buildTree(double lower, double upper, double tolerance) throws NumberIsTooLargeException {
      if (!Precision.equals(lower, upper, 0) && !(upper - lower >= (Math.PI * 2D))) {
         if (lower > upper) {
            throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, lower, upper, true);
         } else {
            double normalizedLower = MathUtils.normalizeAngle(lower, Math.PI);
            double normalizedUpper = normalizedLower + (upper - lower);
            SubHyperplane<Sphere1D> lowerCut = (new LimitAngle(new S1Point(normalizedLower), false, tolerance)).wholeHyperplane();
            if (normalizedUpper <= (Math.PI * 2D)) {
               SubHyperplane<Sphere1D> upperCut = (new LimitAngle(new S1Point(normalizedUpper), true, tolerance)).wholeHyperplane();
               return new BSPTree(lowerCut, new BSPTree(Boolean.FALSE), new BSPTree(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object)null), (Object)null);
            } else {
               SubHyperplane<Sphere1D> upperCut = (new LimitAngle(new S1Point(normalizedUpper - (Math.PI * 2D)), true, tolerance)).wholeHyperplane();
               return new BSPTree(lowerCut, new BSPTree(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), (Object)null), new BSPTree(Boolean.TRUE), (Object)null);
            }
         }
      } else {
         return new BSPTree(Boolean.TRUE);
      }
   }

   private void check2PiConsistency() throws InconsistentStateAt2PiWrapping {
      BSPTree<Sphere1D> root = this.getTree(false);
      if (root.getCut() != null) {
         Boolean stateBefore = (Boolean)this.getFirstLeaf(root).getAttribute();
         Boolean stateAfter = (Boolean)this.getLastLeaf(root).getAttribute();
         if (stateBefore ^ stateAfter) {
            throw new InconsistentStateAt2PiWrapping();
         }
      }
   }

   private BSPTree getFirstLeaf(BSPTree root) {
      if (root.getCut() == null) {
         return root;
      } else {
         BSPTree<Sphere1D> smallest = null;

         for(BSPTree<Sphere1D> n = root; n != null; n = this.previousInternalNode(n)) {
            smallest = n;
         }

         return this.leafBefore(smallest);
      }
   }

   private BSPTree getLastLeaf(BSPTree root) {
      if (root.getCut() == null) {
         return root;
      } else {
         BSPTree<Sphere1D> largest = null;

         for(BSPTree<Sphere1D> n = root; n != null; n = this.nextInternalNode(n)) {
            largest = n;
         }

         return this.leafAfter(largest);
      }
   }

   private BSPTree getFirstArcStart() {
      BSPTree<Sphere1D> node = this.getTree(false);
      if (node.getCut() == null) {
         return null;
      } else {
         for(node = this.getFirstLeaf(node).getParent(); node != null && !this.isArcStart(node); node = this.nextInternalNode(node)) {
         }

         return node;
      }
   }

   private boolean isArcStart(BSPTree node) {
      if ((Boolean)this.leafBefore(node).getAttribute()) {
         return false;
      } else {
         return (Boolean)this.leafAfter(node).getAttribute();
      }
   }

   private boolean isArcEnd(BSPTree node) {
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
      BSPTree<Sphere1D> parent = node.getParent();
      if (parent == null) {
         return false;
      } else {
         return node == this.childBefore(parent);
      }
   }

   private boolean isAfterParent(BSPTree node) {
      BSPTree<Sphere1D> parent = node.getParent();
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
      return ((LimitAngle)node.getCut().getHyperplane()).isDirect();
   }

   private double getAngle(BSPTree node) {
      return ((LimitAngle)node.getCut().getHyperplane()).getLocation().getAlpha();
   }

   public ArcsSet buildNew(BSPTree tree) {
      return new ArcsSet(tree, this.getTolerance());
   }

   protected void computeGeometricalProperties() {
      if (this.getTree(false).getCut() == null) {
         this.setBarycenter(S1Point.NaN);
         this.setSize((Boolean)this.getTree(false).getAttribute() ? (Math.PI * 2D) : (double)0.0F);
      } else {
         double size = (double)0.0F;
         double sum = (double)0.0F;

         for(double[] a : this) {
            double length = a[1] - a[0];
            size += length;
            sum += length * (a[0] + a[1]);
         }

         this.setSize(size);
         if (Precision.equals(size, (Math.PI * 2D), 0)) {
            this.setBarycenter(S1Point.NaN);
         } else if (size >= Precision.SAFE_MIN) {
            this.setBarycenter(new S1Point(sum / ((double)2.0F * size)));
         } else {
            LimitAngle limit = (LimitAngle)this.getTree(false).getCut().getHyperplane();
            this.setBarycenter(limit.getLocation());
         }
      }

   }

   public BoundaryProjection projectToBoundary(Point point) {
      double alpha = ((S1Point)point).getAlpha();
      boolean wrapFirst = false;
      double first = Double.NaN;
      double previous = Double.NaN;

      for(double[] a : this) {
         if (Double.isNaN(first)) {
            first = a[0];
         }

         if (!wrapFirst) {
            if (alpha < a[0]) {
               if (!Double.isNaN(previous)) {
                  double previousOffset = alpha - previous;
                  double currentOffset = a[0] - alpha;
                  if (previousOffset < currentOffset) {
                     return new BoundaryProjection(point, new S1Point(previous), previousOffset);
                  }

                  return new BoundaryProjection(point, new S1Point(a[0]), currentOffset);
               }

               wrapFirst = true;
            } else if (alpha <= a[1]) {
               double offset0 = a[0] - alpha;
               double offset1 = alpha - a[1];
               if (offset0 < offset1) {
                  return new BoundaryProjection(point, new S1Point(a[1]), offset1);
               }

               return new BoundaryProjection(point, new S1Point(a[0]), offset0);
            }
         }

         previous = a[1];
      }

      if (Double.isNaN(previous)) {
         return new BoundaryProjection(point, (Point)null, (Math.PI * 2D));
      } else if (wrapFirst) {
         double previousOffset = alpha - (previous - (Math.PI * 2D));
         double currentOffset = first - alpha;
         if (previousOffset < currentOffset) {
            return new BoundaryProjection(point, new S1Point(previous), previousOffset);
         } else {
            return new BoundaryProjection(point, new S1Point(first), currentOffset);
         }
      } else {
         double previousOffset = alpha - previous;
         double currentOffset = first + (Math.PI * 2D) - alpha;
         if (previousOffset < currentOffset) {
            return new BoundaryProjection(point, new S1Point(previous), previousOffset);
         } else {
            return new BoundaryProjection(point, new S1Point(first), currentOffset);
         }
      }
   }

   public List asList() {
      List<Arc> list = new ArrayList();

      for(double[] a : this) {
         list.add(new Arc(a[0], a[1], this.getTolerance()));
      }

      return list;
   }

   public Iterator iterator() {
      return new SubArcsIterator();
   }

   /** @deprecated */
   @Deprecated
   public Side side(Arc arc) {
      return this.split(arc).getSide();
   }

   public Split split(Arc arc) {
      List<Double> minus = new ArrayList();
      List<Double> plus = new ArrayList();
      double reference = Math.PI + arc.getInf();
      double arcLength = arc.getSup() - arc.getInf();

      for(double[] a : this) {
         double syncedStart = MathUtils.normalizeAngle(a[0], reference) - arc.getInf();
         double arcOffset = a[0] - syncedStart;
         double syncedEnd = a[1] - arcOffset;
         if (syncedStart < arcLength) {
            minus.add(a[0]);
            if (syncedEnd > arcLength) {
               double minusToPlus = arcLength + arcOffset;
               minus.add(minusToPlus);
               plus.add(minusToPlus);
               if (syncedEnd > (Math.PI * 2D)) {
                  double plusToMinus = (Math.PI * 2D) + arcOffset;
                  plus.add(plusToMinus);
                  minus.add(plusToMinus);
                  minus.add(a[1]);
               } else {
                  plus.add(a[1]);
               }
            } else {
               minus.add(a[1]);
            }
         } else {
            plus.add(a[0]);
            if (syncedEnd > (Math.PI * 2D)) {
               double plusToMinus = (Math.PI * 2D) + arcOffset;
               plus.add(plusToMinus);
               minus.add(plusToMinus);
               if (syncedEnd > (Math.PI * 2D) + arcLength) {
                  double minusToPlus = (Math.PI * 2D) + arcLength + arcOffset;
                  minus.add(minusToPlus);
                  plus.add(minusToPlus);
                  plus.add(a[1]);
               } else {
                  minus.add(a[1]);
               }
            } else {
               plus.add(a[1]);
            }
         }
      }

      return new Split(this.createSplitPart(plus), this.createSplitPart(minus));
   }

   private void addArcLimit(BSPTree tree, double alpha, boolean isStart) {
      LimitAngle limit = new LimitAngle(new S1Point(alpha), !isStart, this.getTolerance());
      BSPTree<Sphere1D> node = tree.getCell(limit.getLocation(), this.getTolerance());
      if (node.getCut() != null) {
         throw new MathInternalError();
      } else {
         node.insertCut(limit);
         node.setAttribute((Object)null);
         node.getPlus().setAttribute(Boolean.FALSE);
         node.getMinus().setAttribute(Boolean.TRUE);
      }
   }

   private ArcsSet createSplitPart(List limits) {
      if (limits.isEmpty()) {
         return null;
      } else {
         for(int i = 0; i < limits.size(); ++i) {
            int j = (i + 1) % limits.size();
            double lA = (Double)limits.get(i);
            double lB = MathUtils.normalizeAngle((Double)limits.get(j), lA);
            if (FastMath.abs(lB - lA) <= this.getTolerance()) {
               if (j > 0) {
                  limits.remove(j);
                  limits.remove(i);
                  --i;
               } else {
                  double lEnd = (Double)limits.remove(limits.size() - 1);
                  double lStart = (Double)limits.remove(0);
                  if (limits.isEmpty()) {
                     if (lEnd - lStart > Math.PI) {
                        return new ArcsSet(new BSPTree(Boolean.TRUE), this.getTolerance());
                     }

                     return null;
                  }

                  limits.add((Double)limits.remove(0) + (Math.PI * 2D));
               }
            }
         }

         BSPTree<Sphere1D> tree = new BSPTree(Boolean.FALSE);

         for(int i = 0; i < limits.size() - 1; i += 2) {
            this.addArcLimit(tree, (Double)limits.get(i), true);
            this.addArcLimit(tree, (Double)limits.get(i + 1), false);
         }

         return tree.getCut() == null ? null : new ArcsSet(tree, this.getTolerance());
      }
   }

   private class SubArcsIterator implements Iterator {
      private final BSPTree firstStart = ArcsSet.this.getFirstArcStart();
      private BSPTree current;
      private double[] pending;

      SubArcsIterator() {
         this.current = this.firstStart;
         if (this.firstStart == null) {
            if ((Boolean)ArcsSet.this.getFirstLeaf(ArcsSet.this.getTree(false)).getAttribute()) {
               this.pending = new double[]{(double)0.0F, (Math.PI * 2D)};
            } else {
               this.pending = null;
            }
         } else {
            this.selectPending();
         }

      }

      private void selectPending() {
         BSPTree<Sphere1D> start;
         for(start = this.current; start != null && !ArcsSet.this.isArcStart(start); start = ArcsSet.this.nextInternalNode(start)) {
         }

         if (start == null) {
            this.current = null;
            this.pending = null;
         } else {
            BSPTree<Sphere1D> end;
            for(end = start; end != null && !ArcsSet.this.isArcEnd(end); end = ArcsSet.this.nextInternalNode(end)) {
            }

            if (end != null) {
               this.pending = new double[]{ArcsSet.this.getAngle(start), ArcsSet.this.getAngle(end)};
               this.current = end;
            } else {
               for(end = this.firstStart; end != null && !ArcsSet.this.isArcEnd(end); end = ArcsSet.this.previousInternalNode(end)) {
               }

               if (end == null) {
                  throw new MathInternalError();
               }

               this.pending = new double[]{ArcsSet.this.getAngle(start), ArcsSet.this.getAngle(end) + (Math.PI * 2D)};
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

   public static class Split {
      private final ArcsSet plus;
      private final ArcsSet minus;

      private Split(ArcsSet plus, ArcsSet minus) {
         this.plus = plus;
         this.minus = minus;
      }

      public ArcsSet getPlus() {
         return this.plus;
      }

      public ArcsSet getMinus() {
         return this.minus;
      }

      public Side getSide() {
         if (this.plus != null) {
            return this.minus != null ? Side.BOTH : Side.PLUS;
         } else {
            return this.minus != null ? Side.MINUS : Side.HYPER;
         }
      }
   }

   public static class InconsistentStateAt2PiWrapping extends MathIllegalArgumentException {
      private static final long serialVersionUID = 20140107L;

      public InconsistentStateAt2PiWrapping() {
         super(LocalizedFormats.INCONSISTENT_STATE_AT_2_PI_WRAPPING);
      }
   }
}
