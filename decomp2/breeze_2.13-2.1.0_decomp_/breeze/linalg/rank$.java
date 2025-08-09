package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcD$sp;
import dev.ludovic.netlib.lapack.LAPACK;
import scala.MatchError;
import scala.Tuple3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

public final class rank$ implements UFunc {
   public static final rank$ MODULE$ = new rank$();

   static {
      UFunc.$init$(MODULE$);
   }

   public final Object apply(final Object v, final UFunc.UImpl impl) {
      return UFunc.apply$(this, v, impl);
   }

   public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDDc$sp$(this, v, impl);
   }

   public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDFc$sp$(this, v, impl);
   }

   public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
      return UFunc.apply$mDIc$sp$(this, v, impl);
   }

   public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFDc$sp$(this, v, impl);
   }

   public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFFc$sp$(this, v, impl);
   }

   public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
      return UFunc.apply$mFIc$sp$(this, v, impl);
   }

   public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIDc$sp$(this, v, impl);
   }

   public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIFc$sp$(this, v, impl);
   }

   public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
      return UFunc.apply$mIIc$sp$(this, v, impl);
   }

   public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$(this, v1, v2, impl);
   }

   public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
   }

   public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
   }

   public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
   }

   public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
      return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$(this, v1, v2, v3, impl);
   }

   public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
   }

   public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
   }

   public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
   }

   public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
      return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
   }

   public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return UFunc.apply$(this, v1, v2, v3, v4, impl);
   }

   public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return UFunc.inPlace$(this, v, impl);
   }

   public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return UFunc.inPlace$(this, v, v2, impl);
   }

   public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return UFunc.inPlace$(this, v, v2, v3, impl);
   }

   public final Object withSink(final Object s) {
      return UFunc.withSink$(this, s);
   }

   public UFunc.UImpl implRankFromSVD(final UFunc.UImpl canSVD, final UFunc.UImpl maxS, final CanTraverseValues travS, final UFunc.UImpl nF) {
      return new UFunc.UImpl(canSVD, maxS, nF, travS) {
         private final UFunc.UImpl canSVD$1;
         private final UFunc.UImpl maxS$1;
         public final UFunc.UImpl nF$1;
         private final CanTraverseValues travS$1;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public int apply(final Object m) {
            svd.SVD var4 = (svd.SVD)svd$.MODULE$.apply(m, this.canSVD$1);
            if (var4 != null) {
               Object u = var4.leftVectors();
               Object s = var4.singularValues();
               Object vt = var4.rightVectors();
               Tuple3 var2 = new Tuple3(u, s, vt);
               Object var8 = var2._1();
               Object sx = var2._2();
               Object var10 = var2._3();
               double eps = (double)2.0F * LAPACK.getInstance().dlamch("e");
               double tol = eps * BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(max$.MODULE$.apply(sx, this.maxS$1), this.nF$1));
               IntRef n = IntRef.create(0);
               this.travS$1.traverse(sx, new CanTraverseValues.ValuesVisitor(tol, n) {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;
                  private final double tol$1;
                  private final IntRef n$1;

                  public void visit$mcZ$sp(final boolean a) {
                     CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
                  }

                  public void visit$mcB$sp(final byte a) {
                     CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
                  }

                  public void visit$mcC$sp(final char a) {
                     CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
                  }

                  public void visit$mcD$sp(final double a) {
                     CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
                  }

                  public void visit$mcF$sp(final float a) {
                     CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
                  }

                  public void visit$mcI$sp(final int a) {
                     CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
                  }

                  public void visit$mcJ$sp(final long a) {
                     CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
                  }

                  public void visit$mcS$sp(final short a) {
                     CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
                  }

                  public void visit$mcV$sp(final BoxedUnit a) {
                     CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
                  }

                  public void visitArray(final Object arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$(this, arr);
                  }

                  public void visitArray$mcZ$sp(final boolean[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
                  }

                  public void visitArray$mcB$sp(final byte[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
                  }

                  public void visitArray$mcC$sp(final char[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
                  }

                  public void visitArray$mcD$sp(final double[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
                  }

                  public void visitArray$mcF$sp(final float[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
                  }

                  public void visitArray$mcI$sp(final int[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
                  }

                  public void visitArray$mcJ$sp(final long[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
                  }

                  public void visitArray$mcS$sp(final short[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
                  }

                  public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
                  }

                  public void visitArray(final Object arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
                  }

                  public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcD$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
                  }

                  public void visit(final Object a) {
                     if (BoxesRunTime.unboxToDouble(this.$outer.nF$1.apply(a)) > this.tol$1) {
                        ++this.n$1.elem;
                     }

                  }

                  public void zeros(final int numZero, final Object zeroValue) {
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        this.tol$1 = tol$1;
                        this.n$1 = n$1;
                        CanTraverseValues.ValuesVisitor.$init$(this);
                     }
                  }
               });
               return n.elem;
            } else {
               throw new MatchError(var4);
            }
         }

         public {
            this.canSVD$1 = canSVD$1;
            this.maxS$1 = maxS$1;
            this.nF$1 = nF$1;
            this.travS$1 = travS$1;
         }
      };
   }

   public UFunc.UImpl2 implRankTol(final UFunc.UImpl canSVD, final UFunc.UImpl maxS, final CanTraverseValues travS) {
      return new UFunc.UImpl2(canSVD, travS) {
         private final UFunc.UImpl canSVD$2;
         private final CanTraverseValues travS$2;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public int apply(final Object m, final double tol) {
            Tuple3 var6 = (Tuple3)svd$.MODULE$.apply(m, this.canSVD$2);
            if (var6 != null) {
               Object u = var6._1();
               Object s = var6._2();
               Object vt = var6._3();
               Tuple3 var4 = new Tuple3(u, s, vt);
               Object var10 = var4._1();
               Object s = var4._2();
               Object var12 = var4._3();
               IntRef n = IntRef.create(0);
               this.travS$2.traverse(s, new CanTraverseValues$ValuesVisitor$mcD$sp(tol, n) {
                  private final double tol$2;
                  private final IntRef n$2;

                  public void visitArray(final double[] arr) {
                     CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$(this, arr);
                  }

                  public void visitArray$mcD$sp(final double[] arr) {
                     CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$mcD$sp$(this, arr);
                  }

                  public void visitArray(final double[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$mcD$sp$(this, arr, offset, length, stride);
                  }

                  public void visit$mcZ$sp(final boolean a) {
                     CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
                  }

                  public void visit$mcB$sp(final byte a) {
                     CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
                  }

                  public void visit$mcC$sp(final char a) {
                     CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
                  }

                  public void visit$mcF$sp(final float a) {
                     CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
                  }

                  public void visit$mcI$sp(final int a) {
                     CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
                  }

                  public void visit$mcJ$sp(final long a) {
                     CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
                  }

                  public void visit$mcS$sp(final short a) {
                     CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
                  }

                  public void visit$mcV$sp(final BoxedUnit a) {
                     CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
                  }

                  public void visitArray$mcZ$sp(final boolean[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
                  }

                  public void visitArray$mcB$sp(final byte[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
                  }

                  public void visitArray$mcC$sp(final char[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
                  }

                  public void visitArray$mcF$sp(final float[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
                  }

                  public void visitArray$mcI$sp(final int[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
                  }

                  public void visitArray$mcJ$sp(final long[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
                  }

                  public void visitArray$mcS$sp(final short[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
                  }

                  public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
                  }

                  public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
                  }

                  public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
                  }

                  public void visit(final double a) {
                     this.visit$mcD$sp(a);
                  }

                  public void zeros(final int numZero, final double zeroValue) {
                     this.zeros$mcD$sp(numZero, zeroValue);
                  }

                  public void visit$mcD$sp(final double a) {
                     if (a > this.tol$2) {
                        ++this.n$2.elem;
                     }

                  }

                  public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                  }

                  public {
                     this.tol$2 = tol$2;
                     this.n$2 = n$2;
                     CanTraverseValues.ValuesVisitor.$init$(this);
                  }
               });
               return n.elem;
            } else {
               throw new MatchError(var6);
            }
         }

         public {
            this.canSVD$2 = canSVD$2;
            this.travS$2 = travS$2;
         }
      };
   }

   private rank$() {
   }
}
