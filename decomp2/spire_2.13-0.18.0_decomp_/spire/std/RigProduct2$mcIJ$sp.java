package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcIJ$sp extends RigProduct2, SemiringProduct2$mcIJ$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcIJ$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcIJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcIJ$sp$(final RigProduct2$mcIJ$sp $this) {
      return $this.one$mcIJ$sp();
   }

   default Tuple2 one$mcIJ$sp() {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().one$mcI$sp(), this.structure2$mcJ$sp().one$mcJ$sp());
   }
}
