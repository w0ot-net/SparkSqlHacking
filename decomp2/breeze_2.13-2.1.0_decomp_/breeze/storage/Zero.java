package breeze.storage;

import breeze.math.Semiring;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tUc\u0001B\u0017/\u0001NB\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t7\u0002\u0011\t\u0012)A\u0005\u0019\")A\f\u0001C\u0001;\"9\u0011\rAA\u0001\n\u0003\u0011\u0007bB5\u0001#\u0003%\tA\u001b\u0005\bq\u0002\t\t\u0011\"\u0011z\u0011%\t)\u0001AA\u0001\n\u0003\t9\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0001\u0002\u0012!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0004\u0005\n\u0003O\u0001\u0011\u0011!C\u0001\u0003SA\u0011\"a\r\u0001\u0003\u0003%\t%!\u000e\t\u0013\u0005e\u0002!!A\u0005B\u0005m\u0002\"CA\u001f\u0001\u0005\u0005I\u0011IA \u0011%\t\t\u0005AA\u0001\n\u0003\n\u0019eB\u0004\u0002T9B\t!!\u0016\u0007\r5r\u0003\u0012AA,\u0011\u0019a\u0006\u0003\"\u0001\u0002d!I\u0011Q\r\tC\u0002\u0013\r\u0011q\r\u0005\t\u0003W\u0002\u0002\u0015!\u0003\u0002j!I\u0011Q\u000e\tC\u0002\u0013\r\u0011q\u000e\u0005\t\u0003s\u0002\u0002\u0015!\u0003\u0002r!I\u00111\u0010\tC\u0002\u0013\r\u0011Q\u0010\u0005\t\u0003\u000f\u0003\u0002\u0015!\u0003\u0002\u0000!I\u0011\u0011\u0012\tC\u0002\u0013\r\u00111\u0012\u0005\t\u0003+\u0003\u0002\u0015!\u0003\u0002\u000e\"I\u0011q\u0013\tC\u0002\u0013\r\u0011\u0011\u0014\u0005\t\u0003G\u0003\u0002\u0015!\u0003\u0002\u001c\"I\u0011Q\u0015\tC\u0002\u0013\r\u0011q\u0015\u0005\t\u0003c\u0003\u0002\u0015!\u0003\u0002*\"I\u00111\u0017\tC\u0002\u0013\r\u0011Q\u0017\u0005\t\u0003\u007f\u0003\u0002\u0015!\u0003\u00028\"I\u0011\u0011\u0019\tC\u0002\u0013\r\u00111\u0019\u0005\t\u0003\u000f\u0004\u0002\u0015!\u0003\u0002F\"I\u0011\u0011\u001a\tC\u0002\u0013\r\u00111\u001a\u0005\t\u0003+\u0004\u0002\u0015!\u0003\u0002N\"Q\u0011q\u001b\t\t\u0006\u0004%\u0019!!7\t\u0013\u0005\r\bC1A\u0005\u0004\u0005\u0015\b\u0002CA|!\u0001\u0006I!a:\t\u000f\u0005e\b\u0003b\u0001\u0002|\"I!Q\u0003\tC\u0002\u0013\u0005!q\u0003\u0005\t\u0005C\u0001\u0002\u0015!\u0003\u0003\u001a!I!1\u0005\t\u0002\u0002\u0013\u0005%Q\u0005\u0005\n\u0005g\u0001\u0012\u0011!CA\u0005kA\u0011Ba\u0013\u0011\u0003\u0003%IA!\u0014\u0003\ti+'o\u001c\u0006\u0003_A\nqa\u001d;pe\u0006<WMC\u00012\u0003\u0019\u0011'/Z3{K\u000e\u0001QC\u0001\u001bO'\u0011\u0001QgO$\u0011\u0005YJT\"A\u001c\u000b\u0003a\nQa]2bY\u0006L!AO\u001c\u0003\r\u0005s\u0017PU3g!\taDI\u0004\u0002>\u0005:\u0011a(Q\u0007\u0002\u007f)\u0011\u0001IM\u0001\u0007yI|w\u000e\u001e \n\u0003aJ!aQ\u001c\u0002\u000fA\f7m[1hK&\u0011QI\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0007^\u0002\"A\u000e%\n\u0005%;$a\u0002)s_\u0012,8\r^\u0001\u0005u\u0016\u0014x.F\u0001M!\tie\n\u0004\u0001\u0005\u0013=\u0003\u0001\u0015!A\u0001\u0006\u0004\u0001&!\u0001+\u0012\u0005E#\u0006C\u0001\u001cS\u0013\t\u0019vGA\u0004O_RD\u0017N\\4\u0011\u0005Y*\u0016B\u0001,8\u0005\r\te.\u001f\u0015\u0003\u001db\u0003\"AN-\n\u0005i;$aC:qK\u000eL\u0017\r\\5{K\u0012\fQA_3s_\u0002\na\u0001P5oSRtDC\u00010a!\ry\u0006\u0001T\u0007\u0002]!)!j\u0001a\u0001\u0019\u0006!1m\u001c9z+\t\u0019g\r\u0006\u0002eQB\u0019q\fA3\u0011\u000553G!C(\u0005A\u0003\u0005\tQ1\u0001QQ\t1\u0007\fC\u0004K\tA\u0005\t\u0019A3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u00111N^\u000b\u0002Y*\u0012A*\\\u0016\u0002]B\u0011q\u000e^\u0007\u0002a*\u0011\u0011O]\u0001\nk:\u001c\u0007.Z2lK\u0012T!a]\u001c\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002va\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u0013=+\u0001\u0015!A\u0001\u0006\u0004\u0001\u0006F\u0001<Y\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t!\u0010E\u0002|\u0003\u0003i\u0011\u0001 \u0006\u0003{z\fA\u0001\\1oO*\tq0\u0001\u0003kCZ\f\u0017bAA\u0002y\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u0003\u0011\u0007Y\nY!C\u0002\u0002\u000e]\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2\u0001VA\n\u0011%\t)\u0002CA\u0001\u0002\u0004\tI!A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u00037\u0001R!!\b\u0002$Qk!!a\b\u000b\u0007\u0005\u0005r'\u0001\u0006d_2dWm\u0019;j_:LA!!\n\u0002 \tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY#!\r\u0011\u0007Y\ni#C\u0002\u00020]\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0016)\t\t\u00111\u0001U\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007i\f9\u0004C\u0005\u0002\u0016-\t\t\u00111\u0001\u0002\n\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\n\u0005AAo\\*ue&tw\rF\u0001{\u0003\u0019)\u0017/^1mgR!\u00111FA#\u0011!\t)BDA\u0001\u0002\u0004!\u0006f\u0002\u0001\u0002J\u0005=\u0013\u0011\u000b\t\u0004m\u0005-\u0013bAA'o\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0003\u0005!!,\u001a:p!\ty\u0006c\u0005\u0003\u0011k\u0005e\u0003\u0003BA.\u0003Cj!!!\u0018\u000b\u0007\u0005}c0\u0001\u0002j_&\u0019Q)!\u0018\u0015\u0005\u0005U\u0013aB%oij+'o\\\u000b\u0003\u0003S\u0002Ba\u0018\u0001\u0002\n\u0005A\u0011J\u001c;[KJ|\u0007%A\u0005TQ>\u0014HOW3s_V\u0011\u0011\u0011\u000f\t\u0005?\u0002\t\u0019\bE\u00027\u0003kJ1!a\u001e8\u0005\u0015\u0019\u0006n\u001c:u\u0003)\u0019\u0006n\u001c:u5\u0016\u0014x\u000eI\u0001\t\u0019>twMW3s_V\u0011\u0011q\u0010\t\u0005?\u0002\t\t\tE\u00027\u0003\u0007K1!!\"8\u0005\u0011auN\\4\u0002\u00131{gn\u001a.fe>\u0004\u0013\u0001\u0003\"zi\u0016TVM]8\u0016\u0005\u00055\u0005\u0003B0\u0001\u0003\u001f\u00032ANAI\u0013\r\t\u0019j\u000e\u0002\u0005\u0005f$X-A\u0005CsR,',\u001a:pA\u0005A1\t[1s5\u0016\u0014x.\u0006\u0002\u0002\u001cB!q\fAAO!\r1\u0014qT\u0005\u0004\u0003C;$\u0001B\"iCJ\f\u0011b\u00115bej+'o\u001c\u0011\u0002\u0013\u0019cw.\u0019;[KJ|WCAAU!\u0011y\u0006!a+\u0011\u0007Y\ni+C\u0002\u00020^\u0012QA\u00127pCR\f!B\u00127pCRTVM]8!\u0003)!u.\u001e2mKj+'o\\\u000b\u0003\u0003o\u0003Ba\u0018\u0001\u0002:B\u0019a'a/\n\u0007\u0005uvG\u0001\u0004E_V\u0014G.Z\u0001\f\t>,(\r\\3[KJ|\u0007%A\u0006C_>dW-\u00198[KJ|WCAAc!\u0011y\u0006!a\u000b\u0002\u0019\t{w\u000e\\3b]j+'o\u001c\u0011\u0002\u0015\tKw-\u00138u5\u0016\u0014x.\u0006\u0002\u0002NB!q\fAAh!\ra\u0014\u0011[\u0005\u0004\u0003'4%A\u0002\"jO&sG/A\u0006CS\u001eLe\u000e\u001e.fe>\u0004\u0013A\u0004\"jO\u0012+7-[7bYj+'o\\\u000b\u0003\u00037\u0004Ba\u0018\u0001\u0002^B\u0019A(a8\n\u0007\u0005\u0005hI\u0001\u0006CS\u001e$UmY5nC2\f!b\u0015;sS:<',\u001a:p+\t\t9\u000f\u0005\u0003`\u0001\u0005%\b\u0003BAv\u0003gtA!!<\u0002pB\u0011ahN\u0005\u0004\u0003c<\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0004\u0005U(bAAyo\u0005Y1\u000b\u001e:j]\u001eTVM]8!\u0003AQXM]8Ge>l7+Z7je&tw-\u0006\u0003\u0002~\n\rA\u0003BA\u0000\u0005\u000b\u0001Ba\u0018\u0001\u0003\u0002A\u0019QJa\u0001\u0005\u000b=;#\u0019\u0001)\t\u000f\t\u001dq\u0005q\u0001\u0003\n\u0005!!/\u001b8h!\u0019\u0011YA!\u0005\u0003\u00025\u0011!Q\u0002\u0006\u0004\u0005\u001f\u0001\u0014\u0001B7bi\"LAAa\u0005\u0003\u000e\tA1+Z7je&tw-\u0001\u0006sK\u001a$UMZ1vYR,\"A!\u0007\u0011\t}\u0003!1\u0004\t\u0004m\tu\u0011b\u0001B\u0010o\t!a*\u001e7m\u0003-\u0011XM\u001a#fM\u0006,H\u000e\u001e\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\t\u001d\"Q\u0006\u000b\u0005\u0005S\u0011\t\u0004\u0005\u0003`\u0001\t-\u0002cA'\u0003.\u0011IqJ\u000bQ\u0001\u0002\u0003\u0015\r\u0001\u0015\u0015\u0004\u0005[A\u0006B\u0002&+\u0001\u0004\u0011Y#A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\t]\"\u0011\t\u000b\u0005\u0005s\u0011)\u0005E\u00037\u0005w\u0011y$C\u0002\u0003>]\u0012aa\u00149uS>t\u0007cA'\u0003B\u0011Iqj\u000bQ\u0001\u0002\u0003\u0015\r\u0001\u0015\u0015\u0004\u0005\u0003B\u0006\"\u0003B$W\u0005\u0005\t\u0019\u0001B%\u0003\rAH\u0005\r\t\u0005?\u0002\u0011y$\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003PA\u00191P!\u0015\n\u0007\tMCP\u0001\u0004PE*,7\r\u001e"
)
public class Zero implements Serializable, Product {
   private static final long serialVersionUID = 1L;
   public final Object zero;

