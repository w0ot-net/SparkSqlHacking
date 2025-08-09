package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcDD$sp extends RigProduct2, SemiringProduct2$mcDD$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcDD$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcDD$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDD$sp$(final RigProduct2$mcDD$sp $this) {
      return $this.one$mcDD$sp();
   }

   default Tuple2 one$mcDD$sp() {
      return new Tuple2.mcDD.sp(this.structure1$mcD$sp().one$mcD$sp(), this.structure2$mcD$sp().one$mcD$sp());
   }
}
