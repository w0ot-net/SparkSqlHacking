package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface MonoidProduct2$mcIF$sp extends MonoidProduct2, SemigroupProduct2$mcIF$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcIF$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcIF$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcIF$sp$(final MonoidProduct2$mcIF$sp $this) {
      return $this.empty$mcIF$sp();
   }

   default Tuple2 empty$mcIF$sp() {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().empty$mcI$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().empty$mcF$sp()));
   }
}
