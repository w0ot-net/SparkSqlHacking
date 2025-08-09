package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.math.CoordinateField;
import breeze.math.MutableFiniteCoordinateField;
import scala.;

public final class OptimizationPackage$ implements OptimizationPackageLowPriority {
   public static final OptimizationPackage$ MODULE$ = new OptimizationPackage$();

   static {
      OptimizationPackageLowPriority2.$init$(MODULE$);
      OptimizationPackageLowPriority.$init$(MODULE$);
   }

   public OptimizationPackageLowPriority.LBFGSMinimizationPackage lbfgsMinimizationPackage(final MutableFiniteCoordinateField space, final .less.colon.less df) {
      return OptimizationPackageLowPriority.lbfgsMinimizationPackage$(this, space, df);
   }

   public OptimizationPackageLowPriority2.ImmutableFirstOrderOptimizationPackage imFirstOrderPackage(final CoordinateField space, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap, final .less.colon.less df) {
      return OptimizationPackageLowPriority2.imFirstOrderPackage$(this, space, canIterate, canMap, canZipMap, df);
   }

   public OptimizationPackage.SecondOrderOptimizationPackage secondOrderPackage(final MutableFiniteCoordinateField space, final UFunc.UImpl2 mult) {
      return new OptimizationPackage.SecondOrderOptimizationPackage(space, mult);
   }

   public OptimizationPackage.FirstOrderStochasticOptimizationPackage firstOrderStochasticPackage(final MutableFiniteCoordinateField space) {
      return new OptimizationPackage.FirstOrderStochasticOptimizationPackage(space);
   }

   public OptimizationPackage.FirstOrderBatchOptimizationPackage firstOrderBatchPackage(final MutableFiniteCoordinateField space) {
      return new OptimizationPackage.FirstOrderBatchOptimizationPackage(space);
   }

   private OptimizationPackage$() {
   }
}
