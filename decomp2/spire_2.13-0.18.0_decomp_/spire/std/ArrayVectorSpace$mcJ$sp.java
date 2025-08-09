package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.NotGiven;
import spire.algebra.CModule$mcJ$sp;
import spire.algebra.VectorSpace$mcJ$sp;

public final class ArrayVectorSpace$mcJ$sp extends ArrayVectorSpace implements VectorSpace$mcJ$sp {
   private static final long serialVersionUID = 0L;
   public final Field evidence$29$mcJ$sp;
   private final ClassTag evidence$28;
   private final NotGiven nnvs;

   public Object divr(final Object v, final long f) {
      return VectorSpace$mcJ$sp.divr$(this, v, f);
   }

   public Object divr$mcJ$sp(final Object v, final long f) {
      return VectorSpace$mcJ$sp.divr$mcJ$sp$(this, v, f);
   }

   public Object timesr(final Object v, final long r) {
      return CModule$mcJ$sp.timesr$(this, v, r);
   }

   public Object timesr$mcJ$sp(final Object v, final long r) {
      return CModule$mcJ$sp.timesr$mcJ$sp$(this, v, r);
   }

   public Field scalar() {
      return this.scalar$mcJ$sp();
   }

   public Field scalar$mcJ$sp() {
      return spire.algebra.package$.MODULE$.Field().apply(this.evidence$29$mcJ$sp);
   }

   public long[] zero() {
      return this.zero$mcJ$sp();
   }

   public long[] zero$mcJ$sp() {
      return (long[])this.spire$std$ArrayVectorSpace$$evidence$28.newArray(0);
   }

   public long[] negate(final long[] x) {
      return this.negate$mcJ$sp(x);
   }

   public long[] negate$mcJ$sp(final long[] x) {
      return ArraySupport$.MODULE$.negate$mJc$sp(x, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcJ$sp);
   }

   public long[] plus(final long[] x, final long[] y) {
      return this.plus$mcJ$sp(x, y);
   }

   public long[] plus$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.plus$mJc$sp(x, y, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcJ$sp);
   }

   public long[] minus(final long[] x, final long[] y) {
      return this.minus$mcJ$sp(x, y);
   }

   public long[] minus$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.minus$mJc$sp(x, y, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcJ$sp);
   }

   public long[] timesl(final long r, final long[] x) {
      return this.timesl$mcJ$sp(r, x);
   }

   public long[] timesl$mcJ$sp(final long r, final long[] x) {
      return ArraySupport$.MODULE$.timesl$mJc$sp(r, x, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcJ$sp);
   }

   public ArrayVectorSpace$mcJ$sp(final ClassTag evidence$28, final Field evidence$29$mcJ$sp, final NotGiven nnvs) {
      super(evidence$28, evidence$29$mcJ$sp, nnvs);
      this.evidence$29$mcJ$sp = evidence$29$mcJ$sp;
      this.evidence$28 = evidence$28;
      this.nnvs = nnvs;
   }
}
