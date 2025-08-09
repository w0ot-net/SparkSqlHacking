package breeze.linalg;

import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanMapValues;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.ArrowAssoc.;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public class SliceMatrix$mcIID$sp extends SliceMatrix implements Matrix$mcD$sp {
   public final Tensor tensor$mcIID$sp;
   public final Semiring evidence$1$mcD$sp;
   private final ClassTag evidence$2;

   public final double apply(final Tuple2 i) {
      return Matrix$mcD$sp.apply$(this, i);
   }

   public final double apply$mcD$sp(final Tuple2 i) {
      return Matrix$mcD$sp.apply$mcD$sp$(this, i);
   }

   public final void update(final Tuple2 i, final double e) {
      Matrix$mcD$sp.update$(this, i, e);
   }

   public final void update$mcD$sp(final Tuple2 i, final double e) {
      Matrix$mcD$sp.update$mcD$sp$(this, i, e);
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return Matrix$mcD$sp.toDenseMatrix$(this, cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcD$sp(final ClassTag cm, final Zero zero) {
      return Matrix$mcD$sp.toDenseMatrix$mcD$sp$(this, cm, zero);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcD$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcD$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcD$sp.map$mcD$sp$(this, fn, canMapValues);
   }

   public Tensor tensor$mcIID$sp() {
      return this.tensor$mcIID$sp;
   }

   public Tensor tensor() {
      return this.tensor$mcIID$sp();
   }

   public double apply(final int i, final int j) {
      return this.apply$mcD$sp(i, j);
   }

   public double apply$mcD$sp(final int i, final int j) {
      return BoxesRunTime.unboxToDouble(this.tensor().apply(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.slice1().apply(i)), this.slice2().apply(j))));
   }

   public void update(final int i, final int j, final double e) {
      this.update$mcD$sp(i, j, e);
   }

   public void update$mcD$sp(final int i, final int j, final double e) {
      this.tensor().update(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.slice1().apply(i)), this.slice2().apply(j)), BoxesRunTime.boxToDouble(e));
   }

   public SliceMatrix repr() {
      return this.repr$mcIID$sp();
   }

   public SliceMatrix repr$mcIID$sp() {
      return this;
   }

   public Matrix copy() {
      return this.copy$mcD$sp();
   }

   public Matrix copy$mcD$sp() {
      Object var10000;
      if (this.rows() == 0) {
         var10000 = Matrix$.MODULE$.zeroRows(this.cols(), this.breeze$linalg$SliceMatrix$$evidence$2);
      } else if (this.cols() == 0) {
         var10000 = Matrix$.MODULE$.zeroCols(this.rows(), this.breeze$linalg$SliceMatrix$$evidence$2);
      } else {
         DenseMatrix result = new DenseMatrix$mcD$sp(this.rows(), this.cols(), (double[])this.breeze$linalg$SliceMatrix$$evidence$2.newArray(this.size()));
         result.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DM_M$mDc$sp());
         var10000 = result;
      }

      return (Matrix)var10000;
   }

   public Vector flatten(final View view) {
      return this.flatten$mcD$sp(view);
   }

   public Vector flatten$mcD$sp(final View view) {
      if (View.Require$.MODULE$.equals(view)) {
         throw new UnsupportedOperationException("Cannot make Vector as view of SliceMatrix.");
      } else {
         Vector var2;
         if (!View.Copy$.MODULE$.equals(view)) {
            if (!View.Prefer$.MODULE$.equals(view)) {
               throw new MatchError(view);
            }

            var2 = this.flatten$mcD$sp(View.Copy$.MODULE$);
         } else {
            VectorBuilder vb = new VectorBuilder$mcD$sp(this.rows() * this.cols(), this.activeSize(), this.evidence$1$mcD$sp, this.breeze$linalg$SliceMatrix$$evidence$2);
            Iterator ai = this.activeIterator();

            while(ai.hasNext()) {
               Tuple2 var8 = (Tuple2)ai.next();
               if (var8 == null) {
                  throw new MatchError(var8);
               }

               Tuple2 var9 = (Tuple2)var8._1();
               double v = var8._2$mcD$sp();
               if (var9 == null) {
                  throw new MatchError(var8);
               }

               int r = var9._1$mcI$sp();
               int c = var9._2$mcI$sp();
               Tuple3 var3 = new Tuple3(BoxesRunTime.boxToInteger(r), BoxesRunTime.boxToInteger(c), BoxesRunTime.boxToDouble(v));
               int r = BoxesRunTime.unboxToInt(var3._1());
               int c = BoxesRunTime.unboxToInt(var3._2());
               double v = BoxesRunTime.unboxToDouble(var3._3());
               vb.add$mcD$sp(c * this.rows() + r, v);
            }

            var2 = vb.toVector$mcD$sp();
         }

         return var2;
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public SliceMatrix$mcIID$sp(final Tensor tensor$mcIID$sp, final IndexedSeq slice1, final IndexedSeq slice2, final Semiring evidence$1$mcD$sp, final ClassTag evidence$2) {
      super((Tensor)null, slice1, slice2, evidence$1$mcD$sp, evidence$2);
      this.tensor$mcIID$sp = tensor$mcIID$sp;
      this.evidence$1$mcD$sp = evidence$1$mcD$sp;
      this.evidence$2 = evidence$2;
   }
}
