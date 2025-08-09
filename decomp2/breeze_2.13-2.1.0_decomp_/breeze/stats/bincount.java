package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.Counter;
import breeze.linalg.Counter$;
import breeze.linalg.DenseVector;
import breeze.linalg.SparseVector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.linalg.VectorBuilder$mcF$sp;
import breeze.linalg.VectorBuilder$mcI$sp;
import breeze.linalg.max$;
import breeze.linalg.min$;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcI$sp;
import breeze.math.Complex;
import breeze.math.Complex$;
import breeze.math.Semiring$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005q<QAD\b\t\u0002Q1QAF\b\t\u0002]AQ\u0001J\u0001\u0005\u0002\u0015BQAJ\u0001\u0005\u0004\u001dBQ!O\u0001\u0005\u0004iBQaQ\u0001\u0005\u0004\u0011CQAS\u0001\u0005\u0004-;QaY\u0001\t\u0002\u00114Q!Z\u0001\t\u0002\u0019DQ\u0001\n\u0005\u0005\u0002\u001dDQA\n\u0005\u0005\u0004!DQ!\u000f\u0005\u0005\u00049DQa\u0011\u0005\u0005\u0004EDQA\u0013\u0005\u0005\u0004Q\f\u0001BY5oG>,h\u000e\u001e\u0006\u0003!E\tQa\u001d;biNT\u0011AE\u0001\u0007EJ,WM_3\u0004\u0001A\u0011Q#A\u0007\u0002\u001f\tA!-\u001b8d_VtGoE\u0002\u00021y\u0001\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0007CA\u0010#\u001b\u0005\u0001#BA\u0011\u0012\u0003\u001d9WM\\3sS\u000eL!a\t\u0011\u0003\u000bU3UO\\2\u0002\rqJg.\u001b;?)\u0005!\u0012!\u0005<fGZ+'o]5p]~#u.\u001e2mKV\t\u0001\u0006E\u0003*U1*T'D\u0001\u0002\u0013\tY#EA\u0003J[Bd'\u0007E\u0002.aIj\u0011A\f\u0006\u0003_E\ta\u0001\\5oC2<\u0017BA\u0019/\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u0005e\u0019\u0014B\u0001\u001b\u001b\u0005\rIe\u000e\u001e\t\u0004[A2\u0004CA\r8\u0013\tA$D\u0001\u0004E_V\u0014G.Z\u0001\u0013m\u0016\u001cg+\u001a:tS>twlQ8na2,\u00070F\u0001<!\u0015I#\u0006\f\u001f=!\ri\u0003'\u0010\t\u0003}\u0005k\u0011a\u0010\u0006\u0003\u0001F\tA!\\1uQ&\u0011!i\u0010\u0002\b\u0007>l\u0007\u000f\\3y\u0003A1Xm\u0019,feNLwN\\0GY>\fG/F\u0001F!\u0015I#\u0006\f$G!\ri\u0003g\u0012\t\u00033!K!!\u0013\u000e\u0003\u000b\u0019cw.\u0019;\u0002\rI,G-^2f+\ta%\u000b\u0006\u0002N7B!\u0011F\u0014)-\u0013\ty%E\u0001\u0003J[Bd\u0007CA)S\u0019\u0001!Qa\u0015\u0004C\u0002Q\u0013\u0011\u0001V\t\u0003+b\u0003\"!\u0007,\n\u0005]S\"a\u0002(pi\"Lgn\u001a\t\u00033eK!A\u0017\u000e\u0003\u0007\u0005s\u0017\u0010C\u0003]\r\u0001\u000fQ,\u0001\u0003ji\u0016\u0014\b\u0003\u00020b!Jj\u0011a\u0018\u0006\u0003A:\nqa];qa>\u0014H/\u0003\u0002c?\n\t2)\u00198Ue\u00064XM]:f-\u0006dW/Z:\u0002\rM\u0004\u0018M]:f!\tI\u0003B\u0001\u0004ta\u0006\u00148/Z\n\u0004\u0011aqB#\u00013\u0016\u0003%\u0004RA\u001b\u0016-k-l\u0011\u0001\u0003\t\u0004[14\u0014BA7/\u00051\u0019\u0006/\u0019:tKZ+7\r^8s+\u0005y\u0007#\u00026+Yq\u0002\bcA\u0017m{U\t!\u000fE\u0003kU125\u000fE\u0002.Y\u001e+\"!\u001e=\u0015\u0005YT\b\u0003\u00026Oof\u0004\"!\u0015=\u0005\u000bMk!\u0019\u0001+\u0011\u00075b'\u0007C\u0003]\u001b\u0001\u000f1\u0010\u0005\u0003_C^\u0014\u0004"
)
public final class bincount {
   public static UFunc.UImpl reduce(final CanTraverseValues iter) {
      return bincount$.MODULE$.reduce(iter);
   }

