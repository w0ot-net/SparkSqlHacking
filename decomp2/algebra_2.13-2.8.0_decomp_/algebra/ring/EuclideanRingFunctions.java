package algebra.ring;

import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mba\u0002\u0004\b!\u0003\r\t\u0001\u0004\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006a\u0001!\t!\r\u0005\u0006A\u0002!\t!\u0019\u0005\u0006i\u0002!\t!\u001e\u0005\b\u0003\u001f\u0001A\u0011AA\t\u0005Y)Uo\u00197jI\u0016\fgNU5oO\u001a+hn\u0019;j_:\u001c(B\u0001\u0005\n\u0003\u0011\u0011\u0018N\\4\u000b\u0003)\tq!\u00197hK\n\u0014\u0018m\u0001\u0001\u0016\u00055Q2c\u0001\u0001\u000f)A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u00042!\u0006\f\u0019\u001b\u00059\u0011BA\f\b\u0005A95\t\u0012*j]\u001e4UO\\2uS>t7\u000f\u0005\u0002\u001a51\u0001A!B\u000e\u0001\u0005\u0004a\"!\u0001*\u0016\u0005u)\u0013C\u0001\u0010\"!\tyq$\u0003\u0002!!\t9aj\u001c;iS:<\u0007cA\u000b#I%\u00111e\u0002\u0002\u000e\u000bV\u001cG.\u001b3fC:\u0014\u0016N\\4\u0011\u0005e)C!\u0002\u0014\u001b\u0005\u00049#!\u0001+\u0012\u0005yA\u0003CA\b*\u0013\tQ\u0003CA\u0002B]f\fa\u0001J5oSR$C#A\u0017\u0011\u0005=q\u0013BA\u0018\u0011\u0005\u0011)f.\u001b;\u0002#\u0015,8\r\\5eK\u0006tg)\u001e8di&|g.\u0006\u00023\tR\u00111G\u0018\u000b\u0003i\u0001\u0003\"!N\u001f\u000f\u0005YZdBA\u001c;\u001b\u0005A$BA\u001d\f\u0003\u0019a$o\\8u}%\t\u0011#\u0003\u0002=!\u00059\u0001/Y2lC\u001e,\u0017B\u0001 @\u0005\u0019\u0011\u0015nZ%oi*\u0011A\b\u0005\u0005\u0006\u0003\n\u0001\u001dAQ\u0001\u0003KZ\u00042!\u0007\u000eD!\tIB\tB\u0005F\u0005\u0001\u0006\t\u0011!b\u0001O\t\t\u0011\t\u000b\u0004E\u000f*{E+\u0017\t\u0003\u001f!K!!\u0013\t\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G-ce*\u0014\b\u0003\u001f1K!!\u0014\t\u0002\u0007%sG/\r\u0003%mi\n\u0012'B\u0012Q#N\u0013fBA\bR\u0013\t\u0011\u0006#\u0001\u0003M_:<\u0017\u0007\u0002\u00137uE\tTaI+W1^s!a\u0004,\n\u0005]\u0003\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u00137uE\tTa\t.\\;rs!aD.\n\u0005q\u0003\u0012A\u0002#pk\ndW-\r\u0003%mi\n\u0002\"B0\u0003\u0001\u0004\u0019\u0015!A1\u0002\u000b\u0015\fXo\u001c;\u0016\u0005\t,GcA2reR\u0011Am\u001c\t\u00033\u0015$\u0011\"R\u0002!\u0002\u0003\u0005)\u0019A\u0014)\r\u0015<u-[6nc\u0015\u00193\n\u00145Nc\u0011!cGO\t2\u000b\r\u0002\u0016K\u001b*2\t\u00112$(E\u0019\u0006GU3FnV\u0019\u0005IYR\u0014#M\u0003$5nsG,\r\u0003%mi\n\u0002\"B!\u0004\u0001\b\u0001\bcA\r\u001bI\")ql\u0001a\u0001I\")1o\u0001a\u0001I\u0006\t!-\u0001\u0003f[>$WC\u0001<z)\u00159\u00181BA\u0007)\rA\u0018q\u0001\t\u00033e$\u0011\"\u0012\u0003!\u0002\u0003\u0005)\u0019A\u0014)\u000fe<50`@\u0002\u0004E*1e\u0013'}\u001bF\"AE\u000e\u001e\u0012c\u0015\u0019\u0003+\u0015@Sc\u0011!cGO\t2\r\r*f+!\u0001Xc\u0011!cGO\t2\r\rR6,!\u0002]c\u0011!cGO\t\t\r\u0005#\u00019AA\u0005!\rI\"\u0004\u001f\u0005\u0006?\u0012\u0001\r\u0001\u001f\u0005\u0006g\u0012\u0001\r\u0001_\u0001\tKF,x\u000e^7pIV!\u00111CA\u0010)\u0019\t)\"a\u000e\u0002:Q!\u0011qCA\u001a!\u001dy\u0011\u0011DA\u000f\u0003;I1!a\u0007\u0011\u0005\u0019!V\u000f\u001d7feA\u0019\u0011$a\b\u0005\u0013\u0015+\u0001\u0015!A\u0001\u0006\u00049\u0003fCA\u0010\u000f\u0006\r\u0012qEA\u0016\u0003_\tdaI&M\u0003Ki\u0015\u0007\u0002\u00137uE\tda\t)R\u0003S\u0011\u0016\u0007\u0002\u00137uE\tdaI+W\u0003[9\u0016\u0007\u0002\u00137uE\tda\t.\\\u0003ca\u0016\u0007\u0002\u00137uEAa!Q\u0003A\u0004\u0005U\u0002\u0003B\r\u001b\u0003;AaaX\u0003A\u0002\u0005u\u0001BB:\u0006\u0001\u0004\ti\u0002"
)
public interface EuclideanRingFunctions extends GCDRingFunctions {
   // $FF: synthetic method
   static BigInt euclideanFunction$(final EuclideanRingFunctions $this, final Object a, final EuclideanRing ev) {
      return $this.euclideanFunction(a, ev);
   }

