package breeze.interpolation;

import breeze.generic.MappingUFunc;
import breeze.generic.UFunc;
import breeze.generic.VariableUFunc;
import breeze.linalg.Vector;
import breeze.math.Field;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%t!B\u000f\u001f\u0011\u0003\u0019c!B\u0013\u001f\u0011\u00031\u0003\"B\u0017\u0002\t\u0003qs!B\u0018\u0002\u0011\u0003\u0001d!\u0002\u001a\u0002\u0011\u0003\u0019\u0004\"B\u0017\u0005\t\u0003Q\u0004\"B\u001e\u0005\t\u0007adaB#\u0002!\u0003\r\nA\u0012\u0005\u00061\u001e1\t!\u0017\u0004\u0006=\u0006\t\ta\u0018\u0005\tI&\u0011\t\u0011)A\u0005K\"A1.\u0003B\u0001B\u0003%Q\r\u0003\u0005m\u0013\t\r\t\u0015a\u0003n\u0011!\u0019\u0018BaA!\u0002\u0017!\b\u0002\u0003>\n\u0005\u0007\u0005\u000b1B>\t\r5JA\u0011AA\u0007\u0011%\ti\"\u0003b\u0001\n\u0013\ty\u0002\u0003\u0005\u0002.%\u0001\u000b\u0011BA\u0011\u0011%\ty#\u0003b\u0001\n#\t\t\u0004\u0003\u0005\u00026%\u0001\u000b\u0011BA\u001a\u0011%\t9$\u0003b\u0001\n#\t\t\u0004\u0003\u0005\u0002:%\u0001\u000b\u0011BA\u001a\u0011%\tY$\u0003b\u0001\n\u0013\ti\u0004C\u0004\u0002@%\u0001\u000b\u0011B>\t\raKA\u0011AA!\u0011\u001d\t)%\u0003D\t\u0003\u000fBq!a\u0013\n\t#\ti\u0005C\u0004\u0002R%!\t\"a\u0015\t\u000f\u0005E\u0013\u0002\"\u0003\u0002^\u00059\u0001/Y2lC\u001e,'BA\u0010!\u00035Ig\u000e^3sa>d\u0017\r^5p]*\t\u0011%\u0001\u0004ce\u0016,'0Z\u0002\u0001!\t!\u0013!D\u0001\u001f\u0005\u001d\u0001\u0018mY6bO\u0016\u001c\"!A\u0014\u0011\u0005!ZS\"A\u0015\u000b\u0003)\nQa]2bY\u0006L!\u0001L\u0015\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t1%\u0001\u000eV]&4\u0018M]5bi\u0016Le\u000e^3sa>d\u0017\r^8s\u00136\u0004H\u000e\u0005\u00022\t5\t\u0011A\u0001\u000eV]&4\u0018M]5bi\u0016Le\u000e^3sa>d\u0017\r^8s\u00136\u0004HnE\u0002\u0005OQ\u0002\"!\u000e\u001d\u000e\u0003YR!a\u000e\u0011\u0002\u000f\u001d,g.\u001a:jG&\u0011\u0011H\u000e\u0002\r\u001b\u0006\u0004\b/\u001b8h+\u001a+hn\u0019\u000b\u0002a\u0005!\u0011.\u001c9m+\tiT,F\u0001?!\u0015y\u0004\t\u0012/]\u001b\u0005!\u0011BA!C\u0005\u0015IU\u000e\u001d73\u0013\t\u0019eGA\u0003V\rVt7\rE\u00022\u000fq\u0013a#\u00168jm\u0006\u0014\u0018.\u0019;f\u0013:$XM\u001d9pY\u0006$xN]\u000b\u0003\u000f>\u001b2aB\u0014I!\u0011)\u0014j\u0013'\n\u0005)3$!\u0004,be&\f'\r\\3V\rVt7M\u0004\u00022\u0007A\u0019\u0011gB'\u0011\u00059{E\u0002\u0001\u0003\u0006!\u001e\u0011\r!\u0015\u0002\u0002)F\u0011!+\u0016\t\u0003QMK!\u0001V\u0015\u0003\u000f9{G\u000f[5oOB\u0011\u0001FV\u0005\u0003/&\u00121!\u00118z\u0003\u0015\t\u0007\u000f\u001d7z)\ti%\fC\u0003\\\u0011\u0001\u0007Q*A\u0001y!\tqU\fB\u0003Q\r\t\u0007\u0011KA\u000eIC:$\u00170\u00168jm\u0006\u0014\u0018.\u0019;f\u0013:$XM\u001d9pY\u0006$xN]\u000b\u0003A\u000e\u001c2!C\u0014b!\r\ttA\u0019\t\u0003\u001d\u000e$Q\u0001U\u0005C\u0002E\u000b\u0001\u0002_0d_>\u0014Hm\u001d\t\u0004M&\u0014W\"A4\u000b\u0005!\u0004\u0013A\u00027j]\u0006dw-\u0003\u0002kO\n1a+Z2u_J\f\u0001\"_0d_>\u0014Hm]\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004c\u00018rE6\tqN\u0003\u0002qS\u00059!/\u001a4mK\u000e$\u0018B\u0001:p\u0005!\u0019E.Y:t)\u0006<\u0017AC3wS\u0012,gnY3%eA\u0019Q\u000f\u001f2\u000e\u0003YT!a\u001e\u0011\u0002\t5\fG\u000f[\u0005\u0003sZ\u0014QAR5fY\u0012\f!\"\u001a<jI\u0016t7-\u001a\u00134!\u0011a\u0018q\u00012\u000f\u0007u\f)AD\u0002\u007f\u0003\u0007i\u0011a \u0006\u0004\u0003\u0003\u0011\u0013A\u0002\u001fs_>$h(C\u0001+\u0013\ti\u0012&\u0003\u0003\u0002\n\u0005-!\u0001C(sI\u0016\u0014\u0018N\\4\u000b\u0005uICCBA\b\u00033\tY\u0002\u0006\u0005\u0002\u0012\u0005M\u0011QCA\f!\r\t\u0014B\u0019\u0005\u0006Y>\u0001\u001d!\u001c\u0005\u0006g>\u0001\u001d\u0001\u001e\u0005\u0006u>\u0001\u001da\u001f\u0005\u0006I>\u0001\r!\u001a\u0005\u0006W>\u0001\r!Z\u0001\u0006]>$Wm]\u000b\u0003\u0003C\u0001R\u0001KA\u0012\u0003OI1!!\n*\u0005\u0015\t%O]1z!\u0015A\u0013\u0011\u00062c\u0013\r\tY#\u000b\u0002\u0007)V\u0004H.\u001a\u001a\u0002\r9|G-Z:!\u0003\u0005AVCAA\u001a!\u0011A\u00131\u00052\u0002\u0005a\u0003\u0013!A-\u0002\u0005e\u0003\u0013aA8sIV\t10\u0001\u0003pe\u0012\u0004Cc\u00012\u0002D!)1\f\u0007a\u0001E\u0006Y\u0011N\u001c;feB|G.\u0019;f)\r\u0011\u0017\u0011\n\u0005\u00067f\u0001\rAY\u0001\fKb$(/\u00199pY\u0006$X\rF\u0002c\u0003\u001fBQa\u0017\u000eA\u0002\t\f\u0001BY5tK\u0006\u00148\r\u001b\u000b\u0005\u0003+\nY\u0006E\u0002)\u0003/J1!!\u0017*\u0005\rIe\u000e\u001e\u0005\u00067n\u0001\rA\u0019\u000b\t\u0003+\ny&a\u0019\u0002h!9\u0011\u0011\r\u000fA\u0002\u0005U\u0013a\u00017po\"9\u0011Q\r\u000fA\u0002\u0005U\u0013\u0001\u00025jO\"DQa\u0017\u000fA\u0002\t\u0004"
)
public final class package {
   public static class UnivariateInterpolatorImpl$ implements MappingUFunc {
      public static final UnivariateInterpolatorImpl$ MODULE$ = new UnivariateInterpolatorImpl$();

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

