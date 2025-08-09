package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcII$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcII$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcII$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcII$sp$(final SemiringProduct2$mcII$sp $this) {
      return $this.zero$mcII$sp();
   }

   default Tuple2 zero$mcII$sp() {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().zero$mcI$sp(), this.structure2$mcI$sp().zero$mcI$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcII$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcII$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcII$sp$(final SemiringProduct2$mcII$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcII$sp(x0, x1);
   }

   default Tuple2 plus$mcII$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().plus$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()), this.structure2$mcI$sp().plus$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcII$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcII$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcII$sp$(final SemiringProduct2$mcII$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcII$sp(x0, x1);
   }

   default Tuple2 times$mcII$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().times$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()), this.structure2$mcI$sp().times$mcI$sp(x0._2$mcI$sp(), x1._2$mcI$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcII$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcII$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcII$sp$(final SemiringProduct2$mcII$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcII$sp(x0, x1);
   }

   default Tuple2 pow$mcII$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcII.sp(this.structure1$mcI$sp().pow$mcI$sp(x0._1$mcI$sp(), x1), this.structure2$mcI$sp().pow$mcI$sp(x0._2$mcI$sp(), x1));
   }
}
