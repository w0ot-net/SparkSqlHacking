package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\tuaa\u0002\u00180!\u0003\r\n\u0001\u000e\u0005\u0006y\u00011\t!\u0010\u0005\u0006\u001f\u00021\t\u0001\u0015\u0005\u0006+\u00021\tA\u0016\u0005\u00067\u00021\t\u0001\u0018\u0005\u0006C\u00021\tA\u0019\u0005\u0006O\u00021\t\u0001\u001b\u0005\u0006[\u00021\tA\u001c\u0005\u0006y\u00021\t! \u0005\b\u0003\u000b\u0001a\u0011AA\u0004\u0011\u001d\t\u0019\u0002\u0001D\u0001\u0003+Aq!a\b\u0001\r\u0003\t\t\u0003C\u0004\u0002,\u00011\t!!\f\t\u000f\u0005]\u0002A\"\u0001\u0002:!9\u0011\u0011\u000b\u0001\u0007\u0002\u0005MsaBA4_!\u0005\u0011\u0011\u000e\u0004\u0007]=B\t!a\u001b\t\u000f\u0005M\u0004\u0003\"\u0001\u0002v!9\u0011q\u000f\t\u0005\u0006\u0005e\u0004\"CAH!\t\u0007IqAAI\u0011!\t)\n\u0005Q\u0001\u000e\u0005M\u0005\"CAL!\t\u0007IqAAM\u0011!\ti\n\u0005Q\u0001\u000e\u0005m\u0005\"CAP!\t\u0007IqAAQ\u0011!\t)\u000b\u0005Q\u0001\u000e\u0005\r\u0006\"CAT!\t\u0007IqAAU\u0011!\ti\u000b\u0005Q\u0001\u000e\u0005-\u0006\"CAX!\t\u0007IqAAY\u0011!\t)\f\u0005Q\u0001\u000e\u0005M\u0006\"CA\\!\t\u0007IqAA]\u0011!\ti\f\u0005Q\u0001\u000e\u0005m\u0006\"CA`!\t\u0007IqAAa\u0011!\t)\r\u0005Q\u0001\u000e\u0005\r\u0007\"CAd!\t\u0007IqAAe\u0011!\ti\r\u0005Q\u0001\u000e\u0005-\u0007\"CAh!\t\u0007IqAAi\u0011!\t)\u000e\u0005Q\u0001\u000e\u0005M\u0007\"CAl!\t\u0007IqAAm\u0011!\ti\u000e\u0005Q\u0001\u000e\u0005m\u0007\"CAp!\t\u0007IqAAq\u0011!\tY\u000f\u0005Q\u0001\u000e\u0005\r\b\"CAw!\t\u0007IqAAx\u0011!\t\u0019\u0010\u0005Q\u0001\u000e\u0005E\b\"CA{!\t\u0007IqAA|\u0011!\u0011\t\u0001\u0005Q\u0001\u000e\u0005e\bb\u0002B\u0002!\u0011\r!Q\u0001\u0002\u0010\u0007>tg/\u001a:uC\ndWM\u0012:p[*\u0011\u0001'M\u0001\u0005[\u0006$\bNC\u00013\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"!N#\u0014\u0005\u00011\u0004CA\u001c;\u001b\u0005A$\"A\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005mB$aA!os\u00061Ao\u001c\"zi\u0016$\"AP!\u0011\u0005]z\u0014B\u0001!9\u0005\u0011\u0011\u0015\u0010^3\t\u000b\t\u000b\u0001\u0019A\"\u0002\u0003\u0005\u0004\"\u0001R#\r\u0001\u0011Ia\t\u0001Q\u0001\u0002\u0003\u0015\ra\u0012\u0002\u0002\u0003F\u0011\u0001J\u000e\t\u0003o%K!A\u0013\u001d\u0003\u000f9{G\u000f[5oO\"\u0012Q\t\u0014\t\u0003o5K!A\u0014\u001d\u0003\u0017M\u0004XmY5bY&TX\rZ\u0001\bi>\u001c\u0006n\u001c:u)\t\tF\u000b\u0005\u00028%&\u00111\u000b\u000f\u0002\u0006'\"|'\u000f\u001e\u0005\u0006\u0005\n\u0001\raQ\u0001\u0006i>Le\u000e\u001e\u000b\u0003/j\u0003\"a\u000e-\n\u0005eC$aA%oi\")!i\u0001a\u0001\u0007\u00061Ao\u001c'p]\u001e$\"!\u00181\u0011\u0005]r\u0016BA09\u0005\u0011auN\\4\t\u000b\t#\u0001\u0019A\"\u0002\u000fQ|g\t\\8biR\u00111M\u001a\t\u0003o\u0011L!!\u001a\u001d\u0003\u000b\u0019cw.\u0019;\t\u000b\t+\u0001\u0019A\"\u0002\u0011Q|Gi\\;cY\u0016$\"!\u001b7\u0011\u0005]R\u0017BA69\u0005\u0019!u.\u001e2mK\")!I\u0002a\u0001\u0007\u0006AAo\u001c\"jO&sG\u000f\u0006\u0002pwB\u0011\u0001\u000f\u001f\b\u0003cZt!A];\u000e\u0003MT!\u0001^\u001a\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0014BA<9\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u001f>\u0003\r\tKw-\u00138u\u0015\t9\b\bC\u0003C\u000f\u0001\u00071)\u0001\u0007u_\nKw\rR3dS6\fG\u000eF\u0002\u007f\u0003\u0007\u0001\"\u0001]@\n\u0007\u0005\u0005!P\u0001\u0006CS\u001e$UmY5nC2DQA\u0011\u0005A\u0002\r\u000b!\u0002^8SCRLwN\\1m)\u0011\tI!!\u0005\u0011\t\u0005-\u0011QB\u0007\u0002_%\u0019\u0011qB\u0018\u0003\u0011I\u000bG/[8oC2DQAQ\u0005A\u0002\r\u000b1\u0002^8BY\u001e,'M]1jGR!\u0011qCA\u000f!\u0011\tY!!\u0007\n\u0007\u0005mqFA\u0005BY\u001e,'M]1jG\")!I\u0003a\u0001\u0007\u00061Ao\u001c*fC2$B!a\t\u0002*A!\u00111BA\u0013\u0013\r\t9c\f\u0002\u0005%\u0016\fG\u000eC\u0003C\u0017\u0001\u00071)\u0001\u0005u_:+XNY3s)\u0011\ty#!\u000e\u0011\t\u0005-\u0011\u0011G\u0005\u0004\u0003gy#A\u0002(v[\n,'\u000fC\u0003C\u0019\u0001\u00071)\u0001\u0004u_RK\b/Z\u000b\u0005\u0003w\t\t\u0005\u0006\u0003\u0002>\u0005=C\u0003BA \u0003\u000b\u00022\u0001RA!\t\u0019\t\u0019%\u0004b\u0001\u000f\n\t!\tC\u0005\u0002H5\t\t\u0011q\u0001\u0002J\u0005YQM^5eK:\u001cW\rJ\u00198!\u0019\tY!a\u0013\u0002@%\u0019\u0011QJ\u0018\u0003\u001b\r{gN^3si\u0006\u0014G.\u001a+p\u0011\u0015\u0011U\u00021\u0001D\u0003!!xn\u0015;sS:<G\u0003BA+\u0003K\u0002B!a\u0016\u0002`9!\u0011\u0011LA.!\t\u0011\b(C\u0002\u0002^a\na\u0001\u0015:fI\u00164\u0017\u0002BA1\u0003G\u0012aa\u0015;sS:<'bAA/q!)!I\u0004a\u0001\u0007\u0006y1i\u001c8wKJ$\u0018M\u00197f\rJ|W\u000eE\u0002\u0002\fA\u00192\u0001EA7!\r9\u0014qN\u0005\u0004\u0003cB$AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003S\nQ!\u00199qYf,B!a\u001f\u0002\u0002R!\u0011QPAB!\u0015\tY\u0001AA@!\r!\u0015\u0011\u0011\u0003\u0006\rJ\u0011\ra\u0012\u0005\b\u0003\u000b\u0013\u00029AA?\u0003\t)g\u000fK\u0002\u0013\u0003\u0013\u00032aNAF\u0013\r\ti\t\u000f\u0002\u0007S:d\u0017N\\3\u0002'\r{gN^3si\u0006\u0014G.\u001a$s_6\u0014\u0015\u0010^3\u0016\u0005\u0005M\u0005\u0003BA\u0006\u0001y\nAcQ8om\u0016\u0014H/\u00192mK\u001a\u0013x.\u001c\"zi\u0016\u0004\u0013\u0001F\"p]Z,'\u000f^1cY\u00164%o\\7TQ>\u0014H/\u0006\u0002\u0002\u001cB!\u00111\u0002\u0001R\u0003U\u0019uN\u001c<feR\f'\r\\3Ge>l7\u000b[8si\u0002\n!cQ8om\u0016\u0014H/\u00192mK\u001a\u0013x.\\%oiV\u0011\u00111\u0015\t\u0005\u0003\u0017\u0001q+A\nD_:4XM\u001d;bE2,gI]8n\u0013:$\b%A\nD_:4XM\u001d;bE2,gI]8n\u0019>tw-\u0006\u0002\u0002,B!\u00111\u0002\u0001^\u0003Q\u0019uN\u001c<feR\f'\r\\3Ge>lGj\u001c8hA\u0005!2i\u001c8wKJ$\u0018M\u00197f\rJ|WN\u00127pCR,\"!a-\u0011\t\u0005-\u0001aY\u0001\u0016\u0007>tg/\u001a:uC\ndWM\u0012:p[\u001acw.\u0019;!\u0003U\u0019uN\u001c<feR\f'\r\\3Ge>lGi\\;cY\u0016,\"!a/\u0011\t\u0005-\u0001![\u0001\u0017\u0007>tg/\u001a:uC\ndWM\u0012:p[\u0012{WO\u00197fA\u0005)2i\u001c8wKJ$\u0018M\u00197f\rJ|WNQ5h\u0013:$XCAAb!\u0011\tY\u0001A8\u0002-\r{gN^3si\u0006\u0014G.\u001a$s_6\u0014\u0015nZ%oi\u0002\n\u0011dQ8om\u0016\u0014H/\u00192mK\u001a\u0013x.\u001c\"jO\u0012+7-[7bYV\u0011\u00111\u001a\t\u0005\u0003\u0017\u0001a0\u0001\u000eD_:4XM\u001d;bE2,gI]8n\u0005&<G)Z2j[\u0006d\u0007%A\fD_:4XM\u001d;bE2,gI]8n%\u0006$\u0018n\u001c8bYV\u0011\u00111\u001b\t\u0006\u0003\u0017\u0001\u0011\u0011B\u0001\u0019\u0007>tg/\u001a:uC\ndWM\u0012:p[J\u000bG/[8oC2\u0004\u0013\u0001G\"p]Z,'\u000f^1cY\u00164%o\\7BY\u001e,'M]1jGV\u0011\u00111\u001c\t\u0006\u0003\u0017\u0001\u0011qC\u0001\u001a\u0007>tg/\u001a:uC\ndWM\u0012:p[\u0006cw-\u001a2sC&\u001c\u0007%A\fD_:4XM\u001d;bE2,gI]8n'\u00064W\rT8oOV\u0011\u00111\u001d\t\u0006\u0003\u0017\u0001\u0011Q\u001d\t\u0005\u0003\u0017\t9/C\u0002\u0002j>\u0012\u0001bU1gK2{gnZ\u0001\u0019\u0007>tg/\u001a:uC\ndWM\u0012:p[N\u000bg-\u001a'p]\u001e\u0004\u0013!F\"p]Z,'\u000f^1cY\u00164%o\\7Ok6\u0014WM]\u000b\u0003\u0003c\u0004R!a\u0003\u0001\u0003_\tacQ8om\u0016\u0014H/\u00192mK\u001a\u0013x.\u001c(v[\n,'\u000fI\u0001\u0017\u0007>tg/\u001a:uC\ndWM\u0012:p[:\u000bG/\u001e:bYV\u0011\u0011\u0011 \t\u0006\u0003\u0017\u0001\u00111 \t\u0005\u0003\u0017\ti0C\u0002\u0002\u0000>\u0012qAT1ukJ\fG.A\fD_:4XM\u001d;bE2,gI]8n\u001d\u0006$XO]1mA\u000512m\u001c8wKJ$\u0018M\u00197f\rJ|WnQ8na2,\u00070\u0006\u0003\u0003\b\tEA\u0003\u0002B\u0005\u0005'\u0001b!a\u0003\u0003\f\t=\u0011b\u0001B\u0007_\t12i\u001c8wKJ$\u0018M\u00197f\rJ|WnQ8na2,\u0007\u0010E\u0002E\u0005#!QAR\u0017C\u0002\u001dC\u0011B!\u0006.\u0003\u0003\u0005\u001dAa\u0006\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$3G\r\t\u0007\u0003\u0017\u0011IBa\u0004\n\u0007\tmqF\u0001\u0005J]R,wM]1m\u0001"
)
public interface ConvertableFrom {
   static ConvertableFromComplex convertableFromComplex(final Integral evidence$32) {
      return ConvertableFrom$.MODULE$.convertableFromComplex(evidence$32);
   }

