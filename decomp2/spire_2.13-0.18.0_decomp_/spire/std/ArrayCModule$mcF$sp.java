package spire.std;

import algebra.ring.CommutativeRing;
import scala.reflect.ClassTag;
import spire.NotGiven;
import spire.algebra.CModule$mcF$sp;

public final class ArrayCModule$mcF$sp extends ArrayCModule implements CModule$mcF$sp {
   private static final long serialVersionUID = 0L;
   public final CommutativeRing evidence$27$mcF$sp;
   private final ClassTag evidence$26;
   private final NotGiven nvs;

   public Object timesr(final Object v, final float r) {
      return CModule$mcF$sp.timesr$(this, v, r);
   }

   public Object timesr$mcF$sp(final Object v, final float r) {
      return CModule$mcF$sp.timesr$mcF$sp$(this, v, r);
   }

   public CommutativeRing scalar() {
      return this.scalar$mcF$sp();
   }

   public CommutativeRing scalar$mcF$sp() {
      return spire.algebra.package$.MODULE$.CRing().apply(this.evidence$27$mcF$sp);
   }

   public float[] zero() {
      return this.zero$mcF$sp();
   }

   public float[] zero$mcF$sp() {
      return (float[])this.spire$std$ArrayCModule$$evidence$26.newArray(0);
   }

   public float[] negate(final float[] x) {
      return this.negate$mcF$sp(x);
   }

   public float[] negate$mcF$sp(final float[] x) {
      return ArraySupport$.MODULE$.negate$mFc$sp(x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcF$sp);
   }

   public float[] plus(final float[] x, final float[] y) {
      return this.plus$mcF$sp(x, y);
   }

   public float[] plus$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.plus$mFc$sp(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcF$sp);
   }

   public float[] minus(final float[] x, final float[] y) {
      return this.minus$mcF$sp(x, y);
   }

   public float[] minus$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.minus$mFc$sp(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcF$sp);
   }

   public float[] timesl(final float r, final float[] x) {
      return this.timesl$mcF$sp(r, x);
   }

   public float[] timesl$mcF$sp(final float r, final float[] x) {
      return ArraySupport$.MODULE$.timesl$mFc$sp(r, x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcF$sp);
   }

   public ArrayCModule$mcF$sp(final ClassTag evidence$26, final CommutativeRing evidence$27$mcF$sp, final NotGiven nvs) {
      super(evidence$26, evidence$27$mcF$sp, nvs);
      this.evidence$27$mcF$sp = evidence$27$mcF$sp;
      this.evidence$26 = evidence$26;
      this.nvs = nvs;
   }
}
