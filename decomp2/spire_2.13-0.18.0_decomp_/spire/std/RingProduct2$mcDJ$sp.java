package spire.std;

import scala.Tuple2;

public interface RingProduct2$mcDJ$sp extends RingProduct2, RngProduct2$mcDJ$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcDJ$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcDJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcDJ$sp$(final RingProduct2$mcDJ$sp $this, final int x0) {
      return $this.fromInt$mcDJ$sp(x0);
   }

   default Tuple2 fromInt$mcDJ$sp(final int x0) {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().fromInt$mcD$sp(x0), this.structure2$mcJ$sp().fromInt$mcJ$sp(x0));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcDJ$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcDJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcDJ$sp$(final RingProduct2$mcDJ$sp $this) {
      return $this.one$mcDJ$sp();
   }

   default Tuple2 one$mcDJ$sp() {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().one$mcD$sp(), this.structure2$mcJ$sp().one$mcJ$sp());
   }
}
