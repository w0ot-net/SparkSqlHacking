package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;

public class BoundaryProjection {
   private final Point original;
   private final Point projected;
   private final double offset;

   public BoundaryProjection(Point original, Point projected, double offset) {
      this.original = original;
      this.projected = projected;
      this.offset = offset;
   }

   public Point getOriginal() {
      return this.original;
   }

   public Point getProjected() {
      return this.projected;
   }

   public double getOffset() {
      return this.offset;
   }
}
