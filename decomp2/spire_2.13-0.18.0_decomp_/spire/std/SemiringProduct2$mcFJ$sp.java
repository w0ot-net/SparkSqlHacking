package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemiringProduct2$mcFJ$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcFJ$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcFJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcFJ$sp$(final SemiringProduct2$mcFJ$sp $this) {
      return $this.zero$mcFJ$sp();
   }

   default Tuple2 zero$mcFJ$sp() {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().zero$mcF$sp()), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().zero$mcJ$sp()));
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcFJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcFJ$sp$(final SemiringProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcFJ$sp(x0, x1);
   }

   default Tuple2 plus$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().plus$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().plus$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp())));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcFJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcFJ$sp$(final SemiringProduct2$mcFJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcFJ$sp(x0, x1);
   }

   default Tuple2 times$mcFJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), BoxesRunTime.unboxToFloat(x1._1()))), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().times$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp())));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcFJ$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcFJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcFJ$sp$(final SemiringProduct2$mcFJ$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcFJ$sp(x0, x1);
   }

   default Tuple2 pow$mcFJ$sp(final Tuple2 x0, final int x1) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.structure1$mcF$sp().pow$mcF$sp(BoxesRunTime.unboxToFloat(x0._1()), x1)), BoxesRunTime.boxToLong(this.structure2$mcJ$sp().pow$mcJ$sp(x0._2$mcJ$sp(), x1)));
   }
}
