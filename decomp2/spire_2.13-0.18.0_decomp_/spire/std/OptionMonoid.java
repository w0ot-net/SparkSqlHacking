package spire.std;

import cats.kernel.Eq;
import cats.kernel.Monoid;
import cats.kernel.Semigroup;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3A!\u0002\u0004\u0001\u0017!A\u0011\b\u0001B\u0002B\u0003-!\bC\u0003>\u0001\u0011\u0005a\bC\u0003D\u0001\u0011\u0005A\tC\u0003F\u0001\u0011\u0005aI\u0001\u0007PaRLwN\\'p]>LGM\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001!\u0006\u0002\rQM!\u0001!D\n2!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A\u0003I\u0012\u000f\u0005UibB\u0001\f\u001c\u001d\t9\"$D\u0001\u0019\u0015\tI\"\"\u0001\u0004=e>|GOP\u0005\u0002\u0013%\u0011A\u0004C\u0001\bC2<WM\u0019:b\u0013\tqr$A\u0004qC\u000e\\\u0017mZ3\u000b\u0005qA\u0011BA\u0011#\u0005\u0019iuN\\8jI*\u0011ad\b\t\u0004\u001d\u00112\u0013BA\u0013\u0010\u0005\u0019y\u0005\u000f^5p]B\u0011q\u0005\u000b\u0007\u0001\t\u0015I\u0003A1\u0001+\u0005\u0005\t\u0015CA\u0016/!\tqA&\u0003\u0002.\u001f\t9aj\u001c;iS:<\u0007C\u0001\b0\u0013\t\u0001tBA\u0002B]f\u0004\"A\r\u001c\u000f\u0005M*dBA\f5\u0013\u0005\u0001\u0012B\u0001\u0010\u0010\u0013\t9\u0004H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002\u001f\u001f\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007QYd%\u0003\u0002=E\tI1+Z7jOJ|W\u000f]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003}\"\"\u0001\u0011\"\u0011\u0007\u0005\u0003a%D\u0001\u0007\u0011\u0015I$\u0001q\u0001;\u0003\u0015)W\u000e\u001d;z+\u0005\u0019\u0013aB2p[\nLg.\u001a\u000b\u0004G\u001dK\u0005\"\u0002%\u0005\u0001\u0004\u0019\u0013!\u0001=\t\u000b)#\u0001\u0019A\u0012\u0002\u0003eDC\u0001\u0001'P!B\u0011a\"T\u0005\u0003\u001d>\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0001\u0001"
)
public class OptionMonoid implements Monoid {
   private static final long serialVersionUID = 0L;
   private final Semigroup evidence$1;

   public double empty$mcD$sp() {
      return Monoid.empty$mcD$sp$(this);
   }

   public float empty$mcF$sp() {
      return Monoid.empty$mcF$sp$(this);
   }

   public int empty$mcI$sp() {
      return Monoid.empty$mcI$sp$(this);
   }

   public long empty$mcJ$sp() {
      return Monoid.empty$mcJ$sp$(this);
   }

   public boolean isEmpty(final Object a, final Eq ev) {
      return Monoid.isEmpty$(this, a, ev);
   }

   public boolean isEmpty$mcD$sp(final double a, final Eq ev) {
      return Monoid.isEmpty$mcD$sp$(this, a, ev);
   }

   public boolean isEmpty$mcF$sp(final float a, final Eq ev) {
      return Monoid.isEmpty$mcF$sp$(this, a, ev);
   }

   public boolean isEmpty$mcI$sp(final int a, final Eq ev) {
      return Monoid.isEmpty$mcI$sp$(this, a, ev);
   }

   public boolean isEmpty$mcJ$sp(final long a, final Eq ev) {
      return Monoid.isEmpty$mcJ$sp$(this, a, ev);
   }

   public Object combineN(final Object a, final int n) {
      return Monoid.combineN$(this, a, n);
   }

   public double combineN$mcD$sp(final double a, final int n) {
      return Monoid.combineN$mcD$sp$(this, a, n);
   }

