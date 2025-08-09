package org.apache.commons.math3.geometry.enclosing;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Point;

public class WelzlEncloser implements Encloser {
   private final double tolerance;
   private final SupportBallGenerator generator;

   public WelzlEncloser(double tolerance, SupportBallGenerator generator) {
      this.tolerance = tolerance;
      this.generator = generator;
   }

   public EnclosingBall enclose(Iterable points) {
      return points != null && points.iterator().hasNext() ? this.pivotingBall(points) : this.generator.ballOnSupport(new ArrayList());
   }

   private EnclosingBall pivotingBall(Iterable points) {
      P first = (P)((Point)points.iterator().next());
      List<P> extreme = new ArrayList(first.getSpace().getDimension() + 1);
      List<P> support = new ArrayList(first.getSpace().getDimension() + 1);
      extreme.add(first);
      EnclosingBall<S, P> ball = this.moveToFrontBall(extreme, extreme.size(), support);

      while(true) {
         P farthest = (P)this.selectFarthest(points, ball);
         if (ball.contains(farthest, this.tolerance)) {
            return ball;
         }

         support.clear();
         support.add(farthest);
         EnclosingBall<S, P> savedBall = ball;
         ball = this.moveToFrontBall(extreme, extreme.size(), support);
         if (ball.getRadius() < savedBall.getRadius()) {
            throw new MathInternalError();
         }

         extreme.add(0, farthest);
         extreme.subList(ball.getSupportSize(), extreme.size()).clear();
      }
   }

   private EnclosingBall moveToFrontBall(List extreme, int nbExtreme, List support) {
      EnclosingBall<S, P> ball = this.generator.ballOnSupport(support);
      if (ball.getSupportSize() <= ball.getCenter().getSpace().getDimension()) {
         for(int i = 0; i < nbExtreme; ++i) {
            P pi = (P)((Point)extreme.get(i));
            if (!ball.contains(pi, this.tolerance)) {
               support.add(pi);
               ball = this.moveToFrontBall(extreme, i, support);
               support.remove(support.size() - 1);

               for(int j = i; j > 0; --j) {
                  extreme.set(j, extreme.get(j - 1));
               }

               extreme.set(0, pi);
            }
         }
      }

      return ball;
   }

   public Point selectFarthest(Iterable points, EnclosingBall ball) {
      P center = (P)ball.getCenter();
      P farthest = (P)null;
      double dMax = (double)-1.0F;

      for(Point point : points) {
         double d = point.distance(center);
         if (d > dMax) {
            farthest = point;
            dMax = d;
         }
      }

      return farthest;
   }
}
