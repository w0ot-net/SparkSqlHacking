package spire.algebra;

public interface NormedVectorSpace$mcF$sp extends NormedVectorSpace, MetricSpace$mcF$sp, VectorSpace$mcF$sp {
   // $FF: synthetic method
   static float distance$(final NormedVectorSpace$mcF$sp $this, final Object v, final Object w) {
      return $this.distance(v, w);
   }

   default float distance(final Object v, final Object w) {
      return this.distance$mcF$sp(v, w);
   }

   // $FF: synthetic method
   static float distance$mcF$sp$(final NormedVectorSpace$mcF$sp $this, final Object v, final Object w) {
      return $this.distance$mcF$sp(v, w);
   }

   default float distance$mcF$sp(final Object v, final Object w) {
      return this.norm$mcF$sp(this.minus(v, w));
   }
}
