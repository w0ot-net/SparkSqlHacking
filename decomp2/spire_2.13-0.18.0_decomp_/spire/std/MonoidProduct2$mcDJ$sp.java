package spire.std;

import scala.Tuple2;

public interface MonoidProduct2$mcDJ$sp extends MonoidProduct2, SemigroupProduct2$mcDJ$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcDJ$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcDJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcDJ$sp$(final MonoidProduct2$mcDJ$sp $this) {
      return $this.empty$mcDJ$sp();
   }

   default Tuple2 empty$mcDJ$sp() {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().empty$mcD$sp(), this.structure2$mcJ$sp().empty$mcJ$sp());
   }
}
