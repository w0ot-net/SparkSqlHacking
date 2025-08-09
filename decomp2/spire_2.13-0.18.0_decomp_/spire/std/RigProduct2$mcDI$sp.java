package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcDI$sp extends RigProduct2, SemiringProduct2$mcDI$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcDI$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcDI$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDI$sp$(final RigProduct2$mcDI$sp $this) {
      return $this.one$mcDI$sp();
   }

   default Tuple2 one$mcDI$sp() {
      return new Tuple2.mcDI.sp(this.structure1$mcD$sp().one$mcD$sp(), this.structure2$mcI$sp().one$mcI$sp());
   }
}
