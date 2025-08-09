package algebra.ring;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$mcB$sp extends TruncatedDivision, Signed$mcB$sp {
   // $FF: synthetic method
   static Tuple2 tquotmod$(final TruncatedDivision$mcB$sp $this, final byte x, final byte y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final byte x, final byte y) {
      return this.tquotmod$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcB$sp$(final TruncatedDivision$mcB$sp $this, final byte x, final byte y) {
      return $this.tquotmod$mcB$sp(x, y);
   }

   default Tuple2 tquotmod$mcB$sp(final byte x, final byte y) {
      return new Tuple2(BoxesRunTime.boxToByte(this.tquot$mcB$sp(x, y)), BoxesRunTime.boxToByte(this.tmod$mcB$sp(x, y)));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$mcB$sp $this, final byte x, final byte y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final byte x, final byte y) {
      return this.fquotmod$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcB$sp$(final TruncatedDivision$mcB$sp $this, final byte x, final byte y) {
      return $this.fquotmod$mcB$sp(x, y);
   }

   default Tuple2 fquotmod$mcB$sp(final byte x, final byte y) {
      return new Tuple2(BoxesRunTime.boxToByte(this.fquot$mcB$sp(x, y)), BoxesRunTime.boxToByte(this.fmod$mcB$sp(x, y)));
   }
}
