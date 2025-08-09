package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface MonoidProduct2$mcDF$sp extends MonoidProduct2, SemigroupProduct2$mcDF$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcDF$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcDF$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcDF$sp$(final MonoidProduct2$mcDF$sp $this) {
      return $this.empty$mcDF$sp();
   }

   default Tuple2 empty$mcDF$sp() {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().empty$mcD$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().empty$mcF$sp()));
   }
}
