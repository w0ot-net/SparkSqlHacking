package algebra.ring;

import cats.kernel.Comparison;
import cats.kernel.Eq;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mdaB\t\u0013!\u0003\r\ta\u0006\u0005\u0006%\u0002!\ta\u0015\u0005\u0006/\u00021\t\u0001\u0017\u0005\u0006;\u00021\tA\u0018\u0005\u0006C\u0002!\tA\u0019\u0005\u0006Q\u00021\t!\u001b\u0005\u0006Y\u00021\t!\u001c\u0005\u0006a\u0002!\t!]\u0004\u0006iJA\t!\u001e\u0004\u0006#IA\tA\u001e\u0005\b\u00033IA\u0011AA\u000e\r%\ti\"\u0003I\u0001\u0004\u0003\ty\u0002C\u0003S\u0017\u0011\u00051\u000b\u0003\u0004m\u0017\u0011\u0005\u0011q\u000b\u0005\u0007Q.!\t!!\u0018\t\rA\\A\u0011IA2\u0011\u001d\tY'\u0003C\u0001\u0003[\u0012\u0011\u0003\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0015\t\u0019B#\u0001\u0003sS:<'\"A\u000b\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001QC\u0001\r&'\r\u0001\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\u0004\u0003:L\bc\u0001\u0011\"G5\t!#\u0003\u0002#%\t11+[4oK\u0012\u0004\"\u0001J\u0013\r\u0001\u0011Ia\u0005\u0001Q\u0001\u0002\u0003\u0015\ra\n\u0002\u0002\u0003F\u0011\u0001&\u0007\t\u00035%J!AK\u000e\u0003\u000f9{G\u000f[5oO\"BQ\u0005L\u0018:}\rCU\n\u0005\u0002\u001b[%\u0011af\u0007\u0002\fgB,7-[1mSj,G-M\u0003$aE\u001a$G\u0004\u0002\u001bc%\u0011!gG\u0001\u0005\u0005f$X-\r\u0003%iabbBA\u001b9\u001b\u00051$BA\u001c\u0017\u0003\u0019a$o\\8u}%\tA$M\u0003$umjDH\u0004\u0002\u001bw%\u0011AhG\u0001\u0006'\"|'\u000f^\u0019\u0005IQBD$M\u0003$\u007f\u0001\u0013\u0015I\u0004\u0002\u001b\u0001&\u0011\u0011iG\u0001\u0004\u0013:$\u0018\u0007\u0002\u00135qq\tTa\t#F\u000f\u001as!AG#\n\u0005\u0019[\u0012\u0001\u0002'p]\u001e\fD\u0001\n\u001b99E*1%\u0013&M\u0017:\u0011!DS\u0005\u0003\u0017n\tQA\u00127pCR\fD\u0001\n\u001b99E*1ET(R!:\u0011!dT\u0005\u0003!n\ta\u0001R8vE2,\u0017\u0007\u0002\u00135qq\ta\u0001J5oSR$C#\u0001+\u0011\u0005i)\u0016B\u0001,\u001c\u0005\u0011)f.\u001b;\u0002\u000bQ\fXo\u001c;\u0015\u0007\rJ6\fC\u0003[\u0005\u0001\u00071%A\u0001y\u0011\u0015a&\u00011\u0001$\u0003\u0005I\u0018\u0001\u0002;n_\u0012$2aI0a\u0011\u0015Q6\u00011\u0001$\u0011\u0015a6\u00011\u0001$\u0003!!\u0018/^8u[>$GcA2gOB!!\u0004Z\u0012$\u0013\t)7D\u0001\u0004UkBdWM\r\u0005\u00065\u0012\u0001\ra\t\u0005\u00069\u0012\u0001\raI\u0001\u0006MF,x\u000e\u001e\u000b\u0004G)\\\u0007\"\u0002.\u0006\u0001\u0004\u0019\u0003\"\u0002/\u0006\u0001\u0004\u0019\u0013\u0001\u00024n_\u0012$2a\t8p\u0011\u0015Qf\u00011\u0001$\u0011\u0015af\u00011\u0001$\u0003!1\u0017/^8u[>$GcA2sg\")!l\u0002a\u0001G!)Al\u0002a\u0001G\u0005\tBK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u0011\u0005\u0001J1\u0003B\u0005x\u0003#\u00012\u0001_?\u0000\u001b\u0005I(B\u0001>|\u0003\u0019YWM\u001d8fY*\tA0\u0001\u0003dCR\u001c\u0018B\u0001@z\u00059y%\u000fZ3s\rVt7\r^5p]N\u0004B!!\u0001\u0002\f9!\u00111AA\u0004\u001d\r)\u0014QA\u0005\u0002+%\u0019\u0011\u0011\u0002\u000b\u0002\u000fA\f7m[1hK&!\u0011QBA\b\u0005\u0015y%\u000fZ3s\u0015\r\tI\u0001\u0006\t\u0006A\u0005M\u0011qC\u0005\u0004\u0003+\u0011\"A\u0007+sk:\u001c\u0017\r^3e\t&4\u0018n]5p]\u001a+hn\u0019;j_:\u001c\bC\u0001\u0011\u0001\u0003\u0019a\u0014N\\5u}Q\tQO\u0001\ng_J\u001cu.\\7vi\u0006$\u0018N^3SS:<W\u0003BA\u0011\u0003O\u0019\u0002bC\r\u0002$\u0005\r\u0013\u0011\u000b\t\u0005A\u0001\t)\u0003E\u0002%\u0003O!\u0011BJ\u0006!\u0002\u0003\u0005)\u0019A\u0014)\u001f\u0005\u001dB&a\u000b\u00020\u0005M\u0012qGA\u001e\u0003\u007f\tda\t\u00192\u0003[\u0011\u0014\u0007\u0002\u00135qq\tda\t\u001e<\u0003ca\u0014\u0007\u0002\u00135qq\tdaI A\u0003k\t\u0015\u0007\u0002\u00135qq\tda\t#F\u0003s1\u0015\u0007\u0002\u00135qq\tdaI%K\u0003{Y\u0015\u0007\u0002\u00135qq\tda\t(P\u0003\u0003\u0002\u0016\u0007\u0002\u00135qq\u0001b!!\u0012\u0002L\u0005\u0015bb\u0001\u0011\u0002H%\u0019\u0011\u0011\n\n\u0002\rMKwM\\3e\u0013\u0011\ti%a\u0014\u00037\u0019|'/\u00113eSRLg/Z\"p[6,H/\u0019;jm\u0016<%o\\;q\u0015\r\tIE\u0005\t\u0006A\u0005M\u0013QE\u0005\u0004\u0003+\u0012\"aD\"p[6,H/\u0019;jm\u0016\u0014\u0016N\\4\u0015\r\u0005\u0015\u0012\u0011LA.\u0011\u0019QV\u00021\u0001\u0002&!1A,\u0004a\u0001\u0003K!b!!\n\u0002`\u0005\u0005\u0004B\u0002.\u000f\u0001\u0004\t)\u0003\u0003\u0004]\u001d\u0001\u0007\u0011Q\u0005\u000b\u0007\u0003K\n9'!\u001b\u0011\ri!\u0017QEA\u0013\u0011\u0019Qv\u00021\u0001\u0002&!1Al\u0004a\u0001\u0003K\tQ!\u00199qYf,B!a\u001c\u0002vQ!\u0011\u0011OA<!\u0011\u0001\u0003!a\u001d\u0011\u0007\u0011\n)\bB\u0003'!\t\u0007q\u0005C\u0004\u0002zA\u0001\u001d!!\u001d\u0002\u0005\u00154\b"
)
public interface TruncatedDivision extends Signed {
   static TruncatedDivision apply(final TruncatedDivision ev) {
      return TruncatedDivision$.MODULE$.apply(ev);
   }

