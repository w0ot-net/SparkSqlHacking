package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcID$sp extends RingProduct2, RngProduct2$mcID$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcID$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcID$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcID$sp$(final RingProduct2$mcID$sp $this, final int x0) {
      return $this.fromInt$mcID$sp(x0);
   }

   default Tuple2 fromInt$mcID$sp(final int x0) {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().fromInt$mcI$sp(x0), this.structure2$mcD$sp().fromInt$mcD$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcID$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcID$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcID$sp$(final RingProduct2$mcID$sp $this) {
      return $this.one$mcID$sp();
   }

   default Tuple2 one$mcID$sp() {
      return new Tuple2.mcID.sp(this.structure1$mcI$sp().one$mcI$sp(), this.structure2$mcD$sp().one$mcD$sp());
   }
}
