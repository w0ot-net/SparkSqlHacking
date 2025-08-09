package spire.std;

import scala.reflect.ClassTag;

public final class ArrayMonoid$mcD$sp extends ArrayMonoid {
   private static final long serialVersionUID = 0L;
   private final ClassTag evidence$34;

   public double[] empty() {
      return this.empty$mcD$sp();
   }

   public double[] empty$mcD$sp() {
      return (double[])this.spire$std$ArrayMonoid$$evidence$34.newArray(0);
   }

   public double[] combine(final double[] x, final double[] y) {
      return this.combine$mcD$sp(x, y);
   }

   public double[] combine$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.concat$mDc$sp(x, y, this.spire$std$ArrayMonoid$$evidence$34);
   }

   public ArrayMonoid$mcD$sp(final ClassTag evidence$34) {
      super(evidence$34);
      this.evidence$34 = evidence$34;
   }
}
