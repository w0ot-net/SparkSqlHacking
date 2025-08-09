package spire.algebra;

public interface CModule$mcJ$sp extends CModule, RightModule$mcJ$sp, LeftModule$mcJ$sp {
   // $FF: synthetic method
   static Object timesr$(final CModule$mcJ$sp $this, final Object v, final long r) {
      return $this.timesr(v, r);
   }

   default Object timesr(final Object v, final long r) {
      return this.timesr$mcJ$sp(v, r);
   }

   // $FF: synthetic method
   static Object timesr$mcJ$sp$(final CModule$mcJ$sp $this, final Object v, final long r) {
      return $this.timesr$mcJ$sp(v, r);
   }

   default Object timesr$mcJ$sp(final Object v, final long r) {
      return this.timesl$mcJ$sp(r, v);
   }
}
