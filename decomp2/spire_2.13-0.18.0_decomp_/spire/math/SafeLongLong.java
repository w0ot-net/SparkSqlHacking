package spire.math;

import java.math.BigInteger;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichLong;
import scala.runtime.Statics;
import spire.macros.ArithmeticOverflowException;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001df!B$I\u0005\"c\u0005\u0002\u00033\u0001\u0005+\u0007I\u0011A3\t\u0011%\u0004!\u0011#Q\u0001\n\u0019DQA\u001b\u0001\u0005\u0002-DQA\u001c\u0001\u0005\u0002=DQa\u001d\u0001\u0005\u0002=DQ\u0001\u001e\u0001\u0005\u0002=DQ!\u001e\u0001\u0005\u0002=DQA\u001e\u0001\u0005\u0002]DQa\u001f\u0001\u0005\u0002qDaa \u0001\u0005\u0002\u0005\u0005\u0001bBA\u0003\u0001\u0011\u0005\u0011q\u0001\u0005\b\u0003\u0017\u0001A\u0011AA\u0007\u0011\u001d\t\t\u0002\u0001C\u0001\u0003'Aq!a\u0006\u0001\t\u0003\tI\u0002C\u0004\u0002$\u0001!\t!!\n\t\u000f\u0005%\u0002\u0001\"\u0001\u0002,!9\u0011q\u0006\u0001\u0005\u0002\u0005E\u0002bBA\u001b\u0001\u0011\u0005\u0011q\u0007\u0005\b\u0003w\u0001A\u0011AA\u001f\u0011\u001d\t\t\u0005\u0001C\u0001\u0003\u0007Baa\u001f\u0001\u0005\u0002\u0005\u001d\u0003BB@\u0001\t\u0003\tI\u0006C\u0004\u0002\u0006\u0001!\t!!\u0018\t\u000f\u0005-\u0001\u0001\"\u0001\u0002b!9\u0011\u0011\u0003\u0001\u0005\u0002\u0005\u0015\u0004bBA\f\u0001\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003G\u0001A\u0011AA7\u0011\u001d\tI\u0003\u0001C\u0001\u0003cBq!a\f\u0001\t\u0003\t)\bC\u0004\u00026\u0001!\t!!\u001f\t\u000f\u0005m\u0002\u0001\"\u0001\u0002~!9\u0011\u0011\t\u0001\u0005\u0002\u0005\u0005\u0005bBAC\u0001\u0011\u0005\u0011q\u0011\u0005\b\u0003\u0013\u0003A\u0011IAF\u0011\u001d\t\t\n\u0001C!\u0003'Cq!a&\u0001\t\u0003\nI\nC\u0004\u0002\u001e\u0002!\t%a(\t\u000f\u0005\r\u0006\u0001\"\u0001\u0002&\"9\u0011\u0011\u0016\u0001\u0005\u0002\u0005-\u0006bBAY\u0001\u0011\u0005\u00111\u0017\u0005\b\u0003o\u0003A\u0011IA]\u0011\u001d\t\u0019\r\u0001C\u0001\u0003\u000fCq!!2\u0001\t\u0003\t9\rC\u0004\u0002L\u0002!\t!!4\t\u000f\u0005U\u0007\u0001\"\u0001\u0002X\"1\u0011q\u001c\u0001\u0005\u0002\u0015Da!!9\u0001\t\u00039\bbBAr\u0001\u0011\u0005\u0011Q\u001d\u0005\u0007\u0003c\u0004A\u0011A8\t\u000f\u0005M\b\u0001\"\u0001\u0002v\"1!1\u0001\u0001\u0005B\u0015DqA!\u0002\u0001\t\u0003\u00119\u0001C\u0004\u0003\n\u0001!\tAa\u0003\t\r\tM\u0001\u0001\"\u0001x\u0011%\u0011)\u0002AA\u0001\n\u0003\u00119\u0002C\u0005\u0003\u001c\u0001\t\n\u0011\"\u0001\u0003\u001e!I!1\u0007\u0001\u0002\u0002\u0013\u0005#Q\u0007\u0005\t\u0005{\u0001\u0011\u0011!C\u0001o\"I!q\b\u0001\u0002\u0002\u0013\u0005!\u0011\t\u0005\n\u0005\u000f\u0002\u0011\u0011!C!\u0005\u0013B\u0011Ba\u0016\u0001\u0003\u0003%\tA!\u0017\t\u0013\tu\u0003!!A\u0005B\t}\u0003\"\u0003B2\u0001\u0005\u0005I\u0011\tB3\u000f)\u00119\u0007SA\u0001\u0012\u0003A%\u0011\u000e\u0004\n\u000f\"\u000b\t\u0011#\u0001I\u0005WBaA[!\u0005\u0002\t\r\u0005\"\u0003BC\u0003\u0006\u0005IQ\tBD\u0011%\u0011I)QA\u0001\n\u0003\u0013Y\tC\u0005\u0003\u0010\u0006\u000b\t\u0011\"!\u0003\u0012\"I!QT!\u0002\u0002\u0013%!q\u0014\u0002\r'\u00064W\rT8oO2{gn\u001a\u0006\u0003\u0013*\u000bA!\\1uQ*\t1*A\u0003ta&\u0014Xm\u0005\u0003\u0001\u001bF;\u0006C\u0001(P\u001b\u0005A\u0015B\u0001)I\u0005!\u0019\u0016MZ3M_:<\u0007C\u0001*V\u001b\u0005\u0019&\"\u0001+\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001b&a\u0002)s_\u0012,8\r\u001e\t\u00031\u0006t!!W0\u000f\u0005isV\"A.\u000b\u0005qk\u0016A\u0002\u001fs_>$hh\u0001\u0001\n\u0003QK!\u0001Y*\u0002\u000fA\f7m[1hK&\u0011!m\u0019\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003AN\u000b\u0011\u0001_\u000b\u0002MB\u0011!kZ\u0005\u0003QN\u0013A\u0001T8oO\u0006\u0011\u0001\u0010I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00051l\u0007C\u0001(\u0001\u0011\u0015!7\u00011\u0001g\u0003\u0019I7OW3s_V\t\u0001\u000f\u0005\u0002Sc&\u0011!o\u0015\u0002\b\u0005>|G.Z1o\u0003\u0015I7o\u00148f\u0003\u0015I7o\u00143e\u0003\u0019I7/\u0012<f]\u000611/[4ok6,\u0012\u0001\u001f\t\u0003%fL!A_*\u0003\u0007%sG/A\u0003%a2,8\u000f\u0006\u0002N{\")a0\u0003a\u0001M\u0006\t\u00110\u0001\u0004%[&tWo\u001d\u000b\u0004\u001b\u0006\r\u0001\"\u0002@\u000b\u0001\u00041\u0017A\u0002\u0013uS6,7\u000fF\u0002N\u0003\u0013AQA`\u0006A\u0002\u0019\fA\u0001\n3jmR\u0019Q*a\u0004\t\u000byd\u0001\u0019\u00014\u0002\u0011\u0011\u0002XM]2f]R$2!TA\u000b\u0011\u0015qX\u00021\u0001g\u00031!C-\u001b<%a\u0016\u00148-\u001a8u)\u0011\tY\"!\t\u0011\u000bI\u000bi\"T'\n\u0007\u0005}1K\u0001\u0004UkBdWM\r\u0005\u0006}:\u0001\rAZ\u0001\tKF,x\u000e^7pIR!\u00111DA\u0014\u0011\u0015qx\u00021\u0001g\u0003\u0015)\u0017/^8u)\ri\u0015Q\u0006\u0005\u0006}B\u0001\rAZ\u0001\u0005K6|G\rF\u0002N\u0003gAQA`\tA\u0002\u0019\fA\u0001J1naR\u0019Q*!\u000f\t\u000by\u0014\u0002\u0019\u00014\u0002\t\u0011\u0012\u0017M\u001d\u000b\u0004\u001b\u0006}\u0002\"\u0002@\u0014\u0001\u00041\u0017a\u0001\u0013vaR\u0019Q*!\u0012\t\u000by$\u0002\u0019\u00014\u0015\u00075\u000bI\u0005\u0003\u0004\u007f+\u0001\u0007\u00111\n\t\u0005\u0003\u001b\n)&\u0004\u0002\u0002P)\u0019\u0011*!\u0015\u000b\u0005\u0005M\u0013\u0001\u00026bm\u0006LA!a\u0016\u0002P\tQ!)[4J]R,w-\u001a:\u0015\u00075\u000bY\u0006\u0003\u0004\u007f-\u0001\u0007\u00111\n\u000b\u0004\u001b\u0006}\u0003B\u0002@\u0018\u0001\u0004\tY\u0005F\u0002N\u0003GBaA \rA\u0002\u0005-CcA'\u0002h!1a0\u0007a\u0001\u0003\u0017\"B!a\u0007\u0002l!1aP\u0007a\u0001\u0003\u0017\"B!a\u0007\u0002p!1ap\u0007a\u0001\u0003\u0017\"2!TA:\u0011\u0019qH\u00041\u0001\u0002LQ\u0019Q*a\u001e\t\ryl\u0002\u0019AA&)\ri\u00151\u0010\u0005\u0007}z\u0001\r!a\u0013\u0015\u00075\u000by\b\u0003\u0004\u007f?\u0001\u0007\u00111\n\u000b\u0004\u001b\u0006\r\u0005B\u0002@!\u0001\u0004\tY%\u0001\u0007v]\u0006\u0014\u0018p\u0018\u0013nS:,8/F\u0001N\u0003\u0015!C.Z:t)\r\u0001\u0018Q\u0012\u0005\u0007\u0003\u001f\u0013\u0003\u0019A'\u0002\tQD\u0017\r^\u0001\tI1,7o\u001d\u0013fcR\u0019\u0001/!&\t\r\u0005=5\u00051\u0001N\u0003!!sM]3bi\u0016\u0014Hc\u00019\u0002\u001c\"1\u0011q\u0012\u0013A\u00025\u000b1\u0002J4sK\u0006$XM\u001d\u0013fcR\u0019\u0001/!)\t\r\u0005=U\u00051\u0001N\u0003\u001d\u0019w.\u001c9be\u0016$2\u0001_AT\u0011\u0019\tyI\na\u0001\u001b\u0006QA\u0005\\3tg\u0012bWm]:\u0015\u00075\u000bi\u000b\u0003\u0004\u00020\u001e\u0002\r\u0001_\u0001\u0002]\u0006\u0001Be\u001a:fCR,'\u000fJ4sK\u0006$XM\u001d\u000b\u0004\u001b\u0006U\u0006BBAXQ\u0001\u0007\u00010\u0001\u0004fcV\fGn\u001d\u000b\u0004a\u0006m\u0006bBAHS\u0001\u0007\u0011Q\u0018\t\u0004%\u0006}\u0016bAAa'\n\u0019\u0011I\\=\u0002\u0007\u0005\u00147/A\u0002hG\u0012$2!TAe\u0011\u0019\tyi\u000ba\u0001\u001b\u0006YAm\\;cY\u00164\u0016\r\\;f+\t\ty\rE\u0002S\u0003#L1!a5T\u0005\u0019!u.\u001e2mK\u0006Qa\r\\8biZ\u000bG.^3\u0016\u0005\u0005e\u0007c\u0001*\u0002\\&\u0019\u0011Q\\*\u0003\u000b\u0019cw.\u0019;\u0002\u00131|gn\u001a,bYV,\u0017\u0001C5oiZ\u000bG.^3\u0002\u0015UtG-\u001a:ms&tw\r\u0006\u0002\u0002hB!\u0011\u0011^Ax\u001b\t\tYO\u0003\u0003\u0002n\u0006E\u0013\u0001\u00027b]\u001eL1\u0001[Av\u0003-I7OV1mS\u0012duN\\4\u0002\u000f\u001d,G\u000fT8oOV\u0011\u0011q\u001f\t\u0006\u0003s\fyPZ\u0007\u0003\u0003wT1!!@K\u0003\u0011)H/\u001b7\n\t\t\u0005\u00111 \u0002\u0004\u001fB$\u0018A\u0002;p\u0019>tw-\u0001\u0007u_\nKw-\u00138uK\u001e,'/\u0006\u0002\u0002L\u0005aAo\u001c\"jO\u0012+7-[7bYV\u0011!Q\u0002\t\u00041\n=\u0011b\u0001B\tG\nQ!)[4EK\u000eLW.\u00197\u0002\u0013\tLG\u000fT3oORD\u0017\u0001B2paf$2\u0001\u001cB\r\u0011\u001d!w\u0007%AA\u0002\u0019\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003 )\u001aaM!\t,\u0005\t\r\u0002\u0003\u0002B\u0013\u0005_i!Aa\n\u000b\t\t%\"1F\u0001\nk:\u001c\u0007.Z2lK\u0012T1A!\fT\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005c\u00119CA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001B\u001c!\u0011\tIO!\u000f\n\t\tm\u00121\u001e\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011Q\u0018B\"\u0011!\u0011)eOA\u0001\u0002\u0004A\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003LA1!Q\nB*\u0003{k!Aa\u0014\u000b\u0007\tE3+\u0001\u0006d_2dWm\u0019;j_:LAA!\u0016\u0003P\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r\u0001(1\f\u0005\n\u0005\u000bj\u0014\u0011!a\u0001\u0003{\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!q\u0007B1\u0011!\u0011)EPA\u0001\u0002\u0004A\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003a\fAbU1gK2{gn\u001a'p]\u001e\u0004\"AT!\u0014\u000b\u0005\u0013iG!\u001f\u0011\r\t=$Q\u000f4m\u001b\t\u0011\tHC\u0002\u0003tM\u000bqA];oi&lW-\u0003\u0003\u0003x\tE$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!!1\u0010BA\u001b\t\u0011iH\u0003\u0003\u0003\u0000\u0005E\u0013AA5p\u0013\r\u0011'Q\u0010\u000b\u0003\u0005S\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005o\tQ!\u00199qYf$2\u0001\u001cBG\u0011\u0015!G\t1\u0001g\u0003\u001d)h.\u00199qYf$BAa%\u0003\u001aB!!K!&g\u0013\r\u00119j\u0015\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\tmU)!AA\u00021\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011\t\u000b\u0005\u0003\u0002j\n\r\u0016\u0002\u0002BS\u0003W\u0014aa\u00142kK\u000e$\b"
)
public final class SafeLongLong extends SafeLong implements Product {
   private final long x;

