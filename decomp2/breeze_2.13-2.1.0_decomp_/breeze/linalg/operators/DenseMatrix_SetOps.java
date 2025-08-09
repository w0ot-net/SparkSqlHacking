package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.util.ArrayUtil$;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-eaB\b\u0011!\u0003\r\ta\u0006\u0005\u0006E\u0001!\ta\t\u0004\u0005O\u0001\u0001\u0001\u0006C\u0003a\u0005\u0011\u0005\u0011\rC\u0003e\u0005\u0011\u0005Q\rC\u0003k\u0005\u0011\u00051N\u0002\u0004\u0002\u000e\u0001\u0001\u0011q\u0002\u0005\u0007A\u001a!\t!a\r\t\r\u00114A\u0011AA\u001c\r\u0019\ti\u0004\u0001\u0001\u0002@!1\u0001-\u0003C\u0001\u0003;Ba\u0001Z\u0005\u0005\u0002\u0005\u0005\u0004bBA4\u0013\u0011%\u0011\u0011\u000e\u0005\b\u0003_\u0002A1AA9\u0011\u001d\ti\b\u0001C\u0002\u0003\u007f\u0012!\u0003R3og\u0016l\u0015\r\u001e:jq~\u001bV\r^(qg*\u0011\u0011CE\u0001\n_B,'/\u0019;peNT!a\u0005\u000b\u0002\r1Lg.\u00197h\u0015\u0005)\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0003?\u0001j\u0011\u0001E\u0005\u0003CA\u0011a\u0003R3og\u0016l\u0015\r\u001e:jq~\u001bF.[2j]\u001e|\u0005o]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0011\u0002\"!G\u0013\n\u0005\u0019R\"\u0001B+oSR\u0014\u0011bU3u\t6#Uj\u00149\u0016\u0005%R4c\u0001\u0002\u0019UA!1F\f\u001b5\u001d\tyB&\u0003\u0002.!\u0005)q\n]*fi&\u0011q\u0006\r\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HNM\u0005\u0003cI\u0012Q!\u0016$v]\u000eT!a\r\u000b\u0002\u000f\u001d,g.\u001a:jGB\u0019QG\u000e\u001d\u000e\u0003II!a\u000e\n\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0003sib\u0001\u0001B\u0005<\u0005\u0001\u0006\t\u0011!b\u0001y\t\ta+\u0005\u0002>\u0001B\u0011\u0011DP\u0005\u0003\u007fi\u0011qAT8uQ&tw\r\u0005\u0002\u001a\u0003&\u0011!I\u0007\u0002\u0004\u0003:L\bF\u0002\u001eE\u000fF36\f\u0005\u0002\u001a\u000b&\u0011aI\u0007\u0002\fgB,7-[1mSj,G-M\u0003$\u0011&[%J\u0004\u0002\u001a\u0013&\u0011!JG\u0001\u0007\t>,(\r\\32\t\u0011b\u0005k\u0007\b\u0003\u001bBk\u0011A\u0014\u0006\u0003\u001fZ\ta\u0001\u0010:p_Rt\u0014\"A\u000e2\u000b\r\u00126+\u0016+\u000f\u0005e\u0019\u0016B\u0001+\u001b\u0003\rIe\u000e^\u0019\u0005I1\u00036$M\u0003$/bS\u0016L\u0004\u0002\u001a1&\u0011\u0011LG\u0001\u0006\r2|\u0017\r^\u0019\u0005I1\u00036$M\u0003$9v{fL\u0004\u0002\u001a;&\u0011aLG\u0001\u0005\u0019>tw-\r\u0003%\u0019B[\u0012A\u0002\u001fj]&$h\bF\u0001c!\r\u0019'\u0001O\u0007\u0002\u0001\u0005)\u0011\r\u001d9msR\u0019AE\u001a5\t\u000b\u001d$\u0001\u0019\u0001\u001b\u0002\u0003\u0005DQ!\u001b\u0003A\u0002Q\n\u0011AY\u0001\u0018G\u0006\u001c\u0007.Z(cY&4\u0018n\\;t)J\fgn\u001d9pg\u0016$b\u0002\n7rgV<HP`A\u0001\u0003\u000b\tI\u0001C\u0003n\u000b\u0001\u0007a.\u0001\u0004s\u0005\u0016<\u0017N\u001c\t\u00033=L!\u0001\u001d\u000e\u0003\u0007%sG\u000fC\u0003s\u000b\u0001\u0007a.\u0001\u0003s\u000b:$\u0007\"\u0002;\u0006\u0001\u0004q\u0017AB2CK\u001eLg\u000eC\u0003w\u000b\u0001\u0007a.\u0001\u0003d\u000b:$\u0007\"\u0002=\u0006\u0001\u0004I\u0018a\u00013tiB\u0019\u0011D\u001f\u001d\n\u0005mT\"!B!se\u0006L\b\"B?\u0006\u0001\u0004q\u0017A\u00023ti>3g\rC\u0003\u0000\u000b\u0001\u0007a.\u0001\u0007b\u001b\u0006TwN]*ue&$W\r\u0003\u0004\u0002\u0004\u0015\u0001\r!_\u0001\u0004gJ\u001c\u0007BBA\u0004\u000b\u0001\u0007a.\u0001\u0004te\u000e|eM\u001a\u0005\u0007\u0003\u0017)\u0001\u0019\u00018\u0002\u0019\tl\u0015M[8s'R\u0014\u0018\u000eZ3\u0003\u0013M+G\u000fR'E->\u0003X\u0003BA\t\u00033\u0019BA\u0002\r\u0002\u0014A11FLA\u000b\u0003[\u0001B!\u000e\u001c\u0002\u0018A\u0019\u0011(!\u0007\u0005\u0013m2\u0001\u0015!A\u0001\u0006\u0004a\u0004fCA\r\t\u0006u\u0011\u0011EA\u0013\u0003S\tda\t%J\u0003?Q\u0015\u0007\u0002\u0013M!n\tda\t*T\u0003G!\u0016\u0007\u0002\u0013M!n\tdaI,Y\u0003OI\u0016\u0007\u0002\u0013M!n\tda\t/^\u0003Wq\u0016\u0007\u0002\u0013M!n\u0001R!NA\u0018\u0003/I1!!\r\u0013\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0015\u0005\u0005U\u0002\u0003B2\u0007\u0003/!R\u0001JA\u001d\u0003wAaa\u001a\u0005A\u0002\u0005U\u0001BB5\t\u0001\u0004\tiCA\u0004TKRl5k\u00149\u0016\t\u0005\u0005\u0013\u0011J\n\u0005\u0013a\t\u0019\u0005\u0005\u0004,]\u0005\u0015\u0013q\t\t\u0005kY\n9\u0005E\u0002:\u0003\u0013\"\u0011bO\u0005!\u0002\u0003\u0005)\u0019\u0001\u001f)\u0017\u0005%C)!\u0014\u0002R\u0005U\u0013\u0011L\u0019\u0007G!K\u0015q\n&2\t\u0011b\u0005kG\u0019\u0007GI\u001b\u00161\u000b+2\t\u0011b\u0005kG\u0019\u0007G]C\u0016qK-2\t\u0011b\u0005kG\u0019\u0007Gqk\u00161\f02\t\u0011b\u0005k\u0007\u000b\u0003\u0003?\u0002BaY\u0005\u0002HQ)A%a\u0019\u0002f!1qm\u0003a\u0001\u0003\u000bBa![\u0006A\u0002\u0005\u001d\u0013\u0001C:m_^\u0004\u0016\r\u001e5\u0015\u000b\u0011\nY'!\u001c\t\r\u001dd\u0001\u0019AA#\u0011\u0019IG\u00021\u0001\u0002H\u0005Y\u0012.\u001c9m?>\u0003X*\u001e7TKR|\u0016J\u001c)mC\u000e,w\fR'`\t6+B!a\u001d\u0002|U\u0011\u0011Q\u000f\t\u0007W9\n9(a\u001e\u0011\tU2\u0014\u0011\u0010\t\u0004s\u0005mD!B\u001e\u000e\u0005\u0004a\u0014AG5na2|v\n]'vYN+GoX%o!2\f7-Z0E\u001b~\u001bV\u0003BAA\u0003\u0013+\"!a!\u0011\r-r\u0013QQAD!\u0011)d'a\"\u0011\u0007e\nI\tB\u0003<\u001d\t\u0007A\b"
)
public interface DenseMatrix_SetOps extends DenseMatrix_SlicingOps {
   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpMulSet_InPlace_DM_DM$(final DenseMatrix_SetOps $this) {
      return $this.impl_OpMulSet_InPlace_DM_DM();
   }

