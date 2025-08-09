package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.minMax$;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcD$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcF$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcI$sp;
import breeze.linalg.support.CanZipAndTraverseValues;
import breeze.storage.Zero$;
import breeze.util.WideningConversion;
import scala.MatchError;
import scala.Tuple2;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class hist$ implements UFunc {
   public static final hist$ MODULE$ = new hist$();

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

   public UFunc.UImpl defaultHist(final UFunc.UImpl2 innerImpl) {
      return new UFunc.UImpl(innerImpl) {
         private final UFunc.UImpl2 innerImpl$1;

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

         public hist.Histogram apply(final Object v) {
            return (hist.Histogram)this.innerImpl$1.apply(v, BoxesRunTime.boxToInteger(10));
         }

         public {
            this.innerImpl$1 = innerImpl$1;
         }
      };
   }

   public UFunc.UImpl2 defaultHistBins(final UFunc.UImpl mm, final WideningConversion conv, final UFunc.UImpl3 impl3) {
      return new UFunc.UImpl2(mm, impl3, conv) {
         private final UFunc.UImpl mm$1;
         private final UFunc.UImpl3 impl3$1;
         private final WideningConversion conv$1;

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

         public hist.Histogram apply(final Object v, final int bins) {
            Tuple2 var5 = (Tuple2)minMax$.MODULE$.apply(v, this.mm$1);
            if (var5 != null) {
               Object minS = var5._1();
               Object maxS = var5._2();
               Tuple2 var3 = new Tuple2(minS, maxS);
               Object minSx = var3._1();
               Object maxSx = var3._2();
               return (hist.Histogram)this.impl3$1.apply(v, BoxesRunTime.boxToInteger(bins), new Tuple2.mcDD.sp(BoxesRunTime.unboxToDouble(this.conv$1.apply(minSx)), BoxesRunTime.unboxToDouble(this.conv$1.apply(maxSx))));
            } else {
               throw new MatchError(var5);
            }
         }

         public {
            this.mm$1 = mm$1;
            this.impl3$1 = impl3$1;
            this.conv$1 = conv$1;
         }
      };
   }

   public UFunc.UImpl3 canTraverseValuesImpl(final CanTraverseValues iter, final WideningConversion conv) {
      return new UFunc.UImpl3(conv, iter) {
         public final WideningConversion conv$2;
         private final CanTraverseValues iter$1;

         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public hist.Histogram apply(final Object v, final int bins, final Tuple2 range) {
            if (range != null) {
               double minimum = range._1$mcD$sp();
               double maximum = range._2$mcD$sp();
               Tuple2.mcDD.sp var4 = new Tuple2.mcDD.sp(minimum, maximum);
               double minimum = ((Tuple2)var4)._1$mcD$sp();
               double maximum = ((Tuple2)var4)._2$mcD$sp();
               if (maximum <= minimum) {
                  throw new IllegalArgumentException("Minimum of a histogram must not be greater than the maximum");
               } else {
                  DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(bins, .MODULE$.Int(), Zero$.MODULE$.IntZero());
                  CanTraverseValues.ValuesVisitor visitor = new CanTraverseValues.ValuesVisitor(bins, result, maximum, minimum) {
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;
                     private final int bins$1;
                     private final DenseVector result$1;
                     private final double maximum$1;
                     private final double minimum$1;

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
                        double ad = BoxesRunTime.unboxToDouble(this.$outer.conv$2.apply(a));
                        int i = this.binOf(ad);
                        if (i >= 0 && i < this.bins$1) {
                           this.result$1.update$mcI$sp(i, this.result$1.apply$mcI$sp(i) + 1);
                        }

                        if (ad == this.maximum$1) {
                           int var5 = this.bins$1 - 1;
                           this.result$1.update$mcI$sp(var5, this.result$1.apply$mcI$sp(var5) + 1);
                        }

                     }

                     public void zeros(final int numZero, final Object zeroValue) {
                        double ad = BoxesRunTime.unboxToDouble(this.$outer.conv$2.apply(zeroValue));
                        int i = this.binOf(ad);
                        if (i >= 0 && i < this.bins$1) {
                           this.result$1.update$mcI$sp(i, this.result$1.apply$mcI$sp(i) + numZero);
                        }

                        if (ad == this.maximum$1) {
                           int var6 = this.bins$1 - 1;
                           this.result$1.update$mcI$sp(var6, this.result$1.apply$mcI$sp(var6) + 1);
                        }

                     }

                     private int binOf(final double v) {
                        return (int)scala.math.package..MODULE$.floor((double)this.bins$1 * ((v - this.minimum$1) / (this.maximum$1 - this.minimum$1)));
                     }

                     public {
                        if (<VAR_NAMELESS_ENCLOSURE> == null) {
                           throw null;
                        } else {
                           this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                           this.bins$1 = bins$1;
                           this.result$1 = result$1;
                           this.maximum$1 = maximum$1;
                           this.minimum$1 = minimum$1;
                           CanTraverseValues.ValuesVisitor.$init$(this);
                        }
                     }
                  };
                  this.iter$1.traverse(v, visitor);
                  return new hist.Histogram(result, minimum, maximum, (double)bins);
               }
            } else {
               throw new MatchError(range);
            }
         }

         public {
            this.conv$2 = conv$2;
            this.iter$1 = iter$1;
         }
      };
   }

   public UFunc.UImpl2 defaultHistWeights(final UFunc.UImpl3 innerImpl) {
      return new UFunc.UImpl2(innerImpl) {
         private final UFunc.UImpl3 innerImpl$2;

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

         public hist.Histogram apply(final Object v, final Object weights) {
            return (hist.Histogram)this.innerImpl$2.apply(v, BoxesRunTime.boxToInteger(10), weights);
         }

         public {
            this.innerImpl$2 = innerImpl$2;
         }
      };
   }

   public UFunc.UImpl3 defaultHistBinsWeights(final UFunc.UImpl4 innerImpl, final UFunc.UImpl mm, final WideningConversion conv) {
      return new UFunc.UImpl3(mm, innerImpl, conv) {
         private final UFunc.UImpl mm$2;
         private final UFunc.UImpl4 innerImpl$3;
         private final WideningConversion conv$3;

         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public hist.Histogram apply(final Object v, final int bins, final Object weights) {
            Tuple2 var6 = (Tuple2)minMax$.MODULE$.apply(v, this.mm$2);
            if (var6 != null) {
               Object minS = var6._1();
               Object maxS = var6._2();
               Tuple2 var4 = new Tuple2(minS, maxS);
               Object minSx = var4._1();
               Object maxSx = var4._2();
               return (hist.Histogram)this.innerImpl$3.apply(v, BoxesRunTime.boxToInteger(bins), new Tuple2.mcDD.sp(BoxesRunTime.unboxToDouble(this.conv$3.apply(minSx)), BoxesRunTime.unboxToDouble(this.conv$3.apply(maxSx))), weights);
            } else {
               throw new MatchError(var6);
            }
         }

         public {
            this.mm$2 = mm$2;
            this.innerImpl$3 = innerImpl$3;
            this.conv$3 = conv$3;
         }
      };
   }

   public UFunc.UImpl4 canTraverseValuesImplWeighted(final CanZipAndTraverseValues iter, final WideningConversion conv) {
      return new UFunc.UImpl4(conv, iter) {
         public final WideningConversion conv$4;
         private final CanZipAndTraverseValues iter$2;

         public double apply$mcDDDDD$sp(final double v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDDDF$sp(final double v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDDDI$sp(final double v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDDDFD$sp(final double v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDDFF$sp(final double v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDDFI$sp(final double v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDDDID$sp(final double v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDDIF$sp(final double v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDDII$sp(final double v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDDFDD$sp(final double v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDFDF$sp(final double v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDFDI$sp(final double v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDDFFD$sp(final double v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDFFF$sp(final double v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDFFI$sp(final double v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDDFID$sp(final double v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDFIF$sp(final double v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDFII$sp(final double v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDDIDD$sp(final double v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDIDF$sp(final double v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDIDI$sp(final double v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDDIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDDIFD$sp(final double v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDIFF$sp(final double v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDIFI$sp(final double v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDDIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDDIID$sp(final double v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDDIIF$sp(final double v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDDIII$sp(final double v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDDIII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFDDD$sp(final double v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFDDF$sp(final double v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFDDI$sp(final double v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFDFD$sp(final double v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFDFF$sp(final double v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFDFI$sp(final double v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFDID$sp(final double v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFDIF$sp(final double v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFDII$sp(final double v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFFDD$sp(final double v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFFDF$sp(final double v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFFDI$sp(final double v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFFFD$sp(final double v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFFFF$sp(final double v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFFFI$sp(final double v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFFID$sp(final double v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFFIF$sp(final double v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFFII$sp(final double v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFIDD$sp(final double v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFIDF$sp(final double v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFIDI$sp(final double v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDFIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFIFD$sp(final double v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFIFF$sp(final double v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFIFI$sp(final double v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDFIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDFIID$sp(final double v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDFIIF$sp(final double v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDFIII$sp(final double v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDFIII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIDDD$sp(final double v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIDDF$sp(final double v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIDDI$sp(final double v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIDFD$sp(final double v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIDFF$sp(final double v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIDFI$sp(final double v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIDID$sp(final double v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIDIF$sp(final double v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIDII$sp(final double v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIFDD$sp(final double v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIFDF$sp(final double v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIFDI$sp(final double v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIFFD$sp(final double v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIFFF$sp(final double v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIFFI$sp(final double v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIFID$sp(final double v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIFIF$sp(final double v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIFII$sp(final double v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIIDD$sp(final double v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIIDF$sp(final double v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIIDI$sp(final double v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcDIIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIIFD$sp(final double v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIIFF$sp(final double v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIIFI$sp(final double v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcDIIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcDIIID$sp(final double v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcDIIIF$sp(final double v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcDIIII$sp(final double v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcDIIII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDDDD$sp(final float v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDDDF$sp(final float v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDDDI$sp(final float v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDDFD$sp(final float v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDDFF$sp(final float v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDDFI$sp(final float v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDDID$sp(final float v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDDIF$sp(final float v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDDII$sp(final float v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDFDD$sp(final float v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDFDF$sp(final float v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDFDI$sp(final float v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDFFD$sp(final float v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDFFF$sp(final float v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDFFI$sp(final float v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDFID$sp(final float v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDFIF$sp(final float v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDFII$sp(final float v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDIDD$sp(final float v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDIDF$sp(final float v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDIDI$sp(final float v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFDIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDIFD$sp(final float v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDIFF$sp(final float v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDIFI$sp(final float v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFDIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFDIID$sp(final float v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFDIIF$sp(final float v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFDIII$sp(final float v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFDIII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFDDD$sp(final float v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFDDF$sp(final float v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFDDI$sp(final float v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFDFD$sp(final float v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFDFF$sp(final float v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFDFI$sp(final float v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFDID$sp(final float v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFDIF$sp(final float v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFDII$sp(final float v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFFDD$sp(final float v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFFDF$sp(final float v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFFDI$sp(final float v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFFFD$sp(final float v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFFFF$sp(final float v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFFFI$sp(final float v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFFID$sp(final float v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFFIF$sp(final float v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFFII$sp(final float v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFIDD$sp(final float v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFIDF$sp(final float v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFIDI$sp(final float v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFFIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFIFD$sp(final float v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFIFF$sp(final float v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFIFI$sp(final float v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFFIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFFIID$sp(final float v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFFIIF$sp(final float v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFFIII$sp(final float v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFFIII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIDDD$sp(final float v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIDDF$sp(final float v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIDDI$sp(final float v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIDFD$sp(final float v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIDFF$sp(final float v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIDFI$sp(final float v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIDID$sp(final float v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIDIF$sp(final float v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIDII$sp(final float v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIFDD$sp(final float v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIFDF$sp(final float v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIFDI$sp(final float v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIFFD$sp(final float v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIFFF$sp(final float v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIFFI$sp(final float v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIFID$sp(final float v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIFIF$sp(final float v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIFII$sp(final float v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIIDD$sp(final float v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIIDF$sp(final float v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIIDI$sp(final float v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcFIIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIIFD$sp(final float v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIIFF$sp(final float v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIIFI$sp(final float v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcFIIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcFIIID$sp(final float v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcFIIIF$sp(final float v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcFIIII$sp(final float v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcFIIII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDDDD$sp(final int v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDDDF$sp(final int v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDDDI$sp(final int v, final double v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDDFD$sp(final int v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDDFF$sp(final int v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDDFI$sp(final int v, final double v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDDID$sp(final int v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDDIF$sp(final int v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDDII$sp(final int v, final double v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDFDD$sp(final int v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDFDF$sp(final int v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDFDI$sp(final int v, final double v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDFFD$sp(final int v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDFFF$sp(final int v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDFFI$sp(final int v, final double v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDFID$sp(final int v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDFIF$sp(final int v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDFII$sp(final int v, final double v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDIDD$sp(final int v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDIDF$sp(final int v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDIDI$sp(final int v, final double v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIDIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDIFD$sp(final int v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDIFF$sp(final int v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDIFI$sp(final int v, final double v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIDIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIDIID$sp(final int v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIDIIF$sp(final int v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIDIII$sp(final int v, final double v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIDIII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFDDD$sp(final int v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFDDF$sp(final int v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFDDI$sp(final int v, final float v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFDFD$sp(final int v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFDFF$sp(final int v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFDFI$sp(final int v, final float v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFDID$sp(final int v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFDIF$sp(final int v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFDII$sp(final int v, final float v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFFDD$sp(final int v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFFDF$sp(final int v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFFDI$sp(final int v, final float v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFFFD$sp(final int v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFFFF$sp(final int v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFFFI$sp(final int v, final float v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFFID$sp(final int v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFFIF$sp(final int v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFFII$sp(final int v, final float v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFIDD$sp(final int v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFIDF$sp(final int v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFIDI$sp(final int v, final float v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIFIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFIFD$sp(final int v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFIFF$sp(final int v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFIFI$sp(final int v, final float v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIFIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIFIID$sp(final int v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIFIIF$sp(final int v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIFIII$sp(final int v, final float v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIFIII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIDDD$sp(final int v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIDDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIDDF$sp(final int v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIDDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIDDI$sp(final int v, final int v2, final double v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIDDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIDFD$sp(final int v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIDFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIDFF$sp(final int v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIDFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIDFI$sp(final int v, final int v2, final double v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIDFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIDID$sp(final int v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIDID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIDIF$sp(final int v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIDIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIDII$sp(final int v, final int v2, final double v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIDII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIFDD$sp(final int v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIFDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIFDF$sp(final int v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIFDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIFDI$sp(final int v, final int v2, final float v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIFDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIFFD$sp(final int v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIFFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIFFF$sp(final int v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIFFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIFFI$sp(final int v, final int v2, final float v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIFFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIFID$sp(final int v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIFID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIFIF$sp(final int v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIFIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIFII$sp(final int v, final int v2, final float v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIFII$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIIDD$sp(final int v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIIDD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIIDF$sp(final int v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIIDF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIIDI$sp(final int v, final int v2, final int v3, final double v4) {
            return UFunc.UImpl4.apply$mcIIIDI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIIFD$sp(final int v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIIFD$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIIFF$sp(final int v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIIFF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIIFI$sp(final int v, final int v2, final int v3, final float v4) {
            return UFunc.UImpl4.apply$mcIIIFI$sp$(this, v, v2, v3, v4);
         }

         public double apply$mcIIIID$sp(final int v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIIID$sp$(this, v, v2, v3, v4);
         }

         public float apply$mcIIIIF$sp(final int v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIIIF$sp$(this, v, v2, v3, v4);
         }

         public int apply$mcIIIII$sp(final int v, final int v2, final int v3, final int v4) {
            return UFunc.UImpl4.apply$mcIIIII$sp$(this, v, v2, v3, v4);
         }

         public hist.Histogram apply(final Object v, final int bins, final Tuple2 range, final Object weights) {
            if (range != null) {
               double minimum = range._1$mcD$sp();
               double maximum = range._2$mcD$sp();
               Tuple2.mcDD.sp var5 = new Tuple2.mcDD.sp(minimum, maximum);
               double minimum = ((Tuple2)var5)._1$mcD$sp();
               double maximum = ((Tuple2)var5)._2$mcD$sp();
               if (maximum <= minimum) {
                  throw new IllegalArgumentException("Minimum of a histogram must not be greater than the maximum");
               } else {
                  DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(bins, .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
                  CanZipAndTraverseValues.PairValuesVisitor visitor = new CanZipAndTraverseValues.PairValuesVisitor(bins, minimum, maximum, result) {
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;
                     private final int bins$2;
                     private final double minimum$2;
                     private final double maximum$2;
                     private final DenseVector result$2;

                     public void visitArray(final Object arr, final Object arr2) {
                        CanZipAndTraverseValues.PairValuesVisitor.visitArray$(this, arr, arr2);
                     }

                     public void visit(final Object a, final double w) {
                        double ad = BoxesRunTime.unboxToDouble(this.$outer.conv$4.apply(a));
                        int i = (int)scala.math.package..MODULE$.floor((double)this.bins$2 * ((ad - this.minimum$2) / (this.maximum$2 - this.minimum$2)));
                        if (i >= 0 && i < this.bins$2) {
                           this.result$2.update$mcD$sp(i, this.result$2.apply$mcD$sp(i) + w);
                        }

                        if (ad == this.maximum$2) {
                           int var7 = this.bins$2 - 1;
                           this.result$2.update$mcD$sp(var7, this.result$2.apply$mcD$sp(var7) + w);
                        }

                     }

                     public {
                        if (<VAR_NAMELESS_ENCLOSURE> == null) {
                           throw null;
                        } else {
                           this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                           this.bins$2 = bins$2;
                           this.minimum$2 = minimum$2;
                           this.maximum$2 = maximum$2;
                           this.result$2 = result$2;
                           CanZipAndTraverseValues.PairValuesVisitor.$init$(this);
                        }
                     }
                  };
                  this.iter$2.traverse(v, weights, visitor);
                  return new hist.Histogram(result, minimum, maximum, (double)bins);
               }
            } else {
               throw new MatchError(range);
            }
         }

         public {
            this.conv$4 = conv$4;
            this.iter$2 = iter$2;
         }
      };
   }

   public UFunc.UImpl3 canTraverseValuesImpl$mDc$sp(final CanTraverseValues iter, final WideningConversion conv) {
      return new UFunc.UImpl3(conv, iter) {
         public final WideningConversion conv$5;
         private final CanTraverseValues iter$3;

         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public hist.Histogram apply(final Object v, final int bins, final Tuple2 range) {
            if (range != null) {
               double minimum = range._1$mcD$sp();
               double maximum = range._2$mcD$sp();
               Tuple2.mcDD.sp var4 = new Tuple2.mcDD.sp(minimum, maximum);
               double minimum = ((Tuple2)var4)._1$mcD$sp();
               double maximum = ((Tuple2)var4)._2$mcD$sp();
               if (maximum <= minimum) {
                  throw new IllegalArgumentException("Minimum of a histogram must not be greater than the maximum");
               } else {
                  DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(bins, .MODULE$.Int(), Zero$.MODULE$.IntZero());
                  CanTraverseValues.ValuesVisitor visitor = new CanTraverseValues$ValuesVisitor$mcD$sp(bins, minimum, maximum, result) {
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;
                     private final int bins$3;
                     private final double minimum$3;
                     private final double maximum$3;
                     private final DenseVector result$3;

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

                     public int breeze$stats$hist$$anon$$anon$$binOf(final double v) {
                        return (int)scala.math.package..MODULE$.floor((double)this.bins$3 * ((v - this.minimum$3) / (this.maximum$3 - this.minimum$3)));
                     }

                     public void visit$mcD$sp(final double a) {
                        double ad = BoxesRunTime.unboxToDouble(this.$outer.conv$5.apply(BoxesRunTime.boxToDouble(a)));
                        int i = this.breeze$stats$hist$$anon$$anon$$binOf(ad);
                        if (i >= 0 && i < this.bins$3) {
                           this.result$3.update$mcI$sp(i, this.result$3.apply$mcI$sp(i) + 1);
                        }

                        if (ad == this.maximum$3) {
                           int var6 = this.bins$3 - 1;
                           this.result$3.update$mcI$sp(var6, this.result$3.apply$mcI$sp(var6) + 1);
                        }

                     }

                     public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                        double ad = BoxesRunTime.unboxToDouble(this.$outer.conv$5.apply(BoxesRunTime.boxToDouble(zeroValue)));
                        int i = this.breeze$stats$hist$$anon$$anon$$binOf(ad);
                        if (i >= 0 && i < this.bins$3) {
                           this.result$3.update$mcI$sp(i, this.result$3.apply$mcI$sp(i) + numZero);
                        }

                        if (ad == this.maximum$3) {
                           int var7 = this.bins$3 - 1;
                           this.result$3.update$mcI$sp(var7, this.result$3.apply$mcI$sp(var7) + 1);
                        }

                     }

                     public {
                        if (<VAR_NAMELESS_ENCLOSURE> == null) {
                           throw null;
                        } else {
                           this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                           this.bins$3 = bins$3;
                           this.minimum$3 = minimum$3;
                           this.maximum$3 = maximum$3;
                           this.result$3 = result$3;
                           CanTraverseValues.ValuesVisitor.$init$(this);
                        }
                     }
                  };
                  this.iter$3.traverse(v, visitor);
                  return new hist.Histogram(result, minimum, maximum, (double)bins);
               }
            } else {
               throw new MatchError(range);
            }
         }

         public {
            this.conv$5 = conv$5;
            this.iter$3 = iter$3;
         }
      };
   }

   public UFunc.UImpl3 canTraverseValuesImpl$mFc$sp(final CanTraverseValues iter, final WideningConversion conv) {
      return new UFunc.UImpl3(conv, iter) {
         public final WideningConversion conv$6;
         private final CanTraverseValues iter$4;

         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public hist.Histogram apply(final Object v, final int bins, final Tuple2 range) {
            if (range != null) {
               double minimum = range._1$mcD$sp();
               double maximum = range._2$mcD$sp();
               Tuple2.mcDD.sp var4 = new Tuple2.mcDD.sp(minimum, maximum);
               double minimum = ((Tuple2)var4)._1$mcD$sp();
               double maximum = ((Tuple2)var4)._2$mcD$sp();
               if (maximum <= minimum) {
                  throw new IllegalArgumentException("Minimum of a histogram must not be greater than the maximum");
               } else {
                  DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(bins, .MODULE$.Int(), Zero$.MODULE$.IntZero());
                  CanTraverseValues.ValuesVisitor visitor = new CanTraverseValues$ValuesVisitor$mcF$sp(bins, minimum, maximum, result) {
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;
                     private final int bins$4;
                     private final double minimum$4;
                     private final double maximum$4;
                     private final DenseVector result$4;

                     public void visitArray(final float[] arr) {
                        CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$(this, arr);
                     }

                     public void visitArray$mcF$sp(final float[] arr) {
                        CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$mcF$sp$(this, arr);
                     }

                     public void visitArray(final float[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$mcF$sp$(this, arr, offset, length, stride);
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

                     public void visit$mcD$sp(final double a) {
                        CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
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

                     public void visitArray$mcD$sp(final double[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
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

                     public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
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

                     public void visit(final float a) {
                        this.visit$mcF$sp(a);
                     }

                     public void zeros(final int numZero, final float zeroValue) {
                        this.zeros$mcF$sp(numZero, zeroValue);
                     }

                     public int breeze$stats$hist$$anon$$anon$$binOf(final double v) {
                        return (int)scala.math.package..MODULE$.floor((double)this.bins$4 * ((v - this.minimum$4) / (this.maximum$4 - this.minimum$4)));
                     }

                     public void visit$mcF$sp(final float a) {
                        double ad = this.$outer.conv$6.apply$mcFD$sp(a);
                        int i = this.breeze$stats$hist$$anon$$anon$$binOf(ad);
                        if (i >= 0 && i < this.bins$4) {
                           this.result$4.update$mcI$sp(i, this.result$4.apply$mcI$sp(i) + 1);
                        }

                        if (ad == this.maximum$4) {
                           int var5 = this.bins$4 - 1;
                           this.result$4.update$mcI$sp(var5, this.result$4.apply$mcI$sp(var5) + 1);
                        }

                     }

                     public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                        double ad = this.$outer.conv$6.apply$mcFD$sp(zeroValue);
                        int i = this.breeze$stats$hist$$anon$$anon$$binOf(ad);
                        if (i >= 0 && i < this.bins$4) {
                           this.result$4.update$mcI$sp(i, this.result$4.apply$mcI$sp(i) + numZero);
                        }

                        if (ad == this.maximum$4) {
                           int var6 = this.bins$4 - 1;
                           this.result$4.update$mcI$sp(var6, this.result$4.apply$mcI$sp(var6) + 1);
                        }

                     }

                     public {
                        if (<VAR_NAMELESS_ENCLOSURE> == null) {
                           throw null;
                        } else {
                           this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                           this.bins$4 = bins$4;
                           this.minimum$4 = minimum$4;
                           this.maximum$4 = maximum$4;
                           this.result$4 = result$4;
                           CanTraverseValues.ValuesVisitor.$init$(this);
                        }
                     }
                  };
                  this.iter$4.traverse(v, visitor);
                  return new hist.Histogram(result, minimum, maximum, (double)bins);
               }
            } else {
               throw new MatchError(range);
            }
         }

         public {
            this.conv$6 = conv$6;
            this.iter$4 = iter$4;
         }
      };
   }

   public UFunc.UImpl3 canTraverseValuesImpl$mIc$sp(final CanTraverseValues iter, final WideningConversion conv) {
      return new UFunc.UImpl3(conv, iter) {
         public final WideningConversion conv$7;
         private final CanTraverseValues iter$5;

         public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
         }

         public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
         }

         public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
         }

         public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
         }

         public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
         }

         public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
         }

         public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
         }

         public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
         }

         public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
         }

         public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
         }

         public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
         }

         public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
         }

         public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
            return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
            return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
         }

         public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
            return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
         }

         public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
            return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
            return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
         }

         public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
            return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
         }

         public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
            return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
         }

         public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
            return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
         }

         public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
         }

         public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
         }

         public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
            return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
         }

         public hist.Histogram apply(final Object v, final int bins, final Tuple2 range) {
            if (range != null) {
               double minimum = range._1$mcD$sp();
               double maximum = range._2$mcD$sp();
               Tuple2.mcDD.sp var4 = new Tuple2.mcDD.sp(minimum, maximum);
               double minimum = ((Tuple2)var4)._1$mcD$sp();
               double maximum = ((Tuple2)var4)._2$mcD$sp();
               if (maximum <= minimum) {
                  throw new IllegalArgumentException("Minimum of a histogram must not be greater than the maximum");
               } else {
                  DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(bins, .MODULE$.Int(), Zero$.MODULE$.IntZero());
                  CanTraverseValues.ValuesVisitor visitor = new CanTraverseValues$ValuesVisitor$mcI$sp(bins, minimum, maximum, result) {
                     // $FF: synthetic field
                     private final <undefinedtype> $outer;
                     private final int bins$5;
                     private final double minimum$5;
                     private final double maximum$5;
                     private final DenseVector result$5;

                     public void visitArray(final int[] arr) {
                        CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$(this, arr);
                     }

                     public void visitArray$mcI$sp(final int[] arr) {
                        CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$mcI$sp$(this, arr);
                     }

                     public void visitArray(final int[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$mcI$sp$(this, arr, offset, length, stride);
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

                     public void visit$mcD$sp(final double a) {
                        CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
                     }

                     public void visit$mcF$sp(final float a) {
                        CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
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

                     public void visitArray$mcD$sp(final double[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
                     }

                     public void visitArray$mcF$sp(final float[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
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

                     public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
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

                     public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
                     }

                     public void visit(final int a) {
                        this.visit$mcI$sp(a);
                     }

                     public void zeros(final int numZero, final int zeroValue) {
                        this.zeros$mcI$sp(numZero, zeroValue);
                     }

                     public int breeze$stats$hist$$anon$$anon$$binOf(final double v) {
                        return (int)scala.math.package..MODULE$.floor((double)this.bins$5 * ((v - this.minimum$5) / (this.maximum$5 - this.minimum$5)));
                     }

                     public void visit$mcI$sp(final int a) {
                        double ad = this.$outer.conv$7.apply$mcID$sp(a);
                        int i = this.breeze$stats$hist$$anon$$anon$$binOf(ad);
                        if (i >= 0 && i < this.bins$5) {
                           this.result$5.update$mcI$sp(i, this.result$5.apply$mcI$sp(i) + 1);
                        }

                        if (ad == this.maximum$5) {
                           int var5 = this.bins$5 - 1;
                           this.result$5.update$mcI$sp(var5, this.result$5.apply$mcI$sp(var5) + 1);
                        }

                     }

                     public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                        double ad = this.$outer.conv$7.apply$mcID$sp(zeroValue);
                        int i = this.breeze$stats$hist$$anon$$anon$$binOf(ad);
                        if (i >= 0 && i < this.bins$5) {
                           this.result$5.update$mcI$sp(i, this.result$5.apply$mcI$sp(i) + numZero);
                        }

                        if (ad == this.maximum$5) {
                           int var6 = this.bins$5 - 1;
                           this.result$5.update$mcI$sp(var6, this.result$5.apply$mcI$sp(var6) + 1);
                        }

                     }

                     public {
                        if (<VAR_NAMELESS_ENCLOSURE> == null) {
                           throw null;
                        } else {
                           this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                           this.bins$5 = bins$5;
                           this.minimum$5 = minimum$5;
                           this.maximum$5 = maximum$5;
                           this.result$5 = result$5;
                           CanTraverseValues.ValuesVisitor.$init$(this);
                        }
                     }
                  };
                  this.iter$5.traverse(v, visitor);
                  return new hist.Histogram(result, minimum, maximum, (double)bins);
               }
            } else {
               throw new MatchError(range);
            }
         }

         public {
            this.conv$7 = conv$7;
            this.iter$5 = iter$5;
         }
      };
   }

   private hist$() {
   }
}
