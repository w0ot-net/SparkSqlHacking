package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface MonoidProduct2$mcFF$sp extends MonoidProduct2, SemigroupProduct2$mcFF$sp {
   // $FF: synthetic method
   static Tuple2 empty$(final MonoidProduct2$mcFF$sp $this) {
      return $this.empty();
   }

   default Tuple2 empty() {
      return this.empty$mcFF$sp();
   }

   // $FF: synthetic method
   static Tuple2 empty$mcFF$sp$(final MonoidProduct2$mcFF$sp $this) {
      return $this.empty$mcFF$sp();
   }

   default Tuple2 empty$mcFF$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().empty$mcF$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().empty$mcF$sp()));
   }
}