   static ConvertableFrom ConvertableFromNatural() {
      return ConvertableFrom$.MODULE$.ConvertableFromNatural();
   }

   static ConvertableFrom ConvertableFromNumber() {
      return ConvertableFrom$.MODULE$.ConvertableFromNumber();
   }

   static ConvertableFrom ConvertableFromSafeLong() {
      return ConvertableFrom$.MODULE$.ConvertableFromSafeLong();
   }

   static ConvertableFrom ConvertableFromAlgebraic() {
      return ConvertableFrom$.MODULE$.ConvertableFromAlgebraic();
   }

   static ConvertableFrom ConvertableFromRational() {
      return ConvertableFrom$.MODULE$.ConvertableFromRational();
   }

   static ConvertableFrom ConvertableFromBigDecimal() {
      return ConvertableFrom$.MODULE$.ConvertableFromBigDecimal();
   }

   static ConvertableFrom ConvertableFromBigInt() {
      return ConvertableFrom$.MODULE$.ConvertableFromBigInt();
   }

   static ConvertableFrom ConvertableFromDouble() {
      return ConvertableFrom$.MODULE$.ConvertableFromDouble();
   }

   static ConvertableFrom ConvertableFromFloat() {
      return ConvertableFrom$.MODULE$.ConvertableFromFloat();
   }

