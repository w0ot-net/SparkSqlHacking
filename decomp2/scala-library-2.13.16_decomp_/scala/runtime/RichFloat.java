package scala.runtime;

import scala.Proxy;
import scala.math.Fractional;
import scala.math.Numeric;
import scala.math.Ordered;
import scala.math.Ordering;
import scala.math.Ordering$Float$TotalOrdering$;
import scala.math.ScalaNumericAnyConversions;
import scala.math.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t=b\u0001B!C\u0005\u001dC\u0001b\u0015\u0001\u0003\u0006\u0004%\t\u0001\u0016\u0005\t+\u0002\u0011\t\u0011)A\u0005!\")a\u000b\u0001C\u0001/\")!\f\u0001C\t7\")1\r\u0001C\tI\")\u0001\u000e\u0001C!S\")Q\u000e\u0001C!)\")a\u000e\u0001C!_\")1\u000f\u0001C!i\")\u0001\u0010\u0001C!s\")Q\u0010\u0001C!}\"9\u0011Q\u0001\u0001\u0005B\u0005\u001d\u0001bBA\b\u0001\u0011\u0005\u0013q\u0001\u0005\b\u0003#\u0001A\u0011IA\u0004\u0011\u001d\t\u0019\u0002\u0001C!\u0003\u000fAq!!\u0006\u0001\t\u0003\n9\u0001C\u0004\u0002\u0018\u0001!\t!a\u0002\t\u000f\u0005e\u0001\u0001\"\u0001\u0002\b!9\u00111\u0004\u0001\u0005\u0002\u0005\u001d\u0001bBA\u000f\u0001\u0011\u0005\u0011q\u0001\u0005\b\u0003?\u0001A\u0011AA\u0004\u0011\u0019\t\t\u0003\u0001C!)\"9\u00111\u0005\u0001\u0005B\u0005\u0015\u0002bBA\u0016\u0001\u0011\u0005\u0013Q\u0006\u0005\u0007\u0003c\u0001A\u0011\t;\t\r\u0005\u001d\u0003\u0001\"\u0001u\u0011\u0019\tI\u0005\u0001C\u0001)\"1\u00111\n\u0001\u0005\u0002QCa!!\u0014\u0001\t\u0003!\u0006BBA(\u0001\u0011\u0005A\u000bC\u0005\u0002R\u0001\t\t\u0011\"\u0011\u0002T!I\u0011Q\u000b\u0001\u0002\u0002\u0013\u0005\u0013qK\u0004\n\u0003G\u0012\u0015\u0011!E\u0001\u0003K2\u0001\"\u0011\"\u0002\u0002#\u0005\u0011q\r\u0005\u0007-\n\"\t!a\u001c\t\u000f\u0005E$\u0005\"\u0002\u0002t!9\u0011\u0011\u0010\u0012\u0005\u0006\u0005m\u0004bBA@E\u0011\u0015\u0011\u0011\u0011\u0005\b\u0003\u000b\u0013CQAAD\u0011\u001d\tYI\tC\u0003\u0003\u001bCq!!%#\t\u000b\t\u0019\nC\u0004\u0002\u0018\n\")!!'\t\u000f\u0005u%\u0005\"\u0002\u0002 \"9\u00111\u0015\u0012\u0005\u0006\u0005\u0015\u0006bBAUE\u0011\u0015\u00111\u0016\u0005\b\u0003_\u0013CQAAY\u0011\u001d\t)L\tC\u0003\u0003oCq!a/#\t\u000b\ti\fC\u0004\u0002B\n\")!a1\t\u000f\u0005\u001d'\u0005\"\u0002\u0002J\"9\u0011Q\u001a\u0012\u0005\u0006\u0005=\u0007bBAjE\u0011\u0015\u0011Q\u001b\u0005\b\u00033\u0014CQAAn\u0011\u001d\tyN\tC\u0003\u0003CDq!!:#\t\u000b\t9\u000fC\u0004\u0002p\n\")!!=\t\u000f\u0005e(\u0005\"\u0002\u0002|\"9!\u0011\u0001\u0012\u0005\u0006\t\r\u0001b\u0002B\u0004E\u0011\u0015!\u0011\u0002\u0005\b\u0005\u001b\u0011CQ\u0001B\b\u0011\u001d\u0011\u0019B\tC\u0003\u0005+AqA!\u0007#\t\u000b\u0011Y\u0002C\u0005\u0003 \t\n\t\u0011\"\u0002\u0003\"!I!Q\u0005\u0012\u0002\u0002\u0013\u0015!q\u0005\u0002\n%&\u001c\u0007N\u00127pCRT!a\u0011#\u0002\u000fI,h\u000e^5nK*\tQ)A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001AE\n\u0005\u0002J\u00156\tA)\u0003\u0002L\t\n1\u0011I\\=WC2\u00042!\u0014(Q\u001b\u0005\u0011\u0015BA(C\u0005=1%/Y2uS>t\u0017\r\u001c)s_bL\bCA%R\u0013\t\u0011FIA\u0003GY>\fG/\u0001\u0003tK24W#\u0001)\u0002\u000bM,GN\u001a\u0011\u0002\rqJg.\u001b;?)\tA\u0016\f\u0005\u0002N\u0001!)1k\u0001a\u0001!\u0006\u0019a.^7\u0016\u0003q\u00032!\u00181Q\u001d\tIe,\u0003\u0002`\t\u00069\u0001/Y2lC\u001e,\u0017BA1c\u0005)1%/Y2uS>t\u0017\r\u001c\u0006\u0003?\u0012\u000b1a\u001c:e+\u0005)\u0007cA/g!&\u0011qM\u0019\u0002\t\u001fJ$WM]5oO\u0006YAm\\;cY\u00164\u0016\r\\;f+\u0005Q\u0007CA%l\u0013\taGI\u0001\u0004E_V\u0014G.Z\u0001\u000bM2|\u0017\r\u001e,bYV,\u0017!\u00037p]\u001e4\u0016\r\\;f+\u0005\u0001\bCA%r\u0013\t\u0011HI\u0001\u0003M_:<\u0017\u0001C5oiZ\u000bG.^3\u0016\u0003U\u0004\"!\u0013<\n\u0005]$%aA%oi\u0006I!-\u001f;f-\u0006dW/Z\u000b\u0002uB\u0011\u0011j_\u0005\u0003y\u0012\u0013AAQ=uK\u0006Q1\u000f[8siZ\u000bG.^3\u0016\u0003}\u00042!SA\u0001\u0013\r\t\u0019\u0001\u0012\u0002\u0006'\"|'\u000f^\u0001\bSN<\u0006n\u001c7f+\t\tI\u0001E\u0002J\u0003\u0017I1!!\u0004E\u0005\u001d\u0011un\u001c7fC:\f1\"[:WC2LGMQ=uK\u0006a\u0011n\u001d,bY&$7\u000b[8si\u0006Y\u0011n\u001d,bY&$7\t[1s\u0003)I7OV1mS\u0012Le\u000e^\u0001\u0006SNt\u0015MT\u0001\u000bSNLeNZ5oSRL\u0018\u0001C5t\r&t\u0017\u000e^3\u0002\u001b%\u001c\bk\\:J]\u001aLg.\u001b;z\u00035I7OT3h\u0013:4\u0017N\\5us\u0006\u0019\u0011MY:\u0002\u00075\f\u0007\u0010F\u0002Q\u0003OAa!!\u000b\u0018\u0001\u0004\u0001\u0016\u0001\u0002;iCR\f1!\\5o)\r\u0001\u0016q\u0006\u0005\u0007\u0003SA\u0002\u0019\u0001)\u0002\rMLwM\\;nQ-I\u0012QGA\u001e\u0003{\t\t%a\u0011\u0011\u0007%\u000b9$C\u0002\u0002:\u0011\u0013!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#!a\u0010\u0002\tNLwM\\;nA\u0011|Wm\u001d\u0011o_R\u0004\u0003.\u00198eY\u0016\u0004S\u0006\r\u00181M\u0002z'\u000f\t$m_\u0006$hFT1Ow\u0001*8/\u001a\u0011ag&<g\u000e\u0019\u0011nKRDw\u000e\u001a\u0011j]N$X-\u00193\u0002\u000bMLgnY3\"\u0005\u0005\u0015\u0013A\u0002\u001a/cMr\u0003'A\u0003s_VtG-\u0001\u0003dK&d\u0017!\u00024m_>\u0014\u0018!\u0003;p%\u0006$\u0017.\u00198t\u0003%!x\u000eR3he\u0016,7/\u0001\u0005iCND7i\u001c3f)\u0005)\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\n\u0005e\u0003\"CA.A\u0005\u0005\t\u0019AA/\u0003\rAH%\r\t\u0004\u0013\u0006}\u0013bAA1\t\n\u0019\u0011I\\=\u0002\u0013IK7\r\u001b$m_\u0006$\bCA'#'\r\u0011\u0013\u0011\u000e\t\u0004\u0013\u0006-\u0014bAA7\t\n1\u0011I\\=SK\u001a$\"!!\u001a\u0002\u001b9,X\u000eJ3yi\u0016t7/[8o)\ra\u0016Q\u000f\u0005\u0007\u0003o\"\u0003\u0019\u0001-\u0002\u000b\u0011\"\b.[:\u0002\u001b=\u0014H\rJ3yi\u0016t7/[8o)\r)\u0017Q\u0010\u0005\u0007\u0003o*\u0003\u0019\u0001-\u0002+\u0011|WO\u00197f-\u0006dW/\u001a\u0013fqR,gn]5p]R\u0019!.a!\t\r\u0005]d\u00051\u0001Y\u0003Q1Gn\\1u-\u0006dW/\u001a\u0013fqR,gn]5p]R\u0019\u0001+!#\t\r\u0005]t\u00051\u0001Y\u0003MawN\\4WC2,X\rJ3yi\u0016t7/[8o)\r\u0001\u0018q\u0012\u0005\u0007\u0003oB\u0003\u0019\u0001-\u0002%%tGOV1mk\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0004k\u0006U\u0005BBA<S\u0001\u0007\u0001,A\ncsR,g+\u00197vK\u0012*\u0007\u0010^3og&|g\u000eF\u0002{\u00037Ca!a\u001e+\u0001\u0004A\u0016\u0001F:i_J$h+\u00197vK\u0012*\u0007\u0010^3og&|g\u000eF\u0002\u0000\u0003CCa!a\u001e,\u0001\u0004A\u0016!E5t/\"|G.\u001a\u0013fqR,gn]5p]R!\u0011\u0011BAT\u0011\u0019\t9\b\fa\u00011\u0006)\u0012n\u001d,bY&$')\u001f;fI\u0015DH/\u001a8tS>tG\u0003BA\u0005\u0003[Ca!a\u001e.\u0001\u0004A\u0016AF5t-\u0006d\u0017\u000eZ*i_J$H%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005%\u00111\u0017\u0005\u0007\u0003or\u0003\u0019\u0001-\u0002+%\u001ch+\u00197jI\u000eC\u0017M\u001d\u0013fqR,gn]5p]R!\u0011\u0011BA]\u0011\u0019\t9h\fa\u00011\u0006!\u0012n\u001d,bY&$\u0017J\u001c;%Kb$XM\\:j_:$B!!\u0003\u0002@\"1\u0011q\u000f\u0019A\u0002a\u000bq\"[:OC:#S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003\u0013\t)\r\u0003\u0004\u0002xE\u0002\r\u0001W\u0001\u0015SNLeNZ5oSRLH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005%\u00111\u001a\u0005\u0007\u0003o\u0012\u0004\u0019\u0001-\u0002%%\u001ch)\u001b8ji\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003\u0013\t\t\u000e\u0003\u0004\u0002xM\u0002\r\u0001W\u0001\u0018SN\u0004vn]%oM&t\u0017\u000e^=%Kb$XM\\:j_:$B!!\u0003\u0002X\"1\u0011q\u000f\u001bA\u0002a\u000bq#[:OK\u001eLeNZ5oSRLH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\u0005%\u0011Q\u001c\u0005\u0007\u0003o*\u0004\u0019\u0001-\u0002\u001b\u0005\u00147\u000fJ3yi\u0016t7/[8o)\r\u0001\u00161\u001d\u0005\u0007\u0003o2\u0004\u0019\u0001-\u0002\u001b5\f\u0007\u0010J3yi\u0016t7/[8o)\u0011\tI/!<\u0015\u0007A\u000bY\u000f\u0003\u0004\u0002*]\u0002\r\u0001\u0015\u0005\u0007\u0003o:\u0004\u0019\u0001-\u0002\u001b5Lg\u000eJ3yi\u0016t7/[8o)\u0011\t\u00190a>\u0015\u0007A\u000b)\u0010\u0003\u0004\u0002*a\u0002\r\u0001\u0015\u0005\u0007\u0003oB\u0004\u0019\u0001-\u0002!MLwM\\;nI\u0015DH/\u001a8tS>tGcA;\u0002~\"1\u0011qO\u001dA\u0002aC3\"OA\u001b\u0003w\ti$!\u0011\u0002D\u0005y!o\\;oI\u0012*\u0007\u0010^3og&|g\u000eF\u0002v\u0005\u000bAa!a\u001e;\u0001\u0004A\u0016AD2fS2$S\r\u001f;f]NLwN\u001c\u000b\u0004!\n-\u0001BBA<w\u0001\u0007\u0001,A\bgY>|'\u000fJ3yi\u0016t7/[8o)\r\u0001&\u0011\u0003\u0005\u0007\u0003ob\u0004\u0019\u0001-\u0002'Q|'+\u00193jC:\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007A\u00139\u0002\u0003\u0004\u0002xu\u0002\r\u0001W\u0001\u0014i>$Um\u001a:fKN$S\r\u001f;f]NLwN\u001c\u000b\u0004!\nu\u0001BBA<}\u0001\u0007\u0001,\u0001\niCND7i\u001c3fI\u0015DH/\u001a8tS>tG\u0003BA*\u0005GAa!a\u001e@\u0001\u0004A\u0016\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\u0011\u0011IC!\f\u0015\t\u0005%!1\u0006\u0005\n\u00037\u0002\u0015\u0011!a\u0001\u0003;Ba!a\u001eA\u0001\u0004A\u0006"
)
public final class RichFloat implements FractionalProxy {
   private final float self;

