package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RigProduct2$mcFJ$sp extends RigProduct2, SemiringProduct2$mcFJ$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcFJ$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcFJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFJ$sp$(final RigProduct2$mcFJ$sp $this) {
      return $this.one$mcFJ$sp();
   }

   default Tuple2 one$mcFJ$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().one$mcF$sp()), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().one$mcJ$sp()));
   }
}
