package breeze.linalg;

import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.util.ArrayBuilder;
import breeze.util.ArrayBuilder$;
import breeze.util.Sorting$;
import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public class CSCMatrix$Builder$mcF$sp extends CSCMatrix.Builder {
   public final Semiring evidence$9$mcF$sp;
   public final Zero evidence$10$mcF$sp;
   public final ArrayBuilder vs$mcF$sp;
   private final int initNnz;
   private final ClassTag evidence$8;

   public Semiring ring() {
      return this.ring$mcF$sp();
   }

   public Semiring ring$mcF$sp() {
      return (Semiring).MODULE$.implicitly(this.evidence$9$mcF$sp);
   }

   public void add(final int r, final int c, final float v) {
      this.add$mcF$sp(r, c, v);
   }

   public void add$mcF$sp(final int r, final int c, final float v) {
      if (v != this.ring$mcF$sp().zero$mcF$sp()) {
         this.breeze$linalg$CSCMatrix$Builder$$numAdded_$eq(this.breeze$linalg$CSCMatrix$Builder$$numAdded() + 1);
         this.vs().$plus$eq(BoxesRunTime.boxToFloat(v));
         this.breeze$linalg$CSCMatrix$Builder$$indices().$plus$eq(BoxesRunTime.boxToLong((long)c << 32 | (long)r & 4294967295L));
      }

   }

   public ArrayBuilder vs$mcF$sp() {
      return this.vs$mcF$sp;
   }

   public ArrayBuilder vs() {
      return this.vs$mcF$sp();
   }

   public CSCMatrix result() {
      return this.result$mcF$sp();
   }

   public CSCMatrix result$mcF$sp() {
      return this.result$mcF$sp(false, false);
   }

   public CSCMatrix result(final boolean keysAlreadyUnique, final boolean keysAlreadySorted) {
      return this.result$mcF$sp(keysAlreadyUnique, keysAlreadySorted);
   }

   public CSCMatrix result$mcF$sp(final boolean keysAlreadyUnique, final boolean keysAlreadySorted) {
      long[] indices = this.breeze$linalg$CSCMatrix$Builder$$indices().result();
      float[] vs = (float[])this.vs().result();
      int nnz = indices.length;
      int _rows = this.rows() >= 0 ? this.rows() : BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.longArrayOps(indices), (JFunction1.mcIJ.sp)(idx) -> this.breeze$linalg$CSCMatrix$Builder$$rowFromIndex(idx), scala.reflect.ClassTag..MODULE$.Int())), BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(x$2, x$3) -> scala.runtime.RichInt..MODULE$.max$extension(.MODULE$.intWrapper(x$2), x$3))) + 1;
      int _cols = this.cols() >= 0 ? this.cols() : BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.longArrayOps(indices), (JFunction1.mcIJ.sp)(idx) -> this.breeze$linalg$CSCMatrix$Builder$$colFromIndex(idx), scala.reflect.ClassTag..MODULE$.Int())), BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(x$4, x$5) -> scala.runtime.RichInt..MODULE$.max$extension(.MODULE$.intWrapper(x$4), x$5))) + 1;
      int[] outCols = new int[_cols + 1];
      if (nnz == 0) {
         return new CSCMatrix$mcF$sp(vs, _rows, _cols, outCols, 0, (int[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.Int()), this.evidence$10$mcF$sp);
      } else {
         Sorting$.MODULE$.indirectSort$mFc$sp((long[])indices, vs, 0, nnz);
         int[] outRows = new int[nnz];
         float[] outData = (float[])this.breeze$linalg$CSCMatrix$Builder$$evidence$8.newArray(nnz);
         outRows[0] = this.breeze$linalg$CSCMatrix$Builder$$rowFromIndex(indices[0]);
         outData[0] = vs[0];
         int outDataIndex = 0;
         int i = 1;

         int lastCol;
         for(lastCol = this.breeze$linalg$CSCMatrix$Builder$$colFromIndex(indices[0]); i < nnz; ++i) {
            long index = indices[i];
            int col = this.breeze$linalg$CSCMatrix$Builder$$colFromIndex(index);
            boolean cond$macro$1 = this.cols() < 0 || col < this.cols();
            if (!cond$macro$1) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: ").append((new StringBuilder(54)).append("Column index ").append(col).append(" is out of bounds for number of columns ").append(this.cols()).append("!").toString()).append(": ").append("Builder.this.cols.<(0).||(col.<(Builder.this.cols))").toString());
            }

            boolean colsEqual = col == lastCol;
            int row = this.breeze$linalg$CSCMatrix$Builder$$rowFromIndex(index);
            boolean cond$macro$2 = this.rows() < 0 || row < this.rows();
            if (!cond$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: ").append((new StringBuilder(48)).append("Row index ").append(row).append(" is out of bounds for number of rows ").append(this.rows()).append("!").toString()).append(": ").append("Builder.this.rows.<(0).||(row.<(Builder.this.rows))").toString());
            }

            if (colsEqual && row == this.breeze$linalg$CSCMatrix$Builder$$rowFromIndex(indices[i - 1])) {
               boolean cond$macro$3 = !keysAlreadyUnique;
               if (!cond$macro$3) {
                  throw new AssertionError("assertion failed: keysAlreadyUnique.unary_!");
               }

               outData[outDataIndex] = this.ring$mcF$sp().$plus$mcF$sp(outData[outDataIndex], vs[i]);
            } else {
               ++outDataIndex;
               outRows[outDataIndex] = row;
               outData[outDataIndex] = vs[i];
            }

            if (!colsEqual) {
               while(lastCol < col) {
                  outCols[lastCol + 1] = outDataIndex;
                  ++lastCol;
               }
            }
         }

         ++outDataIndex;
         if (keysAlreadyUnique && outDataIndex != nnz) {
            throw new AssertionError((new StringBuilder(44)).append("assertion failed: ").append("outDataIndex == nnz (").append(outDataIndex).append(" ").append("!=").append(" ").append(nnz).append(")").toString());
         } else {
            while(lastCol < _cols) {
               outCols[lastCol + 1] = outDataIndex;
               ++lastCol;
            }

            CSCMatrix out = new CSCMatrix$mcF$sp(outData, _rows, _cols, outCols, outDataIndex, outRows, this.evidence$10$mcF$sp);
            if (!keysAlreadyUnique) {
               out.compact();
            }

            return out;
         }
      }
   }

   public boolean specInstance$() {
      return true;
   }

   public CSCMatrix$Builder$mcF$sp(final int rows, final int cols, final int initNnz, final ClassTag evidence$8, final Semiring evidence$9$mcF$sp, final Zero evidence$10$mcF$sp) {
      super(rows, cols, initNnz, evidence$8, evidence$9$mcF$sp, evidence$10$mcF$sp);
      this.evidence$9$mcF$sp = evidence$9$mcF$sp;
      this.evidence$10$mcF$sp = evidence$10$mcF$sp;
      this.initNnz = initNnz;
      this.evidence$8 = evidence$8;
      this.vs$mcF$sp = ArrayBuilder$.MODULE$.make(this.breeze$linalg$CSCMatrix$Builder$$evidence$8);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
