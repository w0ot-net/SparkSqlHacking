package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.algebra.CModule$mcJ$sp;
import spire.algebra.InnerProductSpace$mcJ$sp;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace$mcJ$sp;

public final class ArrayInnerProductSpace$mcJ$sp extends ArrayInnerProductSpace implements InnerProductSpace$mcJ$sp {
   private static final long serialVersionUID = 0L;
   public final Field evidence$32$mcJ$sp;
   private final ClassTag evidence$31;

   public NormedVectorSpace normed(final NRoot ev) {
      return InnerProductSpace$mcJ$sp.normed$(this, ev);
   }

   public NormedVectorSpace normed$mcJ$sp(final NRoot ev) {
      return InnerProductSpace$mcJ$sp.normed$mcJ$sp$(this, ev);
   }

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
      return spire.algebra.package$.MODULE$.Field().apply(this.evidence$32$mcJ$sp);
   }

   public long[] zero() {
      return this.zero$mcJ$sp();
   }

   public long[] zero$mcJ$sp() {
      return (long[])this.spire$std$ArrayInnerProductSpace$$evidence$31.newArray(0);
   }

   public long[] negate(final long[] x) {
      return this.negate$mcJ$sp(x);
   }

   public long[] negate$mcJ$sp(final long[] x) {
      return ArraySupport$.MODULE$.negate$mJc$sp(x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcJ$sp);
   }

   public long[] plus(final long[] x, final long[] y) {
      return this.plus$mcJ$sp(x, y);
   }

   public long[] plus$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.plus$mJc$sp(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcJ$sp);
   }

   public long[] minus(final long[] x, final long[] y) {
      return this.minus$mcJ$sp(x, y);
   }

   public long[] minus$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.minus$mJc$sp(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcJ$sp);
   }

   public long[] timesl(final long r, final long[] x) {
      return this.timesl$mcJ$sp(r, x);
   }

   public long[] timesl$mcJ$sp(final long r, final long[] x) {
      return ArraySupport$.MODULE$.timesl$mJc$sp(r, x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcJ$sp);
   }

   public long dot(final long[] x, final long[] y) {
      return this.dot$mcJ$sp(x, y);
   }

   public long dot$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.dot$mJc$sp(x, y, this.evidence$32$mcJ$sp);
   }

   public ArrayInnerProductSpace$mcJ$sp(final ClassTag evidence$31, final Field evidence$32$mcJ$sp) {
      super(evidence$31, evidence$32$mcJ$sp);
      this.evidence$32$mcJ$sp = evidence$32$mcJ$sp;
      this.evidence$31 = evidence$31;
   }
}
