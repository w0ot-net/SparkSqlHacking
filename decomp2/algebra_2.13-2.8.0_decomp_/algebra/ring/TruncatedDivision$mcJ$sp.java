package algebra.ring;

import scala.Tuple2;

public interface TruncatedDivision$mcJ$sp extends TruncatedDivision, Signed$mcJ$sp {
   // $FF: synthetic method
   static Tuple2 tquotmod$(final TruncatedDivision$mcJ$sp $this, final long x, final long y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final long x, final long y) {
      return this.tquotmod$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcJ$sp$(final TruncatedDivision$mcJ$sp $this, final long x, final long y) {
      return $this.tquotmod$mcJ$sp(x, y);
   }

   default Tuple2 tquotmod$mcJ$sp(final long x, final long y) {
      return new Tuple2.mcJJ.sp(this.tquot$mcJ$sp(x, y), this.tmod$mcJ$sp(x, y));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$mcJ$sp $this, final long x, final long y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final long x, final long y) {
      return this.fquotmod$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcJ$sp$(final TruncatedDivision$mcJ$sp $this, final long x, final long y) {
      return $this.fquotmod$mcJ$sp(x, y);
   }

   default Tuple2 fquotmod$mcJ$sp(final long x, final long y) {
      return new Tuple2.mcJJ.sp(this.fquot$mcJ$sp(x, y), this.fmod$mcJ$sp(x, y));
   }
}
