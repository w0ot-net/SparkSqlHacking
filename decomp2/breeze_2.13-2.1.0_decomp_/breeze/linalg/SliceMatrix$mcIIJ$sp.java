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

public class SliceMatrix$mcIIJ$sp extends SliceMatrix implements Matrix$mcJ$sp {
   public final Tensor tensor$mcIIJ$sp;
   public final Semiring evidence$1$mcJ$sp;
   private final ClassTag evidence$2;

   public final long apply(final Tuple2 i) {
      return Matrix$mcJ$sp.apply$(this, i);
   }

   public final long apply$mcJ$sp(final Tuple2 i) {
      return Matrix$mcJ$sp.apply$mcJ$sp$(this, i);
   }

   public final void update(final Tuple2 i, final long e) {
      Matrix$mcJ$sp.update$(this, i, e);
   }

   public final void update$mcJ$sp(final Tuple2 i, final long e) {
      Matrix$mcJ$sp.update$mcJ$sp$(this, i, e);
   }

   public DenseMatrix toDenseMatrix(final ClassTag cm, final Zero zero) {
      return Matrix$mcJ$sp.toDenseMatrix$(this, cm, zero);
   }

   public DenseMatrix toDenseMatrix$mcJ$sp(final ClassTag cm, final Zero zero) {
      return Matrix$mcJ$sp.toDenseMatrix$mcJ$sp$(this, cm, zero);
   }

   public Object map(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcJ$sp.map$(this, fn, canMapValues);
   }

   public Object map$mcJ$sp(final Function1 fn, final CanMapValues canMapValues) {
      return MatrixLike$mcJ$sp.map$mcJ$sp$(this, fn, canMapValues);
   }

   public Tensor tensor$mcIIJ$sp() {
      return this.tensor$mcIIJ$sp;
   }

   public Tensor tensor() {
      return this.tensor$mcIIJ$sp();
   }

   public long apply(final int i, final int j) {
      return this.apply$mcJ$sp(i, j);
   }

   public long apply$mcJ$sp(final int i, final int j) {
      return BoxesRunTime.unboxToLong(this.tensor().apply(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.slice1().apply(i)), this.slice2().apply(j))));
   }

   public void update(final int i, final int j, final long e) {
      this.update$mcJ$sp(i, j, e);
   }

   public void update$mcJ$sp(final int i, final int j, final long e) {
      this.tensor().update(.MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(this.slice1().apply(i)), this.slice2().apply(j)), BoxesRunTime.boxToLong(e));
   }

   public SliceMatrix repr() {
      return this.repr$mcIIJ$sp();
   }

   public SliceMatrix repr$mcIIJ$sp() {
      return this;
   }

   public Matrix copy() {
      return this.copy$mcJ$sp();
   }

   public Matrix copy$mcJ$sp() {
      Object var10000;
      if (this.rows() == 0) {
         var10000 = Matrix$.MODULE$.zeroRows(this.cols(), this.breeze$linalg$SliceMatrix$$evidence$2);
      } else if (this.cols() == 0) {
         var10000 = Matrix$.MODULE$.zeroCols(this.rows(), this.breeze$linalg$SliceMatrix$$evidence$2);
      } else {
         DenseMatrix result = new DenseMatrix$mcJ$sp(this.rows(), this.cols(), (long[])this.breeze$linalg$SliceMatrix$$evidence$2.newArray(this.size()));
         result.$colon$eq(this, HasOps$.MODULE$.impl_OpSet_InPlace_DM_M$mJc$sp());
         var10000 = result;
      }

      return (Matrix)var10000;
   }

   public Vector flatten(final View view) {
      return this.flatten$mcJ$sp(view);
   }

   public Vector flatten$mcJ$sp(final View view) {
      if (View.Require$.MODULE$.equals(view)) {
         throw new UnsupportedOperationException("Cannot make Vector as view of SliceMatrix.");
      } else {
         Vector var2;
         if (!View.Copy$.MODULE$.equals(view)) {
            if (!View.Prefer$.MODULE$.equals(view)) {
               throw new MatchError(view);
            }

            var2 = this.flatten$mcJ$sp(View.Copy$.MODULE$);
         } else {
            VectorBuilder vb = new VectorBuilder$mcJ$sp(this.rows() * this.cols(), this.activeSize(), this.evidence$1$mcJ$sp, this.breeze$linalg$SliceMatrix$$evidence$2);
            Iterator ai = this.activeIterator();

            while(ai.hasNext()) {
               Tuple2 var8 = (Tuple2)ai.next();
               if (var8 == null) {
                  throw new MatchError(var8);
               }

               Tuple2 var9 = (Tuple2)var8._1();
               long v = var8._2$mcJ$sp();
               if (var9 == null) {
                  throw new MatchError(var8);
               }

               int r = var9._1$mcI$sp();
               int c = var9._2$mcI$sp();
               Tuple3 var3 = new Tuple3(BoxesRunTime.boxToInteger(r), BoxesRunTime.boxToInteger(c), BoxesRunTime.boxToLong(v));
               int r = BoxesRunTime.unboxToInt(var3._1());
               int c = BoxesRunTime.unboxToInt(var3._2());
               long v = BoxesRunTime.unboxToLong(var3._3());
               vb.add$mcJ$sp(c * this.rows() + r, v);
            }

            var2 = vb.toVector$mcJ$sp();
         }

         return var2;
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public SliceMatrix$mcIIJ$sp(final Tensor tensor$mcIIJ$sp, final IndexedSeq slice1, final IndexedSeq slice2, final Semiring evidence$1$mcJ$sp, final ClassTag evidence$2) {
      super((Tensor)null, slice1, slice2, evidence$1$mcJ$sp, evidence$2);
      this.tensor$mcIIJ$sp = tensor$mcIIJ$sp;
      this.evidence$1$mcJ$sp = evidence$1$mcJ$sp;
      this.evidence$2 = evidence$2;
   }
}
