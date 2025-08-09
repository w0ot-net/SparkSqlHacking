package spire.std;

import scala.Tuple2;

public interface RigProduct2$mcDJ$sp extends RigProduct2, SemiringProduct2$mcDJ$sp {
   // $FF: synthetic method
   static Tuple2 one$(final RigProduct2$mcDJ$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcDJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDJ$sp$(final RigProduct2$mcDJ$sp $this) {
      return $this.one$mcDJ$sp();
   }

   default Tuple2 one$mcDJ$sp() {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().one$mcD$sp(), this.structure2$mcJ$sp().one$mcJ$sp());
   }
}
