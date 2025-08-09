package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;

public class SubLine extends AbstractSubHyperplane {
   private static final double DEFAULT_TOLERANCE = 1.0E-10;

   public SubLine(Hyperplane hyperplane, Region remainingRegion) {
      super(hyperplane, remainingRegion);
   }

   public SubLine(Vector2D start, Vector2D end, double tolerance) {
      super(new Line(start, end, tolerance), buildIntervalSet(start, end, tolerance));
   }

   /** @deprecated */
   @Deprecated
   public SubLine(Vector2D start, Vector2D end) {
      this(start, end, 1.0E-10);
   }

   public SubLine(Segment segment) {
      super(segment.getLine(), buildIntervalSet(segment.getStart(), segment.getEnd(), segment.getLine().getTolerance()));
   }

   public List getSegments() {
      Line line = (Line)this.getHyperplane();
      List<Interval> list = ((IntervalsSet)this.getRemainingRegion()).asList();
      List<Segment> segments = new ArrayList(list.size());

      for(Interval interval : list) {
         Vector2D start = line.toSpace((Point)(new Vector1D(interval.getInf())));
         Vector2D end = line.toSpace((Point)(new Vector1D(interval.getSup())));
         segments.add(new Segment(start, end, line));
      }

      return segments;
   }

   public Vector2D intersection(SubLine subLine, boolean includeEndPoints) {
      Line line1 = (Line)this.getHyperplane();
      Line line2 = (Line)subLine.getHyperplane();
      Vector2D v2D = line1.intersection(line2);
      if (v2D == null) {
         return null;
      } else {
         Region.Location loc1 = this.getRemainingRegion().checkPoint(line1.toSubSpace((Point)v2D));
         Region.Location loc2 = subLine.getRemainingRegion().checkPoint(line2.toSubSpace((Point)v2D));
         if (includeEndPoints) {
            return loc1 != Region.Location.OUTSIDE && loc2 != Region.Location.OUTSIDE ? v2D : null;
         } else {
            return loc1 == Region.Location.INSIDE && loc2 == Region.Location.INSIDE ? v2D : null;
         }
      }
   }

   private static IntervalsSet buildIntervalSet(Vector2D start, Vector2D end, double tolerance) {
      Line line = new Line(start, end, tolerance);
      return new IntervalsSet(line.toSubSpace((Point)start).getX(), line.toSubSpace((Point)end).getX(), tolerance);
   }

   protected AbstractSubHyperplane buildNew(Hyperplane hyperplane, Region remainingRegion) {
      return new SubLine(hyperplane, remainingRegion);
   }

   public SubHyperplane.SplitSubHyperplane split(Hyperplane hyperplane) {
      Line thisLine = (Line)this.getHyperplane();
      Line otherLine = (Line)hyperplane;
      Vector2D crossing = thisLine.intersection(otherLine);
      double tolerance = thisLine.getTolerance();
      if (crossing == null) {
         double global = otherLine.getOffset(thisLine);
         if (global < -tolerance) {
            return new SubHyperplane.SplitSubHyperplane((SubHyperplane)null, this);
         } else {
            return global > tolerance ? new SubHyperplane.SplitSubHyperplane(this, (SubHyperplane)null) : new SubHyperplane.SplitSubHyperplane((SubHyperplane)null, (SubHyperplane)null);
         }
      } else {
         boolean direct = FastMath.sin(thisLine.getAngle() - otherLine.getAngle()) < (double)0.0F;
         Vector1D x = thisLine.toSubSpace((Point)crossing);
         SubHyperplane<Euclidean1D> subPlus = (new OrientedPoint(x, !direct, tolerance)).wholeHyperplane();
         SubHyperplane<Euclidean1D> subMinus = (new OrientedPoint(x, direct, tolerance)).wholeHyperplane();
         BSPTree<Euclidean1D> splitTree = this.getRemainingRegion().getTree(false).split(subMinus);
         BSPTree<Euclidean1D> plusTree = this.getRemainingRegion().isEmpty(splitTree.getPlus()) ? new BSPTree(Boolean.FALSE) : new BSPTree(subPlus, new BSPTree(Boolean.FALSE), splitTree.getPlus(), (Object)null);
         BSPTree<Euclidean1D> minusTree = this.getRemainingRegion().isEmpty(splitTree.getMinus()) ? new BSPTree(Boolean.FALSE) : new BSPTree(subMinus, new BSPTree(Boolean.FALSE), splitTree.getMinus(), (Object)null);
         return new SubHyperplane.SplitSubHyperplane(new SubLine(thisLine.copySelf(), new IntervalsSet(plusTree, tolerance)), new SubLine(thisLine.copySelf(), new IntervalsSet(minusTree, tolerance)));
      }
   }
}
