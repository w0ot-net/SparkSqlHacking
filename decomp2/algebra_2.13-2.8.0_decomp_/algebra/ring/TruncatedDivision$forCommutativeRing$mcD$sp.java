package algebra.ring;

import scala.MatchError;
import scala.Tuple2;
import scala.runtime.BoxesRunTime;

public interface TruncatedDivision$forCommutativeRing$mcD$sp extends TruncatedDivision.forCommutativeRing, CommutativeRing$mcD$sp, TruncatedDivision$mcD$sp {
   // $FF: synthetic method
   static double fmod$(final TruncatedDivision$forCommutativeRing$mcD$sp $this, final double x, final double y) {
      return $this.fmod(x, y);
   }

   default double fmod(final double x, final double y) {
      return this.fmod$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static double fmod$mcD$sp$(final TruncatedDivision$forCommutativeRing$mcD$sp $this, final double x, final double y) {
      return $this.fmod$mcD$sp(x, y);
   }

   default double fmod$mcD$sp(final double x, final double y) {
      double tm = this.tmod$mcD$sp(x, y);
      return this.signum(BoxesRunTime.boxToDouble(tm)) == -this.signum(BoxesRunTime.boxToDouble(y)) ? this.plus$mcD$sp(tm, y) : tm;
   }

   // $FF: synthetic method
   static double fquot$(final TruncatedDivision$forCommutativeRing$mcD$sp $this, final double x, final double y) {
      return $this.fquot(x, y);
   }

   default double fquot(final double x, final double y) {
      return this.fquot$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static double fquot$mcD$sp$(final TruncatedDivision$forCommutativeRing$mcD$sp $this, final double x, final double y) {
      return $this.fquot$mcD$sp(x, y);
   }

   default double fquot$mcD$sp(final double x, final double y) {
      Tuple2 var7 = this.tquotmod$mcD$sp(x, y);
      if (var7 != null) {
         double tq = var7._1$mcD$sp();
         double tm = var7._2$mcD$sp();
         Tuple2.mcDD.sp var5 = new Tuple2.mcDD.sp(tq, tm);
         double tq = ((Tuple2)var5)._1$mcD$sp();
         double tm = ((Tuple2)var5)._2$mcD$sp();
         return this.signum(BoxesRunTime.boxToDouble(tm)) == -this.signum(BoxesRunTime.boxToDouble(y)) ? this.minus$mcD$sp(tq, this.one$mcD$sp()) : tq;
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision$forCommutativeRing$mcD$sp $this, final double x, final double y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final double x, final double y) {
      return this.fquotmod$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcD$sp$(final TruncatedDivision$forCommutativeRing$mcD$sp $this, final double x, final double y) {
      return $this.fquotmod$mcD$sp(x, y);
   }

   default Tuple2 fquotmod$mcD$sp(final double x, final double y) {
      Tuple2 var7 = this.tquotmod$mcD$sp(x, y);
      if (var7 != null) {
         double tq = var7._1$mcD$sp();
         double tm = var7._2$mcD$sp();
         Tuple2.mcDD.sp var5 = new Tuple2.mcDD.sp(tq, tm);
         double tq = ((Tuple2)var5)._1$mcD$sp();
         double tm = ((Tuple2)var5)._2$mcD$sp();
         boolean signsDiffer = this.signum(BoxesRunTime.boxToDouble(tm)) == -this.signum(BoxesRunTime.boxToDouble(y));
         double fq = signsDiffer ? this.minus$mcD$sp(tq, this.one$mcD$sp()) : tq;
         double fm = signsDiffer ? this.plus$mcD$sp(tm, y) : tm;
         return new Tuple2.mcDD.sp(fq, fm);
      } else {
         throw new MatchError(var7);
      }
   }
}
