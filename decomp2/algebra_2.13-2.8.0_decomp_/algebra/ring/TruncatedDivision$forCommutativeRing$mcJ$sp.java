package algebra.ring;

import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$forCommutativeRing$mcJ$sp extends TruncatedDivision.forCommutativeRing, CommutativeRing$mcJ$sp, TruncatedDivision$mcJ$sp {
   // $FF: synthetic method
   static long fmod$(final TruncatedDivision$forCommutativeRing$mcJ$sp $this, final long x, final long y) {
      return $this.fmod(x, y);
   }

   default long fmod(final long x, final long y) {
      return this.fmod$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static long fmod$mcJ$sp$(final TruncatedDivision$forCommutativeRing$mcJ$sp $this, final long x, final long y) {
      return $this.fmod$mcJ$sp(x, y);
   }

   default long fmod$mcJ$sp(final long x, final long y) {
      long tm = this.tmod$mcJ$sp(x, y);
      return this.signum(BoxesRunTime.boxToLong(tm)) == -this.signum(BoxesRunTime.boxToLong(y)) ? this.plus$mcJ$sp(tm, y) : tm;
   }

   // $FF: synthetic method
   static long fquot$(final TruncatedDivision$forCommutativeRing$mcJ$sp $this, final long x, final long y) {
      return $this.fquot(x, y);
   }

   default long fquot(final long x, final long y) {
      return this.fquot$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static long fquot$mcJ$sp$(final TruncatedDivision$forCommutativeRing$mcJ$sp $this, final long x, final long y) {
      return $this.fquot$mcJ$sp(x, y);
   }

   default long fquot$mcJ$sp(final long x, final long y) {
      Tuple2 var7 = this.tquotmod$mcJ$sp(x, y);
      if (var7 != null) {
         long tq = var7._1$mcJ$sp();
         long tm = var7._2$mcJ$sp();
         Tuple2.mcJJ.sp var5 = new Tuple2.mcJJ.sp(tq, tm);
         long tq = ((Tuple2)var5)._1$mcJ$sp();
         long tm = ((Tuple2)var5)._2$mcJ$sp();
         return this.signum(BoxesRunTime.boxToLong(tm)) == -this.signum(BoxesRunTime.boxToLong(y)) ? this.minus$mcJ$sp(tq, this.one$mcJ$sp()) : tq;
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$forCommutativeRing$mcJ$sp $this, final long x, final long y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final long x, final long y) {
      return this.fquotmod$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcJ$sp$(final TruncatedDivision$forCommutativeRing$mcJ$sp $this, final long x, final long y) {
      return $this.fquotmod$mcJ$sp(x, y);
   }

   default Tuple2 fquotmod$mcJ$sp(final long x, final long y) {
      Tuple2 var7 = this.tquotmod$mcJ$sp(x, y);
      if (var7 != null) {
         long tq = var7._1$mcJ$sp();
         long tm = var7._2$mcJ$sp();
         Tuple2.mcJJ.sp var5 = new Tuple2.mcJJ.sp(tq, tm);
         long tq = ((Tuple2)var5)._1$mcJ$sp();
         long tm = ((Tuple2)var5)._2$mcJ$sp();
         boolean signsDiffer = this.signum(BoxesRunTime.boxToLong(tm)) == -this.signum(BoxesRunTime.boxToLong(y));
         long fq = signsDiffer ? this.minus$mcJ$sp(tq, this.one$mcJ$sp()) : tq;
         long fm = signsDiffer ? this.plus$mcJ$sp(tm, y) : tm;
         return new Tuple2.mcJJ.sp(fq, fm);
      } else {
         throw new MatchError(var7);
      }
   }
}
