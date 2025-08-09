package breeze.util;

import scala.MatchError;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.immutable.Range;
import scala.runtime.BoxesRunTime;

public final class RangeUtils$ {
   public static final RangeUtils$ MODULE$ = new RangeUtils$();

   public boolean overlaps(final Range a, final Range b) {
      boolean var10000;
      if (!a.isEmpty() && !b.isEmpty() && a.start() <= b.end() && b.start() <= a.end()) {
         if (a.step() == 1 && b.step() == 1) {
            var10000 = true;
         } else {
            Tuple3 var5 = this.extendedEuclideanAlgorithm(a.step(), b.step());
            if (var5 == null) {
               throw new MatchError(var5);
            }

            int x0_ = BoxesRunTime.unboxToInt(var5._1());
            int y0_ = BoxesRunTime.unboxToInt(var5._2());
            int gcd = BoxesRunTime.unboxToInt(var5._3());
            Tuple3 var3 = new Tuple3(BoxesRunTime.boxToInteger(x0_), BoxesRunTime.boxToInteger(y0_), BoxesRunTime.boxToInteger(gcd));
            int x0_ = BoxesRunTime.unboxToInt(var3._1());
            int y0_ = BoxesRunTime.unboxToInt(var3._2());
            int gcd = BoxesRunTime.unboxToInt(var3._3());
            .MODULE$.assert(a.step() * x0_ + b.step() * y0_ == gcd);
            int target = b.start() - a.start();
            int q = target / gcd;
            int x0 = x0_ * q;
            int y0 = y0_ * q;
            int s = a.step() / gcd;
            int t = b.step() / gcd;
            .MODULE$.assert(s * x0_ + t * y0_ == 1);
            if (target % gcd != 0) {
               var10000 = false;
            } else {
               .MODULE$.assert(x0 * a.step() + a.start() == -y0 * b.step() + b.start());
               double x0d = (double)x0;
               double y0d = (double)y0;
               double minK = scala.math.package..MODULE$.max(-x0d / (double)t, y0d / (double)s);
               double maxK = scala.math.package..MODULE$.min(((double)(a.length() - 1) - x0d) / (double)t, ((double)(b.length() - 1) + y0d) / (double)s);
               var10000 = scala.math.package..MODULE$.ceil(minK) <= scala.math.package..MODULE$.floor(maxK);
            }
         }
      } else {
         var10000 = false;
      }

      return var10000;
   }

   public Tuple3 extendedEuclideanAlgorithm(final int a, final int b) {
      long s = 0L;
      long old_s = 1L;
      long t = 1L;
      long old_t = 0L;
      long r = (long)b;

      long old_r;
      long temp;
      for(old_r = (long)a; r != 0L; old_t = temp) {
         long quotient = old_r / r;
         temp = r;
         r = old_r % r;
         old_r = temp;
         temp = s;
         s = old_s - quotient * s;
         old_s = temp;
         temp = t;
         t = old_t - quotient * t;
      }

      return new Tuple3(BoxesRunTime.boxToInteger((int)old_s), BoxesRunTime.boxToInteger((int)old_t), BoxesRunTime.boxToInteger((int)old_r));
   }

   private RangeUtils$() {
   }
}
