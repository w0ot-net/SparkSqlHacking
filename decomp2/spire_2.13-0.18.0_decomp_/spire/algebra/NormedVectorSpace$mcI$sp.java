package spire.algebra;

public interface NormedVectorSpace$mcI$sp extends NormedVectorSpace, MetricSpace$mcI$sp, VectorSpace$mcI$sp {
   // $FF: synthetic method
   static int distance$(final NormedVectorSpace$mcI$sp $this, final Object v, final Object w) {
      return $this.distance(v, w);
   }

   default int distance(final Object v, final Object w) {
      return this.distance$mcI$sp(v, w);
   }

   // $FF: synthetic method
   static int distance$mcI$sp$(final NormedVectorSpace$mcI$sp $this, final Object v, final Object w) {
      return $this.distance$mcI$sp(v, w);
   }

   default int distance$mcI$sp(final Object v, final Object w) {
      return this.norm$mcI$sp(this.minus(v, w));
   }
}
