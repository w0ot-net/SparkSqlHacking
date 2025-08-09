package spire.algebra;

public interface CModule$mcF$sp extends CModule, RightModule$mcF$sp, LeftModule$mcF$sp {
   // $FF: synthetic method
   static Object timesr$(final CModule$mcF$sp $this, final Object v, final float r) {
      return $this.timesr(v, r);
   }

   default Object timesr(final Object v, final float r) {
      return this.timesr$mcF$sp(v, r);
   }

   // $FF: synthetic method
   static Object timesr$mcF$sp$(final CModule$mcF$sp $this, final Object v, final float r) {
      return $this.timesr$mcF$sp(v, r);
   }

   default Object timesr$mcF$sp(final Object v, final float r) {
      return this.timesl$mcF$sp(r, v);
   }
}
