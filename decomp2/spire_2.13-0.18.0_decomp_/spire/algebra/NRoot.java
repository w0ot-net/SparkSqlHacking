package spire.algebra;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A4q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0019\u0005Q\u0004C\u0003K\u0001\u0011\u00051\nC\u0003N\u0001\u0019\u0005ajB\u0003S\u0015!\u00051KB\u0003\n\u0015!\u0005Q\u000bC\u0003Z\r\u0011\u0005!\fC\u0003\\\r\u0011\u0015ALA\u0003O%>|GO\u0003\u0002\f\u0019\u00059\u0011\r\\4fEJ\f'\"A\u0007\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011\u0001\u0003I\n\u0003\u0001E\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u00111!\u00118z\u0003\u0019!\u0013N\\5uIQ\t\u0011\u0004\u0005\u0002\u00135%\u00111d\u0005\u0002\u0005+:LG/A\u0003oe>|G\u000fF\u0002\u001f\u0007\u0016\u0003\"a\b\u0011\r\u0001\u0011I\u0011\u0005\u0001Q\u0001\u0002\u0003\u0015\rA\t\u0002\u0002\u0003F\u00111%\u0005\t\u0003%\u0011J!!J\n\u0003\u000f9{G\u000f[5oO\"2\u0001e\n\u00165sy\u0002\"A\u0005\u0015\n\u0005%\u001a\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\u0016-]5r!A\u0005\u0017\n\u00055\u001a\u0012A\u0002#pk\ndW-\r\u0003%_M\"bB\u0001\u00194\u001b\u0005\t$B\u0001\u001a\u000f\u0003\u0019a$o\\8u}%\tA#M\u0003$kYBtG\u0004\u0002\u0013m%\u0011qgE\u0001\u0006\r2|\u0017\r^\u0019\u0005I=\u001aD#M\u0003$umjDH\u0004\u0002\u0013w%\u0011AhE\u0001\u0004\u0013:$\u0018\u0007\u0002\u00130gQ\tTaI A\u0005\u0006s!A\u0005!\n\u0005\u0005\u001b\u0012\u0001\u0002'p]\u001e\fD\u0001J\u00184)!)AI\u0001a\u0001=\u0005\t\u0011\rC\u0003G\u0005\u0001\u0007q)A\u0001o!\t\u0011\u0002*\u0003\u0002J'\t\u0019\u0011J\u001c;\u0002\tM\f(\u000f\u001e\u000b\u0003=1CQ\u0001R\u0002A\u0002y\tAA\u001a9poR\u0019ad\u0014)\t\u000b\u0011#\u0001\u0019\u0001\u0010\t\u000bE#\u0001\u0019\u0001\u0010\u0002\u0003\t\fQA\u0014*p_R\u0004\"\u0001\u0016\u0004\u000e\u0003)\u0019\"A\u0002,\u0011\u0005I9\u0016B\u0001-\u0014\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012aU\u0001\u0006CB\u0004H._\u000b\u0003;\u0002$\"A\u00186\u0011\u0007Q\u0003q\f\u0005\u0002 A\u0012I\u0011\u0005\u0003Q\u0001\u0002\u0003\u0015\rA\t\u0015\u0007A\u001e\u0012GM\u001a52\u000b\rR4h\u0019\u001f2\t\u0011z3\u0007F\u0019\u0006G}\u0002U-Q\u0019\u0005I=\u001aD#M\u0003$kY:w'\r\u0003%_M\"\u0012'B\u0012,Y%l\u0013\u0007\u0002\u00130gQAQa\u001b\u0005A\u0004y\u000b!!\u001a<)\u0005!i\u0007C\u0001\no\u0013\ty7C\u0001\u0004j]2Lg.\u001a"
)
public interface NRoot {
   static NRoot apply(final NRoot ev) {
      return NRoot$.MODULE$.apply(ev);
   }

   Object nroot(final Object a, final int n);

   // $FF: synthetic method
   static Object sqrt$(final NRoot $this, final Object a) {
      return $this.sqrt(a);
   }

   default Object sqrt(final Object a) {
      return this.nroot(a, 2);
   }

   Object fpow(final Object a, final Object b);

   // $FF: synthetic method
   static double nroot$mcD$sp$(final NRoot $this, final double a, final int n) {
      return $this.nroot$mcD$sp(a, n);
   }

   default double nroot$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.nroot(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float nroot$mcF$sp$(final NRoot $this, final float a, final int n) {
      return $this.nroot$mcF$sp(a, n);
   }

   default float nroot$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.nroot(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int nroot$mcI$sp$(final NRoot $this, final int a, final int n) {
      return $this.nroot$mcI$sp(a, n);
   }

   default int nroot$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.nroot(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long nroot$mcJ$sp$(final NRoot $this, final long a, final int n) {
      return $this.nroot$mcJ$sp(a, n);
   }

   default long nroot$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.nroot(BoxesRunTime.boxToLong(a), n));
   }

   // $FF: synthetic method
   static double sqrt$mcD$sp$(final NRoot $this, final double a) {
      return $this.sqrt$mcD$sp(a);
   }

   default double sqrt$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.sqrt(BoxesRunTime.boxToDouble(a)));
   }

   // $FF: synthetic method
   static float sqrt$mcF$sp$(final NRoot $this, final float a) {
      return $this.sqrt$mcF$sp(a);
   }

   default float sqrt$mcF$sp(final float a) {
      return BoxesRunTime.unboxToFloat(this.sqrt(BoxesRunTime.boxToFloat(a)));
   }

   // $FF: synthetic method
   static int sqrt$mcI$sp$(final NRoot $this, final int a) {
      return $this.sqrt$mcI$sp(a);
   }

   default int sqrt$mcI$sp(final int a) {
      return BoxesRunTime.unboxToInt(this.sqrt(BoxesRunTime.boxToInteger(a)));
   }

   // $FF: synthetic method
   static long sqrt$mcJ$sp$(final NRoot $this, final long a) {
      return $this.sqrt$mcJ$sp(a);
   }

   default long sqrt$mcJ$sp(final long a) {
      return BoxesRunTime.unboxToLong(this.sqrt(BoxesRunTime.boxToLong(a)));
   }

   // $FF: synthetic method
   static double fpow$mcD$sp$(final NRoot $this, final double a, final double b) {
      return $this.fpow$mcD$sp(a, b);
   }

   default double fpow$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.fpow(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   // $FF: synthetic method
   static float fpow$mcF$sp$(final NRoot $this, final float a, final float b) {
      return $this.fpow$mcF$sp(a, b);
   }

   default float fpow$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.fpow(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   // $FF: synthetic method
   static int fpow$mcI$sp$(final NRoot $this, final int a, final int b) {
      return $this.fpow$mcI$sp(a, b);
   }

   default int fpow$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.fpow(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   // $FF: synthetic method
   static long fpow$mcJ$sp$(final NRoot $this, final long a, final long b) {
      return $this.fpow$mcJ$sp(a, b);
   }

   default long fpow$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.fpow(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   static void $init$(final NRoot $this) {
   }
}
