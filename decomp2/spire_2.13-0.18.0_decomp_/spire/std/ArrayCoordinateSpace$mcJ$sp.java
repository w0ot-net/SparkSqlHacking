package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;

public class ArrayCoordinateSpace$mcJ$sp extends ArrayCoordinateSpace {
   private static final long serialVersionUID = 0L;
   public final Field scalar$mcJ$sp;
   private final ClassTag evidence$35;

   public Field scalar$mcJ$sp() {
      return this.scalar$mcJ$sp;
   }

   public Field scalar() {
      return this.scalar$mcJ$sp();
   }

   public long[] zero() {
      return this.zero$mcJ$sp();
   }

   public long[] zero$mcJ$sp() {
      return (long[])this.spire$std$ArrayCoordinateSpace$$evidence$35.newArray(0);
   }

   public long[] negate(final long[] x) {
      return this.negate$mcJ$sp(x);
   }

   public long[] negate$mcJ$sp(final long[] x) {
      return ArraySupport$.MODULE$.negate$mJc$sp(x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public long[] plus(final long[] x, final long[] y) {
      return this.plus$mcJ$sp(x, y);
   }

   public long[] plus$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.plus$mJc$sp(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public long[] minus(final long[] x, final long[] y) {
      return this.minus$mcJ$sp(x, y);
   }

   public long[] minus$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.minus$mJc$sp(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public long[] timesl(final long r, final long[] x) {
      return this.timesl$mcJ$sp(r, x);
   }

   public long[] timesl$mcJ$sp(final long r, final long[] x) {
      return ArraySupport$.MODULE$.timesl$mJc$sp(r, x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public long dot(final long[] x, final long[] y) {
      return this.dot$mcJ$sp(x, y);
   }

   public long dot$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.dot$mJc$sp(x, y, this.scalar());
   }

   public long coord(final long[] v, final int i) {
      return this.coord$mcJ$sp(v, i);
   }

   public long coord$mcJ$sp(final long[] v, final int i) {
      return v[i];
   }

   public long[] axis(final int i) {
      return this.axis$mcJ$sp(i);
   }

   public long[] axis$mcJ$sp(final int i) {
      return (long[])ArraySupport$.MODULE$.axis(this.dimensions(), i, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public boolean specInstance$() {
      return true;
   }

   public ArrayCoordinateSpace$mcJ$sp(final int dimensions, final ClassTag evidence$35, final Field scalar$mcJ$sp) {
      super(dimensions, evidence$35, (Field)null);
      this.scalar$mcJ$sp = scalar$mcJ$sp;
      this.evidence$35 = evidence$35;
   }
}
