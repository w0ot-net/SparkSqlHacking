package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}ha\u0002\u0017.!\u0003\r\nA\r\u0005\u0006u\u00011\ta\u000f\u0005\u0006\u001b\u00021\tA\u0014\u0005\u0006'\u00021\t\u0001\u0016\u0005\u00063\u00021\tA\u0017\u0005\u0006?\u00021\t\u0001\u0019\u0005\u0006K\u00021\tA\u001a\u0005\u0006W\u00021\t\u0001\u001c\u0005\u0006u\u00021\ta\u001f\u0005\b\u0003\u0003\u0001a\u0011AA\u0002\u0011\u001d\ty\u0001\u0001D\u0001\u0003#Aq!a\u0007\u0001\r\u0003\ti\u0002C\u0004\u0002(\u00011\t!!\u000b\b\u000f\u0005\rS\u0006#\u0001\u0002F\u00191A&\fE\u0001\u0003\u000fBq!a\u0014\u000f\t\u0003\t\t\u0006C\u0004\u0002T9!)!!\u0016\t\u0013\u0005-dB1A\u0005\b\u00055\u0004\u0002CA9\u001d\u0001\u0006i!a\u001c\t\u0013\u0005MdB1A\u0005\b\u0005U\u0004\u0002CA=\u001d\u0001\u0006i!a\u001e\t\u0013\u0005mdB1A\u0005\b\u0005u\u0004\u0002CAA\u001d\u0001\u0006i!a \t\u0013\u0005\reB1A\u0005\b\u0005\u0015\u0005\u0002CAE\u001d\u0001\u0006i!a\"\t\u0013\u0005-eB1A\u0005\b\u00055\u0005\u0002CAI\u001d\u0001\u0006i!a$\t\u0013\u0005MeB1A\u0005\b\u0005U\u0005\u0002CAM\u001d\u0001\u0006i!a&\t\u0013\u0005meB1A\u0005\b\u0005u\u0005\u0002CAQ\u001d\u0001\u0006i!a(\t\u0013\u0005\rfB1A\u0005\b\u0005\u0015\u0006\u0002CAU\u001d\u0001\u0006i!a*\t\u0013\u0005-fB1A\u0005\b\u00055\u0006\u0002CAY\u001d\u0001\u0006i!a,\t\u0013\u0005MfB1A\u0005\b\u0005U\u0006\u0002CA]\u001d\u0001\u0006i!a.\t\u0013\u0005mfB1A\u0005\b\u0005u\u0006\u0002CAd\u001d\u0001\u0006i!a0\t\u0013\u0005%gB1A\u0005\b\u0005-\u0007\u0002CAk\u001d\u0001\u0006i!!4\t\u0013\u0005]gB1A\u0005\b\u0005e\u0007\u0002CAr\u001d\u0001\u0006i!a7\t\u000f\u0005\u0015h\u0002b\u0001\u0002h\ni1i\u001c8wKJ$\u0018M\u00197f)>T!AL\u0018\u0002\t5\fG\u000f\u001b\u0006\u0002a\u0005)1\u000f]5sK\u000e\u0001QCA\u001a?'\t\u0001A\u0007\u0005\u00026q5\taGC\u00018\u0003\u0015\u00198-\u00197b\u0013\tIdGA\u0002B]f\f\u0001B\u001a:p[\nKH/\u001a\u000b\u0003y!\u0003\"!\u0010 \r\u0001\u0011Iq\b\u0001Q\u0001\u0002\u0003\u0015\r\u0001\u0011\u0002\u0002\u0003F\u0011\u0011\t\u000e\t\u0003k\tK!a\u0011\u001c\u0003\u000f9{G\u000f[5oO\"\u0012a(\u0012\t\u0003k\u0019K!a\u0012\u001c\u0003\u0017M\u0004XmY5bY&TX\r\u001a\u0005\u0006\u0013\u0006\u0001\rAS\u0001\u0002]B\u0011QgS\u0005\u0003\u0019Z\u0012AAQ=uK\u0006IaM]8n'\"|'\u000f\u001e\u000b\u0003y=CQ!\u0013\u0002A\u0002A\u0003\"!N)\n\u0005I3$!B*i_J$\u0018a\u00024s_6Le\u000e\u001e\u000b\u0003yUCQ!S\u0002A\u0002Y\u0003\"!N,\n\u0005a3$aA%oi\u0006AaM]8n\u0019>tw\r\u0006\u0002=7\")\u0011\n\u0002a\u00019B\u0011Q'X\u0005\u0003=Z\u0012A\u0001T8oO\u0006IaM]8n\r2|\u0017\r\u001e\u000b\u0003y\u0005DQ!S\u0003A\u0002\t\u0004\"!N2\n\u0005\u00114$!\u0002$m_\u0006$\u0018A\u00034s_6$u.\u001e2mKR\u0011Ah\u001a\u0005\u0006\u0013\u001a\u0001\r\u0001\u001b\t\u0003k%L!A\u001b\u001c\u0003\r\u0011{WO\u00197f\u0003)1'o\\7CS\u001eLe\u000e\u001e\u000b\u0003y5DQ!S\u0004A\u00029\u0004\"a\\<\u000f\u0005A,hBA9u\u001b\u0005\u0011(BA:2\u0003\u0019a$o\\8u}%\tq'\u0003\u0002wm\u00059\u0001/Y2lC\u001e,\u0017B\u0001=z\u0005\u0019\u0011\u0015nZ%oi*\u0011aON\u0001\u000fMJ|WNQ5h\t\u0016\u001c\u0017.\\1m)\taD\u0010C\u0003J\u0011\u0001\u0007Q\u0010\u0005\u0002p}&\u0011q0\u001f\u0002\u000b\u0005&<G)Z2j[\u0006d\u0017\u0001\u00044s_6\u0014\u0016\r^5p]\u0006dGc\u0001\u001f\u0002\u0006!1\u0011*\u0003a\u0001\u0003\u000f\u0001B!!\u0003\u0002\f5\tQ&C\u0002\u0002\u000e5\u0012\u0001BU1uS>t\u0017\r\\\u0001\u000eMJ|W.\u00117hK\n\u0014\u0018-[2\u0015\u0007q\n\u0019\u0002\u0003\u0004J\u0015\u0001\u0007\u0011Q\u0003\t\u0005\u0003\u0013\t9\"C\u0002\u0002\u001a5\u0012\u0011\"\u00117hK\n\u0014\u0018-[2\u0002\u0011\u0019\u0014x.\u001c*fC2$2\u0001PA\u0010\u0011\u0019I5\u00021\u0001\u0002\"A!\u0011\u0011BA\u0012\u0013\r\t)#\f\u0002\u0005%\u0016\fG.\u0001\u0005ge>lG+\u001f9f+\u0011\tY#a\u000f\u0015\t\u00055\u0012q\b\u000b\u0004y\u0005=\u0002\"CA\u0019\u0019\u0005\u0005\t9AA\u001a\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003\u0013\t)$!\u000f\n\u0007\u0005]RFA\bD_:4XM\u001d;bE2,gI]8n!\ri\u00141\b\u0003\u0007\u0003{a!\u0019\u0001!\u0003\u0003\tCq!!\u0011\r\u0001\u0004\tI$A\u0001c\u00035\u0019uN\u001c<feR\f'\r\\3U_B\u0019\u0011\u0011\u0002\b\u0014\u00079\tI\u0005E\u00026\u0003\u0017J1!!\u00147\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\"!!\u0012\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005]\u0013Q\f\u000b\u0005\u00033\ny\u0006E\u0003\u0002\n\u0001\tY\u0006E\u0002>\u0003;\"Qa\u0010\tC\u0002\u0001Cq!!\u0019\u0011\u0001\b\tI&\u0001\u0002fm\"\u001a\u0001#!\u001a\u0011\u0007U\n9'C\u0002\u0002jY\u0012a!\u001b8mS:,\u0017!E\"p]Z,'\u000f^1cY\u0016$vNQ=uKV\u0011\u0011q\u000e\t\u0005\u0003\u0013\u0001!*\u0001\nD_:4XM\u001d;bE2,Gk\u001c\"zi\u0016\u0004\u0013AE\"p]Z,'\u000f^1cY\u0016$vn\u00155peR,\"!a\u001e\u0011\t\u0005%\u0001\u0001U\u0001\u0014\u0007>tg/\u001a:uC\ndW\rV8TQ>\u0014H\u000fI\u0001\u0011\u0007>tg/\u001a:uC\ndW\rV8J]R,\"!a \u0011\t\u0005%\u0001AV\u0001\u0012\u0007>tg/\u001a:uC\ndW\rV8J]R\u0004\u0013!E\"p]Z,'\u000f^1cY\u0016$v\u000eT8oOV\u0011\u0011q\u0011\t\u0005\u0003\u0013\u0001A,\u0001\nD_:4XM\u001d;bE2,Gk\u001c'p]\u001e\u0004\u0013aE\"p]Z,'\u000f^1cY\u0016$vNQ5h\u0013:$XCAAH!\u0011\tI\u0001\u00018\u0002)\r{gN^3si\u0006\u0014G.\u001a+p\u0005&<\u0017J\u001c;!\u0003I\u0019uN\u001c<feR\f'\r\\3U_\u001acw.\u0019;\u0016\u0005\u0005]\u0005\u0003BA\u0005\u0001\t\f1cQ8om\u0016\u0014H/\u00192mKR{g\t\\8bi\u0002\n1cQ8om\u0016\u0014H/\u00192mKR{Gi\\;cY\u0016,\"!a(\u0011\t\u0005%\u0001\u0001[\u0001\u0015\u0007>tg/\u001a:uC\ndW\rV8E_V\u0014G.\u001a\u0011\u0002/\r{gN^3si\u0006\u0014G.\u001a+p\u0005&<G)Z2j[\u0006dWCAAT!\u0011\tI\u0001A?\u00021\r{gN^3si\u0006\u0014G.\u001a+p\u0005&<G)Z2j[\u0006d\u0007%A\u000bD_:4XM\u001d;bE2,Gk\u001c*bi&|g.\u00197\u0016\u0005\u0005=\u0006#BA\u0005\u0001\u0005\u001d\u0011AF\"p]Z,'\u000f^1cY\u0016$vNU1uS>t\u0017\r\u001c\u0011\u0002-\r{gN^3si\u0006\u0014G.\u001a+p\u00032<WM\u0019:bS\u000e,\"!a.\u0011\u000b\u0005%\u0001!!\u0006\u0002/\r{gN^3si\u0006\u0014G.\u001a+p\u00032<WM\u0019:bS\u000e\u0004\u0013!F\"p]Z,'\u000f^1cY\u0016$vnU1gK2{gnZ\u000b\u0003\u0003\u007f\u0003R!!\u0003\u0001\u0003\u0003\u0004B!!\u0003\u0002D&\u0019\u0011QY\u0017\u0003\u0011M\u000bg-\u001a'p]\u001e\facQ8om\u0016\u0014H/\u00192mKR{7+\u00194f\u0019>tw\rI\u0001\u0014\u0007>tg/\u001a:uC\ndW\rV8Ok6\u0014WM]\u000b\u0003\u0003\u001b\u0004R!!\u0003\u0001\u0003\u001f\u0004B!!\u0003\u0002R&\u0019\u00111[\u0017\u0003\r9+XNY3s\u0003Q\u0019uN\u001c<feR\f'\r\\3U_:+XNY3sA\u0005!2i\u001c8wKJ$\u0018M\u00197f)>t\u0015\r^;sC2,\"!a7\u0011\u000b\u0005%\u0001!!8\u0011\t\u0005%\u0011q\\\u0005\u0004\u0003Cl#a\u0002(biV\u0014\u0018\r\\\u0001\u0016\u0007>tg/\u001a:uC\ndW\rV8OCR,(/\u00197!\u0003Q\u0019wN\u001c<feR\f'\r\\3U_\u000e{W\u000e\u001d7fqV!\u0011\u0011^Az)\u0011\tY/!>\u0011\r\u0005%\u0011Q^Ay\u0013\r\ty/\f\u0002\u0015\u0007>tg/\u001a:uC\ndW\rV8D_6\u0004H.\u001a=\u0011\u0007u\n\u0019\u0010B\u0003@W\t\u0007\u0001\tC\u0005\u0002x.\n\t\u0011q\u0001\u0002z\u0006YQM^5eK:\u001cW\rJ\u00197!\u0019\tI!a?\u0002r&\u0019\u0011Q`\u0017\u0003\u0011%sG/Z4sC2\u0004"
)
public interface ConvertableTo {
   static ConvertableToComplex convertableToComplex(final Integral evidence$16) {
      return ConvertableTo$.MODULE$.convertableToComplex(evidence$16);
   }

