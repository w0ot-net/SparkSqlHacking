package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcID$sp extends MonoidProduct2, SemigroupProduct2$mcID$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcID$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcID$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcID$sp$(final MonoidProduct2$mcID$sp $this) {
      return $this.empty$mcID$sp();
   }

   default Tuple2 empty$mcID$sp() {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().empty$mcI$sp(), this.structure2$mcD$sp().empty$mcD$sp());
   }
}
