package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RingProduct2$mcIF$sp extends RingProduct2, RngProduct2$mcIF$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcIF$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcIF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcIF$sp$(final RingProduct2$mcIF$sp $this, final int x0) {
      return $this.fromInt$mcIF$sp(x0);
   }

   default Tuple2 fromInt$mcIF$sp(final int x0) {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().fromInt$mcI$sp(x0)), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().fromInt$mcF$sp(x0)));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcIF$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcIF$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcIF$sp$(final RingProduct2$mcIF$sp $this) {
      return $this.one$mcIF$sp();
   }

   default Tuple2 one$mcIF$sp() {
      return new Tuple2(BoxesRunTime.boxToInteger(this.structure1$mcI$sp().one$mcI$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().one$mcF$sp()));
   }
}
