package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcDI$sp extends MonoidProduct2, SemigroupProduct2$mcDI$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcDI$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcDI$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcDI$sp$(final MonoidProduct2$mcDI$sp $this) {
      return $this.empty$mcDI$sp();
   }

   default Tuple2 empty$mcDI$sp() {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().empty$mcD$sp(), this.structure2$mcI$sp().empty$mcI$sp());
   }
}
