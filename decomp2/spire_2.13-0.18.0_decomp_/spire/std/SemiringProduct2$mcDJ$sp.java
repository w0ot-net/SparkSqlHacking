package spire.std;

import scala.Tuple2;

public interface SemiringProduct2$mcDJ$sp extends SemiringProduct2 {
   // $FF: synthetic method
   static Tuple2 zero$(final SemiringProduct2$mcDJ$sp $this) {
      return $this.zero();
   }

   default Tuple2 zero() {
      return this.zero$mcDJ$sp();
   }

   // $FF: synthetic method
   static Tuple2 zero$mcDJ$sp$(final SemiringProduct2$mcDJ$sp $this) {
      return $this.zero$mcDJ$sp();
   }

   default Tuple2 zero$mcDJ$sp() {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().zero$mcD$sp(), this.structure2$mcJ$sp().zero$mcJ$sp());
   }

   // $FF: synthetic method
   static Tuple2 plus$(final SemiringProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple2 plus(final Tuple2 x0, final Tuple2 x1) {
      return this.plus$mcDJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 plus$mcDJ$sp$(final SemiringProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.plus$mcDJ$sp(x0, x1);
   }

   default Tuple2 plus$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().plus$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()), this.structure2$mcJ$sp().plus$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp()));
   }

   // $FF: synthetic method
   static Tuple2 times$(final SemiringProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times(x0, x1);
   }

   default Tuple2 times(final Tuple2 x0, final Tuple2 x1) {
      return this.times$mcDJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 times$mcDJ$sp$(final SemiringProduct2$mcDJ$sp $this, final Tuple2 x0, final Tuple2 x1) {
      return $this.times$mcDJ$sp(x0, x1);
   }

   default Tuple2 times$mcDJ$sp(final Tuple2 x0, final Tuple2 x1) {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().times$mcD$sp(x0._1$mcD$sp(), x1._1$mcD$sp()), this.structure2$mcJ$sp().times$mcJ$sp(x0._2$mcJ$sp(), x1._2$mcJ$sp()));
   }

   // $FF: synthetic method
   static Tuple2 pow$(final SemiringProduct2$mcDJ$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple2 pow(final Tuple2 x0, final int x1) {
      return this.pow$mcDJ$sp(x0, x1);
   }

   // $FF: synthetic method
   static Tuple2 pow$mcDJ$sp$(final SemiringProduct2$mcDJ$sp $this, final Tuple2 x0, final int x1) {
      return $this.pow$mcDJ$sp(x0, x1);
   }

   default Tuple2 pow$mcDJ$sp(final Tuple2 x0, final int x1) {
      return new Tuple2.mcDJ.sp(this.structure1$mcD$sp().pow$mcD$sp(x0._1$mcD$sp(), x1), this.structure2$mcJ$sp().pow$mcJ$sp(x0._2$mcJ$sp(), x1));
   }
}
