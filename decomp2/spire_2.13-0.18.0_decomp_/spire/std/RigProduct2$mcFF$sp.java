package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RigProduct2$mcFF$sp extends RigProduct2, SemiringProduct2$mcFF$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcFF$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcFF$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFF$sp$(final RigProduct2$mcFF$sp $this) {
      return $this.one$mcFF$sp();
   }

   default Tuple2 one$mcFF$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().one$mcF$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().one$mcF$sp()));
   }
}
