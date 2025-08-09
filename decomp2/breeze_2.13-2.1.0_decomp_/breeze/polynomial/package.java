package breeze.polynomial;

import breeze.generic.UFunc;
import breeze.generic.VariableUFunc;
import scala.reflect.ScalaSignature;
import spire.math.poly.PolyDense;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eq!\u0002\u000e\u001c\u0011\u0003\u0001c!\u0002\u0012\u001c\u0011\u0003\u0019\u0003\"\u0002\u0016\u0002\t\u0003Ys!\u0002\u0017\u0002\u0011\u0003ic!B\u0018\u0002\u0011\u0003\u0001\u0004\"\u0002\u0016\u0005\t\u00039t!\u0002\u001d\u0005\u0011\u0007Id!B\u001e\u0005\u0011\u0003a\u0004\"\u0002\u0016\b\t\u0003I\u0006\"\u0002.\b\t\u0003Y\u0006b\u00021\b\u0003\u0003%I!Y\u0004\u0006U\u0012A\u0019a\u001b\u0004\u0006Y\u0012A\t!\u001c\u0005\u0006U1!\t!\u001e\u0005\u000652!\tA\u001e\u0005\bA2\t\t\u0011\"\u0003b\u000f\u0015IH\u0001c\u0001{\r\u0015YH\u0001#\u0001}\u0011\u0019Q\u0013\u0003\"\u0001\u0002\u0004!1!,\u0005C\u0001\u0003\u000bAq\u0001Y\t\u0002\u0002\u0013%\u0011M\u0002\u0003B\u0003\u0005\u0011\u0005\u0002C$\u0016\u0005\u000b\u0007I\u0011\u0001%\t\u0011Y+\"\u0011!Q\u0001\n%CQAK\u000b\u0005\u0002]C\u0011\"a\u0003\u0002\u0003\u0003%\u0019!!\u0004\u0002\u000fA\f7m[1hK*\u0011A$H\u0001\u000ba>d\u0017P\\8nS\u0006d'\"\u0001\u0010\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"!I\u0001\u000e\u0003m\u0011q\u0001]1dW\u0006<Wm\u0005\u0002\u0002IA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u0011\u0002\u0019\u0011,gn]3Q_2Lh/\u00197\u0011\u00059\"Q\"A\u0001\u0003\u0019\u0011,gn]3Q_2Lh/\u00197\u0014\u0007\u0011!\u0013\u0007\u0005\u00023k5\t1G\u0003\u00025;\u00059q-\u001a8fe&\u001c\u0017B\u0001\u001c4\u0005\u0015)f)\u001e8d)\u0005i\u0013A\u00033pk\ndW-S7qYB\u0011!hB\u0007\u0002\t\tQAm\\;cY\u0016LU\u000e\u001d7\u0014\u0007\u001d!S\bE\u0003;}\u0001\u001b6+\u0003\u0002@k\t)\u0011*\u001c9meA\u0011a&\u0006\u0002\u0016!>d\u0017\u0010R3og\u0016,f)\u001e8d/J\f\u0007\u000f]3s'\r)Be\u0011\t\u0005e\u00113\u0005)\u0003\u0002Fg\tia+\u0019:jC\ndW-\u0016$v]\u000et!AL\u0002\u0002\u0003A,\u0012!\u0013\t\u0004\u0015F\u001bV\"A&\u000b\u00051k\u0015\u0001\u00029pYfT!AT(\u0002\t5\fG\u000f\u001b\u0006\u0002!\u0006)1\u000f]5sK&\u0011!k\u0013\u0002\n!>d\u0017\u0010R3og\u0016\u0004\"!\n+\n\u0005U3#A\u0002#pk\ndW-\u0001\u0002qAQ\u0011\u0001\t\u0017\u0005\u0006\u000fb\u0001\r!\u0013\u000b\u0002s\u0005)\u0011\r\u001d9msR\u00191\u000b\u00180\t\u000buK\u0001\u0019\u0001!\u0002\u0003-DQaX\u0005A\u0002M\u000b\u0011A^\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002EB\u00111\r[\u0007\u0002I*\u0011QMZ\u0001\u0005Y\u0006twMC\u0001h\u0003\u0011Q\u0017M^1\n\u0005%$'AB(cU\u0016\u001cG/A\beK:\u001cXMV3di>\u0014\u0018*\u001c9m!\tQDBA\beK:\u001cXMV3di>\u0014\u0018*\u001c9m'\raAE\u001c\t\u0006uy\u0002un\u001c\t\u0004aN\u001cV\"A9\u000b\u0005Il\u0012A\u00027j]\u0006dw-\u0003\u0002uc\nYA)\u001a8tKZ+7\r^8s)\u0005YGcA8xq\")QL\u0004a\u0001\u0001\")qL\u0004a\u0001_\u0006yA-\u001a8tK6\u000bGO]5y\u00136\u0004H\u000e\u0005\u0002;#\tyA-\u001a8tK6\u000bGO]5y\u00136\u0004HnE\u0002\u0012Iu\u0004RA\u000f A}z\u00042\u0001]@T\u0013\r\t\t!\u001d\u0002\f\t\u0016t7/Z'biJL\u0007\u0010F\u0001{)\u0015q\u0018qAA\u0005\u0011\u0015i6\u00031\u0001A\u0011\u0015y6\u00031\u0001\u007f\u0003U\u0001v\u000e\\=EK:\u001cX-\u0016$v]\u000e<&/\u00199qKJ$2\u0001QA\b\u0011\u00159\u0015\u00041\u0001J\u0001"
)
public final class package {
   public static PolyDenseUFuncWrapper PolyDenseUFuncWrapper(final PolyDense p) {
      return package$.MODULE$.PolyDenseUFuncWrapper(p);
   }

   public static class densePolyval$ implements UFunc {
      public static final densePolyval$ MODULE$ = new densePolyval$();

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
   }

   public static class PolyDenseUFuncWrapper implements VariableUFunc {
      private final PolyDense p;

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

      public PolyDense p() {
         return this.p;
      }

      public PolyDenseUFuncWrapper(final PolyDense p) {
         this.p = p;
         VariableUFunc.$init$(this);
      }
   }
}
