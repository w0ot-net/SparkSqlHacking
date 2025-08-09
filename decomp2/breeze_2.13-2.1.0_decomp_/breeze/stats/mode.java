package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!<Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0004\u0001BQ!Q\u0001\u0005\u0004\tCQ\u0001U\u0001\u0005\u0004ECQ\u0001X\u0001\u0005\u0004u\u000bA!\\8eK*\u0011\u0011BC\u0001\u0006gR\fGo\u001d\u0006\u0002\u0017\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u000f\u00035\t\u0001B\u0001\u0003n_\u0012,7cA\u0001\u0012/A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\u0004\"\u0001G\u000e\u000e\u0003eQ!A\u0007\u0006\u0002\u000f\u001d,g.\u001a:jG&\u0011A$\u0007\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\tQB]3ek\u000e,w\fR8vE2,WCA\u0011))\t\u0011s\u0007\u0005\u0003$I\u0019\nT\"A\u0001\n\u0005\u0015Z\"\u0001B%na2\u0004\"a\n\u0015\r\u0001\u0011)\u0011f\u0001b\u0001U\t\tA+\u0005\u0002,]A\u0011!\u0003L\u0005\u0003[M\u0011qAT8uQ&tw\r\u0005\u0002\u0013_%\u0011\u0001g\u0005\u0002\u0004\u0003:L\bc\u0001\b3i%\u00111\u0007\u0003\u0002\u000b\u001b>$WMU3tk2$\bC\u0001\n6\u0013\t14C\u0001\u0004E_V\u0014G.\u001a\u0005\u0006q\r\u0001\u001d!O\u0001\u0005SR,'\u000f\u0005\u0003;\u007f\u0019\"T\"A\u001e\u000b\u0005qj\u0014aB:vaB|'\u000f\u001e\u0006\u0003})\ta\u0001\\5oC2<\u0017B\u0001!<\u0005E\u0019\u0015M\u001c+sCZ,'o]3WC2,Xm]\u0001\u000fe\u0016$WoY3`\u0007>l\u0007\u000f\\3y+\t\u0019e\t\u0006\u0002E\u001dB!1\u0005J#H!\t9c\tB\u0003*\t\t\u0007!\u0006E\u0002\u000fe!\u0003\"!\u0013'\u000e\u0003)S!a\u0013\u0006\u0002\t5\fG\u000f[\u0005\u0003\u001b*\u0013qaQ8na2,\u0007\u0010C\u00039\t\u0001\u000fq\n\u0005\u0003;\u007f\u0015C\u0015\u0001\u0004:fIV\u001cWm\u0018$m_\u0006$XC\u0001*V)\t\u0019&\f\u0005\u0003$IQ3\u0006CA\u0014V\t\u0015ISA1\u0001+!\rq!g\u0016\t\u0003%aK!!W\n\u0003\u000b\u0019cw.\u0019;\t\u000ba*\u00019A.\u0011\tizDkV\u0001\u000be\u0016$WoY3`\u0013:$XC\u00010b)\tyf\r\u0005\u0003$I\u0001\u0014\u0007CA\u0014b\t\u0015IcA1\u0001+!\rq!g\u0019\t\u0003%\u0011L!!Z\n\u0003\u0007%sG\u000fC\u00039\r\u0001\u000fq\r\u0005\u0003;\u007f\u0001\u001c\u0007"
)
public final class mode {
   public static UFunc.UImpl reduce_Int(final CanTraverseValues iter) {
      return mode$.MODULE$.reduce_Int(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return mode$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Complex(final CanTraverseValues iter) {
      return mode$.MODULE$.reduce_Complex(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return mode$.MODULE$.reduce_Double(iter);
   }

   public static Object withSink(final Object s) {
      return mode$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return mode$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return mode$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return mode$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return mode$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return mode$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return mode$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return mode$.MODULE$.apply(v, impl);
   }
}
