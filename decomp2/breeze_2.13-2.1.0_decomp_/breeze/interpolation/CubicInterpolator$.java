package breeze.interpolation;

import breeze.linalg.Vector;

public final class CubicInterpolator$ {
   public static final CubicInterpolator$ MODULE$ = new CubicInterpolator$();

   public CubicInterpolator apply(final Vector x_coords, final Vector y_coords) {
      return new CubicInterpolator(x_coords, y_coords);
   }

   private CubicInterpolator$() {
   }
}
