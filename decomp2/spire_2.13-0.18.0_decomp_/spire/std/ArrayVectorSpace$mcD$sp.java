package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.NotGiven;
import spire.algebra.CModule$mcD$sp;
import spire.algebra.VectorSpace$mcD$sp;

public final class ArrayVectorSpace$mcD$sp extends ArrayVectorSpace implements VectorSpace$mcD$sp {
   private static final long serialVersionUID = 0L;
   public final Field evidence$29$mcD$sp;
   private final ClassTag evidence$28;
   private final NotGiven nnvs;

   public Object divr(final Object v, final double f) {
      return VectorSpace$mcD$sp.divr$(this, v, f);
   }

   public Object divr$mcD$sp(final Object v, final double f) {
      return VectorSpace$mcD$sp.divr$mcD$sp$(this, v, f);
   }

   public Object timesr(final Object v, final double r) {
      return CModule$mcD$sp.timesr$(this, v, r);
   }

   public Object timesr$mcD$sp(final Object v, final double r) {
      return CModule$mcD$sp.timesr$mcD$sp$(this, v, r);
   }

   public Field scalar() {
      return this.scalar$mcD$sp();
   }

   public Field scalar$mcD$sp() {
      return spire.algebra.package$.MODULE$.Field().apply(this.evidence$29$mcD$sp);
   }

   public double[] zero() {
      return this.zero$mcD$sp();
   }

   public double[] zero$mcD$sp() {
      return (double[])this.spire$std$ArrayVectorSpace$$evidence$28.newArray(0);
   }

   public double[] negate(final double[] x) {
      return this.negate$mcD$sp(x);
   }

   public double[] negate$mcD$sp(final double[] x) {
      return ArraySupport$.MODULE$.negate$mDc$sp(x, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcD$sp);
   }

   public double[] plus(final double[] x, final double[] y) {
      return this.plus$mcD$sp(x, y);
   }

   public double[] plus$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.plus$mDc$sp(x, y, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcD$sp);
   }

   public double[] minus(final double[] x, final double[] y) {
      return this.minus$mcD$sp(x, y);
   }

   public double[] minus$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.minus$mDc$sp(x, y, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcD$sp);
   }

   public double[] timesl(final double r, final double[] x) {
      return this.timesl$mcD$sp(r, x);
   }

   public double[] timesl$mcD$sp(final double r, final double[] x) {
      return ArraySupport$.MODULE$.timesl$mDc$sp(r, x, this.spire$std$ArrayVectorSpace$$evidence$28, this.evidence$29$mcD$sp);
   }

   public ArrayVectorSpace$mcD$sp(final ClassTag evidence$28, final Field evidence$29$mcD$sp, final NotGiven nnvs) {
      super(evidence$28, evidence$29$mcD$sp, nnvs);
      this.evidence$29$mcD$sp = evidence$29$mcD$sp;
      this.evidence$28 = evidence$28;
      this.nnvs = nnvs;
   }
}
