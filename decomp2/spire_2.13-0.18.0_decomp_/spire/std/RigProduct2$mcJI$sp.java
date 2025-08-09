package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcJI$sp extends RigProduct2, SemiringProduct2$mcJI$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcJI$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcJI$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJI$sp$(final RigProduct2$mcJI$sp $this) {
      return $this.one$mcJI$sp();
   }

   default Tuple2 one$mcJI$sp() {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().one$mcJ$sp(), this.structure2$mcI$sp().one$mcI$sp());
   }
}
