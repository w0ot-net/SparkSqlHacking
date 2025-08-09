package spire.std;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRing;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import spire.algebra.CModule;
import spire.algebra.LeftModule;

@ScalaSignature(
   bytes = "\u0006\u0005U4AAC\u0006\u0001!!A!\t\u0001BC\u0002\u0013\r1\t\u0003\u0005O\u0001\t\u0005\t\u0015!\u0003E\u0011!y\u0005A!A!\u0002\u0017\u0001\u0006\"B*\u0001\t\u0003!\u0006\"\u0002.\u0001\t\u0003Y\u0006\"\u0002/\u0001\t\u0003i\u0006\"\u00021\u0001\t\u0003\t\u0007\"\u00024\u0001\t\u0003:\u0007\"\u00026\u0001\t\u0003Y'AC*fc\u000eku\u000eZ;mK*\u0011A\"D\u0001\u0004gR$'\"\u0001\b\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0019\u0011#\f\u0011\u0014\t\u0001\u0011\u0002D\u000e\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\teab\u0004L\u0007\u00025)\u00111$D\u0001\bC2<WM\u0019:b\u0013\ti\"DA\u0004D\u001b>$W\u000f\\3\u0011\u0005}\u0001C\u0002\u0001\u0003\u0006C\u0001\u0011\rA\t\u0002\u0003'\u0006\u000b\"a\t\u0014\u0011\u0005M!\u0013BA\u0013\u0015\u0005\u001dqu\u000e\u001e5j]\u001e\u0004Ra\n\u0016-gyi\u0011\u0001\u000b\u0006\u0003SQ\t!bY8mY\u0016\u001cG/[8o\u0013\tY\u0003F\u0001\u0004TKF|\u0005o\u001d\t\u0003?5\"QA\f\u0001C\u0002=\u0012\u0011!Q\t\u0003GA\u0002\"aE\u0019\n\u0005I\"\"aA!osB\u0011q\u0005N\u0005\u0003k!\u00121aU3r!\t9tH\u0004\u00029{9\u0011\u0011\bP\u0007\u0002u)\u00111hD\u0001\u0007yI|w\u000e\u001e \n\u0003UI!A\u0010\u000b\u0002\u000fA\f7m[1hK&\u0011\u0001)\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003}Q\taa]2bY\u0006\u0014X#\u0001#\u0011\u0007\u0015[EF\u0004\u0002G\u0015:\u0011q)\u0013\b\u0003s!K\u0011AD\u0005\u000375I!A\u0010\u000e\n\u00051k%!B\"SS:<'B\u0001 \u001b\u0003\u001d\u00198-\u00197be\u0002\n1a\u00192g!\u00119\u0013\u000b\f\u0010\n\u0005IC#a\u0002$bGR|'/_\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003U#2A\u0016-Z!\u00119\u0006\u0001\f\u0010\u000e\u0003-AQA\u0011\u0003A\u0004\u0011CQa\u0014\u0003A\u0004A\u000bAA_3s_V\ta$\u0001\u0004oK\u001e\fG/\u001a\u000b\u0003=yCQa\u0018\u0004A\u0002y\t!a]1\u0002\tAdWo\u001d\u000b\u0004=\t$\u0007\"B2\b\u0001\u0004q\u0012!\u0001=\t\u000b\u0015<\u0001\u0019\u0001\u0010\u0002\u0003e\fQ!\\5okN$2A\b5j\u0011\u0015\u0019\u0007\u00021\u0001\u001f\u0011\u0015)\u0007\u00021\u0001\u001f\u0003\u0019!\u0018.\\3tYR\u0019a\u0004\u001c8\t\u000b5L\u0001\u0019\u0001\u0017\u0002\u0003IDQaX\u0005A\u0002yAC\u0001\u00019tiB\u00111#]\u0005\u0003eR\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0001\u0001"
)
public class SeqCModule implements CModule {
   private static final long serialVersionUID = 0L;
   private final CommutativeRing scalar;
   private final Factory cbf;

   public CommutativeRing scalar$mcD$sp() {
      return CModule.scalar$mcD$sp$(this);
   }

   public CommutativeRing scalar$mcF$sp() {
      return CModule.scalar$mcF$sp$(this);
   }

   public CommutativeRing scalar$mcI$sp() {
      return CModule.scalar$mcI$sp$(this);
   }

   public CommutativeRing scalar$mcJ$sp() {
      return CModule.scalar$mcJ$sp$(this);
   }

   public Object timesr(final Object v, final Object r) {
      return CModule.timesr$(this, v, r);
   }

   public Object timesr$mcD$sp(final Object v, final double r) {
      return CModule.timesr$mcD$sp$(this, v, r);
   }

   public Object timesr$mcF$sp(final Object v, final float r) {
      return CModule.timesr$mcF$sp$(this, v, r);
   }

   public Object timesr$mcI$sp(final Object v, final int r) {
      return CModule.timesr$mcI$sp$(this, v, r);
   }

   public Object timesr$mcJ$sp(final Object v, final long r) {
      return CModule.timesr$mcJ$sp$(this, v, r);
   }

   public Object timesl$mcD$sp(final double r, final Object v) {
      return LeftModule.timesl$mcD$sp$(this, r, v);
   }

   public Object timesl$mcF$sp(final float r, final Object v) {
      return LeftModule.timesl$mcF$sp$(this, r, v);
   }

