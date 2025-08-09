package cats.kernel;

import scala.Function2;
import scala.Option;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EaaB\u0005\u000b!\u0003\r\ta\u0004\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0007\u000b\u0002\u0001K\u0011\u000b$\b\u000b9S\u0001\u0012A(\u0007\u000b%Q\u0001\u0012\u0001)\t\u000bu#A\u0011\u00010\t\u000b}#AQ\u00011\t\u000bQ$A\u0011A;\t\u0013\u0005\u0005A!!A\u0005\n\u0005\r!\u0001\u0002\"b]\u0012T!a\u0003\u0007\u0002\r-,'O\\3m\u0015\u0005i\u0011\u0001B2biN\u001c\u0001!\u0006\u0002\u0011;M\u0019\u0001!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\u0007\u0005s\u0017\u0010E\u0002\u00193mi\u0011AC\u0005\u00035)\u0011\u0011bU3nS\u001e\u0014x.\u001e9\u0011\u0005qiB\u0002\u0001\u0003\n=\u0001\u0001\u000b\u0011!AC\u0002}\u0011\u0011!Q\t\u0003AE\u0001\"AE\u0011\n\u0005\t\u001a\"a\u0002(pi\"Lgn\u001a\u0015\u0007;\u0011:\u0013GN\u001e\u0011\u0005I)\u0013B\u0001\u0014\u0014\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rB\u0013f\u000b\u0016\u000f\u0005II\u0013B\u0001\u0016\u0014\u0003\rIe\u000e^\u0019\u0005I1\u0002DC\u0004\u0002.a5\taF\u0003\u00020\u001d\u00051AH]8pizJ\u0011\u0001F\u0019\u0006GI\u001aT\u0007\u000e\b\u0003%MJ!\u0001N\n\u0002\t1{gnZ\u0019\u0005I1\u0002D#M\u0003$oaR\u0014H\u0004\u0002\u0013q%\u0011\u0011hE\u0001\u0006\r2|\u0017\r^\u0019\u0005I1\u0002D#M\u0003$yuzdH\u0004\u0002\u0013{%\u0011ahE\u0001\u0007\t>,(\r\\32\t\u0011b\u0003\u0007F\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\t\u0003\"AE\"\n\u0005\u0011\u001b\"\u0001B+oSR\f\u0001C]3qK\u0006$X\rZ\"p[\nLg.\u001a(\u0015\u0007m9\u0015\nC\u0003I\u0005\u0001\u00071$A\u0001b\u0011\u0015Q%\u00011\u0001L\u0003\u0005q\u0007C\u0001\nM\u0013\ti5CA\u0002J]R\fAAQ1oIB\u0011\u0001\u0004B\n\u0004\tE+\u0006c\u0001\rS)&\u00111K\u0003\u0002\u0013'\u0016l\u0017n\u001a:pkB4UO\\2uS>t7\u000f\u0005\u0002\u0019\u0001A\u0011akW\u0007\u0002/*\u0011\u0001,W\u0001\u0003S>T\u0011AW\u0001\u0005U\u00064\u0018-\u0003\u0002]/\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012aT\u0001\u0006CB\u0004H._\u000b\u0003C\u0012$\"A\u00198\u0011\u0007a\u00011\r\u0005\u0002\u001dI\u0012IaD\u0002Q\u0001\u0002\u0003\u0015\ra\b\u0015\u0007I\u00122\u0007N\u001b72\u000b\rB\u0013f\u001a\u00162\t\u0011b\u0003\u0007F\u0019\u0006GI\u001a\u0014\u000eN\u0019\u0005I1\u0002D#M\u0003$oaZ\u0017(\r\u0003%YA\"\u0012'B\u0012={5t\u0014\u0007\u0002\u0013-aQAQa\u001c\u0004A\u0004\t\f!!\u001a<)\u0005\u0019\t\bC\u0001\ns\u0013\t\u00198C\u0001\u0004j]2Lg.Z\u0001\tS:\u001cH/\u00198dKV\u0011a/\u001f\u000b\u0003oj\u00042\u0001\u0007\u0001y!\ta\u0012\u0010B\u0003\u001f\u000f\t\u0007q\u0004C\u0003|\u000f\u0001\u0007A0A\u0002d[\n\u0004RAE?yqbL!A`\n\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004FA\u0004r\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0001\u0005\u0003\u0002\b\u00055QBAA\u0005\u0015\r\tY!W\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u0010\u0005%!AB(cU\u0016\u001cG\u000f"
)
public interface Band extends Semigroup {
   static Band instance(final Function2 cmb) {
      return Band$.MODULE$.instance(cmb);
   }

   static Band apply(final Band ev) {
      return Band$.MODULE$.apply(ev);
   }

   static boolean isIdempotent(final Semigroup ev) {
      return Band$.MODULE$.isIdempotent(ev);
   }

   static boolean isCommutative(final Semigroup ev) {
      return Band$.MODULE$.isCommutative(ev);
   }

   static Object maybeCombine(final Object x, final Option oy, final Semigroup ev) {
      return Band$.MODULE$.maybeCombine(x, oy, ev);
   }

   static Object maybeCombine(final Option ox, final Object y, final Semigroup ev) {
      return Band$.MODULE$.maybeCombine(ox, y, ev);
   }

   // $FF: synthetic method
   static Object repeatedCombineN$(final Band $this, final Object a, final int n) {
      return $this.repeatedCombineN(a, n);
   }

   default Object repeatedCombineN(final Object a, final int n) {
      return a;
   }

   // $FF: synthetic method
   static double repeatedCombineN$mcD$sp$(final Band $this, final double a, final int n) {
      return $this.repeatedCombineN$mcD$sp(a, n);
   }

   default double repeatedCombineN$mcD$sp(final double a, final int n) {
      return BoxesRunTime.unboxToDouble(this.repeatedCombineN(BoxesRunTime.boxToDouble(a), n));
   }

   // $FF: synthetic method
   static float repeatedCombineN$mcF$sp$(final Band $this, final float a, final int n) {
      return $this.repeatedCombineN$mcF$sp(a, n);
   }

   default float repeatedCombineN$mcF$sp(final float a, final int n) {
      return BoxesRunTime.unboxToFloat(this.repeatedCombineN(BoxesRunTime.boxToFloat(a), n));
   }

   // $FF: synthetic method
   static int repeatedCombineN$mcI$sp$(final Band $this, final int a, final int n) {
      return $this.repeatedCombineN$mcI$sp(a, n);
   }

   default int repeatedCombineN$mcI$sp(final int a, final int n) {
      return BoxesRunTime.unboxToInt(this.repeatedCombineN(BoxesRunTime.boxToInteger(a), n));
   }

   // $FF: synthetic method
   static long repeatedCombineN$mcJ$sp$(final Band $this, final long a, final int n) {
      return $this.repeatedCombineN$mcJ$sp(a, n);
   }

   default long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return BoxesRunTime.unboxToLong(this.repeatedCombineN(BoxesRunTime.boxToLong(a), n));
   }

   static void $init$(final Band $this) {
   }
}
