package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RingProduct2$mcFJ$sp extends RingProduct2, RngProduct2$mcFJ$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcFJ$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcFJ$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcFJ$sp$(final RingProduct2$mcFJ$sp $this, final int x0) {
      return $this.fromInt$mcFJ$sp(x0);
   }

   default Tuple2 fromInt$mcFJ$sp(final int x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().fromInt$mcF$sp(x0)), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().fromInt$mcJ$sp(x0)));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcFJ$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcFJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFJ$sp$(final RingProduct2$mcFJ$sp $this) {
      return $this.one$mcFJ$sp();
   }

   default Tuple2 one$mcFJ$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().one$mcF$sp()), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().one$mcJ$sp()));
   }
}
