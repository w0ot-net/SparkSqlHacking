package org.apache.commons.math3.geometry.enclosing;

import [Lorg.apache.commons.math3.geometry.Point;;
import java.io.Serializable;
import org.apache.commons.math3.geometry.Point;

public class EnclosingBall implements Serializable {
   private static final long serialVersionUID = 20140126L;
   private final Point center;
   private final double radius;
   private final Point[] support;

   public EnclosingBall(Point center, double radius, Point... support) {
      this.center = center;
      this.radius = radius;
      this.support = (Point[])((Point;)support).clone();
   }

   public Point getCenter() {
      return this.center;
   }

   public double getRadius() {
      return this.radius;
   }

   public Point[] getSupport() {
      return (Point[])this.support.clone();
   }

   public int getSupportSize() {
      return this.support.length;
   }

   public boolean contains(Point point) {
      return point.distance(this.center) <= this.radius;
   }

   public boolean contains(Point point, double margin) {
      return point.distance(this.center) <= this.radius + margin;
   }
}