   public static UFunc.UImpl2 vecVersion_Float() {
      return bincount$.MODULE$.vecVersion_Float();
   }

   public static UFunc.UImpl2 vecVersion_Complex() {
      return bincount$.MODULE$.vecVersion_Complex();
   }

   public static UFunc.UImpl2 vecVersion_Double() {
      return bincount$.MODULE$.vecVersion_Double();
   }

   public static Object withSink(final Object s) {
      return bincount$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return bincount$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return bincount$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return bincount$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return bincount$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return bincount$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return bincount$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return bincount$.MODULE$.apply(v, impl);
   }

   public static class sparse$ implements UFunc {
      public static final sparse$ MODULE$ = new sparse$();

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

      public UFunc.UImpl2 vecVersion_Double() {
         return new UFunc.UImpl2() {
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

            public SparseVector apply(final DenseVector x, final DenseVector weights) {
               .MODULE$.require(BoxesRunTime.unboxToInt(min$.MODULE$.apply(x, min$.MODULE$.reduce_Int(HasOps$.MODULE$.DV_canIterateValues()))) >= 0);
               .MODULE$.require(x.length() == weights.length());
               Counter counter = Counter$.MODULE$.apply(Zero$.MODULE$.DoubleZero());
               int index$macro$2 = 0;

               for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  counter.update(BoxesRunTime.boxToInteger(x.apply$mcI$sp(index$macro$2)), BoxesRunTime.boxToDouble(BoxesRunTime.unboxToDouble(counter.apply(BoxesRunTime.boxToInteger(x.apply$mcI$sp(index$macro$2)))) + weights.apply$mcD$sp(index$macro$2)));
               }

               VectorBuilder builder = new VectorBuilder$mcD$sp(BoxesRunTime.unboxToInt(max$.MODULE$.apply(x, max$.MODULE$.reduce_Int(HasOps$.MODULE$.DV_canIterateValues()))) + 1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double());
               counter.iterator().foreach((xx) -> {
                  $anonfun$apply$5(builder, xx);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcD$sp();
            }

            // $FF: synthetic method
            public static final void $anonfun$apply$5(final VectorBuilder builder$1, final Tuple2 x) {
               builder$1.add$mcD$sp(x._1$mcI$sp(), x._2$mcD$sp());
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public UFunc.UImpl2 vecVersion_Complex() {
         return new UFunc.UImpl2() {
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

            public SparseVector apply(final DenseVector x, final DenseVector weights) {
               .MODULE$.require(BoxesRunTime.unboxToInt(min$.MODULE$.apply(x, min$.MODULE$.reduce_Int(HasOps$.MODULE$.DV_canIterateValues()))) >= 0);
               .MODULE$.require(x.length() == weights.length());
               Counter counter = Counter$.MODULE$.apply(Complex$.MODULE$.ComplexZero());
               int index$macro$2 = 0;

               for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  counter.update(BoxesRunTime.boxToInteger(x.apply$mcI$sp(index$macro$2)), ((Complex)counter.apply(BoxesRunTime.boxToInteger(x.apply$mcI$sp(index$macro$2)))).$plus((Complex)weights.apply(index$macro$2)));
               }

               VectorBuilder builder = new VectorBuilder(BoxesRunTime.unboxToInt(max$.MODULE$.apply(x, max$.MODULE$.reduce_Int(HasOps$.MODULE$.DV_canIterateValues()))) + 1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Complex.scalar$.MODULE$, scala.reflect.ClassTag..MODULE$.apply(Complex.class));
               counter.iterator().foreach((xx) -> {
                  $anonfun$apply$6(builder, xx);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector();
            }

            // $FF: synthetic method
            public static final void $anonfun$apply$6(final VectorBuilder builder$2, final Tuple2 x) {
               builder$2.add(x._1$mcI$sp(), x._2());
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public UFunc.UImpl2 vecVersion_Float() {
         return new UFunc.UImpl2() {
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

            public SparseVector apply(final DenseVector x, final DenseVector weights) {
               .MODULE$.require(BoxesRunTime.unboxToInt(min$.MODULE$.apply(x, min$.MODULE$.reduce_Int(HasOps$.MODULE$.DV_canIterateValues()))) >= 0);
               .MODULE$.require(x.length() == weights.length());
               Counter counter = Counter$.MODULE$.apply(Zero$.MODULE$.FloatZero());
               int index$macro$2 = 0;

               for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  counter.update(BoxesRunTime.boxToInteger(x.apply$mcI$sp(index$macro$2)), BoxesRunTime.boxToFloat(BoxesRunTime.unboxToFloat(counter.apply(BoxesRunTime.boxToInteger(x.apply$mcI$sp(index$macro$2)))) + weights.apply$mcF$sp(index$macro$2)));
               }

               VectorBuilder builder = new VectorBuilder$mcF$sp(BoxesRunTime.unboxToInt(max$.MODULE$.apply(x, max$.MODULE$.reduce_Int(HasOps$.MODULE$.DV_canIterateValues()))) + 1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringFloat(), scala.reflect.ClassTag..MODULE$.Float());
               counter.iterator().foreach((xx) -> {
                  $anonfun$apply$7(builder, xx);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcF$sp();
            }

            // $FF: synthetic method
            public static final void $anonfun$apply$7(final VectorBuilder builder$3, final Tuple2 x) {
               builder$3.add$mcF$sp(x._1$mcI$sp(), BoxesRunTime.unboxToFloat(x._2()));
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public UFunc.UImpl reduce(final CanTraverseValues iter) {
         return new UFunc.UImpl(iter) {
            private final CanTraverseValues iter$15;

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

            public SparseVector apply(final Object x) {
               .MODULE$.require(BoxesRunTime.unboxToInt(min$.MODULE$.apply(x, min$.MODULE$.reduce_Int(this.iter$15))) >= 0);
               Counter counter = Counter$.MODULE$.apply(Zero$.MODULE$.IntZero());

               class BincountVisitor$2 implements CanTraverseValues$ValuesVisitor$mcI$sp {
                  private final Counter counter$1;

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

                  public void visit$mcI$sp(final int a) {
                     this.counter$1.update(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.counter$1.apply(BoxesRunTime.boxToInteger(a))) + 1));
                  }

                  public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                     this.counter$1.update(BoxesRunTime.boxToInteger(zeroValue), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.counter$1.apply(BoxesRunTime.boxToInteger(zeroValue))) + numZero));
                  }

                  public BincountVisitor$2(final Counter counter$1) {
                     this.counter$1 = counter$1;
                     CanTraverseValues.ValuesVisitor.$init$(this);
                  }
               }

               this.iter$15.traverse(x, new BincountVisitor$2(counter));
               VectorBuilder builder = new VectorBuilder$mcI$sp(BoxesRunTime.unboxToInt(max$.MODULE$.apply(x, max$.MODULE$.reduce_Int(this.iter$15))) + 1, VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringInt(), scala.reflect.ClassTag..MODULE$.Int());
               counter.iterator().foreach((xx) -> {
                  $anonfun$apply$8(builder, xx);
                  return BoxedUnit.UNIT;
               });
               return builder.toSparseVector$mcI$sp();
            }

            // $FF: synthetic method
            public static final void $anonfun$apply$8(final VectorBuilder builder$4, final Tuple2 x) {
               builder$4.add$mcI$sp(x._1$mcI$sp(), x._2$mcI$sp());
            }

            public {
               this.iter$15 = iter$15;
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }
   }
}