   static ConvertableTo ConvertableToNatural() {
      return ConvertableTo$.MODULE$.ConvertableToNatural();
   }

   static ConvertableTo ConvertableToNumber() {
      return ConvertableTo$.MODULE$.ConvertableToNumber();
   }

   static ConvertableTo ConvertableToSafeLong() {
      return ConvertableTo$.MODULE$.ConvertableToSafeLong();
   }

   static ConvertableTo ConvertableToAlgebraic() {
      return ConvertableTo$.MODULE$.ConvertableToAlgebraic();
   }

   static ConvertableTo ConvertableToRational() {
      return ConvertableTo$.MODULE$.ConvertableToRational();
   }

   static ConvertableTo ConvertableToBigDecimal() {
      return ConvertableTo$.MODULE$.ConvertableToBigDecimal();
   }

   static ConvertableTo ConvertableToDouble() {
      return ConvertableTo$.MODULE$.ConvertableToDouble();
   }

   static ConvertableTo ConvertableToFloat() {
      return ConvertableTo$.MODULE$.ConvertableToFloat();
   }

   static ConvertableTo ConvertableToBigInt() {
      return ConvertableTo$.MODULE$.ConvertableToBigInt();
   }

   static ConvertableTo ConvertableToLong() {
      return ConvertableTo$.MODULE$.ConvertableToLong();
   }

