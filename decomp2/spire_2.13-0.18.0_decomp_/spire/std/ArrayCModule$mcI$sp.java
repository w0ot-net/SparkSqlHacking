package spire.std;

import algebra.ring.CommutativeRing;
import scala.reflect.ClassTag;
import spire.NotGiven;
import spire.algebra.CModule$mcI$sp;

public final class ArrayCModule$mcI$sp extends ArrayCModule implements CModule$mcI$sp {
   private static final long serialVersionUID = 0L;
   public final CommutativeRing evidence$27$mcI$sp;
   private final ClassTag evidence$26;
   private final NotGiven nvs;

   public Object timesr(final Object v, final int r) {
      return CModule$mcI$sp.timesr$(this, v, r);
   }

   public Object timesr$mcI$sp(final Object v, final int r) {
      return CModule$mcI$sp.timesr$mcI$sp$(this, v, r);
   }

   public CommutativeRing scalar() {
      return this.scalar$mcI$sp();
   }

   public CommutativeRing scalar$mcI$sp() {
      return spire.algebra.package$.MODULE$.CRing().apply(this.evidence$27$mcI$sp);
   }

   public int[] zero() {
      return this.zero$mcI$sp();
   }

   public int[] zero$mcI$sp() {
      return (int[])this.spire$std$ArrayCModule$$evidence$26.newArray(0);
   }

   public int[] negate(final int[] x) {
      return this.negate$mcI$sp(x);
   }

   public int[] negate$mcI$sp(final int[] x) {
      return ArraySupport$.MODULE$.negate$mIc$sp(x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcI$sp);
   }

   public int[] plus(final int[] x, final int[] y) {
      return this.plus$mcI$sp(x, y);
   }

   public int[] plus$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.plus$mIc$sp(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcI$sp);
   }

   public int[] minus(final int[] x, final int[] y) {
      return this.minus$mcI$sp(x, y);
   }

   public int[] minus$mcI$sp(final int[] x, final int[] y) {
      return ArraySupport$.MODULE$.minus$mIc$sp(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcI$sp);
   }

   public int[] timesl(final int r, final int[] x) {
      return this.timesl$mcI$sp(r, x);
   }

   public int[] timesl$mcI$sp(final int r, final int[] x) {
      return ArraySupport$.MODULE$.timesl$mIc$sp(r, x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27$mcI$sp);
   }

   public ArrayCModule$mcI$sp(final ClassTag evidence$26, final CommutativeRing evidence$27$mcI$sp, final NotGiven nvs) {
      super(evidence$26, evidence$27$mcI$sp, nvs);
      this.evidence$27$mcI$sp = evidence$27$mcI$sp;
      this.evidence$26 = evidence$26;
      this.nvs = nvs;
   }
}
