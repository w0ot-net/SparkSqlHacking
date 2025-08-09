package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.norm$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EeaB\n\u0015!\u0003\r\t!\u0007\u0005\u0006%\u0002!\ta\u0015\u0005\u0006/\u00021\t\u0001\u0017\u0005\u0006;\u0002!\tA\u0018\u0005\u0006C\u00021\tA\u0019\u0005\bK\u0002\u0011\rQb\u0001g\u0011\u0015I\b\u0001\"\u0001{\u000f\u0015aH\u0003#\u0001~\r\u0015\u0019B\u0003#\u0001\u007f\u0011\u001d\ty\u0001\u0003C\u0001\u0003#Aq!a\u0005\t\t\u0007\t)\u0002C\u0004\u0002\u001a!!\u0019!a\u0007\t\u000f\u0005\u0015\u0002\u0002b\u0001\u0002(!9\u0011\u0011\u0007\u0005\u0005\u0004\u0005M\u0002bBA\u001f\u0011\u0011\r\u0011q\b\u0005\b\u0003#BA1AA*\u0011\u001d\ti\u0006\u0003C\u0002\u0003?Bq!!\u001b\t\t\u0007\tY\u0007C\u0005\u0002\u0002\"\t\t\u0011\"\u0003\u0002\u0004\n!!+\u001b8h\u0015\t)b#\u0001\u0003nCRD'\"A\f\u0002\r\t\u0014X-\u001a>f\u0007\u0001)\"AG\u0014\u0014\u0007\u0001Y\u0012\u0005\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VM\u001a\t\u0004E\r*S\"\u0001\u000b\n\u0005\u0011\"\"\u0001C*f[&\u0014\u0018N\\4\u0011\u0005\u0019:C\u0002\u0001\u0003\nQ\u0001\u0001\u000b\u0011!AC\u0002%\u0012\u0011AV\t\u0003U5\u0002\"\u0001H\u0016\n\u00051j\"a\u0002(pi\"Lgn\u001a\t\u000399J!aL\u000f\u0003\u0007\u0005s\u0017\u0010K\u0004(cQr4\tS'\u0011\u0005q\u0011\u0014BA\u001a\u001e\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r*d\u0007O\u001c\u000f\u0005q1\u0014BA\u001c\u001e\u0003\rIe\u000e^\u0019\u0005IejdD\u0004\u0002;{5\t1H\u0003\u0002=1\u00051AH]8pizJ\u0011AH\u0019\u0006G}\u0002%)\u0011\b\u00039\u0001K!!Q\u000f\u0002\u000bMCwN\u001d;2\t\u0011JTHH\u0019\u0006G\u0011+uI\u0012\b\u00039\u0015K!AR\u000f\u0002\t1{gnZ\u0019\u0005Iejd$M\u0003$\u0013*c5J\u0004\u0002\u001d\u0015&\u00111*H\u0001\u0006\r2|\u0017\r^\u0019\u0005Iejd$M\u0003$\u001d>\u000b\u0006K\u0004\u0002\u001d\u001f&\u0011\u0001+H\u0001\u0007\t>,(\r\\32\t\u0011JTHH\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Q\u0003\"\u0001H+\n\u0005Yk\"\u0001B+oSR\fa\u0001J7j]V\u001cHcA\u0013Z7\")!L\u0001a\u0001K\u0005\t\u0011\rC\u0003]\u0005\u0001\u0007Q%A\u0001c\u0003\u0019qWmZ1uKR\u0011Qe\u0018\u0005\u0006A\u000e\u0001\r!J\u0001\u0002g\u0006AA\u0005]3sG\u0016tG\u000fF\u0002&G\u0012DQA\u0017\u0003A\u0002\u0015BQ\u0001\u0018\u0003A\u0002\u0015\n\u0001B\\8s[&k\u0007\u000f\\\u000b\u0002OB!\u0001\u000e]\u0013w\u001d\tIWN\u0004\u0002kW6\ta#\u0003\u0002m-\u00051A.\u001b8bY\u001eL!A\\8\u0002\t9|'/\u001c\u0006\u0003YZI!!\u001d:\u0003\t%k\u0007\u000f\\\u0005\u0003gR\u0014Q!\u0016$v]\u000eT!!\u001e\f\u0002\u000f\u001d,g.\u001a:jGB\u0011Ad^\u0005\u0003qv\u0011a\u0001R8vE2,\u0017!B:O_JlGC\u0001<|\u0011\u0015Qf\u00011\u0001&\u0003\u0011\u0011\u0016N\\4\u0011\u0005\tB1c\u0001\u0005\u001c\u007fB!\u0011\u0011AA\u0006\u001b\t\t\u0019A\u0003\u0003\u0002\u0006\u0005\u001d\u0011AA5p\u0015\t\tI!\u0001\u0003kCZ\f\u0017\u0002BA\u0007\u0003\u0007\u0011AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#A?\u0002\u000bILgn\u001a#\u0016\u0005\u0005]\u0001c\u0001\u0012\u0001m\u0006I!/\u001b8h\r2|\u0017\r^\u000b\u0003\u0003;\u0001BA\t\u0001\u0002 A\u0019A$!\t\n\u0007\u0005\rRDA\u0003GY>\fG/A\u0004sS:<\u0017J\u001c;\u0016\u0005\u0005%\u0002\u0003\u0002\u0012\u0001\u0003W\u00012\u0001HA\u0017\u0013\r\ty#\b\u0002\u0004\u0013:$\u0018\u0001\u0003:j]\u001eduN\\4\u0016\u0005\u0005U\u0002\u0003\u0002\u0012\u0001\u0003o\u00012\u0001HA\u001d\u0013\r\tY$\b\u0002\u0005\u0019>tw-\u0001\u0006sS:<')[4J]R,\"!!\u0011\u0011\t\t\u0002\u00111\t\t\u0005\u0003\u000b\nYED\u0002:\u0003\u000fJ1!!\u0013\u001e\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0014\u0002P\t1!)[4J]RT1!!\u0013\u001e\u0003%\u0011\u0018N\\4TQ>\u0014H/\u0006\u0002\u0002VA!!\u0005AA,!\ra\u0012\u0011L\u0005\u0004\u00037j\"!B*i_J$\u0018a\u0003:j]\u001e\u001cu.\u001c9mKb,\"!!\u0019\u0011\t\t\u0002\u00111\r\t\u0004E\u0005\u0015\u0014bAA4)\t91i\\7qY\u0016D\u0018!\u0004:j]\u001e4%o\\7GS\u0016dG-\u0006\u0003\u0002n\u0005MD\u0003BA8\u0003o\u0002BA\t\u0001\u0002rA\u0019a%a\u001d\u0005\r\u0005U\u0014C1\u0001*\u0005\u0005!\u0006bBA=#\u0001\u000f\u00111P\u0001\u0006M&,G\u000e\u001a\t\u0006E\u0005u\u0014\u0011O\u0005\u0004\u0003\u007f\"\"!\u0002$jK2$\u0017\u0001D<sSR,'+\u001a9mC\u000e,GCAAC!\u0011\t9)!$\u000e\u0005\u0005%%\u0002BAF\u0003\u000f\tA\u0001\\1oO&!\u0011qRAE\u0005\u0019y%M[3di\u0002"
)
public interface Ring extends Semiring {
   static Ring ringFromField(final Field field) {
      return Ring$.MODULE$.ringFromField(field);
   }

