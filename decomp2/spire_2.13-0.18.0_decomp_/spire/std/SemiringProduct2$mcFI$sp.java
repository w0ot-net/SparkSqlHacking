package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemiringProduct2$mcFI$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcFI$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcFI$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcFI$sp$(final SemiringProduct2$mcFI$sp $this) {
      return $this.zero$mcFI$sp();
   }

   default Tuple2 zero$mcFI$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().zero$mcF$sp()), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().zero$mcI$sp()));
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcFI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcFI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcFI$sp$(final SemiringProduct2$mcFI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcFI$sp(x0, x1);
   }

   default Tuple2 plus$mcFI$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().plus$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().plus$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp())));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcFI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcFI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcFI$sp$(final SemiringProduct2$mcFI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcFI$sp(x0, x1);
   }

   default Tuple2 times$mcFI$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().times$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp())));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcFI$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcFI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcFI$sp$(final SemiringProduct2$mcFI$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcFI$sp(x0, x1);
   }

   default Tuple2 pow$mcFI$sp(final Tuple2 x0, final int x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().pow$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), x1)), BoxesRunTime.boxToInteger(this.structure2$mcI$sp().pow$mcI$sp(x0._2$mcI$sp(), x1)));
   }
}
