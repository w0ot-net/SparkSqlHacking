package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RigProduct2$mcDF$sp extends RigProduct2, SemiringProduct2$mcDF$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcDF$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcDF$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDF$sp$(final RigProduct2$mcDF$sp $this) {
      return $this.one$mcDF$sp();
   }

   default Tuple2 one$mcDF$sp() {
      return new Tuple2(BoxesRunTime.boxToDouble(this.structure1$mcD$sp().one$mcD$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().one$mcF$sp()));
   }
}