   public float combineN$mcF$sp(final float a, final int n) {
      return Monoid.combineN$mcF$sp$(this, a, n);
   }

   public int combineN$mcI$sp(final int a, final int n) {
      return Monoid.combineN$mcI$sp$(this, a, n);
   }

   public long combineN$mcJ$sp(final long a, final int n) {
      return Monoid.combineN$mcJ$sp$(this, a, n);
   }

   public Object combineAll(final IterableOnce as) {
      return Monoid.combineAll$(this, as);
   }

   public double combineAll$mcD$sp(final IterableOnce as) {
      return Monoid.combineAll$mcD$sp$(this, as);
   }

   public float combineAll$mcF$sp(final IterableOnce as) {
      return Monoid.combineAll$mcF$sp$(this, as);
   }

   public int combineAll$mcI$sp(final IterableOnce as) {
      return Monoid.combineAll$mcI$sp$(this, as);
   }

   public long combineAll$mcJ$sp(final IterableOnce as) {
      return Monoid.combineAll$mcJ$sp$(this, as);
   }

   public Option combineAllOption(final IterableOnce as) {
      return Monoid.combineAllOption$(this, as);
   }

   public Monoid reverse() {
      return Monoid.reverse$(this);
   }

   public Monoid reverse$mcD$sp() {
      return Monoid.reverse$mcD$sp$(this);
   }

   public Monoid reverse$mcF$sp() {
      return Monoid.reverse$mcF$sp$(this);
   }

   public Monoid reverse$mcI$sp() {
      return Monoid.reverse$mcI$sp$(this);
   }

   public Monoid reverse$mcJ$sp() {
      return Monoid.reverse$mcJ$sp$(this);
   }

   public double combine$mcD$sp(final double x, final double y) {
      return Semigroup.combine$mcD$sp$(this, x, y);
   }

   public float combine$mcF$sp(final float x, final float y) {
      return Semigroup.combine$mcF$sp$(this, x, y);
   }

   public int combine$mcI$sp(final int x, final int y) {
      return Semigroup.combine$mcI$sp$(this, x, y);
   }

   public long combine$mcJ$sp(final long x, final long y) {
      return Semigroup.combine$mcJ$sp$(this, x, y);
   }

   public Object repeatedCombineN(final Object a, final int n) {
      return Semigroup.repeatedCombineN$(this, a, n);
   }

   public double repeatedCombineN$mcD$sp(final double a, final int n) {
      return Semigroup.repeatedCombineN$mcD$sp$(this, a, n);
   }

   public float repeatedCombineN$mcF$sp(final float a, final int n) {
      return Semigroup.repeatedCombineN$mcF$sp$(this, a, n);
   }

   public int repeatedCombineN$mcI$sp(final int a, final int n) {
      return Semigroup.repeatedCombineN$mcI$sp$(this, a, n);
   }

   public long repeatedCombineN$mcJ$sp(final long a, final int n) {
      return Semigroup.repeatedCombineN$mcJ$sp$(this, a, n);
   }

   public Semigroup intercalate(final Object middle) {
      return Semigroup.intercalate$(this, middle);
   }

   public Semigroup intercalate$mcD$sp(final double middle) {
      return Semigroup.intercalate$mcD$sp$(this, middle);
   }

   public Semigroup intercalate$mcF$sp(final float middle) {
      return Semigroup.intercalate$mcF$sp$(this, middle);
   }

   public Semigroup intercalate$mcI$sp(final int middle) {
      return Semigroup.intercalate$mcI$sp$(this, middle);
   }

   public Semigroup intercalate$mcJ$sp(final long middle) {
      return Semigroup.intercalate$mcJ$sp$(this, middle);
   }

   public Option empty() {
      return .MODULE$;
   }

   public Option combine(final Option x, final Option y) {
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
               var3 = new Some(this.evidence$1.combine(x, y));
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

   public OptionMonoid(final Semigroup evidence$1) {
      this.evidence$1 = evidence$1;
      Semigroup.$init$(this);
      Monoid.$init$(this);
   }
}
