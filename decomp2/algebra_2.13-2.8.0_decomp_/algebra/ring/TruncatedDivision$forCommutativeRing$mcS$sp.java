package algebra.ring;

import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$forCommutativeRing$mcS$sp extends TruncatedDivision.forCommutativeRing, TruncatedDivision$mcS$sp {
   // $FF: synthetic method
   static short fmod$(final TruncatedDivision$forCommutativeRing$mcS$sp $this, final short x, final short y) {
      return $this.fmod(x, y);
   }

   default short fmod(final short x, final short y) {
      return this.fmod$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static short fmod$mcS$sp$(final TruncatedDivision$forCommutativeRing$mcS$sp $this, final short x, final short y) {
      return $this.fmod$mcS$sp(x, y);
   }

   default short fmod$mcS$sp(final short x, final short y) {
      short tm = this.tmod$mcS$sp(x, y);
      return this.signum(BoxesRunTime.boxToShort(tm)) == -this.signum(BoxesRunTime.boxToShort(y)) ? BoxesRunTime.unboxToShort(this.plus(BoxesRunTime.boxToShort(tm), BoxesRunTime.boxToShort(y))) : tm;
   }

   // $FF: synthetic method
   static short fquot$(final TruncatedDivision$forCommutativeRing$mcS$sp $this, final short x, final short y) {
      return $this.fquot(x, y);
   }

   default short fquot(final short x, final short y) {
      return this.fquot$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static short fquot$mcS$sp$(final TruncatedDivision$forCommutativeRing$mcS$sp $this, final short x, final short y) {
      return $this.fquot$mcS$sp(x, y);
   }

   default short fquot$mcS$sp(final short x, final short y) {
      Tuple2 var5 = this.tquotmod$mcS$sp(x, y);
      if (var5 != null) {
         short tq = BoxesRunTime.unboxToShort(var5._1());
         short tm = BoxesRunTime.unboxToShort(var5._2());
         Tuple2 var3 = new Tuple2(BoxesRunTime.boxToShort(tq), BoxesRunTime.boxToShort(tm));
         short tq = BoxesRunTime.unboxToShort(var3._1());
         short tm = BoxesRunTime.unboxToShort(var3._2());
         return this.signum(BoxesRunTime.boxToShort(tm)) == -this.signum(BoxesRunTime.boxToShort(y)) ? BoxesRunTime.unboxToShort(this.minus(BoxesRunTime.boxToShort(tq), this.one())) : tq;
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$forCommutativeRing$mcS$sp $this, final short x, final short y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final short x, final short y) {
      return this.fquotmod$mcS$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcS$sp$(final TruncatedDivision$forCommutativeRing$mcS$sp $this, final short x, final short y) {
      return $this.fquotmod$mcS$sp(x, y);
   }

   default Tuple2 fquotmod$mcS$sp(final short x, final short y) {
      Tuple2 var5 = this.tquotmod$mcS$sp(x, y);
      if (var5 != null) {
         short tq = BoxesRunTime.unboxToShort(var5._1());
         short tm = BoxesRunTime.unboxToShort(var5._2());
         Tuple2 var3 = new Tuple2(BoxesRunTime.boxToShort(tq), BoxesRunTime.boxToShort(tm));
         short tq = BoxesRunTime.unboxToShort(var3._1());
         short tm = BoxesRunTime.unboxToShort(var3._2());
         boolean signsDiffer = this.signum(BoxesRunTime.boxToShort(tm)) == -this.signum(BoxesRunTime.boxToShort(y));
         short fq = signsDiffer ? BoxesRunTime.unboxToShort(this.minus(BoxesRunTime.boxToShort(tq), this.one())) : tq;
         short fm = signsDiffer ? BoxesRunTime.unboxToShort(this.plus(BoxesRunTime.boxToShort(tm), BoxesRunTime.boxToShort(y))) : tm;
         return new Tuple2(BoxesRunTime.boxToShort(fq), BoxesRunTime.boxToShort(fm));
      } else {
         throw new MatchError(var5);
      }
   }
}
