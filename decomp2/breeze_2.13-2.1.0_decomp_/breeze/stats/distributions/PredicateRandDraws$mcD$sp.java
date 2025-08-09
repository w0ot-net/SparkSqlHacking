package breeze.stats.distributions;

public interface PredicateRandDraws$mcD$sp extends PredicateRandDraws, Rand$mcD$sp {
   // $FF: synthetic method
   static double draw$(final PredicateRandDraws$mcD$sp $this) {
      return $this.draw();
   }

   default double draw() {
      return this.draw$mcD$sp();
   }

   // $FF: synthetic method
   static double draw$mcD$sp$(final PredicateRandDraws$mcD$sp $this) {
      return $this.draw$mcD$sp();
   }

   default double draw$mcD$sp() {
      double x;
      for(x = this.rand$mcD$sp().draw$mcD$sp(); !this.predicate$mcD$sp(x); x = this.rand$mcD$sp().draw$mcD$sp()) {
      }

      return x;
   }
}
