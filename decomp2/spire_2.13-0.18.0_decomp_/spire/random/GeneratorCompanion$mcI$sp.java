package spire.random;

public interface GeneratorCompanion$mcI$sp extends GeneratorCompanion {
   // $FF: synthetic method
   static Object apply$(final GeneratorCompanion$mcI$sp $this, final int seed) {
      return $this.apply(seed);
   }

   default Object apply(final int seed) {
      return this.apply$mcI$sp(seed);
   }

   // $FF: synthetic method
   static Object apply$mcI$sp$(final GeneratorCompanion$mcI$sp $this, final int seed) {
      return $this.apply$mcI$sp(seed);
   }

   default Object apply$mcI$sp(final int seed) {
      return this.fromSeed$mcI$sp(seed);
   }
}
