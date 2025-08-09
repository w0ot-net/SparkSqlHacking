package algebra.ring;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$mcS$sp extends TruncatedDivision, Signed$mcS$sp {
   // $FF: synthetic method
   static Tuple2 tquotmod$(final TruncatedDivision$mcS$sp $this, final short x, final short y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final short x, final short y) {
      return this.tquotmod$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcS$sp$(final TruncatedDivision$mcS$sp $this, final short x, final short y) {
      return $this.tquotmod$mcS$sp(x, y);
   }

   default Tuple2 tquotmod$mcS$sp(final short x, final short y) {
      return new Tuple2(BoxesRunTime.boxToShort(this.tquot$mcS$sp(x, y)), BoxesRunTime.boxToShort(this.tmod$mcS$sp(x, y)));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$mcS$sp $this, final short x, final short y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final short x, final short y) {
      return this.fquotmod$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcS$sp$(final TruncatedDivision$mcS$sp $this, final short x, final short y) {
      return $this.fquotmod$mcS$sp(x, y);
   }

   default Tuple2 fquotmod$mcS$sp(final short x, final short y) {
      return new Tuple2(BoxesRunTime.boxToShort(this.fquot$mcS$sp(x, y)), BoxesRunTime.boxToShort(this.fmod$mcS$sp(x, y)));
   }
}