   static Comparison comparison(final Object x, final Object y, final Order ev) {
      return TruncatedDivision$.MODULE$.comparison(x, y, ev);
   }

   static Object max(final Object x, final Object y, final Order ev) {
      return TruncatedDivision$.MODULE$.max(x, y, ev);
   }

   static Object min(final Object x, final Object y, final Order ev) {
      return TruncatedDivision$.MODULE$.min(x, y, ev);
   }

   static int compare(final Object x, final Object y, final Order ev) {
      return TruncatedDivision$.MODULE$.compare(x, y, ev);
   }

   static boolean gt(final Object x, final Object y, final PartialOrder ev) {
      return TruncatedDivision$.MODULE$.gt(x, y, ev);
   }

   static boolean gteqv(final Object x, final Object y, final PartialOrder ev) {
      return TruncatedDivision$.MODULE$.gteqv(x, y, ev);
   }

   static boolean lt(final Object x, final Object y, final PartialOrder ev) {
      return TruncatedDivision$.MODULE$.lt(x, y, ev);
   }

   static boolean lteqv(final Object x, final Object y, final PartialOrder ev) {
      return TruncatedDivision$.MODULE$.lteqv(x, y, ev);
   }

   static Option pmax(final Object x, final Object y, final PartialOrder ev) {
      return TruncatedDivision$.MODULE$.pmax(x, y, ev);
   }

