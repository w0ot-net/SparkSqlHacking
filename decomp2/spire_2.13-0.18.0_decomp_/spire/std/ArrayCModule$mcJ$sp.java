package spire.std;

import algebra.ring.CommutativeRing;
import scala.reflect.ClassTag;
import spire.NotGiven;
import spire.algebra.CModule$mcJ$sp;

public final class ArrayCModule$mcJ$sp extends ArrayCModule implements CModule$mcJ$sp {
   private static final long serialVersionUID = 0L;
   public final CommutativeRing evidence$27$mcJ$sp;
   private final ClassTag evidence$26;
   private final NotGiven nvs;

   public Object timesr(final Object v, final long r) {
      return CModule$mcJ$sp.timesr$(this, v, r);
   }

   public Object timesr$mcJ$sp(final Object v, final long r) {
      return CModule$mcJ$sp.timesr$mcJ$sp$(this, v, r);
   }

   public CommutativeRing scalar() {
      return this.scalar$mcJ$sp();
   }

   public CommutativeRing scalar$mcJ$sp() {
      return spire.algebra.package$.MODULE$.CRing().apply(this.evidence$27$mcJ$sp);
   }

   public long[] zero() {
      return this.zero$mcJ$sp();
   }

   public long[] zero$mcJ$sp() {
      return (long[])this.spire$std$ArrayCModule$$evidence$26.newArray(0);
   }

   public long[] negate(final long[] x) {
      return this.negate$mcJ$sp(x);
   }

   public long[] negate$mcJ$sp(final long[] x) {
      return ArraySupport$.MODULE$.negate$mJc$sp(x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcJ$sp);
   }

   public long[] plus(final long[] x, final long[] y) {
      return this.plus$mcJ$sp(x, y);
   }

   public long[] plus$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.plus$mJc$sp(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcJ$sp);
   }

   public long[] minus(final long[] x, final long[] y) {
      return this.minus$mcJ$sp(x, y);
   }

   public long[] minus$mcJ$sp(final long[] x, final long[] y) {
      return ArraySupport$.MODULE$.minus$mJc$sp(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcJ$sp);
   }

   public long[] timesl(final long r, final long[] x) {
      return this.timesl$mcJ$sp(r, x);
   }

   public long[] timesl$mcJ$sp(final long r, final long[] x) {
      return ArraySupport$.MODULE$.timesl$mJc$sp(r, x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcJ$sp);
   }

   public ArrayCModule$mcJ$sp(final ClassTag evidence$26, final CommutativeRing evidence$27$mcJ$sp, final NotGiven nvs) {
      super(evidence$26, evidence$27$mcJ$sp, nvs);
      this.evidence$27$mcJ$sp = evidence$27$mcJ$sp;
      this.evidence$26 = evidence$26;
      this.nvs = nvs;
   }
}
