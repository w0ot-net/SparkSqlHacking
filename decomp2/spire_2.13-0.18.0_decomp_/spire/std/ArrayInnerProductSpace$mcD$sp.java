package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.algebra.CModule$mcD$sp;
import spire.algebra.InnerProductSpace$mcD$sp;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace$mcD$sp;

public final class ArrayInnerProductSpace$mcD$sp extends ArrayInnerProductSpace implements InnerProductSpace$mcD$sp {
   private static final long serialVersionUID = 0L;
   public final Field evidence$32$mcD$sp;
   private final ClassTag evidence$31;

   public NormedVectorSpace normed(final NRoot ev) {
      return InnerProductSpace$mcD$sp.normed$(this, ev);
   }

   public NormedVectorSpace normed$mcD$sp(final NRoot ev) {
      return InnerProductSpace$mcD$sp.normed$mcD$sp$(this, ev);
   }

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
      return spire.algebra.package$.MODULE$.Field().apply(this.evidence$32$mcD$sp);
   }

   public double[] zero() {
      return this.zero$mcD$sp();
   }

   public double[] zero$mcD$sp() {
      return (double[])this.spire$std$ArrayInnerProductSpace$$evidence$31.newArray(0);
   }

   public double[] negate(final double[] x) {
      return this.negate$mcD$sp(x);
   }

   public double[] negate$mcD$sp(final double[] x) {
      return ArraySupport$.MODULE$.negate$mDc$sp(x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcD$sp);
   }

   public double[] plus(final double[] x, final double[] y) {
      return this.plus$mcD$sp(x, y);
   }

   public double[] plus$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.plus$mDc$sp(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcD$sp);
   }

   public double[] minus(final double[] x, final double[] y) {
      return this.minus$mcD$sp(x, y);
   }

   public double[] minus$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.minus$mDc$sp(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcD$sp);
   }

   public double[] timesl(final double r, final double[] x) {
      return this.timesl$mcD$sp(r, x);
   }

   public double[] timesl$mcD$sp(final double r, final double[] x) {
      return ArraySupport$.MODULE$.timesl$mDc$sp(r, x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32$mcD$sp);
   }

   public double dot(final double[] x, final double[] y) {
      return this.dot$mcD$sp(x, y);
   }

   public double dot$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.dot$mDc$sp(x, y, this.evidence$32$mcD$sp);
   }

   public ArrayInnerProductSpace$mcD$sp(final ClassTag evidence$31, final Field evidence$32$mcD$sp) {
      super(evidence$31, evidence$32$mcD$sp);
      this.evidence$32$mcD$sp = evidence$32$mcD$sp;
      this.evidence$31 = evidence$31;
   }
}