   static Option pmin(final Object x, final Object y, final PartialOrder ev) {
      return TruncatedDivision$.MODULE$.pmin(x, y, ev);
   }

   static Option tryCompare(final Object x, final Object y, final PartialOrder ev) {
      return TruncatedDivision$.MODULE$.tryCompare(x, y, ev);
   }

   static double partialCompare(final Object x, final Object y, final PartialOrder ev) {
      return TruncatedDivision$.MODULE$.partialCompare(x, y, ev);
   }

   static boolean neqv(final Object x, final Object y, final Eq ev) {
      return TruncatedDivision$.MODULE$.neqv(x, y, ev);
   }

   static boolean eqv(final Object x, final Object y, final Eq ev) {
      return TruncatedDivision$.MODULE$.eqv(x, y, ev);
   }

   Object tquot(final Object x, final Object y);

   Object tmod(final Object x, final Object y);

   // $FF: synthetic method
   static Tuple2 tquotmod$(final TruncatedDivision $this, final Object x, final Object y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final Object x, final Object y) {
      return new Tuple2(this.tquot(x, y), this.tmod(x, y));
   }

   Object fquot(final Object x, final Object y);

   Object fmod(final Object x, final Object y);

   // $FF: synthetic method
   static Tuple2 fquotmod$(final TruncatedDivision $this, final Object x, final Object y) {
      return $this.fquotmod(x, y);
   }

   default Tuple2 fquotmod(final Object x, final Object y) {
      return new Tuple2(this.fquot(x, y), this.fmod(x, y));
   }

   // $FF: synthetic method
   static byte tquot$mcB$sp$(final TruncatedDivision $this, final byte x, final byte y) {
      return $this.tquot$mcB$sp(x, y);
   }

