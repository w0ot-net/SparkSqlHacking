package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface MonoidProduct2$mcFJ$sp extends MonoidProduct2, SemigroupProduct2$mcFJ$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcFJ$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcFJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcFJ$sp$(final MonoidProduct2$mcFJ$sp $this) {
      return $this.empty$mcFJ$sp();
   }

   default Tuple2 empty$mcFJ$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().empty$mcF$sp()), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().empty$mcJ$sp()));
   }
}
