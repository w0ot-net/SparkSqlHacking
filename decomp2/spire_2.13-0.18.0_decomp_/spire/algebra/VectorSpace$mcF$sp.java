package spire.algebra;

public interface VectorSpace$mcF$sp extends VectorSpace, CModule$mcF$sp {
   // $FF: synthetic method
   static Object divr$(final VectorSpace$mcF$sp $this, final Object v, final float f) {
      return $this.divr(v, f);
   }

   default Object divr(final Object v, final float f) {
      return this.divr$mcF$sp(v, f);
   }

   // $FF: synthetic method
   static Object divr$mcF$sp$(final VectorSpace$mcF$sp $this, final Object v, final float f) {
      return $this.divr$mcF$sp(v, f);
   }

   default Object divr$mcF$sp(final Object v, final float f) {
      return this.timesl$mcF$sp(this.scalar$mcF$sp().reciprocal$mcF$sp(f), v);
   }
}