   default byte tquot$mcB$sp(final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(this.tquot(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   static double tquot$mcD$sp$(final TruncatedDivision $this, final double x, final double y) {
      return $this.tquot$mcD$sp(x, y);
   }

   default double tquot$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.tquot(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float tquot$mcF$sp$(final TruncatedDivision $this, final float x, final float y) {
      return $this.tquot$mcF$sp(x, y);
   }

   default float tquot$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.tquot(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int tquot$mcI$sp$(final TruncatedDivision $this, final int x, final int y) {
      return $this.tquot$mcI$sp(x, y);
   }

   default int tquot$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.tquot(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long tquot$mcJ$sp$(final TruncatedDivision $this, final long x, final long y) {
      return $this.tquot$mcJ$sp(x, y);
   }

   default long tquot$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.tquot(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static short tquot$mcS$sp$(final TruncatedDivision $this, final short x, final short y) {
      return $this.tquot$mcS$sp(x, y);
   }

   default short tquot$mcS$sp(final short x, final short y) {
      return BoxesRunTime.unboxToShort(this.tquot(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   static byte tmod$mcB$sp$(final TruncatedDivision $this, final byte x, final byte y) {
      return $this.tmod$mcB$sp(x, y);
   }

   default byte tmod$mcB$sp(final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(this.tmod(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   static double tmod$mcD$sp$(final TruncatedDivision $this, final double x, final double y) {
      return $this.tmod$mcD$sp(x, y);
   }

   default double tmod$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.tmod(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float tmod$mcF$sp$(final TruncatedDivision $this, final float x, final float y) {
      return $this.tmod$mcF$sp(x, y);
   }

   default float tmod$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.tmod(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int tmod$mcI$sp$(final TruncatedDivision $this, final int x, final int y) {
      return $this.tmod$mcI$sp(x, y);
   }

   default int tmod$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.tmod(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long tmod$mcJ$sp$(final TruncatedDivision $this, final long x, final long y) {
      return $this.tmod$mcJ$sp(x, y);
   }

   default long tmod$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.tmod(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static short tmod$mcS$sp$(final TruncatedDivision $this, final short x, final short y) {
      return $this.tmod$mcS$sp(x, y);
   }

   default short tmod$mcS$sp(final short x, final short y) {
      return BoxesRunTime.unboxToShort(this.tmod(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcB$sp$(final TruncatedDivision $this, final byte x, final byte y) {
      return $this.tquotmod$mcB$sp(x, y);
   }

   default Tuple2 tquotmod$mcB$sp(final byte x, final byte y) {
      return this.tquotmod(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcD$sp$(final TruncatedDivision $this, final double x, final double y) {
      return $this.tquotmod$mcD$sp(x, y);
   }

   default Tuple2 tquotmod$mcD$sp(final double x, final double y) {
      return this.tquotmod(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcF$sp$(final TruncatedDivision $this, final float x, final float y) {
      return $this.tquotmod$mcF$sp(x, y);
   }

   default Tuple2 tquotmod$mcF$sp(final float x, final float y) {
      return this.tquotmod(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcI$sp$(final TruncatedDivision $this, final int x, final int y) {
      return $this.tquotmod$mcI$sp(x, y);
   }

   default Tuple2 tquotmod$mcI$sp(final int x, final int y) {
      return this.tquotmod(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcJ$sp$(final TruncatedDivision $this, final long x, final long y) {
      return $this.tquotmod$mcJ$sp(x, y);
   }

   default Tuple2 tquotmod$mcJ$sp(final long x, final long y) {
      return this.tquotmod(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$mcS$sp$(final TruncatedDivision $this, final short x, final short y) {
      return $this.tquotmod$mcS$sp(x, y);
   }

   default Tuple2 tquotmod$mcS$sp(final short x, final short y) {
      return this.tquotmod(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   // $FF: synthetic method
   static byte fquot$mcB$sp$(final TruncatedDivision $this, final byte x, final byte y) {
      return $this.fquot$mcB$sp(x, y);
   }

   default byte fquot$mcB$sp(final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(this.fquot(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   static double fquot$mcD$sp$(final TruncatedDivision $this, final double x, final double y) {
      return $this.fquot$mcD$sp(x, y);
   }

   default double fquot$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.fquot(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float fquot$mcF$sp$(final TruncatedDivision $this, final float x, final float y) {
      return $this.fquot$mcF$sp(x, y);
   }

   default float fquot$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.fquot(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int fquot$mcI$sp$(final TruncatedDivision $this, final int x, final int y) {
      return $this.fquot$mcI$sp(x, y);
   }

   default int fquot$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.fquot(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long fquot$mcJ$sp$(final TruncatedDivision $this, final long x, final long y) {
      return $this.fquot$mcJ$sp(x, y);
   }

   default long fquot$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.fquot(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static short fquot$mcS$sp$(final TruncatedDivision $this, final short x, final short y) {
      return $this.fquot$mcS$sp(x, y);
   }

   default short fquot$mcS$sp(final short x, final short y) {
      return BoxesRunTime.unboxToShort(this.fquot(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   static byte fmod$mcB$sp$(final TruncatedDivision $this, final byte x, final byte y) {
      return $this.fmod$mcB$sp(x, y);
   }

   default byte fmod$mcB$sp(final byte x, final byte y) {
      return BoxesRunTime.unboxToByte(this.fmod(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
   }

   // $FF: synthetic method
   static double fmod$mcD$sp$(final TruncatedDivision $this, final double x, final double y) {
      return $this.fmod$mcD$sp(x, y);
   }

   default double fmod$mcD$sp(final double x, final double y) {
      return BoxesRunTime.unboxToDouble(this.fmod(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
   }

   // $FF: synthetic method
   static float fmod$mcF$sp$(final TruncatedDivision $this, final float x, final float y) {
      return $this.fmod$mcF$sp(x, y);
   }

   default float fmod$mcF$sp(final float x, final float y) {
      return BoxesRunTime.unboxToFloat(this.fmod(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
   }

   // $FF: synthetic method
   static int fmod$mcI$sp$(final TruncatedDivision $this, final int x, final int y) {
      return $this.fmod$mcI$sp(x, y);
   }

   default int fmod$mcI$sp(final int x, final int y) {
      return BoxesRunTime.unboxToInt(this.fmod(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
   }

   // $FF: synthetic method
   static long fmod$mcJ$sp$(final TruncatedDivision $this, final long x, final long y) {
      return $this.fmod$mcJ$sp(x, y);
   }

   default long fmod$mcJ$sp(final long x, final long y) {
      return BoxesRunTime.unboxToLong(this.fmod(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
   }

   // $FF: synthetic method
   static short fmod$mcS$sp$(final TruncatedDivision $this, final short x, final short y) {
      return $this.fmod$mcS$sp(x, y);
   }

   default short fmod$mcS$sp(final short x, final short y) {
      return BoxesRunTime.unboxToShort(this.fmod(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcB$sp$(final TruncatedDivision $this, final byte x, final byte y) {
      return $this.fquotmod$mcB$sp(x, y);
   }

   default Tuple2 fquotmod$mcB$sp(final byte x, final byte y) {
      return this.fquotmod(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcD$sp$(final TruncatedDivision $this, final double x, final double y) {
      return $this.fquotmod$mcD$sp(x, y);
   }

   default Tuple2 fquotmod$mcD$sp(final double x, final double y) {
      return this.fquotmod(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcF$sp$(final TruncatedDivision $this, final float x, final float y) {
      return $this.fquotmod$mcF$sp(x, y);
   }

   default Tuple2 fquotmod$mcF$sp(final float x, final float y) {
      return this.fquotmod(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcI$sp$(final TruncatedDivision $this, final int x, final int y) {
      return $this.fquotmod$mcI$sp(x, y);
   }

   default Tuple2 fquotmod$mcI$sp(final int x, final int y) {
      return this.fquotmod(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcJ$sp$(final TruncatedDivision $this, final long x, final long y) {
      return $this.fquotmod$mcJ$sp(x, y);
   }

   default Tuple2 fquotmod$mcJ$sp(final long x, final long y) {
      return this.fquotmod(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
   }

   // $FF: synthetic method
   static Tuple2 fquotmod$mcS$sp$(final TruncatedDivision $this, final short x, final short y) {
      return $this.fquotmod$mcS$sp(x, y);
   }

   default Tuple2 fquotmod$mcS$sp(final short x, final short y) {
      return this.fquotmod(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
   }

   static void $init$(final TruncatedDivision $this) {
   }

   public interface forCommutativeRing extends TruncatedDivision, Signed.forAdditiveCommutativeGroup, CommutativeRing {
      // $FF: synthetic method
      static Object fmod$(final forCommutativeRing $this, final Object x, final Object y) {
         return $this.fmod(x, y);
      }

      default Object fmod(final Object x, final Object y) {
         Object tm = this.tmod(x, y);
         return this.signum(tm) == -this.signum(y) ? this.plus(tm, y) : tm;
      }

      // $FF: synthetic method
      static Object fquot$(final forCommutativeRing $this, final Object x, final Object y) {
         return $this.fquot(x, y);
      }

      default Object fquot(final Object x, final Object y) {
         Tuple2 var5 = this.tquotmod(x, y);
         if (var5 != null) {
            Object tq = var5._1();
            Object tm = var5._2();
            Tuple2 var3 = new Tuple2(tq, tm);
            Object tq = var3._1();
            Object tm = var3._2();
            return this.signum(tm) == -this.signum(y) ? this.minus(tq, this.one()) : tq;
         } else {
            throw new MatchError(var5);
         }
      }

      // $FF: synthetic method
      static Tuple2 fquotmod$(final forCommutativeRing $this, final Object x, final Object y) {
         return $this.fquotmod(x, y);
      }

      default Tuple2 fquotmod(final Object x, final Object y) {
         Tuple2 var5 = this.tquotmod(x, y);
         if (var5 != null) {
            Object tq = var5._1();
            Object tm = var5._2();
            Tuple2 var3 = new Tuple2(tq, tm);
            Object tq = var3._1();
            Object tm = var3._2();
            boolean signsDiffer = this.signum(tm) == -this.signum(y);
            Object fq = signsDiffer ? this.minus(tq, this.one()) : tq;
            Object fm = signsDiffer ? this.plus(tm, y) : tm;
            return new Tuple2(fq, fm);
         } else {
            throw new MatchError(var5);
         }
      }

      // $FF: synthetic method
      static byte fmod$mcB$sp$(final forCommutativeRing $this, final byte x, final byte y) {
         return $this.fmod$mcB$sp(x, y);
      }

      default byte fmod$mcB$sp(final byte x, final byte y) {
         return BoxesRunTime.unboxToByte(this.fmod(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
      }

      // $FF: synthetic method
      static double fmod$mcD$sp$(final forCommutativeRing $this, final double x, final double y) {
         return $this.fmod$mcD$sp(x, y);
      }

      default double fmod$mcD$sp(final double x, final double y) {
         return BoxesRunTime.unboxToDouble(this.fmod(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
      }

      // $FF: synthetic method
      static float fmod$mcF$sp$(final forCommutativeRing $this, final float x, final float y) {
         return $this.fmod$mcF$sp(x, y);
      }

      default float fmod$mcF$sp(final float x, final float y) {
         return BoxesRunTime.unboxToFloat(this.fmod(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
      }

      // $FF: synthetic method
      static int fmod$mcI$sp$(final forCommutativeRing $this, final int x, final int y) {
         return $this.fmod$mcI$sp(x, y);
      }

      default int fmod$mcI$sp(final int x, final int y) {
         return BoxesRunTime.unboxToInt(this.fmod(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
      }

      // $FF: synthetic method
      static long fmod$mcJ$sp$(final forCommutativeRing $this, final long x, final long y) {
         return $this.fmod$mcJ$sp(x, y);
      }

      default long fmod$mcJ$sp(final long x, final long y) {
         return BoxesRunTime.unboxToLong(this.fmod(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
      }

      // $FF: synthetic method
      static short fmod$mcS$sp$(final forCommutativeRing $this, final short x, final short y) {
         return $this.fmod$mcS$sp(x, y);
      }

      default short fmod$mcS$sp(final short x, final short y) {
         return BoxesRunTime.unboxToShort(this.fmod(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
      }

      // $FF: synthetic method
      static byte fquot$mcB$sp$(final forCommutativeRing $this, final byte x, final byte y) {
         return $this.fquot$mcB$sp(x, y);
      }

      default byte fquot$mcB$sp(final byte x, final byte y) {
         return BoxesRunTime.unboxToByte(this.fquot(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y)));
      }

      // $FF: synthetic method
      static double fquot$mcD$sp$(final forCommutativeRing $this, final double x, final double y) {
         return $this.fquot$mcD$sp(x, y);
      }

      default double fquot$mcD$sp(final double x, final double y) {
         return BoxesRunTime.unboxToDouble(this.fquot(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y)));
      }

      // $FF: synthetic method
      static float fquot$mcF$sp$(final forCommutativeRing $this, final float x, final float y) {
         return $this.fquot$mcF$sp(x, y);
      }

      default float fquot$mcF$sp(final float x, final float y) {
         return BoxesRunTime.unboxToFloat(this.fquot(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y)));
      }

      // $FF: synthetic method
      static int fquot$mcI$sp$(final forCommutativeRing $this, final int x, final int y) {
         return $this.fquot$mcI$sp(x, y);
      }

      default int fquot$mcI$sp(final int x, final int y) {
         return BoxesRunTime.unboxToInt(this.fquot(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y)));
      }

      // $FF: synthetic method
      static long fquot$mcJ$sp$(final forCommutativeRing $this, final long x, final long y) {
         return $this.fquot$mcJ$sp(x, y);
      }

      default long fquot$mcJ$sp(final long x, final long y) {
         return BoxesRunTime.unboxToLong(this.fquot(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y)));
      }

      // $FF: synthetic method
      static short fquot$mcS$sp$(final forCommutativeRing $this, final short x, final short y) {
         return $this.fquot$mcS$sp(x, y);
      }

      default short fquot$mcS$sp(final short x, final short y) {
         return BoxesRunTime.unboxToShort(this.fquot(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y)));
      }

      // $FF: synthetic method
      static Tuple2 fquotmod$mcB$sp$(final forCommutativeRing $this, final byte x, final byte y) {
         return $this.fquotmod$mcB$sp(x, y);
      }

      default Tuple2 fquotmod$mcB$sp(final byte x, final byte y) {
         return this.fquotmod(BoxesRunTime.boxToByte(x), BoxesRunTime.boxToByte(y));
      }

      // $FF: synthetic method
      static Tuple2 fquotmod$mcD$sp$(final forCommutativeRing $this, final double x, final double y) {
         return $this.fquotmod$mcD$sp(x, y);
      }

      default Tuple2 fquotmod$mcD$sp(final double x, final double y) {
         return this.fquotmod(BoxesRunTime.boxToDouble(x), BoxesRunTime.boxToDouble(y));
      }

      // $FF: synthetic method
      static Tuple2 fquotmod$mcF$sp$(final forCommutativeRing $this, final float x, final float y) {
         return $this.fquotmod$mcF$sp(x, y);
      }

      default Tuple2 fquotmod$mcF$sp(final float x, final float y) {
         return this.fquotmod(BoxesRunTime.boxToFloat(x), BoxesRunTime.boxToFloat(y));
      }

      // $FF: synthetic method
      static Tuple2 fquotmod$mcI$sp$(final forCommutativeRing $this, final int x, final int y) {
         return $this.fquotmod$mcI$sp(x, y);
      }

      default Tuple2 fquotmod$mcI$sp(final int x, final int y) {
         return this.fquotmod(BoxesRunTime.boxToInteger(x), BoxesRunTime.boxToInteger(y));
      }

      // $FF: synthetic method
      static Tuple2 fquotmod$mcJ$sp$(final forCommutativeRing $this, final long x, final long y) {
         return $this.fquotmod$mcJ$sp(x, y);
      }

      default Tuple2 fquotmod$mcJ$sp(final long x, final long y) {
         return this.fquotmod(BoxesRunTime.boxToLong(x), BoxesRunTime.boxToLong(y));
      }

      // $FF: synthetic method
      static Tuple2 fquotmod$mcS$sp$(final forCommutativeRing $this, final short x, final short y) {
         return $this.fquotmod$mcS$sp(x, y);
      }

      default Tuple2 fquotmod$mcS$sp(final short x, final short y) {
         return this.fquotmod(BoxesRunTime.boxToShort(x), BoxesRunTime.boxToShort(y));
      }

      static void $init$(final forCommutativeRing $this) {
      }
   }
}
