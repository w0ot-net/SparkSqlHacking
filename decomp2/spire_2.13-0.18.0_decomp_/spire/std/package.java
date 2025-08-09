package spire.std;

import algebra.lattice.Bool;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRing;
import algebra.ring.CommutativeSemiring;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.Semigroup;
import java.math.MathContext;
import scala.collection.Factory;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.NotGiven;
import spire.algebra.CModule;
import spire.algebra.InnerProductSpace;
import spire.algebra.MetricSpace;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace;
import spire.math.BitString;
import spire.math.NumberTag;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015x!B A\u0011\u0003)e!B$A\u0011\u0003A\u0005\"B(\u0002\t\u0003\u0001v!B)\u0002\u0011\u0003\u0011f!\u0002+\u0002\u0011\u0003)\u0006\"B(\u0005\t\u0003Iv!\u0002.\u0002\u0011\u0003Yf!\u0002/\u0002\u0011\u0003i\u0006\"B(\b\t\u0003\tw!\u00022\u0002\u0011\u0003\u0019g!\u00023\u0002\u0011\u0003)\u0007\"B(\u000b\t\u0003Iw!\u00026\u0002\u0011\u0003Yg!\u00027\u0002\u0011\u0003i\u0007\"B(\u000e\t\u0003\tx!\u0002:\u0002\u0011\u0003\u0019h!\u0002;\u0002\u0011\u0003)\b\"B(\u0011\t\u0003Ix!\u0002>\u0002\u0011\u0003Yh!\u0002?\u0002\u0011\u0003i\bBB(\u0014\t\u0003\t\u0019aB\u0004\u0002\u0006\u0005A\t!a\u0002\u0007\u000f\u0005%\u0011\u0001#\u0001\u0002\f!1qJ\u0006C\u0001\u0003'9q!!\u0006\u0002\u0011\u0003\t9BB\u0004\u0002\u001a\u0005A\t!a\u0007\t\r=KB\u0011AA\u0012\u000f\u001d\t)#\u0001E\u0001\u0003O1q!!\u000b\u0002\u0011\u0003\tY\u0003\u0003\u0004P9\u0011\u0005\u00111G\u0004\b\u0003k\t\u0001\u0012AA\u001c\r\u001d\tI$\u0001E\u0001\u0003wAaaT\u0010\u0005\u0002\u0005\rsaBA#\u0003!\u0005\u0011q\t\u0004\b\u0003\u0013\n\u0001\u0012AA&\u0011\u0019y%\u0005\"\u0001\u0002T\u001d9\u0011QK\u0001\t\u0002\u0005]caBA-\u0003!\u0005\u00111\f\u0005\u0007\u001f\u0016\"\t!a\u0019\b\u000f\u0005\u0015\u0014\u0001#\u0001\u0002h\u00199\u0011\u0011N\u0001\t\u0002\u0005-\u0004BB()\t\u0003\t\u0019hB\u0004\u0002v\u0005A\t!a\u001e\u0007\u000f\u0005e\u0014\u0001#\u0001\u0002|!1qj\u000bC\u0001\u0003\u0007;q!!\"\u0002\u0011\u0003\t9IB\u0004\u0002\n\u0006A\t!a#\t\r=sC\u0011AAJ\u000f\u001d\t)*\u0001E\u0001\u0003/3q!!'\u0002\u0011\u0003\tY\n\u0003\u0004Pc\u0011\u0005\u00111U\u0004\b\u0003K\u000b\u0001\u0012AAT\r\u001d\tI+\u0001E\u0001\u0003WCaa\u0014\u001b\u0005\u0002\u0005MvaBA[\u0003!\u0005\u0011q\u0017\u0004\b\u0003s\u000b\u0001\u0012AA^\u0011\u0019yu\u0007\"\u0001\u0002D\u001e9\u0011QY\u0001\t\u0002\u0005\u001dgaBAe\u0003!\u0005\u00111\u001a\u0005\u0007\u001fj\"\t!a5\b\u000f\u0005U\u0017\u0001#\u0001\u0002X\u001a9\u0011\u0011\\\u0001\t\u0002\u0005m\u0007BB(>\t\u0003\t\u0019/A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0005\u0013\u0015aA:uI*\t1)A\u0003ta&\u0014Xm\u0001\u0001\u0011\u0005\u0019\u000bQ\"\u0001!\u0003\u000fA\f7m[1hKN\u0011\u0011!\u0013\t\u0003\u00156k\u0011a\u0013\u0006\u0002\u0019\u0006)1oY1mC&\u0011aj\u0013\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005)\u0015aA1osB\u00111\u000bB\u0007\u0002\u0003\t\u0019\u0011M\\=\u0014\u0007\u0011Ie\u000b\u0005\u0002G/&\u0011\u0001\f\u0011\u0002\r\u0003:L\u0018J\\:uC:\u001cWm\u001d\u000b\u0002%\u00069!m\\8mK\u0006t\u0007CA*\b\u0005\u001d\u0011wn\u001c7fC:\u001c2aB%_!\t1u,\u0003\u0002a\u0001\n\u0001\"i\\8mK\u0006t\u0017J\\:uC:\u001cWm\u001d\u000b\u00027\u0006!1\r[1s!\t\u0019&B\u0001\u0003dQ\u0006\u00148c\u0001\u0006JMB\u0011aiZ\u0005\u0003Q\u0002\u0013Qb\u00115be&s7\u000f^1oG\u0016\u001cH#A2\u0002\t\tLH/\u001a\t\u0003'6\u0011AAY=uKN\u0019Q\"\u00138\u0011\u0005\u0019{\u0017B\u00019A\u00055\u0011\u0015\u0010^3J]N$\u0018M\\2fgR\t1.A\u0003tQ>\u0014H\u000f\u0005\u0002T!\t)1\u000f[8siN\u0019\u0001#\u0013<\u0011\u0005\u0019;\u0018B\u0001=A\u00059\u0019\u0006n\u001c:u\u0013:\u001cH/\u00198dKN$\u0012a]\u0001\u0004S:$\bCA*\u0014\u0005\rIg\u000e^\n\u0004'%s\bC\u0001$\u0000\u0013\r\t\t\u0001\u0011\u0002\r\u0013:$\u0018J\\:uC:\u001cWm\u001d\u000b\u0002w\u0006!An\u001c8h!\t\u0019fC\u0001\u0003m_:<7\u0003\u0002\fJ\u0003\u001b\u00012ARA\b\u0013\r\t\t\u0002\u0011\u0002\u000e\u0019>tw-\u00138ti\u0006t7-Z:\u0015\u0005\u0005\u001d\u0011!\u00024m_\u0006$\bCA*\u001a\u0005\u00151Gn\\1u'\u0011I\u0012*!\b\u0011\u0007\u0019\u000by\"C\u0002\u0002\"\u0001\u0013aB\u00127pCRLen\u001d;b]\u000e,7\u000f\u0006\u0002\u0002\u0018\u00051Am\\;cY\u0016\u0004\"a\u0015\u000f\u0003\r\u0011|WO\u00197f'\u0011a\u0012*!\f\u0011\u0007\u0019\u000by#C\u0002\u00022\u0001\u0013q\u0002R8vE2,\u0017J\\:uC:\u001cWm\u001d\u000b\u0003\u0003O\taAY5h\u0013:$\bCA* \u0005\u0019\u0011\u0017nZ%oiN!q$SA\u001f!\r1\u0015qH\u0005\u0004\u0003\u0003\u0002%a\u0004\"jO&sG/\u00138ti\u0006t7-Z:\u0015\u0005\u0005]\u0012A\u00032jO&sG/Z4feB\u00111K\t\u0002\u000bE&<\u0017J\u001c;fO\u0016\u00148\u0003\u0002\u0012J\u0003\u001b\u00022ARA(\u0013\r\t\t\u0006\u0011\u0002\u0014\u0005&<\u0017J\u001c;fO\u0016\u0014\u0018J\\:uC:\u001cWm\u001d\u000b\u0003\u0003\u000f\n!BY5h\t\u0016\u001c\u0017.\\1m!\t\u0019VE\u0001\u0006cS\u001e$UmY5nC2\u001cB!J%\u0002^A\u0019a)a\u0018\n\u0007\u0005\u0005\u0004IA\nCS\u001e$UmY5nC2Len\u001d;b]\u000e,7\u000f\u0006\u0002\u0002X\u000511\u000f\u001e:j]\u001e\u0004\"a\u0015\u0015\u0003\rM$(/\u001b8h'\u0011A\u0013*!\u001c\u0011\u0007\u0019\u000by'C\u0002\u0002r\u0001\u0013qb\u0015;sS:<\u0017J\\:uC:\u001cWm\u001d\u000b\u0003\u0003O\n\u0001\"\u001b;fe\u0006\u0014G.\u001a\t\u0003'.\u0012\u0001\"\u001b;fe\u0006\u0014G.Z\n\u0005W%\u000bi\bE\u0002G\u0003\u007fJ1!!!A\u0005EIE/\u001a:bE2,\u0017J\\:uC:\u001cWm\u001d\u000b\u0003\u0003o\nQ!\u0019:sCf\u0004\"a\u0015\u0018\u0003\u000b\u0005\u0014(/Y=\u0014\t9J\u0015Q\u0012\t\u0004\r\u0006=\u0015bAAI\u0001\nq\u0011I\u001d:bs&s7\u000f^1oG\u0016\u001cHCAAD\u0003\r\u0019X-\u001d\t\u0003'F\u00121a]3r'\u0011\t\u0014*!(\u0011\u0007\u0019\u000by*C\u0002\u0002\"\u0002\u0013AbU3r\u0013:\u001cH/\u00198dKN$\"!a&\u0002\u00075\f\u0007\u000f\u0005\u0002Ti\t\u0019Q.\u00199\u0014\tQJ\u0015Q\u0016\t\u0004\r\u0006=\u0016bAAY\u0001\naQ*\u00199J]N$\u0018M\\2fgR\u0011\u0011qU\u0001\u0007iV\u0004H.Z:\u0011\u0005M;$A\u0002;va2,7o\u0005\u00038\u0013\u0006u\u0006c\u0001$\u0002@&\u0019\u0011\u0011\u0019!\u0003!A\u0013x\u000eZ;di&s7\u000f^1oG\u0016\u001cHCAA\\\u0003\u0019y\u0007\u000f^5p]B\u00111K\u000f\u0002\u0007_B$\u0018n\u001c8\u0014\tiJ\u0015Q\u001a\t\u0004\r\u0006=\u0017bAAi\u0001\nyq\n\u001d;j_:Len\u001d;b]\u000e,7\u000f\u0006\u0002\u0002H\u0006!QO\\5u!\t\u0019VH\u0001\u0003v]&$8\u0003B\u001fJ\u0003;\u00042ARAp\u0013\r\t\t\u000f\u0011\u0002\u000e+:LG/\u00138ti\u0006t7-Z:\u0015\u0005\u0005]\u0007"
)
public final class package {
   public static class any$ implements AnyInstances {
      public static final any$ MODULE$ = new any$();
      private static CommutativeGroup UnitAlgebra;
      private static Monoid StringAlgebra;
      private static Order StringOrder;
      private static Field BigDecimalAlgebra;
      private static NumberTag BigDecimalTag;
      private static EuclideanRing BigIntegerAlgebra;
      private static NumberTag BigIntegerTag;
      private static EuclideanRing BigIntAlgebra;
      private static NumberTag BigIntTag;
      private static Field DoubleAlgebra;
      private static NumberTag DoubleTag;
      private static Field FloatAlgebra;
      private static NumberTag FloatTag;
      private static BitString LongBitString;
      private static EuclideanRing LongAlgebra;
      private static NumberTag LongTag;
      private static BitString IntBitString;
      private static EuclideanRing IntAlgebra;
      private static NumberTag IntTag;
      private static BitString ShortBitString;
      private static EuclideanRing ShortAlgebra;
      private static NumberTag ShortTag;
      private static BitString ByteBitString;
      private static EuclideanRing ByteAlgebra;
      private static NumberTag ByteTag;
      private static Order CharAlgebra;
      private static Bool BooleanStructure;

