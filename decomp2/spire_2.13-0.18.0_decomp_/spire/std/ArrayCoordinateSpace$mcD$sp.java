package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.algebra.CModule$mcD$sp;
import spire.algebra.CoordinateSpace$mcD$sp;
import spire.algebra.InnerProductSpace$mcD$sp;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace$mcD$sp;

public class ArrayCoordinateSpace$mcD$sp extends ArrayCoordinateSpace implements CoordinateSpace$mcD$sp {
   private static final long serialVersionUID = 0L;
   public final Field scalar$mcD$sp;
   private final ClassTag evidence$35;

   public double _x(final Object v) {
      return CoordinateSpace$mcD$sp._x$(this, v);
   }

   public double _x$mcD$sp(final Object v) {
      return CoordinateSpace$mcD$sp._x$mcD$sp$(this, v);
   }

   public double _y(final Object v) {
      return CoordinateSpace$mcD$sp._y$(this, v);
   }

   public double _y$mcD$sp(final Object v) {
      return CoordinateSpace$mcD$sp._y$mcD$sp$(this, v);
   }

   public double _z(final Object v) {
      return CoordinateSpace$mcD$sp._z$(this, v);
   }

   public double _z$mcD$sp(final Object v) {
      return CoordinateSpace$mcD$sp._z$mcD$sp$(this, v);
   }

   public NormedVectorSpace normed(final NRoot ev) {
      return InnerProductSpace$mcD$sp.normed$(this, ev);
   }

   public NormedVectorSpace normed$mcD$sp(final NRoot ev) {
      return InnerProductSpace$mcD$sp.normed$mcD$sp$(this, ev);
   }

   public Object divr(final Object v, final double f) {
      return VectorSpace$mcD$sp.divr$(this, v, f);
   }

   public Object divr$mcD$sp(final Object v, final double f) {
      return VectorSpace$mcD$sp.divr$mcD$sp$(this, v, f);
   }

   public Object timesr(final Object v, final double r) {
      return CModule$mcD$sp.timesr$(this, v, r);
   }

   public Object timesr$mcD$sp(final Object v, final double r) {
      return CModule$mcD$sp.timesr$mcD$sp$(this, v, r);
   }

   public Field scalar$mcD$sp() {
      return this.scalar$mcD$sp;
   }

   public Field scalar() {
      return this.scalar$mcD$sp();
   }

   public double[] zero() {
      return this.zero$mcD$sp();
   }

   public double[] zero$mcD$sp() {
      return (double[])this.spire$std$ArrayCoordinateSpace$$evidence$35.newArray(0);
   }

   public double[] negate(final double[] x) {
      return this.negate$mcD$sp(x);
   }

   public double[] negate$mcD$sp(final double[] x) {
      return ArraySupport$.MODULE$.negate$mDc$sp(x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public double[] plus(final double[] x, final double[] y) {
      return this.plus$mcD$sp(x, y);
   }

   public double[] plus$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.plus$mDc$sp(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public double[] minus(final double[] x, final double[] y) {
      return this.minus$mcD$sp(x, y);
   }

   public double[] minus$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.minus$mDc$sp(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public double[] timesl(final double r, final double[] x) {
      return this.timesl$mcD$sp(r, x);
   }

   public double[] timesl$mcD$sp(final double r, final double[] x) {
      return ArraySupport$.MODULE$.timesl$mDc$sp(r, x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public double dot(final double[] x, final double[] y) {
      return this.dot$mcD$sp(x, y);
   }

   public double dot$mcD$sp(final double[] x, final double[] y) {
      return ArraySupport$.MODULE$.dot$mDc$sp(x, y, this.scalar());
   }

   public double coord(final double[] v, final int i) {
      return this.coord$mcD$sp(v, i);
   }

   public double coord$mcD$sp(final double[] v, final int i) {
      return v[i];
   }

   public double[] axis(final int i) {
      return this.axis$mcD$sp(i);
   }

   public double[] axis$mcD$sp(final int i) {
      return ArraySupport$.MODULE$.axis$mDc$sp(this.dimensions(), i, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public boolean specInstance$() {
      return true;
   }

   public ArrayCoordinateSpace$mcD$sp(final int dimensions, final ClassTag evidence$35, final Field scalar$mcD$sp) {
      super(dimensions, evidence$35, (Field)null);
      this.scalar$mcD$sp = scalar$mcD$sp;
      this.evidence$35 = evidence$35;
   }
}
