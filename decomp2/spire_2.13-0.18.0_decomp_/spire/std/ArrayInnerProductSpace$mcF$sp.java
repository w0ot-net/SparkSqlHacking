package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.algebra.CModule$mcF$sp;
import spire.algebra.InnerProductSpace$mcF$sp;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace$mcF$sp;

public final class ArrayInnerProductSpace$mcF$sp extends ArrayInnerProductSpace implements InnerProductSpace$mcF$sp {
   private static final long serialVersionUID = 0L;
   public final Field evidence$32$mcF$sp;
   private final ClassTag evidence$31;

   public NormedVectorSpace normed(final NRoot ev) {
      return InnerProductSpace$mcF$sp.normed$(this, ev);
   }

   public NormedVectorSpace normed$mcF$sp(final NRoot ev) {
      return InnerProductSpace$mcF$sp.normed$mcF$sp$(this, ev);
   }

   public Object divr(final Object v, final float f) {
      return VectorSpace$mcF$sp.divr$(this, v, f);
   }

   public Object divr$mcF$sp(final Object v, final float f) {
      return VectorSpace$mcF$sp.divr$mcF$sp$(this, v, f);
   }

   public Object timesr(final Object v, final float r) {
      return CModule$mcF$sp.timesr$(this, v, r);
   }

   public Object timesr$mcF$sp(final Object v, final float r) {
      return CModule$mcF$sp.timesr$mcF$sp$(this, v, r);
   }

   public Field scalar() {
      return this.scalar$mcF$sp();
   }

   public Field scalar$mcF$sp() {
      return spire.algebra.package$.MODULE$.Field().apply(this.evidence$32$mcF$sp);
   }

   public float[] zero() {
      return this.zero$mcF$sp();
   }

   public float[] zero$mcF$sp() {
      return (float[])this.spire$std$ArrayInnerProductSpace$$evidence$31.newArray(0);
   }

   public float[] negate(final float[] x) {
      return this.negate$mcF$sp(x);
   }

   public float[] negate$mcF$sp(final float[] x) {
      return ArraySupport$.MODULE$.negate$mFc$sp(x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcF$sp);
   }

   public float[] plus(final float[] x, final float[] y) {
      return this.plus$mcF$sp(x, y);
   }

   public float[] plus$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.plus$mFc$sp(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcF$sp);
   }

   public float[] minus(final float[] x, final float[] y) {
      return this.minus$mcF$sp(x, y);
   }

   public float[] minus$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.minus$mFc$sp(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcF$sp);
   }

   public float[] timesl(final float r, final float[] x) {
      return this.timesl$mcF$sp(r, x);
   }

   public float[] timesl$mcF$sp(final float r, final float[] x) {
      return ArraySupport$.MODULE$.timesl$mFc$sp(r, x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcF$sp);
   }

   public float dot(final float[] x, final float[] y) {
      return this.dot$mcF$sp(x, y);
   }

   public float dot$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.dot$mFc$sp(x, y, this.evidence$32$mcF$sp);
   }

   public ArrayInnerProductSpace$mcF$sp(final ClassTag evidence$31, final Field evidence$32$mcF$sp) {
      super(evidence$31, evidence$32$mcF$sp);
      this.evidence$32$mcF$sp = evidence$32$mcF$sp;
      this.evidence$31 = evidence$31;
   }
}
