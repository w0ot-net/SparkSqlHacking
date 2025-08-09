package breeze.stats;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00051;Q\u0001C\u0005\t\u000291Q\u0001E\u0005\t\u0002EAQAH\u0001\u0005\u0002}AQ\u0001I\u0001\u0005\u0004\u0005:QaP\u0001\t\u0002\u00013Q!Q\u0001\t\u0002\tCQAH\u0003\u0005\u0002\rCQ\u0001I\u0003\u0005\u0004\u0011\u000b\u0001B^1sS\u0006t7-\u001a\u0006\u0003\u0015-\tQa\u001d;biNT\u0011\u0001D\u0001\u0007EJ,WM_3\u0004\u0001A\u0011q\"A\u0007\u0002\u0013\tAa/\u0019:jC:\u001cWmE\u0002\u0002%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007CA\r\u001d\u001b\u0005Q\"BA\u000e\f\u0003\u001d9WM\\3sS\u000eL!!\b\u000e\u0003\u000bU3UO\\2\u0002\rqJg.\u001b;?)\u0005q\u0011\u0001\u0004:fIV\u001cW\rR8vE2,WC\u0001\u0012*)\t\u0019S\u0007\u0005\u0003%K\u001d\u0012T\"A\u0001\n\u0005\u0019b\"\u0001B%na2\u0004\"\u0001K\u0015\r\u0001\u0011)!f\u0001b\u0001W\t\tA+\u0005\u0002-_A\u00111#L\u0005\u0003]Q\u0011qAT8uQ&tw\r\u0005\u0002\u0014a%\u0011\u0011\u0007\u0006\u0002\u0004\u0003:L\bCA\n4\u0013\t!DC\u0001\u0004E_V\u0014G.\u001a\u0005\u0006m\r\u0001\u001daN\u0001\u0003[Z\u0004B\u0001O\u0013(w9\u0011q\"O\u0005\u0003u%\tq\"\\3b]\u0006sGMV1sS\u0006t7-\u001a\t\u0003qqJ!!\u0010 \u0003\u001f5+\u0017M\\!oIZ\u000b'/[1oG\u0016T!AO\u0005\u0002\u0015A|\u0007/\u001e7bi&|g\u000e\u0005\u0002%\u000b\tQ\u0001o\u001c9vY\u0006$\u0018n\u001c8\u0014\u0007\u0015\u0011\u0002\u0004F\u0001A+\t)\u0015\n\u0006\u0002G\u0015B!q)\n%3\u001b\u0005)\u0001C\u0001\u0015J\t\u0015QsA1\u0001,\u0011\u00151t\u0001q\u0001L!\u0011AT\u0005S\u001e"
)
public final class variance {
   public static UFunc.UImpl reduceDouble(final UFunc.UImpl mv) {
      return variance$.MODULE$.reduceDouble(mv);
   }

   public static Object withSink(final Object s) {
      return variance$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return variance$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return variance$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return variance$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return variance$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return variance$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return variance$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return variance$.MODULE$.apply(v, impl);
   }

   public static class population$ implements UFunc {
      public static final population$ MODULE$ = new population$();

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

      public UFunc.UImpl reduceDouble(final UFunc.UImpl mv) {
         return new UFunc.UImpl(mv) {
            private final UFunc.UImpl mv$2;

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

            public double apply(final Object v) {
               return ((meanAndVariance.MeanAndVariance)this.mv$2.apply(v)).populationVariance();
            }

            public {
               this.mv$2 = mv$2;
            }
         };
      }
   }
}
