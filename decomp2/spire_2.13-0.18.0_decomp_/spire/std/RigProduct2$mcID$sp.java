package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcID$sp extends RigProduct2, SemiringProduct2$mcID$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcID$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcID$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcID$sp$(final RigProduct2$mcID$sp $this) {
      return $this.one$mcID$sp();
   }

   default Tuple2 one$mcID$sp() {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().one$mcI$sp(), this.structure2$mcD$sp().one$mcD$sp());
   }
}
