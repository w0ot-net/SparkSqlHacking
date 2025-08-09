package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.algebra.CModule$mcI$sp;
import spire.algebra.InnerProductSpace$mcI$sp;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace$mcI$sp;

public final class ArrayInnerProductSpace$mcI$sp extends ArrayInnerProductSpace implements InnerProductSpace$mcI$sp {
   private static final long serialVersionUID = 0L;
   public final Field evidence$32$mcI$sp;
   private final ClassTag evidence$31;

   public NormedVectorSpace normed(final NRoot ev) {
      return InnerProductSpace$mcI$sp.normed$(this, ev);
   }

   public NormedVectorSpace normed$mcI$sp(final NRoot ev) {
      return InnerProductSpace$mcI$sp.normed$mcI$sp$(this, ev);
   }

   public Object divr(final Object v, final int f) {
      return VectorSpace$mcI$sp.divr$(this, v, f);
   }

   public Object divr$mcI$sp(final Object v, final int f) {
      return VectorSpace$mcI$sp.divr$mcI$sp$(this, v, f);
   }

   public Object timesr(final Object v, final int r) {
      return CModule$mcI$sp.timesr$(this, v, r);
   }

   public Object timesr$mcI$sp(final Object v, final int r) {
      return CModule$mcI$sp.timesr$mcI$sp$(this, v, r);
   }

   public Field scalar() {
      return this.scalar$mcI$sp();
   }

   public Field scalar$mcI$sp() {
      return spire.algebra.package$.MODULE$.Field().apply(this.evidence$32$mcI$sp);
   }

   public int[] zero() {
      return this.zero$mcI$sp();
   }

   public int[] zero$mcI$sp() {
      return (int[])this.spire$std$ArrayInnerProductSpace$$evidence$31.newArray(0);
   }

   public int[] negate(final int[] x) {
      return this.negate$mcI$sp(x);
   }

   public int[] negate$mcI$sp(final int[] x) {
      return ArraySupport$.MODULE$.negate$mIc$sp(x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcI$sp);
   }

   public int[] plus(final int[] x, final int[] y) {
      return this.plus$mcI$sp(x, y);
   }

   public int[] plus$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.plus$mIc$sp(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcI$sp);
   }

   public int[] minus(final int[] x, final int[] y) {
      return this.minus$mcI$sp(x, y);
   }

   public int[] minus$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.minus$mIc$sp(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcI$sp);
   }

   public int[] timesl(final int r, final int[] x) {
      return this.timesl$mcI$sp(r, x);
   }

   public int[] timesl$mcI$sp(final int r, final int[] x) {
      return ArraySupport$.MODULE$.timesl$mIc$sp(r, x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcI$sp);
   }

   public int dot(final int[] x, final int[] y) {
      return this.dot$mcI$sp(x, y);
   }

   public int dot$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.dot$mIc$sp(x, y, this.evidence$32$mcI$sp);
   }

   public ArrayInnerProductSpace$mcI$sp(final ClassTag evidence$31, final Field evidence$32$mcI$sp) {
      super(evidence$31, evidence$32$mcI$sp);
      this.evidence$32$mcI$sp = evidence$32$mcI$sp;
      this.evidence$31 = evidence$31;
   }
}
