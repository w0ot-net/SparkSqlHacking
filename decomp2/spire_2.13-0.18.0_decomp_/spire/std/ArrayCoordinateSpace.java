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
import scala.collection.immutable.Vector;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;
import spire.algebra.CModule;
import spire.algebra.CoordinateSpace;
import spire.algebra.InnerProductSpace;
import spire.algebra.LeftModule;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b\u0001B\b\u0011\u0001UA\u0001\"\u0016\u0001\u0003\u0006\u0004%)A\u0016\u0005\t5\u0002\u0011\t\u0011)A\u0007/\"A1\f\u0001B\u0002B\u0003-A\f\u0003\u0005d\u0001\t\u0015\r\u0011b\u0001e\u0011!y\u0007A!A!\u0002\u0013)\u0007\"\u00029\u0001\t\u0003\t\b\"\u0002=\u0001\t\u0003I\b\"\u0002>\u0001\t\u0003Y\b\"\u0002@\u0001\t\u0003y\bbBA\u0004\u0001\u0011\u0005\u0013\u0011\u0002\u0005\b\u0003\u001f\u0001A\u0011AA\t\u0011\u001d\tI\u0002\u0001C!\u00037Aq!!\t\u0001\t\u0003\t\u0019\u0003C\u0004\u0002.\u0001!\t!a\f\u0003)\u0005\u0013(/Y=D_>\u0014H-\u001b8bi\u0016\u001c\u0006/Y2f\u0015\t\t\"#A\u0002ti\u0012T\u0011aE\u0001\u0006gBL'/Z\u0002\u0001+\t1\u0002f\u0005\u0003\u0001/uq\u0005C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g\r\u0005\u0003\u001fC\r2S\"A\u0010\u000b\u0005\u0001\u0012\u0012aB1mO\u0016\u0014'/Y\u0005\u0003E}\u0011qbQ8pe\u0012Lg.\u0019;f'B\f7-\u001a\t\u00041\u00112\u0013BA\u0013\u001a\u0005\u0015\t%O]1z!\t9\u0003\u0006\u0004\u0001\u0005\u0013%\u0002\u0001\u0015!A\u0001\u0006\u0004Q#!A!\u0012\u0005-r\u0003C\u0001\r-\u0013\ti\u0013DA\u0004O_RD\u0017N\\4\u0011\u0005ay\u0013B\u0001\u0019\u001a\u0005\r\te.\u001f\u0015\u0007QI*t\bR%\u0011\u0005a\u0019\u0014B\u0001\u001b\u001a\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r2t'\u000f\u001d\u000f\u0005a9\u0014B\u0001\u001d\u001a\u0003\rIe\u000e^\u0019\u0005Iir$D\u0004\u0002<}5\tAH\u0003\u0002>)\u00051AH]8pizJ\u0011AG\u0019\u0006G\u0001\u000b5I\u0011\b\u00031\u0005K!AQ\r\u0002\t1{gnZ\u0019\u0005Iir$$M\u0003$\u000b\u001aCuI\u0004\u0002\u0019\r&\u0011q)G\u0001\u0006\r2|\u0017\r^\u0019\u0005Iir$$M\u0003$\u0015.kEJ\u0004\u0002\u0019\u0017&\u0011A*G\u0001\u0007\t>,(\r\\32\t\u0011RdH\u0007\t\u0003\u001fJs!A\u000f)\n\u0005EK\u0012a\u00029bG.\fw-Z\u0005\u0003'R\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!U\r\u0002\u0015\u0011LW.\u001a8tS>t7/F\u0001X!\tA\u0002,\u0003\u0002Z3\t\u0019\u0011J\u001c;\u0002\u0017\u0011LW.\u001a8tS>t7\u000fI\u0001\fKZLG-\u001a8dK\u0012\u001aT\u0007E\u0002^A\u001ar!AX0\u000e\u0003II!!\u0015\n\n\u0005\u0005\u0014'\u0001C\"mCN\u001cH+Y4\u000b\u0005E\u0013\u0012AB:dC2\f'/F\u0001f!\r1GN\n\b\u0003O.t!\u0001\u001b6\u000f\u0005mJ\u0017\"A\n\n\u0005\u0001\u0012\u0012BA) \u0013\tigNA\u0003GS\u0016dGM\u0003\u0002R?\u000591oY1mCJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002soR\u00191/\u001e<\u0011\u0007Q\u0004a%D\u0001\u0011\u0011\u0015Yf\u0001q\u0001]\u0011\u0015\u0019g\u0001q\u0001f\u0011\u0015)f\u00011\u0001X\u0003\u0011QXM]8\u0016\u0003\r\naA\\3hCR,GCA\u0012}\u0011\u0015i\b\u00021\u0001$\u0003\u0005A\u0018\u0001\u00029mkN$RaIA\u0001\u0003\u0007AQ!`\u0005A\u0002\rBa!!\u0002\n\u0001\u0004\u0019\u0013!A=\u0002\u000b5Lg.^:\u0015\u000b\r\nY!!\u0004\t\u000buT\u0001\u0019A\u0012\t\r\u0005\u0015!\u00021\u0001$\u0003\u0019!\u0018.\\3tYR)1%a\u0005\u0002\u0018!1\u0011QC\u0006A\u0002\u0019\n\u0011A\u001d\u0005\u0006{.\u0001\raI\u0001\u0004I>$H#\u0002\u0014\u0002\u001e\u0005}\u0001\"B?\r\u0001\u0004\u0019\u0003BBA\u0003\u0019\u0001\u00071%A\u0003d_>\u0014H\rF\u0003'\u0003K\tI\u0003\u0003\u0004\u0002(5\u0001\raI\u0001\u0002m\"1\u00111F\u0007A\u0002]\u000b\u0011![\u0001\u0005CbL7\u000fF\u0002$\u0003cAa!a\u000b\u000f\u0001\u00049\u0006f\u0002\u0001\u00026\u0005m\u0012Q\b\t\u00041\u0005]\u0012bAA\u001d3\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0001\u0001"
)
public class ArrayCoordinateSpace implements CoordinateSpace {
   private static final long serialVersionUID = 0L;
   private final int dimensions;
   public final ClassTag spire$std$ArrayCoordinateSpace$$evidence$35;
   public final Field scalar;