   public static Option unapply(final Zero x$0) {
      return Zero$.MODULE$.unapply(x$0);
   }

   public static Zero apply(final Object zero) {
      return Zero$.MODULE$.apply(zero);
   }

   public static Zero refDefault() {
      return Zero$.MODULE$.refDefault();
   }

   public static Zero zeroFromSemiring(final Semiring ring) {
      return Zero$.MODULE$.zeroFromSemiring(ring);
   }

   public static Zero StringZero() {
      return Zero$.MODULE$.StringZero();
   }

   public static Zero BigDecimalZero() {
      return Zero$.MODULE$.BigDecimalZero();
   }

   public static Zero BigIntZero() {
      return Zero$.MODULE$.BigIntZero();
   }

   public static Zero BooleanZero() {
      return Zero$.MODULE$.BooleanZero();
   }

   public static Zero DoubleZero() {
      return Zero$.MODULE$.DoubleZero();
   }

   public static Zero FloatZero() {
      return Zero$.MODULE$.FloatZero();
   }

   public static Zero CharZero() {
      return Zero$.MODULE$.CharZero();
   }

   public static Zero ByteZero() {
      return Zero$.MODULE$.ByteZero();
   }

   public static Zero LongZero() {
      return Zero$.MODULE$.LongZero();
   }

