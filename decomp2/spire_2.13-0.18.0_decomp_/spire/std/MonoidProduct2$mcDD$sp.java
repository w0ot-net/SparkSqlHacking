package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcDD$sp extends MonoidProduct2, SemigroupProduct2$mcDD$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcDD$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcDD$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcDD$sp$(final MonoidProduct2$mcDD$sp $this) {
      return $this.empty$mcDD$sp();
   }

   default Tuple2 empty$mcDD$sp() {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().empty$mcD$sp(), this.structure2$mcD$sp().empty$mcD$sp());
   }
}
