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
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tee!B$I\u0005\"c\u0005\u0002\u00033\u0001\u0005+\u0007I\u0011A3\t\u00115\u0004!\u0011#Q\u0001\n\u0019DQA\u001c\u0001\u0005\u0002=DQA\u001d\u0001\u0005\u0002MDQa\u001e\u0001\u0005\u0002MDQ\u0001\u001f\u0001\u0005\u0002MDQ!\u001f\u0001\u0005\u0002MDQA\u001f\u0001\u0005\u0002mDaa \u0001\u0005\u0002\u0005\u0005\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\b\u0003'\u0001A\u0011AA\u000b\u0011\u001d\tI\u0002\u0001C\u0001\u00037Aq!a\b\u0001\t\u0003\t\t\u0003C\u0004\u0002&\u0001!\t!a\n\t\u000f\u0005E\u0002\u0001\"\u0001\u00024!9\u0011q\u0007\u0001\u0005\u0002\u0005e\u0002bBA\u001f\u0001\u0011\u0005\u0011q\b\u0005\b\u0003\u0007\u0002A\u0011AA#\u0011\u001d\tI\u0005\u0001C\u0001\u0003\u0017Bq!a\u0014\u0001\t\u0003\t\t\u0006\u0003\u0004\u0000\u0001\u0011\u0005\u0011Q\u000b\u0005\b\u0003\u001b\u0001A\u0011AA-\u0011\u001d\t\u0019\u0002\u0001C\u0001\u0003;Bq!!\u0007\u0001\t\u0003\t\t\u0007C\u0004\u0002 \u0001!\t!!\u001a\t\u000f\u0005\u0015\u0002\u0001\"\u0001\u0002j!9\u0011\u0011\u0007\u0001\u0005\u0002\u00055\u0004bBA\u001c\u0001\u0011\u0005\u0011\u0011\u000f\u0005\b\u0003{\u0001A\u0011AA;\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003sBq!!\u0013\u0001\t\u0003\ti\bC\u0004\u0002P\u0001!\t!!!\t\u000f\u0005\u0015\u0005\u0001\"\u0001\u0002\b\"9\u0011\u0011\u0012\u0001\u0005\u0002\u0005-\u0005bBAI\u0001\u0011\u0005\u00111\u0013\u0005\b\u00033\u0003A\u0011AAN\u0011\u001d\ty\n\u0001C!\u0003CCq!a+\u0001\t\u0003\t9\tC\u0004\u0002.\u0002!\t!a,\t\u000f\u0005M\u0006\u0001\"\u0001\u00026\"9\u0011Q\u0018\u0001\u0005\u0002\u0005}\u0006bBAd\u0001\u0011\u0005\u0011\u0011\u001a\u0005\u0007\u0003\u0017\u0004A\u0011A>\t\r\u00055\u0007\u0001\"\u0011t\u0011\u0019\ty\r\u0001C!g\"1\u0011\u0011\u001b\u0001\u0005BMDa!a5\u0001\t\u0003\u001a\bBBAk\u0001\u0011\u00053\u000fC\u0004\u0002X\u0002!\t!!7\t\u000f\u0005\u0005\b\u0001\"\u0001\u0002d\"9\u0011\u0011\u001f\u0001\u0005B\u0005%\u0007BBAz\u0001\u0011\u0005Q\rC\u0004\u0002v\u0002!\t!a>\t\r\u0005}\b\u0001\"\u0001|\u0011%\u0011\t\u0001AA\u0001\n\u0003\u0011\u0019\u0001C\u0005\u0003\b\u0001\t\n\u0011\"\u0001\u0003\n!I!q\u0004\u0001\u0002\u0002\u0013\u0005#\u0011\u0005\u0005\t\u0005_\u0001\u0011\u0011!C\u0001w\"I!\u0011\u0007\u0001\u0002\u0002\u0013\u0005!1\u0007\u0005\n\u0005s\u0001\u0011\u0011!C!\u0005wA\u0011B!\u0013\u0001\u0003\u0003%\tAa\u0013\t\u0013\t=\u0003!!A\u0005B\tE\u0003\"\u0003B+\u0001\u0005\u0005I\u0011\tB,\u000f)\u0011I\u0006SA\u0001\u0012\u0003A%1\f\u0004\n\u000f\"\u000b\t\u0011#\u0001I\u0005;BaA\\!\u0005\u0002\tU\u0004\"\u0003B<\u0003\u0006\u0005IQ\tB=\u0011%\u0011Y(QA\u0001\n\u0003\u0013i\bC\u0005\u0003\u0002\u0006\u000b\t\u0011\"!\u0003\u0004\"I!qR!\u0002\u0002\u0013%!\u0011\u0013\u0002\u0013'\u00064W\rT8oO\nKw-\u00138uK\u001e,'O\u0003\u0002J\u0015\u0006!Q.\u0019;i\u0015\u0005Y\u0015!B:qSJ,7\u0003\u0002\u0001N#^\u0003\"AT(\u000e\u0003!K!\u0001\u0015%\u0003\u0011M\u000bg-\u001a'p]\u001e\u0004\"AU+\u000e\u0003MS\u0011\u0001V\u0001\u0006g\u000e\fG.Y\u0005\u0003-N\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002YC:\u0011\u0011l\u0018\b\u00035zk\u0011a\u0017\u0006\u00039v\u000ba\u0001\u0010:p_Rt4\u0001A\u0005\u0002)&\u0011\u0001mU\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00117M\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002a'\u0006\t\u00010F\u0001g!\t97.D\u0001i\u0015\tI\u0015NC\u0001k\u0003\u0011Q\u0017M^1\n\u00051D'A\u0003\"jO&sG/Z4fe\u0006\u0011\u0001\u0010I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005A\f\bC\u0001(\u0001\u0011\u0015!7\u00011\u0001g\u0003\u0019I7OW3s_V\tA\u000f\u0005\u0002Sk&\u0011ao\u0015\u0002\b\u0005>|G.Z1o\u0003\u0015I7o\u00148f\u0003\u0015I7o\u00143e\u0003\u0019I7/\u0012<f]\u000611/[4ok6,\u0012\u0001 \t\u0003%vL!A`*\u0003\u0007%sG/A\u0003%a2,8\u000fF\u0002N\u0003\u0007Aq!!\u0002\n\u0001\u0004\t9!A\u0001z!\r\u0011\u0016\u0011B\u0005\u0004\u0003\u0017\u0019&\u0001\u0002'p]\u001e\fa\u0001J7j]V\u001cHcA'\u0002\u0012!9\u0011Q\u0001\u0006A\u0002\u0005\u001d\u0011A\u0002\u0013uS6,7\u000fF\u0002N\u0003/Aq!!\u0002\f\u0001\u0004\t9!\u0001\u0003%I&4HcA'\u0002\u001e!9\u0011Q\u0001\u0007A\u0002\u0005\u001d\u0011\u0001\u0003\u0013qKJ\u001cWM\u001c;\u0015\u00075\u000b\u0019\u0003C\u0004\u0002\u00065\u0001\r!a\u0002\u0002\u0019\u0011\"\u0017N\u001e\u0013qKJ\u001cWM\u001c;\u0015\t\u0005%\u0012q\u0006\t\u0006%\u0006-R*T\u0005\u0004\u0003[\u0019&A\u0002+va2,'\u0007C\u0004\u0002\u00069\u0001\r!a\u0002\u0002\u0011\u0015\fXo\u001c;n_\u0012$B!!\u000b\u00026!9\u0011QA\bA\u0002\u0005\u001d\u0011!B3rk>$HcA'\u0002<!9\u0011Q\u0001\tA\u0002\u0005\u001d\u0011\u0001B3n_\u0012$2!TA!\u0011\u001d\t)!\u0005a\u0001\u0003\u000f\tA\u0001J1naR\u0019Q*a\u0012\t\u000f\u0005\u0015!\u00031\u0001\u0002\b\u0005!AEY1s)\ri\u0015Q\n\u0005\b\u0003\u000b\u0019\u0002\u0019AA\u0004\u0003\r!S\u000f\u001d\u000b\u0004\u001b\u0006M\u0003bBA\u0003)\u0001\u0007\u0011q\u0001\u000b\u0004\u001b\u0006]\u0003BBA\u0003+\u0001\u0007a\rF\u0002N\u00037Ba!!\u0002\u0017\u0001\u00041GcA'\u0002`!1\u0011QA\fA\u0002\u0019$2!TA2\u0011\u0019\t)\u0001\u0007a\u0001MR\u0019Q*a\u001a\t\r\u0005\u0015\u0011\u00041\u0001g)\u0011\tI#a\u001b\t\r\u0005\u0015!\u00041\u0001g)\u0011\tI#a\u001c\t\r\u0005\u00151\u00041\u0001g)\ri\u00151\u000f\u0005\u0007\u0003\u000ba\u0002\u0019\u00014\u0015\u00075\u000b9\b\u0003\u0004\u0002\u0006u\u0001\rA\u001a\u000b\u0004\u001b\u0006m\u0004BBA\u0003=\u0001\u0007a\rF\u0002N\u0003\u007fBa!!\u0002 \u0001\u00041GcA'\u0002\u0004\"1\u0011Q\u0001\u0011A\u0002\u0019\fA\"\u001e8bef|F%\\5okN,\u0012!T\u0001\bG>l\u0007/\u0019:f)\ra\u0018Q\u0012\u0005\u0007\u0003\u001f\u0013\u0003\u0019A'\u0002\tQD\u0017\r^\u0001\u000bI1,7o\u001d\u0013mKN\u001cHcA'\u0002\u0016\"1\u0011qS\u0012A\u0002q\f\u0011A\\\u0001\u0011I\u001d\u0014X-\u0019;fe\u0012:'/Z1uKJ$2!TAO\u0011\u0019\t9\n\na\u0001y\u00061Q-];bYN$2\u0001^AR\u0011\u001d\ty)\na\u0001\u0003K\u00032AUAT\u0013\r\tIk\u0015\u0002\u0004\u0003:L\u0018aA1cg\u0006\u0019qm\u00193\u0015\u00075\u000b\t\f\u0003\u0004\u0002\u0010\u001e\u0002\r!T\u0001\fI>,(\r\\3WC2,X-\u0006\u0002\u00028B\u0019!+!/\n\u0007\u0005m6K\u0001\u0004E_V\u0014G.Z\u0001\u000bM2|\u0017\r\u001e,bYV,WCAAa!\r\u0011\u00161Y\u0005\u0004\u0003\u000b\u001c&!\u0002$m_\u0006$\u0018!\u00037p]\u001e4\u0016\r\\;f+\t\t9!\u0001\u0005j]R4\u0016\r\\;f\u0003-I7OV1mS\u0012\u0014\u0015\u0010^3\u0002\u0019%\u001ch+\u00197jINCwN\u001d;\u0002\u0015%\u001ch+\u00197jI&sG/A\u0006jgZ\u000bG.\u001b3M_:<\u0017aC5t-\u0006d\u0017\u000eZ\"iCJ\f!\"\u001e8eKJd\u00170\u001b8h)\t\tY\u000eE\u0002Y\u0003;L1!a8d\u0005\u0019\u0011\u0015nZ%oi\u00069q-\u001a;M_:<WCAAs!\u0019\t9/!<\u0002\b5\u0011\u0011\u0011\u001e\u0006\u0004\u0003WT\u0015\u0001B;uS2LA!a<\u0002j\n\u0019q\n\u001d;\u0002\rQ|Gj\u001c8h\u00031!xNQ5h\u0013:$XmZ3s\u00031!xNQ5h\t\u0016\u001c\u0017.\\1m+\t\tI\u0010E\u0002Y\u0003wL1!!@d\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\\\u0001\nE&$H*\u001a8hi\"\fAaY8qsR\u0019\u0001O!\u0002\t\u000f\u0011<\u0004\u0013!a\u0001M\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001B\u0006U\r1'QB\u0016\u0003\u0005\u001f\u0001BA!\u0005\u0003\u001c5\u0011!1\u0003\u0006\u0005\u0005+\u00119\"A\u0005v]\u000eDWmY6fI*\u0019!\u0011D*\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003\u001e\tM!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"Aa\t\u0011\t\t\u0015\"1F\u0007\u0003\u0005OQ1A!\u000bj\u0003\u0011a\u0017M\\4\n\t\t5\"q\u0005\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011Q\u0015B\u001b\u0011!\u00119dOA\u0001\u0002\u0004a\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003>A1!q\bB#\u0003Kk!A!\u0011\u000b\u0007\t\r3+\u0001\u0006d_2dWm\u0019;j_:LAAa\u0012\u0003B\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r!(Q\n\u0005\n\u0005oi\u0014\u0011!a\u0001\u0003K\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!1\u0005B*\u0011!\u00119DPA\u0001\u0002\u0004a\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003q\f!cU1gK2{gn\u001a\"jO&sG/Z4feB\u0011a*Q\n\u0006\u0003\n}#1\u000e\t\u0007\u0005C\u00129G\u001a9\u000e\u0005\t\r$b\u0001B3'\u00069!/\u001e8uS6,\u0017\u0002\u0002B5\u0005G\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\u0011iGa\u001d\u000e\u0005\t=$b\u0001B9S\u0006\u0011\u0011n\\\u0005\u0004E\n=DC\u0001B.\u0003!!xn\u0015;sS:<GC\u0001B\u0012\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0001(q\u0010\u0005\u0006I\u0012\u0003\rAZ\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011)Ia#\u0011\tI\u00139IZ\u0005\u0004\u0005\u0013\u001b&AB(qi&|g\u000e\u0003\u0005\u0003\u000e\u0016\u000b\t\u00111\u0001q\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005'\u0003BA!\n\u0003\u0016&!!q\u0013B\u0014\u0005\u0019y%M[3di\u0002"
)
public final class SafeLongBigInteger extends SafeLong implements Product {
   private final BigInteger x;

