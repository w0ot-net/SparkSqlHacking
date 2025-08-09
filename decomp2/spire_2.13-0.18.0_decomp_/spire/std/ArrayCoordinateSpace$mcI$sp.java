package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;

public class ArrayCoordinateSpace$mcI$sp extends ArrayCoordinateSpace {
   private static final long serialVersionUID = 0L;
   public final Field scalar$mcI$sp;
   private final ClassTag evidence$35;

   public Field scalar$mcI$sp() {
      return this.scalar$mcI$sp;
   }

   public Field scalar() {
      return this.scalar$mcI$sp();
   }

   public int[] zero() {
      return this.zero$mcI$sp();
   }

   public int[] zero$mcI$sp() {
      return (int[])this.spire$std$ArrayCoordinateSpace$$evidence$35.newArray(0);
   }

   public int[] negate(final int[] x) {
      return this.negate$mcI$sp(x);
   }

   public int[] negate$mcI$sp(final int[] x) {
      return ArraySupport$.MODULE$.negate$mIc$sp(x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public int[] plus(final int[] x, final int[] y) {
      return this.plus$mcI$sp(x, y);
   }

   public int[] plus$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.plus$mIc$sp(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public int[] minus(final int[] x, final int[] y) {
      return this.minus$mcI$sp(x, y);
   }

   public int[] minus$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.minus$mIc$sp(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public int[] timesl(final int r, final int[] x) {
      return this.timesl$mcI$sp(r, x);
   }

   public int[] timesl$mcI$sp(final int r, final int[] x) {
      return ArraySupport$.MODULE$.timesl$mIc$sp(r, x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public int dot(final int[] x, final int[] y) {
      return this.dot$mcI$sp(x, y);
   }

   public int dot$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.dot$mIc$sp(x, y, this.scalar());
   }

   public int coord(final int[] v, final int i) {
      return this.coord$mcI$sp(v, i);
   }

   public int coord$mcI$sp(final int[] v, final int i) {
      return v[i];
   }

   public int[] axis(final int i) {
      return this.axis$mcI$sp(i);
   }

   public int[] axis$mcI$sp(final int i) {
      return (int[])ArraySupport$.MODULE$.axis(this.dimensions(), i, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public boolean specInstance$() {
      return true;
   }

   public ArrayCoordinateSpace$mcI$sp(final int dimensions, final ClassTag evidence$35, final Field scalar$mcI$sp) {
      super(dimensions, evidence$35, (Field)null);
      this.scalar$mcI$sp = scalar$mcI$sp;
      this.evidence$35 = evidence$35;
   }
}
