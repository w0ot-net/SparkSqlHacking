package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import spire.algebra.CModule$mcF$sp;
import spire.algebra.CoordinateSpace$mcF$sp;
import spire.algebra.InnerProductSpace$mcF$sp;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;
import spire.algebra.VectorSpace$mcF$sp;

public class ArrayCoordinateSpace$mcF$sp extends ArrayCoordinateSpace implements CoordinateSpace$mcF$sp {
   private static final long serialVersionUID = 0L;
   public final Field scalar$mcF$sp;
   private final ClassTag evidence$35;

   public float _x(final Object v) {
      return CoordinateSpace$mcF$sp._x$(this, v);
   }

   public float _x$mcF$sp(final Object v) {
      return CoordinateSpace$mcF$sp._x$mcF$sp$(this, v);
   }

   public float _y(final Object v) {
      return CoordinateSpace$mcF$sp._y$(this, v);
   }

   public float _y$mcF$sp(final Object v) {
      return CoordinateSpace$mcF$sp._y$mcF$sp$(this, v);
   }

   public float _z(final Object v) {
      return CoordinateSpace$mcF$sp._z$(this, v);
   }

   public float _z$mcF$sp(final Object v) {
      return CoordinateSpace$mcF$sp._z$mcF$sp$(this, v);
   }

   public NormedVectorSpace normed(final NRoot ev) {
      return InnerProductSpace$mcF$sp.normed$(this, ev);
   }

   public NormedVectorSpace normed$mcF$sp(final NRoot ev) {
      return InnerProductSpace$mcF$sp.normed$mcF$sp$(this, ev);
   }

   public Object divr(final Object v, final float f) {
      return VectorSpace$mcF$sp.divr$(this, v, f);
   }

   public Object divr$mcF$sp(final Object v, final float f) {
      return VectorSpace$mcF$sp.divr$mcF$sp$(this, v, f);
   }

   public Object timesr(final Object v, final float r) {
      return CModule$mcF$sp.timesr$(this, v, r);
   }

   public Object timesr$mcF$sp(final Object v, final float r) {
      return CModule$mcF$sp.timesr$mcF$sp$(this, v, r);
   }

   public Field scalar$mcF$sp() {
      return this.scalar$mcF$sp;
   }

   public Field scalar() {
      return this.scalar$mcF$sp();
   }

   public float[] zero() {
      return this.zero$mcF$sp();
   }

   public float[] zero$mcF$sp() {
      return (float[])this.spire$std$ArrayCoordinateSpace$$evidence$35.newArray(0);
   }

   public float[] negate(final float[] x) {
      return this.negate$mcF$sp(x);
   }

   public float[] negate$mcF$sp(final float[] x) {
      return ArraySupport$.MODULE$.negate$mFc$sp(x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public float[] plus(final float[] x, final float[] y) {
      return this.plus$mcF$sp(x, y);
   }

   public float[] plus$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.plus$mFc$sp(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public float[] minus(final float[] x, final float[] y) {
      return this.minus$mcF$sp(x, y);
   }

   public float[] minus$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.minus$mFc$sp(x, y, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public float[] timesl(final float r, final float[] x) {
      return this.timesl$mcF$sp(r, x);
   }

   public float[] timesl$mcF$sp(final float r, final float[] x) {
      return ArraySupport$.MODULE$.timesl$mFc$sp(r, x, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public float dot(final float[] x, final float[] y) {
      return this.dot$mcF$sp(x, y);
   }

   public float dot$mcF$sp(final float[] x, final float[] y) {
      return ArraySupport$.MODULE$.dot$mFc$sp(x, y, this.scalar());
   }

   public float coord(final float[] v, final int i) {
      return this.coord$mcF$sp(v, i);
   }

   public float coord$mcF$sp(final float[] v, final int i) {
      return v[i];
   }

   public float[] axis(final int i) {
      return this.axis$mcF$sp(i);
   }

   public float[] axis$mcF$sp(final int i) {
      return ArraySupport$.MODULE$.axis$mFc$sp(this.dimensions(), i, this.spire$std$ArrayCoordinateSpace$$evidence$35, this.scalar());
   }

   public boolean specInstance$() {
      return true;
   }

   public ArrayCoordinateSpace$mcF$sp(final int dimensions, final ClassTag evidence$35, final Field scalar$mcF$sp) {
      super(dimensions, evidence$35, (Field)null);
      this.scalar$mcF$sp = scalar$mcF$sp;
      this.evidence$35 = evidence$35;
   }
}