   public static Option unapply(final SafeLongLong x$0) {
      return SafeLongLong$.MODULE$.unapply(x$0);
   }

   public static SafeLongLong apply(final long x) {
      return SafeLongLong$.MODULE$.apply(x);
   }

   public static Function1 andThen(final Function1 g) {
      return SafeLongLong$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SafeLongLong$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long x() {
      return this.x;
   }

   public boolean isZero() {
      return this.x() == 0L;
   }

   public boolean isOne() {
      return this.x() == 1L;
   }

   public boolean isOdd() {
      return (this.x() & 1L) != 0L;
   }

   public boolean isEven() {
      return (this.x() & 1L) == 0L;
   }

   public int signum() {
      return Long.signum(this.x());
   }

   public SafeLong $plus(final long y) {
      Object var10000;
      try {
         var10000 = this.checked$attempt$macro$1$1(y);
      } catch (ArithmeticException var3) {
         var10000 = new SafeLongBigInteger(BigInteger.valueOf(this.x()).add(BigInteger.valueOf(y)));
      }

      return (SafeLong)var10000;
   }

   public SafeLong $minus(final long y) {
      Object var10000;
      try {
         var10000 = this.checked$attempt$macro$1$2(y);
      } catch (ArithmeticException var3) {
         var10000 = new SafeLongBigInteger(BigInteger.valueOf(this.x()).subtract(BigInteger.valueOf(y)));
      }

      return (SafeLong)var10000;
   }

   public SafeLong $times(final long y) {
      Object var10000;
      try {
         var10000 = this.checked$attempt$macro$1$3(y);
      } catch (ArithmeticException var3) {
         var10000 = new SafeLongBigInteger(BigInteger.valueOf(this.x()).multiply(BigInteger.valueOf(y)));
      }

      return (SafeLong)var10000;
   }

   public SafeLong $div(final long y) {
      return (SafeLong)(this.x() == Long.MIN_VALUE && y == -1L ? SafeLong$.MODULE$.safe64() : new SafeLongLong(this.x() / y));
   }

   public SafeLong $percent(final long y) {
      return (SafeLong)(this.x() == Long.MIN_VALUE && y == -1L ? SafeLong$.MODULE$.zero() : new SafeLongLong(this.x() % y));
   }

   public Tuple2 $div$percent(final long y) {
      return this.x() == Long.MIN_VALUE && y == -1L ? new Tuple2(SafeLong$.MODULE$.safe64(), SafeLong$.MODULE$.zero()) : new Tuple2(new SafeLongLong(this.x() / y), new SafeLongLong(this.x() % y));
   }

   public Tuple2 equotmod(final long y) {
      Tuple2 var10000;
      if (this.x() == Long.MIN_VALUE && y == -1L) {
         var10000 = new Tuple2(SafeLong$.MODULE$.safe64(), SafeLong$.MODULE$.zero());
      } else {
         Tuple2 var5 = package$.MODULE$.equotmod(this.x(), y);
         if (var5 == null) {
            throw new MatchError(var5);
         }

         long q = var5._1$mcJ$sp();
         long r = var5._2$mcJ$sp();
         Tuple2.mcJJ.sp var3 = new Tuple2.mcJJ.sp(q, r);
         long q = ((Tuple2)var3)._1$mcJ$sp();
         long r = ((Tuple2)var3)._2$mcJ$sp();
         var10000 = new Tuple2(new SafeLongLong(q), new SafeLongLong(r));
      }

      return var10000;
   }

   public SafeLong equot(final long y) {
      return (SafeLong)(this.x() == Long.MIN_VALUE && y == -1L ? SafeLong$.MODULE$.safe64() : new SafeLongLong(package$.MODULE$.equot(this.x(), y)));
   }

   public SafeLong emod(final long y) {
      return (SafeLong)(this.x() == Long.MIN_VALUE && y == -1L ? SafeLong$.MODULE$.zero() : new SafeLongLong(package$.MODULE$.emod(this.x(), y)));
   }

   public SafeLong $amp(final long y) {
      return new SafeLongLong(this.x() & y);
   }

   public SafeLong $bar(final long y) {
      return new SafeLongLong(this.x() | y);
   }

   public SafeLong $up(final long y) {
      return new SafeLongLong(this.x() ^ y);
   }

   public SafeLong $plus(final BigInteger y) {
      return y.bitLength() <= 63 ? this.$plus(y.longValue()) : SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(BigInteger.valueOf(this.x()).add(y)));
   }

