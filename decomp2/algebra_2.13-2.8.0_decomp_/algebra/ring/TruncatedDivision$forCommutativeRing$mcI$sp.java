package algebra.ring;

import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$forCommutativeRing$mcI$sp extends TruncatedDivision.forCommutativeRing, CommutativeRing$mcI$sp, TruncatedDivision$mcI$sp {
   // $FF: synthetic method
   static int fmod$(final TruncatedDivision$forCommutativeRing$mcI$sp $this, final int x, final int y) {
      return $this.fmod(x, y);
   }

   default int fmod(final int x, final int y) {
      return this.fmod$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int fmod$mcI$sp$(final TruncatedDivision$forCommutativeRing$mcI$sp $this, final int x, final int y) {
      return $this.fmod$mcI$sp(x, y);
   }

   default int fmod$mcI$sp(final int x, final int y) {
      int tm = this.tmod$mcI$sp(x, y);
      return this.signum(BoxesRunTime.boxToInteger(tm)) == -this.signum(BoxesRunTime.boxToInteger(y)) ? this.plus$mcI$sp(tm, y) : tm;
   }

   // $FF: synthetic method
   static int fquot$(final TruncatedDivision$forCommutativeRing$mcI$sp $this, final int x, final int y) {
      return $this.fquot(x, y);
   }

   default int fquot(final int x, final int y) {
      return this.fquot$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static int fquot$mcI$sp$(final TruncatedDivision$forCommutativeRing$mcI$sp $this, final int x, final int y) {
      return $this.fquot$mcI$sp(x, y);
   }

   default int fquot$mcI$sp(final int x, final int y) {
      Tuple2 var5 = this.tquotmod$mcI$sp(x, y);
      if (var5 != null) {
         int tq = var5._1$mcI$sp();
         int tm = var5._2$mcI$sp();
         Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(tq, tm);
         int tq = ((Tuple2)var3)._1$mcI$sp();
         int tm = ((Tuple2)var3)._2$mcI$sp();
         return this.signum(BoxesRunTime.boxToInteger(tm)) == -this.signum(BoxesRunTime.boxToInteger(y)) ? this.minus$mcI$sp(tq, this.one$mcI$sp()) : tq;
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$forCommutativeRing$mcI$sp $this, final int x, final int y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final int x, final int y) {
      return this.fquotmod$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcI$sp$(final TruncatedDivision$forCommutativeRing$mcI$sp $this, final int x, final int y) {
      return $this.fquotmod$mcI$sp(x, y);
   }

   default Tuple2 fquotmod$mcI$sp(final int x, final int y) {
      Tuple2 var5 = this.tquotmod$mcI$sp(x, y);
      if (var5 != null) {
         int tq = var5._1$mcI$sp();
         int tm = var5._2$mcI$sp();
         Tuple2.mcII.sp var3 = new Tuple2.mcII.sp(tq, tm);
         int tq = ((Tuple2)var3)._1$mcI$sp();
         int tm = ((Tuple2)var3)._2$mcI$sp();
         boolean signsDiffer = this.signum(BoxesRunTime.boxToInteger(tm)) == -this.signum(BoxesRunTime.boxToInteger(y));
         int fq = signsDiffer ? this.minus$mcI$sp(tq, this.one$mcI$sp()) : tq;
         int fm = signsDiffer ? this.plus$mcI$sp(tm, y) : tm;
         return new Tuple2.mcII.sp(fq, fm);
      } else {
         throw new MatchError(var5);
      }
   }
}
