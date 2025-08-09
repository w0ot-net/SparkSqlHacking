package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua!\u0002\r\u001a\u0001})\u0003\u0002C\u001d\u0001\u0005\u000b\u0007I\u0011\u0001\u001e\t\u0011y\u0002!\u0011!Q\u0001\nmB\u0001b\u0010\u0001\u0003\u0006\u0004%\tA\u000f\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005w!A\u0011\t\u0001BC\u0002\u0013\u0005!\t\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003D\u0011!I\u0005A!b\u0001\n\u0003\u0011\u0005\u0002\u0003&\u0001\u0005\u0003\u0005\u000b\u0011B\"\t\u0011-\u0003!Q1A\u0005\u0002\tC\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006Ia\u0011\u0005\t\u001b\u0002\u0011)\u0019!C\u0001\u001d\"A!\u000b\u0001B\u0001B\u0003%q\nC\u0003T\u0001\u0011\u0005A\u000bC\u0003^\u0001\u0011\u0005c\fC\u0003h\u0001\u0011\u0005!\bC\u0003i\u0001\u0011\u0005!h\u0002\u0004j3!\u0005qD\u001b\u0004\u00071eA\taH6\t\u000bM\u0013B\u0011A:\t\u000bQ\u0014B\u0011A;\t\u000b]\u0014B\u0011\u0001=\t\u000fi\u0014\u0012\u0013!C\u0001w\"I\u0011Q\u0002\n\u0002\u0002\u0013%\u0011q\u0002\u0002\u000e\u00136\u0004XO]5usN#\u0018\r^:\u000b\u0005iY\u0012!B7pI\u0016d'B\u0001\u000f\u001e\u0003\u0011!(/Z3\u000b\u0005yy\u0012!B7mY&\u0014'B\u0001\u0011\"\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00113%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002I\u0005\u0019qN]4\u0014\u0007\u00011C\u0006\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VM\u001a\t\u0003[Yr!A\f\u001b\u000f\u0005=\u001aT\"\u0001\u0019\u000b\u0005E\u0012\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003%J!!\u000e\u0015\u0002\u000fA\f7m[1hK&\u0011q\u0007\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003k!\nAaZ1j]V\t1\b\u0005\u0002(y%\u0011Q\b\u000b\u0002\u0007\t>,(\r\\3\u0002\u000b\u001d\f\u0017N\u001c\u0011\u0002\u0011%l\u0007/\u001e:jif\f\u0011\"[7qkJLG/\u001f\u0011\u0002%%l\u0007/\u001e:jif\u001c\u0015\r\\2vY\u0006$xN]\u000b\u0002\u0007B\u0011AIR\u0007\u0002\u000b*\u0011qhG\u0005\u0003\u000f\u0016\u0013!#S7qkJLG/_\"bY\u000e,H.\u0019;pe\u0006\u0019\u0012.\u001c9ve&$\u0018pQ1mGVd\u0017\r^8sA\u00051B.\u001a4u\u00136\u0004XO]5us\u000e\u000bGnY;mCR|'/A\fmK\u001a$\u0018*\u001c9ve&$\u0018pQ1mGVd\u0017\r^8sA\u00059\"/[4ii&k\u0007/\u001e:jif\u001c\u0015\r\\2vY\u0006$xN]\u0001\u0019e&<\u0007\u000e^%naV\u0014\u0018\u000e^=DC2\u001cW\u000f\\1u_J\u0004\u0013!\u0002<bY&$W#A(\u0011\u0005\u001d\u0002\u0016BA))\u0005\u001d\u0011un\u001c7fC:\faA^1mS\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0004V/bK&l\u0017/\u0011\u0005Y\u0003Q\"A\r\t\u000bej\u0001\u0019A\u001e\t\u000b}j\u0001\u0019A\u001e\t\u000b\u0005k\u0001\u0019A\"\t\u000b%k\u0001\u0019A\"\t\u000b-k\u0001\u0019A\"\t\u000f5k\u0001\u0013!a\u0001\u001f\u0006AAo\\*ue&tw\rF\u0001`!\t\u0001GM\u0004\u0002bEB\u0011q\u0006K\u0005\u0003G\"\na\u0001\u0015:fI\u00164\u0017BA3g\u0005\u0019\u0019FO]5oO*\u00111\rK\u0001\rY\u00164G/S7qkJLG/_\u0001\u000ee&<\u0007\u000e^%naV\u0014\u0018\u000e^=\u0002\u001b%k\u0007/\u001e:jif\u001cF/\u0019;t!\t1&cE\u0002\u0013M1\u0004\"!\u001c:\u000e\u00039T!a\u001c9\u0002\u0005%|'\"A9\u0002\t)\fg/Y\u0005\u0003o9$\u0012A[\u0001\u0018O\u0016$\u0018J\u001c<bY&$\u0017*\u001c9ve&$\u0018p\u0015;biN$\"!\u0016<\t\u000b\u0005#\u0002\u0019A\"\u0002+\u001d,G/R7qifLU\u000e];sSRL8\u000b^1ugR\u0011Q+\u001f\u0005\u0006\u0003V\u0001\raQ\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001c\u0016\u0003qT#aT?,\u0003y\u00042a`A\u0005\u001b\t\t\tA\u0003\u0003\u0002\u0004\u0005\u0015\u0011!C;oG\",7m[3e\u0015\r\t9\u0001K\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0006\u0003\u0003\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0002\u0005\u0003\u0002\u0014\u0005eQBAA\u000b\u0015\r\t9\u0002]\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u001c\u0005U!AB(cU\u0016\u001cG\u000f"
)
public class ImpurityStats implements Serializable {
   private final double gain;
   private final double impurity;
   private final ImpurityCalculator impurityCalculator;
   private final ImpurityCalculator leftImpurityCalculator;
   private final ImpurityCalculator rightImpurityCalculator;
   private final boolean valid;

