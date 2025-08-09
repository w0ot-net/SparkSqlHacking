package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RigProduct2$mcJF$sp extends RigProduct2, SemiringProduct2$mcJF$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcJF$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcJF$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJF$sp$(final RigProduct2$mcJF$sp $this) {
      return $this.one$mcJF$sp();
   }

   default Tuple2 one$mcJF$sp() {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().one$mcJ$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().one$mcF$sp()));
   }
}
