package spire.std;

import scala.reflect.ClassTag;

public final class ArrayMonoid$mcF$sp extends ArrayMonoid {
   private static final long serialVersionUID = 0L;
   private final ClassTag evidence$34;

   public float[] empty() {
      return this.empty$mcF$sp();
   }

   public float[] empty$mcF$sp() {
      return (float[])this.spire$std$ArrayMonoid$$evidence$34.newArray(0);
   }

   public float[] combine(final float[] x, final float[] y) {
      return this.combine$mcF$sp(x, y);
   }

   public float[] combine$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.concat$mFc$sp(x, y, this.spire$std$ArrayMonoid$$evidence$34);
   }

   public ArrayMonoid$mcF$sp(final ClassTag evidence$34) {
      super(evidence$34);
      this.evidence$34 = evidence$34;
   }
}
