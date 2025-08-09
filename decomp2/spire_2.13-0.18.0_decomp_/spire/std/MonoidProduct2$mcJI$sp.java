package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcJI$sp extends MonoidProduct2, SemigroupProduct2$mcJI$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcJI$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcJI$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcJI$sp$(final MonoidProduct2$mcJI$sp $this) {
      return $this.empty$mcJI$sp();
   }

   default Tuple2 empty$mcJI$sp() {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().empty$mcJ$sp(), this.structure2$mcI$sp().empty$mcI$sp());
   }
}
