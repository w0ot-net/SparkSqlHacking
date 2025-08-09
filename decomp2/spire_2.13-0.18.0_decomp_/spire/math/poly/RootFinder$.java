package spire.math.poly;

import java.math.MathContext;
import spire.math.Polynomial;

public final class RootFinder$ {
   public static final RootFinder$ MODULE$ = new RootFinder$();
   private static final RootFinder RealRootFinder = new RootFinder() {
      public Roots findRoots(final Polynomial p) {
         return new FixedRealRoots(p);
      }
   };
   private static final RootFinder NumberRootFinder = new RootFinder() {
      public Roots findRoots(final Polynomial p) {
         return new NumberRoots(p);
      }
   };

   public final RootFinder apply(final RootFinder finder) {
      return finder;
   }

   public RootFinder BigDecimalScaleRootFinder(final int scale) {
      return new RootFinder(scale) {
         private final int scale$1;

         public Roots findRoots(final Polynomial poly) {
            return new BigDecimalSimpleRoots(poly, this.scale$1);
         }

         public {
            this.scale$1 = scale$1;
         }
      };
   }

   public RootFinder BigDecimalMathContextRootFinder(final MathContext mc) {
      return new RootFinder(mc) {
         private final MathContext mc$1;

         public Roots findRoots(final Polynomial poly) {
            return new BigDecimalRelativeRoots(poly, this.mc$1);
         }

         public {
            this.mc$1 = mc$1;
         }
      };
   }

   public RootFinder RealRootFinder() {
      return RealRootFinder;
   }

   public RootFinder NumberRootFinder() {
      return NumberRootFinder;
   }

   private RootFinder$() {
   }
}
