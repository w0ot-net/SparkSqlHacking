package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface MonoidProduct2$mcFI$sp extends MonoidProduct2, SemigroupProduct2$mcFI$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcFI$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcFI$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcFI$sp$(final MonoidProduct2$mcFI$sp $this) {
      return $this.empty$mcFI$sp();
   }

   default Tuple2 empty$mcFI$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().empty$mcF$sp()), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().empty$mcI$sp()));
   }
}
