package algebra.ring;

import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$forCommutativeRing$mcB$sp extends TruncatedDivision.forCommutativeRing, TruncatedDivision$mcB$sp {
   // $FF: synthetic method
   static byte fmod$(final TruncatedDivision$forCommutativeRing$mcB$sp $this, final byte x, final byte y) {
      return $this.fmod(x, y);
   }

   default byte fmod(final byte x, final byte y) {
      return this.fmod$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static byte fmod$mcB$sp$(final TruncatedDivision$forCommutativeRing$mcB$sp $this, final byte x, final byte y) {
      return $this.fmod$mcB$sp(x, y);
   }

   default byte fmod$mcB$sp(final byte x, final byte y) {
      byte tm = this.tmod$mcB$sp(x, y);
      return this.signum(BoxesRunTime.boxToByte(tm)) == -this.signum(BoxesRunTime.boxToByte(y)) ? BoxesRunTime.unboxToByte(this.plus(BoxesRunTime.boxToByte(tm), BoxesRunTime.boxToByte(y))) : tm;
   }

   // $FF: synthetic method
   static byte fquot$(final TruncatedDivision$forCommutativeRing$mcB$sp $this, final byte x, final byte y) {
      return $this.fquot(x, y);
   }

   default byte fquot(final byte x, final byte y) {
      return this.fquot$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static byte fquot$mcB$sp$(final TruncatedDivision$forCommutativeRing$mcB$sp $this, final byte x, final byte y) {
      return $this.fquot$mcB$sp(x, y);
   }

   default byte fquot$mcB$sp(final byte x, final byte y) {
      Tuple2 var5 = this.tquotmod$mcB$sp(x, y);
      if (var5 != null) {
         byte tq = BoxesRunTime.unboxToByte(var5._1());
         byte tm = BoxesRunTime.unboxToByte(var5._2());
         Tuple2 var3 = new Tuple2(BoxesRunTime.boxToByte(tq), BoxesRunTime.boxToByte(tm));
         byte tq = BoxesRunTime.unboxToByte(var3._1());
         byte tm = BoxesRunTime.unboxToByte(var3._2());
         return this.signum(BoxesRunTime.boxToByte(tm)) == -this.signum(BoxesRunTime.boxToByte(y)) ? BoxesRunTime.unboxToByte(this.minus(BoxesRunTime.boxToByte(tq), this.one())) : tq;
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$forCommutativeRing$mcB$sp $this, final byte x, final byte y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final byte x, final byte y) {
      return this.fquotmod$mcB$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcB$sp$(final TruncatedDivision$forCommutativeRing$mcB$sp $this, final byte x, final byte y) {
      return $this.fquotmod$mcB$sp(x, y);
   }

   default Tuple2 fquotmod$mcB$sp(final byte x, final byte y) {
      Tuple2 var5 = this.tquotmod$mcB$sp(x, y);
      if (var5 != null) {
         byte tq = BoxesRunTime.unboxToByte(var5._1());
         byte tm = BoxesRunTime.unboxToByte(var5._2());
         Tuple2 var3 = new Tuple2(BoxesRunTime.boxToByte(tq), BoxesRunTime.boxToByte(tm));
         byte tq = BoxesRunTime.unboxToByte(var3._1());
         byte tm = BoxesRunTime.unboxToByte(var3._2());
         boolean signsDiffer = this.signum(BoxesRunTime.boxToByte(tm)) == -this.signum(BoxesRunTime.boxToByte(y));
         byte fq = signsDiffer ? BoxesRunTime.unboxToByte(this.minus(BoxesRunTime.boxToByte(tq), this.one())) : tq;
         byte fm = signsDiffer ? BoxesRunTime.unboxToByte(this.plus(BoxesRunTime.boxToByte(tm), BoxesRunTime.boxToByte(y))) : tm;
         return new Tuple2(BoxesRunTime.boxToByte(fq), BoxesRunTime.boxToByte(fm));
      } else {
         throw new MatchError(var5);
      }
   }
}
