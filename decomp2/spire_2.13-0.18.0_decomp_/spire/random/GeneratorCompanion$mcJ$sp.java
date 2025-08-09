package spire.random;

public interface GeneratorCompanion$mcJ$sp extends GeneratorCompanion {
   // $FF: synthetic method
   static Object apply$(final GeneratorCompanion$mcJ$sp $this, final long seed) {
      return $this.apply(seed);
   }

   default Object apply(final long seed) {
      return this.apply$mcJ$sp(seed);
   }

   // $FF: synthetic method
   static Object apply$mcJ$sp$(final GeneratorCompanion$mcJ$sp $this, final long seed) {
      return $this.apply$mcJ$sp(seed);
   }

   default Object apply$mcJ$sp(final long seed) {
      return this.fromSeed$mcJ$sp(seed);
   }
}
