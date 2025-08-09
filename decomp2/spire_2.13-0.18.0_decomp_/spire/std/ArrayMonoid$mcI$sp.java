package spire.std;

import scala.reflect.ClassTag;

public final class ArrayMonoid$mcI$sp extends ArrayMonoid {
   private static final long serialVersionUID = 0L;
   private final ClassTag evidence$34;

   public int[] empty() {
      return this.empty$mcI$sp();
   }

   public int[] empty$mcI$sp() {
      return (int[])this.spire$std$ArrayMonoid$$evidence$34.newArray(0);
   }

   public int[] combine(final int[] x, final int[] y) {
      return this.combine$mcI$sp(x, y);
   }

   public int[] combine$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.concat$mIc$sp(x, y, this.spire$std$ArrayMonoid$$evidence$34);
   }

   public ArrayMonoid$mcI$sp(final ClassTag evidence$34) {
      super(evidence$34);
      this.evidence$34 = evidence$34;
   }
}
