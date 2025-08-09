package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcIJ$sp extends MonoidProduct2, SemigroupProduct2$mcIJ$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcIJ$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcIJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcIJ$sp$(final MonoidProduct2$mcIJ$sp $this) {
      return $this.empty$mcIJ$sp();
   }

   default Tuple2 empty$mcIJ$sp() {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().empty$mcI$sp(), this.structure2$mcJ$sp().empty$mcJ$sp());
   }
}