   static ConvertableTo ConvertableToInt() {
      return ConvertableTo$.MODULE$.ConvertableToInt();
   }

   static ConvertableTo ConvertableToShort() {
      return ConvertableTo$.MODULE$.ConvertableToShort();
   }

   static ConvertableTo ConvertableToByte() {
      return ConvertableTo$.MODULE$.ConvertableToByte();
   }

   static ConvertableTo apply(final ConvertableTo ev) {
      return ConvertableTo$.MODULE$.apply(ev);
   }

   Object fromByte(final byte n);

   Object fromShort(final short n);

   Object fromInt(final int n);

   Object fromLong(final long n);

   Object fromFloat(final float n);

   Object fromDouble(final double n);

   Object fromBigInt(final BigInt n);

   Object fromBigDecimal(final BigDecimal n);

   Object fromRational(final Rational n);

   Object fromAlgebraic(final Algebraic n);

   Object fromReal(final Real n);

   Object fromType(final Object b, final ConvertableFrom evidence$1);

   // $FF: synthetic method
   static boolean fromByte$mcZ$sp$(final ConvertableTo $this, final byte n) {
      return $this.fromByte$mcZ$sp(n);
   }

   default boolean fromByte$mcZ$sp(final byte n) {
      return BoxesRunTime.unboxToBoolean(this.fromByte(n));
   }

   // $FF: synthetic method
   static byte fromByte$mcB$sp$(final ConvertableTo $this, final byte n) {
      return $this.fromByte$mcB$sp(n);
   }

   default byte fromByte$mcB$sp(final byte n) {
      return BoxesRunTime.unboxToByte(this.fromByte(n));
   }

   // $FF: synthetic method
   static char fromByte$mcC$sp$(final ConvertableTo $this, final byte n) {
      return $this.fromByte$mcC$sp(n);
   }

   default char fromByte$mcC$sp(final byte n) {
      return BoxesRunTime.unboxToChar(this.fromByte(n));
   }

   // $FF: synthetic method
   static double fromByte$mcD$sp$(final ConvertableTo $this, final byte n) {
      return $this.fromByte$mcD$sp(n);
   }

   default double fromByte$mcD$sp(final byte n) {
      return BoxesRunTime.unboxToDouble(this.fromByte(n));
   }

   // $FF: synthetic method
   static float fromByte$mcF$sp$(final ConvertableTo $this, final byte n) {
      return $this.fromByte$mcF$sp(n);
   }

   default float fromByte$mcF$sp(final byte n) {
      return BoxesRunTime.unboxToFloat(this.fromByte(n));
   }

   // $FF: synthetic method
   static int fromByte$mcI$sp$(final ConvertableTo $this, final byte n) {
      return $this.fromByte$mcI$sp(n);
   }

   default int fromByte$mcI$sp(final byte n) {
      return BoxesRunTime.unboxToInt(this.fromByte(n));
   }

   // $FF: synthetic method
   static long fromByte$mcJ$sp$(final ConvertableTo $this, final byte n) {
      return $this.fromByte$mcJ$sp(n);
   }

   default long fromByte$mcJ$sp(final byte n) {
      return BoxesRunTime.unboxToLong(this.fromByte(n));
   }

   // $FF: synthetic method
   static short fromByte$mcS$sp$(final ConvertableTo $this, final byte n) {
      return $this.fromByte$mcS$sp(n);
   }

   default short fromByte$mcS$sp(final byte n) {
      return BoxesRunTime.unboxToShort(this.fromByte(n));
   }

   // $FF: synthetic method
   static void fromByte$mcV$sp$(final ConvertableTo $this, final byte n) {
      $this.fromByte$mcV$sp(n);
   }

   default void fromByte$mcV$sp(final byte n) {
      this.fromByte(n);
   }

   // $FF: synthetic method
   static boolean fromShort$mcZ$sp$(final ConvertableTo $this, final short n) {
      return $this.fromShort$mcZ$sp(n);
   }

   default boolean fromShort$mcZ$sp(final short n) {
      return BoxesRunTime.unboxToBoolean(this.fromShort(n));
   }

