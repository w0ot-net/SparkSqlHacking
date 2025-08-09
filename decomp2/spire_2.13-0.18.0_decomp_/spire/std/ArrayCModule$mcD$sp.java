package spire.std;

import algebra.ring.CommutativeRing;
import scala.reflect.ClassTag;
import spire.NotGiven;
import spire.algebra.CModule$mcD$sp;

public final class ArrayCModule$mcD$sp extends ArrayCModule implements CModule$mcD$sp {
   private static final long serialVersionUID = 0L;
   public final CommutativeRing evidence$27$mcD$sp;
   private final ClassTag evidence$26;
   private final NotGiven nvs;

   public Object timesr(final Object v, final double r) {
      return CModule$mcD$sp.timesr$(this, v, r);
   }

   public Object timesr$mcD$sp(final Object v, final double r) {
      return CModule$mcD$sp.timesr$mcD$sp$(this, v, r);
   }

   public CommutativeRing scalar() {
      return this.scalar$mcD$sp();
   }

   public CommutativeRing scalar$mcD$sp() {
      return spire.algebra.package$.MODULE$.CRing().apply(this.evidence$27$mcD$sp);
   }

   public double[] zero() {
      return this.zero$mcD$sp();
   }

   public double[] zero$mcD$sp() {
      return (double[])this.spire$std$ArrayCModule$$evidence$26.newArray(0);
   }

   public double[] negate(final double[] x) {
      return this.negate$mcD$sp(x);
   }

   public double[] negate$mcD$sp(final double[] x) {
      return ArraySupport$.MODULE$.negate$mDc$sp(x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcD$sp);
   }

   public double[] plus(final double[] x, final double[] y) {
      return this.plus$mcD$sp(x, y);
   }

   public double[] plus$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.plus$mDc$sp(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcD$sp);
   }

   public double[] minus(final double[] x, final double[] y) {
      return this.minus$mcD$sp(x, y);
   }

   public double[] minus$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.minus$mDc$sp(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcD$sp);
   }

   public double[] timesl(final double r, final double[] x) {
      return this.timesl$mcD$sp(r, x);
   }

   public double[] timesl$mcD$sp(final double r, final double[] x) {
      return ArraySupport$.MODULE$.timesl$mDc$sp(r, x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcD$sp);
   }

   public ArrayCModule$mcD$sp(final ClassTag evidence$26, final CommutativeRing evidence$27$mcD$sp, final NotGiven nvs) {
      super(evidence$26, evidence$27$mcD$sp, nvs);
      this.evidence$27$mcD$sp = evidence$27$mcD$sp;
      this.evidence$26 = evidence$26;
      this.nvs = nvs;
   }
}
