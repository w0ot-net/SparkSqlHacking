package spire.std;

import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Monoid;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3A!\u0002\u0004\u0001\u0017!A\u0011\b\u0001B\u0002B\u0003-!\bC\u0003>\u0001\u0011\u0005a\bC\u0003D\u0001\u0011\u0005A\tC\u0003F\u0001\u0011\u0005aI\u0001\u000ePaRLwN\\'vYRL\u0007\u000f\\5dCRLg/Z'p]>LGM\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001!\u0006\u0002\rQM!\u0001!D\n2!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A\u0003I\u0012\u000f\u0005UibB\u0001\f\u001c\u001d\t9\"$D\u0001\u0019\u0015\tI\"\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u0011A\u0004C\u0001\bC2<WM\u0019:b\u0013\tqr$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005qA\u0011BA\u0011#\u0005QiU\u000f\u001c;ja2L7-\u0019;jm\u0016luN\\8jI*\u0011ad\b\t\u0004\u001d\u00112\u0013BA\u0013\u0010\u0005\u0019y\u0005\u000f^5p]B\u0011q\u0005\u000b\u0007\u0001\t\u0015I\u0003A1\u0001+\u0005\u0005\t\u0015CA\u0016/!\tqA&\u0003\u0002.\u001f\t9aj\u001c;iS:<\u0007C\u0001\b0\u0013\t\u0001tBA\u0002B]f\u0004\"A\r\u001c\u000f\u0005M*dBA\f5\u0013\u0005\u0001\u0012B\u0001\u0010\u0010\u0013\t9\u0004H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u001f\u001f\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\u0007QYd%\u0003\u0002=E\t9R*\u001e7uSBd\u0017nY1uSZ,7+Z7jOJ|W\u000f]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003}\"\"\u0001\u0011\"\u0011\u0007\u0005\u0003a%D\u0001\u0007\u0011\u0015I$\u0001q\u0001;\u0003\ryg.Z\u000b\u0002G\u0005)A/[7fgR\u00191eR%\t\u000b!#\u0001\u0019A\u0012\u0002\u0003aDQA\u0013\u0003A\u0002\r\n\u0011!\u001f\u0015\u0005\u00011{\u0005\u000b\u0005\u0002\u000f\u001b&\u0011aj\u0004\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001"
)
public class OptionMultiplicativeMonoid implements MultiplicativeMonoid {
   private static final long serialVersionUID = 0L;
   private final MultiplicativeSemigroup evidence$4;

   public Monoid multiplicative() {
      return MultiplicativeMonoid.multiplicative$(this);
   }

   public Monoid multiplicative$mcD$sp() {
      return MultiplicativeMonoid.multiplicative$mcD$sp$(this);
   }

   public Monoid multiplicative$mcF$sp() {
      return MultiplicativeMonoid.multiplicative$mcF$sp$(this);
   }

   public Monoid multiplicative$mcI$sp() {
      return MultiplicativeMonoid.multiplicative$mcI$sp$(this);
   }

   public Monoid multiplicative$mcJ$sp() {
      return MultiplicativeMonoid.multiplicative$mcJ$sp$(this);
   }

   public double one$mcD$sp() {
      return MultiplicativeMonoid.one$mcD$sp$(this);
   }

   public float one$mcF$sp() {
      return MultiplicativeMonoid.one$mcF$sp$(this);
   }

   public int one$mcI$sp() {
      return MultiplicativeMonoid.one$mcI$sp$(this);
   }

   public long one$mcJ$sp() {
      return MultiplicativeMonoid.one$mcJ$sp$(this);
   }

   public boolean isOne(final Object a, final Eq ev) {
      return MultiplicativeMonoid.isOne$(this, a, ev);
   }

   public boolean isOne$mcD$sp(final double a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
   }

   public boolean isOne$mcF$sp(final float a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
   }

   public boolean isOne$mcI$sp(final int a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
   }

   public boolean isOne$mcJ$sp(final long a, final Eq ev) {
      return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
   }

   public Object pow(final Object a, final int n) {
      return MultiplicativeMonoid.pow$(this, a, n);
   }

   public double pow$mcD$sp(final double a, final int n) {
      return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
   }

   public float pow$mcF$sp(final float a, final int n) {
      return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
   }

   public int pow$mcI$sp(final int a, final int n) {
      return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
   }

   public long pow$mcJ$sp(final long a, final int n) {
      return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
   }

   public Object product(final IterableOnce as) {
      return MultiplicativeMonoid.product$(this, as);
   }

   public double product$mcD$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcD$sp$(this, as);
   }

   public float product$mcF$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcF$sp$(this, as);
   }

   public int product$mcI$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcI$sp$(this, as);
   }

   public long product$mcJ$sp(final IterableOnce as) {
      return MultiplicativeMonoid.product$mcJ$sp$(this, as);
   }

   public Option tryProduct(final IterableOnce as) {
      return MultiplicativeMonoid.tryProduct$(this, as);
   }

   public double times$mcD$sp(final double x, final double y) {
      return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
   }

   public float times$mcF$sp(final float x, final float y) {
      return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
   }

   public int times$mcI$sp(final int x, final int y) {
      return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
   }

   public long times$mcJ$sp(final long x, final long y) {
      return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
   }

   public Object positivePow(final Object a, final int n) {
      return MultiplicativeSemigroup.positivePow$(this, a, n);
   }

   public double positivePow$mcD$sp(final double a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
   }

   public float positivePow$mcF$sp(final float a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
   }

   public int positivePow$mcI$sp(final int a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
   }

   public long positivePow$mcJ$sp(final long a, final int n) {
      return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
   }

   public Option one() {
      return .MODULE$;
   }

   public Option times(final Option x, final Option y) {
      Tuple2 var4 = new Tuple2(x, y);
      Object var3;
      if (var4 != null) {
         Option var5 = (Option)var4._1();
         Option var6 = (Option)var4._2();
         if (var5 instanceof Some) {
            Some var7 = (Some)var5;
            Object x = var7.value();
            if (var6 instanceof Some) {
               Some var9 = (Some)var6;
               Object y = var9.value();
               var3 = new Some(this.evidence$4.times(x, y));
               return (Option)var3;
            }
         }
      }

      if (var4 != null) {
         Option var11 = (Option)var4._1();
         Option var12 = (Option)var4._2();
         if (.MODULE$.equals(var11) && .MODULE$.equals(var12)) {
            var3 = .MODULE$;
            return (Option)var3;
         }
      }

      if (var4 != null) {
         Option x = (Option)var4._1();
         Option var14 = (Option)var4._2();
         if (.MODULE$.equals(var14)) {
            var3 = x;
            return (Option)var3;
         }
      }

      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         Option var15 = (Option)var4._1();
         Option y = (Option)var4._2();
         if (!.MODULE$.equals(var15)) {
            throw new MatchError(var4);
         } else {
            var3 = y;
            return (Option)var3;
         }
      }
   }

   public OptionMultiplicativeMonoid(final MultiplicativeSemigroup evidence$4) {
      this.evidence$4 = evidence$4;
      MultiplicativeSemigroup.$init$(this);
      MultiplicativeMonoid.$init$(this);
   }
}