      static {
         BooleanInstances.$init$(MODULE$);
         CharInstances.$init$(MODULE$);
         ByteInstances.$init$(MODULE$);
         ShortInstances.$init$(MODULE$);
         IntInstances.$init$(MODULE$);
         LongInstances.$init$(MODULE$);
         FloatInstances.$init$(MODULE$);
         DoubleInstances.$init$(MODULE$);
         BigIntInstances.$init$(MODULE$);
         BigIntegerInstances.$init$(MODULE$);
         BigDecimalInstances.$init$(MODULE$);
         StringInstances0.$init$(MODULE$);
         StringInstances.$init$(MODULE$);
         IterableInstances.$init$(MODULE$);
         ArrayInstances0.$init$(MODULE$);
         ArrayInstances1.$init$(MODULE$);
         ArrayInstances2.$init$(MODULE$);
         ArrayInstances3.$init$(MODULE$);
         ArrayInstances.$init$(MODULE$);
         SeqInstances0.$init$(MODULE$);
         SeqInstances1.$init$(MODULE$);
         SeqInstances2.$init$(MODULE$);
         SeqInstances3.$init$(MODULE$);
         MapInstances0.$init$(MODULE$);
         MapInstances1.$init$(MODULE$);
         MapInstances2.$init$(MODULE$);
         MapInstances3.$init$(MODULE$);
         SemigroupProductInstances.$init$(MODULE$);
         MonoidProductInstances.$init$(MODULE$);
         GroupProductInstances.$init$(MODULE$);
         AbGroupProductInstances.$init$(MODULE$);
         SemiringProductInstances.$init$(MODULE$);
         RngProductInstances.$init$(MODULE$);
         RigProductInstances.$init$(MODULE$);
         RingProductInstances.$init$(MODULE$);
         EqProductInstances.$init$(MODULE$);
         OrderProductInstances.$init$(MODULE$);
         OptionInstances0.$init$(MODULE$);
         OptionInstances.$init$(MODULE$);
         UnitInstances.$init$(MODULE$);
      }

      public OptionCMonoid OptionCMonoid(final CommutativeSemigroup evidence$9) {
         return OptionInstances.OptionCMonoid$(this, evidence$9);
      }

      public OptionAdditiveMonoid OptionAdditiveMonoid(final AdditiveSemigroup evidence$10) {
         return OptionInstances.OptionAdditiveMonoid$(this, evidence$10);
      }

      public OptionMultiplicativeMonoid OptionMultiplicativeMonoid(final MultiplicativeSemigroup evidence$11) {
         return OptionInstances.OptionMultiplicativeMonoid$(this, evidence$11);
      }

      public OptionOrder OptionOrder(final Order evidence$12) {
         return OptionInstances.OptionOrder$(this, evidence$12);
      }

      public OptionEq OptionEq(final Eq evidence$7) {
         return OptionInstances0.OptionEq$(this, evidence$7);
      }

      public OptionMonoid OptionMonoid(final Semigroup evidence$8) {
         return OptionInstances0.OptionMonoid$(this, evidence$8);
      }

