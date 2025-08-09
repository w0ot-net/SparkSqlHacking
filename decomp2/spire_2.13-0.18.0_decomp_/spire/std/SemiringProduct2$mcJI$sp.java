package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcJI$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcJI$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcJI$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcJI$sp$(final SemiringProduct2$mcJI$sp $this) {
      return $this.zero$mcJI$sp();
   }

   default Tuple2 zero$mcJI$sp() {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().zero$mcJ$sp(), this.structure2$mcI$sp().zero$mcI$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcJI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcJI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcJI$sp$(final SemiringProduct2$mcJI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcJI$sp(x0, x1);
   }

   default Tuple2 plus$mcJI$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().plus$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()), this.structure2$mcI$sp().plus$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcJI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcJI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcJI$sp$(final SemiringProduct2$mcJI$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcJI$sp(x0, x1);
   }

   default Tuple2 times$mcJI$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().times$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()), this.structure2$mcI$sp().times$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcJI$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcJI$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcJI$sp$(final SemiringProduct2$mcJI$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcJI$sp(x0, x1);
   }

   default Tuple2 pow$mcJI$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcJI.sp(this.structure1$mcJ$sp().pow$mcJ$sp(x0._1$mcJ$sp(), x1), this.structure2$mcI$sp().pow$mcI$sp(x0._2$mcI$sp(), x1));
   }
}
