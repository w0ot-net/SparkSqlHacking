package algebra.ring;

import scala.Tuple2;

public interface TruncatedDivision$mcD$sp extends TruncatedDivision, Signed$mcD$sp {
   // $FF: synthetic method
   static Tuple2 tquotmod$(final TruncatedDivision$mcD$sp $this, final double x, final double y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final double x, final double y) {
      return this.tquotmod$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcD$sp$(final TruncatedDivision$mcD$sp $this, final double x, final double y) {
      return $this.tquotmod$mcD$sp(x, y);
   }

   default Tuple2 tquotmod$mcD$sp(final double x, final double y) {
      return new Tuple2.mcDD.sp(this.tquot$mcD$sp(x, y), this.tmod$mcD$sp(x, y));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$mcD$sp $this, final double x, final double y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final double x, final double y) {
      return this.fquotmod$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcD$sp$(final TruncatedDivision$mcD$sp $this, final double x, final double y) {
      return $this.fquotmod$mcD$sp(x, y);
   }

   default Tuple2 fquotmod$mcD$sp(final double x, final double y) {
      return new Tuple2.mcDD.sp(this.fquot$mcD$sp(x, y), this.fmod$mcD$sp(x, y));
   }
}
