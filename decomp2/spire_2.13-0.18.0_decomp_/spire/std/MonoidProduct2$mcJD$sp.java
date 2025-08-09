package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcJD$sp extends MonoidProduct2, SemigroupProduct2$mcJD$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcJD$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcJD$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcJD$sp$(final MonoidProduct2$mcJD$sp $this) {
      return $this.empty$mcJD$sp();
   }

   default Tuple2 empty$mcJD$sp() {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().empty$mcJ$sp(), this.structure2$mcD$sp().empty$mcD$sp());
   }
}
