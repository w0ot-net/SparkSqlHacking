package spire.std;

import scala.reflect.ClassTag;

public final class ArrayMonoid$mcJ$sp extends ArrayMonoid {
   private static final long serialVersionUID = 0L;
   private final ClassTag evidence$34;

   public long[] empty() {
      return this.empty$mcJ$sp();
   }

   public long[] empty$mcJ$sp() {
      return (long[])this.spire$std$ArrayMonoid$$evidence$34.newArray(0);
   }

   public long[] combine(final long[] x, final long[] y) {
      return this.combine$mcJ$sp(x, y);
   }

   public long[] combine$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.concat$mJc$sp(x, y, this.spire$std$ArrayMonoid$$evidence$34);
   }

   public ArrayMonoid$mcJ$sp(final ClassTag evidence$34) {
      super(evidence$34);
      this.evidence$34 = evidence$34;
   }
}
