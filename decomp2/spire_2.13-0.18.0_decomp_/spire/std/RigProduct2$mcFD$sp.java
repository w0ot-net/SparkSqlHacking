package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RigProduct2$mcFD$sp extends RigProduct2, SemiringProduct2$mcFD$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcFD$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcFD$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFD$sp$(final RigProduct2$mcFD$sp $this) {
      return $this.one$mcFD$sp();
   }

   default Tuple2 one$mcFD$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().one$mcF$sp()), BoxesRunTime.boxToDouble(this.structure2$mcD$sp().one$mcD$sp()));
   }
}
