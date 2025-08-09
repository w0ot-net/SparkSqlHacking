package spire.std;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface SemiringProduct2$mcJF$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcJF$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcJF$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcJF$sp$(final SemiringProduct2$mcJF$sp $this) {
      return $this.zero$mcJF$sp();
   }

   default Tuple2 zero$mcJF$sp() {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().zero$mcJ$sp()), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().zero$mcF$sp()));
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcJF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcJF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcJF$sp$(final SemiringProduct2$mcJF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcJF$sp(x0, x1);
   }

   default Tuple2 plus$mcJF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().plus$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().plus$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcJF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcJF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcJF$sp$(final SemiringProduct2$mcJF$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcJF$sp(x0, x1);
   }

   default Tuple2 times$mcJF$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().times$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp())), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().times$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), BoxesRunTime.unboxToFloat(x1._2()))));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcJF$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcJF$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcJF$sp$(final SemiringProduct2$mcJF$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcJF$sp(x0, x1);
   }

   default Tuple2 pow$mcJF$sp(final Tuple2 x0, final int x1) {
      return new Tuple2(BoxesRunTime.boxToLong(this.structure1$mcJ$sp().pow$mcJ$sp(x0._1$mcJ$sp(), x1)), BoxesRunTime.boxToFloat(this.structure2$mcF$sp().pow$mcF$sp(BoxesRunTime.unboxToFloat(x0._2()), x1)));
   }
}
