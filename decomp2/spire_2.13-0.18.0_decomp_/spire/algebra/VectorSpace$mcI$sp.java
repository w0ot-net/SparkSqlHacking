package spire.algebra;

public interface VectorSpace$mcI$sp extends VectorSpace, CModule$mcI$sp {
   // $FF: synthetic method
   static Object divr$(final VectorSpace$mcI$sp $this, final Object v, final int f) {
      return $this.divr(v, f);
   }

   default Object divr(final Object v, final int f) {
      return this.divr$mcI$sp(v, f);
   }

   // $FF: synthetic method
   static Object divr$mcI$sp$(final VectorSpace$mcI$sp $this, final Object v, final int f) {
      return $this.divr$mcI$sp(v, f);
   }

   default Object divr$mcI$sp(final Object v, final int f) {
      return this.timesl$mcI$sp(this.scalar$mcI$sp().reciprocal$mcI$sp(f), v);
   }
}
