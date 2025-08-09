package spire.algebra;

public interface VectorSpace$mcD$sp extends VectorSpace, CModule$mcD$sp {
   // $FF: synthetic method
   static Object divr$(final VectorSpace$mcD$sp $this, final Object v, final double f) {
      return $this.divr(v, f);
   }

   default Object divr(final Object v, final double f) {
      return this.divr$mcD$sp(v, f);
   }

   // $FF: synthetic method
   static Object divr$mcD$sp$(final VectorSpace$mcD$sp $this, final Object v, final double f) {
      return $this.divr$mcD$sp(v, f);
   }

   default Object divr$mcD$sp(final Object v, final double f) {
      return this.timesl$mcD$sp(this.scalar$mcD$sp().reciprocal$mcD$sp(f), v);
   }
}
