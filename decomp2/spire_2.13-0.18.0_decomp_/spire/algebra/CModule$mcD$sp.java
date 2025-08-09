package spire.algebra;

public interface CModule$mcD$sp extends CModule, RightModule$mcD$sp, LeftModule$mcD$sp {
   // $FF: synthetic method
   static Object timesr$(final CModule$mcD$sp $this, final Object v, final double r) {
      return $this.timesr(v, r);
   }

   default Object timesr(final Object v, final double r) {
      return this.timesr$mcD$sp(v, r);
   }

   // $FF: synthetic method
   static Object timesr$mcD$sp$(final CModule$mcD$sp $this, final Object v, final double r) {
      return $this.timesr$mcD$sp(v, r);
   }

   default Object timesr$mcD$sp(final Object v, final double r) {
      return this.timesl$mcD$sp(r, v);
   }
}
