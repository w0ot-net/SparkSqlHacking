package spire.std;

import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.Field;
import cats.kernel.CommutativeGroup;
import cats.kernel.Eq;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.CModule;
import spire.algebra.InnerProductSpace;
import spire.algebra.LeftModule;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a\u0001B\u0006\r\rEA\u0001\"\u0015\u0001\u0003\u0004\u0003\u0006YA\u0015\u0005\t3\u0002\u0011\u0019\u0011)A\u00065\")A\r\u0001C\u0001K\")1\u000e\u0001C\u0001Y\")Q\u000e\u0001C\u0001]\")q\u000e\u0001C\u0001a\")1\u000f\u0001C\u0001i\")\u0001\u0010\u0001C!s\")A\u0010\u0001C\u0001{\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015!AF!se\u0006L\u0018J\u001c8feB\u0013x\u000eZ;diN\u0003\u0018mY3\u000b\u00055q\u0011aA:uI*\tq\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005I!3\u0003\u0002\u0001\u00143)\u0003\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007\u0003\u0002\u000e\u001e?\tj\u0011a\u0007\u0006\u000399\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u001f7\t\t\u0012J\u001c8feB\u0013x\u000eZ;diN\u0003\u0018mY3\u0011\u0007Q\u0001#%\u0003\u0002\"+\t)\u0011I\u001d:bsB\u00111\u0005\n\u0007\u0001\t%)\u0003\u0001)A\u0001\u0002\u000b\u0007aEA\u0001B#\t9#\u0006\u0005\u0002\u0015Q%\u0011\u0011&\u0006\u0002\b\u001d>$\b.\u001b8h!\t!2&\u0003\u0002-+\t\u0019\u0011I\\=)\r\u0011r\u0013g\u000f!F!\t!r&\u0003\u00021+\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019#gM\u001b5\u001d\t!2'\u0003\u00025+\u0005\u0019\u0011J\u001c;2\t\u00112$H\u0006\b\u0003oij\u0011\u0001\u000f\u0006\u0003sA\ta\u0001\u0010:p_Rt\u0014\"\u0001\f2\u000b\rbTh\u0010 \u000f\u0005Qi\u0014B\u0001 \u0016\u0003\u00151En\\1uc\u0011!cG\u000f\f2\u000b\r\n%\tR\"\u000f\u0005Q\u0011\u0015BA\"\u0016\u0003\u0011auN\\42\t\u00112$HF\u0019\u0006G\u0019;\u0015\n\u0013\b\u0003)\u001dK!\u0001S\u000b\u0002\r\u0011{WO\u00197fc\u0011!cG\u000f\f\u0011\u0005-seB\u0001\u001cM\u0013\tiU#A\u0004qC\u000e\\\u0017mZ3\n\u0005=\u0003&\u0001D*fe&\fG.\u001b>bE2,'BA'\u0016\u0003-)g/\u001b3f]\u000e,GeM\u0019\u0011\u0007M3&E\u0004\u0002U+6\ta\"\u0003\u0002N\u001d%\u0011q\u000b\u0017\u0002\t\u00072\f7o\u001d+bO*\u0011QJD\u0001\fKZLG-\u001a8dK\u0012\u001a$\u0007E\u0002\\C\nr!\u0001\u00181\u000f\u0005u{fBA\u001c_\u0013\u0005y\u0011B\u0001\u000f\u000f\u0013\ti5$\u0003\u0002cG\n)a)[3mI*\u0011QjG\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0019$2aZ5k!\rA\u0007AI\u0007\u0002\u0019!)\u0011k\u0001a\u0002%\")\u0011l\u0001a\u00025\u000611oY1mCJ,\u0012AW\u0001\u0005u\u0016\u0014x.F\u0001 \u0003\u0019qWmZ1uKR\u0011q$\u001d\u0005\u0006e\u001a\u0001\raH\u0001\u0002q\u0006!\u0001\u000f\\;t)\ryRO\u001e\u0005\u0006e\u001e\u0001\ra\b\u0005\u0006o\u001e\u0001\raH\u0001\u0002s\u0006)Q.\u001b8vgR\u0019qD_>\t\u000bID\u0001\u0019A\u0010\t\u000b]D\u0001\u0019A\u0010\u0002\rQLW.Z:m)\u0011yb0!\u0001\t\u000b}L\u0001\u0019\u0001\u0012\u0002\u0003IDQA]\u0005A\u0002}\t1\u0001Z8u)\u0015\u0011\u0013qAA\u0005\u0011\u0015\u0011(\u00021\u0001 \u0011\u00159(\u00021\u0001 Q\u001d\u0001\u0011QBA\n\u0003+\u00012\u0001FA\b\u0013\r\t\t\"\u0006\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001"
)
public class ArrayInnerProductSpace implements InnerProductSpace {
   private static final long serialVersionUID = 0L;
   public final ClassTag spire$std$ArrayInnerProductSpace$$evidence$31;
   public final Field evidence$32;