   public static boolean $lessinit$greater$default$6() {
      return ImpurityStats$.MODULE$.$lessinit$greater$default$6();
   }

   public static ImpurityStats getEmptyImpurityStats(final ImpurityCalculator impurityCalculator) {
      return ImpurityStats$.MODULE$.getEmptyImpurityStats(impurityCalculator);
   }

   public static ImpurityStats getInvalidImpurityStats(final ImpurityCalculator impurityCalculator) {
      return ImpurityStats$.MODULE$.getInvalidImpurityStats(impurityCalculator);
   }

   public double gain() {
      return this.gain;
   }

   public double impurity() {
      return this.impurity;
   }

   public ImpurityCalculator impurityCalculator() {
      return this.impurityCalculator;
   }

   public ImpurityCalculator leftImpurityCalculator() {
      return this.leftImpurityCalculator;
   }

   public ImpurityCalculator rightImpurityCalculator() {
      return this.rightImpurityCalculator;
   }

   public boolean valid() {
      return this.valid;
   }

   public String toString() {
      double var10000 = this.gain();
      return "gain = " + var10000 + ", impurity = " + this.impurity() + ", left impurity = " + this.leftImpurity() + ", right impurity = " + this.rightImpurity();
   }

   public double leftImpurity() {
      return this.leftImpurityCalculator() != null ? this.leftImpurityCalculator().calculate() : (double)-1.0F;
   }

   public double rightImpurity() {
      return this.rightImpurityCalculator() != null ? this.rightImpurityCalculator().calculate() : (double)-1.0F;
   }

   public ImpurityStats(final double gain, final double impurity, final ImpurityCalculator impurityCalculator, final ImpurityCalculator leftImpurityCalculator, final ImpurityCalculator rightImpurityCalculator, final boolean valid) {
      this.gain = gain;
      this.impurity = impurity;
      this.impurityCalculator = impurityCalculator;
      this.leftImpurityCalculator = leftImpurityCalculator;
      this.rightImpurityCalculator = rightImpurityCalculator;
      this.valid = valid;
   }
}
