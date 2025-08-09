package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcJI$sp extends RingProduct2, RngProduct2$mcJI$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcJI$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcJI$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcJI$sp$(final RingProduct2$mcJI$sp $this, final int x0) {
      return $this.fromInt$mcJI$sp(x0);
   }

   default Tuple2 fromInt$mcJI$sp(final int x0) {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().fromInt$mcJ$sp(x0), this.structure2$mcI$sp().fromInt$mcI$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcJI$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcJI$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJI$sp$(final RingProduct2$mcJI$sp $this) {
      return $this.one$mcJI$sp();
   }

   default Tuple2 one$mcJI$sp() {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().one$mcJ$sp(), this.structure2$mcI$sp().one$mcI$sp());
   }
}
