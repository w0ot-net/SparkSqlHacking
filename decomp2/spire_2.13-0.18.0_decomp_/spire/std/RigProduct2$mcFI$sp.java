package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RigProduct2$mcFI$sp extends RigProduct2, SemiringProduct2$mcFI$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcFI$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcFI$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFI$sp$(final RigProduct2$mcFI$sp $this) {
      return $this.one$mcFI$sp();
   }

   default Tuple2 one$mcFI$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().one$mcF$sp()), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().one$mcI$sp()));
   }
}
