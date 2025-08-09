package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.math.Semiring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u:Q\u0001B\u0003\t\u000211QAD\u0003\t\u0002=AQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0004\t\n1b\u00149Nk2\u001c6-\u00197be*\u0011aaB\u0001\n_B,'/\u0019;peNT!\u0001C\u0005\u0002\r1Lg.\u00197h\u0015\u0005Q\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u00055\tQ\"A\u0003\u0003\u0017=\u0003X*\u001e7TG\u0006d\u0017M]\n\u0005\u0003A1\u0012\u0004\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001b]I!\u0001G\u0003\u0003\r=\u0003H+\u001f9f!\tQR$D\u0001\u001c\u0015\ta\u0012\"A\u0004hK:,'/[2\n\u0005yY\"\u0001E#mK6,g\u000e^<jg\u0016,f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\tA\"A\fpa6+HnU2bY\u0006\u0014hI]8n'\u0016l\u0017N]5oOV\u00111\u0005\f\u000b\u0003IU\u0002R!\n\u0014+U)j\u0011!A\u0005\u0003O!\u0012Q!S7qYJJ!!K\u000e\u0003\u000bU3UO\\2\u0011\u0005-bC\u0002\u0001\u0003\u0006[\r\u0011\rA\f\u0002\u0002'F\u0011qF\r\t\u0003#AJ!!\r\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011cM\u0005\u0003iI\u00111!\u00118z\u0011\u001d14!!AA\u0004]\n!\"\u001a<jI\u0016t7-\u001a\u00134!\rA4HK\u0007\u0002s)\u0011!(C\u0001\u0005[\u0006$\b.\u0003\u0002=s\tA1+Z7je&tw\r"
)
public final class OpMulScalar {
   public static UFunc.UImpl2 opMulScalarFromSemiring(final Semiring evidence$3) {
      return OpMulScalar$.MODULE$.opMulScalarFromSemiring(evidence$3);
   }

   public static Object withSink(final Object s) {
      return OpMulScalar$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return OpMulScalar$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return OpMulScalar$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return OpMulScalar$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return OpMulScalar$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return OpMulScalar$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return OpMulScalar$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return OpMulScalar$.MODULE$.apply(v, impl);
   }
}
