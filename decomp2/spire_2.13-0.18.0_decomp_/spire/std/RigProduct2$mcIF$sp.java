package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RigProduct2$mcIF$sp extends RigProduct2, SemiringProduct2$mcIF$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcIF$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcIF$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcIF$sp$(final RigProduct2$mcIF$sp $this) {
      return $this.one$mcIF$sp();
   }

   default Tuple2 one$mcIF$sp() {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().one$mcI$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().one$mcF$sp()));
   }
}
