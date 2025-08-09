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
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.NotGiven;
import spire.algebra.CModule;
import spire.algebra.LeftModule;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a\u0001B\u0006\r\rEA\u0001\"\u0015\u0001\u0003\u0004\u0003\u0006YA\u0015\u0005\t3\u0002\u0011\u0019\u0011)A\u00065\"AA\r\u0001B\u0001B\u0003-Q\rC\u0003l\u0001\u0011\u0005A\u000eC\u0003t\u0001\u0011\u0005A\u000fC\u0003v\u0001\u0011\u0005a\u000fC\u0003x\u0001\u0011\u0005\u0001\u0010C\u0003|\u0001\u0011\u0005A\u0010C\u0004\u0002\u0002\u0001!\t%a\u0001\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f\ta\u0011I\u001d:bs\u000eku\u000eZ;mK*\u0011QBD\u0001\u0004gR$'\"A\b\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011!\u0003J\n\u0005\u0001MI\"\n\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00055uy\"%D\u0001\u001c\u0015\tab\"A\u0004bY\u001e,'M]1\n\u0005yY\"aB\"N_\u0012,H.\u001a\t\u0004)\u0001\u0012\u0013BA\u0011\u0016\u0005\u0015\t%O]1z!\t\u0019C\u0005\u0004\u0001\u0005\u0013\u0015\u0002\u0001\u0015!A\u0001\u0006\u00041#!A!\u0012\u0005\u001dR\u0003C\u0001\u000b)\u0013\tISCA\u0004O_RD\u0017N\\4\u0011\u0005QY\u0013B\u0001\u0017\u0016\u0005\r\te.\u001f\u0015\u0007I9\n4\bQ#\u0011\u0005Qy\u0013B\u0001\u0019\u0016\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r\u00124'\u000e\u001b\u000f\u0005Q\u0019\u0014B\u0001\u001b\u0016\u0003\rIe\u000e^\u0019\u0005IYRdC\u0004\u00028u5\t\u0001H\u0003\u0002:!\u00051AH]8pizJ\u0011AF\u0019\u0006GqjtH\u0010\b\u0003)uJ!AP\u000b\u0002\t1{gnZ\u0019\u0005IYRd#M\u0003$\u0003\n#5I\u0004\u0002\u0015\u0005&\u00111)F\u0001\u0006\r2|\u0017\r^\u0019\u0005IYRd#M\u0003$\r\u001eK\u0005J\u0004\u0002\u0015\u000f&\u0011\u0001*F\u0001\u0007\t>,(\r\\32\t\u00112$H\u0006\t\u0003\u0017:s!A\u000e'\n\u00055+\u0012a\u00029bG.\fw-Z\u0005\u0003\u001fB\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!T\u000b\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#G\u000e\t\u0004'Z\u0013cB\u0001+V\u001b\u0005q\u0011BA'\u000f\u0013\t9\u0006L\u0001\u0005DY\u0006\u001c8\u000fV1h\u0015\tie\"A\u0006fm&$WM\\2fII:\u0004cA.bE9\u0011A\f\u0019\b\u0003;~s!a\u000e0\n\u0003=I!\u0001\b\b\n\u00055[\u0012B\u00012d\u0005\u0015\u0019%+\u001b8h\u0015\ti5$A\u0002omN\u00042\u0001\u00164i\u0013\t9gB\u0001\u0005O_R<\u0015N^3o!\u0011Q\u0012n\b\u0012\n\u0005)\\\"a\u0003,fGR|'o\u00159bG\u0016\fa\u0001P5oSRtD#A7\u0015\t9\u0004\u0018O\u001d\t\u0004_\u0002\u0011S\"\u0001\u0007\t\u000bE#\u00019\u0001*\t\u000be#\u00019\u0001.\t\u000b\u0011$\u00019A3\u0002\rM\u001c\u0017\r\\1s+\u0005Q\u0016\u0001\u0002>fe>,\u0012aH\u0001\u0007]\u0016<\u0017\r^3\u0015\u0005}I\b\"\u0002>\b\u0001\u0004y\u0012!\u0001=\u0002\tAdWo\u001d\u000b\u0004?ut\b\"\u0002>\t\u0001\u0004y\u0002\"B@\t\u0001\u0004y\u0012!A=\u0002\u000b5Lg.^:\u0015\u000b}\t)!a\u0002\t\u000biL\u0001\u0019A\u0010\t\u000b}L\u0001\u0019A\u0010\u0002\rQLW.Z:m)\u0015y\u0012QBA\t\u0011\u0019\tyA\u0003a\u0001E\u0005\t!\u000fC\u0003{\u0015\u0001\u0007q\u0004K\u0004\u0001\u0003+\tY\"!\b\u0011\u0007Q\t9\"C\u0002\u0002\u001aU\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0001\u0001"
)
public class ArrayCModule implements CModule {
   private static final long serialVersionUID = 0L;
   public final ClassTag spire$std$ArrayCModule$$evidence$26;
   public final CommutativeRing evidence$27;

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
      return spire.algebra.package$.MODULE$.CRing().apply(this.evidence$27);
   }

   public Object zero() {
      return this.spire$std$ArrayCModule$$evidence$26.newArray(0);
   }

   public Object negate(final Object x) {
      return ArraySupport$.MODULE$.negate(x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27);
   }

   public Object plus(final Object x, final Object y) {
      return ArraySupport$.MODULE$.plus(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27);
   }

   public Object minus(final Object x, final Object y) {
      return ArraySupport$.MODULE$.minus(x, y, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27);
   }

   public Object timesl(final Object r, final Object x) {
      return ArraySupport$.MODULE$.timesl(r, x, this.spire$std$ArrayCModule$$evidence$26, this.evidence$27);
   }

   public CommutativeRing scalar$mcD$sp() {
      return this.scalar();
   }

   public CommutativeRing scalar$mcF$sp() {
      return this.scalar();
   }

   public CommutativeRing scalar$mcI$sp() {
      return this.scalar();
   }

   public CommutativeRing scalar$mcJ$sp() {
      return this.scalar();
   }

   public double[] zero$mcD$sp() {
      return (double[])this.zero();
   }

   public float[] zero$mcF$sp() {
      return (float[])this.zero();
   }

   public int[] zero$mcI$sp() {
      return (int[])this.zero();
   }

   public long[] zero$mcJ$sp() {
      return (long[])this.zero();
   }

   public double[] negate$mcD$sp(final double[] x) {
      return (double[])this.negate(x);
   }

   public float[] negate$mcF$sp(final float[] x) {
      return (float[])this.negate(x);
   }

   public int[] negate$mcI$sp(final int[] x) {
      return (int[])this.negate(x);
   }

   public long[] negate$mcJ$sp(final long[] x) {
      return (long[])this.negate(x);
   }

   public double[] plus$mcD$sp(final double[] x, final double[] y) {
      return (double[])this.plus(x, y);
   }

   public float[] plus$mcF$sp(final float[] x, final float[] y) {
      return (float[])this.plus(x, y);
   }

   public int[] plus$mcI$sp(final int[] x, final int[] y) {
      return (int[])this.plus(x, y);
   }

   public long[] plus$mcJ$sp(final long[] x, final long[] y) {
      return (long[])this.plus(x, y);
   }

   public double[] minus$mcD$sp(final double[] x, final double[] y) {
      return (double[])this.minus(x, y);
   }

   public float[] minus$mcF$sp(final float[] x, final float[] y) {
      return (float[])this.minus(x, y);
   }

   public int[] minus$mcI$sp(final int[] x, final int[] y) {
      return (int[])this.minus(x, y);
   }

   public long[] minus$mcJ$sp(final long[] x, final long[] y) {
      return (long[])this.minus(x, y);
   }

   public double[] timesl$mcD$sp(final double r, final double[] x) {
      return (double[])this.timesl(BoxesRunTime.boxToDouble(r), x);
   }

   public float[] timesl$mcF$sp(final float r, final float[] x) {
      return (float[])this.timesl(BoxesRunTime.boxToFloat(r), x);
   }

   public int[] timesl$mcI$sp(final int r, final int[] x) {
      return (int[])this.timesl(BoxesRunTime.boxToInteger(r), x);
   }

   public long[] timesl$mcJ$sp(final long r, final long[] x) {
      return (long[])this.timesl(BoxesRunTime.boxToLong(r), x);
   }

   public ArrayCModule(final ClassTag evidence$26, final CommutativeRing evidence$27, final NotGiven nvs) {
      this.spire$std$ArrayCModule$$evidence$26 = evidence$26;
      this.evidence$27 = evidence$27;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      CModule.$init$(this);
   }
}