   public SafeLong $minus(final BigInteger y) {
      return y.bitLength() <= 63 ? this.$minus(y.longValue()) : SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(BigInteger.valueOf(this.x()).subtract(y)));
   }

   public SafeLong $times(final BigInteger y) {
      return y.bitLength() <= 63 ? this.$times(y.longValue()) : SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(BigInteger.valueOf(this.x()).multiply(y)));
   }

   public SafeLong $div(final BigInteger y) {
      return y.bitLength() <= 63 ? this.$div(y.longValue()) : (this.x() == Long.MIN_VALUE && y.equals(SafeLong$.MODULE$.big64()) ? SafeLong$.MODULE$.minusOne() : SafeLong$.MODULE$.zero());
   }

   public SafeLong $percent(final BigInteger y) {
      return (SafeLong)(y.bitLength() <= 63 ? this.$percent(y.longValue()) : (this.x() == Long.MIN_VALUE && y.equals(SafeLong$.MODULE$.big64()) ? SafeLong$.MODULE$.zero() : this));
   }

   public Tuple2 $div$percent(final BigInteger y) {
      return y.bitLength() <= 63 ? this.$div$percent(y.longValue()) : (this.x() == Long.MIN_VALUE && y.equals(SafeLong$.MODULE$.big64()) ? new Tuple2(SafeLong$.MODULE$.minusOne(), SafeLong$.MODULE$.zero()) : new Tuple2(SafeLong$.MODULE$.zero(), this));
   }

   public Tuple2 equotmod(final BigInteger y) {
      Tuple2 var10000;
      if (y.bitLength() <= 63) {
         var10000 = this.equotmod(y.longValue());
      } else {
         Tuple2 var4 = package$.MODULE$.equotmod(BigInteger.valueOf(this.x()), y);
         if (var4 == null) {
            throw new MatchError(var4);
         }

         BigInteger q = (BigInteger)var4._1();
         BigInteger r = (BigInteger)var4._2();
         Tuple2 var2 = new Tuple2(q, r);
         BigInteger q = (BigInteger)var2._1();
         BigInteger r = (BigInteger)var2._2();
         var10000 = new Tuple2(SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(q)), SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(r)));
      }

      return var10000;
   }

   public SafeLong equot(final BigInteger y) {
      SafeLong var10000;
      if (y.bitLength() <= 63) {
         var10000 = this.equot(y.longValue());
      } else {
         BigInteger q = package$.MODULE$.equot(BigInteger.valueOf(this.x()), y);
         var10000 = SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(q));
      }

      return var10000;
   }

   public SafeLong emod(final BigInteger y) {
      SafeLong var10000;
      if (y.bitLength() <= 63) {
         var10000 = this.emod(y.longValue());
      } else {
         BigInteger r = package$.MODULE$.emod(BigInteger.valueOf(this.x()), y);
         var10000 = SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(r));
      }

      return var10000;
   }

   public SafeLong $amp(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(BigInteger.valueOf(this.x()).and(y)));
   }

   public SafeLong $bar(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(BigInteger.valueOf(this.x()).or(y)));
   }

   public SafeLong $up(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(BigInteger.valueOf(this.x()).xor(y)));
   }

   public SafeLong unary_$minus() {
      Object var10000;
      try {
         var10000 = this.checked$attempt$macro$1$4();
      } catch (ArithmeticException var1) {
         var10000 = new SafeLongBigInteger(BigInteger.valueOf(this.x()).negate());
      }

      return (SafeLong)var10000;
   }

   public boolean $less(final SafeLong that) {
      boolean var2;
      if (that instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)that;
         long y = var4.x();
         var2 = this.x() < y;
      } else {
         if (!(that instanceof SafeLongBigInteger)) {
            throw new MatchError(that);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)that;
         BigInteger y = var7.x();
         var2 = y.signum() > 0;
      }

      return var2;
   }

   public boolean $less$eq(final SafeLong that) {
      boolean var2;
      if (that instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)that;
         long y = var4.x();
         var2 = this.x() <= y;
      } else {
         if (!(that instanceof SafeLongBigInteger)) {
            throw new MatchError(that);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)that;
         BigInteger y = var7.x();
         var2 = y.signum() > 0;
      }

      return var2;
   }

   public boolean $greater(final SafeLong that) {
      boolean var2;
      if (that instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)that;
         long y = var4.x();
         var2 = this.x() > y;
      } else {
         if (!(that instanceof SafeLongBigInteger)) {
            throw new MatchError(that);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)that;
         BigInteger y = var7.x();
         var2 = y.signum() < 0;
      }

      return var2;
   }

   public boolean $greater$eq(final SafeLong that) {
      boolean var2;
      if (that instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)that;
         long y = var4.x();
         var2 = this.x() >= y;
      } else {
         if (!(that instanceof SafeLongBigInteger)) {
            throw new MatchError(that);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)that;
         BigInteger y = var7.x();
         var2 = y.signum() < 0;
      }

      return var2;
   }

   public int compare(final SafeLong that) {
      int var2;
      if (that instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)that;
         long y = var4.x();
         var2 = (new RichLong(scala.Predef..MODULE$.longWrapper(this.x()))).compare(BoxesRunTime.boxToLong(y));
      } else {
         if (!(that instanceof SafeLongBigInteger)) {
            throw new MatchError(that);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)that;
         BigInteger y = var7.x();
         var2 = -y.signum();
      }

      return var2;
   }

   public SafeLong $less$less(final int n) {
      if (this.x() == 0L) {
         return this;
      } else if (n < 0) {
         return this.$greater$greater(-n);
      } else {
         if (n < 64) {
            if (this.x() >= 0L) {
               if (this.x() <= Long.MAX_VALUE >> n) {
                  return new SafeLongLong(this.x() << n);
               }
            } else if (this.x() >= Long.MIN_VALUE >> n) {
               return new SafeLongLong(this.x() << n);
            }
         }

         return new SafeLongBigInteger(BigInteger.valueOf(this.x()).shiftLeft(n));
      }
   }

   public SafeLong $greater$greater(final int n) {
      Object var10000;
      if (n >= 64) {
         var10000 = this.x() >= 0L ? SafeLong$.MODULE$.zero() : SafeLong$.MODULE$.minusOne();
      } else if (n >= 0) {
         var10000 = new SafeLongLong(this.x() >> n);
      } else {
         if (n == Integer.MIN_VALUE) {
            throw new ArithmeticException(">> MinValue not supported");
         }

         var10000 = this.$less$less(-n);
      }

      return (SafeLong)var10000;
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)that;
         long y = var4.x();
         var2 = this.x() == y;
      } else if (that instanceof SafeLongBigInteger) {
         var2 = false;
      } else if (that instanceof BigInt) {
         BigInt var7 = (BigInt)that;
         var2 = var7.bitLength() > 63 ? false : var7.toLong() == this.x();
      } else {
         var2 = BoxesRunTime.equals(that, BoxesRunTime.boxToLong(this.x()));
      }

      return var2;
   }

   public SafeLong abs() {
      return (SafeLong)(this.x() >= 0L ? this : (this.x() == Long.MIN_VALUE ? SafeLong$.MODULE$.safe64() : SafeLong$.MODULE$.apply(-this.x())));
   }

   public SafeLong gcd(final SafeLong that) {
      SafeLong var2;
      if (that instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)that;
         long y = var4.x();
         var2 = SafeLong$.MODULE$.longGcd(this.x(), y);
      } else {
         if (!(that instanceof SafeLongBigInteger)) {
            throw new MatchError(that);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)that;
         BigInteger y = var7.x();
         var2 = SafeLong$.MODULE$.mixedGcd(this.x(), y);
      }

      return var2;
   }

   public double doubleValue() {
      return (double)this.x();
   }

   public float floatValue() {
      return (float)this.x();
   }

   public long longValue() {
      return this.x();
   }

   public int intValue() {
      return (int)this.x();
   }

   public Long underlying() {
      return this.x();
   }

   public boolean isValidLong() {
      return true;
   }

   public Long getLong() {
      return (Long)spire.util.Opt..MODULE$.apply(BoxesRunTime.boxToLong(this.x()));
   }

   public long toLong() {
      return this.x();
   }

   public BigInteger toBigInteger() {
      return BigInteger.valueOf(this.x());
   }

   public BigDecimal toBigDecimal() {
      return scala.package..MODULE$.BigDecimal().apply(this.x());
   }

   public int bitLength() {
      return 64 - Long.numberOfLeadingZeros(this.x());
   }

   public SafeLongLong copy(final long x) {
      return new SafeLongLong(x);
   }

   public long copy$default$1() {
      return this.x();
   }

   public String productPrefix() {
      return "SafeLongLong";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToLong(this.x());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof SafeLongLong;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "x";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.x()));
      return Statics.finalizeHash(var1, 1);
   }

   private static final SafeLongLong checked$fallback$macro$2$1() {
      throw new ArithmeticOverflowException();
   }

   private final SafeLongLong checked$attempt$macro$1$1(final long y$1) {
      SafeLongLong var10000 = new SafeLongLong;
      long x$macro$4 = this.x();
      long z$macro$3 = x$macro$4 + y$1;
      if ((~(x$macro$4 ^ y$1) & (x$macro$4 ^ z$macro$3)) < 0L) {
         return checked$fallback$macro$2$1();
      } else {
         var10000.<init>(z$macro$3);
         return var10000;
      }
   }

   private static final SafeLongLong checked$fallback$macro$2$2() {
      throw new ArithmeticOverflowException();
   }

   private final SafeLongLong checked$attempt$macro$1$2(final long y$2) {
      SafeLongLong var10000 = new SafeLongLong;
      long x$macro$4 = this.x();
      long z$macro$3 = x$macro$4 - y$2;
      if (((x$macro$4 ^ y$2) & (x$macro$4 ^ z$macro$3)) < 0L) {
         return checked$fallback$macro$2$2();
      } else {
         var10000.<init>(z$macro$3);
         return var10000;
      }
   }

   private static final SafeLongLong checked$fallback$macro$2$3() {
      throw new ArithmeticOverflowException();
   }

   private final SafeLongLong checked$attempt$macro$1$3(final long y$3) {
      SafeLongLong var10000 = new SafeLongLong;
      long x$macro$4 = this.x();
      long z$macro$3 = x$macro$4 * y$3;
      if (x$macro$4 != 0L && (y$3 != z$macro$3 / x$macro$4 || x$macro$4 == -1L && y$3 == Long.MIN_VALUE)) {
         return checked$fallback$macro$2$3();
      } else {
         var10000.<init>(z$macro$3);
         return var10000;
      }
   }

   private static final SafeLongLong checked$fallback$macro$2$4() {
      throw new ArithmeticOverflowException();
   }

   private final SafeLongLong checked$attempt$macro$1$4() {
      SafeLongLong var10000 = new SafeLongLong;
      long x$macro$3 = this.x();
      if (x$macro$3 == Long.MIN_VALUE) {
         return checked$fallback$macro$2$4();
      } else {
         var10000.<init>(-x$macro$3);
         return var10000;
      }
   }

   public SafeLongLong(final long x) {
      this.x = x;
      Product.$init$(this);
   }
}