   public Object timesl$mcI$sp(final int r, final Object v) {
      return LeftModule.timesl$mcI$sp$(this, r, v);
   }

   public Object timesl$mcJ$sp(final long r, final Object v) {
      return LeftModule.timesl$mcJ$sp$(this, r, v);
   }

   public CommutativeGroup additive() {
      return AdditiveCommutativeGroup.additive$(this);
   }

   public CommutativeGroup additive$mcD$sp() {
      return AdditiveCommutativeGroup.additive$mcD$sp$(this);
   }

   public CommutativeGroup additive$mcF$sp() {
      return AdditiveCommutativeGroup.additive$mcF$sp$(this);
   }

   public CommutativeGroup additive$mcI$sp() {
      return AdditiveCommutativeGroup.additive$mcI$sp$(this);
   }

   public CommutativeGroup additive$mcJ$sp() {
      return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
   }

   public double negate$mcD$sp(final double x) {
      return AdditiveGroup.negate$mcD$sp$(this, x);
   }

   public float negate$mcF$sp(final float x) {
      return AdditiveGroup.negate$mcF$sp$(this, x);
   }

   public int negate$mcI$sp(final int x) {
      return AdditiveGroup.negate$mcI$sp$(this, x);
   }

   public long negate$mcJ$sp(final long x) {
      return AdditiveGroup.negate$mcJ$sp$(this, x);
   }

   public double minus$mcD$sp(final double x, final double y) {
      return AdditiveGroup.minus$mcD$sp$(this, x, y);
   }

   public float minus$mcF$sp(final float x, final float y) {
      return AdditiveGroup.minus$mcF$sp$(this, x, y);
   }

   public int minus$mcI$sp(final int x, final int y) {
      return AdditiveGroup.minus$mcI$sp$(this, x, y);
   }

   public long minus$mcJ$sp(final long x, final long y) {
      return AdditiveGroup.minus$mcJ$sp$(this, x, y);
   }

   public Object sumN(final Object a, final int n) {
      return AdditiveGroup.sumN$(this, a, n);
   }

   public double sumN$mcD$sp(final double a, final int n) {
      return AdditiveGroup.sumN$mcD$sp$(this, a, n);
   }

   public float sumN$mcF$sp(final float a, final int n) {
      return AdditiveGroup.sumN$mcF$sp$(this, a, n);
   }

   public int sumN$mcI$sp(final int a, final int n) {
      return AdditiveGroup.sumN$mcI$sp$(this, a, n);
   }

   public long sumN$mcJ$sp(final long a, final int n) {
      return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
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

   public CommutativeRing scalar() {
      return this.scalar;
   }

   public SeqOps zero() {
      return (SeqOps)this.cbf.newBuilder().result();
   }

   public SeqOps negate(final SeqOps sa) {
      return (SeqOps)this.cbf.fromSpecific((IterableOnce)sa.map((x) -> this.scalar().negate(x)));
   }

   public SeqOps plus(final SeqOps x, final SeqOps y) {
      return this.add2$1(x.iterator(), y.iterator(), this.cbf.newBuilder());
   }

   public SeqOps minus(final SeqOps x, final SeqOps y) {
      return this.sub2$1(x.iterator(), y.iterator(), this.cbf.newBuilder());
   }

   public SeqOps timesl(final Object r, final SeqOps sa) {
      return (SeqOps)this.cbf.fromSpecific((IterableOnce)sa.map((x$1) -> this.scalar().times(r, x$1)));
   }

   private final SeqOps add1$1(final Iterator it, final Builder b) {
      while(it.hasNext()) {
         b.$plus$eq(it.next());
         b = b;
         it = it;
      }

      return (SeqOps)b.result();
   }

   private final SeqOps add2$1(final Iterator xi, final Iterator yi, final Builder b) {
      while(true) {
         SeqOps var10000;
         if (!xi.hasNext()) {
            var10000 = this.add1$1(yi, b);
         } else {
            if (yi.hasNext()) {
               b.$plus$eq(this.scalar().plus(xi.next(), yi.next()));
               b = b;
               yi = yi;
               xi = xi;
               continue;
            }

            var10000 = this.add1$1(xi, b);
         }

         return var10000;
      }
   }

   private final SeqOps subl$1(final Iterator it, final Builder b) {
      while(it.hasNext()) {
         b.$plus$eq(it.next());
         b = b;
         it = it;
      }

      return (SeqOps)b.result();
   }

   private final SeqOps subr$1(final Iterator it, final Builder b) {
      while(it.hasNext()) {
         b.$plus$eq(this.scalar().negate(it.next()));
         b = b;
         it = it;
      }

      return (SeqOps)b.result();
   }

   private final SeqOps sub2$1(final Iterator xi, final Iterator yi, final Builder b) {
      while(true) {
         SeqOps var10000;
         if (!xi.hasNext()) {
            var10000 = this.subr$1(yi, b);
         } else {
            if (yi.hasNext()) {
               b.$plus$eq(this.scalar().minus(xi.next(), yi.next()));
               b = b;
               yi = yi;
               xi = xi;
               continue;
            }

            var10000 = this.subl$1(xi, b);
         }

         return var10000;
      }
   }

   public SeqCModule(final CommutativeRing scalar, final Factory cbf) {
      this.scalar = scalar;
      this.cbf = cbf;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      CModule.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
