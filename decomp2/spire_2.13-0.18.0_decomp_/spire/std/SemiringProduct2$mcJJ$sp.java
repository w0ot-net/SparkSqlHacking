package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcJJ$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcJJ$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcJJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcJJ$sp$(final SemiringProduct2$mcJJ$sp $this) {
      return $this.zero$mcJJ$sp();
   }

   default Tuple2 zero$mcJJ$sp() {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().zero$mcJ$sp(), this.structure2$mcJ$sp().zero$mcJ$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcJJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcJJ$sp$(final SemiringProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcJJ$sp(x0, x1);
   }

   default Tuple2 plus$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().plus$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()), this.structure2$mcJ$sp().plus$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcJJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcJJ$sp$(final SemiringProduct2$mcJJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcJJ$sp(x0, x1);
   }

   default Tuple2 times$mcJJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().times$mcJ$sp(x0._1$mcJ$sp(), x1._1$mcJ$sp()), this.structure2$mcJ$sp().times$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcJJ$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcJJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcJJ$sp$(final SemiringProduct2$mcJJ$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcJJ$sp(x0, x1);
   }

   default Tuple2 pow$mcJJ$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcJJ.sp(this.structure1$mcJ$sp().pow$mcJ$sp(x0._1$mcJ$sp(), x1), this.structure2$mcJ$sp().pow$mcJ$sp(x0._2$mcJ$sp(), x1));
   }
}
