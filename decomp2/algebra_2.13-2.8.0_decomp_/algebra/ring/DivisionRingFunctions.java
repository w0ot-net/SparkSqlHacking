package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003,\u0001\u0011\u0005A\u0006C\u00031\u0001\u0011\u0005\u0011GA\u000bESZL7/[8o%&twMR;oGRLwN\\:\u000b\u0005\u00151\u0011\u0001\u0002:j]\u001eT\u0011aB\u0001\bC2<WM\u0019:b\u0007\u0001)\"AC\f\u0014\t\u0001Y\u0011\u0003\u000b\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007I\u0019R#D\u0001\u0005\u0013\t!BAA\u0007SS:<g)\u001e8di&|gn\u001d\t\u0003-]a\u0001\u0001B\u0003\u0019\u0001\t\u0007\u0011DA\u0001G+\tQ\"%\u0005\u0002\u001c=A\u0011A\u0002H\u0005\u0003;5\u0011qAT8uQ&tw\rE\u0002\u0013?\u0005J!\u0001\t\u0003\u0003\u0019\u0011Kg/[:j_:\u0014\u0016N\\4\u0011\u0005Y\u0011C!B\u0012\u0018\u0005\u0004!#!\u0001+\u0012\u0005m)\u0003C\u0001\u0007'\u0013\t9SBA\u0002B]f\u00042AE\u0015\u0016\u0013\tQCA\u0001\u000fNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3He>,\bOR;oGRLwN\\:\u0002\r\u0011Jg.\u001b;%)\u0005i\u0003C\u0001\u0007/\u0013\tySB\u0001\u0003V]&$\u0018A\u00034s_6$u.\u001e2mKV\u0011!'\u000e\u000b\u0003g]#\"\u0001\u000e+\u0011\u0005Y)D!\u0003\u001c\u0003A\u0003\u0005\tQ1\u0001%\u0005\u0005\t\u0005FB\u001b9w\u0015Su\n\u0005\u0002\rs%\u0011!(\u0004\u0002\fgB,7-[1mSj,G-M\u0003$yuzdH\u0004\u0002\r{%\u0011a(D\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013A\t:q!!\u0011#\u000e\u0003\tS!a\u0011\u0005\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011'B\u0012G\u000f&CeB\u0001\u0007H\u0013\tAU\"\u0001\u0003M_:<\u0017\u0007\u0002\u0013A\t:\tTaI&M\u001d6s!\u0001\u0004'\n\u00055k\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u0013A\t:\tTa\t)R'Js!\u0001D)\n\u0005Ik\u0011A\u0002#pk\ndW-\r\u0003%\u0001\u0012s\u0001\"B+\u0003\u0001\b1\u0016AA3w!\r1r\u0003\u000e\u0005\u00061\n\u0001\r!W\u0001\u0002]B\u0011ABW\u0005\u000376\u0011a\u0001R8vE2,\u0007"
)
public interface DivisionRingFunctions extends RingFunctions, MultiplicativeGroupFunctions {
   // $FF: synthetic method
   static Object fromDouble$(final DivisionRingFunctions $this, final double n, final DivisionRing ev) {
      return $this.fromDouble(n, ev);
   }

   default Object fromDouble(final double n, final DivisionRing ev) {
      return ev.fromDouble(n);
   }

   // $FF: synthetic method
   static double fromDouble$mDc$sp$(final DivisionRingFunctions $this, final double n, final DivisionRing ev) {
      return $this.fromDouble$mDc$sp(n, ev);
   }

   default double fromDouble$mDc$sp(final double n, final DivisionRing ev) {
      return ev.fromDouble$mcD$sp(n);
   }

   // $FF: synthetic method
   static float fromDouble$mFc$sp$(final DivisionRingFunctions $this, final double n, final DivisionRing ev) {
      return $this.fromDouble$mFc$sp(n, ev);
   }

   default float fromDouble$mFc$sp(final double n, final DivisionRing ev) {
      return ev.fromDouble$mcF$sp(n);
   }

   // $FF: synthetic method
   static int fromDouble$mIc$sp$(final DivisionRingFunctions $this, final double n, final DivisionRing ev) {
      return $this.fromDouble$mIc$sp(n, ev);
   }

   default int fromDouble$mIc$sp(final double n, final DivisionRing ev) {
      return ev.fromDouble$mcI$sp(n);
   }

   // $FF: synthetic method
   static long fromDouble$mJc$sp$(final DivisionRingFunctions $this, final double n, final DivisionRing ev) {
      return $this.fromDouble$mJc$sp(n, ev);
   }

   default long fromDouble$mJc$sp(final double n, final DivisionRing ev) {
      return ev.fromDouble$mcJ$sp(n);
   }

   static void $init$(final DivisionRingFunctions $this) {
   }
}
