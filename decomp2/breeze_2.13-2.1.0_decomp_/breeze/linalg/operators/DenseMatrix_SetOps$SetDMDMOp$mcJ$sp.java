package breeze.linalg.operators;

import breeze.linalg.DenseMatrix;
import java.lang.invoke.SerializedLambda;

public class DenseMatrix_SetOps$SetDMDMOp$mcJ$sp extends DenseMatrix_SetOps.SetDMDMOp {
   public void apply(final DenseMatrix a, final DenseMatrix b) {
      this.apply$mcJ$sp(a, b);
   }

   public void apply$mcJ$sp(final DenseMatrix a, final DenseMatrix b) {
      int left$macro$1 = a.rows();
      int right$macro$2 = b.rows();
      if (left$macro$1 != right$macro$2) {
         throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Matrixs must have same number of rows: ").append("a.rows == b.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
      } else {
         int left$macro$3 = a.cols();
         int right$macro$4 = b.cols();
         if (left$macro$3 != right$macro$4) {
            throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Matrixs must have same number of columns: ").append("a.cols == b.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
         } else {
            if (a.isTranspose() == b.isTranspose() && a.isContiguous() && b.isContiguous()) {
               System.arraycopy(b.data$mcJ$sp(), b.offset(), a.data$mcJ$sp(), a.offset(), a.size());
            } else if (a.isTranspose() == b.isTranspose()) {
               int index$macro$6 = 0;

               for(int limit$macro$8 = a.majorSize(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                  System.arraycopy(b.data$mcJ$sp(), b.offset() + index$macro$6 * b.majorStride(), a.data$mcJ$sp(), a.offset() + index$macro$6 * a.majorStride(), a.minorSize());
               }
            } else {
               this.cacheObliviousTranspose$mcJ$sp(0, a.majorSize(), 0, b.majorSize(), a.data$mcJ$sp(), a.offset(), a.majorStride(), b.data$mcJ$sp(), b.offset(), b.majorStride());
            }

         }
      }
   }

   public void cacheObliviousTranspose(final int rBegin, final int rEnd, final int cBegin, final int cEnd, final long[] dst, final int dstOff, final int aMajorStride, final long[] src, final int srcOff, final int bMajorStride) {
      this.cacheObliviousTranspose$mcJ$sp(rBegin, rEnd, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
   }

   public void cacheObliviousTranspose$mcJ$sp(final int rBegin, final int rEnd, final int cBegin, final int cEnd, final long[] dst, final int dstOff, final int aMajorStride, final long[] src, final int srcOff, final int bMajorStride) {
      int r = rEnd - rBegin;
      int c = cEnd - cBegin;
      if (r <= 16 && c <= 16) {
         int index$macro$7 = rBegin;

         for(int limit$macro$9 = rEnd; index$macro$7 < limit$macro$9; ++index$macro$7) {
            int index$macro$2 = cBegin;

            for(int limit$macro$4 = cEnd; index$macro$2 < limit$macro$4; ++index$macro$2) {
               ((j, i) -> dst[dstOff + j * aMajorStride + i] = src[srcOff + i * bMajorStride + j]).apply$mcVII$sp(index$macro$7, index$macro$2);
            }
         }
      } else if (r >= c) {
         this.cacheObliviousTranspose$mcJ$sp(rBegin, rBegin + r / 2, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
         this.cacheObliviousTranspose$mcJ$sp(rBegin + r / 2, rEnd, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
      } else {
         this.cacheObliviousTranspose$mcJ$sp(rBegin, rEnd, cBegin, cBegin + c / 2, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
         this.cacheObliviousTranspose$mcJ$sp(rBegin, rEnd, cBegin + c / 2, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
      }

   }

   // $FF: synthetic method
   public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetDMDMOp$mcJ$sp$$$outer() {
      return this.$outer;
   }

   public DenseMatrix_SetOps$SetDMDMOp$mcJ$sp(final DenseMatrix_SetOps $outer) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
