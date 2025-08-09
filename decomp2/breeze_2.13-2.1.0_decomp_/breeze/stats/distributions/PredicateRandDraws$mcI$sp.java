package breeze.stats.distributions;

public interface PredicateRandDraws$mcI$sp extends PredicateRandDraws, Rand$mcI$sp {
   // $FF: synthetic method
   static int draw$(final PredicateRandDraws$mcI$sp $this) {
      return $this.draw();
   }

   default int draw() {
      return this.draw$mcI$sp();
   }

   // $FF: synthetic method
   static int draw$mcI$sp$(final PredicateRandDraws$mcI$sp $this) {
      return $this.draw$mcI$sp();
   }

   default int draw$mcI$sp() {
      int x;
      for(x = this.rand$mcI$sp().draw$mcI$sp(); !this.predicate$mcI$sp(x); x = this.rand$mcI$sp().draw$mcI$sp()) {
      }

      return x;
   }
}