   public static Option unapply(final SafeLongBigInteger x$0) {
      return SafeLongBigInteger$.MODULE$.unapply(x$0);
   }

   public static SafeLongBigInteger apply(final BigInteger x) {
      return SafeLongBigInteger$.MODULE$.apply(x);
   }

   public static Function1 andThen(final Function1 g) {
      return SafeLongBigInteger$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return SafeLongBigInteger$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public BigInteger x() {
      return this.x;
   }

   public boolean isZero() {
      return false;
   }

   public boolean isOne() {
      return false;
   }

   public boolean isOdd() {
      return this.x().testBit(0);
   }

   public boolean isEven() {
      return !this.x().testBit(0);
   }

   public int signum() {
      return this.x().signum();
   }

   public SafeLong $plus(final long y) {
      return (SafeLong)(((long)this.x().signum() ^ y) < 0L ? SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().add(BigInteger.valueOf(y)))) : new SafeLongBigInteger(this.x().add(BigInteger.valueOf(y))));
   }

   public SafeLong $minus(final long y) {
      return (SafeLong)(((long)this.x().signum() ^ y) >= 0L ? SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().subtract(BigInteger.valueOf(y)))) : new SafeLongBigInteger(this.x().subtract(BigInteger.valueOf(y))));
   }

   public SafeLong $times(final long y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().multiply(BigInteger.valueOf(y))));
   }

   public SafeLong $div(final long y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().divide(BigInteger.valueOf(y))));
   }

   public SafeLong $percent(final long y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().remainder(BigInteger.valueOf(y))));
   }

   public Tuple2 $div$percent(final long y) {
      BigInteger[] a = this.x().divideAndRemainder(BigInteger.valueOf(y));
      BigInteger q = a[0];
      BigInteger r = a[1];
      return new Tuple2(SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(q)), SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(r)));
   }

   public Tuple2 equotmod(final long y) {
      return this.equotmod(BigInteger.valueOf(y));
   }

   public SafeLong equot(final long y) {
      return this.equot(BigInteger.valueOf(y));
   }

   public SafeLong emod(final long y) {
      return this.emod(BigInteger.valueOf(y));
   }

   public SafeLong $amp(final long y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().and(BigInteger.valueOf(y))));
   }

   public SafeLong $bar(final long y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().or(BigInteger.valueOf(y))));
   }

   public SafeLong $up(final long y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().xor(BigInteger.valueOf(y))));
   }

   public SafeLong $plus(final BigInteger y) {
      return (SafeLong)((this.x().signum() ^ y.signum()) < 0 ? SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().add(y))) : new SafeLongBigInteger(this.x().add(y)));
   }

   public SafeLong $minus(final BigInteger y) {
      return (SafeLong)((this.x().signum() ^ y.signum()) < 0 ? new SafeLongBigInteger(this.x().subtract(y)) : SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().subtract(y))));
   }

   public SafeLong $times(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().multiply(y)));
   }

   public SafeLong $div(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().divide(y)));
   }

   public SafeLong $percent(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().remainder(y)));
   }

   public Tuple2 $div$percent(final BigInteger y) {
      BigInteger[] a = this.x().divideAndRemainder(y);
      BigInteger q = a[0];
      BigInteger r = a[1];
      return new Tuple2(SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(q)), SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(r)));
   }

   public Tuple2 equotmod(final BigInteger y) {
      BigInteger[] a = this.x().divideAndRemainder(y);
      BigInteger qt = a[0];
      BigInteger rt = a[1];
      return rt.signum() >= 0 ? new Tuple2(SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(qt)), SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(rt))) : (y.signum() > 0 ? new Tuple2(SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(qt.subtract(BigInteger.ONE))), SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(rt.add(y)))) : new Tuple2(SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(qt.add(BigInteger.ONE))), SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(rt.subtract(y)))));
   }

   public SafeLong equot(final BigInteger y) {
      BigInteger[] a = this.x().divideAndRemainder(y);
      BigInteger qt = a[0];
      BigInteger rt = a[1];
      return rt.signum() >= 0 ? SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(qt)) : (y.signum() > 0 ? SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(qt.subtract(BigInteger.ONE))) : SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(qt.add(BigInteger.ONE))));
   }

   public SafeLong emod(final BigInteger y) {
      BigInteger rt = this.x().remainder(y);
      return rt.signum() >= 0 ? SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(rt)) : (y.signum() > 0 ? SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(rt.add(y))) : SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(rt.subtract(y))));
   }

   public SafeLong $amp(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().and(y)));
   }

   public SafeLong $bar(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().or(y)));
   }

   public SafeLong $up(final BigInteger y) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().xor(y)));
   }

   public SafeLong unary_$minus() {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().negate()));
   }

   public int compare(final SafeLong that) {
      int var2;
      if (that instanceof SafeLongLong) {
         var2 = this.x().signum();
      } else {
         if (!(that instanceof SafeLongBigInteger)) {
            throw new MatchError(that);
         }

         SafeLongBigInteger var4 = (SafeLongBigInteger)that;
         BigInteger y = var4.x();
         var2 = this.x().compareTo(y);
      }

      return var2;
   }

   public SafeLong $less$less(final int n) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().shiftLeft(n)));
   }

   public SafeLong $greater$greater(final int n) {
      return SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().shiftRight(n)));
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof SafeLongLong) {
         var2 = false;
      } else if (that instanceof SafeLongBigInteger) {
         SafeLongBigInteger var4 = (SafeLongBigInteger)that;
         BigInteger y = var4.x();
         var2 = BoxesRunTime.equalsNumNum(this.x(), y);
      } else if (that instanceof BigInt) {
         BigInt var6 = (BigInt)that;
         var2 = this.x().equals(var6.bigInteger());
      } else {
         var2 = BoxesRunTime.equals(that, scala.package..MODULE$.BigInt().apply(this.x()));
      }

      return var2;
   }

   public SafeLong abs() {
      return this.x().signum() >= 0 ? this : new SafeLongBigInteger(this.x().negate());
   }

   public SafeLong gcd(final SafeLong that) {
      SafeLong var2;
      if (that instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)that;
         long y = var4.x();
         var2 = SafeLong$.MODULE$.mixedGcd(y, this.x());
      } else {
         if (!(that instanceof SafeLongBigInteger)) {
            throw new MatchError(that);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)that;
         BigInteger y = var7.x();
         var2 = SafeLong$.MODULE$.apply(.MODULE$.javaBigInteger2bigInt(this.x().gcd(y)));
      }

      return var2;
   }

   public double doubleValue() {
      return this.x().doubleValue();
   }

   public float floatValue() {
      return this.x().floatValue();
   }

   public long longValue() {
      return this.x().longValue();
   }

   public int intValue() {
      return this.x().intValue();
   }

   public boolean isValidByte() {
      return false;
   }

   public boolean isValidShort() {
      return false;
   }

   public boolean isValidInt() {
      return false;
   }

   public boolean isValidLong() {
      return false;
   }

   public boolean isValidChar() {
      return false;
   }

   public BigInt underlying() {
      return scala.package..MODULE$.BigInt().apply(this.x());
   }

   public Long getLong() {
      return (Long)spire.util.Opt..MODULE$.empty();
   }

   public long toLong() {
      return this.x().longValue();
   }

   public BigInteger toBigInteger() {
      return this.x();
   }

   public BigDecimal toBigDecimal() {
      return scala.package..MODULE$.BigDecimal().apply(.MODULE$.javaBigInteger2bigInt(this.x()));
   }

   public int bitLength() {
      return this.x().bitLength();
   }

   public SafeLongBigInteger copy(final BigInteger x) {
      return new SafeLongBigInteger(x);
   }

   public BigInteger copy$default$1() {
      return this.x();
   }

   public String productPrefix() {
      return "SafeLongBigInteger";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.x();
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
      return x$1 instanceof SafeLongBigInteger;
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
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public SafeLongBigInteger(final BigInteger x) {
      this.x = x;
      Product.$init$(this);
   }
}