   public static boolean equals$extension(final float $this, final Object x$1) {
      return RichFloat$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Float.hashCode($this);
   }

   public static float toDegrees$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var1 = package$.MODULE$;
      return (float)Math.toDegrees((double)$this);
   }

   public static float toRadians$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var1 = package$.MODULE$;
      return (float)Math.toRadians((double)$this);
   }

   public static float floor$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var1 = package$.MODULE$;
      return (float)Math.floor((double)$this);
   }

   public static float ceil$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var1 = package$.MODULE$;
      return (float)Math.ceil((double)$this);
   }

   public static int round$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var1 = package$.MODULE$;
      return Math.round($this);
   }

   /** @deprecated */
   public static int signum$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var1 = package$.MODULE$;
      return (int)Math.signum($this);
   }

   public static float min$extension(final float $this, final float that) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var2 = package$.MODULE$;
      return Math.min($this, that);
   }

   public static float max$extension(final float $this, final float that) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var2 = package$.MODULE$;
      return Math.max($this, that);
   }

   public static float abs$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      package$ var1 = package$.MODULE$;
      return Math.abs($this);
   }

   public static boolean isNegInfinity$extension(final float $this) {
      return RichFloat$.MODULE$.isNegInfinity$extension($this);
   }

   public static boolean isPosInfinity$extension(final float $this) {
      return RichFloat$.MODULE$.isPosInfinity$extension($this);
   }

   public static boolean isFinite$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Float.isFinite($this);
   }

   public static boolean isInfinity$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Float.isInfinite($this);
   }

   public static boolean isNaN$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Float.isNaN($this);
   }

   public static boolean isValidInt$extension(final float $this) {
      return RichFloat$.MODULE$.isValidInt$extension($this);
   }

   public static boolean isValidChar$extension(final float $this) {
      return RichFloat$.MODULE$.isValidChar$extension($this);
   }

   public static boolean isValidShort$extension(final float $this) {
      return RichFloat$.MODULE$.isValidShort$extension($this);
   }

   public static boolean isValidByte$extension(final float $this) {
      return RichFloat$.MODULE$.isValidByte$extension($this);
   }

   public static boolean isWhole$extension(final float $this) {
      return RichFloat$.MODULE$.isWhole$extension($this);
   }

   public static short shortValue$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (short)((int)$this);
   }

   public static byte byteValue$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (byte)((int)$this);
   }

   public static int intValue$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (int)$this;
   }

   public static long longValue$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (long)$this;
   }

   public static float floatValue$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return $this;
   }

   public static double doubleValue$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (double)$this;
   }

   public static Ordering ord$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Ordering$Float$TotalOrdering$.MODULE$;
   }

   public static Fractional num$extension(final float $this) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Numeric.FloatIsFractional$.MODULE$;
   }

   public Object sign() {
      return ScalaNumberProxy.sign$(this);
   }

   public int compare(final Object y) {
      return OrderedProxy.compare$(this, y);
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   public String toString() {
      return Proxy.toString$(this);
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public byte toByte() {
      return ScalaNumericAnyConversions.toByte$(this);
   }

   public short toShort() {
      return ScalaNumericAnyConversions.toShort$(this);
   }

   public int toInt() {
      return ScalaNumericAnyConversions.toInt$(this);
   }

   public long toLong() {
      return ScalaNumericAnyConversions.toLong$(this);
   }

   public float toFloat() {
      return ScalaNumericAnyConversions.toFloat$(this);
   }

   public double toDouble() {
      return ScalaNumericAnyConversions.toDouble$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public float self() {
      return this.self;
   }

   public Fractional num() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      this.self();
      return Numeric.FloatIsFractional$.MODULE$;
   }

   public Ordering ord() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      this.self();
      return Ordering$Float$TotalOrdering$.MODULE$;
   }

   public double doubleValue() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (double)this.self();
   }

   public float floatValue() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return this.self();
   }

   public long longValue() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (long)this.self();
   }

   public int intValue() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (int)this.self();
   }

   public byte byteValue() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (byte)((int)this.self());
   }

   public short shortValue() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return (short)((int)this.self());
   }

   public boolean isWhole() {
      return RichFloat$.MODULE$.isWhole$extension(this.self());
   }

   public boolean isValidByte() {
      return RichFloat$.MODULE$.isValidByte$extension(this.self());
   }

   public boolean isValidShort() {
      return RichFloat$.MODULE$.isValidShort$extension(this.self());
   }

   public boolean isValidChar() {
      return RichFloat$.MODULE$.isValidChar$extension(this.self());
   }

   public boolean isValidInt() {
      return RichFloat$.MODULE$.isValidInt$extension(this.self());
   }

   public boolean isNaN() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Float.isNaN(this.self());
   }

   public boolean isInfinity() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Float.isInfinite(this.self());
   }

   public boolean isFinite() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Float.isFinite(this.self());
   }

   public boolean isPosInfinity() {
      return RichFloat$.MODULE$.isPosInfinity$extension(this.self());
   }

   public boolean isNegInfinity() {
      return RichFloat$.MODULE$.isNegInfinity$extension(this.self());
   }

   public float abs() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float abs$extension_$this = this.self();
      package$ var2 = package$.MODULE$;
      return Math.abs(abs$extension_$this);
   }

   public float max(final float that) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float max$extension_$this = this.self();
      package$ var3 = package$.MODULE$;
      return Math.max(max$extension_$this, that);
   }

   public float min(final float that) {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float min$extension_$this = this.self();
      package$ var3 = package$.MODULE$;
      return Math.min(min$extension_$this, that);
   }

   /** @deprecated */
   public int signum() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float signum$extension_$this = this.self();
      package$ var2 = package$.MODULE$;
      return (int)Math.signum(signum$extension_$this);
   }

   public int round() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float round$extension_$this = this.self();
      package$ var2 = package$.MODULE$;
      return Math.round(round$extension_$this);
   }

   public float ceil() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float ceil$extension_$this = this.self();
      package$ var2 = package$.MODULE$;
      return (float)Math.ceil((double)ceil$extension_$this);
   }

   public float floor() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float floor$extension_$this = this.self();
      package$ var2 = package$.MODULE$;
      return (float)Math.floor((double)floor$extension_$this);
   }

   public float toRadians() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float toRadians$extension_$this = this.self();
      package$ var2 = package$.MODULE$;
      return (float)Math.toRadians((double)toRadians$extension_$this);
   }

   public float toDegrees() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      float toDegrees$extension_$this = this.self();
      package$ var2 = package$.MODULE$;
      return (float)Math.toDegrees((double)toDegrees$extension_$this);
   }

   public int hashCode() {
      RichFloat$ var10000 = RichFloat$.MODULE$;
      return Float.hashCode(this.self());
   }

   public boolean equals(final Object x$1) {
      return RichFloat$.MODULE$.equals$extension(this.self(), x$1);
   }

   public RichFloat(final float self) {
      this.self = self;
   }
}
