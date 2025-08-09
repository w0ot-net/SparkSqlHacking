package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003*\u0001\u0011\u0005!\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u0003X\u0001\u0011\u0005\u0001L\u0001\u000fNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3He>,\bOR;oGRLwN\\:\u000b\u0005\u00199\u0011\u0001\u0002:j]\u001eT\u0011\u0001C\u0001\bC2<WM\u0019:b\u0007\u0001)\"a\u0003\r\u0014\u0007\u0001a!\u0003\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VM\u001a\t\u0004'Q1R\"A\u0003\n\u0005U)!!H'vYRL\u0007\u000f\\5dCRLg/Z'p]>LGMR;oGRLwN\\:\u0011\u0005]AB\u0002\u0001\u0003\u00063\u0001\u0011\rA\u0007\u0002\u0002\u000fV\u00111dI\t\u00039}\u0001\"!D\u000f\n\u0005yq!a\u0002(pi\"Lgn\u001a\t\u0004'\u0001\u0012\u0013BA\u0011\u0006\u0005MiU\u000f\u001c;ja2L7-\u0019;jm\u0016<%o\\;q!\t92\u0005B\u0003%1\t\u0007QEA\u0001U#\tab\u0005\u0005\u0002\u000eO%\u0011\u0001F\u0004\u0002\u0004\u0003:L\u0018A\u0002\u0013j]&$H\u0005F\u0001,!\tiA&\u0003\u0002.\u001d\t!QK\\5u\u0003)\u0011XmY5qe>\u001c\u0017\r\\\u000b\u0003aM\"\"!M+\u0015\u0005I\u0012\u0006CA\f4\t%!$\u0001)A\u0001\u0002\u000b\u0007QEA\u0001BQ\u0019\u0019d'O\"I\u001bB\u0011QbN\u0005\u0003q9\u00111b\u001d9fG&\fG.\u001b>fIF*1EO\u001e>y9\u0011QbO\u0005\u0003y9\t1!\u00138uc\u0011!cHQ\b\u000f\u0005}\u0012U\"\u0001!\u000b\u0005\u0005K\u0011A\u0002\u001fs_>$h(C\u0001\u0010c\u0015\u0019C)R$G\u001d\tiQ)\u0003\u0002G\u001d\u0005!Aj\u001c8hc\u0011!cHQ\b2\u000b\rJ%\nT&\u000f\u00055Q\u0015BA&\u000f\u0003\u00151En\\1uc\u0011!cHQ\b2\u000b\rru*\u0015)\u000f\u00055y\u0015B\u0001)\u000f\u0003\u0019!u.\u001e2mKF\"AE\u0010\"\u0010\u0011\u0015\u0019&\u0001q\u0001U\u0003\t)g\u000fE\u0002\u00181IBQA\u0016\u0002A\u0002I\n\u0011\u0001_\u0001\u0004I&4XCA-])\rQ\u0006.\u001b\u000b\u00037\u001a\u0004\"a\u0006/\u0005\u0013Q\u001a\u0001\u0015!A\u0001\u0006\u0004)\u0003F\u0002/7=\u0002\u0014G-M\u0003$umzF(\r\u0003%}\t{\u0011'B\u0012E\u000b\u00064\u0015\u0007\u0002\u0013?\u0005>\tTaI%KG.\u000bD\u0001\n C\u001fE*1ET(f!F\"AE\u0010\"\u0010\u0011\u0015\u00196\u0001q\u0001h!\r9\u0002d\u0017\u0005\u0006-\u000e\u0001\ra\u0017\u0005\u0006U\u000e\u0001\raW\u0001\u0002s\u0002"
)
public interface MultiplicativeGroupFunctions extends MultiplicativeMonoidFunctions {
   // $FF: synthetic method
   static Object reciprocal$(final MultiplicativeGroupFunctions $this, final Object x, final MultiplicativeGroup ev) {
      return $this.reciprocal(x, ev);
   }

   default Object reciprocal(final Object x, final MultiplicativeGroup ev) {
      return ev.reciprocal(x);
   }

   // $FF: synthetic method
   static Object div$(final MultiplicativeGroupFunctions $this, final Object x, final Object y, final MultiplicativeGroup ev) {
      return $this.div(x, y, ev);
   }

   default Object div(final Object x, final Object y, final MultiplicativeGroup ev) {
      return ev.div(x, y);
   }

   // $FF: synthetic method
   static double reciprocal$mDc$sp$(final MultiplicativeGroupFunctions $this, final double x, final MultiplicativeGroup ev) {
      return $this.reciprocal$mDc$sp(x, ev);
   }

   default double reciprocal$mDc$sp(final double x, final MultiplicativeGroup ev) {
      return ev.reciprocal$mcD$sp(x);
   }

   // $FF: synthetic method
   static float reciprocal$mFc$sp$(final MultiplicativeGroupFunctions $this, final float x, final MultiplicativeGroup ev) {
      return $this.reciprocal$mFc$sp(x, ev);
   }

   default float reciprocal$mFc$sp(final float x, final MultiplicativeGroup ev) {
      return ev.reciprocal$mcF$sp(x);
   }

   // $FF: synthetic method
   static int reciprocal$mIc$sp$(final MultiplicativeGroupFunctions $this, final int x, final MultiplicativeGroup ev) {
      return $this.reciprocal$mIc$sp(x, ev);
   }

   default int reciprocal$mIc$sp(final int x, final MultiplicativeGroup ev) {
      return ev.reciprocal$mcI$sp(x);
   }

   // $FF: synthetic method
   static long reciprocal$mJc$sp$(final MultiplicativeGroupFunctions $this, final long x, final MultiplicativeGroup ev) {
      return $this.reciprocal$mJc$sp(x, ev);
   }

   default long reciprocal$mJc$sp(final long x, final MultiplicativeGroup ev) {
      return ev.reciprocal$mcJ$sp(x);
   }

   // $FF: synthetic method
   static double div$mDc$sp$(final MultiplicativeGroupFunctions $this, final double x, final double y, final MultiplicativeGroup ev) {
      return $this.div$mDc$sp(x, y, ev);
   }

   default double div$mDc$sp(final double x, final double y, final MultiplicativeGroup ev) {
      return ev.div$mcD$sp(x, y);
   }

   // $FF: synthetic method
   static float div$mFc$sp$(final MultiplicativeGroupFunctions $this, final float x, final float y, final MultiplicativeGroup ev) {
      return $this.div$mFc$sp(x, y, ev);
   }

   default float div$mFc$sp(final float x, final float y, final MultiplicativeGroup ev) {
      return ev.div$mcF$sp(x, y);
   }

   // $FF: synthetic method
   static int div$mIc$sp$(final MultiplicativeGroupFunctions $this, final int x, final int y, final MultiplicativeGroup ev) {
      return $this.div$mIc$sp(x, y, ev);
   }

   default int div$mIc$sp(final int x, final int y, final MultiplicativeGroup ev) {
      return ev.div$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long div$mJc$sp$(final MultiplicativeGroupFunctions $this, final long x, final long y, final MultiplicativeGroup ev) {
      return $this.div$mJc$sp(x, y, ev);
   }

   default long div$mJc$sp(final long x, final long y, final MultiplicativeGroup ev) {
      return ev.div$mcJ$sp(x, y);
   }

   static void $init$(final MultiplicativeGroupFunctions $this) {
   }
}
