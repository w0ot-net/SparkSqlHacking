package spire.algebra;

public interface NormedVectorSpace$mcD$sp extends NormedVectorSpace, MetricSpace$mcD$sp, VectorSpace$mcD$sp {
   // $FF: synthetic method
   static double distance$(final NormedVectorSpace$mcD$sp $this, final Object v, final Object w) {
      return $this.distance(v, w);
   }

   default double distance(final Object v, final Object w) {
      return this.distance$mcD$sp(v, w);
   }

   // $FF: synthetic method
   static double distance$mcD$sp$(final NormedVectorSpace$mcD$sp $this, final Object v, final Object w) {
      return $this.distance$mcD$sp(v, w);
   }

   default double distance$mcD$sp(final Object v, final Object w) {
      return this.norm$mcD$sp(this.minus(v, w));
   }
}