      public Order OrderProduct2(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mDDc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mDFc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mDIc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mDJc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mFDc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mFFc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mFIc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mFJc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mIDc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mIFc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mIIc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mIJc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mJDc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mJFc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mJIc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mJJc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct3(final Order _structure1, final Order _structure2, final Order _structure3) {
         return OrderProductInstances.OrderProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Order OrderProduct4(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4) {
         return OrderProductInstances.OrderProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Order OrderProduct5(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5) {
         return OrderProductInstances.OrderProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Order OrderProduct6(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6) {
         return OrderProductInstances.OrderProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Order OrderProduct7(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7) {
         return OrderProductInstances.OrderProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Order OrderProduct8(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8) {
         return OrderProductInstances.OrderProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Order OrderProduct9(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9) {
         return OrderProductInstances.OrderProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Order OrderProduct10(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10) {
         return OrderProductInstances.OrderProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Order OrderProduct11(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11) {
         return OrderProductInstances.OrderProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Order OrderProduct12(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12) {
         return OrderProductInstances.OrderProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Order OrderProduct13(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13) {
         return OrderProductInstances.OrderProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Order OrderProduct14(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14) {
         return OrderProductInstances.OrderProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Order OrderProduct15(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15) {
         return OrderProductInstances.OrderProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Order OrderProduct16(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16) {
         return OrderProductInstances.OrderProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Order OrderProduct17(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17) {
         return OrderProductInstances.OrderProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Order OrderProduct18(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18) {
         return OrderProductInstances.OrderProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Order OrderProduct19(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19) {
         return OrderProductInstances.OrderProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Order OrderProduct20(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20) {
         return OrderProductInstances.OrderProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Order OrderProduct21(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20, final Order _structure21) {
         return OrderProductInstances.OrderProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Order OrderProduct22(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20, final Order _structure21, final Order _structure22) {
         return OrderProductInstances.OrderProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Eq EqProduct2(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mDDc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mDFc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mDIc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mDJc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mFDc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mFFc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mFIc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mFJc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mIDc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mIFc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mIIc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mIJc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mJDc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mJFc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mJIc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mJJc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct3(final Eq _structure1, final Eq _structure2, final Eq _structure3) {
         return EqProductInstances.EqProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Eq EqProduct4(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4) {
         return EqProductInstances.EqProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Eq EqProduct5(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5) {
         return EqProductInstances.EqProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Eq EqProduct6(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6) {
         return EqProductInstances.EqProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Eq EqProduct7(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7) {
         return EqProductInstances.EqProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Eq EqProduct8(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8) {
         return EqProductInstances.EqProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Eq EqProduct9(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9) {
         return EqProductInstances.EqProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Eq EqProduct10(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10) {
         return EqProductInstances.EqProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Eq EqProduct11(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11) {
         return EqProductInstances.EqProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Eq EqProduct12(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12) {
         return EqProductInstances.EqProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Eq EqProduct13(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13) {
         return EqProductInstances.EqProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Eq EqProduct14(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14) {
         return EqProductInstances.EqProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Eq EqProduct15(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15) {
         return EqProductInstances.EqProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Eq EqProduct16(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16) {
         return EqProductInstances.EqProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Eq EqProduct17(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17) {
         return EqProductInstances.EqProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Eq EqProduct18(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18) {
         return EqProductInstances.EqProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Eq EqProduct19(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19) {
         return EqProductInstances.EqProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Eq EqProduct20(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20) {
         return EqProductInstances.EqProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Eq EqProduct21(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20, final Eq _structure21) {
         return EqProductInstances.EqProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Eq EqProduct22(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20, final Eq _structure21, final Eq _structure22) {
         return EqProductInstances.EqProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Ring RingProduct2(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mDDc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mDFc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mDIc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mDJc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mFDc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mFFc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mFIc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mFJc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mIDc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mIFc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mIIc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mIJc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mJDc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mJFc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mJIc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mJJc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct3(final Ring _structure1, final Ring _structure2, final Ring _structure3) {
         return RingProductInstances.RingProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Ring RingProduct4(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4) {
         return RingProductInstances.RingProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Ring RingProduct5(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5) {
         return RingProductInstances.RingProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Ring RingProduct6(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6) {
         return RingProductInstances.RingProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Ring RingProduct7(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7) {
         return RingProductInstances.RingProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Ring RingProduct8(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8) {
         return RingProductInstances.RingProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Ring RingProduct9(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9) {
         return RingProductInstances.RingProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Ring RingProduct10(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10) {
         return RingProductInstances.RingProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Ring RingProduct11(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11) {
         return RingProductInstances.RingProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Ring RingProduct12(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12) {
         return RingProductInstances.RingProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Ring RingProduct13(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13) {
         return RingProductInstances.RingProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Ring RingProduct14(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14) {
         return RingProductInstances.RingProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Ring RingProduct15(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15) {
         return RingProductInstances.RingProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Ring RingProduct16(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16) {
         return RingProductInstances.RingProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Ring RingProduct17(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17) {
         return RingProductInstances.RingProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Ring RingProduct18(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18) {
         return RingProductInstances.RingProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Ring RingProduct19(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19) {
         return RingProductInstances.RingProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Ring RingProduct20(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20) {
         return RingProductInstances.RingProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Ring RingProduct21(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20, final Ring _structure21) {
         return RingProductInstances.RingProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Ring RingProduct22(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20, final Ring _structure21, final Ring _structure22) {
         return RingProductInstances.RingProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Rig RigProduct2(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mDDc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mDFc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mDIc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mDJc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mFDc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mFFc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mFIc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mFJc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mIDc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mIFc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mIIc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mIJc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mJDc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mJFc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mJIc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mJJc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct3(final Rig _structure1, final Rig _structure2, final Rig _structure3) {
         return RigProductInstances.RigProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Rig RigProduct4(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4) {
         return RigProductInstances.RigProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Rig RigProduct5(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5) {
         return RigProductInstances.RigProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Rig RigProduct6(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6) {
         return RigProductInstances.RigProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Rig RigProduct7(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7) {
         return RigProductInstances.RigProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Rig RigProduct8(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8) {
         return RigProductInstances.RigProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Rig RigProduct9(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9) {
         return RigProductInstances.RigProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Rig RigProduct10(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10) {
         return RigProductInstances.RigProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Rig RigProduct11(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11) {
         return RigProductInstances.RigProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Rig RigProduct12(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12) {
         return RigProductInstances.RigProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Rig RigProduct13(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13) {
         return RigProductInstances.RigProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Rig RigProduct14(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14) {
         return RigProductInstances.RigProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Rig RigProduct15(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15) {
         return RigProductInstances.RigProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Rig RigProduct16(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16) {
         return RigProductInstances.RigProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Rig RigProduct17(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17) {
         return RigProductInstances.RigProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Rig RigProduct18(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18) {
         return RigProductInstances.RigProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Rig RigProduct19(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19) {
         return RigProductInstances.RigProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Rig RigProduct20(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20) {
         return RigProductInstances.RigProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Rig RigProduct21(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20, final Rig _structure21) {
         return RigProductInstances.RigProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Rig RigProduct22(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20, final Rig _structure21, final Rig _structure22) {
         return RigProductInstances.RigProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Rng RngProduct2(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mDDc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mDFc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mDIc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mDJc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mFDc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mFFc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mFIc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mFJc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mIDc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mIFc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mIIc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mIJc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mJDc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mJFc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mJIc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mJJc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct3(final Rng _structure1, final Rng _structure2, final Rng _structure3) {
         return RngProductInstances.RngProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Rng RngProduct4(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4) {
         return RngProductInstances.RngProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Rng RngProduct5(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5) {
         return RngProductInstances.RngProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Rng RngProduct6(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6) {
         return RngProductInstances.RngProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Rng RngProduct7(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7) {
         return RngProductInstances.RngProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Rng RngProduct8(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8) {
         return RngProductInstances.RngProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Rng RngProduct9(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9) {
         return RngProductInstances.RngProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Rng RngProduct10(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10) {
         return RngProductInstances.RngProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Rng RngProduct11(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11) {
         return RngProductInstances.RngProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Rng RngProduct12(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12) {
         return RngProductInstances.RngProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Rng RngProduct13(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13) {
         return RngProductInstances.RngProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Rng RngProduct14(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14) {
         return RngProductInstances.RngProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Rng RngProduct15(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15) {
         return RngProductInstances.RngProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Rng RngProduct16(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16) {
         return RngProductInstances.RngProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Rng RngProduct17(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17) {
         return RngProductInstances.RngProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Rng RngProduct18(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18) {
         return RngProductInstances.RngProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Rng RngProduct19(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19) {
         return RngProductInstances.RngProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Rng RngProduct20(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20) {
         return RngProductInstances.RngProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Rng RngProduct21(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20, final Rng _structure21) {
         return RngProductInstances.RngProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Rng RngProduct22(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20, final Rng _structure21, final Rng _structure22) {
         return RngProductInstances.RngProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Semiring SemiringProduct2(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mDDc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mDFc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mDIc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mDJc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mFDc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mFFc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mFIc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mFJc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mIDc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mIFc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mIIc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mIJc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mJDc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mJFc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mJIc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mJJc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct3(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3) {
         return SemiringProductInstances.SemiringProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Semiring SemiringProduct4(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4) {
         return SemiringProductInstances.SemiringProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Semiring SemiringProduct5(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5) {
         return SemiringProductInstances.SemiringProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Semiring SemiringProduct6(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6) {
         return SemiringProductInstances.SemiringProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Semiring SemiringProduct7(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7) {
         return SemiringProductInstances.SemiringProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Semiring SemiringProduct8(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8) {
         return SemiringProductInstances.SemiringProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Semiring SemiringProduct9(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9) {
         return SemiringProductInstances.SemiringProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Semiring SemiringProduct10(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10) {
         return SemiringProductInstances.SemiringProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Semiring SemiringProduct11(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11) {
         return SemiringProductInstances.SemiringProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Semiring SemiringProduct12(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12) {
         return SemiringProductInstances.SemiringProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Semiring SemiringProduct13(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13) {
         return SemiringProductInstances.SemiringProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Semiring SemiringProduct14(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14) {
         return SemiringProductInstances.SemiringProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Semiring SemiringProduct15(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15) {
         return SemiringProductInstances.SemiringProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Semiring SemiringProduct16(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16) {
         return SemiringProductInstances.SemiringProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Semiring SemiringProduct17(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17) {
         return SemiringProductInstances.SemiringProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Semiring SemiringProduct18(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18) {
         return SemiringProductInstances.SemiringProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Semiring SemiringProduct19(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19) {
         return SemiringProductInstances.SemiringProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Semiring SemiringProduct20(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20) {
         return SemiringProductInstances.SemiringProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Semiring SemiringProduct21(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20, final Semiring _structure21) {
         return SemiringProductInstances.SemiringProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Semiring SemiringProduct22(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20, final Semiring _structure21, final Semiring _structure22) {
         return SemiringProductInstances.SemiringProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public CommutativeGroup AbGroupProduct2(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mDDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mDFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mDIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mDJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mFDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mFFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mFIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mFJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mIDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mIFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mIIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mIJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mJDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mJFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mJIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mJJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct3(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3) {
         return AbGroupProductInstances.AbGroupProduct3$(this, _structure1, _structure2, _structure3);
      }

      public CommutativeGroup AbGroupProduct4(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4) {
         return AbGroupProductInstances.AbGroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public CommutativeGroup AbGroupProduct5(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5) {
         return AbGroupProductInstances.AbGroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public CommutativeGroup AbGroupProduct6(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6) {
         return AbGroupProductInstances.AbGroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public CommutativeGroup AbGroupProduct7(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7) {
         return AbGroupProductInstances.AbGroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public CommutativeGroup AbGroupProduct8(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8) {
         return AbGroupProductInstances.AbGroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public CommutativeGroup AbGroupProduct9(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9) {
         return AbGroupProductInstances.AbGroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public CommutativeGroup AbGroupProduct10(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10) {
         return AbGroupProductInstances.AbGroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public CommutativeGroup AbGroupProduct11(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11) {
         return AbGroupProductInstances.AbGroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public CommutativeGroup AbGroupProduct12(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12) {
         return AbGroupProductInstances.AbGroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public CommutativeGroup AbGroupProduct13(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13) {
         return AbGroupProductInstances.AbGroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public CommutativeGroup AbGroupProduct14(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14) {
         return AbGroupProductInstances.AbGroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public CommutativeGroup AbGroupProduct15(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15) {
         return AbGroupProductInstances.AbGroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public CommutativeGroup AbGroupProduct16(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16) {
         return AbGroupProductInstances.AbGroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public CommutativeGroup AbGroupProduct17(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17) {
         return AbGroupProductInstances.AbGroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public CommutativeGroup AbGroupProduct18(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18) {
         return AbGroupProductInstances.AbGroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public CommutativeGroup AbGroupProduct19(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19) {
         return AbGroupProductInstances.AbGroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public CommutativeGroup AbGroupProduct20(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20) {
         return AbGroupProductInstances.AbGroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public CommutativeGroup AbGroupProduct21(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20, final CommutativeGroup _structure21) {
         return AbGroupProductInstances.AbGroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public CommutativeGroup AbGroupProduct22(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20, final CommutativeGroup _structure21, final CommutativeGroup _structure22) {
         return AbGroupProductInstances.AbGroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Group GroupProduct2(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mDDc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mDFc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mDIc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mDJc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mFDc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mFFc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mFIc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mFJc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mIDc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mIFc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mIIc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mIJc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mJDc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mJFc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mJIc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mJJc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct3(final Group _structure1, final Group _structure2, final Group _structure3) {
         return GroupProductInstances.GroupProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Group GroupProduct4(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4) {
         return GroupProductInstances.GroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Group GroupProduct5(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5) {
         return GroupProductInstances.GroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Group GroupProduct6(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6) {
         return GroupProductInstances.GroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Group GroupProduct7(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7) {
         return GroupProductInstances.GroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Group GroupProduct8(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8) {
         return GroupProductInstances.GroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Group GroupProduct9(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9) {
         return GroupProductInstances.GroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Group GroupProduct10(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10) {
         return GroupProductInstances.GroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Group GroupProduct11(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11) {
         return GroupProductInstances.GroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Group GroupProduct12(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12) {
         return GroupProductInstances.GroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Group GroupProduct13(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13) {
         return GroupProductInstances.GroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Group GroupProduct14(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14) {
         return GroupProductInstances.GroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Group GroupProduct15(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15) {
         return GroupProductInstances.GroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Group GroupProduct16(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16) {
         return GroupProductInstances.GroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Group GroupProduct17(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17) {
         return GroupProductInstances.GroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Group GroupProduct18(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18) {
         return GroupProductInstances.GroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Group GroupProduct19(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19) {
         return GroupProductInstances.GroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Group GroupProduct20(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20) {
         return GroupProductInstances.GroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Group GroupProduct21(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20, final Group _structure21) {
         return GroupProductInstances.GroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Group GroupProduct22(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20, final Group _structure21, final Group _structure22) {
         return GroupProductInstances.GroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Monoid MonoidProduct2(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mDDc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mDFc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mDIc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mDJc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mFDc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mFFc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mFIc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mFJc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mIDc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mIFc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mIIc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mIJc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mJDc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mJFc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mJIc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mJJc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct3(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3) {
         return MonoidProductInstances.MonoidProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Monoid MonoidProduct4(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4) {
         return MonoidProductInstances.MonoidProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Monoid MonoidProduct5(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5) {
         return MonoidProductInstances.MonoidProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Monoid MonoidProduct6(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6) {
         return MonoidProductInstances.MonoidProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Monoid MonoidProduct7(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7) {
         return MonoidProductInstances.MonoidProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Monoid MonoidProduct8(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8) {
         return MonoidProductInstances.MonoidProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Monoid MonoidProduct9(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9) {
         return MonoidProductInstances.MonoidProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Monoid MonoidProduct10(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10) {
         return MonoidProductInstances.MonoidProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Monoid MonoidProduct11(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11) {
         return MonoidProductInstances.MonoidProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Monoid MonoidProduct12(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12) {
         return MonoidProductInstances.MonoidProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Monoid MonoidProduct13(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13) {
         return MonoidProductInstances.MonoidProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Monoid MonoidProduct14(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14) {
         return MonoidProductInstances.MonoidProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Monoid MonoidProduct15(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15) {
         return MonoidProductInstances.MonoidProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Monoid MonoidProduct16(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16) {
         return MonoidProductInstances.MonoidProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Monoid MonoidProduct17(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17) {
         return MonoidProductInstances.MonoidProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Monoid MonoidProduct18(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18) {
         return MonoidProductInstances.MonoidProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Monoid MonoidProduct19(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19) {
         return MonoidProductInstances.MonoidProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Monoid MonoidProduct20(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20) {
         return MonoidProductInstances.MonoidProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Monoid MonoidProduct21(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20, final Monoid _structure21) {
         return MonoidProductInstances.MonoidProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Monoid MonoidProduct22(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20, final Monoid _structure21, final Monoid _structure22) {
         return MonoidProductInstances.MonoidProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Semigroup SemigroupProduct2(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mDDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mDFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mDIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mDJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mFDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mFFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mFIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mFJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mIDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mIFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mIIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mIJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mJDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mJFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mJIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mJJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct3(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3) {
         return SemigroupProductInstances.SemigroupProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Semigroup SemigroupProduct4(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4) {
         return SemigroupProductInstances.SemigroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Semigroup SemigroupProduct5(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5) {
         return SemigroupProductInstances.SemigroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Semigroup SemigroupProduct6(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6) {
         return SemigroupProductInstances.SemigroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Semigroup SemigroupProduct7(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7) {
         return SemigroupProductInstances.SemigroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Semigroup SemigroupProduct8(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8) {
         return SemigroupProductInstances.SemigroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Semigroup SemigroupProduct9(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9) {
         return SemigroupProductInstances.SemigroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Semigroup SemigroupProduct10(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10) {
         return SemigroupProductInstances.SemigroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Semigroup SemigroupProduct11(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11) {
         return SemigroupProductInstances.SemigroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Semigroup SemigroupProduct12(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12) {
         return SemigroupProductInstances.SemigroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Semigroup SemigroupProduct13(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13) {
         return SemigroupProductInstances.SemigroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Semigroup SemigroupProduct14(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14) {
         return SemigroupProductInstances.SemigroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Semigroup SemigroupProduct15(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15) {
         return SemigroupProductInstances.SemigroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Semigroup SemigroupProduct16(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16) {
         return SemigroupProductInstances.SemigroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Semigroup SemigroupProduct17(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17) {
         return SemigroupProductInstances.SemigroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Semigroup SemigroupProduct18(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18) {
         return SemigroupProductInstances.SemigroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Semigroup SemigroupProduct19(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19) {
         return SemigroupProductInstances.SemigroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Semigroup SemigroupProduct20(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20) {
         return SemigroupProductInstances.SemigroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Semigroup SemigroupProduct21(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20, final Semigroup _structure21) {
         return SemigroupProductInstances.SemigroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Semigroup SemigroupProduct22(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20, final Semigroup _structure21, final Semigroup _structure22) {
         return SemigroupProductInstances.SemigroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public MapInnerProductSpace MapInnerProductSpace(final Field evidence$7) {
         return MapInstances3.MapInnerProductSpace$(this, evidence$7);
      }

      public MapEq MapEq(final Eq V0) {
         return MapInstances3.MapEq$(this, V0);
      }

      public MapGroup MapGroup(final Group evidence$5) {
         return MapInstances2.MapGroup$(this, evidence$5);
      }

      public MapVectorSpace MapVectorSpace(final Field evidence$6) {
         return MapInstances2.MapVectorSpace$(this, evidence$6);
      }

      public MapCRng MapCRng(final CommutativeRing evidence$4) {
         return MapInstances1.MapCRng$(this, evidence$4);
      }

      public MapMonoid MapMonoid(final Semigroup evidence$2) {
         return MapInstances0.MapMonoid$(this, evidence$2);
      }

      public MapCSemiring MapCSemiring(final CommutativeSemiring evidence$3) {
         return MapInstances0.MapCSemiring$(this, evidence$3);
      }

      public NormedVectorSpace SeqNormedVectorSpace(final Field field0, final NRoot nroot0, final Factory cbf0) {
         return SeqInstances3.SeqNormedVectorSpace$(this, field0, nroot0, cbf0);
      }

      public SeqInnerProductSpace SeqInnerProductSpace(final Field field0, final Factory cbf0) {
         return SeqInstances2.SeqInnerProductSpace$(this, field0, cbf0);
      }

      public SeqOrder SeqOrder(final Order A0) {
         return SeqInstances2.SeqOrder$(this, A0);
      }

      public SeqVectorSpace SeqVectorSpace(final Field field0, final Factory cbf0, final NotGiven ev) {
         return SeqInstances1.SeqVectorSpace$(this, field0, cbf0, ev);
      }

      public SeqEq SeqEq(final Eq A0) {
         return SeqInstances1.SeqEq$(this, A0);
      }

      public SeqCModule SeqCModule(final CommutativeRing ring0, final Factory cbf0, final NotGiven ev) {
         return SeqInstances0.SeqCModule$(this, ring0, cbf0, ev);
      }

      public Monoid ArrayMonoid(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mZc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mZc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mBc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mBc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mCc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mCc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mDc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mDc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mFc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mFc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mIc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mIc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mJc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mJc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mSc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mSc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mVc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mVc$sp$(this, evidence$25);
      }

      public NormedVectorSpace ArrayNormedVectorSpace(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
         return ArrayInstances3.ArrayNormedVectorSpace$(this, evidence$22, evidence$23, evidence$24);
      }

      public NormedVectorSpace ArrayNormedVectorSpace$mDc$sp(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
         return ArrayInstances3.ArrayNormedVectorSpace$mDc$sp$(this, evidence$22, evidence$23, evidence$24);
      }

      public NormedVectorSpace ArrayNormedVectorSpace$mFc$sp(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
         return ArrayInstances3.ArrayNormedVectorSpace$mFc$sp$(this, evidence$22, evidence$23, evidence$24);
      }

      public InnerProductSpace ArrayInnerProductSpace(final Field evidence$19, final ClassTag evidence$20) {
         return ArrayInstances2.ArrayInnerProductSpace$(this, evidence$19, evidence$20);
      }

      public InnerProductSpace ArrayInnerProductSpace$mDc$sp(final Field evidence$19, final ClassTag evidence$20) {
         return ArrayInstances2.ArrayInnerProductSpace$mDc$sp$(this, evidence$19, evidence$20);
      }

      public InnerProductSpace ArrayInnerProductSpace$mFc$sp(final Field evidence$19, final ClassTag evidence$20) {
         return ArrayInstances2.ArrayInnerProductSpace$mFc$sp$(this, evidence$19, evidence$20);
      }

      public Order ArrayOrder(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$(this, evidence$21);
      }

      public Order ArrayOrder$mZc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mZc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mBc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mBc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mCc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mCc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mDc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mDc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mFc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mFc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mIc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mIc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mJc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mJc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mSc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mSc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mVc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mVc$sp$(this, evidence$21);
      }

      public VectorSpace ArrayVectorSpace(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$(this, evidence$15, evidence$16, evidence$17);
      }

      public VectorSpace ArrayVectorSpace$mDc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$mDc$sp$(this, evidence$15, evidence$16, evidence$17);
      }

      public VectorSpace ArrayVectorSpace$mFc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$mFc$sp$(this, evidence$15, evidence$16, evidence$17);
      }

      public VectorSpace ArrayVectorSpace$mIc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$mIc$sp$(this, evidence$15, evidence$16, evidence$17);
      }

      public VectorSpace ArrayVectorSpace$mJc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$mJc$sp$(this, evidence$15, evidence$16, evidence$17);
      }

      public Eq ArrayEq(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$(this, evidence$18);
      }

      public Eq ArrayEq$mZc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mZc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mBc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mBc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mCc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mCc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mDc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mDc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mFc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mFc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mIc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mIc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mJc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mJc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mSc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mSc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mVc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mVc$sp$(this, evidence$18);
      }

      public CModule ArrayCModule(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$(this, evidence$12, evidence$13, evidence$14);
      }

      public CModule ArrayCModule$mDc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$mDc$sp$(this, evidence$12, evidence$13, evidence$14);
      }

      public CModule ArrayCModule$mFc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$mFc$sp$(this, evidence$12, evidence$13, evidence$14);
      }

      public CModule ArrayCModule$mIc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$mIc$sp$(this, evidence$12, evidence$13, evidence$14);
      }

      public CModule ArrayCModule$mJc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$mJc$sp$(this, evidence$12, evidence$13, evidence$14);
      }

      public Monoid IterableMonoid(final Factory cbf) {
         return IterableInstances.IterableMonoid$(this, cbf);
      }

      public MetricSpace levenshteinDistance() {
         return StringInstances0.levenshteinDistance$(this);
      }

      public BigDecimalIsTrig BigDecimalIsTrig(final MathContext mc) {
         return BigDecimalInstances.BigDecimalIsTrig$(this, mc);
      }

      public MathContext BigDecimalIsTrig$default$1() {
         return BigDecimalInstances.BigDecimalIsTrig$default$1$(this);
      }

      public final CommutativeGroup UnitAlgebra() {
         return UnitAlgebra;
      }

      public final void spire$std$UnitInstances$_setter_$UnitAlgebra_$eq(final CommutativeGroup x$1) {
         UnitAlgebra = x$1;
      }

      public final Monoid StringAlgebra() {
         return StringAlgebra;
      }

      public final Order StringOrder() {
         return StringOrder;
      }

      public final void spire$std$StringInstances$_setter_$StringAlgebra_$eq(final Monoid x$1) {
         StringAlgebra = x$1;
      }

      public final void spire$std$StringInstances$_setter_$StringOrder_$eq(final Order x$1) {
         StringOrder = x$1;
      }

      public final Field BigDecimalAlgebra() {
         return BigDecimalAlgebra;
      }

      public final NumberTag BigDecimalTag() {
         return BigDecimalTag;
      }

      public final void spire$std$BigDecimalInstances$_setter_$BigDecimalAlgebra_$eq(final Field x$1) {
         BigDecimalAlgebra = x$1;
      }

      public final void spire$std$BigDecimalInstances$_setter_$BigDecimalTag_$eq(final NumberTag x$1) {
         BigDecimalTag = x$1;
      }

      public final EuclideanRing BigIntegerAlgebra() {
         return BigIntegerAlgebra;
      }

      public final NumberTag BigIntegerTag() {
         return BigIntegerTag;
      }

      public final void spire$std$BigIntegerInstances$_setter_$BigIntegerAlgebra_$eq(final EuclideanRing x$1) {
         BigIntegerAlgebra = x$1;
      }

      public final void spire$std$BigIntegerInstances$_setter_$BigIntegerTag_$eq(final NumberTag x$1) {
         BigIntegerTag = x$1;
      }

      public final EuclideanRing BigIntAlgebra() {
         return BigIntAlgebra;
      }

      public final NumberTag BigIntTag() {
         return BigIntTag;
      }

      public final void spire$std$BigIntInstances$_setter_$BigIntAlgebra_$eq(final EuclideanRing x$1) {
         BigIntAlgebra = x$1;
      }

      public final void spire$std$BigIntInstances$_setter_$BigIntTag_$eq(final NumberTag x$1) {
         BigIntTag = x$1;
      }

      public final Field DoubleAlgebra() {
         return DoubleAlgebra;
      }

      public final NumberTag DoubleTag() {
         return DoubleTag;
      }

      public final void spire$std$DoubleInstances$_setter_$DoubleAlgebra_$eq(final Field x$1) {
         DoubleAlgebra = x$1;
      }

      public final void spire$std$DoubleInstances$_setter_$DoubleTag_$eq(final NumberTag x$1) {
         DoubleTag = x$1;
      }

      public final Field FloatAlgebra() {
         return FloatAlgebra;
      }

      public final NumberTag FloatTag() {
         return FloatTag;
      }

      public final void spire$std$FloatInstances$_setter_$FloatAlgebra_$eq(final Field x$1) {
         FloatAlgebra = x$1;
      }

      public final void spire$std$FloatInstances$_setter_$FloatTag_$eq(final NumberTag x$1) {
         FloatTag = x$1;
      }

      public final BitString LongBitString() {
         return LongBitString;
      }

      public final EuclideanRing LongAlgebra() {
         return LongAlgebra;
      }

      public final NumberTag LongTag() {
         return LongTag;
      }

      public final void spire$std$LongInstances$_setter_$LongBitString_$eq(final BitString x$1) {
         LongBitString = x$1;
      }

      public final void spire$std$LongInstances$_setter_$LongAlgebra_$eq(final EuclideanRing x$1) {
         LongAlgebra = x$1;
      }

      public final void spire$std$LongInstances$_setter_$LongTag_$eq(final NumberTag x$1) {
         LongTag = x$1;
      }

      public final BitString IntBitString() {
         return IntBitString;
      }

      public final EuclideanRing IntAlgebra() {
         return IntAlgebra;
      }

      public final NumberTag IntTag() {
         return IntTag;
      }

      public final void spire$std$IntInstances$_setter_$IntBitString_$eq(final BitString x$1) {
         IntBitString = x$1;
      }

      public final void spire$std$IntInstances$_setter_$IntAlgebra_$eq(final EuclideanRing x$1) {
         IntAlgebra = x$1;
      }

      public final void spire$std$IntInstances$_setter_$IntTag_$eq(final NumberTag x$1) {
         IntTag = x$1;
      }

      public final BitString ShortBitString() {
         return ShortBitString;
      }

      public final EuclideanRing ShortAlgebra() {
         return ShortAlgebra;
      }

      public final NumberTag ShortTag() {
         return ShortTag;
      }

      public final void spire$std$ShortInstances$_setter_$ShortBitString_$eq(final BitString x$1) {
         ShortBitString = x$1;
      }

      public final void spire$std$ShortInstances$_setter_$ShortAlgebra_$eq(final EuclideanRing x$1) {
         ShortAlgebra = x$1;
      }

      public final void spire$std$ShortInstances$_setter_$ShortTag_$eq(final NumberTag x$1) {
         ShortTag = x$1;
      }

      public final BitString ByteBitString() {
         return ByteBitString;
      }

      public final EuclideanRing ByteAlgebra() {
         return ByteAlgebra;
      }

      public final NumberTag ByteTag() {
         return ByteTag;
      }

      public final void spire$std$ByteInstances$_setter_$ByteBitString_$eq(final BitString x$1) {
         ByteBitString = x$1;
      }

      public final void spire$std$ByteInstances$_setter_$ByteAlgebra_$eq(final EuclideanRing x$1) {
         ByteAlgebra = x$1;
      }

      public final void spire$std$ByteInstances$_setter_$ByteTag_$eq(final NumberTag x$1) {
         ByteTag = x$1;
      }

      public final Order CharAlgebra() {
         return CharAlgebra;
      }

      public final void spire$std$CharInstances$_setter_$CharAlgebra_$eq(final Order x$1) {
         CharAlgebra = x$1;
      }

      public final Bool BooleanStructure() {
         return BooleanStructure;
      }

      public final void spire$std$BooleanInstances$_setter_$BooleanStructure_$eq(final Bool x$1) {
         BooleanStructure = x$1;
      }
   }

   public static class boolean$ implements BooleanInstances {
      public static final boolean$ MODULE$ = new boolean$();
      private static Bool BooleanStructure;

      static {
         BooleanInstances.$init$(MODULE$);
      }

      public final Bool BooleanStructure() {
         return BooleanStructure;
      }

      public final void spire$std$BooleanInstances$_setter_$BooleanStructure_$eq(final Bool x$1) {
         BooleanStructure = x$1;
      }
   }

   public static class char$ implements CharInstances {
      public static final char$ MODULE$ = new char$();
      private static Order CharAlgebra;

      static {
         CharInstances.$init$(MODULE$);
      }

      public final Order CharAlgebra() {
         return CharAlgebra;
      }

      public final void spire$std$CharInstances$_setter_$CharAlgebra_$eq(final Order x$1) {
         CharAlgebra = x$1;
      }
   }

   public static class byte$ implements ByteInstances {
      public static final byte$ MODULE$ = new byte$();
      private static BitString ByteBitString;
      private static EuclideanRing ByteAlgebra;
      private static NumberTag ByteTag;

      static {
         ByteInstances.$init$(MODULE$);
      }

      public final BitString ByteBitString() {
         return ByteBitString;
      }

      public final EuclideanRing ByteAlgebra() {
         return ByteAlgebra;
      }

      public final NumberTag ByteTag() {
         return ByteTag;
      }

      public final void spire$std$ByteInstances$_setter_$ByteBitString_$eq(final BitString x$1) {
         ByteBitString = x$1;
      }

      public final void spire$std$ByteInstances$_setter_$ByteAlgebra_$eq(final EuclideanRing x$1) {
         ByteAlgebra = x$1;
      }

      public final void spire$std$ByteInstances$_setter_$ByteTag_$eq(final NumberTag x$1) {
         ByteTag = x$1;
      }
   }

   public static class short$ implements ShortInstances {
      public static final short$ MODULE$ = new short$();
      private static BitString ShortBitString;
      private static EuclideanRing ShortAlgebra;
      private static NumberTag ShortTag;

      static {
         ShortInstances.$init$(MODULE$);
      }

      public final BitString ShortBitString() {
         return ShortBitString;
      }

      public final EuclideanRing ShortAlgebra() {
         return ShortAlgebra;
      }

      public final NumberTag ShortTag() {
         return ShortTag;
      }

      public final void spire$std$ShortInstances$_setter_$ShortBitString_$eq(final BitString x$1) {
         ShortBitString = x$1;
      }

      public final void spire$std$ShortInstances$_setter_$ShortAlgebra_$eq(final EuclideanRing x$1) {
         ShortAlgebra = x$1;
      }

      public final void spire$std$ShortInstances$_setter_$ShortTag_$eq(final NumberTag x$1) {
         ShortTag = x$1;
      }
   }

   public static class int$ implements IntInstances {
      public static final int$ MODULE$ = new int$();
      private static BitString IntBitString;
      private static EuclideanRing IntAlgebra;
      private static NumberTag IntTag;

      static {
         IntInstances.$init$(MODULE$);
      }

      public final BitString IntBitString() {
         return IntBitString;
      }

      public final EuclideanRing IntAlgebra() {
         return IntAlgebra;
      }

      public final NumberTag IntTag() {
         return IntTag;
      }

      public final void spire$std$IntInstances$_setter_$IntBitString_$eq(final BitString x$1) {
         IntBitString = x$1;
      }

      public final void spire$std$IntInstances$_setter_$IntAlgebra_$eq(final EuclideanRing x$1) {
         IntAlgebra = x$1;
      }

      public final void spire$std$IntInstances$_setter_$IntTag_$eq(final NumberTag x$1) {
         IntTag = x$1;
      }
   }

   public static class long$ implements LongInstances {
      public static final long$ MODULE$ = new long$();
      private static BitString LongBitString;
      private static EuclideanRing LongAlgebra;
      private static NumberTag LongTag;

      static {
         LongInstances.$init$(MODULE$);
      }

      public final BitString LongBitString() {
         return LongBitString;
      }

      public final EuclideanRing LongAlgebra() {
         return LongAlgebra;
      }

      public final NumberTag LongTag() {
         return LongTag;
      }

      public final void spire$std$LongInstances$_setter_$LongBitString_$eq(final BitString x$1) {
         LongBitString = x$1;
      }

      public final void spire$std$LongInstances$_setter_$LongAlgebra_$eq(final EuclideanRing x$1) {
         LongAlgebra = x$1;
      }

      public final void spire$std$LongInstances$_setter_$LongTag_$eq(final NumberTag x$1) {
         LongTag = x$1;
      }
   }

   public static class float$ implements FloatInstances {
      public static final float$ MODULE$ = new float$();
      private static Field FloatAlgebra;
      private static NumberTag FloatTag;

      static {
         FloatInstances.$init$(MODULE$);
      }

      public final Field FloatAlgebra() {
         return FloatAlgebra;
      }

      public final NumberTag FloatTag() {
         return FloatTag;
      }

      public final void spire$std$FloatInstances$_setter_$FloatAlgebra_$eq(final Field x$1) {
         FloatAlgebra = x$1;
      }

      public final void spire$std$FloatInstances$_setter_$FloatTag_$eq(final NumberTag x$1) {
         FloatTag = x$1;
      }
   }

   public static class double$ implements DoubleInstances {
      public static final double$ MODULE$ = new double$();
      private static Field DoubleAlgebra;
      private static NumberTag DoubleTag;

      static {
         DoubleInstances.$init$(MODULE$);
      }

      public final Field DoubleAlgebra() {
         return DoubleAlgebra;
      }

      public final NumberTag DoubleTag() {
         return DoubleTag;
      }

      public final void spire$std$DoubleInstances$_setter_$DoubleAlgebra_$eq(final Field x$1) {
         DoubleAlgebra = x$1;
      }

      public final void spire$std$DoubleInstances$_setter_$DoubleTag_$eq(final NumberTag x$1) {
         DoubleTag = x$1;
      }
   }

   public static class bigInt$ implements BigIntInstances {
      public static final bigInt$ MODULE$ = new bigInt$();
      private static EuclideanRing BigIntAlgebra;
      private static NumberTag BigIntTag;

      static {
         BigIntInstances.$init$(MODULE$);
      }

      public final EuclideanRing BigIntAlgebra() {
         return BigIntAlgebra;
      }

      public final NumberTag BigIntTag() {
         return BigIntTag;
      }

      public final void spire$std$BigIntInstances$_setter_$BigIntAlgebra_$eq(final EuclideanRing x$1) {
         BigIntAlgebra = x$1;
      }

      public final void spire$std$BigIntInstances$_setter_$BigIntTag_$eq(final NumberTag x$1) {
         BigIntTag = x$1;
      }
   }

   public static class bigInteger$ implements BigIntegerInstances {
      public static final bigInteger$ MODULE$ = new bigInteger$();
      private static EuclideanRing BigIntegerAlgebra;
      private static NumberTag BigIntegerTag;

      static {
         BigIntegerInstances.$init$(MODULE$);
      }

      public final EuclideanRing BigIntegerAlgebra() {
         return BigIntegerAlgebra;
      }

      public final NumberTag BigIntegerTag() {
         return BigIntegerTag;
      }

      public final void spire$std$BigIntegerInstances$_setter_$BigIntegerAlgebra_$eq(final EuclideanRing x$1) {
         BigIntegerAlgebra = x$1;
      }

      public final void spire$std$BigIntegerInstances$_setter_$BigIntegerTag_$eq(final NumberTag x$1) {
         BigIntegerTag = x$1;
      }
   }

   public static class bigDecimal$ implements BigDecimalInstances {
      public static final bigDecimal$ MODULE$ = new bigDecimal$();
      private static Field BigDecimalAlgebra;
      private static NumberTag BigDecimalTag;

      static {
         BigDecimalInstances.$init$(MODULE$);
      }

      public BigDecimalIsTrig BigDecimalIsTrig(final MathContext mc) {
         return BigDecimalInstances.BigDecimalIsTrig$(this, mc);
      }

      public MathContext BigDecimalIsTrig$default$1() {
         return BigDecimalInstances.BigDecimalIsTrig$default$1$(this);
      }

      public final Field BigDecimalAlgebra() {
         return BigDecimalAlgebra;
      }

      public final NumberTag BigDecimalTag() {
         return BigDecimalTag;
      }

      public final void spire$std$BigDecimalInstances$_setter_$BigDecimalAlgebra_$eq(final Field x$1) {
         BigDecimalAlgebra = x$1;
      }

      public final void spire$std$BigDecimalInstances$_setter_$BigDecimalTag_$eq(final NumberTag x$1) {
         BigDecimalTag = x$1;
      }
   }

   public static class string$ implements StringInstances {
      public static final string$ MODULE$ = new string$();
      private static Monoid StringAlgebra;
      private static Order StringOrder;

      static {
         StringInstances0.$init$(MODULE$);
         StringInstances.$init$(MODULE$);
      }

      public MetricSpace levenshteinDistance() {
         return StringInstances0.levenshteinDistance$(this);
      }

      public final Monoid StringAlgebra() {
         return StringAlgebra;
      }

      public final Order StringOrder() {
         return StringOrder;
      }

      public final void spire$std$StringInstances$_setter_$StringAlgebra_$eq(final Monoid x$1) {
         StringAlgebra = x$1;
      }

      public final void spire$std$StringInstances$_setter_$StringOrder_$eq(final Order x$1) {
         StringOrder = x$1;
      }
   }

   public static class iterable$ implements IterableInstances {
      public static final iterable$ MODULE$ = new iterable$();

      static {
         IterableInstances.$init$(MODULE$);
      }

      public Monoid IterableMonoid(final Factory cbf) {
         return IterableInstances.IterableMonoid$(this, cbf);
      }
   }

   public static class array$ implements ArrayInstances {
      public static final array$ MODULE$ = new array$();

      static {
         ArrayInstances0.$init$(MODULE$);
         ArrayInstances1.$init$(MODULE$);
         ArrayInstances2.$init$(MODULE$);
         ArrayInstances3.$init$(MODULE$);
         ArrayInstances.$init$(MODULE$);
      }

      public Monoid ArrayMonoid(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mZc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mZc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mBc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mBc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mCc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mCc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mDc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mDc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mFc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mFc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mIc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mIc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mJc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mJc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mSc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mSc$sp$(this, evidence$25);
      }

      public Monoid ArrayMonoid$mVc$sp(final ClassTag evidence$25) {
         return ArrayInstances.ArrayMonoid$mVc$sp$(this, evidence$25);
      }

      public NormedVectorSpace ArrayNormedVectorSpace(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
         return ArrayInstances3.ArrayNormedVectorSpace$(this, evidence$22, evidence$23, evidence$24);
      }

      public NormedVectorSpace ArrayNormedVectorSpace$mDc$sp(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
         return ArrayInstances3.ArrayNormedVectorSpace$mDc$sp$(this, evidence$22, evidence$23, evidence$24);
      }

      public NormedVectorSpace ArrayNormedVectorSpace$mFc$sp(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
         return ArrayInstances3.ArrayNormedVectorSpace$mFc$sp$(this, evidence$22, evidence$23, evidence$24);
      }

      public InnerProductSpace ArrayInnerProductSpace(final Field evidence$19, final ClassTag evidence$20) {
         return ArrayInstances2.ArrayInnerProductSpace$(this, evidence$19, evidence$20);
      }

      public InnerProductSpace ArrayInnerProductSpace$mDc$sp(final Field evidence$19, final ClassTag evidence$20) {
         return ArrayInstances2.ArrayInnerProductSpace$mDc$sp$(this, evidence$19, evidence$20);
      }

      public InnerProductSpace ArrayInnerProductSpace$mFc$sp(final Field evidence$19, final ClassTag evidence$20) {
         return ArrayInstances2.ArrayInnerProductSpace$mFc$sp$(this, evidence$19, evidence$20);
      }

      public Order ArrayOrder(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$(this, evidence$21);
      }

      public Order ArrayOrder$mZc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mZc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mBc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mBc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mCc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mCc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mDc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mDc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mFc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mFc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mIc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mIc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mJc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mJc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mSc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mSc$sp$(this, evidence$21);
      }

      public Order ArrayOrder$mVc$sp(final Order evidence$21) {
         return ArrayInstances2.ArrayOrder$mVc$sp$(this, evidence$21);
      }

      public VectorSpace ArrayVectorSpace(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$(this, evidence$15, evidence$16, evidence$17);
      }

      public VectorSpace ArrayVectorSpace$mDc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$mDc$sp$(this, evidence$15, evidence$16, evidence$17);
      }

      public VectorSpace ArrayVectorSpace$mFc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$mFc$sp$(this, evidence$15, evidence$16, evidence$17);
      }

      public VectorSpace ArrayVectorSpace$mIc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$mIc$sp$(this, evidence$15, evidence$16, evidence$17);
      }

      public VectorSpace ArrayVectorSpace$mJc$sp(final NotGiven evidence$15, final ClassTag evidence$16, final Field evidence$17) {
         return ArrayInstances1.ArrayVectorSpace$mJc$sp$(this, evidence$15, evidence$16, evidence$17);
      }

      public Eq ArrayEq(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$(this, evidence$18);
      }

      public Eq ArrayEq$mZc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mZc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mBc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mBc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mCc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mCc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mDc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mDc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mFc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mFc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mIc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mIc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mJc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mJc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mSc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mSc$sp$(this, evidence$18);
      }

      public Eq ArrayEq$mVc$sp(final Eq evidence$18) {
         return ArrayInstances1.ArrayEq$mVc$sp$(this, evidence$18);
      }

      public CModule ArrayCModule(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$(this, evidence$12, evidence$13, evidence$14);
      }

      public CModule ArrayCModule$mDc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$mDc$sp$(this, evidence$12, evidence$13, evidence$14);
      }

      public CModule ArrayCModule$mFc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$mFc$sp$(this, evidence$12, evidence$13, evidence$14);
      }

      public CModule ArrayCModule$mIc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$mIc$sp$(this, evidence$12, evidence$13, evidence$14);
      }

      public CModule ArrayCModule$mJc$sp(final NotGiven evidence$12, final ClassTag evidence$13, final CommutativeRing evidence$14) {
         return ArrayInstances0.ArrayCModule$mJc$sp$(this, evidence$12, evidence$13, evidence$14);
      }
   }

   public static class seq$ implements SeqInstances {
      public static final seq$ MODULE$ = new seq$();

      static {
         SeqInstances0.$init$(MODULE$);
         SeqInstances1.$init$(MODULE$);
         SeqInstances2.$init$(MODULE$);
         SeqInstances3.$init$(MODULE$);
      }

      public NormedVectorSpace SeqNormedVectorSpace(final Field field0, final NRoot nroot0, final Factory cbf0) {
         return SeqInstances3.SeqNormedVectorSpace$(this, field0, nroot0, cbf0);
      }

      public SeqInnerProductSpace SeqInnerProductSpace(final Field field0, final Factory cbf0) {
         return SeqInstances2.SeqInnerProductSpace$(this, field0, cbf0);
      }

      public SeqOrder SeqOrder(final Order A0) {
         return SeqInstances2.SeqOrder$(this, A0);
      }

      public SeqVectorSpace SeqVectorSpace(final Field field0, final Factory cbf0, final NotGiven ev) {
         return SeqInstances1.SeqVectorSpace$(this, field0, cbf0, ev);
      }

      public SeqEq SeqEq(final Eq A0) {
         return SeqInstances1.SeqEq$(this, A0);
      }

      public SeqCModule SeqCModule(final CommutativeRing ring0, final Factory cbf0, final NotGiven ev) {
         return SeqInstances0.SeqCModule$(this, ring0, cbf0, ev);
      }
   }

   public static class map$ implements MapInstances {
      public static final map$ MODULE$ = new map$();

      static {
         MapInstances0.$init$(MODULE$);
         MapInstances1.$init$(MODULE$);
         MapInstances2.$init$(MODULE$);
         MapInstances3.$init$(MODULE$);
      }

      public MapInnerProductSpace MapInnerProductSpace(final Field evidence$7) {
         return MapInstances3.MapInnerProductSpace$(this, evidence$7);
      }

      public MapEq MapEq(final Eq V0) {
         return MapInstances3.MapEq$(this, V0);
      }

      public MapGroup MapGroup(final Group evidence$5) {
         return MapInstances2.MapGroup$(this, evidence$5);
      }

      public MapVectorSpace MapVectorSpace(final Field evidence$6) {
         return MapInstances2.MapVectorSpace$(this, evidence$6);
      }

      public MapCRng MapCRng(final CommutativeRing evidence$4) {
         return MapInstances1.MapCRng$(this, evidence$4);
      }

      public MapMonoid MapMonoid(final Semigroup evidence$2) {
         return MapInstances0.MapMonoid$(this, evidence$2);
      }

      public MapCSemiring MapCSemiring(final CommutativeSemiring evidence$3) {
         return MapInstances0.MapCSemiring$(this, evidence$3);
      }
   }

   public static class tuples$ implements ProductInstances {
      public static final tuples$ MODULE$ = new tuples$();

      static {
         SemigroupProductInstances.$init$(MODULE$);
         MonoidProductInstances.$init$(MODULE$);
         GroupProductInstances.$init$(MODULE$);
         AbGroupProductInstances.$init$(MODULE$);
         SemiringProductInstances.$init$(MODULE$);
         RngProductInstances.$init$(MODULE$);
         RigProductInstances.$init$(MODULE$);
         RingProductInstances.$init$(MODULE$);
         EqProductInstances.$init$(MODULE$);
         OrderProductInstances.$init$(MODULE$);
      }

      public Order OrderProduct2(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mDDc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mDFc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mDIc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mDJc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mFDc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mFFc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mFIc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mFJc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mIDc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mIFc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mIIc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mIJc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mJDc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mJFc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mJIc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct2$mJJc$sp(final Order _structure1, final Order _structure2) {
         return OrderProductInstances.OrderProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Order OrderProduct3(final Order _structure1, final Order _structure2, final Order _structure3) {
         return OrderProductInstances.OrderProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Order OrderProduct4(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4) {
         return OrderProductInstances.OrderProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Order OrderProduct5(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5) {
         return OrderProductInstances.OrderProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Order OrderProduct6(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6) {
         return OrderProductInstances.OrderProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Order OrderProduct7(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7) {
         return OrderProductInstances.OrderProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Order OrderProduct8(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8) {
         return OrderProductInstances.OrderProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Order OrderProduct9(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9) {
         return OrderProductInstances.OrderProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Order OrderProduct10(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10) {
         return OrderProductInstances.OrderProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Order OrderProduct11(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11) {
         return OrderProductInstances.OrderProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Order OrderProduct12(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12) {
         return OrderProductInstances.OrderProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Order OrderProduct13(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13) {
         return OrderProductInstances.OrderProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Order OrderProduct14(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14) {
         return OrderProductInstances.OrderProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Order OrderProduct15(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15) {
         return OrderProductInstances.OrderProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Order OrderProduct16(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16) {
         return OrderProductInstances.OrderProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Order OrderProduct17(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17) {
         return OrderProductInstances.OrderProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Order OrderProduct18(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18) {
         return OrderProductInstances.OrderProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Order OrderProduct19(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19) {
         return OrderProductInstances.OrderProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Order OrderProduct20(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20) {
         return OrderProductInstances.OrderProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Order OrderProduct21(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20, final Order _structure21) {
         return OrderProductInstances.OrderProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Order OrderProduct22(final Order _structure1, final Order _structure2, final Order _structure3, final Order _structure4, final Order _structure5, final Order _structure6, final Order _structure7, final Order _structure8, final Order _structure9, final Order _structure10, final Order _structure11, final Order _structure12, final Order _structure13, final Order _structure14, final Order _structure15, final Order _structure16, final Order _structure17, final Order _structure18, final Order _structure19, final Order _structure20, final Order _structure21, final Order _structure22) {
         return OrderProductInstances.OrderProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Eq EqProduct2(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mDDc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mDFc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mDIc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mDJc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mFDc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mFFc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mFIc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mFJc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mIDc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mIFc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mIIc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mIJc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mJDc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mJFc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mJIc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct2$mJJc$sp(final Eq _structure1, final Eq _structure2) {
         return EqProductInstances.EqProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Eq EqProduct3(final Eq _structure1, final Eq _structure2, final Eq _structure3) {
         return EqProductInstances.EqProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Eq EqProduct4(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4) {
         return EqProductInstances.EqProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Eq EqProduct5(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5) {
         return EqProductInstances.EqProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Eq EqProduct6(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6) {
         return EqProductInstances.EqProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Eq EqProduct7(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7) {
         return EqProductInstances.EqProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Eq EqProduct8(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8) {
         return EqProductInstances.EqProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Eq EqProduct9(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9) {
         return EqProductInstances.EqProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Eq EqProduct10(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10) {
         return EqProductInstances.EqProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Eq EqProduct11(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11) {
         return EqProductInstances.EqProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Eq EqProduct12(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12) {
         return EqProductInstances.EqProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Eq EqProduct13(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13) {
         return EqProductInstances.EqProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Eq EqProduct14(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14) {
         return EqProductInstances.EqProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Eq EqProduct15(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15) {
         return EqProductInstances.EqProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Eq EqProduct16(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16) {
         return EqProductInstances.EqProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Eq EqProduct17(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17) {
         return EqProductInstances.EqProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Eq EqProduct18(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18) {
         return EqProductInstances.EqProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Eq EqProduct19(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19) {
         return EqProductInstances.EqProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Eq EqProduct20(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20) {
         return EqProductInstances.EqProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Eq EqProduct21(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20, final Eq _structure21) {
         return EqProductInstances.EqProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Eq EqProduct22(final Eq _structure1, final Eq _structure2, final Eq _structure3, final Eq _structure4, final Eq _structure5, final Eq _structure6, final Eq _structure7, final Eq _structure8, final Eq _structure9, final Eq _structure10, final Eq _structure11, final Eq _structure12, final Eq _structure13, final Eq _structure14, final Eq _structure15, final Eq _structure16, final Eq _structure17, final Eq _structure18, final Eq _structure19, final Eq _structure20, final Eq _structure21, final Eq _structure22) {
         return EqProductInstances.EqProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Ring RingProduct2(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mDDc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mDFc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mDIc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mDJc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mFDc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mFFc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mFIc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mFJc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mIDc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mIFc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mIIc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mIJc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mJDc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mJFc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mJIc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct2$mJJc$sp(final Ring _structure1, final Ring _structure2) {
         return RingProductInstances.RingProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Ring RingProduct3(final Ring _structure1, final Ring _structure2, final Ring _structure3) {
         return RingProductInstances.RingProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Ring RingProduct4(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4) {
         return RingProductInstances.RingProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Ring RingProduct5(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5) {
         return RingProductInstances.RingProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Ring RingProduct6(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6) {
         return RingProductInstances.RingProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Ring RingProduct7(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7) {
         return RingProductInstances.RingProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Ring RingProduct8(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8) {
         return RingProductInstances.RingProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Ring RingProduct9(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9) {
         return RingProductInstances.RingProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Ring RingProduct10(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10) {
         return RingProductInstances.RingProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Ring RingProduct11(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11) {
         return RingProductInstances.RingProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Ring RingProduct12(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12) {
         return RingProductInstances.RingProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Ring RingProduct13(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13) {
         return RingProductInstances.RingProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Ring RingProduct14(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14) {
         return RingProductInstances.RingProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Ring RingProduct15(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15) {
         return RingProductInstances.RingProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Ring RingProduct16(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16) {
         return RingProductInstances.RingProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Ring RingProduct17(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17) {
         return RingProductInstances.RingProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Ring RingProduct18(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18) {
         return RingProductInstances.RingProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Ring RingProduct19(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19) {
         return RingProductInstances.RingProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Ring RingProduct20(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20) {
         return RingProductInstances.RingProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Ring RingProduct21(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20, final Ring _structure21) {
         return RingProductInstances.RingProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Ring RingProduct22(final Ring _structure1, final Ring _structure2, final Ring _structure3, final Ring _structure4, final Ring _structure5, final Ring _structure6, final Ring _structure7, final Ring _structure8, final Ring _structure9, final Ring _structure10, final Ring _structure11, final Ring _structure12, final Ring _structure13, final Ring _structure14, final Ring _structure15, final Ring _structure16, final Ring _structure17, final Ring _structure18, final Ring _structure19, final Ring _structure20, final Ring _structure21, final Ring _structure22) {
         return RingProductInstances.RingProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Rig RigProduct2(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mDDc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mDFc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mDIc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mDJc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mFDc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mFFc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mFIc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mFJc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mIDc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mIFc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mIIc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mIJc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mJDc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mJFc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mJIc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct2$mJJc$sp(final Rig _structure1, final Rig _structure2) {
         return RigProductInstances.RigProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Rig RigProduct3(final Rig _structure1, final Rig _structure2, final Rig _structure3) {
         return RigProductInstances.RigProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Rig RigProduct4(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4) {
         return RigProductInstances.RigProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Rig RigProduct5(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5) {
         return RigProductInstances.RigProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Rig RigProduct6(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6) {
         return RigProductInstances.RigProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Rig RigProduct7(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7) {
         return RigProductInstances.RigProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Rig RigProduct8(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8) {
         return RigProductInstances.RigProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Rig RigProduct9(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9) {
         return RigProductInstances.RigProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Rig RigProduct10(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10) {
         return RigProductInstances.RigProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Rig RigProduct11(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11) {
         return RigProductInstances.RigProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Rig RigProduct12(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12) {
         return RigProductInstances.RigProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Rig RigProduct13(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13) {
         return RigProductInstances.RigProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Rig RigProduct14(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14) {
         return RigProductInstances.RigProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Rig RigProduct15(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15) {
         return RigProductInstances.RigProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Rig RigProduct16(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16) {
         return RigProductInstances.RigProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Rig RigProduct17(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17) {
         return RigProductInstances.RigProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Rig RigProduct18(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18) {
         return RigProductInstances.RigProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Rig RigProduct19(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19) {
         return RigProductInstances.RigProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Rig RigProduct20(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20) {
         return RigProductInstances.RigProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Rig RigProduct21(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20, final Rig _structure21) {
         return RigProductInstances.RigProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Rig RigProduct22(final Rig _structure1, final Rig _structure2, final Rig _structure3, final Rig _structure4, final Rig _structure5, final Rig _structure6, final Rig _structure7, final Rig _structure8, final Rig _structure9, final Rig _structure10, final Rig _structure11, final Rig _structure12, final Rig _structure13, final Rig _structure14, final Rig _structure15, final Rig _structure16, final Rig _structure17, final Rig _structure18, final Rig _structure19, final Rig _structure20, final Rig _structure21, final Rig _structure22) {
         return RigProductInstances.RigProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Rng RngProduct2(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mDDc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mDFc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mDIc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mDJc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mFDc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mFFc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mFIc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mFJc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mIDc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mIFc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mIIc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mIJc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mJDc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mJFc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mJIc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct2$mJJc$sp(final Rng _structure1, final Rng _structure2) {
         return RngProductInstances.RngProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Rng RngProduct3(final Rng _structure1, final Rng _structure2, final Rng _structure3) {
         return RngProductInstances.RngProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Rng RngProduct4(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4) {
         return RngProductInstances.RngProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Rng RngProduct5(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5) {
         return RngProductInstances.RngProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Rng RngProduct6(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6) {
         return RngProductInstances.RngProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Rng RngProduct7(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7) {
         return RngProductInstances.RngProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Rng RngProduct8(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8) {
         return RngProductInstances.RngProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Rng RngProduct9(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9) {
         return RngProductInstances.RngProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Rng RngProduct10(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10) {
         return RngProductInstances.RngProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Rng RngProduct11(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11) {
         return RngProductInstances.RngProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Rng RngProduct12(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12) {
         return RngProductInstances.RngProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Rng RngProduct13(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13) {
         return RngProductInstances.RngProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Rng RngProduct14(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14) {
         return RngProductInstances.RngProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Rng RngProduct15(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15) {
         return RngProductInstances.RngProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Rng RngProduct16(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16) {
         return RngProductInstances.RngProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Rng RngProduct17(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17) {
         return RngProductInstances.RngProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Rng RngProduct18(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18) {
         return RngProductInstances.RngProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Rng RngProduct19(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19) {
         return RngProductInstances.RngProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Rng RngProduct20(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20) {
         return RngProductInstances.RngProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Rng RngProduct21(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20, final Rng _structure21) {
         return RngProductInstances.RngProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Rng RngProduct22(final Rng _structure1, final Rng _structure2, final Rng _structure3, final Rng _structure4, final Rng _structure5, final Rng _structure6, final Rng _structure7, final Rng _structure8, final Rng _structure9, final Rng _structure10, final Rng _structure11, final Rng _structure12, final Rng _structure13, final Rng _structure14, final Rng _structure15, final Rng _structure16, final Rng _structure17, final Rng _structure18, final Rng _structure19, final Rng _structure20, final Rng _structure21, final Rng _structure22) {
         return RngProductInstances.RngProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Semiring SemiringProduct2(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mDDc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mDFc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mDIc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mDJc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mFDc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mFFc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mFIc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mFJc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mIDc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mIFc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mIIc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mIJc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mJDc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mJFc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mJIc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct2$mJJc$sp(final Semiring _structure1, final Semiring _structure2) {
         return SemiringProductInstances.SemiringProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Semiring SemiringProduct3(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3) {
         return SemiringProductInstances.SemiringProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Semiring SemiringProduct4(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4) {
         return SemiringProductInstances.SemiringProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Semiring SemiringProduct5(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5) {
         return SemiringProductInstances.SemiringProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Semiring SemiringProduct6(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6) {
         return SemiringProductInstances.SemiringProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Semiring SemiringProduct7(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7) {
         return SemiringProductInstances.SemiringProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Semiring SemiringProduct8(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8) {
         return SemiringProductInstances.SemiringProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Semiring SemiringProduct9(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9) {
         return SemiringProductInstances.SemiringProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Semiring SemiringProduct10(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10) {
         return SemiringProductInstances.SemiringProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Semiring SemiringProduct11(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11) {
         return SemiringProductInstances.SemiringProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Semiring SemiringProduct12(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12) {
         return SemiringProductInstances.SemiringProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Semiring SemiringProduct13(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13) {
         return SemiringProductInstances.SemiringProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Semiring SemiringProduct14(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14) {
         return SemiringProductInstances.SemiringProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Semiring SemiringProduct15(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15) {
         return SemiringProductInstances.SemiringProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Semiring SemiringProduct16(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16) {
         return SemiringProductInstances.SemiringProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Semiring SemiringProduct17(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17) {
         return SemiringProductInstances.SemiringProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Semiring SemiringProduct18(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18) {
         return SemiringProductInstances.SemiringProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Semiring SemiringProduct19(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19) {
         return SemiringProductInstances.SemiringProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Semiring SemiringProduct20(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20) {
         return SemiringProductInstances.SemiringProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Semiring SemiringProduct21(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20, final Semiring _structure21) {
         return SemiringProductInstances.SemiringProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Semiring SemiringProduct22(final Semiring _structure1, final Semiring _structure2, final Semiring _structure3, final Semiring _structure4, final Semiring _structure5, final Semiring _structure6, final Semiring _structure7, final Semiring _structure8, final Semiring _structure9, final Semiring _structure10, final Semiring _structure11, final Semiring _structure12, final Semiring _structure13, final Semiring _structure14, final Semiring _structure15, final Semiring _structure16, final Semiring _structure17, final Semiring _structure18, final Semiring _structure19, final Semiring _structure20, final Semiring _structure21, final Semiring _structure22) {
         return SemiringProductInstances.SemiringProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public CommutativeGroup AbGroupProduct2(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mDDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mDFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mDIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mDJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mFDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mFFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mFIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mFJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mIDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mIFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mIIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mIJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mJDc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mJFc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mJIc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct2$mJJc$sp(final CommutativeGroup _structure1, final CommutativeGroup _structure2) {
         return AbGroupProductInstances.AbGroupProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public CommutativeGroup AbGroupProduct3(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3) {
         return AbGroupProductInstances.AbGroupProduct3$(this, _structure1, _structure2, _structure3);
      }

      public CommutativeGroup AbGroupProduct4(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4) {
         return AbGroupProductInstances.AbGroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public CommutativeGroup AbGroupProduct5(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5) {
         return AbGroupProductInstances.AbGroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public CommutativeGroup AbGroupProduct6(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6) {
         return AbGroupProductInstances.AbGroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public CommutativeGroup AbGroupProduct7(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7) {
         return AbGroupProductInstances.AbGroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public CommutativeGroup AbGroupProduct8(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8) {
         return AbGroupProductInstances.AbGroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public CommutativeGroup AbGroupProduct9(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9) {
         return AbGroupProductInstances.AbGroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public CommutativeGroup AbGroupProduct10(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10) {
         return AbGroupProductInstances.AbGroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public CommutativeGroup AbGroupProduct11(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11) {
         return AbGroupProductInstances.AbGroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public CommutativeGroup AbGroupProduct12(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12) {
         return AbGroupProductInstances.AbGroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public CommutativeGroup AbGroupProduct13(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13) {
         return AbGroupProductInstances.AbGroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public CommutativeGroup AbGroupProduct14(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14) {
         return AbGroupProductInstances.AbGroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public CommutativeGroup AbGroupProduct15(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15) {
         return AbGroupProductInstances.AbGroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public CommutativeGroup AbGroupProduct16(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16) {
         return AbGroupProductInstances.AbGroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public CommutativeGroup AbGroupProduct17(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17) {
         return AbGroupProductInstances.AbGroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public CommutativeGroup AbGroupProduct18(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18) {
         return AbGroupProductInstances.AbGroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public CommutativeGroup AbGroupProduct19(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19) {
         return AbGroupProductInstances.AbGroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public CommutativeGroup AbGroupProduct20(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20) {
         return AbGroupProductInstances.AbGroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public CommutativeGroup AbGroupProduct21(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20, final CommutativeGroup _structure21) {
         return AbGroupProductInstances.AbGroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public CommutativeGroup AbGroupProduct22(final CommutativeGroup _structure1, final CommutativeGroup _structure2, final CommutativeGroup _structure3, final CommutativeGroup _structure4, final CommutativeGroup _structure5, final CommutativeGroup _structure6, final CommutativeGroup _structure7, final CommutativeGroup _structure8, final CommutativeGroup _structure9, final CommutativeGroup _structure10, final CommutativeGroup _structure11, final CommutativeGroup _structure12, final CommutativeGroup _structure13, final CommutativeGroup _structure14, final CommutativeGroup _structure15, final CommutativeGroup _structure16, final CommutativeGroup _structure17, final CommutativeGroup _structure18, final CommutativeGroup _structure19, final CommutativeGroup _structure20, final CommutativeGroup _structure21, final CommutativeGroup _structure22) {
         return AbGroupProductInstances.AbGroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Group GroupProduct2(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mDDc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mDFc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mDIc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mDJc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mFDc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mFFc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mFIc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mFJc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mIDc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mIFc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mIIc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mIJc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mJDc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mJFc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mJIc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct2$mJJc$sp(final Group _structure1, final Group _structure2) {
         return GroupProductInstances.GroupProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Group GroupProduct3(final Group _structure1, final Group _structure2, final Group _structure3) {
         return GroupProductInstances.GroupProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Group GroupProduct4(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4) {
         return GroupProductInstances.GroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Group GroupProduct5(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5) {
         return GroupProductInstances.GroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Group GroupProduct6(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6) {
         return GroupProductInstances.GroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Group GroupProduct7(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7) {
         return GroupProductInstances.GroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Group GroupProduct8(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8) {
         return GroupProductInstances.GroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Group GroupProduct9(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9) {
         return GroupProductInstances.GroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Group GroupProduct10(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10) {
         return GroupProductInstances.GroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Group GroupProduct11(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11) {
         return GroupProductInstances.GroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Group GroupProduct12(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12) {
         return GroupProductInstances.GroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Group GroupProduct13(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13) {
         return GroupProductInstances.GroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Group GroupProduct14(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14) {
         return GroupProductInstances.GroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Group GroupProduct15(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15) {
         return GroupProductInstances.GroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Group GroupProduct16(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16) {
         return GroupProductInstances.GroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Group GroupProduct17(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17) {
         return GroupProductInstances.GroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Group GroupProduct18(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18) {
         return GroupProductInstances.GroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Group GroupProduct19(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19) {
         return GroupProductInstances.GroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Group GroupProduct20(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20) {
         return GroupProductInstances.GroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Group GroupProduct21(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20, final Group _structure21) {
         return GroupProductInstances.GroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Group GroupProduct22(final Group _structure1, final Group _structure2, final Group _structure3, final Group _structure4, final Group _structure5, final Group _structure6, final Group _structure7, final Group _structure8, final Group _structure9, final Group _structure10, final Group _structure11, final Group _structure12, final Group _structure13, final Group _structure14, final Group _structure15, final Group _structure16, final Group _structure17, final Group _structure18, final Group _structure19, final Group _structure20, final Group _structure21, final Group _structure22) {
         return GroupProductInstances.GroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Monoid MonoidProduct2(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mDDc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mDFc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mDIc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mDJc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mFDc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mFFc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mFIc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mFJc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mIDc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mIFc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mIIc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mIJc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mJDc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mJFc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mJIc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct2$mJJc$sp(final Monoid _structure1, final Monoid _structure2) {
         return MonoidProductInstances.MonoidProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Monoid MonoidProduct3(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3) {
         return MonoidProductInstances.MonoidProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Monoid MonoidProduct4(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4) {
         return MonoidProductInstances.MonoidProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Monoid MonoidProduct5(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5) {
         return MonoidProductInstances.MonoidProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Monoid MonoidProduct6(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6) {
         return MonoidProductInstances.MonoidProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Monoid MonoidProduct7(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7) {
         return MonoidProductInstances.MonoidProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Monoid MonoidProduct8(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8) {
         return MonoidProductInstances.MonoidProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Monoid MonoidProduct9(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9) {
         return MonoidProductInstances.MonoidProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Monoid MonoidProduct10(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10) {
         return MonoidProductInstances.MonoidProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Monoid MonoidProduct11(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11) {
         return MonoidProductInstances.MonoidProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Monoid MonoidProduct12(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12) {
         return MonoidProductInstances.MonoidProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Monoid MonoidProduct13(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13) {
         return MonoidProductInstances.MonoidProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Monoid MonoidProduct14(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14) {
         return MonoidProductInstances.MonoidProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Monoid MonoidProduct15(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15) {
         return MonoidProductInstances.MonoidProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Monoid MonoidProduct16(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16) {
         return MonoidProductInstances.MonoidProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Monoid MonoidProduct17(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17) {
         return MonoidProductInstances.MonoidProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Monoid MonoidProduct18(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18) {
         return MonoidProductInstances.MonoidProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Monoid MonoidProduct19(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19) {
         return MonoidProductInstances.MonoidProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Monoid MonoidProduct20(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20) {
         return MonoidProductInstances.MonoidProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Monoid MonoidProduct21(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20, final Monoid _structure21) {
         return MonoidProductInstances.MonoidProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Monoid MonoidProduct22(final Monoid _structure1, final Monoid _structure2, final Monoid _structure3, final Monoid _structure4, final Monoid _structure5, final Monoid _structure6, final Monoid _structure7, final Monoid _structure8, final Monoid _structure9, final Monoid _structure10, final Monoid _structure11, final Monoid _structure12, final Monoid _structure13, final Monoid _structure14, final Monoid _structure15, final Monoid _structure16, final Monoid _structure17, final Monoid _structure18, final Monoid _structure19, final Monoid _structure20, final Monoid _structure21, final Monoid _structure22) {
         return MonoidProductInstances.MonoidProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }

      public Semigroup SemigroupProduct2(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mDDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mDDc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mDFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mDFc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mDIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mDIc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mDJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mDJc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mFDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mFDc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mFFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mFFc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mFIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mFIc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mFJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mFJc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mIDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mIDc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mIFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mIFc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mIIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mIIc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mIJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mIJc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mJDc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mJDc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mJFc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mJFc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mJIc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mJIc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct2$mJJc$sp(final Semigroup _structure1, final Semigroup _structure2) {
         return SemigroupProductInstances.SemigroupProduct2$mJJc$sp$(this, _structure1, _structure2);
      }

      public Semigroup SemigroupProduct3(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3) {
         return SemigroupProductInstances.SemigroupProduct3$(this, _structure1, _structure2, _structure3);
      }

      public Semigroup SemigroupProduct4(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4) {
         return SemigroupProductInstances.SemigroupProduct4$(this, _structure1, _structure2, _structure3, _structure4);
      }

      public Semigroup SemigroupProduct5(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5) {
         return SemigroupProductInstances.SemigroupProduct5$(this, _structure1, _structure2, _structure3, _structure4, _structure5);
      }

      public Semigroup SemigroupProduct6(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6) {
         return SemigroupProductInstances.SemigroupProduct6$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6);
      }

      public Semigroup SemigroupProduct7(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7) {
         return SemigroupProductInstances.SemigroupProduct7$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7);
      }

      public Semigroup SemigroupProduct8(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8) {
         return SemigroupProductInstances.SemigroupProduct8$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8);
      }

      public Semigroup SemigroupProduct9(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9) {
         return SemigroupProductInstances.SemigroupProduct9$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9);
      }

      public Semigroup SemigroupProduct10(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10) {
         return SemigroupProductInstances.SemigroupProduct10$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10);
      }

      public Semigroup SemigroupProduct11(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11) {
         return SemigroupProductInstances.SemigroupProduct11$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11);
      }

      public Semigroup SemigroupProduct12(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12) {
         return SemigroupProductInstances.SemigroupProduct12$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12);
      }

      public Semigroup SemigroupProduct13(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13) {
         return SemigroupProductInstances.SemigroupProduct13$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13);
      }

      public Semigroup SemigroupProduct14(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14) {
         return SemigroupProductInstances.SemigroupProduct14$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14);
      }

      public Semigroup SemigroupProduct15(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15) {
         return SemigroupProductInstances.SemigroupProduct15$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15);
      }

      public Semigroup SemigroupProduct16(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16) {
         return SemigroupProductInstances.SemigroupProduct16$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16);
      }

      public Semigroup SemigroupProduct17(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17) {
         return SemigroupProductInstances.SemigroupProduct17$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17);
      }

      public Semigroup SemigroupProduct18(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18) {
         return SemigroupProductInstances.SemigroupProduct18$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18);
      }

      public Semigroup SemigroupProduct19(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19) {
         return SemigroupProductInstances.SemigroupProduct19$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19);
      }

      public Semigroup SemigroupProduct20(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20) {
         return SemigroupProductInstances.SemigroupProduct20$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20);
      }

      public Semigroup SemigroupProduct21(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20, final Semigroup _structure21) {
         return SemigroupProductInstances.SemigroupProduct21$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21);
      }

      public Semigroup SemigroupProduct22(final Semigroup _structure1, final Semigroup _structure2, final Semigroup _structure3, final Semigroup _structure4, final Semigroup _structure5, final Semigroup _structure6, final Semigroup _structure7, final Semigroup _structure8, final Semigroup _structure9, final Semigroup _structure10, final Semigroup _structure11, final Semigroup _structure12, final Semigroup _structure13, final Semigroup _structure14, final Semigroup _structure15, final Semigroup _structure16, final Semigroup _structure17, final Semigroup _structure18, final Semigroup _structure19, final Semigroup _structure20, final Semigroup _structure21, final Semigroup _structure22) {
         return SemigroupProductInstances.SemigroupProduct22$(this, _structure1, _structure2, _structure3, _structure4, _structure5, _structure6, _structure7, _structure8, _structure9, _structure10, _structure11, _structure12, _structure13, _structure14, _structure15, _structure16, _structure17, _structure18, _structure19, _structure20, _structure21, _structure22);
      }
   }

   public static class option$ implements OptionInstances {
      public static final option$ MODULE$ = new option$();

      static {
         OptionInstances0.$init$(MODULE$);
         OptionInstances.$init$(MODULE$);
      }

      public OptionCMonoid OptionCMonoid(final CommutativeSemigroup evidence$9) {
         return OptionInstances.OptionCMonoid$(this, evidence$9);
      }

      public OptionAdditiveMonoid OptionAdditiveMonoid(final AdditiveSemigroup evidence$10) {
         return OptionInstances.OptionAdditiveMonoid$(this, evidence$10);
      }

      public OptionMultiplicativeMonoid OptionMultiplicativeMonoid(final MultiplicativeSemigroup evidence$11) {
         return OptionInstances.OptionMultiplicativeMonoid$(this, evidence$11);
      }

      public OptionOrder OptionOrder(final Order evidence$12) {
         return OptionInstances.OptionOrder$(this, evidence$12);
      }

      public OptionEq OptionEq(final Eq evidence$7) {
         return OptionInstances0.OptionEq$(this, evidence$7);
      }

      public OptionMonoid OptionMonoid(final Semigroup evidence$8) {
         return OptionInstances0.OptionMonoid$(this, evidence$8);
      }
   }

   public static class unit$ implements UnitInstances {
      public static final unit$ MODULE$ = new unit$();
      private static CommutativeGroup UnitAlgebra;

      static {
         UnitInstances.$init$(MODULE$);
      }

      public final CommutativeGroup UnitAlgebra() {
         return UnitAlgebra;
      }

      public final void spire$std$UnitInstances$_setter_$UnitAlgebra_$eq(final CommutativeGroup x$1) {
         UnitAlgebra = x$1;
      }
   }
}
