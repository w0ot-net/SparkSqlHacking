package spire.std;

import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
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
   bytes = "\u0006\u0005E3A!\u0002\u0004\u0001\u0017!A\u0011\b\u0001B\u0002B\u0003-!\bC\u0003>\u0001\u0011\u0005a\bC\u0003D\u0001\u0011\u0005A\tC\u0003F\u0001\u0011\u0005aI\u0001\u000bPaRLwN\\!eI&$\u0018N^3N_:|\u0017\u000e\u001a\u0006\u0003\u000f!\t1a\u001d;e\u0015\u0005I\u0011!B:qSJ,7\u0001A\u000b\u0003\u0019!\u001aB\u0001A\u0007\u0014cA\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\u00042\u0001\u0006\u0011$\u001d\t)RD\u0004\u0002\u001779\u0011qCG\u0007\u00021)\u0011\u0011DC\u0001\u0007yI|w\u000e\u001e \n\u0003%I!\u0001\b\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011adH\u0001\ba\u0006\u001c7.Y4f\u0015\ta\u0002\"\u0003\u0002\"E\tq\u0011\t\u001a3ji&4X-T8o_&$'B\u0001\u0010 !\rqAEJ\u0005\u0003K=\u0011aa\u00149uS>t\u0007CA\u0014)\u0019\u0001!Q!\u000b\u0001C\u0002)\u0012\u0011!Q\t\u0003W9\u0002\"A\u0004\u0017\n\u00055z!a\u0002(pi\"Lgn\u001a\t\u0003\u001d=J!\u0001M\b\u0003\u0007\u0005s\u0017\u0010\u0005\u00023m9\u00111'\u000e\b\u0003/QJ\u0011\u0001E\u0005\u0003==I!a\u000e\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005yy\u0011AC3wS\u0012,gnY3%gA\u0019Ac\u000f\u0014\n\u0005q\u0012#!E!eI&$\u0018N^3TK6LwM]8va\u00061A(\u001b8jiz\"\u0012a\u0010\u000b\u0003\u0001\n\u00032!\u0011\u0001'\u001b\u00051\u0001\"B\u001d\u0003\u0001\bQ\u0014\u0001\u0002>fe>,\u0012aI\u0001\u0005a2,8\u000fF\u0002$\u000f&CQ\u0001\u0013\u0003A\u0002\r\n\u0011\u0001\u001f\u0005\u0006\u0015\u0012\u0001\raI\u0001\u0002s\"\"\u0001\u0001T(Q!\tqQ*\u0003\u0002O\u001f\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0001\u0001"
)
public class OptionAdditiveMonoid implements AdditiveMonoid {
   private static final long serialVersionUID = 0L;
   private final AdditiveSemigroup evidence$3;

   public Monoid additive() {
      return AdditiveMonoid.additive$(this);
   }

   public Monoid additive$mcD$sp() {
      return AdditiveMonoid.additive$mcD$sp$(this);
   }

   public Monoid additive$mcF$sp() {
      return AdditiveMonoid.additive$mcF$sp$(this);
   }

   public Monoid additive$mcI$sp() {
      return AdditiveMonoid.additive$mcI$sp$(this);
   }

   public Monoid additive$mcJ$sp() {
      return AdditiveMonoid.additive$mcJ$sp$(this);
   }

   public double zero$mcD$sp() {
      return AdditiveMonoid.zero$mcD$sp$(this);
   }

   public float zero$mcF$sp() {
      return AdditiveMonoid.zero$mcF$sp$(this);
   }

   public int zero$mcI$sp() {
      return AdditiveMonoid.zero$mcI$sp$(this);
   }

   public long zero$mcJ$sp() {
      return AdditiveMonoid.zero$mcJ$sp$(this);
   }

   public boolean isZero(final Object a, final Eq ev) {
      return AdditiveMonoid.isZero$(this, a, ev);
   }

   public boolean isZero$mcD$sp(final double a, final Eq ev) {
      return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
   }

   public boolean isZero$mcF$sp(final float a, final Eq ev) {
      return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
   }

   public boolean isZero$mcI$sp(final int a, final Eq ev) {
      return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
   }

   public boolean isZero$mcJ$sp(final long a, final Eq ev) {
      return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
   }

   public Object sumN(final Object a, final int n) {
      return AdditiveMonoid.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
   }

   public Object sum(final IterableOnce as) {
      return AdditiveMonoid.sum$(this, as);
   }

   public double sum$mcD$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcD$sp$(this, as);
   }

   public float sum$mcF$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcF$sp$(this, as);
   }

   public int sum$mcI$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcI$sp$(this, as);
   }

   public long sum$mcJ$sp(final IterableOnce as) {
      return AdditiveMonoid.sum$mcJ$sp$(this, as);
   }

   public Option trySum(final IterableOnce as) {
      return AdditiveMonoid.trySum$(this, as);
   }

   public double plus$mcD$sp(final double x, final double y) {
      return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
   }

   public float plus$mcF$sp(final float x, final float y) {
      return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
   }

   public int plus$mcI$sp(final int x, final int y) {
      return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
   }

   public long plus$mcJ$sp(final long x, final long y) {
      return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
   }

   public Object positiveSumN(final Object a, final int n) {
      return AdditiveSemigroup.positiveSumN$(this, a, n);
   }

   public double positiveSumN$mcD$sp(final double a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
   }

   public float positiveSumN$mcF$sp(final float a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
   }

   public int positiveSumN$mcI$sp(final int a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
   }

   public long positiveSumN$mcJ$sp(final long a, final int n) {
      return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
   }

   public Option zero() {
      return .MODULE$;
   }

   public Option plus(final Option x, final Option y) {
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
               var3 = new Some(this.evidence$3.plus(x, y));
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

   public OptionAdditiveMonoid(final AdditiveSemigroup evidence$3) {
      this.evidence$3 = evidence$3;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
   }
}
