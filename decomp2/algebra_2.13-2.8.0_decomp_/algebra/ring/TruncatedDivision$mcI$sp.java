package algebra.ring;

import scala.Tuple2;

public interface TruncatedDivision$mcI$sp extends TruncatedDivision, Signed$mcI$sp {
   // $FF: synthetic method
   static Tuple2 tquotmod$(final TruncatedDivision$mcI$sp $this, final int x, final int y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final int x, final int y) {
      return this.tquotmod$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcI$sp$(final TruncatedDivision$mcI$sp $this, final int x, final int y) {
      return $this.tquotmod$mcI$sp(x, y);
   }

   default Tuple2 tquotmod$mcI$sp(final int x, final int y) {
      return new Tuple2.mcII.sp(this.tquot$mcI$sp(x, y), this.tmod$mcI$sp(x, y));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$mcI$sp $this, final int x, final int y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final int x, final int y) {
      return this.fquotmod$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcI$sp$(final TruncatedDivision$mcI$sp $this, final int x, final int y) {
      return $this.fquotmod$mcI$sp(x, y);
   }

   default Tuple2 fquotmod$mcI$sp(final int x, final int y) {
      return new Tuple2.mcII.sp(this.fquot$mcI$sp(x, y), this.fmod$mcI$sp(x, y));
   }
}
