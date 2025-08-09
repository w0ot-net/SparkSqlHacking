package org.apache.commons.math3.geometry.euclidean.twod;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.util.FastMath;

public class Segment {
   private final Vector2D start;
   private final Vector2D end;
   private final Line line;

   public Segment(Vector2D start, Vector2D end, Line line) {
      this.start = start;
      this.end = end;
      this.line = line;
   }

   public Vector2D getStart() {
      return this.start;
   }

   public Vector2D getEnd() {
      return this.end;
   }

   public Line getLine() {
      return this.line;
   }

   public double distance(Vector2D p) {
      double deltaX = this.end.getX() - this.start.getX();
      double deltaY = this.end.getY() - this.start.getY();
      double r = ((p.getX() - this.start.getX()) * deltaX + (p.getY() - this.start.getY()) * deltaY) / (deltaX * deltaX + deltaY * deltaY);
      if (!(r < (double)0.0F) && !(r > (double)1.0F)) {
         double px = this.start.getX() + r * deltaX;
         double py = this.start.getY() + r * deltaY;
         Vector2D interPt = new Vector2D(px, py);
         return interPt.distance((Point)p);
      } else {
         double dist1 = this.getStart().distance((Point)p);
         double dist2 = this.getEnd().distance((Point)p);
         return FastMath.min(dist1, dist2);
      }
   }
}
