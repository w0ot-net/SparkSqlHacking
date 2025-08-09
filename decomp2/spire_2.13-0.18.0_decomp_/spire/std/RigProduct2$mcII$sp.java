package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcII$sp extends RigProduct2, SemiringProduct2$mcII$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcII$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcII$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcII$sp$(final RigProduct2$mcII$sp $this) {
      return $this.one$mcII$sp();
   }

   default Tuple2 one$mcII$sp() {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().one$mcI$sp(), this.structure2$mcI$sp().one$mcI$sp());
   }
}
