package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcJJ$sp extends RigProduct2, SemiringProduct2$mcJJ$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcJJ$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcJJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJJ$sp$(final RigProduct2$mcJJ$sp $this) {
      return $this.one$mcJJ$sp();
   }

   default Tuple2 one$mcJJ$sp() {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().one$mcJ$sp(), this.structure2$mcJ$sp().one$mcJ$sp());
   }
}
