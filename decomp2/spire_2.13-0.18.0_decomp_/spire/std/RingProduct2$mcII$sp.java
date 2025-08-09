package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcII$sp extends RingProduct2, RngProduct2$mcII$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcII$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcII$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcII$sp$(final RingProduct2$mcII$sp $this, final int x0) {
      return $this.fromInt$mcII$sp(x0);
   }

   default Tuple2 fromInt$mcII$sp(final int x0) {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().fromInt$mcI$sp(x0), this.structure2$mcI$sp().fromInt$mcI$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcII$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcII$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcII$sp$(final RingProduct2$mcII$sp $this) {
      return $this.one$mcII$sp();
   }

   default Tuple2 one$mcII$sp() {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().one$mcI$sp(), this.structure2$mcI$sp().one$mcI$sp());
   }
}