   static ConvertableFrom ConvertableFromLong() {
      return ConvertableFrom$.MODULE$.ConvertableFromLong();
   }

   static ConvertableFrom ConvertableFromInt() {
      return ConvertableFrom$.MODULE$.ConvertableFromInt();
   }

   static ConvertableFrom ConvertableFromShort() {
      return ConvertableFrom$.MODULE$.ConvertableFromShort();
   }

   static ConvertableFrom ConvertableFromByte() {
      return ConvertableFrom$.MODULE$.ConvertableFromByte();
   }

   static ConvertableFrom apply(final ConvertableFrom ev) {
      return ConvertableFrom$.MODULE$.apply(ev);
   }

   byte toByte(final Object a);

   short toShort(final Object a);

   int toInt(final Object a);

   long toLong(final Object a);

   float toFloat(final Object a);

   double toDouble(final Object a);

   BigInt toBigInt(final Object a);

   BigDecimal toBigDecimal(final Object a);

   Rational toRational(final Object a);

   Algebraic toAlgebraic(final Object a);

   Real toReal(final Object a);

   Number toNumber(final Object a);

   Object toType(final Object a, final ConvertableTo evidence$17);

   String toString(final Object a);

   // $FF: synthetic method
   static byte toByte$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toByte$mcZ$sp(a);
   }

   default byte toByte$mcZ$sp(final boolean a) {
      return this.toByte(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static byte toByte$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toByte$mcB$sp(a);
   }

   default byte toByte$mcB$sp(final byte a) {
      return this.toByte(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static byte toByte$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toByte$mcC$sp(a);
   }

   default byte toByte$mcC$sp(final char a) {
      return this.toByte(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static byte toByte$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toByte$mcD$sp(a);
   }

   default byte toByte$mcD$sp(final double a) {
      return this.toByte(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static byte toByte$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toByte$mcF$sp(a);
   }

   default byte toByte$mcF$sp(final float a) {
      return this.toByte(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static byte toByte$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toByte$mcI$sp(a);
   }

   default byte toByte$mcI$sp(final int a) {
      return this.toByte(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static byte toByte$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toByte$mcJ$sp(a);
   }

   default byte toByte$mcJ$sp(final long a) {
      return this.toByte(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static byte toByte$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toByte$mcS$sp(a);
   }

   default byte toByte$mcS$sp(final short a) {
      return this.toByte(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static byte toByte$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toByte$mcV$sp(a);
   }

   default byte toByte$mcV$sp(final BoxedUnit a) {
      return this.toByte(a);
   }

   // $FF: synthetic method
   static short toShort$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toShort$mcZ$sp(a);
   }

   default short toShort$mcZ$sp(final boolean a) {
      return this.toShort(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static short toShort$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toShort$mcB$sp(a);
   }

   default short toShort$mcB$sp(final byte a) {
      return this.toShort(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static short toShort$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toShort$mcC$sp(a);
   }

   default short toShort$mcC$sp(final char a) {
      return this.toShort(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static short toShort$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toShort$mcD$sp(a);
   }

   default short toShort$mcD$sp(final double a) {
      return this.toShort(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static short toShort$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toShort$mcF$sp(a);
   }

   default short toShort$mcF$sp(final float a) {
      return this.toShort(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static short toShort$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toShort$mcI$sp(a);
   }

   default short toShort$mcI$sp(final int a) {
      return this.toShort(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static short toShort$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toShort$mcJ$sp(a);
   }

   default short toShort$mcJ$sp(final long a) {
      return this.toShort(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static short toShort$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toShort$mcS$sp(a);
   }

   default short toShort$mcS$sp(final short a) {
      return this.toShort(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static short toShort$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toShort$mcV$sp(a);
   }

   default short toShort$mcV$sp(final BoxedUnit a) {
      return this.toShort(a);
   }

   // $FF: synthetic method
   static int toInt$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toInt$mcZ$sp(a);
   }

   default int toInt$mcZ$sp(final boolean a) {
      return this.toInt(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static int toInt$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toInt$mcB$sp(a);
   }

   default int toInt$mcB$sp(final byte a) {
      return this.toInt(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static int toInt$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toInt$mcC$sp(a);
   }

   default int toInt$mcC$sp(final char a) {
      return this.toInt(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static int toInt$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toInt$mcD$sp(a);
   }

   default int toInt$mcD$sp(final double a) {
      return this.toInt(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static int toInt$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toInt$mcF$sp(a);
   }

   default int toInt$mcF$sp(final float a) {
      return this.toInt(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static int toInt$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toInt$mcI$sp(a);
   }

   default int toInt$mcI$sp(final int a) {
      return this.toInt(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static int toInt$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toInt$mcJ$sp(a);
   }

   default int toInt$mcJ$sp(final long a) {
      return this.toInt(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static int toInt$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toInt$mcS$sp(a);
   }

   default int toInt$mcS$sp(final short a) {
      return this.toInt(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static int toInt$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toInt$mcV$sp(a);
   }

   default int toInt$mcV$sp(final BoxedUnit a) {
      return this.toInt(a);
   }

   // $FF: synthetic method
   static long toLong$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toLong$mcZ$sp(a);
   }

   default long toLong$mcZ$sp(final boolean a) {
      return this.toLong(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static long toLong$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toLong$mcB$sp(a);
   }

   default long toLong$mcB$sp(final byte a) {
      return this.toLong(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static long toLong$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toLong$mcC$sp(a);
   }

   default long toLong$mcC$sp(final char a) {
      return this.toLong(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static long toLong$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toLong$mcD$sp(a);
   }

   default long toLong$mcD$sp(final double a) {
      return this.toLong(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static long toLong$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toLong$mcF$sp(a);
   }

   default long toLong$mcF$sp(final float a) {
      return this.toLong(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static long toLong$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toLong$mcI$sp(a);
   }

   default long toLong$mcI$sp(final int a) {
      return this.toLong(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static long toLong$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toLong$mcJ$sp(a);
   }

   default long toLong$mcJ$sp(final long a) {
      return this.toLong(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static long toLong$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toLong$mcS$sp(a);
   }

   default long toLong$mcS$sp(final short a) {
      return this.toLong(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static long toLong$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toLong$mcV$sp(a);
   }

   default long toLong$mcV$sp(final BoxedUnit a) {
      return this.toLong(a);
   }

   // $FF: synthetic method
   static float toFloat$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toFloat$mcZ$sp(a);
   }

   default float toFloat$mcZ$sp(final boolean a) {
      return this.toFloat(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static float toFloat$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toFloat$mcB$sp(a);
   }

   default float toFloat$mcB$sp(final byte a) {
      return this.toFloat(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static float toFloat$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toFloat$mcC$sp(a);
   }

   default float toFloat$mcC$sp(final char a) {
      return this.toFloat(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static float toFloat$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toFloat$mcD$sp(a);
   }

   default float toFloat$mcD$sp(final double a) {
      return this.toFloat(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static float toFloat$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toFloat$mcF$sp(a);
   }

   default float toFloat$mcF$sp(final float a) {
      return this.toFloat(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static float toFloat$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toFloat$mcI$sp(a);
   }

   default float toFloat$mcI$sp(final int a) {
      return this.toFloat(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static float toFloat$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toFloat$mcJ$sp(a);
   }

   default float toFloat$mcJ$sp(final long a) {
      return this.toFloat(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static float toFloat$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toFloat$mcS$sp(a);
   }

   default float toFloat$mcS$sp(final short a) {
      return this.toFloat(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static float toFloat$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toFloat$mcV$sp(a);
   }

   default float toFloat$mcV$sp(final BoxedUnit a) {
      return this.toFloat(a);
   }

   // $FF: synthetic method
   static double toDouble$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toDouble$mcZ$sp(a);
   }

   default double toDouble$mcZ$sp(final boolean a) {
      return this.toDouble(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static double toDouble$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toDouble$mcB$sp(a);
   }

   default double toDouble$mcB$sp(final byte a) {
      return this.toDouble(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static double toDouble$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toDouble$mcC$sp(a);
   }

   default double toDouble$mcC$sp(final char a) {
      return this.toDouble(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static double toDouble$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toDouble$mcD$sp(a);
   }

   default double toDouble$mcD$sp(final double a) {
      return this.toDouble(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static double toDouble$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toDouble$mcF$sp(a);
   }

   default double toDouble$mcF$sp(final float a) {
      return this.toDouble(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static double toDouble$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toDouble$mcI$sp(a);
   }

   default double toDouble$mcI$sp(final int a) {
      return this.toDouble(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static double toDouble$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toDouble$mcJ$sp(a);
   }

   default double toDouble$mcJ$sp(final long a) {
      return this.toDouble(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static double toDouble$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toDouble$mcS$sp(a);
   }

   default double toDouble$mcS$sp(final short a) {
      return this.toDouble(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static double toDouble$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toDouble$mcV$sp(a);
   }

   default double toDouble$mcV$sp(final BoxedUnit a) {
      return this.toDouble(a);
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toBigInt$mcZ$sp(a);
   }

   default BigInt toBigInt$mcZ$sp(final boolean a) {
      return this.toBigInt(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toBigInt$mcB$sp(a);
   }

   default BigInt toBigInt$mcB$sp(final byte a) {
      return this.toBigInt(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toBigInt$mcC$sp(a);
   }

   default BigInt toBigInt$mcC$sp(final char a) {
      return this.toBigInt(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toBigInt$mcD$sp(a);
   }

   default BigInt toBigInt$mcD$sp(final double a) {
      return this.toBigInt(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toBigInt$mcF$sp(a);
   }

   default BigInt toBigInt$mcF$sp(final float a) {
      return this.toBigInt(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toBigInt$mcI$sp(a);
   }

   default BigInt toBigInt$mcI$sp(final int a) {
      return this.toBigInt(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toBigInt$mcJ$sp(a);
   }

   default BigInt toBigInt$mcJ$sp(final long a) {
      return this.toBigInt(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toBigInt$mcS$sp(a);
   }

   default BigInt toBigInt$mcS$sp(final short a) {
      return this.toBigInt(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toBigInt$mcV$sp(a);
   }

   default BigInt toBigInt$mcV$sp(final BoxedUnit a) {
      return this.toBigInt(a);
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toBigDecimal$mcZ$sp(a);
   }

   default BigDecimal toBigDecimal$mcZ$sp(final boolean a) {
      return this.toBigDecimal(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toBigDecimal$mcB$sp(a);
   }

   default BigDecimal toBigDecimal$mcB$sp(final byte a) {
      return this.toBigDecimal(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toBigDecimal$mcC$sp(a);
   }

   default BigDecimal toBigDecimal$mcC$sp(final char a) {
      return this.toBigDecimal(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toBigDecimal$mcD$sp(a);
   }

   default BigDecimal toBigDecimal$mcD$sp(final double a) {
      return this.toBigDecimal(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toBigDecimal$mcF$sp(a);
   }

   default BigDecimal toBigDecimal$mcF$sp(final float a) {
      return this.toBigDecimal(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toBigDecimal$mcI$sp(a);
   }

   default BigDecimal toBigDecimal$mcI$sp(final int a) {
      return this.toBigDecimal(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toBigDecimal$mcJ$sp(a);
   }

   default BigDecimal toBigDecimal$mcJ$sp(final long a) {
      return this.toBigDecimal(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toBigDecimal$mcS$sp(a);
   }

   default BigDecimal toBigDecimal$mcS$sp(final short a) {
      return this.toBigDecimal(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toBigDecimal$mcV$sp(a);
   }

   default BigDecimal toBigDecimal$mcV$sp(final BoxedUnit a) {
      return this.toBigDecimal(a);
   }

   // $FF: synthetic method
   static Rational toRational$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toRational$mcZ$sp(a);
   }

   default Rational toRational$mcZ$sp(final boolean a) {
      return this.toRational(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Rational toRational$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toRational$mcB$sp(a);
   }

   default Rational toRational$mcB$sp(final byte a) {
      return this.toRational(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Rational toRational$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toRational$mcC$sp(a);
   }

   default Rational toRational$mcC$sp(final char a) {
      return this.toRational(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Rational toRational$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toRational$mcD$sp(a);
   }

   default Rational toRational$mcD$sp(final double a) {
      return this.toRational(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Rational toRational$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toRational$mcF$sp(a);
   }

   default Rational toRational$mcF$sp(final float a) {
      return this.toRational(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Rational toRational$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toRational$mcI$sp(a);
   }

   default Rational toRational$mcI$sp(final int a) {
      return this.toRational(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Rational toRational$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toRational$mcJ$sp(a);
   }

   default Rational toRational$mcJ$sp(final long a) {
      return this.toRational(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Rational toRational$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toRational$mcS$sp(a);
   }

   default Rational toRational$mcS$sp(final short a) {
      return this.toRational(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Rational toRational$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toRational$mcV$sp(a);
   }

   default Rational toRational$mcV$sp(final BoxedUnit a) {
      return this.toRational(a);
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toAlgebraic$mcZ$sp(a);
   }

   default Algebraic toAlgebraic$mcZ$sp(final boolean a) {
      return this.toAlgebraic(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toAlgebraic$mcB$sp(a);
   }

   default Algebraic toAlgebraic$mcB$sp(final byte a) {
      return this.toAlgebraic(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toAlgebraic$mcC$sp(a);
   }

   default Algebraic toAlgebraic$mcC$sp(final char a) {
      return this.toAlgebraic(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toAlgebraic$mcD$sp(a);
   }

   default Algebraic toAlgebraic$mcD$sp(final double a) {
      return this.toAlgebraic(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toAlgebraic$mcF$sp(a);
   }

   default Algebraic toAlgebraic$mcF$sp(final float a) {
      return this.toAlgebraic(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toAlgebraic$mcI$sp(a);
   }

   default Algebraic toAlgebraic$mcI$sp(final int a) {
      return this.toAlgebraic(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toAlgebraic$mcJ$sp(a);
   }

   default Algebraic toAlgebraic$mcJ$sp(final long a) {
      return this.toAlgebraic(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toAlgebraic$mcS$sp(a);
   }

   default Algebraic toAlgebraic$mcS$sp(final short a) {
      return this.toAlgebraic(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toAlgebraic$mcV$sp(a);
   }

   default Algebraic toAlgebraic$mcV$sp(final BoxedUnit a) {
      return this.toAlgebraic(a);
   }

   // $FF: synthetic method
   static Real toReal$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toReal$mcZ$sp(a);
   }

   default Real toReal$mcZ$sp(final boolean a) {
      return this.toReal(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Real toReal$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toReal$mcB$sp(a);
   }

   default Real toReal$mcB$sp(final byte a) {
      return this.toReal(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Real toReal$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toReal$mcC$sp(a);
   }

   default Real toReal$mcC$sp(final char a) {
      return this.toReal(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Real toReal$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toReal$mcD$sp(a);
   }

   default Real toReal$mcD$sp(final double a) {
      return this.toReal(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Real toReal$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toReal$mcF$sp(a);
   }

   default Real toReal$mcF$sp(final float a) {
      return this.toReal(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Real toReal$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toReal$mcI$sp(a);
   }

   default Real toReal$mcI$sp(final int a) {
      return this.toReal(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Real toReal$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toReal$mcJ$sp(a);
   }

   default Real toReal$mcJ$sp(final long a) {
      return this.toReal(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Real toReal$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toReal$mcS$sp(a);
   }

   default Real toReal$mcS$sp(final short a) {
      return this.toReal(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Real toReal$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toReal$mcV$sp(a);
   }

   default Real toReal$mcV$sp(final BoxedUnit a) {
      return this.toReal(a);
   }

   // $FF: synthetic method
   static Number toNumber$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toNumber$mcZ$sp(a);
   }

   default Number toNumber$mcZ$sp(final boolean a) {
      return this.toNumber(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static Number toNumber$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toNumber$mcB$sp(a);
   }

   default Number toNumber$mcB$sp(final byte a) {
      return this.toNumber(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static Number toNumber$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toNumber$mcC$sp(a);
   }

   default Number toNumber$mcC$sp(final char a) {
      return this.toNumber(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static Number toNumber$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toNumber$mcD$sp(a);
   }

   default Number toNumber$mcD$sp(final double a) {
      return this.toNumber(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static Number toNumber$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toNumber$mcF$sp(a);
   }

   default Number toNumber$mcF$sp(final float a) {
      return this.toNumber(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static Number toNumber$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toNumber$mcI$sp(a);
   }

   default Number toNumber$mcI$sp(final int a) {
      return this.toNumber(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static Number toNumber$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toNumber$mcJ$sp(a);
   }

   default Number toNumber$mcJ$sp(final long a) {
      return this.toNumber(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static Number toNumber$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toNumber$mcS$sp(a);
   }

   default Number toNumber$mcS$sp(final short a) {
      return this.toNumber(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static Number toNumber$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toNumber$mcV$sp(a);
   }

   default Number toNumber$mcV$sp(final BoxedUnit a) {
      return this.toNumber(a);
   }

   // $FF: synthetic method
   static Object toType$mcZ$sp$(final ConvertableFrom $this, final boolean a, final ConvertableTo evidence$17) {
      return $this.toType$mcZ$sp(a, evidence$17);
   }

   default Object toType$mcZ$sp(final boolean a, final ConvertableTo evidence$17) {
      return this.toType(BoxesRunTime.boxToBoolean(a), evidence$17);
   }

   // $FF: synthetic method
   static Object toType$mcB$sp$(final ConvertableFrom $this, final byte a, final ConvertableTo evidence$17) {
      return $this.toType$mcB$sp(a, evidence$17);
   }

   default Object toType$mcB$sp(final byte a, final ConvertableTo evidence$17) {
      return this.toType(BoxesRunTime.boxToByte(a), evidence$17);
   }

   // $FF: synthetic method
   static Object toType$mcC$sp$(final ConvertableFrom $this, final char a, final ConvertableTo evidence$17) {
      return $this.toType$mcC$sp(a, evidence$17);
   }

   default Object toType$mcC$sp(final char a, final ConvertableTo evidence$17) {
      return this.toType(BoxesRunTime.boxToCharacter(a), evidence$17);
   }

   // $FF: synthetic method
   static Object toType$mcD$sp$(final ConvertableFrom $this, final double a, final ConvertableTo evidence$17) {
      return $this.toType$mcD$sp(a, evidence$17);
   }

   default Object toType$mcD$sp(final double a, final ConvertableTo evidence$17) {
      return this.toType(BoxesRunTime.boxToDouble(a), evidence$17);
   }

   // $FF: synthetic method
   static Object toType$mcF$sp$(final ConvertableFrom $this, final float a, final ConvertableTo evidence$17) {
      return $this.toType$mcF$sp(a, evidence$17);
   }

   default Object toType$mcF$sp(final float a, final ConvertableTo evidence$17) {
      return this.toType(BoxesRunTime.boxToFloat(a), evidence$17);
   }

   // $FF: synthetic method
   static Object toType$mcI$sp$(final ConvertableFrom $this, final int a, final ConvertableTo evidence$17) {
      return $this.toType$mcI$sp(a, evidence$17);
   }

   default Object toType$mcI$sp(final int a, final ConvertableTo evidence$17) {
      return this.toType(BoxesRunTime.boxToInteger(a), evidence$17);
   }

   // $FF: synthetic method
   static Object toType$mcJ$sp$(final ConvertableFrom $this, final long a, final ConvertableTo evidence$17) {
      return $this.toType$mcJ$sp(a, evidence$17);
   }

   default Object toType$mcJ$sp(final long a, final ConvertableTo evidence$17) {
      return this.toType(BoxesRunTime.boxToLong(a), evidence$17);
   }

   // $FF: synthetic method
   static Object toType$mcS$sp$(final ConvertableFrom $this, final short a, final ConvertableTo evidence$17) {
      return $this.toType$mcS$sp(a, evidence$17);
   }

   default Object toType$mcS$sp(final short a, final ConvertableTo evidence$17) {
      return this.toType(BoxesRunTime.boxToShort(a), evidence$17);
   }

   // $FF: synthetic method
   static Object toType$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a, final ConvertableTo evidence$17) {
      return $this.toType$mcV$sp(a, evidence$17);
   }

   default Object toType$mcV$sp(final BoxedUnit a, final ConvertableTo evidence$17) {
      return this.toType(a, evidence$17);
   }

   // $FF: synthetic method
   static String toString$mcZ$sp$(final ConvertableFrom $this, final boolean a) {
      return $this.toString$mcZ$sp(a);
   }

   default String toString$mcZ$sp(final boolean a) {
      return this.toString(BoxesRunTime.boxToBoolean(a));
   }

   // $FF: synthetic method
   static String toString$mcB$sp$(final ConvertableFrom $this, final byte a) {
      return $this.toString$mcB$sp(a);
   }

   default String toString$mcB$sp(final byte a) {
      return this.toString(BoxesRunTime.boxToByte(a));
   }

   // $FF: synthetic method
   static String toString$mcC$sp$(final ConvertableFrom $this, final char a) {
      return $this.toString$mcC$sp(a);
   }

   default String toString$mcC$sp(final char a) {
      return this.toString(BoxesRunTime.boxToCharacter(a));
   }

   // $FF: synthetic method
   static String toString$mcD$sp$(final ConvertableFrom $this, final double a) {
      return $this.toString$mcD$sp(a);
   }

   default String toString$mcD$sp(final double a) {
      return this.toString(BoxesRunTime.boxToDouble(a));
   }

   // $FF: synthetic method
   static String toString$mcF$sp$(final ConvertableFrom $this, final float a) {
      return $this.toString$mcF$sp(a);
   }

   default String toString$mcF$sp(final float a) {
      return this.toString(BoxesRunTime.boxToFloat(a));
   }

   // $FF: synthetic method
   static String toString$mcI$sp$(final ConvertableFrom $this, final int a) {
      return $this.toString$mcI$sp(a);
   }

   default String toString$mcI$sp(final int a) {
      return this.toString(BoxesRunTime.boxToInteger(a));
   }

   // $FF: synthetic method
   static String toString$mcJ$sp$(final ConvertableFrom $this, final long a) {
      return $this.toString$mcJ$sp(a);
   }

   default String toString$mcJ$sp(final long a) {
      return this.toString(BoxesRunTime.boxToLong(a));
   }

   // $FF: synthetic method
   static String toString$mcS$sp$(final ConvertableFrom $this, final short a) {
      return $this.toString$mcS$sp(a);
   }

   default String toString$mcS$sp(final short a) {
      return this.toString(BoxesRunTime.boxToShort(a));
   }

   // $FF: synthetic method
   static String toString$mcV$sp$(final ConvertableFrom $this, final BoxedUnit a) {
      return $this.toString$mcV$sp(a);
   }

   default String toString$mcV$sp(final BoxedUnit a) {
      return this.toString(a);
   }
}
