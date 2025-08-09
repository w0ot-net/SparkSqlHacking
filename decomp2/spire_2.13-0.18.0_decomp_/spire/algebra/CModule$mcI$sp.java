package spire.algebra;

public interface CModule$mcI$sp extends CModule, RightModule$mcI$sp, LeftModule$mcI$sp {
   // $FF: synthetic method
   static Object timesr$(final CModule$mcI$sp $this, final Object v, final int r) {
      return $this.timesr(v, r);
   }

   default Object timesr(final Object v, final int r) {
      return this.timesr$mcI$sp(v, r);
   }

   // $FF: synthetic method
   static Object timesr$mcI$sp$(final CModule$mcI$sp $this, final Object v, final int r) {
      return $this.timesr$mcI$sp(v, r);
   }

   default Object timesr$mcI$sp(final Object v, final int r) {
      return this.timesl$mcI$sp(r, v);
   }
}
