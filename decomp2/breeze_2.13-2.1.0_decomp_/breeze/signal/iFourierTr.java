package breeze.signal;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005);Qa\u0003\u0007\t\u0002E1Qa\u0005\u0007\t\u0002QAQ!I\u0001\u0005\u0002\tBqaI\u0001C\u0002\u0013\rA\u0005\u0003\u0004:\u0003\u0001\u0006I!\n\u0005\bu\u0005\u0011\r\u0011b\u0001<\u0011\u0019i\u0014\u0001)A\u0005y!9a(\u0001b\u0001\n\u0007y\u0004B\u0002#\u0002A\u0003%\u0001\tC\u0004F\u0003\t\u0007I1\u0001$\t\r%\u000b\u0001\u0015!\u0003H\u0003)Igi\\;sS\u0016\u0014HK\u001d\u0006\u0003\u001b9\taa]5h]\u0006d'\"A\b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AE\u0001\u000e\u00031\u0011!\"\u001b$pkJLWM\u001d+s'\r\tQc\u0007\t\u0003-ei\u0011a\u0006\u0006\u00021\u0005)1oY1mC&\u0011!d\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005qyR\"A\u000f\u000b\u0005yq\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003Au\u0011Q!\u0016$v]\u000e\fa\u0001P5oSRtD#A\t\u0002\u0019\u00114Hi\\;cY\u0016LeI\u0012+\u0016\u0003\u0015\u0002BAJ\u0014*e9\u0011!\u0003A\u0005\u0003Q}\u0011A!S7qYB\u0019!&L\u0018\u000e\u0003-R!\u0001\f\b\u0002\r1Lg.\u00197h\u0013\tq3FA\u0006EK:\u001cXMV3di>\u0014\bC\u0001\f1\u0013\t\ttC\u0001\u0004E_V\u0014G.\u001a\t\u0004U5\u001a\u0004C\u0001\u001b8\u001b\u0005)$B\u0001\u001c\u000f\u0003\u0011i\u0017\r\u001e5\n\u0005a*$aB\"p[BdW\r_\u0001\u000eIZ$u.\u001e2mK&3e\t\u0016\u0011\u0002\u001b\u001148i\\7qY\u0016D\u0018J\u0012$U+\u0005a\u0004\u0003\u0002\u0014(eI\na\u0002\u001a<D_6\u0004H.\u001a=J\r\u001a#\u0006%A\be[\u000e{W\u000e\u001d7fqJ\"\u0015J\u0012$U+\u0005\u0001\u0005\u0003\u0002\u0014(\u0003\u0006\u00032A\u000b\"4\u0013\t\u00195FA\u0006EK:\u001cX-T1ue&D\u0018\u0001\u00053n\u0007>l\u0007\u000f\\3ye\u0011KeI\u0012+!\u00039!W\u000eR8vE2,'\u0007R%G\rR+\u0012a\u0012\t\u0005M\u001dB\u0015\tE\u0002+\u0005>\nq\u0002Z7E_V\u0014G.\u001a\u001aE\u0013\u001a3E\u000b\t"
)
public final class iFourierTr {
   public static UFunc.UImpl dmDouble2DIFFT() {
      return iFourierTr$.MODULE$.dmDouble2DIFFT();
   }

   public static UFunc.UImpl dmComplex2DIFFT() {
      return iFourierTr$.MODULE$.dmComplex2DIFFT();
   }

   public static UFunc.UImpl dvComplexIFFT() {
      return iFourierTr$.MODULE$.dvComplexIFFT();
   }

   public static UFunc.UImpl dvDoubleIFFT() {
      return iFourierTr$.MODULE$.dvDoubleIFFT();
   }

   public static Object withSink(final Object s) {
      return iFourierTr$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return iFourierTr$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return iFourierTr$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return iFourierTr$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return iFourierTr$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return iFourierTr$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return iFourierTr$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return iFourierTr$.MODULE$.apply(v, impl);
   }
}
