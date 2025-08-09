package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcJJ$sp extends RingProduct2, RngProduct2$mcJJ$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcJJ$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcJJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcJJ$sp$(final RingProduct2$mcJJ$sp $this, final int x0) {
      return $this.fromInt$mcJJ$sp(x0);
   }

   default Tuple2 fromInt$mcJJ$sp(final int x0) {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().fromInt$mcJ$sp(x0), this.structure2$mcJ$sp().fromInt$mcJ$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcJJ$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcJJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcJJ$sp$(final RingProduct2$mcJJ$sp $this) {
      return $this.one$mcJJ$sp();
   }

   default Tuple2 one$mcJJ$sp() {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().one$mcJ$sp(), this.structure2$mcJ$sp().one$mcJ$sp());
   }
}
