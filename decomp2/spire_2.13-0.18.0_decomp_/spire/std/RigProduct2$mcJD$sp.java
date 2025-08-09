package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcJD$sp extends RigProduct2, SemiringProduct2$mcJD$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcJD$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcJD$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJD$sp$(final RigProduct2$mcJD$sp $this) {
      return $this.one$mcJD$sp();
   }

   default Tuple2 one$mcJD$sp() {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().one$mcJ$sp(), this.structure2$mcD$sp().one$mcD$sp());
   }
}