   default UFunc.InPlaceImpl2 impl_OpMulSet_InPlace_DM_DM() {
      return new SetDMDMOp();
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpMulSet_InPlace_DM_S$(final DenseMatrix_SetOps $this) {
      return $this.impl_OpMulSet_InPlace_DM_S();
   }

   default UFunc.InPlaceImpl2 impl_OpMulSet_InPlace_DM_S() {
      return new SetMSOp();
   }

   static void $init$(final DenseMatrix_SetOps $this) {
   }

   public class SetDMDMOp implements UFunc.InPlaceImpl2 {
      // $FF: synthetic field
      public final DenseMatrix_SetOps $outer;

      public void apply$mcD$sp(final Object v, final double v2) {
         UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
      }

      public void apply$mcF$sp(final Object v, final float v2) {
         UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
      }

      public void apply$mcI$sp(final Object v, final int v2) {
         UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
      }

      public void apply(final DenseMatrix a, final DenseMatrix b) {
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
                  System.arraycopy(b.data(), b.offset(), a.data(), a.offset(), a.size());
               } else if (a.isTranspose() == b.isTranspose()) {
                  int index$macro$6 = 0;

                  for(int limit$macro$8 = a.majorSize(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                     System.arraycopy(b.data(), b.offset() + index$macro$6 * b.majorStride(), a.data(), a.offset() + index$macro$6 * a.majorStride(), a.minorSize());
                  }
               } else {
                  this.cacheObliviousTranspose(0, a.majorSize(), 0, b.majorSize(), a.data(), a.offset(), a.majorStride(), b.data(), b.offset(), b.majorStride());
               }

            }
         }
      }