   default BigInt euclideanFunction(final Object a, final EuclideanRing ev) {
      return ev.euclideanFunction(a);
   }

   // $FF: synthetic method
   static Object equot$(final EuclideanRingFunctions $this, final Object a, final Object b, final EuclideanRing ev) {
      return $this.equot(a, b, ev);
   }

   default Object equot(final Object a, final Object b, final EuclideanRing ev) {
      return ev.equot(a, b);
   }

   // $FF: synthetic method
   static Object emod$(final EuclideanRingFunctions $this, final Object a, final Object b, final EuclideanRing ev) {
      return $this.emod(a, b, ev);
   }

   default Object emod(final Object a, final Object b, final EuclideanRing ev) {
      return ev.emod(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$(final EuclideanRingFunctions $this, final Object a, final Object b, final EuclideanRing ev) {
      return $this.equotmod(a, b, ev);
   }

   default Tuple2 equotmod(final Object a, final Object b, final EuclideanRing ev) {
      return ev.equotmod(a, b);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mDc$sp$(final EuclideanRingFunctions $this, final double a, final EuclideanRing ev) {
      return $this.euclideanFunction$mDc$sp(a, ev);
   }

   default BigInt euclideanFunction$mDc$sp(final double a, final EuclideanRing ev) {
      return ev.euclideanFunction$mcD$sp(a);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mFc$sp$(final EuclideanRingFunctions $this, final float a, final EuclideanRing ev) {
      return $this.euclideanFunction$mFc$sp(a, ev);
   }

   default BigInt euclideanFunction$mFc$sp(final float a, final EuclideanRing ev) {
      return ev.euclideanFunction$mcF$sp(a);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mIc$sp$(final EuclideanRingFunctions $this, final int a, final EuclideanRing ev) {
      return $this.euclideanFunction$mIc$sp(a, ev);
   }

   default BigInt euclideanFunction$mIc$sp(final int a, final EuclideanRing ev) {
      return ev.euclideanFunction$mcI$sp(a);
   }

   // $FF: synthetic method
   static BigInt euclideanFunction$mJc$sp$(final EuclideanRingFunctions $this, final long a, final EuclideanRing ev) {
      return $this.euclideanFunction$mJc$sp(a, ev);
   }

   default BigInt euclideanFunction$mJc$sp(final long a, final EuclideanRing ev) {
      return ev.euclideanFunction$mcJ$sp(a);
   }

   // $FF: synthetic method
   static double equot$mDc$sp$(final EuclideanRingFunctions $this, final double a, final double b, final EuclideanRing ev) {
      return $this.equot$mDc$sp(a, b, ev);
   }

   default double equot$mDc$sp(final double a, final double b, final EuclideanRing ev) {
      return ev.equot$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static float equot$mFc$sp$(final EuclideanRingFunctions $this, final float a, final float b, final EuclideanRing ev) {
      return $this.equot$mFc$sp(a, b, ev);
   }

   default float equot$mFc$sp(final float a, final float b, final EuclideanRing ev) {
      return ev.equot$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static int equot$mIc$sp$(final EuclideanRingFunctions $this, final int a, final int b, final EuclideanRing ev) {
      return $this.equot$mIc$sp(a, b, ev);
   }

   default int equot$mIc$sp(final int a, final int b, final EuclideanRing ev) {
      return ev.equot$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static long equot$mJc$sp$(final EuclideanRingFunctions $this, final long a, final long b, final EuclideanRing ev) {
      return $this.equot$mJc$sp(a, b, ev);
   }

   default long equot$mJc$sp(final long a, final long b, final EuclideanRing ev) {
      return ev.equot$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static double emod$mDc$sp$(final EuclideanRingFunctions $this, final double a, final double b, final EuclideanRing ev) {
      return $this.emod$mDc$sp(a, b, ev);
   }

   default double emod$mDc$sp(final double a, final double b, final EuclideanRing ev) {
      return ev.emod$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static float emod$mFc$sp$(final EuclideanRingFunctions $this, final float a, final float b, final EuclideanRing ev) {
      return $this.emod$mFc$sp(a, b, ev);
   }

   default float emod$mFc$sp(final float a, final float b, final EuclideanRing ev) {
      return ev.emod$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static int emod$mIc$sp$(final EuclideanRingFunctions $this, final int a, final int b, final EuclideanRing ev) {
      return $this.emod$mIc$sp(a, b, ev);
   }

   default int emod$mIc$sp(final int a, final int b, final EuclideanRing ev) {
      return ev.emod$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static long emod$mJc$sp$(final EuclideanRingFunctions $this, final long a, final long b, final EuclideanRing ev) {
      return $this.emod$mJc$sp(a, b, ev);
   }

   default long emod$mJc$sp(final long a, final long b, final EuclideanRing ev) {
      return ev.emod$mcJ$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mDc$sp$(final EuclideanRingFunctions $this, final double a, final double b, final EuclideanRing ev) {
      return $this.equotmod$mDc$sp(a, b, ev);
   }

   default Tuple2 equotmod$mDc$sp(final double a, final double b, final EuclideanRing ev) {
      return ev.equotmod$mcD$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mFc$sp$(final EuclideanRingFunctions $this, final float a, final float b, final EuclideanRing ev) {
      return $this.equotmod$mFc$sp(a, b, ev);
   }

   default Tuple2 equotmod$mFc$sp(final float a, final float b, final EuclideanRing ev) {
      return ev.equotmod$mcF$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mIc$sp$(final EuclideanRingFunctions $this, final int a, final int b, final EuclideanRing ev) {
      return $this.equotmod$mIc$sp(a, b, ev);
   }

   default Tuple2 equotmod$mIc$sp(final int a, final int b, final EuclideanRing ev) {
      return ev.equotmod$mcI$sp(a, b);
   }

   // $FF: synthetic method
   static Tuple2 equotmod$mJc$sp$(final EuclideanRingFunctions $this, final long a, final long b, final EuclideanRing ev) {
      return $this.equotmod$mJc$sp(a, b, ev);
   }

   default Tuple2 equotmod$mJc$sp(final long a, final long b, final EuclideanRing ev) {
      return ev.equotmod$mcJ$sp(a, b);
   }

   static void $init$(final EuclideanRingFunctions $this) {
   }
}