   public double coord$mcD$sp(final Object v, final int i) {
      return CoordinateSpace.coord$mcD$sp$(this, v, i);
   }

   public float coord$mcF$sp(final Object v, final int i) {
      return CoordinateSpace.coord$mcF$sp$(this, v, i);
   }

   public Object _x(final Object v) {
      return CoordinateSpace._x$(this, v);
   }

   public double _x$mcD$sp(final Object v) {
      return CoordinateSpace._x$mcD$sp$(this, v);
   }

   public float _x$mcF$sp(final Object v) {
      return CoordinateSpace._x$mcF$sp$(this, v);
   }

   public Object _y(final Object v) {
      return CoordinateSpace._y$(this, v);
   }

   public double _y$mcD$sp(final Object v) {
      return CoordinateSpace._y$mcD$sp$(this, v);
   }

   public float _y$mcF$sp(final Object v) {
      return CoordinateSpace._y$mcF$sp$(this, v);
   }

   public Object _z(final Object v) {
      return CoordinateSpace._z$(this, v);
   }

   public double _z$mcD$sp(final Object v) {
      return CoordinateSpace._z$mcD$sp$(this, v);
   }

   public float _z$mcF$sp(final Object v) {
      return CoordinateSpace._z$mcF$sp$(this, v);
   }

   public Vector basis() {
      return CoordinateSpace.basis$(this);
   }

   public double dot$mcD$sp(final Object v, final Object w) {
      return CoordinateSpace.dot$mcD$sp$(this, v, w);
   }

   public float dot$mcF$sp(final Object v, final Object w) {
      return CoordinateSpace.dot$mcF$sp$(this, v, w);
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

   public final int dimensions() {
      return this.dimensions;
   }

   public Field scalar() {
      return this.scalar;
   }

   public Object zero() {
      return this.spire$std$ArrayCoordinateSpace$$evidence$35.newArray(0);
   }

   public Object negate(final Object x) {
      return ArraySupport$.MODULE$.negate(x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public Object plus(final Object x, final Object y) {
      return ArraySupport$.MODULE$.plus(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public Object minus(final Object x, final Object y) {
      return ArraySupport$.MODULE$.minus(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public Object timesl(final Object r, final Object x) {
      return ArraySupport$.MODULE$.timesl(r, x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public Object dot(final Object x, final Object y) {
      return ArraySupport$.MODULE$.dot(x, y, this.scalar());
   }

   public Object coord(final Object v, final int i) {
      return .MODULE$.array_apply(v, i);
   }

   public Object axis(final int i) {
      return ArraySupport$.MODULE$.axis(this.dimensions(), i, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
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

   public double coord$mcD$sp(final double[] v, final int i) {
      return BoxesRunTime.unboxToDouble(this.coord(v, i));
   }

   public float coord$mcF$sp(final float[] v, final int i) {
      return BoxesRunTime.unboxToFloat(this.coord(v, i));
   }

   public int coord$mcI$sp(final int[] v, final int i) {
      return BoxesRunTime.unboxToInt(this.coord(v, i));
   }

   public long coord$mcJ$sp(final long[] v, final int i) {
      return BoxesRunTime.unboxToLong(this.coord(v, i));
   }

   public double[] axis$mcD$sp(final int i) {
      return (double[])this.axis(i);
   }

   public float[] axis$mcF$sp(final int i) {
      return (float[])this.axis(i);
   }

   public int[] axis$mcI$sp(final int i) {
      return (int[])this.axis(i);
   }

   public long[] axis$mcJ$sp(final int i) {
      return (long[])this.axis(i);
   }

   public boolean specInstance$() {
      return false;
   }

   public ArrayCoordinateSpace(final int dimensions, final ClassTag evidence$35, final Field scalar) {
      this.dimensions = dimensions;
      this.spire$std$ArrayCoordinateSpace$$evidence$35 = evidence$35;
      this.scalar = scalar;
      AdditiveSemigroup.$init$(this);
      AdditiveMonoid.$init$(this);
      AdditiveGroup.$init$(this);
      AdditiveCommutativeSemigroup.$init$(this);
      AdditiveCommutativeMonoid.$init$(this);
      AdditiveCommutativeGroup.$init$(this);
      CModule.$init$(this);
      VectorSpace.$init$(this);
      InnerProductSpace.$init$(this);
      CoordinateSpace.$init$(this);
   }
}