      public UFunc.UImpl2 impl() {
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

            public Object apply(final UnivariateInterpolator k, final Object v) {
               return k.apply(v);
            }
         };
      }
   }

   public abstract static class HandyUnivariateInterpolator implements UnivariateInterpolator {
      private final Tuple2[] nodes;
      private final Object X;
      private final Object Y;
      private final Ordering ord;

      public final Object apply(final Object v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$(this, v, impl);
      }

      public final double apply$mDDc$sp(final double v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mDDc$sp$(this, v, impl);
      }

      public final float apply$mDFc$sp(final double v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mDFc$sp$(this, v, impl);
      }

      public final int apply$mDIc$sp(final double v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mDIc$sp$(this, v, impl);
      }

      public final double apply$mFDc$sp(final float v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mFDc$sp$(this, v, impl);
      }

      public final float apply$mFFc$sp(final float v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mFFc$sp$(this, v, impl);
      }

      public final int apply$mFIc$sp(final float v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mFIc$sp$(this, v, impl);
      }

      public final double apply$mIDc$sp(final int v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mIDc$sp$(this, v, impl);
      }

      public final float apply$mIFc$sp(final int v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mIFc$sp$(this, v, impl);
      }

      public final int apply$mIIc$sp(final int v, final UFunc.UImpl2 impl) {
         return VariableUFunc.apply$mIIc$sp$(this, v, impl);
      }

      public final Object apply(final Object v1, final Object v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$(this, v1, v2, impl);
      }

      public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mDIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mFIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl3 impl) {
         return VariableUFunc.apply$mIIIc$sp$(this, v1, v2, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl4 impl) {
         return VariableUFunc.apply$(this, v1, v2, v3, impl);
      }

      private Tuple2[] nodes() {
         return this.nodes;
      }

      public Object X() {
         return this.X;
      }

      public Object Y() {
         return this.Y;
      }

      private Ordering ord() {
         return this.ord;
      }

      public Object apply(final Object x) {
         return !this.ord().mkOrderingOps(x).$less(.MODULE$.array_apply(this.X(), 0)) && !this.ord().mkOrderingOps(x).$greater(.MODULE$.array_apply(this.X(), scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.genericArrayOps(this.X())) - 1)) ? this.interpolate(x) : this.extrapolate(x);
      }

      public abstract Object interpolate(final Object x);

      public Object extrapolate(final Object x) {
         throw new IndexOutOfBoundsException((new StringBuilder(21)).append("Out of the domain [").append(.MODULE$.array_apply(this.X(), 0)).append(",").append(.MODULE$.array_apply(this.X(), scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.genericArrayOps(this.X())) - 1)).append("]").toString());
      }

      public int bisearch(final Object x) {
         return this.bisearch(0, .MODULE$.array_length(this.X()) - 1, x);
      }

      private int bisearch(final int low, final int high, final Object x) {
         while(true) {
            int var5 = (low + high) / 2;
            switch (var5) {
               default:
                  if (low == high) {
                     return var5;
                  }

                  if (this.ord().mkOrderingOps(.MODULE$.array_apply(this.X(), var5)).$less(x)) {
                     int var10000 = var5 + 1;
                     x = x;
                     high = high;
                     low = var10000;
                  } else {
                     x = x;
                     high = var5;
                     low = low;
                  }
            }
         }
      }

      public HandyUnivariateInterpolator(final Vector x_coords, final Vector y_coords, final ClassTag evidence$1, final Field evidence$2, final Ordering evidence$3) {
         VariableUFunc.$init$(this);
         if (x_coords.size() != scala.Predef..MODULE$.genericWrapArray(x_coords.toArray(evidence$1)).toSet().size()) {
            throw new Exception("x coordinates must be unique");
         } else if (x_coords.size() != y_coords.size()) {
            throw new Exception("x_coords and y_coords must be of the same size");
         } else if (x_coords.size() == 0) {
            throw new Exception("need to provide at least one pair of coordinates");
         } else {
            this.nodes = (Tuple2[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.genericArrayOps(x_coords.toArray(evidence$1)), scala.Predef..MODULE$.genericWrapArray(y_coords.toArray(evidence$1)))), (n) -> n._1(), evidence$3);
            this.X = scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.nodes()), (n) -> n._1(), evidence$1);
            this.Y = scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.nodes()), (n) -> n._2(), evidence$1);
            this.ord = (Ordering)scala.Predef..MODULE$.implicitly(evidence$3);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface UnivariateInterpolator extends VariableUFunc {
      Object apply(final Object x);
   }
}
