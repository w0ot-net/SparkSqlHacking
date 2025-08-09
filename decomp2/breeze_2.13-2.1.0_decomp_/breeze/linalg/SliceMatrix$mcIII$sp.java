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

public class SliceMatrix$mcIII$sp extends SliceMatrix implements Matrix$mcI$sp {
   public final Tensor tensor$mcIII$sp;
   public final Semiring evidence$1$mcI$sp;
   private final ClassTag evidence$2;

   public final int apply(final Tuple2 i) {
      return Matrix$mcI$sp.apply$(this, i);
   }

   public final int apply$mcI$sp(final Tuple2 i) {
      return Matrix$mcI$sp.apply$mcI$sp$(this, i);
   }

   public final void update(final Tuple2 i, final int e) {
      Matrix$mcI$sp.update$(this, i, e);
   }

   public final void update$mcI$sp(final Tuple2 i, final int e) {
      Matrix$mcI$sp.update$mcI$sp$(this, i, e);
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return Matrix$mcI$sp.toDenseMatrix$(this, cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcI$sp(final ClassTag cm, final Zero zero) {
      return Matrix$mcI$sp.toDenseMatrix$mcI$sp$(this, cm, zero);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcI$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcI$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcI$sp.map$mcI$sp$(this, fn, canMapValues);
   }

   public Tensor tensor$mcIII$sp() {
      return this.tensor$mcIII$sp;
   }

   public Tensor tensor() {
      return this.tensor$mcIII$sp();
   }

   public int apply(final int i, final int j) {
      return this.apply$mcI$sp(i, j);
   }

   public int apply$mcI$sp(final int i, final int j) {
      return BoxesRunTime.unboxToInt(this.tensor().apply(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.slice1().apply(i)), this.slice2().apply(j))));
   }

   public void update(final int i, final int j, final int e) {
      this.update$mcI$sp(i, j, e);
   }

   public void update$mcI$sp(final int i, final int j, final int e) {
      this.tensor().update(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.slice1().apply(i)), this.slice2().apply(j)), BoxesRunTime.boxToInteger(e));
   }

   public SliceMatrix repr() {
      return this.repr$mcIII$sp();
   }

   public SliceMatrix repr$mcIII$sp() {
      return this;
   }

   public Matrix copy() {
      return this.copy$mcI$sp();
   }

   public Matrix copy$mcI$sp() {
      Object var10000;
      if (this.rows() == 0) {
         var10000 = Matrix$.MODULE$.zeroRows(this.cols(), this.breeze$linalg$SliceMatrix$$evidence$2);
      } else if (this.cols() == 0) {
         var10000 = Matrix$.MODULE$.zeroCols(this.rows(), this.breeze$linalg$SliceMatrix$$evidence$2);
      } else {
         DenseMatrix result = new DenseMatrix$mcI$sp(this.rows(), this.cols(), (int[])this.breeze$linalg$SliceMatrix$$evidence$2.newArray(this.size()));
         result.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DM_M$mIc$sp());
         var10000 = result;
      }

      return (Matrix)var10000;
   }

   public Vector flatten(final View view) {
      return this.flatten$mcI$sp(view);
   }

   public Vector flatten$mcI$sp(final View view) {
      if (View.Require$.MODULE$.equals(view)) {
         throw new UnsupportedOperationException("Cannot make Vector as view of SliceMatrix.");
      } else {
         Vector var2;
         if (!View.Copy$.MODULE$.equals(view)) {
            if (!View.Prefer$.MODULE$.equals(view)) {
               throw new MatchError(view);
            }

            var2 = this.flatten$mcI$sp(View.Copy$.MODULE$);
         } else {
            VectorBuilder vb = new VectorBuilder$mcI$sp(this.rows() * this.cols(), this.activeSize(), this.evidence$1$mcI$sp, this.breeze$linalg$SliceMatrix$$evidence$2);
            Iterator ai = this.activeIterator();

            while(ai.hasNext()) {
               Tuple2 var8 = (Tuple2)ai.next();
               if (var8 == null) {
                  throw new MatchError(var8);
               }

               Tuple2 var9 = (Tuple2)var8._1();
               int v = var8._2$mcI$sp();
               if (var9 == null) {
                  throw new MatchError(var8);
               }

               int r = var9._1$mcI$sp();
               int c = var9._2$mcI$sp();
               Tuple3 var3 = new Tuple3(BoxesRunTime.boxToInteger(r), BoxesRunTime.boxToInteger(c), BoxesRunTime.boxToInteger(v));
               int r = BoxesRunTime.unboxToInt(var3._1());
               int c = BoxesRunTime.unboxToInt(var3._2());
               int v = BoxesRunTime.unboxToInt(var3._3());
               vb.add$mcI$sp(c * this.rows() + r, v);
            }

            var2 = vb.toVector$mcI$sp();
         }

         return var2;
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public SliceMatrix$mcIII$sp(final Tensor tensor$mcIII$sp, final IndexedSeq slice1, final IndexedSeq slice2, final Semiring evidence$1$mcI$sp, final ClassTag evidence$2) {
      super((Tensor)null, slice1, slice2, evidence$1$mcI$sp, evidence$2);
      this.tensor$mcIII$sp = tensor$mcIII$sp;
      this.evidence$1$mcI$sp = evidence$1$mcI$sp;
      this.evidence$2 = evidence$2;
   }
}