      public void cacheObliviousTranspose(final int rBegin, final int rEnd, final int cBegin, final int cEnd, final Object dst, final int dstOff, final int aMajorStride, final Object src, final int srcOff, final int bMajorStride) {
         int r = rEnd - rBegin;
         int c = cEnd - cBegin;
         if (r <= 16 && c <= 16) {
            int index$macro$7 = rBegin;

            for(int limit$macro$9 = rEnd; index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = cBegin;

               for(int limit$macro$4 = cEnd; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  ((j, i) -> .MODULE$.array_update(dst, dstOff + j * aMajorStride + i, .MODULE$.array_apply(src, srcOff + i * bMajorStride + j))).apply$mcVII$sp(index$macro$7, index$macro$2);
               }
            }
         } else if (r >= c) {
            this.cacheObliviousTranspose(rBegin, rBegin + r / 2, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
            this.cacheObliviousTranspose(rBegin + r / 2, rEnd, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
         } else {
            this.cacheObliviousTranspose(rBegin, rEnd, cBegin, cBegin + c / 2, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
            this.cacheObliviousTranspose(rBegin, rEnd, cBegin + c / 2, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
         }

      }

      public void apply$mcD$sp(final DenseMatrix a, final DenseMatrix b) {
         this.apply(a, b);
      }

      public void apply$mcF$sp(final DenseMatrix a, final DenseMatrix b) {
         this.apply(a, b);
      }

      public void apply$mcI$sp(final DenseMatrix a, final DenseMatrix b) {
         this.apply(a, b);
      }

      public void apply$mcJ$sp(final DenseMatrix a, final DenseMatrix b) {
         this.apply(a, b);
      }

      public void cacheObliviousTranspose$mcD$sp(final int rBegin, final int rEnd, final int cBegin, final int cEnd, final double[] dst, final int dstOff, final int aMajorStride, final double[] src, final int srcOff, final int bMajorStride) {
         this.cacheObliviousTranspose(rBegin, rEnd, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
      }

      public void cacheObliviousTranspose$mcF$sp(final int rBegin, final int rEnd, final int cBegin, final int cEnd, final float[] dst, final int dstOff, final int aMajorStride, final float[] src, final int srcOff, final int bMajorStride) {
         this.cacheObliviousTranspose(rBegin, rEnd, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
      }

      public void cacheObliviousTranspose$mcI$sp(final int rBegin, final int rEnd, final int cBegin, final int cEnd, final int[] dst, final int dstOff, final int aMajorStride, final int[] src, final int srcOff, final int bMajorStride) {
         this.cacheObliviousTranspose(rBegin, rEnd, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
      }

      public void cacheObliviousTranspose$mcJ$sp(final int rBegin, final int rEnd, final int cBegin, final int cEnd, final long[] dst, final int dstOff, final int aMajorStride, final long[] src, final int srcOff, final int bMajorStride) {
         this.cacheObliviousTranspose(rBegin, rEnd, cBegin, cEnd, dst, dstOff, aMajorStride, src, srcOff, bMajorStride);
      }

      // $FF: synthetic method
      public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetDMDMOp$$$outer() {
         return this.$outer;
      }

      public SetDMDMOp() {
         if (DenseMatrix_SetOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrix_SetOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class SetDMDVOp implements UFunc.InPlaceImpl2 {
      // $FF: synthetic field
      public final DenseMatrix_SetOps $outer;

      public void apply$mcD$sp(final Object v, final double v2) {
         UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
      }

      public void apply$mcF$sp(final Object v, final float v2) {
         UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
      }

      public void apply$mcI$sp(final Object v, final int v2) {
         UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
      }

      public void apply(final DenseMatrix a, final DenseVector b) {
         boolean cond$macro$1 = a.rows() == b.length() && a.cols() == 1 || a.cols() == b.length() && a.rows() == 1;
         if (!cond$macro$1) {
            throw new IllegalArgumentException("requirement failed: DenseMatrix must have same number of rows, or same number of columns, as DenseVector, and the other dim must be 1.: a.rows.==(b.length).&&(a.cols.==(1)).||(a.cols.==(b.length).&&(a.rows.==(1)))");
         } else {
            Object ad = a.data();
            Object bd = b.data();
            IntRef boff = IntRef.create(b.offset());
            int index$macro$8 = 0;

            for(int limit$macro$10 = a.cols(); index$macro$8 < limit$macro$10; ++index$macro$8) {
               int index$macro$3 = 0;

               for(int limit$macro$5 = a.rows(); index$macro$3 < limit$macro$5; ++index$macro$3) {
                  ((c, r) -> {
                     .MODULE$.array_update(ad, a.linearIndex(r, c), .MODULE$.array_apply(bd, boff.elem));
                     boff.elem += b.stride();
                  }).apply$mcVII$sp(index$macro$8, index$macro$3);
               }
            }

         }
      }

      public void apply$mcD$sp(final DenseMatrix a, final DenseVector b) {
         this.apply(a, b);
      }

      public void apply$mcF$sp(final DenseMatrix a, final DenseVector b) {
         this.apply(a, b);
      }

      public void apply$mcI$sp(final DenseMatrix a, final DenseVector b) {
         this.apply(a, b);
      }

      public void apply$mcJ$sp(final DenseMatrix a, final DenseVector b) {
         this.apply(a, b);
      }

      // $FF: synthetic method
      public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetDMDVOp$$$outer() {
         return this.$outer;
      }

      public SetDMDVOp() {
         if (DenseMatrix_SetOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrix_SetOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class SetMSOp implements UFunc.InPlaceImpl2 {
      // $FF: synthetic field
      public final DenseMatrix_SetOps $outer;

      public void apply$mcD$sp(final Object v, final double v2) {
         UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
      }

      public void apply$mcF$sp(final Object v, final float v2) {
         UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
      }

      public void apply$mcI$sp(final Object v, final int v2) {
         UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
      }

      public void apply(final DenseMatrix a, final Object b) {
         if (a.isContiguous()) {
            ArrayUtil$.MODULE$.fill(a.data(), a.offset(), a.size(), b);
         } else {
            this.slowPath(a, b);
         }

      }

      public void slowPath(final DenseMatrix a, final Object b) {
         Object ad = a.data();
         int aoff = a.offset();
         int index$macro$7 = 0;

         for(int limit$macro$9 = a.majorSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.minorSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               .MODULE$.array_update(ad, aoff + index$macro$2, b);
            }

            aoff += a.majorStride();
         }

      }

      public void apply$mcD$sp(final DenseMatrix a, final double b) {
         this.apply((DenseMatrix)a, BoxesRunTime.boxToDouble(b));
      }

      public void apply$mcF$sp(final DenseMatrix a, final float b) {
         this.apply((DenseMatrix)a, BoxesRunTime.boxToFloat(b));
      }

      public void apply$mcI$sp(final DenseMatrix a, final int b) {
         this.apply((DenseMatrix)a, BoxesRunTime.boxToInteger(b));
      }

      public void apply$mcJ$sp(final DenseMatrix a, final long b) {
         this.apply((DenseMatrix)a, BoxesRunTime.boxToLong(b));
      }

      public void slowPath$mcD$sp(final DenseMatrix a, final double b) {
         this.slowPath(a, BoxesRunTime.boxToDouble(b));
      }

      public void slowPath$mcF$sp(final DenseMatrix a, final float b) {
         this.slowPath(a, BoxesRunTime.boxToFloat(b));
      }

      public void slowPath$mcI$sp(final DenseMatrix a, final int b) {
         this.slowPath(a, BoxesRunTime.boxToInteger(b));
      }

      public void slowPath$mcJ$sp(final DenseMatrix a, final long b) {
         this.slowPath(a, BoxesRunTime.boxToLong(b));
      }

      // $FF: synthetic method
      public DenseMatrix_SetOps breeze$linalg$operators$DenseMatrix_SetOps$SetMSOp$$$outer() {
         return this.$outer;
      }

      public SetMSOp() {
         if (DenseMatrix_SetOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrix_SetOps.this;
            super();
         }
      }
   }
}
