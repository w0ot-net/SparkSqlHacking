package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcJD$sp extends RingProduct2, RngProduct2$mcJD$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcJD$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcJD$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcJD$sp$(final RingProduct2$mcJD$sp $this, final int x0) {
      return $this.fromInt$mcJD$sp(x0);
   }

   default Tuple2 fromInt$mcJD$sp(final int x0) {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().fromInt$mcJ$sp(x0), this.structure2$mcD$sp().fromInt$mcD$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcJD$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcJD$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJD$sp$(final RingProduct2$mcJD$sp $this) {
      return $this.one$mcJD$sp();
   }

   default Tuple2 one$mcJD$sp() {
      return new Tuple2.mcJD.sp(this.structure1$mcJ$sp().one$mcJ$sp(), this.structure2$mcD$sp().one$mcD$sp());
   }
}
