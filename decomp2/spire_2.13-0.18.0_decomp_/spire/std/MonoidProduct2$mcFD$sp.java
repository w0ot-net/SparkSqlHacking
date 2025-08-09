package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface MonoidProduct2$mcFD$sp extends MonoidProduct2, SemigroupProduct2$mcFD$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcFD$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcFD$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcFD$sp$(final MonoidProduct2$mcFD$sp $this) {
      return $this.empty$mcFD$sp();
   }

   default Tuple2 empty$mcFD$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().empty$mcF$sp()), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().empty$mcD$sp()));
   }
}
