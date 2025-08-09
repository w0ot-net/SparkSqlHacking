package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcII$sp extends MonoidProduct2, SemigroupProduct2$mcII$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcII$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcII$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcII$sp$(final MonoidProduct2$mcII$sp $this) {
      return $this.empty$mcII$sp();
   }

   default Tuple2 empty$mcII$sp() {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().empty$mcI$sp(), this.structure2$mcI$sp().empty$mcI$sp());
   }
}
