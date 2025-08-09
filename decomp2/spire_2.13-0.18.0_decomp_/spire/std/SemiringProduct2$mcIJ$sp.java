package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcIJ$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcIJ$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcIJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcIJ$sp$(final SemiringProduct2$mcIJ$sp $this) {
      return $this.zero$mcIJ$sp();
   }

   default Tuple2 zero$mcIJ$sp() {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().zero$mcI$sp(), this.structure2$mcJ$sp().zero$mcJ$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcIJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcIJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcIJ$sp$(final SemiringProduct2$mcIJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcIJ$sp(x0, x1);
   }

   default Tuple2 plus$mcIJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().plus$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()), this.structure2$mcJ$sp().plus$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcIJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcIJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcIJ$sp$(final SemiringProduct2$mcIJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcIJ$sp(x0, x1);
   }

   default Tuple2 times$mcIJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().times$mcI$sp(x0._1$mcI$sp(), x1._1$mcI$sp()), this.structure2$mcJ$sp().times$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcIJ$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcIJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcIJ$sp$(final SemiringProduct2$mcIJ$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcIJ$sp(x0, x1);
   }

   default Tuple2 pow$mcIJ$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcIJ.sp(this.structure1$mcI$sp().pow$mcI$sp(x0._1$mcI$sp(), x1), this.structure2$mcJ$sp().pow$mcJ$sp(x0._2$mcJ$sp(), x1));
   }
}
