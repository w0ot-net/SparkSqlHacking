package algebra.ring;

import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$forCommutativeRing$mcF$sp extends TruncatedDivision.forCommutativeRing, CommutativeRing$mcF$sp, TruncatedDivision$mcF$sp {
   // $FF: synthetic method
   static float fmod$(final TruncatedDivision$forCommutativeRing$mcF$sp $this, final float x, final float y) {
      return $this.fmod(x, y);
   }

   default float fmod(final float x, final float y) {
      return this.fmod$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static float fmod$mcF$sp$(final TruncatedDivision$forCommutativeRing$mcF$sp $this, final float x, final float y) {
      return $this.fmod$mcF$sp(x, y);
   }

   default float fmod$mcF$sp(final float x, final float y) {
      float tm = this.tmod$mcF$sp(x, y);
      return this.signum(BoxesRunTime.boxToFloat(tm)) == -this.signum(BoxesRunTime.boxToFloat(y)) ? this.plus$mcF$sp(tm, y) : tm;
   }

   // $FF: synthetic method
   static float fquot$(final TruncatedDivision$forCommutativeRing$mcF$sp $this, final float x, final float y) {
      return $this.fquot(x, y);
   }

   default float fquot(final float x, final float y) {
      return this.fquot$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static float fquot$mcF$sp$(final TruncatedDivision$forCommutativeRing$mcF$sp $this, final float x, final float y) {
      return $this.fquot$mcF$sp(x, y);
   }

   default float fquot$mcF$sp(final float x, final float y) {
      Tuple2 var5 = this.tquotmod$mcF$sp(x, y);
      if (var5 != null) {
         float tq = BoxesRunTime.unboxToFloat(var5._1());
         float tm = BoxesRunTime.unboxToFloat(var5._2());
         Tuple2 var3 = new Tuple2(BoxesRunTime.boxToFloat(tq), BoxesRunTime.boxToFloat(tm));
         float tq = BoxesRunTime.unboxToFloat(var3._1());
         float tm = BoxesRunTime.unboxToFloat(var3._2());
         return this.signum(BoxesRunTime.boxToFloat(tm)) == -this.signum(BoxesRunTime.boxToFloat(y)) ? this.minus$mcF$sp(tq, this.one$mcF$sp()) : tq;
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$forCommutativeRing$mcF$sp $this, final float x, final float y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final float x, final float y) {
      return this.fquotmod$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcF$sp$(final TruncatedDivision$forCommutativeRing$mcF$sp $this, final float x, final float y) {
      return $this.fquotmod$mcF$sp(x, y);
   }

   default Tuple2 fquotmod$mcF$sp(final float x, final float y) {
      Tuple2 var5 = this.tquotmod$mcF$sp(x, y);
      if (var5 != null) {
         float tq = BoxesRunTime.unboxToFloat(var5._1());
         float tm = BoxesRunTime.unboxToFloat(var5._2());
         Tuple2 var3 = new Tuple2(BoxesRunTime.boxToFloat(tq), BoxesRunTime.boxToFloat(tm));
         float tq = BoxesRunTime.unboxToFloat(var3._1());
         float tm = BoxesRunTime.unboxToFloat(var3._2());
         boolean signsDiffer = this.signum(BoxesRunTime.boxToFloat(tm)) == -this.signum(BoxesRunTime.boxToFloat(y));
         float fq = signsDiffer ? this.minus$mcF$sp(tq, this.one$mcF$sp()) : tq;
         float fm = signsDiffer ? this.plus$mcF$sp(tm, y) : tm;
         return new Tuple2(BoxesRunTime.boxToFloat(fq), BoxesRunTime.boxToFloat(fm));
      } else {
         throw new MatchError(var5);
      }
   }
}
