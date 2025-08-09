package spire.algebra;

public interface NormedVectorSpace$mcJ$sp extends NormedVectorSpace, MetricSpace$mcJ$sp, VectorSpace$mcJ$sp {
   // $FF: synthetic method
   static long distance$(final NormedVectorSpace$mcJ$sp $this, final Object v, final Object w) {
      return $this.distance(v, w);
   }

   default long distance(final Object v, final Object w) {
      return this.distance$mcJ$sp(v, w);
   }

   // $FF: synthetic method
   static long distance$mcJ$sp$(final NormedVectorSpace$mcJ$sp $this, final Object v, final Object w) {
      return $this.distance$mcJ$sp(v, w);
   }

   default long distance$mcJ$sp(final Object v, final Object w) {
      return this.norm$mcJ$sp(this.minus(v, w));
   }
}
