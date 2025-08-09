package breeze.interpolation;

import breeze.linalg.Vector;
import breeze.math.Field;
import scala.math.Ordering;
import scala.reflect.ClassTag;

public final class LinearInterpolator$ {
   public static final LinearInterpolator$ MODULE$ = new LinearInterpolator$();

   public LinearInterpolator apply(final Vector x_coords, final Vector y_coords, final ClassTag evidence$4, final Field evidence$5, final Ordering evidence$6) {
      return new LinearInterpolator(x_coords, y_coords, evidence$4, evidence$5, evidence$6);
   }

   private LinearInterpolator$() {
   }
}