   public double dot$mcD$sp(final Object v, final Object w) {
      return InnerProductSpace.dot$mcD$sp$(this, v, w);
   }

   public float dot$mcF$sp(final Object v, final Object w) {
      return InnerProductSpace.dot$mcF$sp$(this, v, w);
   }

   public int dot$mcI$sp(final Object v, final Object w) {
      return InnerProductSpace.dot$mcI$sp$(this, v, w);
   }

   public long dot$mcJ$sp(final Object v, final Object w) {
      return InnerProductSpace.dot$mcJ$sp$(this, v, w);
   }

   public NormedVectorSpace normed(final NRoot ev) {
      return InnerProductSpace.normed$(this, ev);
   }

   public NormedVectorSpace normed$mcD$sp(final NRoot ev) {
      return InnerProductSpace.normed$mcD$sp$(this, ev);
   }

   public NormedVectorSpace normed$mcF$sp(final NRoot ev) {
      return InnerProductSpace.normed$mcF$sp$(this, ev);
   }

   public NormedVectorSpace normed$mcI$sp(final NRoot ev) {
      return InnerProductSpace.normed$mcI$sp$(this, ev);
   }

   public NormedVectorSpace normed$mcJ$sp(final NRoot ev) {
      return InnerProductSpace.normed$mcJ$sp$(this, ev);
   }

   public Object divr(final Object v, final Object f) {
      return VectorSpace.divr$(this, v, f);
   }

   public Object divr$mcD$sp(final Object v, final double f) {
      return VectorSpace.divr$mcD$sp$(this, v, f);
   }

   public Object divr$mcF$sp(final Object v, final float f) {
      return VectorSpace.divr$mcF$sp$(this, v, f);
   }

   public Object divr$mcI$sp(final Object v, final int f) {
      return VectorSpace.divr$mcI$sp$(this, v, f);
   }

   public Object divr$mcJ$sp(final Object v, final long f) {
      return VectorSpace.divr$mcJ$sp$(this, v, f);
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

   public Field scalar() {
      return spire.algebra.package$.MODULE$.Field().apply(this.evidence$32);
   }

   public Object zero() {
      return this.spire$std$ArrayInnerProductSpace$$evidence$31.newArray(0);
   }

   public Object negate(final Object x) {
      return ArraySupport$.MODULE$.negate(x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32);
   }

   public Object plus(final Object x, final Object y) {
      return ArraySupport$.MODULE$.plus(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32);
   }

   public Object minus(final Object x, final Object y) {
      return ArraySupport$.MODULE$.minus(x, y, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32);
   }

   public Object timesl(final Object r, final Object x) {
      return ArraySupport$.MODULE$.timesl(r, x, this.spire$std$ArrayInnerProductSpace$$evidence$31, this.evidence$32);
   }

   public Object dot(final Object x, final Object y) {
      return ArraySupport$.MODULE$.dot(x, y, this.evidence$32);
   }

   public Field scalar$mcD$sp() {
      return this.scalar();
   }

   public Field scalar$mcF$sp() {
      return this.scalar();
   }

   public Field scalar$mcI$sp() {
      return this.scalar();
   }

   public Field scalar$mcJ$sp() {
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

   public double dot$mcD$sp(final double[] x, final double[] y) {
      return BoxesRunTime.unboxToDouble(this.dot(x, y));
   }

   public float dot$mcF$sp(final float[] x, final float[] y) {
      return BoxesRunTime.unboxToFloat(this.dot(x, y));
   }

   public int dot$mcI$sp(final int[] x, final int[] y) {
      return BoxesRunTime.unboxToInt(this.dot(x, y));
   }

   public long dot$mcJ$sp(final long[] x, final long[] y) {
      return BoxesRunTime.unboxToLong(this.dot(x, y));
   }

   public ArrayInnerProductSpace(final ClassTag evidence$31, final Field evidence$32) {
      this.spire$std$ArrayInnerProductSpace$$evidence$31 = evidence$31;
      this.evidence$32 = evidence$32;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      CModule.$init$(this);
      VectorSpace.$init$(this);
      InnerProductSpace.$init$(this);
   }
}