   // $FF: synthetic method
   static byte fromShort$mcB$sp$(final ConvertableTo $this, final short n) {
      return $this.fromShort$mcB$sp(n);
   }

   default byte fromShort$mcB$sp(final short n) {
      return BoxesRunTime.unboxToByte(this.fromShort(n));
   }

   // $FF: synthetic method
   static char fromShort$mcC$sp$(final ConvertableTo $this, final short n) {
      return $this.fromShort$mcC$sp(n);
   }

   default char fromShort$mcC$sp(final short n) {
      return BoxesRunTime.unboxToChar(this.fromShort(n));
   }

   // $FF: synthetic method
   static double fromShort$mcD$sp$(final ConvertableTo $this, final short n) {
      return $this.fromShort$mcD$sp(n);
   }

   default double fromShort$mcD$sp(final short n) {
      return BoxesRunTime.unboxToDouble(this.fromShort(n));
   }

   // $FF: synthetic method
   static float fromShort$mcF$sp$(final ConvertableTo $this, final short n) {
      return $this.fromShort$mcF$sp(n);
   }

   default float fromShort$mcF$sp(final short n) {
      return BoxesRunTime.unboxToFloat(this.fromShort(n));
   }

   // $FF: synthetic method
   static int fromShort$mcI$sp$(final ConvertableTo $this, final short n) {
      return $this.fromShort$mcI$sp(n);
   }

   default int fromShort$mcI$sp(final short n) {
      return BoxesRunTime.unboxToInt(this.fromShort(n));
   }

   // $FF: synthetic method
   static long fromShort$mcJ$sp$(final ConvertableTo $this, final short n) {
      return $this.fromShort$mcJ$sp(n);
   }

   default long fromShort$mcJ$sp(final short n) {
      return BoxesRunTime.unboxToLong(this.fromShort(n));
   }

   // $FF: synthetic method
   static short fromShort$mcS$sp$(final ConvertableTo $this, final short n) {
      return $this.fromShort$mcS$sp(n);
   }

   default short fromShort$mcS$sp(final short n) {
      return BoxesRunTime.unboxToShort(this.fromShort(n));
   }

   // $FF: synthetic method
   static void fromShort$mcV$sp$(final ConvertableTo $this, final short n) {
      $this.fromShort$mcV$sp(n);
   }

   default void fromShort$mcV$sp(final short n) {
      this.fromShort(n);
   }

   // $FF: synthetic method
   static boolean fromInt$mcZ$sp$(final ConvertableTo $this, final int n) {
      return $this.fromInt$mcZ$sp(n);
   }

   default boolean fromInt$mcZ$sp(final int n) {
      return BoxesRunTime.unboxToBoolean(this.fromInt(n));
   }

   // $FF: synthetic method
   static byte fromInt$mcB$sp$(final ConvertableTo $this, final int n) {
      return $this.fromInt$mcB$sp(n);
   }

   default byte fromInt$mcB$sp(final int n) {
      return BoxesRunTime.unboxToByte(this.fromInt(n));
   }

   // $FF: synthetic method
   static char fromInt$mcC$sp$(final ConvertableTo $this, final int n) {
      return $this.fromInt$mcC$sp(n);
   }

   default char fromInt$mcC$sp(final int n) {
      return BoxesRunTime.unboxToChar(this.fromInt(n));
   }

   // $FF: synthetic method
   static double fromInt$mcD$sp$(final ConvertableTo $this, final int n) {
      return $this.fromInt$mcD$sp(n);
   }

   default double fromInt$mcD$sp(final int n) {
      return BoxesRunTime.unboxToDouble(this.fromInt(n));
   }

   // $FF: synthetic method
   static float fromInt$mcF$sp$(final ConvertableTo $this, final int n) {
      return $this.fromInt$mcF$sp(n);
   }

   default float fromInt$mcF$sp(final int n) {
      return BoxesRunTime.unboxToFloat(this.fromInt(n));
   }

   // $FF: synthetic method
   static int fromInt$mcI$sp$(final ConvertableTo $this, final int n) {
      return $this.fromInt$mcI$sp(n);
   }

   default int fromInt$mcI$sp(final int n) {
      return BoxesRunTime.unboxToInt(this.fromInt(n));
   }

   // $FF: synthetic method
   static long fromInt$mcJ$sp$(final ConvertableTo $this, final int n) {
      return $this.fromInt$mcJ$sp(n);
   }

   default long fromInt$mcJ$sp(final int n) {
      return BoxesRunTime.unboxToLong(this.fromInt(n));
   }

   // $FF: synthetic method
   static short fromInt$mcS$sp$(final ConvertableTo $this, final int n) {
      return $this.fromInt$mcS$sp(n);
   }

   default short fromInt$mcS$sp(final int n) {
      return BoxesRunTime.unboxToShort(this.fromInt(n));
   }

   // $FF: synthetic method
   static void fromInt$mcV$sp$(final ConvertableTo $this, final int n) {
      $this.fromInt$mcV$sp(n);
   }

   default void fromInt$mcV$sp(final int n) {
      this.fromInt(n);
   }

   // $FF: synthetic method
   static boolean fromLong$mcZ$sp$(final ConvertableTo $this, final long n) {
      return $this.fromLong$mcZ$sp(n);
   }

   default boolean fromLong$mcZ$sp(final long n) {
      return BoxesRunTime.unboxToBoolean(this.fromLong(n));
   }

   // $FF: synthetic method
   static byte fromLong$mcB$sp$(final ConvertableTo $this, final long n) {
      return $this.fromLong$mcB$sp(n);
   }

   default byte fromLong$mcB$sp(final long n) {
      return BoxesRunTime.unboxToByte(this.fromLong(n));
   }

   // $FF: synthetic method
   static char fromLong$mcC$sp$(final ConvertableTo $this, final long n) {
      return $this.fromLong$mcC$sp(n);
   }

   default char fromLong$mcC$sp(final long n) {
      return BoxesRunTime.unboxToChar(this.fromLong(n));
   }

   // $FF: synthetic method
   static double fromLong$mcD$sp$(final ConvertableTo $this, final long n) {
      return $this.fromLong$mcD$sp(n);
   }

