package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class StableRandomGenerator implements NormalizedRandomGenerator {
   private final RandomGenerator generator;
   private final double alpha;
   private final double beta;
   private final double zeta;

   public StableRandomGenerator(RandomGenerator generator, double alpha, double beta) throws NullArgumentException, OutOfRangeException {
      if (generator == null) {
         throw new NullArgumentException();
      } else if (alpha > (double)0.0F && alpha <= (double)2.0F) {
         if (beta >= (double)-1.0F && beta <= (double)1.0F) {
            this.generator = generator;
            this.alpha = alpha;
            this.beta = beta;
            if (alpha < (double)2.0F && beta != (double)0.0F) {
               this.zeta = beta * FastMath.tan(Math.PI * alpha / (double)2.0F);
            } else {
               this.zeta = (double)0.0F;
            }

         } else {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, beta, -1, 1);
         }
      } else {
         throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, alpha, 0, 2);
      }
   }

   public double nextNormalizedDouble() {
      double omega = -FastMath.log(this.generator.nextDouble());
      double phi = Math.PI * (this.generator.nextDouble() - (double)0.5F);
      if (this.alpha == (double)2.0F) {
         return FastMath.sqrt((double)2.0F * omega) * FastMath.sin(phi);
      } else {
         double x;
         if (this.beta == (double)0.0F) {
            if (this.alpha == (double)1.0F) {
               x = FastMath.tan(phi);
            } else {
               x = FastMath.pow(omega * FastMath.cos(((double)1.0F - this.alpha) * phi), (double)1.0F / this.alpha - (double)1.0F) * FastMath.sin(this.alpha * phi) / FastMath.pow(FastMath.cos(phi), (double)1.0F / this.alpha);
            }
         } else {
            double cosPhi = FastMath.cos(phi);
            if (FastMath.abs(this.alpha - (double)1.0F) > 1.0E-8) {
               double alphaPhi = this.alpha * phi;
               double invAlphaPhi = phi - alphaPhi;
               x = (FastMath.sin(alphaPhi) + this.zeta * FastMath.cos(alphaPhi)) / cosPhi * (FastMath.cos(invAlphaPhi) + this.zeta * FastMath.sin(invAlphaPhi)) / FastMath.pow(omega * cosPhi, ((double)1.0F - this.alpha) / this.alpha);
            } else {
               double betaPhi = (Math.PI / 2D) + this.beta * phi;
               x = 0.6366197723675814 * (betaPhi * FastMath.tan(phi) - this.beta * FastMath.log((Math.PI / 2D) * omega * cosPhi / betaPhi));
               if (this.alpha != (double)1.0F) {
                  x += this.beta * FastMath.tan(Math.PI * this.alpha / (double)2.0F);
               }
            }
         }

         return x;
      }
   }
}