   public static Zero ShortZero() {
      return Zero$.MODULE$.ShortZero();
   }

   public static Zero IntZero() {
      return Zero$.MODULE$.IntZero();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object zero() {
      return this.zero;
   }

   public Zero copy(final Object zero) {
      return new Zero(zero);
   }

   public Object copy$default$1() {
      return this.zero();
   }

   public String productPrefix() {
      return "Zero";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.zero();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Zero;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "zero";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Zero) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Zero var4 = (Zero)x$1;
               if (BoxesRunTime.equals(this.zero(), var4.zero()) && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean zero$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this.zero());
   }

   public byte zero$mcB$sp() {
      return BoxesRunTime.unboxToByte(this.zero());
   }

   public char zero$mcC$sp() {
      return BoxesRunTime.unboxToChar(this.zero());
   }

   public double zero$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.zero());
   }

   public float zero$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.zero());
   }

   public int zero$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.zero());
   }

   public long zero$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.zero());
   }

   public short zero$mcS$sp() {
      return BoxesRunTime.unboxToShort(this.zero());
   }

   public void zero$mcV$sp() {
      this.zero();
   }

   public Zero copy$mZc$sp(final boolean zero) {
      return new Zero$mcZ$sp(zero);
   }

   public Zero copy$mBc$sp(final byte zero) {
      return new Zero$mcB$sp(zero);
   }

   public Zero copy$mCc$sp(final char zero) {
      return new Zero$mcC$sp(zero);
   }

   public Zero copy$mDc$sp(final double zero) {
      return new Zero$mcD$sp(zero);
   }

   public Zero copy$mFc$sp(final float zero) {
      return new Zero$mcF$sp(zero);
   }

   public Zero copy$mIc$sp(final int zero) {
      return new Zero$mcI$sp(zero);
   }

   public Zero copy$mJc$sp(final long zero) {
      return new Zero$mcJ$sp(zero);
   }

   public Zero copy$mSc$sp(final short zero) {
      return new Zero$mcS$sp(zero);
   }

   public Zero copy$mVc$sp(final BoxedUnit zero) {
      return new Zero$mcV$sp(zero);
   }

   public boolean copy$default$1$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this.copy$default$1());
   }

   public byte copy$default$1$mcB$sp() {
      return BoxesRunTime.unboxToByte(this.copy$default$1());
   }

   public char copy$default$1$mcC$sp() {
      return BoxesRunTime.unboxToChar(this.copy$default$1());
   }

   public double copy$default$1$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$1());
   }

   public float copy$default$1$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$1());
   }

   public int copy$default$1$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.copy$default$1());
   }

   public long copy$default$1$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.copy$default$1());
   }

   public short copy$default$1$mcS$sp() {
      return BoxesRunTime.unboxToShort(this.copy$default$1());
   }

   public void copy$default$1$mcV$sp() {
      this.copy$default$1();
   }

   public boolean specInstance$() {
      return false;
   }

   public Zero(final Object zero) {
      this.zero = zero;
      Product.$init$(this);
   }
}