   default double fromLong$mcD$sp(final long n) {
      return BoxesRunTime.unboxToDouble(this.fromLong(n));
   }

   // $FF: synthetic method
   static float fromLong$mcF$sp$(final ConvertableTo $this, final long n) {
      return $this.fromLong$mcF$sp(n);
   }

   default float fromLong$mcF$sp(final long n) {
      return BoxesRunTime.unboxToFloat(this.fromLong(n));
   }

   // $FF: synthetic method
   static int fromLong$mcI$sp$(final ConvertableTo $this, final long n) {
      return $this.fromLong$mcI$sp(n);
   }

   default int fromLong$mcI$sp(final long n) {
      return BoxesRunTime.unboxToInt(this.fromLong(n));
   }

   // $FF: synthetic method
   static long fromLong$mcJ$sp$(final ConvertableTo $this, final long n) {
      return $this.fromLong$mcJ$sp(n);
   }

   default long fromLong$mcJ$sp(final long n) {
      return BoxesRunTime.unboxToLong(this.fromLong(n));
   }

   // $FF: synthetic method
   static short fromLong$mcS$sp$(final ConvertableTo $this, final long n) {
      return $this.fromLong$mcS$sp(n);
   }

   default short fromLong$mcS$sp(final long n) {
      return BoxesRunTime.unboxToShort(this.fromLong(n));
   }

   // $FF: synthetic method
   static void fromLong$mcV$sp$(final ConvertableTo $this, final long n) {
      $this.fromLong$mcV$sp(n);
   }

   default void fromLong$mcV$sp(final long n) {
      this.fromLong(n);
   }

   // $FF: synthetic method
   static boolean fromFloat$mcZ$sp$(final ConvertableTo $this, final float n) {
      return $this.fromFloat$mcZ$sp(n);
   }

   default boolean fromFloat$mcZ$sp(final float n) {
      return BoxesRunTime.unboxToBoolean(this.fromFloat(n));
   }

   // $FF: synthetic method
   static byte fromFloat$mcB$sp$(final ConvertableTo $this, final float n) {
      return $this.fromFloat$mcB$sp(n);
   }

   default byte fromFloat$mcB$sp(final float n) {
      return BoxesRunTime.unboxToByte(this.fromFloat(n));
   }

   // $FF: synthetic method
   static char fromFloat$mcC$sp$(final ConvertableTo $this, final float n) {
      return $this.fromFloat$mcC$sp(n);
   }

   default char fromFloat$mcC$sp(final float n) {
      return BoxesRunTime.unboxToChar(this.fromFloat(n));
   }

   // $FF: synthetic method
   static double fromFloat$mcD$sp$(final ConvertableTo $this, final float n) {
      return $this.fromFloat$mcD$sp(n);
   }

   default double fromFloat$mcD$sp(final float n) {
      return BoxesRunTime.unboxToDouble(this.fromFloat(n));
   }

   // $FF: synthetic method
   static float fromFloat$mcF$sp$(final ConvertableTo $this, final float n) {
      return $this.fromFloat$mcF$sp(n);
   }

   default float fromFloat$mcF$sp(final float n) {
      return BoxesRunTime.unboxToFloat(this.fromFloat(n));
   }

   // $FF: synthetic method
   static int fromFloat$mcI$sp$(final ConvertableTo $this, final float n) {
      return $this.fromFloat$mcI$sp(n);
   }

   default int fromFloat$mcI$sp(final float n) {
      return BoxesRunTime.unboxToInt(this.fromFloat(n));
   }

   // $FF: synthetic method
   static long fromFloat$mcJ$sp$(final ConvertableTo $this, final float n) {
      return $this.fromFloat$mcJ$sp(n);
   }

   default long fromFloat$mcJ$sp(final float n) {
      return BoxesRunTime.unboxToLong(this.fromFloat(n));
   }

   // $FF: synthetic method
   static short fromFloat$mcS$sp$(final ConvertableTo $this, final float n) {
      return $this.fromFloat$mcS$sp(n);
   }

   default short fromFloat$mcS$sp(final float n) {
      return BoxesRunTime.unboxToShort(this.fromFloat(n));
   }

   // $FF: synthetic method
   static void fromFloat$mcV$sp$(final ConvertableTo $this, final float n) {
      $this.fromFloat$mcV$sp(n);
   }

   default void fromFloat$mcV$sp(final float n) {
      this.fromFloat(n);
   }

   // $FF: synthetic method
   static boolean fromDouble$mcZ$sp$(final ConvertableTo $this, final double n) {
      return $this.fromDouble$mcZ$sp(n);
   }

   default boolean fromDouble$mcZ$sp(final double n) {
      return BoxesRunTime.unboxToBoolean(this.fromDouble(n));
   }

   // $FF: synthetic method
   static byte fromDouble$mcB$sp$(final ConvertableTo $this, final double n) {
      return $this.fromDouble$mcB$sp(n);
   }

   default byte fromDouble$mcB$sp(final double n) {
      return BoxesRunTime.unboxToByte(this.fromDouble(n));
   }

   // $FF: synthetic method
   static char fromDouble$mcC$sp$(final ConvertableTo $this, final double n) {
      return $this.fromDouble$mcC$sp(n);
   }

   default char fromDouble$mcC$sp(final double n) {
      return BoxesRunTime.unboxToChar(this.fromDouble(n));
   }

   // $FF: synthetic method
   static double fromDouble$mcD$sp$(final ConvertableTo $this, final double n) {
      return $this.fromDouble$mcD$sp(n);
   }

   default double fromDouble$mcD$sp(final double n) {
      return BoxesRunTime.unboxToDouble(this.fromDouble(n));
   }

   // $FF: synthetic method
   static float fromDouble$mcF$sp$(final ConvertableTo $this, final double n) {
      return $this.fromDouble$mcF$sp(n);
   }

   default float fromDouble$mcF$sp(final double n) {
      return BoxesRunTime.unboxToFloat(this.fromDouble(n));
   }

   // $FF: synthetic method
   static int fromDouble$mcI$sp$(final ConvertableTo $this, final double n) {
      return $this.fromDouble$mcI$sp(n);
   }

