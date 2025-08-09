package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcJJ$sp extends MonoidProduct2, SemigroupProduct2$mcJJ$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcJJ$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcJJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcJJ$sp$(final MonoidProduct2$mcJJ$sp $this) {
      return $this.empty$mcJJ$sp();
   }

   default Tuple2 empty$mcJJ$sp() {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().empty$mcJ$sp(), this.structure2$mcJ$sp().empty$mcJ$sp());
   }
}
