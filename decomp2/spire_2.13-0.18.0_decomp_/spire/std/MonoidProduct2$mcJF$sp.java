package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface MonoidProduct2$mcJF$sp extends MonoidProduct2, SemigroupProduct2$mcJF$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcJF$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcJF$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcJF$sp$(final MonoidProduct2$mcJF$sp $this) {
      return $this.empty$mcJF$sp();
   }

   default Tuple2 empty$mcJF$sp() {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().empty$mcJ$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().empty$mcF$sp()));
   }
}