   default int fromDouble$mcI$sp(final double n) {
      return BoxesRunTime.unboxToInt(this.fromDouble(n));
   }

   // $FF: synthetic method
   static long fromDouble$mcJ$sp$(final ConvertableTo $this, final double n) {
      return $this.fromDouble$mcJ$sp(n);
   }

   default long fromDouble$mcJ$sp(final double n) {
      return BoxesRunTime.unboxToLong(this.fromDouble(n));
   }

   // $FF: synthetic method
   static short fromDouble$mcS$sp$(final ConvertableTo $this, final double n) {
      return $this.fromDouble$mcS$sp(n);
   }

   default short fromDouble$mcS$sp(final double n) {
      return BoxesRunTime.unboxToShort(this.fromDouble(n));
   }

   // $FF: synthetic method
   static void fromDouble$mcV$sp$(final ConvertableTo $this, final double n) {
      $this.fromDouble$mcV$sp(n);
   }

   default void fromDouble$mcV$sp(final double n) {
      this.fromDouble(n);
   }

   // $FF: synthetic method
   static boolean fromBigInt$mcZ$sp$(final ConvertableTo $this, final BigInt n) {
      return $this.fromBigInt$mcZ$sp(n);
   }

   default boolean fromBigInt$mcZ$sp(final BigInt n) {
      return BoxesRunTime.unboxToBoolean(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static byte fromBigInt$mcB$sp$(final ConvertableTo $this, final BigInt n) {
      return $this.fromBigInt$mcB$sp(n);
   }

   default byte fromBigInt$mcB$sp(final BigInt n) {
      return BoxesRunTime.unboxToByte(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static char fromBigInt$mcC$sp$(final ConvertableTo $this, final BigInt n) {
      return $this.fromBigInt$mcC$sp(n);
   }

   default char fromBigInt$mcC$sp(final BigInt n) {
      return BoxesRunTime.unboxToChar(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static double fromBigInt$mcD$sp$(final ConvertableTo $this, final BigInt n) {
      return $this.fromBigInt$mcD$sp(n);
   }

   default double fromBigInt$mcD$sp(final BigInt n) {
      return BoxesRunTime.unboxToDouble(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static float fromBigInt$mcF$sp$(final ConvertableTo $this, final BigInt n) {
      return $this.fromBigInt$mcF$sp(n);
   }

   default float fromBigInt$mcF$sp(final BigInt n) {
      return BoxesRunTime.unboxToFloat(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static int fromBigInt$mcI$sp$(final ConvertableTo $this, final BigInt n) {
      return $this.fromBigInt$mcI$sp(n);
   }

   default int fromBigInt$mcI$sp(final BigInt n) {
      return BoxesRunTime.unboxToInt(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static long fromBigInt$mcJ$sp$(final ConvertableTo $this, final BigInt n) {
      return $this.fromBigInt$mcJ$sp(n);
   }

   default long fromBigInt$mcJ$sp(final BigInt n) {
      return BoxesRunTime.unboxToLong(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static short fromBigInt$mcS$sp$(final ConvertableTo $this, final BigInt n) {
      return $this.fromBigInt$mcS$sp(n);
   }

   default short fromBigInt$mcS$sp(final BigInt n) {
      return BoxesRunTime.unboxToShort(this.fromBigInt(n));
   }

   // $FF: synthetic method
   static void fromBigInt$mcV$sp$(final ConvertableTo $this, final BigInt n) {
      $this.fromBigInt$mcV$sp(n);
   }

   default void fromBigInt$mcV$sp(final BigInt n) {
      this.fromBigInt(n);
   }

   // $FF: synthetic method
   static boolean fromBigDecimal$mcZ$sp$(final ConvertableTo $this, final BigDecimal n) {
      return $this.fromBigDecimal$mcZ$sp(n);
   }

   default boolean fromBigDecimal$mcZ$sp(final BigDecimal n) {
      return BoxesRunTime.unboxToBoolean(this.fromBigDecimal(n));
   }

   // $FF: synthetic method
   static byte fromBigDecimal$mcB$sp$(final ConvertableTo $this, final BigDecimal n) {
      return $this.fromBigDecimal$mcB$sp(n);
   }

   default byte fromBigDecimal$mcB$sp(final BigDecimal n) {
      return BoxesRunTime.unboxToByte(this.fromBigDecimal(n));
   }

   // $FF: synthetic method
   static char fromBigDecimal$mcC$sp$(final ConvertableTo $this, final BigDecimal n) {
      return $this.fromBigDecimal$mcC$sp(n);
   }

   default char fromBigDecimal$mcC$sp(final BigDecimal n) {
      return BoxesRunTime.unboxToChar(this.fromBigDecimal(n));
   }

   // $FF: synthetic method
   static double fromBigDecimal$mcD$sp$(final ConvertableTo $this, final BigDecimal n) {
      return $this.fromBigDecimal$mcD$sp(n);
   }

   default double fromBigDecimal$mcD$sp(final BigDecimal n) {
      return BoxesRunTime.unboxToDouble(this.fromBigDecimal(n));
   }

   // $FF: synthetic method
   static float fromBigDecimal$mcF$sp$(final ConvertableTo $this, final BigDecimal n) {
      return $this.fromBigDecimal$mcF$sp(n);
   }

   default float fromBigDecimal$mcF$sp(final BigDecimal n) {
      return BoxesRunTime.unboxToFloat(this.fromBigDecimal(n));
   }

   // $FF: synthetic method
   static int fromBigDecimal$mcI$sp$(final ConvertableTo $this, final BigDecimal n) {
      return $this.fromBigDecimal$mcI$sp(n);
   }

   default int fromBigDecimal$mcI$sp(final BigDecimal n) {
      return BoxesRunTime.unboxToInt(this.fromBigDecimal(n));
   }

   // $FF: synthetic method
   static long fromBigDecimal$mcJ$sp$(final ConvertableTo $this, final BigDecimal n) {
      return $this.fromBigDecimal$mcJ$sp(n);
   }

   default long fromBigDecimal$mcJ$sp(final BigDecimal n) {
      return BoxesRunTime.unboxToLong(this.fromBigDecimal(n));
   }

   // $FF: synthetic method
   static short fromBigDecimal$mcS$sp$(final ConvertableTo $this, final BigDecimal n) {
      return $this.fromBigDecimal$mcS$sp(n);
   }

   default short fromBigDecimal$mcS$sp(final BigDecimal n) {
      return BoxesRunTime.unboxToShort(this.fromBigDecimal(n));
   }

   // $FF: synthetic method
   static void fromBigDecimal$mcV$sp$(final ConvertableTo $this, final BigDecimal n) {
      $this.fromBigDecimal$mcV$sp(n);
   }

   default void fromBigDecimal$mcV$sp(final BigDecimal n) {
      this.fromBigDecimal(n);
   }

   // $FF: synthetic method
   static boolean fromRational$mcZ$sp$(final ConvertableTo $this, final Rational n) {
      return $this.fromRational$mcZ$sp(n);
   }

   default boolean fromRational$mcZ$sp(final Rational n) {
      return BoxesRunTime.unboxToBoolean(this.fromRational(n));
   }

   // $FF: synthetic method
   static byte fromRational$mcB$sp$(final ConvertableTo $this, final Rational n) {
      return $this.fromRational$mcB$sp(n);
   }

   default byte fromRational$mcB$sp(final Rational n) {
      return BoxesRunTime.unboxToByte(this.fromRational(n));
   }

   // $FF: synthetic method
   static char fromRational$mcC$sp$(final ConvertableTo $this, final Rational n) {
      return $this.fromRational$mcC$sp(n);
   }

   default char fromRational$mcC$sp(final Rational n) {
      return BoxesRunTime.unboxToChar(this.fromRational(n));
   }

   // $FF: synthetic method
   static double fromRational$mcD$sp$(final ConvertableTo $this, final Rational n) {
      return $this.fromRational$mcD$sp(n);
   }

   default double fromRational$mcD$sp(final Rational n) {
      return BoxesRunTime.unboxToDouble(this.fromRational(n));
   }

   // $FF: synthetic method
   static float fromRational$mcF$sp$(final ConvertableTo $this, final Rational n) {
      return $this.fromRational$mcF$sp(n);
   }

   default float fromRational$mcF$sp(final Rational n) {
      return BoxesRunTime.unboxToFloat(this.fromRational(n));
   }

   // $FF: synthetic method
   static int fromRational$mcI$sp$(final ConvertableTo $this, final Rational n) {
      return $this.fromRational$mcI$sp(n);
   }

   default int fromRational$mcI$sp(final Rational n) {
      return BoxesRunTime.unboxToInt(this.fromRational(n));
   }

   // $FF: synthetic method
   static long fromRational$mcJ$sp$(final ConvertableTo $this, final Rational n) {
      return $this.fromRational$mcJ$sp(n);
   }

   default long fromRational$mcJ$sp(final Rational n) {
      return BoxesRunTime.unboxToLong(this.fromRational(n));
   }

   // $FF: synthetic method
   static short fromRational$mcS$sp$(final ConvertableTo $this, final Rational n) {
      return $this.fromRational$mcS$sp(n);
   }

   default short fromRational$mcS$sp(final Rational n) {
      return BoxesRunTime.unboxToShort(this.fromRational(n));
   }

   // $FF: synthetic method
   static void fromRational$mcV$sp$(final ConvertableTo $this, final Rational n) {
      $this.fromRational$mcV$sp(n);
   }

   default void fromRational$mcV$sp(final Rational n) {
      this.fromRational(n);
   }

   // $FF: synthetic method
   static boolean fromAlgebraic$mcZ$sp$(final ConvertableTo $this, final Algebraic n) {
      return $this.fromAlgebraic$mcZ$sp(n);
   }

   default boolean fromAlgebraic$mcZ$sp(final Algebraic n) {
      return BoxesRunTime.unboxToBoolean(this.fromAlgebraic(n));
   }

   // $FF: synthetic method
   static byte fromAlgebraic$mcB$sp$(final ConvertableTo $this, final Algebraic n) {
      return $this.fromAlgebraic$mcB$sp(n);
   }

   default byte fromAlgebraic$mcB$sp(final Algebraic n) {
      return BoxesRunTime.unboxToByte(this.fromAlgebraic(n));
   }

   // $FF: synthetic method
   static char fromAlgebraic$mcC$sp$(final ConvertableTo $this, final Algebraic n) {
      return $this.fromAlgebraic$mcC$sp(n);
   }

   default char fromAlgebraic$mcC$sp(final Algebraic n) {
      return BoxesRunTime.unboxToChar(this.fromAlgebraic(n));
   }

   // $FF: synthetic method
   static double fromAlgebraic$mcD$sp$(final ConvertableTo $this, final Algebraic n) {
      return $this.fromAlgebraic$mcD$sp(n);
   }

   default double fromAlgebraic$mcD$sp(final Algebraic n) {
      return BoxesRunTime.unboxToDouble(this.fromAlgebraic(n));
   }

   // $FF: synthetic method
   static float fromAlgebraic$mcF$sp$(final ConvertableTo $this, final Algebraic n) {
      return $this.fromAlgebraic$mcF$sp(n);
   }

   default float fromAlgebraic$mcF$sp(final Algebraic n) {
      return BoxesRunTime.unboxToFloat(this.fromAlgebraic(n));
   }

   // $FF: synthetic method
   static int fromAlgebraic$mcI$sp$(final ConvertableTo $this, final Algebraic n) {
      return $this.fromAlgebraic$mcI$sp(n);
   }

   default int fromAlgebraic$mcI$sp(final Algebraic n) {
      return BoxesRunTime.unboxToInt(this.fromAlgebraic(n));
   }

   // $FF: synthetic method
   static long fromAlgebraic$mcJ$sp$(final ConvertableTo $this, final Algebraic n) {
      return $this.fromAlgebraic$mcJ$sp(n);
   }

   default long fromAlgebraic$mcJ$sp(final Algebraic n) {
      return BoxesRunTime.unboxToLong(this.fromAlgebraic(n));
   }

   // $FF: synthetic method
   static short fromAlgebraic$mcS$sp$(final ConvertableTo $this, final Algebraic n) {
      return $this.fromAlgebraic$mcS$sp(n);
   }

   default short fromAlgebraic$mcS$sp(final Algebraic n) {
      return BoxesRunTime.unboxToShort(this.fromAlgebraic(n));
   }

   // $FF: synthetic method
   static void fromAlgebraic$mcV$sp$(final ConvertableTo $this, final Algebraic n) {
      $this.fromAlgebraic$mcV$sp(n);
   }

   default void fromAlgebraic$mcV$sp(final Algebraic n) {
      this.fromAlgebraic(n);
   }

   // $FF: synthetic method
   static boolean fromReal$mcZ$sp$(final ConvertableTo $this, final Real n) {
      return $this.fromReal$mcZ$sp(n);
   }

   default boolean fromReal$mcZ$sp(final Real n) {
      return BoxesRunTime.unboxToBoolean(this.fromReal(n));
   }

   // $FF: synthetic method
   static byte fromReal$mcB$sp$(final ConvertableTo $this, final Real n) {
      return $this.fromReal$mcB$sp(n);
   }

   default byte fromReal$mcB$sp(final Real n) {
      return BoxesRunTime.unboxToByte(this.fromReal(n));
   }

   // $FF: synthetic method
   static char fromReal$mcC$sp$(final ConvertableTo $this, final Real n) {
      return $this.fromReal$mcC$sp(n);
   }

   default char fromReal$mcC$sp(final Real n) {
      return BoxesRunTime.unboxToChar(this.fromReal(n));
   }

   // $FF: synthetic method
   static double fromReal$mcD$sp$(final ConvertableTo $this, final Real n) {
      return $this.fromReal$mcD$sp(n);
   }

   default double fromReal$mcD$sp(final Real n) {
      return BoxesRunTime.unboxToDouble(this.fromReal(n));
   }

   // $FF: synthetic method
   static float fromReal$mcF$sp$(final ConvertableTo $this, final Real n) {
      return $this.fromReal$mcF$sp(n);
   }

   default float fromReal$mcF$sp(final Real n) {
      return BoxesRunTime.unboxToFloat(this.fromReal(n));
   }

   // $FF: synthetic method
   static int fromReal$mcI$sp$(final ConvertableTo $this, final Real n) {
      return $this.fromReal$mcI$sp(n);
   }

   default int fromReal$mcI$sp(final Real n) {
      return BoxesRunTime.unboxToInt(this.fromReal(n));
   }

   // $FF: synthetic method
   static long fromReal$mcJ$sp$(final ConvertableTo $this, final Real n) {
      return $this.fromReal$mcJ$sp(n);
   }

   default long fromReal$mcJ$sp(final Real n) {
      return BoxesRunTime.unboxToLong(this.fromReal(n));
   }

   // $FF: synthetic method
   static short fromReal$mcS$sp$(final ConvertableTo $this, final Real n) {
      return $this.fromReal$mcS$sp(n);
   }

   default short fromReal$mcS$sp(final Real n) {
      return BoxesRunTime.unboxToShort(this.fromReal(n));
   }

   // $FF: synthetic method
   static void fromReal$mcV$sp$(final ConvertableTo $this, final Real n) {
      $this.fromReal$mcV$sp(n);
   }

   default void fromReal$mcV$sp(final Real n) {
      this.fromReal(n);
   }

   // $FF: synthetic method
   static boolean fromType$mcZ$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      return $this.fromType$mcZ$sp(b, evidence$1);
   }

   default boolean fromType$mcZ$sp(final Object b, final ConvertableFrom evidence$1) {
      return BoxesRunTime.unboxToBoolean(this.fromType(b, evidence$1));
   }

   // $FF: synthetic method
   static byte fromType$mcB$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      return $this.fromType$mcB$sp(b, evidence$1);
   }

   default byte fromType$mcB$sp(final Object b, final ConvertableFrom evidence$1) {
      return BoxesRunTime.unboxToByte(this.fromType(b, evidence$1));
   }

   // $FF: synthetic method
   static char fromType$mcC$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      return $this.fromType$mcC$sp(b, evidence$1);
   }

   default char fromType$mcC$sp(final Object b, final ConvertableFrom evidence$1) {
      return BoxesRunTime.unboxToChar(this.fromType(b, evidence$1));
   }

   // $FF: synthetic method
   static double fromType$mcD$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      return $this.fromType$mcD$sp(b, evidence$1);
   }

   default double fromType$mcD$sp(final Object b, final ConvertableFrom evidence$1) {
      return BoxesRunTime.unboxToDouble(this.fromType(b, evidence$1));
   }

   // $FF: synthetic method
   static float fromType$mcF$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      return $this.fromType$mcF$sp(b, evidence$1);
   }

   default float fromType$mcF$sp(final Object b, final ConvertableFrom evidence$1) {
      return BoxesRunTime.unboxToFloat(this.fromType(b, evidence$1));
   }

   // $FF: synthetic method
   static int fromType$mcI$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      return $this.fromType$mcI$sp(b, evidence$1);
   }

   default int fromType$mcI$sp(final Object b, final ConvertableFrom evidence$1) {
      return BoxesRunTime.unboxToInt(this.fromType(b, evidence$1));
   }

   // $FF: synthetic method
   static long fromType$mcJ$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      return $this.fromType$mcJ$sp(b, evidence$1);
   }

   default long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$1) {
      return BoxesRunTime.unboxToLong(this.fromType(b, evidence$1));
   }

   // $FF: synthetic method
   static short fromType$mcS$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      return $this.fromType$mcS$sp(b, evidence$1);
   }

   default short fromType$mcS$sp(final Object b, final ConvertableFrom evidence$1) {
      return BoxesRunTime.unboxToShort(this.fromType(b, evidence$1));
   }

   // $FF: synthetic method
   static void fromType$mcV$sp$(final ConvertableTo $this, final Object b, final ConvertableFrom evidence$1) {
      $this.fromType$mcV$sp(b, evidence$1);
   }

   default void fromType$mcV$sp(final Object b, final ConvertableFrom evidence$1) {
      this.fromType(b, evidence$1);
   }
}
