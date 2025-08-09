package spire.algebra;

public interface VectorSpace$mcJ$sp extends VectorSpace, CModule$mcJ$sp {
   // $FF: synthetic method
   static Object divr$(final VectorSpace$mcJ$sp $this, final Object v, final long f) {
      return $this.divr(v, f);
   }

   default Object divr(final Object v, final long f) {
      return this.divr$mcJ$sp(v, f);
   }

   // $FF: synthetic method
   static Object divr$mcJ$sp$(final VectorSpace$mcJ$sp $this, final Object v, final long f) {
      return $this.divr$mcJ$sp(v, f);
   }

   default Object divr$mcJ$sp(final Object v, final long f) {
      return this.timesl$mcJ$sp(this.scalar$mcJ$sp().reciprocal$mcJ$sp(f), v);
   }
}
