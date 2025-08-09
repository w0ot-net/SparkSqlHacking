package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.NotGiven;
import spire.algebra.CModule$mcF$sp;
import spire.algebra.VectorSpace$mcF$sp;

public final class ArrayVectorSpace$mcF$sp extends ArrayVectorSpace implements VectorSpace$mcF$sp {
   private static final long serialVersionUID = 0L;
   public final Field evidence$29$mcF$sp;
   private final ClassTag evidence$28;
   private final NotGiven nnvs;

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
      return spire.algebra.package$.MODULE$.Field().apply(this.evidence$29$mcF$sp);
   }

   public float[] zero() {
      return this.zero$mcF$sp();
   }

   public float[] zero$mcF$sp() {
      return (float[])this.spire$std$ArrayVectorSpace$$evidence$28.newArray(0);
   }

   public float[] negate(final float[] x) {
      return this.negate$mcF$sp(x);
   }

   public float[] negate$mcF$sp(final float[] x) {
      return ArraySupport$.MODULE$.negate$mFc$sp(x, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcF$sp);
   }

   public float[] plus(final float[] x, final float[] y) {
      return this.plus$mcF$sp(x, y);
   }

   public float[] plus$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.plus$mFc$sp(x, y, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcF$sp);
   }

   public float[] minus(final float[] x, final float[] y) {
      return this.minus$mcF$sp(x, y);
   }

   public float[] minus$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.minus$mFc$sp(x, y, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcF$sp);
   }

   public float[] timesl(final float r, final float[] x) {
      return this.timesl$mcF$sp(r, x);
   }

   public float[] timesl$mcF$sp(final float r, final float[] x) {
      return ArraySupport$.MODULE$.timesl$mFc$sp(r, x, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcF$sp);
   }

   public ArrayVectorSpace$mcF$sp(final ClassTag evidence$28, final Field evidence$29$mcF$sp, final NotGiven nnvs) {
      super(evidence$28, evidence$29$mcF$sp, nnvs);
      this.evidence$29$mcF$sp = evidence$29$mcF$sp;
      this.evidence$28 = evidence$28;
      this.nnvs = nnvs;
   }
}
