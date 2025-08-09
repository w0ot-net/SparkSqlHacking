package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface RingProduct2$mcFF$sp extends RingProduct2, RngProduct2$mcFF$sp {
   // $FF: synthetic method
   static Tuple2 fromInt$(final RingProduct2$mcFF$sp $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple2 fromInt(final int x0) {
      return this.fromInt$mcFF$sp(x0);
   }

   // $FF: synthetic method
   static Tuple2 fromInt$mcFF$sp$(final RingProduct2$mcFF$sp $this, final int x0) {
      return $this.fromInt$mcFF$sp(x0);
   }

   default Tuple2 fromInt$mcFF$sp(final int x0) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().fromInt$mcF$sp(x0)), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().fromInt$mcF$sp(x0)));
   }

   // $FF: synthetic method
   static Tuple2 one$(final RingProduct2$mcFF$sp $this) {
      return $this.one();
   }

   default Tuple2 one() {
      return this.one$mcFF$sp();
   }

   // $FF: synthetic method
   static Tuple2 one$mcFF$sp$(final RingProduct2$mcFF$sp $this) {
      return $this.one$mcFF$sp();
   }

   default Tuple2 one$mcFF$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().one$mcF$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().one$mcF$sp()));
   }
}