   static Ring ringComplex() {
      return Ring$.MODULE$.ringComplex();
   }

   static Ring ringShort() {
      return Ring$.MODULE$.ringShort();
   }

   static Ring ringBigInt() {
      return Ring$.MODULE$.ringBigInt();
   }

   static Ring ringLong() {
      return Ring$.MODULE$.ringLong();
   }

   static Ring ringInt() {
      return Ring$.MODULE$.ringInt();
   }

   static Ring ringFloat() {
      return Ring$.MODULE$.ringFloat();
   }

   static Ring ringD() {
      return Ring$.MODULE$.ringD();
   }

   Object $minus(final Object a, final Object b);

   // $FF: synthetic method
   static Object negate$(final Ring $this, final Object s) {
      return $this.negate(s);
   }

   default Object negate(final Object s) {
      return this.$minus(this.zero(), s);
   }

   Object $percent(final Object a, final Object b);

   UFunc.UImpl normImpl();

   // $FF: synthetic method
   static double sNorm$(final Ring $this, final Object a) {
      return $this.sNorm(a);
   }

   default double sNorm(final Object a) {
      return BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(a, this.normImpl()));
   }

   // $FF: synthetic method
   static double $minus$mcD$sp$(final Ring $this, final double a, final double b) {
      return $this.$minus$mcD$sp(a, b);
   }

   default double $minus$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.$minus(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   // $FF: synthetic method
   static float $minus$mcF$sp$(final Ring $this, final float a, final float b) {
      return $this.$minus$mcF$sp(a, b);
   }

   default float $minus$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.$minus(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   // $FF: synthetic method
   static int $minus$mcI$sp$(final Ring $this, final int a, final int b) {
      return $this.$minus$mcI$sp(a, b);
   }

   default int $minus$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.$minus(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long $minus$mcJ$sp$(final Ring $this, final long a, final long b) {
      return $this.$minus$mcJ$sp(a, b);
   }

   default long $minus$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.$minus(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static short $minus$mcS$sp$(final Ring $this, final short a, final short b) {
      return $this.$minus$mcS$sp(a, b);
   }

   default short $minus$mcS$sp(final short a, final short b) {
      return BoxesRunTime.unboxToShort(this.$minus(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b)));
   }

   // $FF: synthetic method
   static double negate$mcD$sp$(final Ring $this, final double s) {
      return $this.negate$mcD$sp(s);
   }

   default double negate$mcD$sp(final double s) {
      return BoxesRunTime.unboxToDouble(this.negate(BoxesRunTime.boxToDouble(s)));
   }

   // $FF: synthetic method
   static float negate$mcF$sp$(final Ring $this, final float s) {
      return $this.negate$mcF$sp(s);
   }

   default float negate$mcF$sp(final float s) {
      return BoxesRunTime.unboxToFloat(this.negate(BoxesRunTime.boxToFloat(s)));
   }

   // $FF: synthetic method
   static int negate$mcI$sp$(final Ring $this, final int s) {
      return $this.negate$mcI$sp(s);
   }

   default int negate$mcI$sp(final int s) {
      return BoxesRunTime.unboxToInt(this.negate(BoxesRunTime.boxToInteger(s)));
   }

   // $FF: synthetic method
   static long negate$mcJ$sp$(final Ring $this, final long s) {
      return $this.negate$mcJ$sp(s);
   }

   default long negate$mcJ$sp(final long s) {
      return BoxesRunTime.unboxToLong(this.negate(BoxesRunTime.boxToLong(s)));
   }

   // $FF: synthetic method
   static short negate$mcS$sp$(final Ring $this, final short s) {
      return $this.negate$mcS$sp(s);
   }

   default short negate$mcS$sp(final short s) {
      return BoxesRunTime.unboxToShort(this.negate(BoxesRunTime.boxToShort(s)));
   }

   // $FF: synthetic method
   static double $percent$mcD$sp$(final Ring $this, final double a, final double b) {
      return $this.$percent$mcD$sp(a, b);
   }

   default double $percent$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.$percent(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   // $FF: synthetic method
   static float $percent$mcF$sp$(final Ring $this, final float a, final float b) {
      return $this.$percent$mcF$sp(a, b);
   }

   default float $percent$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.$percent(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   // $FF: synthetic method
   static int $percent$mcI$sp$(final Ring $this, final int a, final int b) {
      return $this.$percent$mcI$sp(a, b);
   }

   default int $percent$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.$percent(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long $percent$mcJ$sp$(final Ring $this, final long a, final long b) {
      return $this.$percent$mcJ$sp(a, b);
   }

   default long $percent$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.$percent(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   // $FF: synthetic method
   static short $percent$mcS$sp$(final Ring $this, final short a, final short b) {
      return $this.$percent$mcS$sp(a, b);
   }

   default short $percent$mcS$sp(final short a, final short b) {
      return BoxesRunTime.unboxToShort(this.$percent(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b)));
   }

   // $FF: synthetic method
   static UFunc.UImpl normImpl$mcD$sp$(final Ring $this) {
      return $this.normImpl$mcD$sp();
   }

   default UFunc.UImpl normImpl$mcD$sp() {
      return this.normImpl();
   }

   // $FF: synthetic method
   static UFunc.UImpl normImpl$mcF$sp$(final Ring $this) {
      return $this.normImpl$mcF$sp();
   }

   default UFunc.UImpl normImpl$mcF$sp() {
      return this.normImpl();
   }

   // $FF: synthetic method
   static UFunc.UImpl normImpl$mcI$sp$(final Ring $this) {
      return $this.normImpl$mcI$sp();
   }

   default UFunc.UImpl normImpl$mcI$sp() {
      return this.normImpl();
   }

   // $FF: synthetic method
   static UFunc.UImpl normImpl$mcJ$sp$(final Ring $this) {
      return $this.normImpl$mcJ$sp();
   }

   default UFunc.UImpl normImpl$mcJ$sp() {
      return this.normImpl();
   }

   // $FF: synthetic method
   static UFunc.UImpl normImpl$mcS$sp$(final Ring $this) {
      return $this.normImpl$mcS$sp();
   }

   default UFunc.UImpl normImpl$mcS$sp() {
      return this.normImpl();
   }

   // $FF: synthetic method
   static double sNorm$mcD$sp$(final Ring $this, final double a) {
      return $this.sNorm$mcD$sp(a);
   }

   default double sNorm$mcD$sp(final double a) {
      return this.sNorm(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static double sNorm$mcF$sp$(final Ring $this, final float a) {
      return $this.sNorm$mcF$sp(a);
   }

   default double sNorm$mcF$sp(final float a) {
      return this.sNorm(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static double sNorm$mcI$sp$(final Ring $this, final int a) {
      return $this.sNorm$mcI$sp(a);
   }

   default double sNorm$mcI$sp(final int a) {
      return this.sNorm(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static double sNorm$mcJ$sp$(final Ring $this, final long a) {
      return $this.sNorm$mcJ$sp(a);
   }

   default double sNorm$mcJ$sp(final long a) {
      return this.sNorm(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static double sNorm$mcS$sp$(final Ring $this, final short a) {
      return $this.sNorm$mcS$sp(a);
   }

   default double sNorm$mcS$sp(final short a) {
      return this.sNorm(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static boolean specInstance$$(final Ring $this) {
      return $this.specInstance$();
   }

   default boolean specInstance$() {
      return false;
   }

   static void $init$(final Ring $this) {
   }
}
