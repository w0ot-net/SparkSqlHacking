package algebra.ring;

import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$mcF$sp extends TruncatedDivision, Signed$mcF$sp {
   // $FF: synthetic method
   static Tuple2 tquotmod$(final TruncatedDivision$mcF$sp $this, final float x, final float y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final float x, final float y) {
      return this.tquotmod$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcF$sp$(final TruncatedDivision$mcF$sp $this, final float x, final float y) {
      return $this.tquotmod$mcF$sp(x, y);
   }

   default Tuple2 tquotmod$mcF$sp(final float x, final float y) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.tquot$mcF$sp(x, y)), BoxesRunTime.boxToFloat(this.tmod$mcF$sp(x, y)));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$mcF$sp $this, final float x, final float y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final float x, final float y) {
      return this.fquotmod$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcF$sp$(final TruncatedDivision$mcF$sp $this, final float x, final float y) {
      return $this.fquotmod$mcF$sp(x, y);
   }

   default Tuple2 fquotmod$mcF$sp(final float x, final float y) {
      return new Tuple2(BoxesRunTime.boxToFloat(this.fquot$mcF$sp(x, y)), BoxesRunTime.boxToFloat(this.fmod$mcF$sp(x, y)));
   }
}
